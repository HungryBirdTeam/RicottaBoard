<template>
  <div id="app">
    <Header :isHeader="isHeader"/>
    <router-view/>
  </div>
</template>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>  
<script> 
import './assets/css/style.scss' 
import Header from './components/common/Header.vue'
import constants from './lib/constants' 
import axios from 'axios'

export default {
  name: 'App',
  components: { 
    Header
  },
  created() {
      let url = this.$route.name;

      this.checkUrl(url);

      console.log("refresh!");
      console.log(this.$store);
      const arr = document.cookie.split(";");
      console.log(this.$store.getters.userData);
      const logInfo = {
          "AccessData" : '',
          "AccessToken" : ''
      };
      arr.forEach(element => {
        console.log("In App, element is : ", element);
          if(element.split('=')[0].trim() == 'AccessToken'){
              // logInfo.AccessToken = element.split('=')[1];
              logInfo.AccessToken = element.split('=')[1];
          }
          if(element.split('=')[0].trim() == 'AccessData'){
              
              logInfo.AccessData = unescape(element.split('=')[1]);
          }
      });

      console.log("In App, logInfo is : ", logInfo);

      if(logInfo.AccessData != '' && logInfo.AccessData != ''){
        this.$store.commit("setDataAgain", logInfo);
        this.$store.dispatch(constants.METHODS.GET_USER, logInfo.AccessData);
      }


  },
  watch: {
      $route (to){

          this.checkUrl(to.name);
      }
  },
  methods : {
      checkUrl(url) { 

          let array = [
              constants.URL_TYPE.USER.LOGIN,
              constants.URL_TYPE.USER.JOIN,
              constants.URL_TYPE.USER.JOINDONE
          ];

          let isHeader = true;
          array.map(path => {
              if (url === path)
                  isHeader = false;
          })
          this.isHeader = isHeader;

      },
  },
  data: function () {
        return {
            isHeader: true,
            constants,
        } 
    }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale; 
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
