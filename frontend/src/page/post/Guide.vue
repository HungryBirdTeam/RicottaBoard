<template>
    <div style=" padding:0px; margin:0px;">
        <v-toolbar dark color="#0d875C">
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
        <div class="intro row">
            <div class="introText m-auto">
                <h4 style=" font-size:30px;  line-height:150%;">리코타보드는 다양한 기술을 탐구하고 적용하여 사용자들이 최상의 서비스를 경험할 수 있도록 노력하고 있습니다</h4>
            </div>
            <div class="introImg m-auto">
                <img style="width:100% " src="../../assets/img/Used.png" />
            </div>
        </div>
        <div class="guide row">
            <div class="guideCarousel">
                <v-carousel  style="width:90%; height:100% " hide-delimiters @change="changePage">
                    <v-carousel-item v-for="(item, idx) in items" :key="idx" :src="item.src"></v-carousel-item>
                </v-carousel>
            </div>
            <div class="guideText">
                <h4 style=" font-size:30px; line-height:150%;">{{ explain }}</h4>
            </div>
        </div>
        <footer
          class="footer"
        >
          <p>ⓒHungryBird</p>
        </footer>
    </div>
</template>

<script>
import example1 from "../../assets/img/example1.png";
import example2 from "../../assets/img/example2.png";
import example3 from "../../assets/img/example3.png";
import example4 from "../../assets/img/example4.png";

export default {
    data() {
        return {            
            items: [
                {
                    src: example1,
                },
                {
                    src: example2,
                },
                {
                    src: example3,
                },
                {
                    src: example4,
                },
            ],
            page: 0,
            explain: "post-it을 이용해서 회의용 메모를 이용해 보세요!"
        }
    },
    methods: {
        changePage(page) {
            if (page == 0) {
                this.explain = "post-it을 이용해서 회의용 메모를 이용해 보세요!"
            } else if (page == 1) {
                this.explain = "poll으로 팀원들과 투표를 통해 결정을 내려보세요!"
            } else if (page == 2) {
                this.explain = "kanban은 그날 할 작업을 관리하여 작업을 효율적으로 진행하도록 도와줍니다."
            } else if (page == 3) {
                this.explain = "scheduler를 통해 작업 스케쥴을 편리하게 관리할 수 있습니다!"
            }
            console.log('change!')
            console.log('test', dataset)
        },
    },
}
</script>

<style scoped>
.intro {
    width: 100vw;
    height: 100vh;
    background:#f5f5ec;
}

.introText{
    width:50%;
    text-align: center;
    padding:3rem;
}

.introImg{
    width:50%;
    padding:3rem;
}

.guide {
    width:100vw;
    height: 80vh;
    background: #f5f5ec;
}

.guideCarousel {
    width:60%;
    padding:3rem;
}

.guideText {
    width:40%;
    text-align: center;
    margin: auto;
    padding:2rem;
    padding-left: 0;
}

.footer {
    width:100vw;
    text-align: center;
    position:absolute;
    bottom:10px;
}
</style>