import axios from "axios";

function createInstance(API_BASE_URL) {
    const instance = axios.create({
        baseURL: API_BASE_URL,
        headers: {
            "Content-Type": "application/json"
        }
    });
    return instance;
}

export { createInstance };