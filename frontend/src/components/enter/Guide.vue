<template>
    <div class="main">
        <v-carousel style="width:100%; height:100% " hide-delimiters>
          <v-carousel-item v-for="(item,i) in items" :key="i" :src="item.src"></v-carousel-item>
        </v-carousel>
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
        }
    }
}
</script>

<style scoped>
.main {
    width:100%;
    margin-top: auto;
    margin-bottom:auto;
    padding:20vh;
    padding:10vw;
    background: #f5f5ec;
}
</style>