import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import { store } from './store'
import cookies from 'vue-cookie';
import constant from './lib/constants'
import vuetify from './plugins/vuetify';
import io from 'socket.io-client';
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import * as API from '../src/api/index.js'

// const socket = io(API.CHAT_BASE_URL);
const socket = io("http://k3a204.p.ssafy.io/api/chat");
console.log('main.js Chat Socket 연결\n' + API.CHAT_BASE_URL);
console.log(socket);
Vue.use(cookies);
Vue.use(router);
// Vue.prototype.$channelSocket = channelSocket;
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