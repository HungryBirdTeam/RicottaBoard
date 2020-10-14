import Vue from 'vue'
import Vuex from 'vuex'
import Router from 'vue-router'
import VueCookie from 'vue-cookie'

import constants from '../lib/constants'

// 유저
import Login from '../page/user/Login.vue'
import Join from '../page/user/Join.vue'
import JoinDone from '../page/user/JoinDone.vue'
import MyPage from '../page/user/MyPage.vue'
import ConfirmDone from '../page/user/ConfirmationCheck.vue'
import PasswordFind from '../page/user/PasswordFind.vue'
import PasswordReset from '../page/user/PasswordReset.vue'
import DeleteUser from '../page/user/ReallyDelete.vue'

// 포스트
import List from '../page/post/List.vue'
 
Vue.use(Router) 
Vue.use(Vuex)
Vue.use(VueCookie)
 
export default new Router({
  routes: [   
    // 로그인/가입
    { 
      path: '/user/login',
      name: constants.URL_TYPE.USER.LOGIN,
      component: Login
    },
    {
      path: '/user/join',
      name: constants.URL_TYPE.USER.JOIN,
      component: Join
    },
    {
      path:'/user/joinDone',
      name: constants.URL_TYPE.USER.JOINDONE,
      component: JoinDone
    },
    {
      path:'/user/MyPage',
      name: constants.URL_TYPE.USER.MYPAGE,
      component: MyPage
    },
    { 
      path: '/api/auth/registrationConfirmation',
      name: constants.URL_TYPE.USER.CONFIRMDONE,
      component: ConfirmDone
    },
    { 
      path: '/user/PasswordFind',
      name: constants.URL_TYPE.USER.PASSWORDFIND,
      component: PasswordFind
    },
    { 
      path: '/user/PasswordReset',
      name: constants.URL_TYPE.USER.PASSWORDRESET,
      component: PasswordReset
    },
    {
      path: '/user/DeleteAccount',
      name: constants.URL_TYPE.USER.DELETEUSER,
      component: DeleteUser
    },
    // 포스트
    { 
      path: '/',
      name: constants.URL_TYPE.POST.MAIN,
      component: List,
    },

    { 
      path: '/error',
      name: constants.ERROR.FRONT_ERROR,
      component: () => import('../page/etc/error.vue'),
    },
    

    // 그 외 페이지 (404, ERROR)
    {
      path: '*',
      name: 'e404',
      component: () => import('../page/etc/e404.vue')
    }

    
  ]
})
