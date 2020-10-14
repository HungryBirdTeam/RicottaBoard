import axios from "axios";

export default axios.create({
  // baseURL: "http://i3a510.p.ssafy.io/api",
  baseURL: "http://localhost:8080",
  // baseURL: "http://218.146.39.122:8080",
  // headers: {
  //   "Content-type": "application/json",
  // }
});