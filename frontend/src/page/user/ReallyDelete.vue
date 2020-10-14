<template class="bg-light">
  <div class="container">
    <div class="form-wrap" v-if="this.$store.getters.status == ''">
      <div class="py-5 text-center" style="margin-bottom:20%;">
        <h2>정말로 계정을 삭제하시겠습니까??</h2>
      </div>

      <div class="col-md-8 order-md-1 container" style="margin-right: auto; margin-left: auto; height: 50vh">
        <div class="mb-5">
          <label for>탈퇴를 원하신다면 비밀번호를 입력해주세요</label>
          <input
            type="password"
            class="form-control"
            id="password"
            v-model="password"
            placeholder="확인용 비밀번호를 입력해주세요."
          />
        </div>
        <br>
        <div class="mb-5">
          <label for>비밀번호 확인</label>
          <input
            type="password"
            class="form-control"
            id="passwordConfirm"
            v-model="passwordConfirm"
            placeholder="비밀번호를 다시 입력해주세요."
          />
        </div>
        <br>
        <br>
        <div class="btn btn-danger btn-lg btn-block">
          <button v-on:click="deleteMyAccount">계정 탈퇴</button>
        </div>
      </div>
    </div>
    <div class="form-wrap" v-else>{{this.$store.getters.status}}</div>
  </div>
</template>

<script>
import "../../assets/css/user.scss";
import constants from "../../lib/constants";
export default {
  data: () => {
    return {
      password: "",
      passwordConfirm: "",
    };
  },
  methods: {
    deleteMyAccount() {
      const token = this.$route.query.token;

      if (this.password == "") {
        return;
      }
      if (this.password != this.passwordConfirm) {
        return;
      } else {
        const data = {
          email: this.$store.getters.userData.email,
          password: this.password,
        };
        console.log(data);
        this.$store.dispatch(constants.METHODS.DELETE_USER, data);
      }
    },
  },
};
</script>

<style>
.container {
  max-width: 960px;
}
</style>