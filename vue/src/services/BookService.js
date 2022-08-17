import axios from 'axios';


export default {

    list(user) {
        return axios.get(`/books`, user);
    },
    listCompleted(user){
        return axios.get('/currently_reading', user)
    },
    listReadingDetails() {
        return axios.get(`/reading_details`)
    },
    minutesRead(reading) {
        return axios.get('/minutes_read', reading)
    },

    submitReading(readingActivity) {
        return axios.post('/log_reading', readingActivity);

    },

    submitBook(addedBook) {
        return axios.post(`/add_book`, addedBook);
    }
}