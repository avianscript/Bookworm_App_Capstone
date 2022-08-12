import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:9000"
// });

export default {

    list(id) {
        return axios.get(`/family/${id}`);
    },
}