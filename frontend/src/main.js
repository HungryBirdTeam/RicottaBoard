import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import { store } from './store'
import cookies from 'vue-cookie';
import constant from './lib/constants'
import vuetify from './plugins/vuetify';
import * as io from 'socket.io-client';
import VueSocketIO from 'vue-socket.io'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import * as API from '../src/api/index.js'

const socket = io(API.CHAT_BASE_URL);
console.log('main.js Chat Socket 연결\n' + API.CHAT_BASE_URL);
console.log(socket);

const face = io('http://localhost:3031');
const face1 = io('https://localhost:3031');
const face6 = io('https://localhost/api/facechat');
const face2 = io('https://k3a204.p.ssafy.io/api/facechat');
const face5 = io('http://k3a204.p.ssafy.io/api/facechat');
const face3 = io('http://k3a204.p.ssafy.io:3031');
const face4 = io('https://k3a204.p.ssafy.io:3031');
console.log("face", face, face1, face2, face3, face4, face5, face6);
Vue.use(cookies);
Vue.use(router);
Vue.prototype.$socket = socket;
Vue.prototype.$axios = axios;

/* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    store,
    components: { App },
    vuetify,
    template: '<App/>'
});