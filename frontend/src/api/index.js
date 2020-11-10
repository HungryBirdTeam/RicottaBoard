import axios from "axios";

const BOARD_BASE_URL = "http://localhost:8081";
const AUTH_BASE_URL = "http://localhost:9004";
const CHAT_BASE_URL = "https://k3a204.p.ssafy.io/api/chat";
// const CHAT_BASE_URL = "http://localhost:3030";

function createInstance(API_BASE_URL) {
    const instance = axios.create({
        baseURL: API_BASE_URL,
        headers: {
            "Content-Type": "application/json"
        }
    });
    return instance;
}

export { BOARD_BASE_URL, AUTH_BASE_URL, CHAT_BASE_URL, createInstance };