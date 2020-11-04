import { BOARD_BASE_URL, createInstance } from "./index.js";
// 보드 서버
const API_BASE_URL = BOARD_BASE_URL;

const instance = createInstance(API_BASE_URL);


function findAllChannel(data, config, success, fail) {
    instance
        .post('api/board/channels', data, config)
        .then(success)
        .catch(fail);
}

function createChannel(data, config, success, fail) {
    instance
        .post('api/board/channel', data, config)
        .then(success)
        .catch(fail);
}

export { findAllChannel, createChannel};