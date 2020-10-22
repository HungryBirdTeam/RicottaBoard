import { createInstance } from "./index.js";
const API_BASE_URL = "http://localhost:8080";

const instance = createInstance(API_BASE_URL);


function loginUser(data, success, fail) {
    instance
        .post('/api/auth/login', data)
        .then(success)
        .catch(fail);
}


export { loginUser, };