<template>
    <div class="MoveableBox paper">
        <textarea 
        name=""
        class="notMoveBox paperTitle"
        cols="30" rows="1"
        v-model="postit.title"
        @click.prevent.self
        @keyup.tab="$store.commit('toggleUpdate')"
        placeholder="title here"
        ></textarea>

        <textarea
        name=""
        class="notMoveBox paperContent"
        cols="30" rows="5"
        v-model="postit.contents"
        @keyup.tab="$store.commit('toggleUpdate')"
        placeholder="content here.."
        ></textarea>
    </div>
</template>

<script>
import image from '../../assets/img/postIt.png'
export default {
    data() {
      return {}
    },
    props: {
      postit: Object,
    },
    // mounted() {
    //   this.title = 'title!!'
    //   this.content = postit.contents 
    //   // console.log(postit)
    // },
    methods: {
    },

}
</script>

<style>
.paper{  
  background-image: url('../../assets/img/postIt.png') !important; 
  background-size: 100% 100% !important;
  width: 300px;
  height: 300px;
  position: absolute;
  text-align: center;
  margin: 0 auto;
  font-weight: 100;
  letter-spacing: 1px;
  display: inline-block;
}

.paperTitle{
  font-size: 30px;
  min-height: 50px;
  /* height: 10%; */
  height: auto;
  resize:none;
  /* padding: 20px 20px 1px 20px; */
  padding: 10px 10px 1px 10px;
}
.paperContent{
  height: 90%;
  resize: none;
  /* padding: 20px; */
  padding: 10px;
  overflow: hidden;
}
.MoveableBox {
  padding: 30px;
  position: absolute;
  background: linear-gradient(-135deg,  #FC887C 20px, white 20px);
}
</style>