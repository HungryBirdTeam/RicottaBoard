<template>
  <div>
    서버 - 보드 통신을 위한 페이지
    <v-btn @click="findAllChannel"> FIND </v-btn>
    <div class="container" id="app" v-cloak>
        <div class="row">
            <div class="col-md-6">
                <h3>채널 리스트</h3>
            </div>
            <div class="col-md-6 text-right">
                <a class="btn btn-primary btn-sm" href="/logout">로그아웃</a>
            </div>
        </div>
        <div class="input-group">
            <div class="input-group-prepend">
                <label class="input-group-text">방제목</label>
            </div>
            <input type="text" class="form-control" v-model="channel_name" v-on:keyup.enter="createChannel">
            <div class="input-group-append">
                <!-- <button class="btn btn-primary" type="button" @click="createChannel">채널 개설</button> -->
            </div>
        </div>
        <ul class="list-group">
            <li class="list-group-item list-group-item-action" v-for="item in channels" v-bind:key="item.channelId" v-on:click="enterRoom(item.channelId, item.channelName)">
                <h6>{{item.channelName}} <span class="badge badge-info badge-pill">{{item.userCount}}</span></h6>
            </li>
        </ul>
    </div>
  </div>
</template>

<script>
// import SockJs from 'sockjs';
import SockJS from 'sockjs-client';
import Stomp from 'stomp-websocket';
import axios from 'axios';
import http from '../../http-common.js';

export default {
  
  name: 'server',
  data () {
    return {
      channel_name : '',
      channels: [
      ]
    }
  },
  created() {
  },
  methods: {
    findAllChannel: function() {
      http.get('/board/channels').then(response => {
        // prevent html, allow json array
        if(Object.prototype.toString.call(response.data) === "[object Array]")
            this.channels = response.data;
      });
    },
  },
}
</script>

<style>

</style>