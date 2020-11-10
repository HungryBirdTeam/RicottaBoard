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

const socket = io('http://localhost:3030');
const channelSocket = io('https://k3a204.p.ssafy.io/api/facechat');
Vue.use(cookies);
Vue.use(router);
Vue.prototype.$channelSocket = channelSocket;
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