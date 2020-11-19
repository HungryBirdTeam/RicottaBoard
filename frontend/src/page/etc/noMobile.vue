
<template>
    <div class="noMobile">
        <div class="text">
            <h1>Sorry! :(</h1>
            <p>리코타 보드는 PC 버전에 최적화 되어있습니다.</p>
            <p>최적의 경험을 안겨드리기 위해 현재 모바일 버전은 개발중에 있습니다.</p>
            <p>PC로 접속하여 리코타 보드를 사용해보세요!</p>
        </div>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
    .noMobile {
        width: 100vw;
        height: 100vh;
        margin:auto;
        background-image: url("../../assets/img/EnterBack.jpg");
        /* background: red;; */
    }
    .text {
        margin: 50% 10%;
        color: #F5F5EC;
    }

</style>