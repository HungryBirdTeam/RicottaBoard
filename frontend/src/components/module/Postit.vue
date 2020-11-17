<template>
    <div class="MoveableBox paper">
        <input 
        name=""
        class="notMoveBox paperTitle"
        maxlength="15"
        v-model="post.title"
        placeholder="title here"
        >

        <textarea
        name=""
        class="notMoveBox paperContent"
        cols="30" rows="5"
        v-model="postit.contents"
        placeholder="content here.."
        ></textarea>
    </div>
</template>

<script>
import image from '../../assets/img/postIt.png'

var postSet;
export default {
    data() {
      return {
        post: Object,
      }
    },
    props: {
      postit: Object,
    },
    watch: {
      'post.title': function() {
        this.changePost();
      },
      'post.contents': function() {
        this.changePost();
      },
      'postit.title': function() {
        this.recvPost();
      },
      'postit.contents': function() {
        this.recvPost();
      },
    },
    methods: {
      changePost() {
        if (postSet) {
          clearTimeout(postSet);
        }
        postSet = setTimeout(() => {
          this.$emit('changePost', this.post);
        }, 500);        
      },
      recvPost() {
        this.post = this.postit;
      }
    },
    created() {
        this.recvPost();      
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
  height: auto;
  padding: 10px 10px 1px 10px;
  overflow-x: hidden;
  width: 100%;
}
.paperContent{
  height: 80%;
  resize: none;
  padding: 10px;
  font-size: 18px;
  overflow: hidden;
  word-wrap: break-word;
  width: 100%;
}
.MoveableBox {
  padding: 30px;
  position: absolute;
  background: linear-gradient(-135deg,  #FC887C 20px, transparent 20px);
}

a :hover{
  text-decoration: none !important;
}
.rCS53a2i7 {
  z-index: 0 !important;
}
</style>