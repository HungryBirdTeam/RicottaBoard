<template>
     <div class="user" id="passwordReset">
      <div class="myBox">
          <div class="middle">
              <h2>비밀번호 재설정</h2>
              <div class="form-wrap" v-if="this.$store.getters.status == ''">
                <div class="info-wrap">
                    <label for="">비밀번호 재설정</label>
                    <input type="password" id="password" v-model="password"
                    placeholder="새 비밀번호를 입력해주세요.">
                </div>
                
                <div class="info-wrap">
                    <label for="">비밀번호 확인</label>
                    <input type="password" id="passwordConfirm" v-model="passwordConfirm"
                    placeholder="비밀번호를 다시 입력해주세요.">
                </div>

        
                <div class="info-wrap">
                        <button v-on:click="resetMyPassword">비밀번호 재설정</button>
                </div>

              </div>

              <div class="form-wrap" v-if="this.$store.getters.status != ''">
                  {{this.$store.getters.status}}
              </div>
          </div>
      </div>
  </div>
</template>

<script>
    import '../../assets/css/user.scss'
    import constants from '../../lib/constants'

    export default {
        data: () =>{
            return {
                password:'',
                passwordConfirm:'',
            }
        },
        methods:   {
            resetMyPassword() {
                const token = this.$route.query.token;

                if(this.password != this.passwordConfirm){
                    return;
                }

                const newData = {
                    "password": this.password,
                    "passwordConfirm": this.passwordConfirm,
                    "token": token,
                }

                this.$store.dispatch(constants.METHODS.RESETMYPASSWORD, newData);
            }
        }
    }
</script>

<style>

</style>