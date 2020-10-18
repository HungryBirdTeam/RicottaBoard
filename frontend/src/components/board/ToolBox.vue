<template>
    <div class="toolbar">
      <v-snackbar
        app
        bottom
        v-model="snackbar.isPresent"
        :timeout="snackbar.timeout"
        :color="snackbar.color"
      >{{ snackbar.text }}</v-snackbar>
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
</template>

<script>
export default {
    data() {
        return {
            snackbar: {
                isPresent: false,
                text: "",
                timeout: 1000,
            },
        }
    },
    methods: {
      createSnackbar(text, timeout, color) {
        this.snackbar.isPresent = true;
        this.snackbar.text = text;
        this.snackbar.timeout = timeout;
        this.snackbar.color = color;
      },
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
        }
        console.log("drag end at : ", event);
      },
      pleaseDrag() {
        this.createSnackbar(
          "생성하고자 하는 위치로 드래그 해주세요!",
          3000,
          "default"
        );
      },
      openInviteModal() {
        //테스트 페이지면 접근안됨
        if(this.testPage) {
          this.createSnackbar('테스트 페이지에선 멤버 초대가 불가능합니다.', 2000, 'error');
          return
        }
        this.$store.state.inviteModal = !this.$store.state.inviteModal
      },
      openWithdrawalModal() {
        //테스트 페이지면 접근안됨
        if(this.testPage) {
          this.createSnackbar('테스트 페이지에선 모임 탈퇴가 불가능합니다.', 2000, 'error');
          return
        }
        this.$store.state.withdrawalModal = !this.$store.state.withdrawalModal
      },
      createPostit(left = this.boardX - 120 + "px", top = this.boardY - 120 + "px") {
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
        this.sendMessage();
        // snackbar
        this.createSnackbar("포스트잇이 생성되었습니다!", 1500, "success");
      },

      createKanban(left = "500px", top = "170px") {
        if (this.board.isKanban == true) {
          this.createSnackbar("보드가 이미 생성되어 있습니다", 3000, "error");
          return;
        }
        this.board.isKanban = true;
        this.board.kanban.states = this.$store.state.Kanban.states;
        this.board.kanban.left = this.moduleXP + "px";
        this.board.kanban.top = this.moduleYP + "px";
        console.log(this.$store.state.Kanban);
        // this.crudMethod("KANBAN", "CREATE", this.board.kanban);
        this.sendMessage();
        this.createSnackbar("보드가 생성되었습니다", 1500, "success");
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
            answers: [ {answer: "", voted: 0}, {answer: "", voted: 0},],
            multipleVotes: false,
            totalVotes: 0,
            userVoted: [ ],
            setAll: false,
            end: false,
          };
          
          this.board.poll.push(newPoll);
          this.sendMessage();
          // snackbar
          this.createSnackbar("투표가 생성되었습니다!", 1500, "success");
        }
      },
    },
}
</script>

<style>
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
</style>