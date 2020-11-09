<template>
    <div style=" padding:0px; margin:0px;">
        <transition name="loader" >
            <div
                class="Loading"
                v-if="!isStarting"
            >
                <img
                    src="../../assets/img/Logo.png"
                    style="margin:20vw; margin-top:40vh; width:30%; height:auto;"
                />
                <footer
                    class="text-white"
                    style="width:100vw; text-align: center; position:absolute; bottom:10px; "
                >
                    <p>ⓒHungrybird</p>
                </footer>
            </div>  
        </transition>
        <transition name="starter">
            <div v-if="isStarting" class="mainEnter">
                <div>
                    <h2>이미지가 들어갑니다.</h2>
                </div>

                <h4 style="font-size:30px; line-height:150%;">
                리코타보드는 온라인 및 오프라인 모임을 지원하는 협업툴 플랫폼입니다
                </h4>
                
                <div class="row">
                    <div class="col-4">
                        <h4 style=" font-size:24px; line-height:130%;">
                            계정이 있으신가요?
                            <br>
                            지금 로그인하세요!
                            <br />
                            <v-btn class="allbtn px-5 py-2" color="#0d875C">
                                <LoginModal />
                            </v-btn>
                            <br>
                            없다면 가입하고 이용해보세요!
                            <br>
                            <router-link to="user/signup">
                                <v-btn class="allbtn px-5 py-2" color="#0d875C">Sign up</v-btn>
                            </router-link>
                        </h4>
                    </div>
                    
                    <div class="col-4">
                        <h4 class="comment">
                            체험해보시고 싶으신가요?
                            <br>
                            아래 버튼을 눌러보세요!
                            <br>
                            <router-link to="channel/earlyBird10TeamTestChannel1">
                                <v-btn class="allbtn px-5 py-2" color="#0d875C">Try it</v-btn>
                            </router-link>
                        </h4>                                
                    </div>
                    
                    <div class="col-4">
                        <h4 class="comment">
                            저희 서비스가 궁금하신가요?
                            <br>
                            가이드를 받아보세요!
                            <br>
                            <router-link to="/guide">
                                <v-btn class="allbtn px-5 py-2" color="#0d875C">Guide</v-btn>
                            </router-link>
                        </h4>
                    </div>
                </div>
                <footer
                    style="width:100vw; text-align:center; position:absolute; bottom:10px; "
                >
                    <p class="footerText" @click="teamPage()">ⓒHungrybird</p>
                </footer>
            </div>
        </transition>  
    </div>
</template>

<script>
import LoginModal from "../../components/common/LoginModal";

export default {
    components: { LoginModal },
    data() {
        return {
            isStarting: false,
        };
    },
    methods: {
        changeScreen() {
            this.isStarting = true;
            console.log('after', this.isStarting);
        },
        teamPage() {
            this.$router.push('/@hungrybird')
        },
    },
    created() {
        this.isStarting = false;
        console.log('before', this.isStarting);
        setTimeout(() => (this.isStarting = true), 1000);
    },
}
</script>

<style scoped>

.Loading {
    width:100%;
    height:100vh;
    left:0px;
    top:0px;
    background: #0d875C;
    text-align: center;
    z-index: 1;
}

.mainEnter {
    width:100%;
    height:100vh;
    left:0px;
    top:0px;
    background: #f5f5ec;
    text-align: center;
    z-index: 0;
}

.comment {
    width: 100%;
    height:100%;
    font-size:24px;
    line-height:130%;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}

.allbtn {
    min-width: 25%;
    text-align: center;
    color: white;
    margin: 1.5rem;
}

.loader-leave, .loader-leave-to {
    opacity: 0;
}

.loader-leave-active{
    transition: opacity 1.0s;
}

.starter-enter, .starter-enter-to {
    opacity: 0;
}

.starter-enter-active{
    transition: opacity 1.0s;
}

.footerText { 
    text-decoration: none;
    color:#000000;
}
</style>