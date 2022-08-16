<template>
<div id="backgroundimg">
    <img class="homebackground" src="living-room.jpg">
    <div class="home">
    <p>Welcome, user!</p>
    </div>
    
    <div class="nav">
    <p><router-link class="profilelink" v-bind:to="{name: 'profile'}">Profile</router-link> </p>
    <router-link class="familylink" :to="{ name: 'family'}">Family</router-link>
    <p><router-link class="readinglistlink" :to="{ name: 'readingList' }">Reading List</router-link></p>
    <p>Prizes</p>
    </div>

     <div class="profilewindow">
        <!-- <h3>Welcome to your profile!</h3> -->
        <!-- <button id="recordreading" v-on:click="setReadingActivitytoTrue()">Add Reading Activity</button> -->
        <form v-show="addReadingActivity" >
            <input name="userName" type="text" v-model="readingActivity.username"/>
            <input name="minutes_read" type="number" v-model="readingActivity.minutes_read" />
            <input name="isbn" type="text" v-model="readingActivity.isbn"/>
            <button type="submit" v-on:click.prevent="submitReadingActivity()">Submit Reading Activity</button>
        </form>
             <record-reading/>
    </div>

 
</div>
   
</template>

<script>
import BookService from '../services/BookService';
import RecordReading from '@/components/RecordReading.vue';
export default {
  components: { RecordReading },
    name: 'the-profile',
  

    data() {
        return {
          addReadingActivity: false,
          readingActivity: {
              username: "", 
              minutes_read: "",
              isbn: ""
          }
    }
},
    methods: {
        setReadingActivitytoTrue() {
            this.addReadingActivity = true;
        },

        submitReadingActivity() {
            BookService
            .submitReading(this.readingActivity).then( response => {
                if (response.status === 201) {
                    this.$router.push('/actioncompleted')
                }
            })
        }
    },
    props: ["user"],
    
    created() {
            BookService.list(this.user).then(response => {
                this.$store.state.bookCompleted = response.data;
            })
        }
    }
</script>


<style scoped>

.bookscompleted {
    height:50%;
    width: 70%;
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
    background: rgba(114,78,145,.95);
    /* background: #22162B; */
    height:88vh;
    width: 70vw;
}
</style>