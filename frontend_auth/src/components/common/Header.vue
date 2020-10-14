<template>
    <div id="header" v-if="isHeader">
        <h1>
            <router-link v-bind:to="{name:constants.URL_TYPE.POST.MAIN}">
                SS_log
            </router-link>
        </h1>
        <div class="right">
            <!-- <template v-if="this.$store.state.email == ''"> -->
            <template v-if="this.$store.getters.accessToken == ''">
                <div class="headBox">
                    <button @click="openModal">
                        로그인
                    </button>
                </div>
            </template>

            <template v-else>
                <div class="headBox">
                    <router-link v-bind:to="{name:constants.URL_TYPE.USER.MYPAGE}" class="btn--text">
                        {{this.$store.state.userData.email}}
                    </router-link> 

                    <button @click="logout">
                        로그아웃
                    </button>
                </div>
            </template>

            
                    <button @click="check">
                        스토어 체크
                    </button>

        </div>  
        
        
            <loginModal @close="closeModal" v-if="modal">
                
                
                <div class="input-wrap">
                        <input v-model="email"
                        id="email" 
                        placeholder="이메일을 입력해주세요"
                        type="text"/>
                    </div>
                    <div class="input-wrap">
                        <input v-model="password" type="password"
                        id="password"
                        placeholder="비밀번호를 입력해주세요"/>
                    </div>
                
                
                <template slot="footer">
                                <button class="btn btn--back btn--login" v-on:click="testMethod(email, password)">
                                    로그인 하기
                                </button>
                                
                </template>

            </loginModal>
        
    </div>
</template>   

<script> 
    import loginModal from '../../page/user/Login.vue'
    import constants from '../../lib/constants'
    import cookies from 'vue-cookie'

    export default {
        name: 'Header',
        components: { 
            loginModal
        },
        props: ['isHeader'],
        watch: {
            modal: function(val) {
                console.log(val);
            }
        },
        created() {
            const arr = document.cookie.split(";");
            console.log(this.$store.getters.userData);
            // console.log("arr is ");
            // console.log(arr);
            arr.forEach(element => {
                if(element.split('=')[0] == 'AccessToken'){
                    this.userinfo = element.split('=')[1];
                }
            });

            // if(document.cookie.split(";")[0].split('=')[0])
            // console.log();

            
        },
        computed: {
            
        },
        methods : {
            openModal(){
                this.modal = true;
            },
            closeModal(){
                this.modal = false;
            },
            async logout(){
                this.$store.commit(constants.METHODS.LOGOUT_USER);
                try{
                   await this.$router.push('/');
                } catch(error) {
                    console.log("route same path!");
                } 
            },
            check(){
                console.log(this.$store.state);
            },
            toggle(){
                this.t = !this.t;
            },
             testMethod(email, password){
                console.log(email,password);

                var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
                if(exptext.test(email)==false){
                    //이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우            
                    alert("이메일형식이 올바르지 않습니다.");
                  
                }
                else if(password=='')
                {
                    alert("비밀번호를 입력해주세요");
                }
                else {

                    const result = this.$store.dispatch(constants.METHODS.LOGIN_USER, {email, password});
                    console.log(this.userData);this.modal = !this.modal;
                    this.email = '';
                };

                    
                    this.password = '';
                
            },
            
        },
        data: function() {
           return {
               constants,
               keyword : "",
               modal:this.$store.state.modal,
                userinfo: '',
                email:"",
                password:"",
           }
        },

    }


</script>




