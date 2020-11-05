<template>
  <div class="user" id="ConfirmDone">
        <div class="wrapC table">
            <div class="middle">
                <h1>회원 인증이 완료되었습니다.</h1>

                <h3>환영합니다!</h3>
                <router-link v-bind:to="{name:constants.URL_TYPE.POST.ENTER}" class="login-btn">
                돌아가기 
                </router-link>  
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
        data: () => {
            return {
                constants
            }
        }

    }
</script>

<style>

</style>