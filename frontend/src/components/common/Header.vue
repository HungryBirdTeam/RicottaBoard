<template>
  <div id="header">
    <button @click="goWhere()">
      <img class="headLogoIcon"
        style=""
        src="../../assets/img/LogoIcon.png"
      />
      <span class="headLogoTitle">Ricotta Board</span>
    </button>

    <!-- <div class="right"> -->
      <template v-if="this.$store.getters.accessToken != ''">
        <div class="headBox">
          <router-link style="margin-left: 20px; padding-top:5px"
            :to="{name:constants.URL_TYPE.USER.MYPAGE}"
            class="btn--text"
          >
          {{(this.$store.getters.userData.nickname)}}
          </router-link>
          님 환영합니다!
          <v-btn dark class="allbtn" style="height: 100%;  " outlined color="white" @click="logout">Logout</v-btn>
        </div>
      </template>
      <template v-if="this.$store.getters.accessToken == ''">
        <div class="headBox">
          <router-link style="margin-left: 20px; padding-top:5px"
            :to="{name:constants.URL_TYPE.USER.JOIN}"
          >
            <v-btn style="height: 100%;  " dark class="allbtn px-5 py-2" outlined color="white">Sign up</v-btn>
          </router-link>

          <v-dialog  width="350px ">
            <template v-slot:activator="{ on, attrs }">                                    
              <v-btn
                dark 
                class="allbtn px-5 py-2"
                v-bind="attrs"
                v-on="on"
                outlined
                color="white"
                style="height: 100%;  "
                v-if="$store.getters.accessToken == ''"
              >
              LOGIN
              </v-btn>
            </template>
            <v-card style="width:350px; height: 280px">
              <v-card-title>LOGIN</v-card-title>
              <v-card-text style=" background-color:white; height:90px; padding-bottom:0"><Login style="height:120px;padding-bottom:0"/></v-card-text>
            </v-card>
          </v-dialog>          
        </div>
      </template>

      <!-- <button @click="check">
                        스토어 체크
      </button>-->
    <!-- </div> -->
    <div class="menuBox">
      <v-menu>

        <template v-slot:activator="{ on, attrs }" 
            justify="space-around"> 
          <v-btn
            color="primary"
            v-bind="attrs"
            v-on="on"
            style="height:100%;"  
          >
            <v-icon color="secondary">mdi-menu</v-icon>
          </v-btn>
        </template>

        <v-card>
          <v-list>
            <v-list-item>
              <v-list-item-content  v-if="this.$store.getters.accessToken != ''">
                <router-link style="margin-left: 20px; padding-top:5px"
                  :to="{name:constants.URL_TYPE.USER.MYPAGE}"
                  class="btn--text"
                >
                {{(this.$store.getters.userData.nickname)}}
                </router-link>
                님 환영합니다!
              </v-list-item-content>

              <v-list-item-content v-else>
                로그인을 먼저 해주세요.
              </v-list-item-content>
            </v-list-item>
          </v-list>


          <v-divider></v-divider>
            

          <v-list>
            <v-list-item v-if="this.$store.getters.accessToken != ''">
              <div>
                <v-btn dark class="allbtn" style="height: 100%;  " outlined color="black" @click="logout">Logout</v-btn>
              </div>
            </v-list-item>
            <v-list-item v-else>
              <div style="margin: auto;">
                <v-dialog  width="350px ">
                  <template  v-slot:activator="{ on, attrs }">
                    <a color="primary" v-on="on" v-bind="attrs" style="margin:auto;"> 
                    LOGIN</a>
                  </template>
                  <v-card style="width:350px; height: 280px">
                    <v-card-title>LOGIN</v-card-title>
                    <v-card-text style=" background-color:white; height:90px; padding-bottom:0"><Login style="height:120px;padding-bottom:0"/></v-card-text>
                  </v-card>
                </v-dialog>          
              </div>
            </v-list-item>
            <v-list-item>
                <router-link style="margin: auto;"
                  :to="{name:constants.URL_TYPE.USER.JOIN}"
                >
                Sign up
                  </router-link>
            </v-list-item>
          </v-list>
        </v-card>

      </v-menu>
    </div>
  </div>
</template>   

<script>
import constants from "../../lib/constants";
import cookies from "vue-cookie";
import logo from "../../assets/img/Logo.png";
import Login from '@/page/user/Login'

export default {
  name: "Header",
  components: {
    Login,
  },
  props: ["isHeader"],
  watch: {
  },
  created() {
    const arr = document.cookie.split(";");
  },
  computed: {},
  methods: {
    goWhere() {
      if (this.$store.getters.accessToken == '') {
        this.$router.push({ name: constants.URL_TYPE.POST.ENTER })
      } else {
        this.$router.push({ name: constants.URL_TYPE.POST.MAIN })
      }
    },
    async logout() {
      this.$store.commit(constants.METHODS.LOGOUT_USER);
      try {
        await this.$router.push("/");
      } catch (error) {
        //console.log("route same path!");
      }
    },
    check() {
      //console.log(this.$store.state);
    },
  },
  data: function () {
    return {
      constants,
      userinfo: "",
      email: "",
      password: "",

      fav: true,
      menu: false,
      message: false,
      hints: true,
    };
  },
};
</script>
<style>
#header {
  background:#0d875C;
  border:solid 0px;
  height: 50px;
  padding: 1.5vh 0px 1.5vh 0px;
}
.headLogoIcon {
  position: absolute;
  top: 0px;
  padding: 14px;
  padding: 9px;
  margin: 0px 10px;
  width: auto;
  height: 50px;

}
.headLogoTitle {
  left: 64px;
  top: 5px;
  font-family: "Candara";
  font-size: 27px;
  position: absolute;
  color: #F5F5EC;
}

#header a{
  color: white;
  text-decoration: none;

}

.toolbar {
  background:#0d875C;
  width: 100vw;
}

.allbtn {
  text-align: center;
  /* color: red !important; */
  height: 3.8vh
}

.headBox{
    color:rgba(255, 255, 255, 0.75);
    line-height: 25px;
    height: 100%;
    float:right;
}

.headBox :nth-child(n+2) {
    margin: 0px 16px;
}

@media screen and (min-width: 701px){
  .headBox{
  }
  .menuBox {
    display: none;
  }
}
@media screen and (max-width: 700px) {
  .headBox {
    display: none;
  }
  .menuBox {
    display: block;
    float: right;
    height: 100%;
    margin: 0px 16px;
  }
}


</style>




