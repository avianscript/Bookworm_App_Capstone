<template>
  <div class="card" v-bind:class="{ selected: isSelected }">
    <img v-on:click="selectBook()" v-if="book.isbn" v-bind:src="'http://covers.openlibrary.org/b/isbn/' + book.isbn + '-M.jpg'" />
    <div v-bind:class="{ 'color-overlay': isSelected }"></div>
    <p class="centered">Minutes Read:</p>
    <form v-show="isSelected" class="centered">
        <!-- <label for="time-read">Minutes Read:</label><br> -->
        
        <input id="submit" v-on:click.prevent="submitReadingInfo()" type="submit"/>
    </form>
  </div>
</template>

<script>
import BookService from '../services/BookService';

export default {
    name: 'book-card',
    props: {
        book: Object,
    },
    data() {
        return {
            url: "/book/" + this.book.isbn,
            readingActivity: {
              username: this.$store.state.user.username, 
              minutes_read: "",
              isbn: this.book.isbn
          }
        }
    },
    methods: {
        selectBook(){
            if(this.$store.state.currentlyReadingSelectedBook == this.book.isbn){
                this.$store.commit('SET_CURRENTLY_READING_SELECTED_BOOK', "")
            } else{
                this.$store.commit('SET_CURRENTLY_READING_SELECTED_BOOK', this.book.isbn)
            }
            
        },
        submitReadingInfo() {
            BookService.submitReading(this.readingActivity).then( response => {
                if (response.status === 201) {
                    this.$router.push('/actioncompleted')
                }
            })
        }
        
    },
    computed: {
        isSelected(){
            return this.$store.state.currentlyReadingSelectedBook == this.book.isbn;
        }
    }
}
</script>

<style scoped>
p {
    color: white;
    font-size: 1.1rem;
    margin: 1px;
}

#time-read {
    width: 5rem;
}

#submit {
    width: 5.5rem;
}

label {
    color: white;
    padding-bottom: 2rem;

}

#time_read {
    width: 6rem
}

.color-overlay {
    /* width: 100%; */
    height: 100%;
    background: #000;
    opacity: .7;
    position: relative;
    top: -101%;
    /* z-index: 1; */
}

.centered {
    position: relative;
    top: -155%;
    left: 0px;
}

.card {
    height: 14rem;
    background-color: #3f2950;
}

.card.selected {
    background-color: rgba(211,211,211,0.1);
    border: 2px solid white;
    border-radius: 1px;
}

.card .book-title {
    font-size: 1.5rem;
}

.card .book-author {
    font-size: 1rem;
}

img {
    height: 98%;
    /* height: 14rem; */
}
</style>