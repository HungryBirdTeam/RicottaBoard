<template>
<div>


 <div class="chat-container" id="chatContainer">
   <div id="chattingBox" v-show="chattingBox">
        <div class="chat-header" id="chatHeader">
          <div id="clientList" v-show="isList">
            <ul>
                <li id="user"
                  v-for="(user, index) in clientList"
                  :key="index"
                >
                  {{ user }}
                </li>
             </ul>
          </div>
          <button id="minimize" class="header-btn" @click="minimize"></button>
          <button id="maximize" class="header-btn" @click="maximize"></button>
          <img id="profile-pic" src='../../assets/img/picture.jpg' width="1">
          <span>
            <a id="username">{{ this.naname }}</a>
            <img title="접속자 보기" id="showList-pic" src='../../assets/img/2.jpg' @click="showList"/>
          </span>

        </div>
    
        <div class="chatbox" id="chatBox">
          <div class="goodchat-bubble bubble">
            매너 채팅 해주세요 :)
          </div>

        </div>
    
        <form>
        <div class="text-box" id="textBox">
          <textarea v-model="chatlog.message" id="msgForm" placeholder="메시지를 입력해주세요 :)" @keyup="enter" ></textarea>
          <button id="sendChat" @click="sendChat" v-show="chatlog.message != ''">전송</button>
          <button id="sendChat_disable" disabled="true" v-show="chatlog.message == ''">전송</button>

          <button id="sendToBoard" @click=sendToBoard>보드로</button>
          <div class="clearfix"></div>
        </div>
       </form>
   </div>
 </div>
 
    <div class="chat-container2" id="chatContainer" v-if="!chattingBox">
       <div class="chat-header" id="chatHeader">
          <button id="minimize" class="header-btn" @click="minimize"></button>
          <button id="maximize" class="header-btn" @click="maximize"></button>
          <img id="profile-pic" src='../../assets/img/picture.jpg' width="1">
          <span>
            <a id="username" style="margin-right:52%">나</a>
            <img class="bell">
            {{notread}}
              
          </span>
          
        </div>
    </div>
 </div>



</template>



<script>

// import ChatlogDataService from "../../services/ChatlogDataService"
 
export default {
  name: "Chat",
  components:{
  },
  created() {
    //console.log("chanelName : "+ localStorage.getItem("wsboard.channelName")); 채널 이름 가져오는 부분
    var myname = this.$store.getters.userData.nickname;
    if(this.$store.getters.userData.nickname == ""){
      myname = "Unknown_"+this.makeRandomName();
    }
    // console.log("my nickname is : ", myname);
    var chatcontainer = document.getElementById("chatContainer");
    var chatheader = document.getElementById("chatHeader");
    var chatbox = document.getElementById("chatBox");
    var textbox = document.getElementById("textBox");
    var $msgForm = $('#msgForm').val();
    this.naname = myname;
    this.Channel = localStorage.getItem("wsboard.channelId");

    console.log('name is: ' + this.naname);
    console.log('channel is: ' + this.Channel);

    console.log("SOCKET IS @@@@@@: ");
    console.log(this.$socket);
    this.$socket._callbacks.$clientList = undefined;
    this.$socket._callbacks.$enter = undefined;
    this.$socket._callbacks.$out = undefined;
    this.$socket._callbacks.$s2c_chat = undefined;
    this.$socket._callbacks.$s2c_chat_me = undefined;
    this.$socket._callbacks.$s2c_text = undefined;


    this.$socket.emit("login", {
      name: this.naname,
      userid: this.naname,
      channelId : this.Channel,
    });

    
    this.$socket.on("enter", (data) => {
      // this.chatLogs.push(data.name + "님이 접속하셨습니다");
      // this.chatComes.push(data.name);
      $('.chatbox').append('<div class="inout-bubble">'+data+'님이 입장하셨습니다.</div>');
    
    });

    this.$socket.on("clientList", (data) => {
      console.log("접속자 : ");
      var exceptme = [];
      //this.clientList = data;
      for(var i=0; i<data.length; i++){
        if(data[i] === myname){
          continue;
        }
        else exceptme.push(data[i]);  // 배열에 나의 정보는 빼고 넣어주었다
      }
      this.clientList = exceptme;
      console.log(this.clientList);
    });

    // 내 메시지는 띄우지 말야아함.
    this.$socket.on("s2c_chat", (data) => {
      var name = data.from.name;
      var msg = data.msg;

    if(name === this.naname){ // 내 이름하고 같을 경우 채팅창에 띄워주지 않는다.
        console.log("지금 내 이름 : "+this.naname);
      }
      else $('.chatbox').append('<div class="friend-bubble bubble">('+name+'님) '+msg+'</div>');

      if(!this.chattingBox){
        
        this.notread += 1;
        console.log("안읽은 메시지 수 : "+this.notread);
      }

      setTimeout(function(){
                    $('.chatbox').scrollTop($('.chatbox').prop('scrollHeight'));
                }, 50);
    });

    this.$socket.on("s2c_text", (data) => {
      var name = data.from.name;
      var msg = data.msg;

      $('.textBoard').append('<h3><span>'+msg+'</span></h3>');

    });

    this.$socket.on("s2c_chat_me", (data) => {
      var name = data.from.name;
      var msg = data.msg;
    
      $('.chatbox').append('<div class="my-bubble bubble">'+msg+'</div>');

      setTimeout(function(){
              $('.chatbox').scrollTop($('.chatbox').prop('scrollHeight'));
          }, 50);
    });

    this.$socket.on("out", (data) => {
      console.log("나갔습니다!!");
      // if(!(data.from.name).eqauls("undefnied")) 
      $('.chatbox').append('<div class="inout-bubble">'+data.from.name+'님이 나가셨습니다.</div>');
    
    });
  },

  destroyed() {
    console.log('Chat destoryed');
    this.$socket.emit("disconnect2", {});
  },

  data() {
    return {
      chatlogs: [],
      chattingBox: true,
      isList: false,
      clientList: [],
      textarea: "",
      message: "",
      chatmem: [],
      chatLogs: [],
      chatComes: [],
      chatNames: [],
      chatMsgs: [],
      naname: "",
      notread: 0,

      chatlog: {
        id: null,
        message: "",
        userid: "",
        roomid: "",
      }

      
    };
  },
  methods: {
    saveChatlog(){
      event.preventDefault(); // 줄바꿈 방지?
      event.stopPropagation();

      var data = {
        message: this.chatlog.message,
        userid: this.naname,
        roomid:  this.Channel,
      };

      console.log("나네임 : "+this.naname);


      // ChatlogDataService.create(data)
      //   .then(response => { 
      //     this.chatlog.id = response.data.id;
      //     console.log(response.data);
      //   })
      //   .catch(e => {
      //     console.log(e);
      //   });
    },


    sendChat() {
      event.preventDefault(); // 줄바꿈 방지?
      event.stopPropagation();
      var $msgForm = $('#msgForm').val();
      console.log("msgForm : "+$msgForm);
      console.log("channel : "+this.Channel);

      if($msgForm==='') alert("메시지를 입력해주세요");
      else{
      this.$socket.emit("chat", {msg: $msgForm});
      $('#msgForm').val("");


      this.saveChatlog();
      }
    },

    sendToBoard() {
      console.log('sendtoboard')
      event.preventDefault(); // 줄바꿈 방지?
      event.stopPropagation();
      var $msgForm = $('#msgForm').val();
      // console.log("msgForm : "+$msgForm);
      // console.log("channel : "+this.Channel);


      this.$socket.emit("text", {msg: $msgForm});
      $('#msgForm').val("");


      //this.saveChatlog();
    },


     enter() { // 엔터 처리
       var code = event.keyCode;
        if(code==13){
          
          if(event.shiftKey === true){ // Shift + Enter 처리
            //console.log("Shift도 눌러짐");
          
          }
          else{
              
              this.sendChat();
             //this.saveChatlog();
          
         }
        }
       
    },

    minimize(){
      this.chattingBox = false;
      //alert("최소화");
    },
    maximize(){
      this.chattingBox = true;
      this.notread = 0;
    },

    makeRandomName() {
      var name = "";
      var possible = "abcdefghijklmnopqrstuvwxyz";
      for (var i = 0; i < 3; i++) {
        name += possible.charAt(Math.floor(Math.random() * possible.length));
      }
      return name;
      // var name = 1;
      // return name + Math.random()*10;
    },

    showList(){
      
      if(this.isList) this.isList=false;
      else this.isList=true;
//       $('#msgForm').val("안녕");
    },

    // retrieveChatlogs(){
    //   ChatlogDataService.getAll()
    //     .then(response =>{
    //       var Logs = response.data;
    //       console.log("채팅로그 불러오기");
    //       console.log("LogRoom : "+Logs[0].roomid);
    //       console.log("channelName : "+this.Channel);
    //       //console.log(Logs[0]);
    //       console.log("logsname : "+Logs[0].userid);
    //       console.log("myname : "+this.naname);
          
    //       for(var i=0; i<Logs.length; i++){
    //         if(Logs[i].roomid === this.Channel){
    //           //console.log(Logs[i].message);
    //           //this.chatlogs.push(Logs[i]);
    //           if(Logs[i].userid === this.naname) $('.chatbox').append('<div class="my-bubble bubble">'+Logs[i].message+'</div>');
    //           else $('.chatbox').append('<div class="friend-bubble bubble">('+Logs[i].userid+'님) '+Logs[i].message+'</div>');
    //         }
    //       }
    //       console.log(Logs);

          
    //     })
    //     .catch(e =>{
    //       console.log(e);
    //     });
    // },
  },

  mounted(){
    // this.retrieveChatlogs();
  }
};
</script>

<style>
* {
    box-sizing: border-box;
  }
  
  body {
    /* background-image: url('../images/background.jpg'); */
    font-family: 'Noto Sans KR', sans-serif;
  }
  
  .chat-container {
    box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
    transition: width 0.3s ease;
    position: absolute;
    
    left: 75%;
    width: 25%;
    bottom: 0%;

  }
  
  .chat-header {
    background-color: white;
    padding: 30px 8px 8px 8px;
  }
  
  .chat-header .header-btn {
    border-radius: 50%;
    border: none;
    width: 12px;
    height: 12px;
    cursor: pointer;
    position: absolute;
    top: 8px;
    padding: 0;
  }
  
  .chat-header #close {
    background-color: #ff6059;
    left: 8px;
  }
  
  .chat-header #minimize {
    background-color: #ffbf2f;
    left: 10px;
  }
  
  .chat-header #maximize {
    background-color: #29cd42;
    left: 28px;
  }
  
  .chat-header #profile-pic {
    vertical-align: middle;
    border-radius: 50%;
    width: 12%;
    height: 12%;
    margin-right: 2%
  }
  
  .chat-header #showList-pic {
      vertical-align: middle;
      border-radius: 50%;
      width: 15%;
      height: 15%;
      margin-right: 2%;
      cursor: pointer;
    }

  .chat-header #bell-pic {
      vertical-align: middle;
      border-radius: 50%;
      width: 17%;
      height: 17%;
      margin-right: 2%;
      cursor: pointer;
    }

   .bell{
        position: relative;
        background-image: url('../../assets/img/bell.png');                                                               
        height: 50px;
        width: 50px;
        border-radius: 50%;
        background-size: cover;
        vertical-align: middle;
    }

    .content{
         position: absolute;
         top:10%;
         left:5%;
         transform: translate(-50%, -50%);                                                                   
         font-size:10px;
         color:red;
         z-index: 2;
         text-align: center;
    }

  .chat-header #username {
    vertical-align: middle;
    font-size: 17px;
    font-weight: 500;
    margin-right: 63%;
    color: #343434;
  }

  .chat-header #userList {
    margin-right: 5%;
  }
  .chat-header #user{
    margin-left: 80%;
    margin-bottom: 2%;
    font-size:13px;
    background-color:yellowgreen;
    border-radius: 10px 10px 10px 10px;
    padding: 7px 15px 7px 15px;
    float: left;
    clear: both;
  }

  /* only header */
  .chat-container2 {
    box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
    transition: width 0.3s ease;
    position: absolute;
    
    left: 75%;
    width: 25%;
    bottom: 0%;

  }
  
  
  /* chat box */
  
  .chatbox {
    height: 400px;
    background-color: #d7e4f2;
    padding: 10px;
    overflow-y: scroll;
    position: relative;
  }
  
  .bubble {
    margin: 5px 0;
    display: inline-block;
    max-width: 300px;
    font-size: 14px;
    position: relative;
  }
  
  .inout-bubble {
    background-color:lightslategray;
    border-radius: 14px 14px 14px 14px;
    padding: 7px 50px 7px 50px;
    float: left;
    clear: both;

    margin: 5px 0;
    max-width: 300px;
    font-size: 14px;
    position: relative;
  }

  .friend-bubble {
    background-color: white;
    border-radius: 14px 14px 14px 0;
    padding: 7px 15px 7px 15px;
    float: left;
    clear: both;
  }

  .goodchat-bubble {
    background-color:lightpink;
    border-radius: 14px 14px 14px 14px;
    padding: 7px 15px 7px 15px;
    float: left;
    clear: both;
  }
  
  .my-bubble {
    background-color: #fff46d;
    border-radius: 14px 14px 0px 14px;
    padding: 7px 15px 7px 15px;
    float: right;
    clear: both;
  }
  
  /* text box */
  
  .text-box {
    background-color: #fafafa;
    padding: 10px;
  }
  
  .text-box textarea {
    height: 60px;
    float: left;
    width: calc(100% - 140px);
    border-radius: 3px;
    background-color: #ffffff;
    border: solid 0.5px #d7d7d7;
    resize: none;
    padding: 10px;
    font-size: 14px;
  }
  
  #sendChat {
    background-color: orange;
    width: 60px;
    height: 60px;
    color: black;
    border: none;
    border-radius: 3px;
    cursor: pointer;
    margin-left: 10px;
    float: left;
  }

  #sendToBoard {
    background-color:skyblue;
    width: 60px;
    height: 60px;
    color: white;
    border: none;
    border-radius: 3px;
    cursor: pointer;
    margin-left: 10px;
    float: left;
  }

  #sendChat_disable {
    background-color: orange;
    color: gray;
    width: 60px;
    height: 60px;
    border: none;
    border-radius: 3px;
    cursor: initial;
    margin-left: 10px;
    float: left;
  }

  
  .clearfix {
    clear: both;
  }

  
h3 {
  /* margin: 20px; */
  font-family: "Paytone One";
  color: #202020;
  text-transform: uppercase;
  letter-spacing: -2px;
}
h3 span {
  display: block;
  margin: 11px 0 17px 0;
  font-size: 80px;
  line-height: 80px;
  color: orange;
  text-shadow: 0 13.36px 8.896px #c4b59d,0 -2px 1px #fff;
  letter-spacing: -4px;
}
</style>