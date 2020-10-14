<template >
    <div class="" id="board" @click="cloakMoveable" @mousemove="mouseMoveEvent">



          <Moveable
          ref="moveable"
          class="moveable"
          v-bind="moveable"
          @drag="handleDrag"
          @resize="handleResize"
          @scale="handleScale"
          @rotate="handleRotate"
          @warp="handleWarp"
          style="display: none;"
          >
          </Moveable> 

          <!-- 도구상자 -->
          <v-toolbar class=" toolBox" @dblclick="changeTargetAction" >
            <!-- <v-btn icon color="orange" @click="createText">
              <v-icon>mdi-sticker</v-icon>
            </v-btn> -->

            <v-btn icon color="orange" @click="createCanvas">
              <v-icon>mdi-palette</v-icon>
            </v-btn>

            <v-btn icon color="orange" @click="createPoll">
              <v-icon>mdi-vote</v-icon>
            </v-btn>

            <v-btn icon color="orange" @click="createMap">
              <v-icon>mdi-map</v-icon>
            </v-btn>
          </v-toolbar>


        <div class="bodyBox " ref="whiteBoard" @dblclick="focusAction"
        @click="changeTargetAction"
        
        @wheel="wheelEvent"
        style="height: 75vh; width: 80vw;">

          <div class="MoveableBox realBoard" >

            <Postit 
            v-for="(a, idx) in counter.textC"
            :key="idx"
            style="position: relative;
                  display: inline-block"
            :uid="board.postits[a].boardId"
            :title="board.postits[a].writer"
            :content="board.postits[a].contents"
            v-on:setTitle="changePITitle"
            v-on:setContent="changePIContent"/> 

              <Canvas @dblclick="focusAction"
            @click="changeTargetAction"
            @click.right="deleteTargetAction"
            v-for="(a, idx) in counter.canvasC"
            :key="idx"
            class="moveable3"  />

            <Poll
            v-for="(a, idx) in counter.pollC"
            :key="idx"
            style="position: relative;
                  display: inline-block"
            />

            <Map 
            @dblclick="focusAction"
            @click="changeTargetAction"
            @click.right="deleteTargetAction"
            v-for="(a, idx) in counter.mapC"
            :key="idx"
            class="moveable2" 
            />
          </div>
        </div>
        
    </div>
</template>


<script>
import SockJS from 'sockjs-client';
import Stomp from 'stomp-websocket';
import http from '../../http-common.js';

// import Canvas from "../../components/common/Canvas";
import Moveable from 'vue-moveable';
import Postit from '../../components/module/Postit';
import Poll from '../../components/common/Poll';
import Map from '../../components/module/Map';

const pI = `<div  class="moveable" @dblclick="dblclickEv"   @click="clickEv"
                ref="contentTextArea" readonly="readonly"
                name="" id="asdfasdf"
                draggable="false"
               >
                it's Post it!
                </div>`
const boardLength = 3000;

export default {
  name: "app",
  components: {
    Moveable,
    // Canvas,
    Postit,
    Poll,
    Map,
  },
  created() { 
    // console.log(document.querySelector('.moveable-control-box'));
    // 우클릭 기본이벤트 차단
    window.oncontextmenu = function() {
      return false;
    };
    // const BASE_URL = "http://localhost:8080"
    // // websocket & stomp initialize
    // var sock = new SockJS(BASE_URL + "/ws-stomp");
    // var ws = Stomp.over(sock);

    
    // this.init();


  },
  mounted() {
    document.querySelector('.realBoard').style.height = boardLength+"px";
    document.querySelector('.realBoard').style.width = boardLength+"px";

    console.log((boardLength/2) - (window.innerWidth * 0.4));
    document.querySelector('.realBoard').style.left = 
      -(boardLength/2) + (window.innerWidth * 0.4) + "px";
      
    document.querySelector('.realBoard').style.top = 
      -(boardLength/2) + (window.innerHeight * 0.4) + "px";
  },
  data: () => ({
    moveable: {
      target: '',
      draggable: true,
      throttleDrag: 1,
      resizable: false,
      throttleResize: 1,
      keepRatio: false,
      scalable: true,
      throttleScale: 0,
      rotatable: true,
      throttleRotate: 0,
      origin: false,
    },
    text: "",
    counter: {
      textC: [],
      // canvasC: [],
      mapC: [],
      pollC: [],
    },
    board: {
      idCounter: 0, 
      postits: [],
      polls: [],
    },
    channelId: '',
    channelName: '',
    sender: '',
    postit: '',
    postitList: [],
    token: '',
    userCount: 0,
    boardScale: 1,
  }),
  methods: {
    // connect() {
    //   // this.roomId = localStorage.getItem('wschat.roomId');
    //   // this.roomName = localStorage.getItem('wschat.roomName');
    //   this.channelId = "5a43b95b-5911-4fe3-b6ad-f0c53dca77c0"
    //   this.channelName = "1234";
    //   var _this = this;
    //   const BASE_URL = "http://localhost:8080"
    //   console.log("axios 이전")
    //   axios.get("http://localhost:8080/board/channel").then(response => {
    //     console.log("axios 요청 성공")
    //       _this.token = response.data.token;
    //       ws.connect({"token":_this.token}, function(frame) {
    //           ws.subscribe("/sub/board/channel/"+_this.channelId, function(message) {
    //               var recv = JSON.parse(message.body);
    //               _this.recvMessage(recv);
    //           });
    //       }, function(error) {
    //           alert("서버 연결에 실패 하였습니다. 다시 접속해 주십시요.");
    //           location.href="/board/channel";
    //       });
    //   }).catch(err => {console.log(err)});
      
    // },
    // init() {
    //   var BASE_URL =  "http://localhost:8080"
    //   var sock = new SockJS(BASE_URL + "/ws-stomp");
    //   var ws = Stomp.over(sock);
    //   this.ws = ws;

    //   this.channelId = localStorage.getItem('wsboard.channelId');
    //   this.channelName = localStorage.getItem('wsboard.channelName');
    //   var _this = this;
    //   http.get('/board/user').then(response => {
    //       _this.token = response.data.token;
    //       ws.connect({"token":_this.token}, function(frame) {
    //           ws.subscribe("/sub/board/channel/"+_this.channelId, function(message) {
    //               var recv = JSON.parse(message.body);
    //               _this.recvMessage(recv);
    //           });
    //       }, function(error) {
    //           alert("서버 연결에 실패 하였습니다. 다시 접속해 주십시요.");
    //           location.href="/board/channel";
    //       });
    //   });
    // },
    sendMessage: function(type) {
        ws.send("/pub/board/message", {"token":this.token}, JSON.stringify({channelId:this.channelId, postitList:this.postitList}));
        this.postit = '';
    },
    recvMessage: function(recv) {
        this.userCount = recv.userCount;
        this.postitList.unshift({"sender":recv.sender,"postitList":recv.postitList})
    },

    handleDrag({ target, left, top }) {
      target.style.left = `${left}px`;
      target.style.top = `${top}px`;
      // console.log(target);
      // console.log(document.querySelector('.bodyBox').style.width);
      // console.log(window.innerWidth * 0.8);
      if(target.getAttribute('class') != null){
        var clas = target.getAttribute('class').split(' ');
        
        for(var cla in clas){
          if(clas[cla] == 'realBoard'){
            // console.log(target.style.left, " ",target.style.top);
            let lp = target.style.left.replace("px", "");
            let tp = target.style.top.replace("px", "");
            console.log(lp, " , ", tp);
            if(lp > 0) {
              target.style.left = '1px'
              document.querySelector('.bodyBox').style.borderLeft = "red 3px solid";
            }
            else if(((lp*-1) + window.innerWidth ) > (boardLength )) {
              target.style.left = (-boardLength + window.innerWidth)+'px';
              document.querySelector('.bodyBox').style.borderRight = "red 3px solid";
            } 
            else {
              document.querySelector('.bodyBox').style.borderLeft = "none";
              document.querySelector('.bodyBox').style.borderRight = "none";
            }

            if(tp > 0) {
              target.style.top = '1px'
              document.querySelector('.bodyBox').style.borderTop = "red 3px solid";
            }
            else if ((tp*-1) + window.innerHeight > boardLength) {
              target.style.top = (-boardLength + window.innerHeight)+'px';
              document.querySelector('.bodyBox').style.borderBottom = "red 3px solid";
            }
            else {
              document.querySelector('.bodyBox').style.borderTop = "none";
              document.querySelector('.bodyBox').style.borderBottom = "none";
            }

            
          }
        }
      }
    },
    handleResize({ target, width, height, delta }) {
      // console.log("onResize", width, height, delta);
      delta[0] && (target.style.width = `${width}px`);
      delta[1] && (target.style.height = `${height}px`);
    },
    handleScale({ target, transform, scale }) {
      //   console.log('onScale scale', scale);
      target.style.transform = transform;
    },
    handleRotate({ target, dist, transform }) {
      //   console.log('onRotate', dist);
      target.style.transform = transform;
    },
    handleWarp({ target, transform }) {
      //   console.log('onWarp', target);
      target.style.transform = transform;
    },  
    focusAction({ target, transform }){
        target.focus();
    },
    changeTargetAction({target}){
      this.blockMoveable();

      if(target.getAttribute('class') != null){
        var clas = target.getAttribute('class').split(' ');
      
        for(var cla in clas){
          // console.log(clas[cla]);
          if(clas[cla] == 'MoveableBox'){
            event.stopPropagation();
            target.blur();
            this.$refs.moveable.moveable.target = target;
          }

          if(clas[cla] == 'realBoard'){
            event.stopPropagation();
            target.blur();
            this.$refs.moveable.moveable.target = target;
            this.cloakMoveable();
          }
        }
      }


      // event.stopPropagation();
      // target.blur();
      // this.$refs.moveable.moveable.target = target;
    },
    deleteTargetAction(idx ,{target}){
      if(confirm("요소를 삭제하시겠습니까?") === true) {
        if(target.getAttribute("class") === "moveable") {
          this.board.postits.splice(idx, 1);
        }
        // target이 뒤에 있는 두 개가 삭제되는 문제가 있다. 
        target.remove();
      }
    },
    blockMoveable() {
      document.querySelector(".moveable-control-box").style.display = "block";
    },
    cloakMoveable() {
      document.querySelector(".moveable-control-box").style.display = "none";
    },
    wheelEvent: function(event) {
      // console.log(event.deltaY);
      if (event.deltaY < 0) { 
        console.log("up!"); 
        this.boardScale += 0.05;

        if(this.boardScale > 1.3) this.boardScale = 1.3;

        console.log(this.boardScale);
      }
      else if (event.deltaY > 0) {
        this.boardScale -= 0.05;

        if(this.boardScale < 0.3) this.boardScale = 0.3;

        console.log(this.boardScale);
         console.log("down!"); 
      }

      document.querySelector(".realBoard").style.transform = `scale(${this.boardScale})`;
      
    },
    mouseMoveEvent(event){
      // console.log(event);
    },
    changePITitle: function(value,index){
      console.log("title is changed!",index ,value);
      this.board.postits[index].title = value;
    },
    changePIContent: function(value,index){
      console.log("content is changed!",index ,value);
      this.board.postits[index].content = value;
    },

    createText(event) {
      event.stopPropagation();
      this.counter.textC.push(this.counter.textC.length);
      // this.board.idCounter++;
      this.board.postits.push({
        // "pid":this.board.idCounter++,
        // "title": "title",
        // "content": "content" ,
        
        // 데이터 테스트 용
        "boardId":this.board.idCounter++,
        "writer": "title",
        "contents": "content" ,
        "point":null,
      });
    },
    // createCanvas() {
    //   event.stopPropagation();
    //   this.counter.canvasC.push(this.counter.canvasC.length);
    // },
    createPoll() {
      event.stopPropagation();
      this.counter.pollC.push(this.counter.pollC.length);
    },
    createMap() {
      event.stopPropagation();
      this.counter.mapC.push(0);
    },
    sendData() {
      // 서버로 보낼 데이터 구성 (testing)
      const API_URL = ""
      var data = { 
        channel: { 
          channelId: 0, 
          name:"sample-channel", 
          }, 
        board: this.board
        
      }
      
    },
    sendMessage: function(type) {
        this.ws.send("/pub/board/message",
         {"token":this.token},
          JSON.stringify({
            channelId:localStorage.getItem('wsboard.channelId'),
            channelName:localStorage.getItem('wsboard.channelName'),
            idCounter: this.board.idCounter, 
            postits: this.board.postits,
            polls: this.board.polls,
            // board:{
            //   idCounter: this.board.idCounter, 
            //   postits: this.board.postits,
            //   polls: this.board.polls,
            // },
            })
          );
        // this.postit = '';
    },
    recvMessage: function(recv) {
      console.log("Response data is : ", recv);
        this.userCount = recv.userCount;
        // this.postits.unshift({
        //   "sender":recv.sender,
        //   "postitList":recv.postitList
        //   })
        this.board.postits = recv.postits;
        this.counter.textC = recv.postits.length;
        console.log("served postits is : ", this.board.postits);
    },
    
  },
  
}
</script>

<style>
.moveable {
  font-family: "Roboto", sans-serif;
  position: relative;
  width: 300px;
  height: 100px;
  text-align: center;
  font-size: 40px;
  margin: 0 auto;
  font-weight: 100;
  letter-spacing: 1px;
  /* background-color: yellow; */
}

.moveable2 {
  font-family: "Roboto", sans-serif;
  position: relative;
  width: 800px;
  height: 600px;
  text-align: center;
  font-size: 40px;
  margin: 0 0;
  font-weight: 100;
  letter-spacing: 1px;
}

.moveable3 {
  font-family: "Roboto", sans-serif;
  position: relative;
  width: 400px;
  height: 250px;
  text-align: center;
  font-size: 32px;
  margin: 0 auto;
  font-weight: 100;
  letter-spacing: 1px;
  padding-bottom: 5%
}

.moveable span {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  white-space: nowrap;
}

.bodyBox {
  position: relative;
  height: 75vh;
  /* width: 80vw; */
  margin: 1% 3%;
  /* transform: translate(-50%, -50%); */
  /* border: solid 1px; */
  background-color: rgba(102, 255, 255, 0.377);
  overflow: hidden;
}

.realBoard {
  /* boardLength와 동일해야함! */
  /* height: 2000px;
  width: 2000px; */
  /* left: -680px;
  top: -680px; */
  border: 1px solid pink;
  background: rgba(209, 130, 144, 0.507);
}

img {
  height: 300px;
  width: 300px;
}

textarea {
  height: 100%;
  width: 100%;
  /* background-color: yellow; */
}

#board button {
  border: 1px solid black;
  border-radius: 5px;
  margin-right: 5px;
}

.moveable-control-box{
    display:none;
  }


.toolBox{
  font-family: "Roboto", sans-serif;
  position: relative;
  width: 400px;
}

  
</style>