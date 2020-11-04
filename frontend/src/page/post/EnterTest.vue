<template>
    <div style=" padding:0px; margin:0px;">
        // transition을 하나로 하면 leave-active 효과가 나타나지 않음
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
            <div>
                <v-toolbar dark color="#0d875C">
                    <v-app-bar-nav-icon></v-app-bar-nav-icon>
                    <v-toolbar-title>                        
                        <img
                            style="position:absolute;top:0px;  margin-top:7px; width:auto;height:50px;"
                            src="../../assets/img/Logo.png"
                        />
                    </v-toolbar-title>

                    <v-spacer></v-spacer>

                    <v-btn color="#0d875C" border="white">
                        Login
                    </v-btn>
                </v-toolbar>

                <div
                    class="Main"
                >
                    <Main v-if="isMain" />
                    <Guide v-if="isGuide" />
                    <button @click="changeComponent"> click </button>
                </div>
            </div>    
        </transition>  
    </div>
</template>

<script>
import Main from "../../components/enter/Main"
import Guide from "../../components/enter/Guide"

export default {
    components: {
        Main,
        Guide,
    },
    data() {
        return {
            isStarting: false,
            isMain: true,
            isGuide: false,
        };
    },
    methods: {
        changeScreen() {
            this.isStarting = true;
            console.log('after', this.isStarting);
        },
        changeComponent() {        
            if (this.isMain) {
                this.isMain = false;
                this.isGuide = true;
            } else {
                this.isMain = true;
                this.isGuide = false; 
            };
        }
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
    height:100%;
    left:0px;
    top:0px;
    background: #0d875C;
    text-align: center;
    z-index: 1;
}

.Main {
    width:100%;
    height:100%;
    left:0px;
    top:0px;
    background: #f5f5ec;
    text-align: center;
}

.loader-leave, .loader-leave-to {
    opacity: 0;
}

.loader-leave-active{
    transition: opacity .8s;
}
</style>