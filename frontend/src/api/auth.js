import { BOARD_BASE_URL, createInstance } from "./index.js";
// 보드서버
const API_BASE_URL = BOARD_BASE_URL;

const instance = createInstance(API_BASE_URL);


function loginUser(data, success, fail) {
    instance
        .post('/api/board/user/login', data)
        .then(success)
        .catch(fail);
}


export { loginUser, };