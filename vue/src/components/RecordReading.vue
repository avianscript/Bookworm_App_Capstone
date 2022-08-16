<template>
<div>
    <h2>Your Books:</h2>
    <div class="book-container">
        <book-card v-bind:book="book" v-for="book in $store.state.bookCompleted" v-bind:key="book.isbn" />
    </div>
</div>
  
</template>

<script>
import BookCard from '@/components/BookCard.vue';

export default {
    name: 'reading-list',
    components: {
        BookCard
    }
}
</script>

<style scoped>
h2 {
    background-color: #3a2649;
    color:white;
    font-size: 2.2rem;
    margin-bottom: 0;
    padding-top: 10px;
    padding-bottom: 10px;
}
.book-container {
    background-color: #22162B;
    display: grid;
    gap: 1rem;
    grid-auto-flow: column;

    overflow-x: auto;
    overscroll-behavior-inline: contain;
    /* border: 10px solid #57386e;
    border-radius: 1px; */
    height: 20rem;
}
</style>