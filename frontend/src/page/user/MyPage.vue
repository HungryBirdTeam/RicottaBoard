<template class="bg-light">
    <div class="container">
  <div class="py-5 text-center">
    <!-- <img class="d-block mx-auto mb-4" src="../assets/brand/bootstrap-solid.svg" alt="" width="72" height="72"> -->
    <h2>회원정보 수정</h2>
    <p class="lead">프로필 수정을 하실 수 있습니다.</p>
  </div>

    
  <div class="col-md-8 order-md-1" style="margin-right: auto;
    margin-left: auto;">
      <h4 class="mb-3">내 프로필</h4>
      <form class="needs-validation" novalidate>

        <div class="mb-3">
            <label for="firstName">이름</label>
            <input type="text" class="form-control" id="Name" placeholder="Name" v-model="this.userData.name" required>
            <div class="invalid-feedback" style="width: 100%;">
              이름을 작성해주세요.
            </div>
        </div>
        

        <div class="mb-3">
            <label for="firstName">비밀번호</label>
            <input type="password" class="form-control" id="password" placeholder="Password" v-model="this.userData.password" required>
            <div class="invalid-feedback" style="width: 100%;">
              비밀번호를 작성해주세요.
            </div>
        </div>

        <div class="mb-3">
          <label for="username">닉네임</label>
          <div class="input-group">
            <input type="text" class="form-control" id="username" placeholder="Nickname" v-model="this.userData.nickname" required>
            <div class="invalid-feedback" style="width: 100%;">
              닉네임을 작성해주세요.
            </div>
          </div>
        </div>

        <div class="mb-3">
          <label for="email">Email</label>
          <input type="email" class="form-control" id="email" placeholder="you@example.com" v-model="this.userData.email">
          <div class="invalid-feedback">
            이메일을 작성해주세요.
          </div>
        </div>

        <br>
        <button class="btn btn-primary btn-lg btn-block" type="submit" @click="submit">수정</button>
        <!-- <button class="btn btn-danger btn-lg btn-block" >회원 탈퇴</button> -->

        <router-link
         v-bind:to="{name:constants.URL_TYPE.USER.DELETEUSER}"
          class="btn btn-danger btn-lg btn-block" style="color:white;">
                                회원 탈퇴
        </router-link> 
    
      </form>
  </div>

  <footer class="my-5 pt-5 text-muted text-center text-small">
    <p class="mb-1">&copy; 2017-2020 이거모임!?</p>
  </footer>
</div>
<!-- <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
      <script>window.jQuery || document.write('<script src="../assets/js/vendor/jquery.slim.min.js"><\/script>')</script><script src="../assets/dist/js/bootstrap.bundle.min.js"></script>
        <script src="form-validation.js"></script> -->
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
            }
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
                passwordConfirm: '',
                isTerm: false,
                passwordType:"password",
                passwordConfirmType:"password",
                constants
            }
        }

    }

</script>
<style scoped>

.container {
  max-width: 960px;
}

.lh-condensed { line-height: 1.25; }

</style>



