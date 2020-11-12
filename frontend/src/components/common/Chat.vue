<template>
  <div class="chat">
      <v-btn
        class="chat-button justify-center ma-3"
        fab
        @click="chattingBox = !chattingBox"
        width="50px"
        height="50px"
        :color="gradColor()"
      >
        <v-icon color="white" size="28px">mdi-chat-processing</v-icon>
      </v-btn>
    <v-navigation-drawer
      v-model="chattingBox"
      :permanent="chattingBox"
      app
      overflow
      right
    >
      <div class="chat-container" id="chatContainer">
        <div id="chattingBox">
          <div class="chat-header" id="chatHeader">
            <div>
              <span id="username">{{ this.naname }}</span><br>
              <span @click="showList">접속자 보기</span>
            </div>
            <v-icon @click="chattingBox = false" >mdi-close</v-icon>
            <div id="clientList" v-show="isList">
              <ul>
                <li id="user" v-for="(user, index) in clientList" :key="index">
                  {{ user }}
                </li>
              </ul>
            </div>
          </div>

          <div class="chatbox" id="chatBox">
            <div class="goodchat-bubble bubble">매너 채팅 해주세요 :)</div>
          </div>

          <form>
            <div class="text-box" id="textBox">
              <v-text-field label="메시지 전송"
                v-model="chatlog.message"
                id="msgForm"
                @keyup.enter="enter"
              >
              </v-text-field>
              <input type="text" style="display:none;"/> 
              <v-icon 
                @click="sendChat"
                class="text-box-icon"
                v-show="!chatlog.message"
              >mdi-send</v-icon>
              <v-icon 
                @click="sendChat"
                class="text-box-icon"
                v-show="chatlog.message"
                color="blue"
              >mdi-send</v-icon>
            </div>
          </form>
        </div>
      </div>
    </v-navigation-drawer>

  </div>
</template>



<script>
// import ChatlogDataService from "../../services/ChatlogDataService"

export default {
  name: "Chat",
  components: {},
  created() {
    var myname = this.$store.getters.userData.nickname;
    if (this.$store.getters.userData.nickname == "") {
      myname = "Unknown_" + this.makeRandomName();
    }
    var chatcontainer = document.getElementById("chatContainer");
    var chatheader = document.getElementById("chatHeader");
    var chatbox = document.getElementById("chatBox");
    var textbox = document.getElementById("textBox");
    var $msgForm = $("#msgForm").val();
    this.naname = myname;
    this.Channel = localStorage.getItem("wsboard.channelId");

    // console.log('name is: ' + this.naname);
    // console.log('channel is: ' + this.Channel);

    // console.log("SOCKET IS @@@@@@: ");
    // console.log(this.$socket);
    this.$socket._callbacks.$clientList = undefined;
    this.$socket._callbacks.$enter = undefined;
    this.$socket._callbacks.$out = undefined;
    this.$socket._callbacks.$s2c_chat = undefined;
    this.$socket._callbacks.$s2c_chat_me = undefined;
    this.$socket._callbacks.$s2c_text = undefined;

    this.$socket.emit("login", {
      name: this.naname,
      userid: this.naname,
      channelId: this.Channel,
    });

    this.$socket.on("enter", (data) => {
      // this.chatLogs.push(data.name + "님이 접속하셨습니다");
      // this.chatComes.push(data.name);
      $(".chatbox").append(
        '<div class="inout-bubble">' + data + "님이 입장하셨습니다.</div>"
      );
    });

    this.$socket.on("clientList", (data) => {
      var exceptme = [];
      //this.clientList = data;
      for (var i = 0; i < data.length; i++) {
        if (data[i] === myname) {
          continue;
        } else exceptme.push(data[i]); // 배열에 나의 정보는 빼고 넣어주었다
      }
      this.clientList = exceptme;
    });

    // 내 메시지는 띄우지 말야아함.
    this.$socket.on("s2c_chat", (data) => {
      var name = data.from.name;
      var msg = data.msg;

      if (name === this.naname) {
        // 내 이름하고 같을 경우 채팅창에 띄워주지 않는다.
        console.log("지금 내 이름 : " + this.naname);
      } else
        $(".chatbox").append(
          '<div class="friend-bubble bubble">(' + name + "님) " + msg + "</div>"
        );

      if (!this.chattingBox) {
        this.notread += 1;
        console.log("안읽은 메시지 수 : " + this.notread);
      }

      setTimeout(function () {
        $(".chatbox").scrollTop($(".chatbox").prop("scrollHeight"));
      }, 50);
    });

    this.$socket.on("s2c_text", (data) => {
      var name = data.from.name;
      var msg = data.msg;

      $(".textBoard").append("<h3><span>" + msg + "</span></h3>");
    });

    this.$socket.on("s2c_chat_me", (data) => {
      var name = data.from.name;
      var msg = data.msg;

      $(".chatbox").append('<div class="my-bubble bubble">' + msg + "</div>");

      setTimeout(function () {
        $(".chatbox").scrollTop($(".chatbox").prop("scrollHeight"));
      }, 50);
    });

    this.$socket.on("out", (data) => {
      // if(!(data.from.name).eqauls("undefnied"))
      $(".chatbox").append(
        '<div class="inout-bubble">' +
          data.from.name +
          "님이 나가셨습니다.</div>"
      );
    });
  },

  destroyed() {
    this.$socket.emit("disconnect2", {});
  },

  data() {
    return {
      chatlogs: [],
      chattingBox: false,
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
      },
      chatDrawer: false,
    };
  },
  methods: {
    saveChatlog() {
      event.preventDefault(); // 줄바꿈 방지?
      event.stopPropagation();

      var data = {
        message: this.chatlog.message,
        userid: this.naname,
        roomid: this.Channel,
      };
    },

    sendChat() {
      event.preventDefault(); // 줄바꿈 방지?
      event.stopPropagation();
      console.log("msgForm : " + this.chatlog.message);
      console.log("channel : " + this.Channel);

      if (this.chatlog.message === "") pass;
      else {
        this.$socket.emit("chat", { msg: this.chatlog.message });
        this.chatlog.message = "";
        // this.saveChatlog();
      }
    },

    sendToBoard() {
      event.preventDefault(); // 줄바꿈 방지?
      event.stopPropagation();
      var $msgForm = $("#msgForm").val();

      this.$socket.emit("text", { msg: $msgForm });
      $("#msgForm").val("");

      //this.saveChatlog();
    },

    enter() {
      // 엔터 처리
      var code = event.keyCode;
      if (code == 13) {
        if (event.shiftKey === true) {
          // Shift + Enter 처리
        } else {
          this.sendChat();
          //this.saveChatlog();
        }
      }
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

    showList() {
      if (this.isList) this.isList = false;
      else this.isList = true;
    },

    gradColor() {
      if(this.chattingBox) {
        return "#08543A"
      }
      return "#0d875c"
    }
  },
};
</script>

<style>
.chat-button {
  position: fixed;
  z-index: 3;
  bottom: 140px;
  left: 12px;
  width: 50px;
  height: 50px;
}


.chat-container {
  padding-left: 16px;
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.5);
  transition: width 0.3s ease;
  position: absolute;
  top: 70px;
  height: 100%;
  width: 100%;
}

.chat-header {
  display: flex;
  justify-content: space-between;
  background-color: white;
  border-bottom: 2px solid rgba(0, 0, 0, 0.3);
  padding: 8px 8px 8px 8px;
}

.chat-header button{
  padding-top: 5px;
  margin-bottom: auto;
}
.content {
  position: absolute;
  top: 10%;
  left: 5%;
  transform: translate(-50%, -50%);
  font-size: 10px;
  color: red;
  z-index: 2;
  text-align: center;
}

.chat-header #username {
  vertical-align: middle;
  font-size: 17px;
  font-weight: 500;
  margin-right: 20px;
  color: #343434;
}

.chat-header #userList {
  margin-right: 5%;
}
.chat-header #user {
  margin-left: 80%;
  margin-bottom: 2%;
  font-size: 13px;
  background-color: yellowgreen;
  border-radius: 10px 10px 10px 10px;
  padding: 7px 15px 7px 15px;
  float: left;
  clear: both;
}

/* chat box */

.chatbox {
  height: calc(100vh - 200px);
  background-color: white;
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
  background-color: lightslategray;
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
  background-color: lightpink;
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
  position: fixed;
  bottom:0;
  display: flex;
  background-color: white;
  border-top: 2px solid rgba(0, 0, 0, 0.3);
  padding: 10px;
}

.text-box-icon {
  margin-top: auto;
  margin-bottom: auto;
  margin-left: 10px;
  margin-right: 10px;
  padding: 2px;
}

#sendToBoard {
  background-color: skyblue;
  width: 60px;
  height: 60px;
  color: white;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  margin-left: 10px;
  float: left;
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
  text-shadow: 0 13.36px 8.896px #c4b59d, 0 -2px 1px #fff;
  letter-spacing: -4px;
}
</style>