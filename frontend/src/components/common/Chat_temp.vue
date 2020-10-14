<template>
  <div>
    <div style="border:solid 1px;">
      <h3>욕설 금지^^</h3>
      <p>{{name}}</p>
      <div>
        <p>현재 참석자는 {{chatComes}}</p>
        <div v-for="(chatMsg, idx) in chatMsgs" :key="idx">
          <!-- {{chatMsg.substring(0,1)}} -->

          <div style="margin:2px" v-if="naname!=chatMsg.name">{{chatMsg.name}}</div>
          <div style="margin:2px" v-if="naname==chatMsg.name">
            <br />
          </div>
          <div class="d-flex">
            <div
            class="ml-auto"
              v-if="naname==chatMsg.name"
              style="background-color:rgb(255, 235, 221); border-radius: 5%;  margin-right:4px; min-width:40vw"
            >{{chatMsg.msg}}</div>
            <div

            class=" mr-auto"
              v-if="naname!=chatMsg.name"
              style="background-color:rgb(255, 235, 221); border-radius: 5%; margin-left:4px; min-width:40vw"
            >{{chatMsg.msg}}</div>
          </div>
          <!-- <br>
          {{chatMsg.substring(chatMsg.substring(0,1)+1,chatMsg.substring(0,1)+4)}}-->
          <!-- <p v-for="chatMsg in chatMsgs" style="border:solid 1px; background-color:rgb(255, 235, 221); border-radius: 2%; margin:10px">
          {{chatMsg}}
            
          </p>-->
        </div>
      </div>
    </div>
    <form style="margin-top:5px  left:0; bottom:0;" class="form-inline">
      <div class="form-group" style="margin-top:3px; ">
        <label for="msgForm"></label>

        <input
          type="text"
          style="border:solid 1px;"
          placeholder="귓말 대상"
          class="form-control d-flex-6"
          id="other"
        />
        <!-- <v-overflow-btn
        style="margin:0 "
        background-color="rgb(230,230,230)"          
        :chatmem="chatmem"
        label="전체에게"
         class="form-control btn d-flex-1" id="other"
         dense
        >
        </v-overflow-btn>-->
        <input
          type="text"
          style="border:solid 1px;"
          placeholder="메시지를 입력해주세요 :)"
          class="form-control d-flex-3"
          id="msgForm"
        />
        <button style="border:solid 1px;" @click="sendMessage()">작성</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: "Chat",
  created() {

    var myname = this.makeRandomName();
    this.naname = myname;

    console.log(this.naname);
    
    this.$socket.emit("login", {
      //name: this.$store.state.name,
      name: myname,
      userid: myname,
    });

    this.$socket.on("login", (data) => {
      this.chatLogs.push(data.name + "님이 접속하셨습니다");
      this.chatComes.push(data.name);
    });

    this.$socket.on("s2c chat", (data) => {
      var name = data.from.name;
      var msg = data.msg;
      var chatMsg = {
        name: data.from.name,
        msg: data.msg,
      };
      this.chatMsgs.push(chatMsg);
    });

    this.$socket.on("out", (data) => {
      this.chatLogs.push(data.from.name + "님이 나가셨습니다.");
      this.chatComes.pop(data.from.name);
    });
    this.$socket.on(() => {});
  },
  data() {
    return {
      textarea: "",
      message: "",
      chatmem: [],
      chatLogs: [],
      chatComes: [],
      chatNames: [],
      chatMsgs: [],
      naname: "",
    };
  },
  methods: {
    sendMessage() {
      event.preventDefault();
      var $msgForm = $("#msgForm");
      var $other = $("#other");
      // 서버로 메시지를 전송한다.
      if ($other.val() == "") {
        // 서버로 메시지를 전송한다.
        this.$socket.emit("chat", { msg: $msgForm.val() });
        $msgForm.val("");
      } else {
        console.log("귓속말 대상 : " + $other.val());
        this.$socket.emit("chatto", { msg: $msgForm.val(), id: $other.val() });
        $msgForm.val("");
      }
      $('#chatLogs').scrollTop($('#chatLogs').prop('scrollHeight'));

                setTimeout(function(){
                    $('#chatLogs').scrollTop($('#chatLogs').prop('scrollHeight'));
                }, 300);

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
  },
};
</script>

<style>
</style>