<template>
    <div class="box3 d-flex black--text" style="width: 100vw; height: 100vh;">
        <div
            class="white--text"
            style="width:40%; padding:10vw; padding-top: 15vh; background:rgb(33,33,33); "
        >
            <h2>TEAM<br />Hungry Bird</h2>
        </div>
        <div style="width:60%; margin-left:10vw; padding:60px; padding-top:15vh;">
            열정적인 리코타보드!
            <p />
            <h4 style=" font-size:30px;">리코타보드는 열정적인 개발자들과 함께 하고 있습니다</h4>
            <v-row>
            <v-col v-for="(slide, i) in slides" :key="i" cols="6" md="4">
                <v-hover v-slot:default="{ hover }">
                <v-card>
                    <v-img
                    :src="slide.src"
                    class="white--text align-end"
                    gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                    >
                    <v-expand-transition>
                        <div
                        v-if="hover"
                        flex
                        class="transition-fast-in-fast-out text-center black white--text darken-3 v-card--reveal"
                        style="font-size:15px; padding:10px ;height: 100%; align-items: center; bottom: 0; justify-content: center; opacity:.5; position: absolute; width: 100%;"
                        >
                        <h2>{{slide.name}}</h2>
                        <br />
                        <br />
                        <p>{{slide.text}}</p>
                        </div>
                    </v-expand-transition>
                    </v-img>
                </v-card>
                </v-hover>
            </v-col>
            </v-row>
        </div>
    </div>
</template>

<script>
import img1 from "../../assets/img/developer1.jpg";
import img2 from "../../assets/img/developer2.jpg";
import img3 from "../../assets/img/developer3.jpg";
import img4 from "../../assets/img/developer4.jpg";
import img5 from "../../assets/img/developer5.jpg";

export default {
    data() {
        return {
            slides: [
      {
        name: "Choi Munkyung",
        src: img1,
        text: "1일 1즐거움~",
      },
      {
        name: "Jeong Yongwoo",
        src: img2,
        text:
          "비상벨? 콘솔에 불났을 때 울리는 그건가? 시켜줘.. 얼리버드 명예소방관..",
      },
      {
        name: "Lee jeonghun",
        src: img3,
        text: "문구 생각해보자 이미지도 바꾸고",
      },
      {
        name: "Kim Kanghyeon",
        src: img4,
        text:
          "배포 그만해.. 그게 뭔데...... 배포 그만하라구.. 그거 어떻게 하는건데...",
      },
      {
        name: "Jeong Jingueon",
        src: img5,
        text: "생각해보자 이미지도 준비하고",
      },
    ],
        }
    }
}
</script>

<style scoped>

</style>