<template>
    <div class="temp_body">
        <div class="headline">
            Welcome! ${userName}
        </div>



        <div >
            <button>Check!</button>

            리코타 보드에 가입해주셔서 감사합니다!
            <a href="${baseUrl}/api/auth/verifyUser?email='${userEmail}'&token='
            ${userEmailVerifyKey}'">계정 인증</a>

        </div>


    </div>



</template>

<style>

</style>