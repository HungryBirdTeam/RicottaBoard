<template>
  <div class="Profile" style="background-color:#f5f5ec">
    <div class="d-flex justify-content-center">
      <div class="profile col text-center mt-3">
        <h2 class="m-3">내 프로필</h2>
        <p class="lead m-3">비밀번호를 입력해야 수정할 수 있습니다.</p>
        <div>
          <label class="col text-left pb-0 pt-2">이메일</label>
          <input
            id="email"
            class="inputsForm col"
            v-model="this.userData.email"
            disabled
          >
        </div>
        <div>
          <label class="col text-left pb-0 pt-2">이름</label>
          <input
            id="name"
            class="inputsForm col"
            v-model="this.userData.name"
            placeholder="이름을 입력해주세요."
            @change="changeName"
          >
        </div>
        <div>
          <label class="col text-left pb-0 pt-2">닉네임</label>
          <input
            id="nickname"
            class="inputsForm col"
            v-model="this.userData.nickname"
            placeholder="닉네임을 입력해주세요."
            @change="changeNickname"
          >
        </div>
        <div>
          <label class="col text-left pb-0 pt-2">비밀번호</label>
          <input
            id="password"
            class="inputsForm col"
            type="password"
            v-model="this.passwordConfirm"
            placeholder="비밀번호를 입력해주세요."
            @change="changePassword"
          >
        </div>
        <button
          @click="submit"
          class="updateButton text-white p-2 mt-5"
          type="button"
        >
          <span>확  인</span>
        </button>
        <div class="row px-2 py-1 justify-content-between">
          <p class="lead text-left m-2 my-0">비밀번호를 변경하시겠습니까?</p>
          <router-link to="/@hungrybird">
            <p class="lead passwordButton text-right m-2 my-0">비밀번호 변경</p>
          </router-link>
        </div>
        <div class="row px-2 py-1 justify-content-between">
          <p class="lead text-left m-2 my-0">탈퇴하시겠습니까?</p>
          <router-link :to="{name:constants.URL_TYPE.USER.DELETEUSER}">
            <p class="lead outButton text-right m-2 my-0">회원 탈퇴</p>
          </router-link>
        </div>
      </div>
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
      this.userData = this.$store.getters.userData;
    },
    methods: {   
      chageName() {
        if (this.userData.name.length == 0) {
          
        }
      },      
      submit(){
          // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation')

        // Loop over them and prevent submission
        Array.prototype.filter.call(forms, function (form) {
          form.addEventListener('submit', function (event) {
            if (form.checkValidity() === false) {
              event.preventDefault()
              event.stopPropagation()
            }
            form.classList.add('was-validated')
          }, false)
        })
      },        
      teamPage() {
          this.$router.push('/@hungrybird')
      },
    },
    computed:{
        
    },
    watch: {
    },
    data: () => {
        return {
            userData:{
              email:'',
              name:'',
              password:'',
              nickname:'',
            },
            messages: {
              name: '',
              password: '',
              nickname: '',
            },
            constants,
            passwordConfirm: '',
        }
    }

}

</script>

<style scoped>

.Profile {
  background:#f5f5ec;
}
.profile {
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

.passwordButton {
  color:#0d875C;
}

.passwordButton:hover {
  text-decoration:underline;
  text-decoration-color:#0d875C;  
}

.outButton {
  color:red;
}

.outButton:hover {
  text-decoration:underline;
  text-decoration-color:red;  
}
</style>



