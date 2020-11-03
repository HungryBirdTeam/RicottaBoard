import { createInstance } from "./index.js";
// 보드 서버
const API_BASE_URL = "http://localhost:8081/api";

const instance = createInstance(API_BASE_URL);


function findAllChannel(data, config, success, fail) {
    instance
        .post('board/channels', data, config)
        .then(success)
        .catch(fail);
}

function createChannel(data, config, success, fail) {
    instance
        .post('board/channel', data, config)
        .then(success)
        .catch(fail);
}

export { findAllChannel, createChannel};