import axios from "axios";

// axios for chatting log

export default axios.create({
    baseURL: "http://i3a510.p.ssafy.io:9004",
    //baseURL: "http://localhost:3000/test",
    headers:{
        "Content-type": "application/json"
    }
});