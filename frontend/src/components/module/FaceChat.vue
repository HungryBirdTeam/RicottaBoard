<template>
  <div
    class="MoveableBox"
    width="300px"
    height="300px"
    style="border: 1px solid black;">
    <video
      :id="videoInfo.vdId"
      autoplay playsinline/>
      <button 
      v-show="userEmail== myEmail"
      id="videoOn" :click="onVideo(videoInfo.vdId)">on</button>
  </div>
</template>


<script>
import { loadChannelInfo, onVideo } from "../../services/FaceChatClientSocket.js"

export default {
  props:{
    videoInfo : Object,
    channelId : String,
    userEmail : String,
    myEmail : String
  },
  methods: {
  },
  created() {
    loadChannelInfo(channelId, user);
  },
};
</script>

<style>
</style>

