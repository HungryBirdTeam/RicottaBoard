///////////////////////////////////////////////// 초기화 구문 /////////////////////////////////////////////////
var localStream;
var remoteStream;
const channelPeerConnectionsMap = new Map();
var isConnect = new Map();

var myInfo;
var users = new Set();

var pcConfig = {
    'iceServers': [{
        'urls': 'stun:stun.l.google.com:19302'
    }]
};

// Set up audio and video regardless of what devices are present.
var sdpConstraints = {
    offerToReceiveAudio: true,
    offerToReceiveVideo: true
};

var socket = io.connect('http://localhost:8080');

//채널 접속
var channel = "tryIt";
///////////////////////////////////////////////// 초기화 구문 /////////////////////////////////////////////////



/////////////////////////////////////////////// 리스너 연결 구문 ///////////////////////////////////////////////
socket.emit('join channel', channel);

socket.on('member', member => {
    if (member != myInfo) {
        if (!users.has(member)) {
            console.log("새 멤버", member);
            users.add(member);
            createPeerConnection(member);
        }

    }
})

socket.on('alert', () => {
    var info = { channel: channel, member: myInfo };
    socket.emit('alert member', info);
})

socket.on('new member', user => {
    if (myInfo == undefined) {
        myInfo = user;
        console.log("myInfo : ", user);
    } else {
        // var info = { user: myInfo, channel: channel };
        socket.emit('new member', channel);
    }
})

socket.on('sender info', function(connect) {
    if (connect.sender != myInfo && connect.receiver == myInfo) {
        var sdp = connect.sdp;
        var sender = connect.sender;

        channelPeerConnectionsMap.get(sender).setRemoteDescription(new RTCSessionDescription(sdp));
        doAnswer(sender);
    }

});

socket.on('receiver info', function(connect) {
    if (connect.sender != myInfo && connect.receiver == myInfo) {
        var sdp = connect.sdp;
        var sender = connect.sender;

        channelPeerConnectionsMap.get(sender).setRemoteDescription(new RTCSessionDescription(sdp));
        channelPeerConnectionsMap.get(sender).addStream(localStream);
        console.log(channelPeerConnectionsMap.get(sender).connectionState)
        console.log("connect complete!");
    }

});

var localVideo = document.querySelector('#localVideo');
var remoteVideo = document.querySelector('#remoteVideo');
var btn = document.querySelector('#btn');


btn.onclick = function() {
    navigator.mediaDevices.getUserMedia({
            audio: true,
            video: true
        })
        .then(gotStream)
        .catch(function(e) {
            alert('getUserMedia() error: ' + e.name);
        });
};
/////////////////////////////////////////////// 리스너 연결 구문 ///////////////////////////////////////////////



////////////////////////////////////////////////// 실행 코드 //////////////////////////////////////////////////
// createPeerConnection();

////////////////////////////////////////////////// 실행 코드 //////////////////////////////////////////////////



////////////////////////////////////////////////// 함수 영역 //////////////////////////////////////////////////
function createOffer() {
    channelPeerConnectionsMap.forEach((value, key) => {
        if (!isConnect.get(key)) {
            value.createOffer(
                sessionDescription => {
                    setLocalAndSendMessage(sessionDescription, key);
                },
                handleCreateOfferError);
            isConnect.set(key, true);
        }
    })
}
// channelPeerConnectionsMap[receiver].createOffer(
//     sessionDescription => {
//         setLocalAndSendMessage(sessionDescription, receiver);
//     },
//     handleCreateOfferError);
// }

function doAnswer(sender) {
    channelPeerConnectionsMap.get(sender).createAnswer().then(
        sessionDescription => {
            setLocalAndSendMessage(sessionDescription, sender);
        },
        onCreateSessionDescriptionError
    );
}

function createPeerConnection(member) {
    try {

        if (!channelPeerConnectionsMap.has(member)) {
            var pc = new RTCPeerConnection(null);
            pc.onicecandidate = handleIceCandidate;
            pc.onaddstream = handleRemoteStreamAdded;
            pc.onremovestream = handleRemoteStreamRemoved;

            channelPeerConnectionsMap.set(member, pc);
            isConnect.set(member, false);
            console.log(member, "와의 커넥션 객체 생성");
        }

    } catch (e) {
        console.log('Failed to create PeerConnection, exception: ' + e.message);
        alert('Cannot create RTCPeerConnection object.');
        return;
    }
}


//peer listener//
function handleIceCandidate(event) {
    console.log('icecandidate event: ', event);
    if (event.candidate) {
        sendMessage({
            event: event,
            type: 'candidate',
            label: event.candidate.sdpMLineIndex,
            id: event.candidate.sdpMid,
            candidate: event.candidate.candidate
        });
    } else {
        console.log('End of candidates.');
    }
}

//해당 유저 리모트 화면에 Stream을 보여줘야 한다.
function handleRemoteStreamAdded(event) {
    console.log('Remote stream added.');
    remoteStream = event.stream;
    remoteVideo.srcObject = remoteStream;
}

function handleRemoteStreamRemoved(event) {
    console.log('Remote stream removed. Event: ', event);
}

/////////////////////////////////


// peer connection result//

function setLocalAndSendMessage(sessionDescription, user) {
    channelPeerConnectionsMap.get(user).setLocalDescription(sessionDescription);
    console.log('setLocalAndSendMessage sending message', sessionDescription);

    if (sessionDescription.type == "offer") {
        console.log(myInfo, "offer to", user)
        var connect = {};
        connect.channel = channel;
        connect.sdp = sessionDescription;
        connect.sender = myInfo;
        connect.receiver = user

        socket.emit('offer connect', connect);
    } else if (sessionDescription.type == "answer") {
        console.log(myInfo, "answer to", user);
        var connect = {};
        connect.channel = channel;
        connect.sdp = sessionDescription;
        connect.sender = myInfo;
        connect.receiver = user;

        socket.emit('answer connect', connect);
    }

}

function onCreateSessionDescriptionError(error) {
    trace('Failed to create session description: ' + error.toString());
}


function handleCreateOfferError(event) {
    console.log('createOffer() error: ', event);
}

/////////////////////////////////



function gotStream(stream) {
    console.log('Adding local stream.');
    localStream = stream;
    localVideo.srcObject = stream;
    createOffer();
}


////////////////////////////////////////////////// 함수 영역 //////////////////////////////////////////////////