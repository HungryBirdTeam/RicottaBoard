<template>
    <div class="user" id="passwordFind">
        <div class="myBox">
            <div class="middle">
                <div class="form-wrap" v-if="this.$store.getters.status == ''">
                    <h2>비밀번호 찾기</h2>
                    <div class="info-wrap">
                        <label for=""></label>
                        <input type="text" id="email" v-model="userEmail"
                        placeholder="이메일을 입력하세요.">
                    </div>

            
                    <div class="info-wrap">
                        <button v-on:click="findMyPassword">비밀번호 재설정</button>
                    </div>
                </div>
                <div class="form-wrap" v-else>
                    {{this.$store.getters.status}}
                </div>
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
        methods:{
            findMyPassword(){
                this.$store.dispatch(constants.METHODS.RESETMYPASSWORDREQ, this.userEmail);
            }
        },
        data: () => {
            return {
                userEmail:"",
                constants
            }
        }
    }
</script>

<style>

</style>