import { createInstance } from "./index.js";
const API_BASE_URL = "http://localhost:9004";

const instance = createInstance(API_BASE_URL);

function deleteUser(user, accessToken, success, fail) {
    instance
        .delete(`/api/user/delete?email=${user.email.trim()}&password=${user.password.trim()}`, {
            headers: {
                Authorization: 'Bearer ' + accessToken
            }
        })
        .then(success)
        .catch(fail);
}

function createUser(user, success, fail) {
    instance
        .post('api/auth/register', user)
        .then(success)
        .catch(fail);
}

function emailCheck(email, success, fail) {
    instance
        .get(`/api/auth/checkEmailInUse?email=${email}`)
        .then(success)
        .catch(fail);
}

function resetMyPasswordReq(email, success, fail) {
    instance
        .post('/api/auth/password/resetlink', email)
        .then(success)
        .catch(fail);
}

function resetMyPassword(password, success, fail) {
    instance
        .post('/api/auth/password/reset', password)
        .then(success)
        .catch(fail);
}

export { deleteUser, createUser, emailCheck, resetMyPasswordReq, resetMyPassword };