import axios from 'axios';


export default {

    list(user) {
        return axios.get(`/books`, user);
    },

    listReadingDetails() {
        return axios.get(`/reading_details`)
    },

    submitReading(readingActivity) {
        return axios.post('/log_reading', readingActivity);

    },

    submitBook(addedBook) {
        return axios.post(`/add_book`, addedBook);
    }
}