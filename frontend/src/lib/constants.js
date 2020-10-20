export default {
    TITLE: 'SSAFY 개발블로그',
    baseUrl: 'http://ec2-54-180-116-157.ap-northeast-2.compute.amazonaws.com:8888',
    LS_KEY: {
        USER_TOKEN: 'userToken',
    },
    ERROR: { 
        FRONT_ERROR: 'FE00' 
    },
    URL_TYPE: {
        USER: {
            LOGIN: 'login',
            JOIN: 'join', 
            JOINDONE: 'joinDone',
            CONFIRMDONE: 'confirmDone',
            MYPAGE: 'myPage',
            PASSWORDFIND: 'passwordFind',
            PASSWORDRESET: 'passwordReset',

            DELETEUSER: 'deleteUser',
        },
        POST: {
            ENTER : "enter",
            MAIN : "main",
        },
    },
    METHODS:{
        // 유저 관련 메소드
        DELETE_USER: 'deleteUser',
        UPDATE_USER: 'updateUser',
        LOGIN_USER:  'loginUser',
        LOGOUT_USER: 'logoutUser',
        CREATE_USER: 'createUser',
        GET_USER: 'getUser',
        ERROR:'error',

        // 이메일 중복체크 메소드
        EMAILCHECK: 'emailCheck',
        
        // 비밀번호 재설정 메소드
        RESETMYPASSWORD: 'resetMyPassword',
        // 비밀번호 재설정 요청 메소드
        RESETMYPASSWORDREQ: 'resetMyPasswordReq',
    }
}

