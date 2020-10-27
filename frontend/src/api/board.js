import { createInstance } from "./index.js";
const API_BASE_URL = "http://localhost:8081";

const instance = createInstance(API_BASE_URL);

function initialRecv(channelId, isTestPage, accessToken, success, fail){
    var url = `/board/${channelId}`; 
    if(isTestPage) url = "/board/tutorial/earlyBird10TeamTestChannel1";
    instance
        .get(url, {
            headers: { Authorization: "Bearer " + accessToken }
        })
        .then(success)
        .catch(fail);
}

export {API_BASE_URL, initialRecv}