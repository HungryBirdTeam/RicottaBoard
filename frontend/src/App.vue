<template>
  <v-app>
    <no-mobile v-show="isMobile"/>
    <Loading :loading="LoadingStatus" :num="LoadingIcon"></Loading>
    <v-main id="bg" v-if="!isMobile">
      <Header v-if="!isBoard"/>
      <router-view id="container" />
    </v-main>
  </v-app>
</template>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script>
import './assets/css/style.scss' 
import Header from './components/common/Header.vue'
import constants from './lib/constants' 
import axios from 'axios'
import Loading from './page/etc/loading.vue'
import bus from "./utils/bus.js"
import NoMobile from './page/etc/noMobile.vue'

export default {
  name: "App",
  components: {
    Header,
    Loading,
    NoMobile,
    
  },
  created() {
    bus.$on('start:Loading', this.startLoad);
    bus.$on('end:Loading', this.stopLoad);

    let url = this.$route.name;
    this.checkBoard(url);
   
    

    const arr = document.cookie.split(";");
    const logInfo = {
      AccessData: "",
      AccessToken: "",
    };
    arr.forEach((element) => {
      //console.log("In App, element is : ", element);
      if (element.split("=")[0].trim() == "AccessToken") {
        // logInfo.AccessToken = element.split('=')[1];
        logInfo.AccessToken = element.split("=")[1];
      }
      if (element.split("=")[0].trim() == "AccessData") {
        const a = element.split("=")[1].split("%2C");
        logInfo.AccessData = {
          email: unescape(a[0].split("%3A")[1]),
          name: decodeURI(a[1].split("%3A")[1]),
          nickname: decodeURI(a[2].split("%3A")[1]),
        };
        // logInfo.AccessData = unescape(element.split('=')[1]);
      }
    });

    //console.log("In App, logInfo is : ", logInfo);

    if (logInfo.AccessData != "" && logInfo.AccessData != "") {
      this.$store.commit("setDataAgain", logInfo);
      this.$store.dispatch(constants.METHODS.GET_USER, logInfo.AccessData);
    }
  },
  watch: {
    $route(to) {
      this.checkBoard(to.name);
    },
  },
  methods: {

    checkBoard(url) {
      let array = ["enter","join"];
      let isBoard = false;
      array.map((path) => {
     
        
        if (url === path) 
        {isBoard = true;}
      });
      this.isBoard = isBoard;
    },

    startLoad(){
      
      var rVar = Math.floor(Math.random() * 4);
      this.LoadingIcon = rVar;

      this.LoadingStatus = true;
      
    },
    stopLoad(){
      this.LoadingStatus = false;
    }
  },
  data: function () {
    return {
      isHeader: true,
      isBoard: false,
      constants,
      LoadingStatus: false,
      LoadingIcon: 0,
      isMobile: false,
    };
  },
  mounted(){
    

    var device = "win16|win32|win64|mac|macintel";

    if ( navigator.platform ) {
      if ( device.indexOf(navigator.platform.toLowerCase()) < 0 ) {
          this.isMobile = false;
      } else {
          this.isMobile = true;
      }
      this.isMobile = !this.isMobile
    }
  },
  beforeDestroy() {
    bus.$off('start:Loading');
    bus.$off('end:Loading');
  }
};
</script>

<style >
  #container{
    /* width: 90vw; */
    /* border: solid gray 1px; */
    background-color: white;
    /* border-radius: 2%; */
    height: 100vh;
    /* margin: 25px; */
    /* padding: 10px; */
    /* margin-left: 50px; */
  }
  #bg{
    background-color: rgba(180, 179, 179, 0.26);
  }
  
</style>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #2c3e50;
}

.footerText{
  text-decoration: none;
  color: #000000;
  font-size: 0.85vw;
  display: block;
  margin: auto;
  width: fit-content
}

::-webkit-scrollbar {
  display: none;
}

</style>
