<template>
    <div class="familymembers" v-on:click="onClick()">
        {{curUser.username}} 
        <div v-if="isVisible">
        Total Minutes Read: {{ReadingActivity.minutes_read}}
        Books Completed: {{ReadingActivity.books_read}}
        Books Reading: {{ReadingActivity.books_reading}}
        </div>
    </div>
</template>

<script>
import FamilyService from "../services/FamilyService";

export default {
  name: "reading-info",
  props: {
      curUser: Object
  },
  data() {
        return {
            isVisible: false,
            ReadingActivity: {
                minutes_read: "",
                books_read: "",
                books_reading: ""
            }
        }
    },
   methods: {
       onClick() {
           FamilyService.getReadingActivity(this.curUser.username).then((response) => {
               this.ReadingActivity = response.data;
               if(!this.isVisible){
                   this.isVisible = true;
               } else {
                   this.isVisible = false;
               }
           })
       }
   }
}
</script>

<style scoped>

.familymembers {
    height:3rem;
    width: 50%;
    background-color: #22162B;
    position:relative;
    margin: 0 auto;
    margin-bottom: 2rem;
    color:white;
    font-size: 20px;
    font-family: "Mouse Memoirs", sans-serif;
}

</style>