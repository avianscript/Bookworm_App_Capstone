<template>
  <div class="book-container">
    <book-card v-bind:book="book" v-for="book in $store.state.bookCompleted" v-bind:key="book.isbn" />
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

<style>
.book-container {
    /* display:flex;
    justify-content: space-evenly;
    flex-wrap: wrap; */
    display: grid;
    gap: 1rem;
    grid-auto-flow: column;

    overflow-x: auto;
    overscroll-behavior-inline: contain;
}
</style>