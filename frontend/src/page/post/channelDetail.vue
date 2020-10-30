<template>
  <div id="app" v-cloak @click="cloakMoveable">
    <div class="toolbar">
      <v-snackbar
        app
        bottom
        v-model="snackbar.isPresent"
        :timeout="snackbar.timeout"
        :color="snackbar.color"
        >{{ snackbar.text }}</v-snackbar
      >
      <div>
        <div class="toolBox">
          <v-tooltip right>
            <template v-slot:activator="{ on }">
              <div v-on="on">
                <v-btn
                  icon
                  color="orange"
                  @click="pleaseDrag"
                  draggable="true"
                  @dragend="moduleDragEnd('postit', $event)"
                >
                  <v-icon>mdi-message</v-icon>
                </v-btn>
              </div>
            </template>
            <span>Post-it</span>
          </v-tooltip>

          <v-tooltip right>
            <template v-slot:activator="{ on }">
              <div v-on="on">
                <v-btn
                  icon
                  color="orange"
                  @click="pleaseDrag"
                  draggable="true"
                  @dragend="moduleDragEnd('kanban', $event)"
                >
                  <v-icon>mdi-clipboard-list-outline</v-icon>
                </v-btn>
              </div>
            </template>
            <span>Kanban-Board</span>
          </v-tooltip>

          <v-tooltip right>
            <template v-slot:activator="{ on }">
              <div v-on="on">
                <v-btn
                  icon
                  color="orange"
                  @click="pleaseDrag"
                  draggable="true"
                  @dragend="moduleDragEnd('scheduler', $event)"
                >
                  <v-icon>mdi-calendar</v-icon>
                </v-btn>
              </div>
            </template>
            <span>Scheduler</span>
          </v-tooltip>

          <v-tooltip right>
            <template v-slot:activator="{ on }">
              <div v-on="on">
                <v-btn
                  icon
                  color="orange"
                  @click="pleaseDrag"
                  draggable="true"
                  @dragend="moduleDragEnd('poll', $event)"
                >
                  <v-icon>mdi-vote</v-icon>
                </v-btn>
              </div>
            </template>
            <span>Poll</span>
          </v-tooltip>

          <v-tooltip right>
            <template v-slot:activator="{ on }">
              <div v-on="on">
                <v-btn
                  icon
                  color="orange"
                  @click="pleaseDrag"
                  draggable="true"
                  @dragend="moduleDragEnd('editor', $event)"
                >
                  <v-icon>mdi-language-markdown</v-icon>
                </v-btn>
              </div>
            </template>
            <span>Editor</span>
          </v-tooltip>
          <v-divider> </v-divider>
          <v-tooltip right>
            <template v-slot:activator="{ on }">
              <div v-on="on" class="tool-divide">
                <v-btn
                  icon
                  color="#FF5722"
                  @click="openInviteModal"
                  draggable="true"
                >
                  <v-icon>mdi-account-supervisor-outline</v-icon>
                </v-btn>
              </div>
            </template>
            <span>멤버 초대하기</span>
          </v-tooltip>

          <v-tooltip right>
            <template v-slot:activator="{ on }">
              <div v-on="on">
                <v-btn
                  icon
                  color="#FF5722"
                  @click="openWithdrawalModal"
                  draggable="true"
                >
                  <v-icon>mdi-close-circle</v-icon>
                </v-btn>
              </div>
            </template>
            <span>모임 나가기</span>
          </v-tooltip>
        </div>
        <br />
      </div>
    </div>

    <div class="testerDot"></div>
    <v-responsive>
      <v-responsive
        class="userListBadge badge-info text-center lighten-2 rounded-circle d-inline-flex align-center justify-center ma-3"
        @mouseover="testIn"
        @mouseout="testOut"
      >
        <v-img src="@/assets/img/team.png" style="color: white"
          ><div v-if="userCount != 0">{{ userCount }}</div></v-img
        >
      </v-responsive>

      <transition name="slide-fade">
        <v-responsive
          id="memberList"
          class="text-center"
          v-show="memberView"
          align="center"
          justify="center"
        >
          <main role="main" class="container">
            <div class="my-3 p-3 bg-white rounded shadow-sm">
              <h4 class="border-bottom border-gray pb-2 mb-0">
                {{ this.channelName }}
              </h4>

              <div class="media pt-3 border-bottom asdf">
                <div
                  v-for="(member, idx) in board.memberList"
                  :key="idx"
                  class="media-body small lh-125 border-gray member"
                >
                  {{ member }}
                </div>
              </div>
            </div>
          </main>
        </v-responsive>
      </transition>
    </v-responsive>

    <v-btn
      class="resetButton text-center lighten-2 rounded-circle d-inline-flex align-center justify-center ma-3"
      icon
      color="black"
      @click="reset"
    >
      <v-icon large>mdi-arrow-expand-all</v-icon>
    </v-btn>

    <Moveable
      ref="moveable"
      class="moveable"
      v-bind="moveable"
      @drag="handleDrag"
      @dragEnd="handleDragEnd"
      @resize="handleResize"
      @scale="handleScale"
      @rotate="handleRotate"
      @warp="handleWarp"
      style="display: none"
    ></Moveable>

    <div
      class="bodyBox"
      ref="whiteBoard"
      @dblclick="focusAction"
      @click="changeTargetAction"
      style="height: 100%; width: 100%"
    >
      <div
        ref="realBoard"
        class="MoveableBox realBoard"
        @dragover="test5"
      >
        <div
          class="postit"
          v-for="(pi, idx) in this.board.postitList"
          :key="pi.frontPostitId"
          @click.right="deleteTargetAction(idx, 'postit', $event)"
        >
          <Postit
            :id="pi.frontPostitId"
            :postit="pi"
            :style="{ left: pi.left, top: pi.top }"
          />
        </div>

        <div class="kanban" @click.right="deleteAction('kanban', $event)">
          <Kanban
            v-if="!!board.kanban.left"
            :style="{ left: board.kanban.left, top: board.kanban.top }"
          />
        </div>

        <div class="Scheduler" @click.right="deleteAction('scheduler', $event)">
          <Scheduler
            v-if="!!board.scheduler.left"
            :style="{ left: board.scheduler.left, top: board.scheduler.top }"
          />
        </div>

        <div
          v-for="(poll, idx) in this.board.poll"
          :key="idx"
          class="Poll"
          @click.right="deleteTargetAction(idx, 'poll', $event)"
        >
          <Poll
            :id="poll.pollId"
            :poll="poll"
            :idx="idx"
            :style="{ left: poll.left, top: poll.top }"
          />
        </div>

        <div
          class="editor"
          v-for="(md, idx) in this.board.editorList"
          :key="md.mdId"
          @click.right="deleteTargetAction(idx, 'editor', $event)"
        >
          <Editor
            :id="md.mdId"
            :editor="md"
            :style="{ left: md.left, top: md.top }"
          />
        </div>

        <InviteModal v-model="$store.state.inviteModal" />
        <WithdrawalModal v-model="$store.state.withdrawalModal" />
      </div>
    </div>
    <Chat />
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "stomp-websocket";
import http from "../../http-common.js";
import Moveable from "vue-moveable";
import Postit from "../../components/module/Postit";
import Scheduler from "../../components/module/Scheduler";
import Chat from "../../components/common/Chat";
import Poll from "../../components/common/Poll";
import Kanban from "../../components/module/Kanban";
import Editor from "../../components/module/Editor";
import InviteModal from "../../components/common/InviteModal";
import WithdrawalModal from "../../components/common/WithdrawalModal";
import { renderer } from "./renderer";
import * as boardApi from "../../api/board.js"


export default {
  computed: {
    poll() {
      return this.$store.state.poll;
    },
    updateOccur() {
      return this.$store.state.updateOccur;
    },
  },
  watch: {
    updateOccur: function () {
      this.sendMessage();
    },
  },
  data() {
    return {
      ws: null,
      channelName: "",
      // 소켓 서버 전송
      board: {
        channelId: "",
        idCount: 1,
        memberList: [],
        // crudModule: {
        //   modulType: "",
        //   crudType: "",
        //   moduleObject: Object,
        // },
        postitList: [],
        kanban: { left: null, top: null, kanbanName: null, states: [{"columnTitle":"TO DO","tasks":[]},{"columnTitle":"IN PROGRESS","tasks":[]},{"columnTitle":"DONE","tasks":[]}]},
        scheduler: {
          left: "600px",
          top: "270px",
          events: this.$store.state.scheduler.events,
        },
        poll: [],
        editorList: [],
        delete: {
          moduleName: "",
          id: -1,
        },
      },
      token: "",
      userCount: 0,
      moveable: {
        target: "",
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

      snackbar: {
        isPresent: false,
        text: "",
        timeout: 1000,
      },
      boardLengthX: 3250,
      boardLengthY: 1750,
      boardScale: 1,
      boardX: this.boardLengthX / 2,
      boardY: this.boardLengthY / 2,
      lp: 0,
      tp: 0,
      lastBX: this.boardX,
      lastBY: this.boardY,
      moduleXP: this.boardLengthX / 2,
      moduleYP: this.boardLengthY / 2,

      memberView: false,
      idc: 0,
      isPoll: false,
      testPage: false,
    };
  },
  created() {
    this.init();
    window.oncontextmenu = function () {
      // 우클릭 default이벤트 차단
      return false;
    };
    if (this.$route.params.channelId === "earlyBird10TeamTestChannel1") {
      this.testPage = true;
    }
    this.initRecv();
  },
  mounted() {
    document.querySelector(".realBoard").style.height =
      this.boardLengthY + "px";
    document.querySelector(".realBoard").style.width = this.boardLengthX + "px";

    // console.log((boardLength/2) - (window.innerWidth * 0.4));
    document.querySelector(".realBoard").style.left =
      -(this.boardLengthX / 2) + window.innerWidth * 0.5 + "px";

    document.querySelector(".realBoard").style.top =
      -(this.boardLengthY / 2) + window.innerHeight * 0.5 + "px";

    document.querySelector(".realBoard").style.transformOrigin = `${
      this.boardLengthX / 2
    }px ${this.boardLengthY / 2}px`;

    const container = this.$refs.realBoard;
    const instance = renderer({
      scaleSensitivity: 10,
      minScale: 0.3,
      maxScale: 2,
      element: container,
    });
    container.addEventListener("wheel", (event) => {
      event.preventDefault();
      this.cloakMoveable();
      instance.zoom({
        deltaScale: Math.sign(event.deltaY) > 0 ? -1 : 1,
        x: event.pageX,
        y: event.pageY,
      });
    });
  },
  methods: {
    init() {
      var sock = new SockJS(boardApi.API_BASE_URL + "/ws-stomp");
      var ws = Stomp.over(sock);
      this.ws = ws;

      this.board.channelId = localStorage.getItem("wsboard.channelId");
      this.channelName = localStorage.getItem("wsboard.channelName");
      var _this = this;
        ws.connect(
          {},
          function (frame) {
            ws.subscribe(
              "/sub/board/channel/" + _this.board.channelId,
              function (message) {
                var recv = JSON.parse(message.body);
                _this.recvMessage(recv);
              }
            );
          },
          function (error) {
            alert("서버 연결에 실패 하였습니다. 다시 접속해 주십시요.");
            location.href = "/";
          }
        );
    },
    initRecv() {
      // 접속시 처음 값을 받아오도록 하기
      // 테스트 페이지인 경우와 아닌 경우로 분기
      boardApi.initialRecv(this.board.channelId, this.testPage, this.$store.getters.accessToken,
        response => {
          console.log("initRecv@@@@");
          console.log(response.data);
          // this.board.postitList = response.data.postitList;
          this.board.idCount = response.data.idCount;
          if (!!response.data) {
            this.board = response.data;
          }
          this.board.delete = { moduleName: "", id: -1 };
          if (response.data.kanban.left !== null) {
            this.board.kanban.states = response.data.kanban.states;
            this.$store.state.kanban.states = response.data.kanban.states;
          } else {
            this.board.kanban.states = this.$store.state.kanban.states;
          }
          if (!response.data.poll) {
            this.board.poll = [];
          } else this.$store.state.poll = this.board.poll;
          if (!!response.data.scheduler.left) {
            this.$store.state.scheduler.events = response.data.scheduler.events;
          }
          this.$store.state.memberList = response.data.memberList;
          this.board.editorList = response.data.editorList;
          // this.$store.state.scheduler.events = response.data.scheduler.events;
          this.createSnackbar(
            `'${this.channelName}' 채널에 입장하였습니다!`,
            3000,
            "info"
          );
          // 지워야할 것
          console.log("this.board before", this.board)
          console.log("this.board onSocket", response.data.editorList)
          // this.board.editorList = [];
          console.log("this.board", this.board)
        },
        err => {
          console.log("initRecv 실패");
          console.log(err);
          console.log("this.board", this.board)
        }
      )
    },
    sendMessage: function (type) {
      this.ws.send(
        "/pub/board/message",
        { token: this.token },
        JSON.stringify(this.board)
      );
      this.createSnackbar("수정되었습니다", 1000, "warning");
    },
    recvMessage: function (recv) {
      this.userCount = recv.userCount;
      this.board.idCount = recv.idCount;
      this.board.postitList = recv.postitList;
      this.board.scheduler = recv.scheduler;
      this.$store.state.scheduler.events = recv.scheduler.events;
      this.board.poll = recv.poll;
      this.$store.state.poll = recv.poll;
      this.board.kanban = recv.kanban;
      this.$store.state.kanban.states = recv.kanban.states;
      //crudModule 초기화
      // this.board.crudModule = {
      //   modulType: "",
      //   crudType: "",
      //   moduleObject: null,
      // };
      this.board.memberList = recv.memberList;
      this.$store.state.memberList = recv.memberList;
      this.board.editorList=  recv.editorList;
    },
    createPostit(
      left = this.boardX - 120 + "px",
      top = this.boardY - 120 + "px"
    ) {
      if (this.board.postitList.length > 20) {
        this.createSnackbar("포스트잇이 너무 많습니다!", 3000, "error");
        return;
      }
      event.stopPropagation();
      const idc = this.board.idCount++;
      // postitList에 새로운 포스트잇 더하기
      var newPostit = {
        frontPostitId: idc,
        left: this.moduleXP - 120 + "px",
        top: this.moduleYP - 120 + "px",
        title: "",
        contents: "",
        channel: this.board.channelId,
      };
      this.board.postitList.push(newPostit);
      // this.crudMethod("POSTIT", "CREATE", newPostit);
      this.sendMessage();
      // snackbar
      this.createSnackbar("포스트잇이 생성되었습니다!", 1500, "success");
    },

    createKanban(left = "500px", top = "170px") {
      if (!!this.board.kanban.left) {
        this.createSnackbar("보드가 이미 생성되어 있습니다", 3000, "error");
        return;
      }
      this.board.kanban.states = this.$store.state.kanban.states;
      this.board.kanban.left = this.moduleXP + "px";
      this.board.kanban.top = this.moduleYP + "px";
      // this.crudMethod("KANBAN", "CREATE", this.board.kanban);
      this.sendMessage();
      this.createSnackbar("보드가 생성되었습니다", 1500, "success");
    },

    deleteKanban({ target }) {
      console.log(target);
      if (confirm("요소를 삭제하시겠습니까?") === true) {
        target.remove();
        this.cloakMoveable();
        // this.crudMethod("KANBAN", "DELETE", this.board.kanban);
        this.$store.state.kanban.states = [
          {
            columnTitle: "TO DO",
            tasks: [],
          },
          {
            columnTitle: "IN PROGRESS",
            tasks: [],
          },
          {
            columnTitle: "DONE",
            tasks: [],
          },
        ];
        this.sendMessage();
      }
    },

    createScheduler(left = "600px", top = "270px") {
      if (!!this.board.scheduler.left) {
        this.createSnackbar("이미 달력이 있습니다!", 3000, "error");
      } else {
        this.board.scheduler = {
          left: this.moduleXP + "px",
          top: this.moduleYP + "px",
          events: this.$store.state.scheduler.events,
        };
        console.log("create Scheduler");
        // this.crudMethod("SCHEDULER", "CREATE", this.board.scheduler);
        this.sendMessage();
        // snackbar
        this.createSnackbar("달력이 생성되었습니다!", 1500, "success");
      }
    },

    createPoll(left = "500px", top = "170px") {
      if (this.board.poll.length >= 3) {
        this.createSnackbar("이미 투표가 있습니다!", 3000, "error");
      } else {
        const idc = this.board.idCount++;
        const newPoll = {
          pollId: idc,
          left: this.moduleXP + "px",
          top: this.moduleYP + "px",
          question: "",
          answers: [
            { answer: "", voted: 0 },
            { answer: "", voted: 0 },
          ],
          multipleVotes: false,
          totalVotes: 0,
          userVoted: [],
          setAll: false,
          end: false,
        };
        console.log(newPoll);
        this.board.poll.push(newPoll);
        this.sendMessage();
        // snackbar
        this.createSnackbar("투표가 생성되었습니다!", 1500, "success");
      }
    },

    createEditor() {
      console.log("editor", this.board)
      if (this.board.editorList.length > 3) {
        this.createSnackbar("마크다운 에디터 수가 최대입니다!", 3000, "error");
      } else {
        const idc = this.board.idCount++;
        const newEditor = {
          mdId: idc,
          left: this.moduleXP + "px",
          top: this.moduleYP + "px",
          title: "",
          text: "",
        };
        console.log(newEditor);
        this.board.editorList.push(newEditor);
        this.sendMessage();
        // snackbar
        this.createSnackbar("마크다운이 생성되었습니다!", 1500, "success");
      }
    },

    createSnackbar(text, timeout, color) {
      this.snackbar.isPresent = true;
      this.snackbar.text = text;
      this.snackbar.timeout = timeout;
      this.snackbar.color = color;
    },
    handleDrag({ target, left, top }) {
      target.style.top = `${top}px`;
      target.style.left = `${left}px`;
      if (target.getAttribute("class") != null) {
        var clas = target.getAttribute("class").split(" ");
        for (var cla in clas) {
          if (clas[cla] == "paper") {
            this.board.postitList.map((postit) => {
              if (postit.frontPostitId == target.id) {
                (postit.left = `${left}px`), (postit.top = `${top}px`);
              }
              return {
                ...postit,
              };
            });
          } else if (clas[cla] == "scheduler") {
            this.board.scheduler.left = `${left}px`;
            this.board.scheduler.top = `${top}px`;
          } else if (clas[cla] == "Pollx") {
            this.board.poll.map((pol) => {
              if (pol.pollId == target.id) {
                (pol.left = `${left}px`), (pol.top = `${top}px`);
              }
            });
          } else if (clas[cla] == "kanban") {
            this.board.kanban.left = `${left}px`;
            this.board.kanban.top = `${top}px`;
          } else if (clas[cla] == "realBoard") {
            this.lp = target.style.left.replace("px", "");
            this.tp = target.style.top.replace("px", "");

            this.boardX = this.lp * -1 + window.innerWidth / 2;
            this.boardY = this.tp * -1 + window.innerHeight / 2;
            return;
          }
        }
      }
    },
    handleDragEnd({ target }) {
      if (target === this.$refs.realBoard)
        // 보드의 경우를 제외하고 sendMessage
        return;
      this.sendMessage();

      if (target.getAttribute("class") != null) {
        var clas = target.getAttribute("class").split(" ");
        for (var cla in clas) {
          if (clas[cla] == "realBoard") {
            document.querySelector(
              ".realBoard"
            ).style.transformOrigin = `${event.offsetX}px ${event.offsetY}px`;

            document.querySelector(".testerDot").style.top = event.offsetY + "px";
            document.querySelector(".testerDot").style.left = event.offsetX + "px";
            // this.crudMethod(target.nodeName, "UPDATE", moduleObj);
            this.sendMessage();
            // this.crudMethod("", "", null);
            console.log("ltp : ", this.lp, ",", this.tp);
            console.log(
              "bodyBox wh : ",
              window.innerWidth,
              ", ",
              window.innerHeight
            );
            console.log("boardXY  : ", this.boardX, ", ", this.boardY);
            console.log(
              "origin : ",
              document.querySelector(".realBoard").style.transformOrigin
            );
            console.log("event : ", event);
          }
        }
      }
    },
    handleResize({ target, width, height, delta }) {
      delta[0] && (target.style.width = `${width}px`);
      delta[1] && (target.style.height = `${height}px`);
    },
    handleScale({ target, transform, scale }) {
      target.style.transform = transform;
    },
    handleRotate({ target, dist, transform }) {
      target.style.transform = transform;
    },
    handleWarp({ target, transform }) {
      target.style.transform = transform;
    },
    focusAction({ target, transform }) {
      target.focus();
    },
    changeTargetAction({ target }) {
      this.blockMoveable();

      if (target.getAttribute("class") != null) {
        var clas = target.getAttribute("class").split(" ");
        // console.log(clas);
        for (var cla in clas) {
          // console.log(clas[cla]);
          if (clas[cla] == "MoveableBox") {
            target.blur();
            event.stopPropagation();
            target.focus();
            this.$refs.moveable.moveable.target = target;
          } else if (clas[cla] == "realBoard" || clas[cla] == "bodyBox") {
            this.cloakMoveable();
          } else if (clas[cla] == "kanban-task") {
            this.$refs.moveable.moveable.target = null;
          }
        }
      }
    },
    deleteTargetAction(idx, moduleName, { target }) {
      if (confirm("요소를 삭제하시겠습니까?") === true) {
        if (moduleName === "postit") {
          this.board.delete.moduleName = "postit";
          this.board.delete.id = this.board.postitList[idx].frontPostitId;
          // this.crudMethod("POSTIT", "DELETE", this.board.postitList[idx]);
          this.board.postitList.splice(idx, 1);
        } else if (moduleName === "poll") {
          this.board.delete.moduleName = "poll";
          this.board.delete.id = this.board.poll[idx].pollId;
          // this.crudMethod("POLL", "DELETE", this.board.poll[idx]);
          this.board.poll.splice(idx, 1);
        } else if (moduleName === "editor") {
          this.board.delete.moduleName = "editor";
          this.board.delete.id = this.board.editorList[idx].mdId;
          this.board.editorList.splice(idx, 1);
        }
        this.sendMessage();
        this.cloakMoveable();
      }
    },
    wheelEvent: function (event) {
      console.log(event.target.getAttribute("class"));
      if (event.target.getAttribute("class") != "MoveableBox realBoard") {
        // 모듈 위에서는 휠업을 방지한다.
        return;
      }
      if (event.deltaY < 0) {
        console.log("up!");
        this.boardScale += 0.05;

        if (this.boardScale > 1.3) {
          this.boardScale = 1.3;
          return;
        }
      } else if (event.deltaY > 0) {
        this.boardScale -= 0.05;

        if (this.boardScale < 0.4) {
          this.boardScale = 0.425;
          this.reset();
          return;
        }
      }
      console.log(this.boardScale);
      let lastOriginX = document
        .querySelector(".realBoard")
        .style.transformOrigin.split(" ")[0];
      let lastOriginY = document
        .querySelector(".realBoard")
        .style.transformOrigin.split(" ")[1];

      console.log("LastOrigin : ", lastOriginX, " ", lastOriginY);

      let diffX = lastOriginX.replace("px", "") - event.offsetX;
      let diffY = lastOriginY.replace("px", "") - event.offsetY;

      console.log("Diff : ", diffX, ",", diffY);

      document.querySelector(
        ".realBoard"
      ).style.transformOrigin = `${event.offsetX}px ${event.offsetY}px`;

      document.querySelector(".testerDot").style.top = event.offsetY + "px";
      document.querySelector(".testerDot").style.left = event.offsetX + "px";

      let leftPoint =
        document.querySelector(".realBoard").style.left.replace("px", "") * 1;
      let topPoint =
        document.querySelector(".realBoard").style.top.replace("px", "") * 1;

      console.log("realBoard left and top : ", leftPoint, ", ", topPoint);
      console.log("so its now  :  ", leftPoint + diffX, ", ", topPoint + diffY);

      if (Math.abs(diffX) > 100 || Math.abs(diffY) > 100) {
        document.querySelector(".realBoard").style.left =
          leftPoint + diffX / 2 + "px";
        document.querySelector(".realBoard").style.top =
          topPoint + diffY / 2 + "px";
      }

      console.log(
        "origin : ",
        document.querySelector(".realBoard").style.transformOrigin
      );

      document.querySelector(
        ".realBoard"
      ).style.transform = `scale(${this.boardScale})`;
    },
    deleteAction(moduleName, { target }) {
      if (confirm("요소를 삭제하시겠습니까?") === true) {
        if (moduleName == "scheduler") {
          // this.crudMethod("SCHEDULER", "DELETE", this.board.scheduler);
          this.board.scheduler = { id: null, left: null, top: null };
        } else if (moduleName == "kanban") {
          // this.crudMethod("KANBAN", "DELETE", this.board.kanban);
          this.$store.state.kanban.states = [
            {
              columnTitle: "TO DO",
              tasks: [],
            },
            {
              columnTitle: "IN PROGRESS",
              tasks: [],
            },
            {
              columnTitle: "DONE",
              tasks: [],
            },
          ];
        }
      }
      this.sendMessage();
      this.cloakMoveable();
    },
    blockMoveable() {
      document.querySelector(".moveable-control-box").style.display = "block";
    },
    cloakMoveable() {
      document.querySelector(".moveable-control-box").style.display = "none";
    },
    // moduleDragEnd(moduleName, { offsetX, offsetY }) {
    moduleDragEnd(moduleName, event) {
      switch (moduleName) {
        case "postit":
          this.createPostit(`${event.offsetX}`, `${event.offsetY}`);
          break;
        case "scheduler":
          this.createScheduler(`${event.offsetX}px`, `${event.offsetY}px`);
          break;
        case "poll":
          this.createPoll(`${event.offsetX}px`, `${event.offsetY}px`);
          break;
        case "kanban":
          this.createKanban(`${event.offsetX}px`, `${event.offsetY}px`);
          break;
        case "editor":
          this.createEditor(`${event.offsetX}px`, `${event.offsetY}px`);
          break;
      }
      console.log("drag end at : ", event);
    },
    pleaseDrag() {
      this.createSnackbar("생성하고자 하는 위치로 드래그 해주세요!", 3000, "default");
    },
    crudMethod(moduleType, crudType, moduleObject) {
      this.board.crudModule = {
        moduleType: moduleType,
        crudType: crudType,
        moduleObject: moduleObject,
      };
    },

    testIn() {
      if (!this.memberView) {
        this.memberView = true;
      }
    },
    testOut() {
      if (this.memberView) {
        this.memberView = false;
      }
    },

    test5(event) {
      // console.log(event.offsetX, event.offsetY);
      this.moduleXP = event.offsetX;
      this.moduleYP = event.offsetY;
    },
    reset() {
      this.boardScale = 0.425;
      (this.boardX = this.boardLengthX / 2),
        (this.boardY = this.boardLengthY / 2),
        (this.lp = 0),
        (this.tp = 0),
        (this.lastBX = this.boardLengthX / 2),
        (this.lastBY = this.boardLengthY / 2),
        (document.querySelector(
          ".realBoard"
        ).style.transformOrigin = `${this.boardX}px ${this.boardY}px`);

      document.querySelector(
        ".realBoard"
      ).style.transform = `scale(${this.boardScale})`;

      console.log("reset lp is : ", this.boardLengthX * this.boardScale);

      console.log("window is : ", window.innerWidth, window.innerHeight);
      document.querySelector(".realBoard").style.left = `${
        -1 * ((this.boardLengthX - window.innerWidth) / 2)
      }px`;

      document.querySelector(".realBoard").style.top = `${
        -1 * ((this.boardLengthY - window.innerHeight) / 2)
      }px`;
    },
    openInviteModal() {
      //테스트 페이지면 접근안됨
      if (this.testPage) {
        this.createSnackbar("테스트 페이지에선 멤버 초대가 불가능합니다.", 2000, "error");
        return;
      }
      this.$store.state.inviteModal = !this.$store.state.inviteModal;
    },
    openWithdrawalModal() {
      //테스트 페이지면 접근안됨
      if (this.testPage) {
        this.createSnackbar("테스트 페이지에선 모임 탈퇴가 불가능합니다.", 2000, "error");
        return;
      }
      this.$store.state.withdrawalModal = !this.$store.state.withdrawalModal;
    },
  },
  components: {
    Moveable,
    Postit,
    Scheduler,
    Chat,
    Kanban,
    Poll,
    InviteModal,
    WithdrawalModal,
    Editor
  },
};

</script>

<style scoped>
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

.bodyBox {
  position: relative;
  height: 75vh;
  margin: 0;
  width: 100vw;
  /* transform: translate(-50%, -50%); */
  /* border: solid 1px; */
  background-color: rgb(255, 255, 255);
  overflow: hidden;
}

.realBoard {
  /* boardLength와 동일해야함! */
  /* height: 2000px;
  width: 2000px; */
  /* left: -680px;
  top: -680px; */
  border: 1px solid rgb(173, 173, 173);
  background: rgb(240, 240, 240);

  background-image: linear-gradient(
      0deg,
      transparent 0%,
      transparent 0px,
      rgba(104, 104, 104, 0.1) 0px,
      rgba(104, 104, 104, 0.1) 1px,
      transparent 1px,
      transparent 49px,
      rgba(104, 104, 104, 0.1) 49px,
      rgba(104, 104, 104, 0.1) 50px,
      transparent 1px,
      transparent 99px,
      rgba(104, 104, 104, 0.1) 99px,
      rgba(104, 104, 104, 0.1) 100px,
      transparent 1px,
      transparent 149px,
      rgba(104, 104, 104, 0.1) 149px,
      rgba(104, 104, 104, 0.1) 150px,
      transparent 1px,
      transparent 199px,
      rgba(104, 104, 104, 0.1) 199px,
      rgba(104, 104, 104, 0.1) 200px,
      transparent 1px,
      transparent 249px,
      rgba(104, 104, 104, 0.3) 249px,
      rgba(104, 104, 104, 0.3) 250px,
      transparent 1px
    ),
    linear-gradient(
      -90deg,
      transparent 0%,
      transparent 0px,
      rgba(104, 104, 104, 0.1) 0px,
      rgba(104, 104, 104, 0.1) 1px,
      transparent 1px,
      transparent 49px,
      rgba(104, 104, 104, 0.1) 49px,
      rgba(104, 104, 104, 0.1) 50px,
      transparent 1px,
      transparent 99px,
      rgba(104, 104, 104, 0.1) 99px,
      rgba(104, 104, 104, 0.1) 100px,
      transparent 1px,
      transparent 149px,
      rgba(104, 104, 104, 0.1) 149px,
      rgba(104, 104, 104, 0.1) 150px,
      transparent 1px,
      transparent 199px,
      rgba(104, 104, 104, 0.1) 199px,
      rgba(104, 104, 104, 0.1) 200px,
      transparent 1px,
      transparent 249px,
      rgba(104, 104, 104, 0.3) 249px,
      rgba(104, 104, 104, 0.3) 250px,
      transparent 1px
    );

  background-size: 250px 250px;
  /* transition: all 0.05s; */
}

.moveable-control-box {
  display: none;
}

.toolBox {
  font-family: "Roboto", sans-serif;
  /* position: relative; */
  position: fixed;
  z-index: 3;
  width: 64px;
  top: 30%;
  left: 2%;
  padding: 10px 0px;
  /* display: inline; */
  background-color: white;
  text-align: center;
  vertical-align: middle;
  border-radius: 20px;
  border: 2px solid rgba(104, 104, 104, 0.5);
}

.toolbar {
  position: fixed;
  z-index: 3;
}

.userListBadge {
  position: fixed;
  z-index: 3;
  bottom: 20px;
  left: 20px;

  background-color: white;
  /* border-radius: 50%; */
  width: 64px;
  height: 64px;
}

.vueBox {
  background-color: rgba(0, 0, 0, 0);
  /* background-color: white; */
  /* border: 1px solid black; */
  position: fixed;
  z-index: 3;
  right: 10%;
  bottom: 1%;
  width: 200px;
  height: 200px;
}

#memberList {
  width: 350px;
  height: auto;
  position: fixed;
  z-index: 2;
  bottom: 20px;
  left: 50px;
  text-align: right;
  padding-right: 1%;
  padding-left: 5%;
}

.moimimg {
  border-radius: 50%;
}

.testerDot {
  height: 4px;
  width: 4px;
  background-color: black;
  position: fixed;
  z-index: 4;
  display: none;
}

.invite-mem {
  margin-top: 20px;
}

.resetButton {
  position: fixed;
  z-index: 3;
  bottom: 100px;
  left: 20px;
  border: solid black 1px;

  /* background-color: white; */
  /* border-radius: 50%; */
  width: 64px;
  height: 64px;
}

.member {
  /* overflow: scroll; */
  /* margin-left: 5px; */
  margin: 5px;
  display: inline-block;
  /* margin-right: 5px; */
}

.asdf {
  width: 100%;
  display: contents;
}
</style>