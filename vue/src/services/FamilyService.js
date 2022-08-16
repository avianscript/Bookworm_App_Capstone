import axios from 'axios';


export default {

    list(username) {
        return axios.get(`/family_members`, username);
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