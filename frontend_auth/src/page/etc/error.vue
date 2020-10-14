<template>
    <div class="etc" id="error">
        <h1>ERROR!</h1>
        <p>요청 오류 페이지!</p>
        <p>{{this.$store.state.errorcode}}</p>
    </div>
</template>

<script>
    import '../../assets/css/user.scss';
    import constants from '../../lib/constants';
    export default {
        
        created(){
            
        },
        data: () =>{
            return{
                
            }
        }
    }
</script>
