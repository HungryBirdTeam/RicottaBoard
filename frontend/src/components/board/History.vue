<template>
  <div class="history-box">
    <span @click="showHistory()" class="main-button">Updates</span>
    <div v-if="isHistory" class="main">
      <span class="hint">최근 수정사항 20개를 보여줍니다</span><hr/>
      <div class="history-list">
        <div v-for="(history, idx) in historyList" :key="idx" class="history-item">
          <span class="module">  수정{{ history.editModule }} </span><br>
          <span class="time">{{ history.editTime.substr(0,10) }} {{ history.editTime.substr(11,8)}} </span><hr/>
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
  text-align: right;
  cursor: pointer;
  background-color: rgba(0, 0, 0, 0.5);
}

.main-button :hover {
  
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