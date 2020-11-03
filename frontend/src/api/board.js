import { createInstance } from "./index.js";
const API_BASE_URL = "http://localhost:8081";
var channelId = localStorage.getItem("wsboard.channelId");

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

// 공지사항 REST API 
function getAllNotice(isTestPage, accessToken, success, fail){
    var url = `/board/${channelId}/notice`; 
    if(isTestPage) url = "/board/tutorial/earlyBird10TeamTestChannel1/notice";
    instance
        .get(url, {
            headers: { Authorization: "Bearer " + accessToken }
        })
        .then(success)
        .catch(fail);
}

function createNotice(notice, isTestPage, accessToken, success, fail){
    console.log
    var url = `/board/${channelId}/notice`; 
    if(isTestPage) url = "/board/tutorial/earlyBird10TeamTestChannel1/notice";
    instance
        .post(url, notice, {
            headers: { Authorization: "Bearer " + accessToken }
        })
        .then(success)
        .catch(fail);
}


function getNotice(channelId, noticeId, isTestPage, accessToken, success, fail){
    var url = `/board/${channelId}/notice/${noticeId}`; 
    if(isTestPage) url = `/board/tutorial/earlyBird10TeamTestChannel1/notice/${noticeId}`;
    instance
        .get(url, {
            headers: { Authorization: "Bearer " + accessToken }
        })
        .then(success)
        .catch(fail);
}

function updateNotice(notice, channelId, noticeId, isTestPage, accessToken, success, fail){
    var url = `/board/${channelId}/notice/${noticeId}`; 
    if(isTestPage) url = `/board/tutorial/earlyBird10TeamTestChannel1/notice/${noticeId}`;
    instance
        .put(url, notice, {
            headers: { Authorization: "Bearer " + accessToken }
        })
        .then(success)
        .catch(fail);
}

function deleteNotice(noticeId, isTestPage, accessToken, success, fail){
    var url = `/board/${channelId}/notice/${noticeId}`; 
    if(isTestPage) url = `/board/tutorial/earlyBird10TeamTestChannel1/notice/${noticeId}`;
    instance
        .delete(url, {
            headers: { Authorization: "Bearer " + accessToken }
        })
        .then(success)
        .catch(fail);
}
export {API_BASE_URL, initialRecv, getAllNotice, getNotice, deleteNotice, createNotice, updateNotice}