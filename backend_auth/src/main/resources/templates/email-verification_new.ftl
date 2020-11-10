<div class="temp_body">


        <div class="headlineContent">
            <h1>Recota board</h1>
        </div>

        <div class="bodyContent">


            <h2>Welcome! ${userName} </h2>

            <div class="mainText">
                <p>안녕하세요. 리코타 보드입니다.</p>
                <p>리코타 보드에 가입해주셔서 감사합니다.</p>
                <p>이제 한단계만 남았습니다!</p>
                <p>회원가입을 완료하기 위해 아래의 Confirm 버튼을 눌러 계정을 활성화 시켜주세요.</p>
                <p>회원님이 가입한 것이 아니라면 해당 이메일은 무시하셔도 좋습니다.</p>
            </div>

            <div class="buttonHere">
                <a class="button__a"
                   href="${userEmailTokenVerificationLink}" target="_blank" rel="noopener">
                    Confirm email</a>
            </div>

            <div class="andLast">
                <p style="font-size:18px">자세한 내용은 리코타 보드 홈페이지를 참고하세요.</p>
                <p style="text-align: right; font-size:18px;">- Hungry Bird -</p>
            </div>

        </div>


        <div class="footer">
            <p>© 2020 Recota Board</p>
            <p>508, Eonju-ro, Gangnam-gu, Seoul, 06152 - Republic of Korea</p>
        </div>
</div>

<style type="text/css">
    .button__a {

        border-radius: 4px;
        color: firebrick;
        display: block;
        font-family: sans-serif;
        font-size: 18px;
        font-weight: bold;
        line-height: 1.1;
        padding: 14px 18px;
        text-decoration: none;
        text-transform: none;
        border: 0;

    }

    .headlineContent{
        background-color: #0D875C;
        /* padding: 1vh; */
        border-radius: 1%;
        /* height: 10vh; */
        padding: 1px 5%;
        color: #F5F5EC;
    }



    .bodyContent{
        background-color: #F5F5EC;
        padding: 5% 5%;
        margin: 3% 5%;
    }

    .bodyContent > h2 {
        /* color: red; */
        margin: 0% 0% 10% 0%;
    }

    .mainText {
        font-size: 15px;
        margin-bottom: 10%;
    }

    .buttonHere {
        margin-bottom: 10%;
    }

    .temp_body{
        font-weight: 200;
        /* padding: 5%; */
        font-family: "돋움";
        background-color: rgb(206, 206, 206);
        font-size: 20px;
    }


    .footer{
        color:#2b3549;
        text-align: center;
        /* font-style: ; */
        padding: 5% 0%;
    }


    .footer p {
        margin: 0;
        font-size: 15px;
        color: rgb(36, 36, 36);
    }
</style>