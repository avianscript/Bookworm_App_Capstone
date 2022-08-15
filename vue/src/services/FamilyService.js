import axios from 'axios';


export default {

    list() {
        return axios.get(`/family_user`);
    },

    registerChild(id) {
        return axios.post(`/register_child`, id)
    },

    registerFamily(id) {
        return axios.post(`/register_family_account`, id)
    },

    addToFamilyAccount(id) {
        return axios.post(`/add_family_member`, id)
    }
}