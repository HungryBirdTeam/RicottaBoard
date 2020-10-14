<template>
    <div class="user" id="join"> 
        <div class="wrapC table">
            <div class="middle">
                <h1>회원가입</h1>
                <div class="form-wrap">
                    <div class="input-wrap">
                        <input v-model="nickName"
                            id="nickName"
                            placeholder="닉네임을 입력해주세요" type="text"/>
                    </div>

                    <div class="input-wrap">
                        <input v-model.lazy="email" 
                            id="email"
                            placeholder="이메일을 입력해주세요"
                            type="text"
                            @change="emailCheck"/>

                        <!-- 이메일이 사용중인지 체크 -->
                        {{this.$store.getters.canIUseIt}}
                    </div>

                    <div class="input-wrap">
                        <input v-model="realName" 
                            id="realName"
                            placeholder="실명을 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap password-wrap">
                        <input v-model="password"
                            id="password" 
                            :type="passwordType"
                            placeholder="비밀번호를 입력해주세요"/>
                        <span :class="{active : passwordType==='text'}">
                                <i class="fas fa-eye"></i>
                            </span>
                    </div>

                    <div class="input-wrap password-wrap">
                        <input v-model="passwordConfirm" 
                            id="passwordConfirm"
                            :type="passwordConfirmType"
                            placeholder="비밀번호를 한번 더 입력해주세요"/>
                        <span :class="{active : passwordConfirmType==='text'}">
                                <i class="fas fa-eye"></i> 
                            </span>
                    </div>
                </div>

                <label>
                    <input v-model="isTerm" type="checkbox" id="term"/>
                    <span>약관에 동의합니다</span>
                </label>

                <span class="go-term">약관 보기</span>

                <button  @click="createUserRequest"  class="btn">
                    <span>
                        작성완료
                    </span>
                </button>

                <!-- <router-link v-bind:to="{name:constants.URL_TYPE.USER.JOINDONE}" @click="createUserRequest" class="btn">
                작성완료 
                </router-link>   -->
            </div>


        </div> 
        

    </div>
</template>

<script>
    import '../../assets/css/user.scss'
    import constants from '../../lib/constants'

    export default {
        components: {
        },
        watch:{
        },
        created(){
        },
        methods: {
            createUserRequest: function(){
                var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
                var passwordExp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d$@$!%*#?&]{8,}$/;
                if(nickName.value == ''){
                    alert("닉네임을 입력해주세요");
                }
                else if(exptext.test(email.value)==false){
                    //이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우            
                    alert("이메일형식이 올바르지 않습니다.");
                }
                else if(realName.value ==''){
                    alert("이름을 입력해주세요");
                }
                else if(password.value =='')
                {
                    alert("비밀번호를 입력해주세요");
                }
                else if (passwordExp.test(password.value) == false){
                    alert("비밀번호 형식이 잘못되었습니다.");
                }
                else if(password.value != passwordConfirm.value){
                    alert("비밀번호가 동일하지않습니다. 다시 입력해주세요.")
                } 
                else if(!this.isTerm){
                    alert("약관을 읽어보시고, 동의란에 체크해주세요.")
                }
                else{
                    this.$store.dispatch(constants.METHODS.CREATE_USER, {
                    email, password, nickName, realName
                     });
                this.$router.push(constants.URL_TYPE.USER.JOINDONE);
                }
            },
            emailCheck: function(){
                this.$store.dispatch(constants.METHODS.EMAILCHECK, email.value);
            },
            test1: function(){
                console.log(this.email);
            }

        },
        watch: {
        },
        data: () => {
            return {
                email: '',
                nickName: '',
                realName:'',
                password: '',
                passwordConfirm: '',
                isTerm: false,
                passwordType:"password",
                passwordConfirmType:"password",
                constants
            }
        }

    }

</script>


