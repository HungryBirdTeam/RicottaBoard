<template>
  <div class="user custom" id="login">
    <div class="wrapC table">
      <div class="middle justify-content-center">
        <div class="input-wrap">
          <input
            id="email"
            v-model="email"
            placeholder="이메일을 입력해주세요"
            type="text"
            class="input-border"
            @keypress.enter="login(email, password)"
          />
        </div>
        <div class="input-wrap">
          <input
            id="password"
            class="input-border"
            v-model="password"
            type="password" 
            placeholder="영문, 숫자 혼용 8자 이상"
            @keypress.enter="login(email, password)"
          />
        </div>
       

        <div class="add-option mb-4">
          <div class="wrap" >
            <p style="margin-bottom:0%">아직 회원이 아니신가요?</p>
            <router-link 
              @click="this.$router.go(0)"
              v-bind:to="{name:constants.URL_TYPE.USER.JOIN}"
              class="btn--text"
              style="color:black; margin-top:0"
            >회원가입</router-link>
            <br>

            <router-link 
              @click="this.$router.go(0)"
              v-bind:to="{name:constants.URL_TYPE.USER.PASSWORDFIND}"
              class="btn--text"
              style="color:black; margin-top:0"
            >비밀번호 재설정</router-link>
            
            <p style="margin-bottom:0%">비밀번호를 잊으셨나요?</p>
          </div>
        </div>

        <v-btn dark depressed block class="allbtn mt-3" color="#0d875C" @click="login(email, password)">로그인 하기</v-btn>
      </div>
    </div>
  </div>
</template>

<script>
import "../../assets/css/user.scss";
import constants from "../../lib/constants";
import cookies from "vue-cookie";
import router from "vue-router";

export default {
  components: {},
  created() {},
  watch: {},
  methods: {
    newnew() {
      this.$router.go(0);
    },
    login(email, password) {
      console.log(email, password);

      var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
      if (exptext.test(email) == false) {
        //이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우
        alert("이메일형식이 올바르지 않습니다.");
      } else if (password == "") {
        alert("비밀번호를 입력해주세요");
      } else {
        const result = this.$store.dispatch(constants.METHODS.LOGIN_USER, {
          email,
          password,
        });
        console.log(this.userData);
        this.modal = !this.modal;
      }
    },
  },
  data: () => {
    return {
      constants,
      email: "",
      password: "",
      modal: false,
    };
  },
};
</script>


<style lang="scss" scoped>
</style>

<style scoped>
.input-border {
  border:solid 1px;
  border-color: #0d875C;
  width:100%;
  margin-bottom:0px;
}

.allbtn {
    text-align: center;
    margin-top:20px;
    font-size:15px;
}
</style>
