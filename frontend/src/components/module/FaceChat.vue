<template>
  <div
    class="MoveableBox video video_container">
    <p>{{userNickname}}</p>
    <video
      :id="videoInfo.vdId"
      autoplay playsinline></video>
      <button 
      v-show="userEmail==myEmail && !onVideo"
      id="videoOn" @click="onClickVideo">on</button>
      <button 
      v-show="userEmail==myEmail && onVideo"
      id="videoOn" @click="onClickVideo">off</button>
  </div>
</template>


<script>
import { loadChannelInfo, onVideo, offVideo } from "../../services/FaceChatClientSocket.js"

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
      console.log("onClick");
      if(!this.onVideo) {
        onVideo(this.videoInfo.vdId);
      } else {
        offVideo();
      }
    }
  },
  data() {
    return {
      onVideo : false
    }
  },
  created() {
  },
};
</script>

<style>
.video_container {
  width: 500px;
  height: 500px;
  background-color: white;
  /* border: solid 2px gray;  */
  border-radius: 5px;
  box-shadow: .5rem 1rem 2rem rgba(0,0,0,.3)!important;
}
</style>

