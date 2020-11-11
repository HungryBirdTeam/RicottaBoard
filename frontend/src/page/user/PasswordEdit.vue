<template>
  <div class="changePassword" style="background-color:#f5f5ec">
    <div class="d-flex justify-content-center">
      <div class="changePasswordForm col text-center mt-3">
        <h2 class="m-3">비밀번호 변경</h2>
        <p class="lead m-3">비밀번호를 변경하시겠습니까?</p>
        <div>
          <label class="col text-left pb-0 pt-2">기존 비밀번호</label>
          <input
            id="password"
            class="inputsForm col"
            type="password"
            placeholder="기존 비밀번호를 입력해주세요."
            v-model="password.origin"
            @change="changeIt()"
            @keypress.enter="submit()"
          >
        </div>
        <div>
          <label class="col text-left pb-0 pt-2">새 비밀번호</label>
          <input
            id="newPassword"
            class="inputsForm col"
            type="password"
            v-model="password.new"
            placeholder="새 비밀번호를 입력해주세요."
            @change="changeIt()"
            @keypress.enter="submit()"
          >
        </div>
        <div>
          <label class="col text-left pb-0 pt-2">비밀번호 확인</label>
          <input
            id="passwordConfirm"
            class="inputsForm col"
            type="password"
            v-model="password.confirm"
            placeholder="비밀번호를 한번더 입력해주세요."
            @change="changeIt()"
            @keypress.enter="submit()"
          >
        </div>
        <button
          @click="submit"
          class="updateButton text-white p-2 mt-5"
          type="button"
        >
          <span>변 경</span>
        </button>
      </div>
      <v-snackbar
          centered
          v-model="snackbar.isPresent"
          :timeout="snackbar.timeout"
          :color="snackbar.color"
      >{{ snackbar.text }}</v-snackbar>
    </div>

    <footer
      class="mx-auto wrap"
      style="text-align:center; position:absolute; bottom:10px;"
    >
      <p class="footerText" @click="teamPage()">ⓒHungrybird</p>
    </footer>
  </div>
</template>



<script>
import '../../assets/css/user.scss'
import constants from '../../lib/constants'

export default {
    components: {
    },
    created(){
    },
    methods: {   
      changeIt() {
      },      
      submit(){
        // console.log("kk", this.isChange)
        // if (this.isChange) {
        //   console.log('sucess')
        //   const newUser = {
        //     "email": this.userData.email,
        //     "username": this.userData.username,
        //     "nickname": this.userData.nickname,
        //     "password": this.passwordCheck,
        //   }
        //   this.$store.dispatch(constants.METHODS.USER_INFO, newUser);
        //   this.passwordCheck = "";
        // } else {
        //   this.createSnackbar("값이 모두 입력되지 않았습니다.", 2000, "error")
        // }
      },        
      teamPage() {
          this.$router.push('/@hungrybird')
      },
      createSnackbar(text, timeout, color) {
        this.snackbar.isPresent = true;
        this.snackbar.text = text;
        this.snackbar.timeout = timeout;
        this.snackbar.color = color;
      },
    },
    computed:{
        
    },
    watch: {
    },
    data: () => {
        return {
            password:{
              orign:'',
              new:'',
              confirm:'',
            },
            snackbar: {
              isPresent: false,
              text: "",
              timeout: 1000,
            },
            isChange: false,
            constants,
        }
    }

}

</script>

<style scoped>

.changePassword {
  background:#f5f5ec;
}
.changePasswordForm {
  max-width: 500px;
}
.lh-condensed { line-height: 1.25; }

.inputsForm {
  border:solid 1px #dadada;
  height: 45px;
  border-radius: 5px;
  background-color:white;
}

.updateButton {
  border-radius: 5px;
  width: 100%;
  background-color:#0d875C;
  border:solid 0px;
}
</style>



