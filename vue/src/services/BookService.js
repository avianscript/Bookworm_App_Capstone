import axios from 'axios';


export default {

    list(user) {
        return axios.get(`${user}/books`);
    }
}