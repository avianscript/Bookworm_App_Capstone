import axios from 'axios';


export default {

    list(user) {
        return axios.get(`/books`, user);
    }
}