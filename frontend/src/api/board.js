import { BOARD_BASE_URL, createInstance } from "./index.js";
// 보드서버
const API_BASE_URL = BOARD_BASE_URL;
var channelId = localStorage.getItem("wsboard.channelId");

const instance = createInstance(API_BASE_URL);

function initialRecv(isTestPage, accessToken, success, fail) {
  var url = `/${channelId}`;
  if (isTestPage) {url = "/tutorial/earlyBird10TeamTestChannel1";}
  instance
    .get(url, {
      headers: { Authorization: "Bearer " + accessToken }
    })
    .then(success)
    .catch(fail);
}
// History REST API
function getHistories(success, fail) {
  var url = `/${channelId}/history`;
  instance
    .get(url)
    .then(success)
    .catch(fail);
}


// 공지사항 REST API 
function getAllNotice(isTestPage, accessToken, success, fail) {
  var url = `/${channelId}/notice`;
  if (isTestPage) {url = "/tutorial/earlyBird10TeamTestChannel1/notice";}
  instance
    .get(url, {
      headers: { Authorization: "Bearer " + accessToken }
    })
    .then(success)
    .catch(fail);
}

function createNotice(notice, isTestPage, accessToken, success, fail) {
  console.log
  var url = `/${channelId}/notice`;
  if (isTestPage) {url = "/tutorial/earlyBird10TeamTestChannel1/notice";}
  instance
    .post(url, notice, {
      headers: { Authorization: "Bearer " + accessToken }
    })
    .then(success)
    .catch(fail);
}


function getNotice(channelId, noticeId, isTestPage, accessToken, success, fail) {
  var url = `/${channelId}/notice/${noticeId}`;
  if (isTestPage) {url = `/tutorial/earlyBird10TeamTestChannel1/notice/${noticeId}`;}
  instance
    .get(url, {
      headers: { Authorization: "Bearer " + accessToken }
    })
    .then(success)
    .catch(fail);
}

function updateNotice(notice, channelId, noticeId, isTestPage, accessToken, success, fail) {
  var url = `/${channelId}/notice/${noticeId}`;
  if (isTestPage) {url = `/tutorial/earlyBird10TeamTestChannel1/notice/${noticeId}`;}
  instance
    .put(url, notice, {
      headers: { Authorization: "Bearer " + accessToken }
    })
    .then(success)
    .catch(fail);
}

function deleteNotice(noticeId, isTestPage, accessToken, success, fail) {
  var url = `/${channelId}/notice/${noticeId}`;
  if (isTestPage) {url = `/tutorial/earlyBird10TeamTestChannel1/notice/${noticeId}`;}
  instance
    .delete(url, {
      headers: { Authorization: "Bearer " + accessToken }
    })
    .then(success)
    .catch(fail);
}
export { API_BASE_URL, initialRecv, getHistories, getAllNotice, getNotice, deleteNotice, createNotice, updateNotice }