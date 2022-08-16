<template>
<div id="backgroundimg">
    <img class="homebackground" src="living-room.jpg">
    <div class="home">
    <p>Welcome, {{this.username}}!</p>
    </div>
    
    <div class="nav">
    <p><router-link class="profilelink" v-bind:to="{name: 'profile'}">Profile</router-link> </p>
    <p><router-link class="familylink" :to="{ name: 'family'}">Family</router-link></p>
    <p><router-link class="readinglistlink" :to="{ name: 'readingList' }">Reading List</router-link></p>
    <p>Prizes</p>
    </div>

     <div class="profilewindow">
        <h3>Welcome to your Books!</h3>
        <button id="addbook" v-on:click="setReadingActivitytoTrue()">Add a Book!</button>
        <form v-show="addReadingActivity" >
            <input name="isbn" type="text" placeholder="Enter ISBN" v-model="addedBook.isbn"/>
            <button v-on:click.prevent="submitAddedBook()">Lookup and Add Book</button>
        </form>
         <div class="bookAdded" v-for="book in this.$store.state.bookCompleted" v-bind:key="book.isbn">
            <p>{{ book.book_name + ", " + book.author + ", " + book.isbn }}</p>
        </div>
    </div>

 
</div>
   
</template>

<script>
import BookService from '../services/BookService'
export default {
    
    name: 'reading-list',
  

    data() {
        return {
            username: this.$store.state.user.username,
          addReadingActivity: false,
            addedBook: {
            isbn: ""
        }
        }
},
    methods: {
        setReadingActivitytoTrue() {
            this.addReadingActivity = true;
        },

        submitAddedBook() {
            BookService
            .submitBook(this.addedBook.isbn).then(response => {

                if (response.status === 201) {
                    this.$router.push('/actioncompleted')
                }
        })},
    
    },
    created() {
            BookService.list(this.user).then(response => {
                this.$store.state.bookCompleted = response.data;
            })
        }
}

</script>


<style scoped>

.bookAdded {
    height:20px;
    width: 50%;
    background-color: #22162B;
    position:relative;
    margin: 0 auto;
    color:white;
}

.profilewindow {
    text-align: center;
    position:absolute;
    top:50px;
    left:18%;
    background: rgba(114,78,145,0.7);
    height:70vh;
    width: 70vw;

}
</style>