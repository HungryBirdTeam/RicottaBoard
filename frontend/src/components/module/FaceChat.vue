<template>
  <div
    class="MoveableBox"
    width="300px"
    height="300px"
    style="border: 1px solid black;">
    <p>{{userNickname}}</p>
    <video
      :id="videoInfo.vdId"
      autoplay playsinline></video>
      <button 
      v-show="userEmail==myEmail"
      id="videoOn" :click="onClickVideo">on</button>
  </div>
</template>


<script>
import { loadChannelInfo, onVideo } from "../../services/FaceChatClientSocket.js"

export default {
  props:{
    videoInfo : Object,
    channelId : String,
    userEmail : String,
    userNickname : String,
    myEmail : String
  },
  methods: {
    onClickVideo() {
      onVideo(videoInfo.vdId);
    }
  },
  created() {
    // loadChannelInfo(channelId, myEmail);
  },
};
</script>

<style>
</style>

