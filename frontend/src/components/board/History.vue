<template>
  <div class="history-box">
    <v-btn @click="showHistory()" class="main-button" fab width="36px" height="36px">
      <v-icon>mdi-update</v-icon>
    </v-btn>
    <div v-if="isHistory" class="main">
      <div class="hint">최근 수정사항 20개를 보여줍니다</div><hr stlye="margin-top: 0px;"/>
      <div class="history-list">
        <div v-for="(history, idx) in historyList" :key="idx" class="history-item">
          <span class="user"> {{ history.editUser }} 님이 </span>
          <span class="time">{{ history.editTime.substr(0,10) }} {{ history.editTime.substr(11,8)}}</span>
          <span> 수정했습니다. </span><hr/>
          <span class="module" v-for="(editModule, i) in history.editModule" :key="i"> {{ editModule }} </span><br>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import * as boardApi from "../../api/board.js";

export default {
  data() {
    return {
      isHistory: false,
      historyList: [],
    };
  },
  created() {
    this.fetchHistory();
  },
  methods: {
    showHistory() {
      this.isHistory = !this.isHistory;
      if(!this.isHistory) 
        this.fetchHistory();
    },
    fetchHistory() {
      boardApi.getHistories(
        (response) => {
          console.log("History Response 정보: ", response);
          this.historyList = response.data;
        },
        (err) => {
          console.log("History 불러오기 에러: ", err);
        }
      );
    },
  },
};
</script>

<style>
.history-box {
  display: flex;
  flex-direction: column;
  margin: 16px;
}

.main-button {
  margin-left: auto;
  cursor: pointer;
  /* background-color: rgba(0, 0, 0, 0.5); */
  border: solid black 1px;
  width: 36px;
  height: 36px;
}


.main {
  padding: 5px;
  background-color: white;
  border-radius: 20px;
  border: 2px solid rgba(104, 104, 104, 0.5);
}

.hint {
  padding: 12px;
}

.history-list {
  width: 300px;
  max-height: 300px;
  background-color: white;
  padding: 12px;
  overflow-y: auto;
}

.history-item {
  /* background-color: white; */
  padding: 4px;
  border-radius: 8px;
}

.time {
  text-align: right;
  font-size: 0.8rem;
}
</style>