<template>
  <div class="card" v-bind:class="{ read: book.read }">
    <img v-if="book.isbn" v-bind:src="'http://covers.openlibrary.org/b/isbn/' + book.isbn + '-M.jpg'" />
    <h2 class="book-title">{{ book.title }}</h2>
    <h1 class="book-author">{{ book.author }}</h1>
  </div>
</template>

<script>
export default {
    name: 'book-card',
    props: {
        book: Object,
    },
    data() {
        return {
            url: "/book/" + this.book.isbn
        }
    },
    methods: {
        // viewBook(){
        //     this.$router.push({ name: 'book-detail', params: { isbn: this.book.isbn } });
        // },
        // setRead(value) {
        //     this.$store.commit('SET_READ_STATUS', {book: this.book, value: value});
        // },
        // addToReadingList(book) {
        //     let addedBook = Object.assign({ read: false }, book);
        //     delete addedBook.bestSeller;
        //     delete addedBook.newRelease;
        //     this.$store.commit('SAVE_BOOK', addedBook);
    }
}
</script>

<style scoped>
.card {
    /* border: 2px solid white; */
    /* border-radius: 10px;
    width: 250px;
    height: 550px;
    margin: 20px; */
    padding:  1rem;
    border-radius: 2rem;
}

.card.read {
    background-color: lightgray;
}

.card .book-title {
    font-size: 1.5rem;
}

.card .book-author {
    font-size: 1rem;
}

img {
    height: 20vh;
}
</style>