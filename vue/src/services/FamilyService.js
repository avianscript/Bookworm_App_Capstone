import axios from 'axios';


export default {

    list(id) {
        return axios.get(`/family/${id}`);
    },
}