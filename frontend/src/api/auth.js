import { AUTH_BASE_URL, createInstance } from "./index.js";
// 보드서버
const API_BASE_URL = AUTH_BASE_URL;

const instance = createInstance(API_BASE_URL);


function loginUser(data, success, fail) {
    instance
        .post('/user/login', data)
        .then(success)
        .catch(fail);
}

function inviteUser(data, success, fail){
    instance
        .post('/invite', data)
        .then(success)
        .catch(fail);
        
}

export { loginUser, inviteUser};