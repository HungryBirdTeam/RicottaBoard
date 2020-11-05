
<template>
    <v-dialog  width="350px ">
      <template v-slot:activator="{ on, attrs }">
        
        <button
          class="white--text"
          style="float:right"
          v-bind="attrs"
          v-on="on"
          color="rgb(223, 223, 223)"
          v-if="$store.getters.accessToken == ''"
        >
          LOGIN
        </button>
        <button
          class="white--text"
          style="float:right"
          v-bind="attrs"
          v-on="on"
          color="rgb(223, 223, 223)"
          v-if="$store.getters.accessToken !== ''"
          @click="checkLogin"
        >
          Go to Main
        </button>
      </template>
      <v-card style="width:350px; height:280px">
        <v-card-title>LOGIN</v-card-title>
        <v-card-text style=" background-color:white; height:90px; padding-bottom:0"><Login style="height:120px;padding-bottom:0"/></v-card-text>
        
      </v-card>
    </v-dialog>
</template>
<script>
    import Login from '../../page/user/Login'
    export default {
        components: {
            Login,
        },
        methods: {
          checkLogin() {
            if(this.$store.getters.accessToken !== ''){
              this.$router.push('/main')
            }
          },
        }
    }
   
</script>

<style>

</style>