<template>
    <div class="temp_body">
        <div class="headline">
            Welcome! ${userName}
        </div>



        <div >
            <button>Check!</button>

            리코타 보드에 가입해주셔서 감사합니다!
            <a href="${baseUrl}/api/auth/verifyUser?email=${userEmail}&authKey=${userEmailVerifyKey}">계정 인증</a>

            href="${baseUrl}/api/auth/verifyUser?email=${userEmail}&authKey=${userEmailVerifyKey}"
<br>
            href="${baseUrl}:9004/api/auth/verifyUser?email=${userEmail}&authKey=${userEmailVerifyKey}"

        </div>


    </div>



</template>

<style>

</style>