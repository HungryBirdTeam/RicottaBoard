<template>
  <div class="user" id="reallyDeleteIt">
        <div class="myBox">
            <div class="middle">
                <div class="form-wrap" v-if="this.$store.getters.status == ''">
                    <h2>정말로 계정을 삭제하시겠습니까??</h2>

                    <div class="info-wrap">
                        <label for="">비밀번호를 입력해주세요</label>
                        <input type="password" id="password" v-model="password"
                        placeholder="확인용 비밀번호를 입력해주세요.">
                    </div>
                
                    <div class="info-wrap">
                        <label for="">비밀번호 확인</label>
                        <input type="password" id="passwordConfirm" v-model="passwordConfirm"
                        placeholder="비밀번호를 다시 입력해주세요.">
                    </div>
            
                    <div class="info-wrap">
                        <button v-on:click="deleteMyAccount">계정 탈퇴</button>
                    </div>
                </div>
                <div class="form-wrap" v-else>
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
            deleteMyAccount() {
                const token = this.$route.query.token;

                    if(this.password == ''){return;}
                    if(this.password != this.passwordConfirm){
                        return;
                    }
                    else {
                        const data = {
                            "email":this.$store.getters.userData.email,
                            "password":this.password
                        }
                        console.log(data);
                        this.$store.dispatch(constants.METHODS.DELETE_USER, data);

                    }
            }
        }
    }
</script>

<style>

</style>