<template>
    <div class="familymembers" v-on:click="onClick()">
        {{curUser.username}} 
        <ul id="flexcontainer" v-if="isVisible">
        <li>Total Minutes Read: {{ReadingActivity.minutes_read}}</li>
        <li>Books Completed: {{ReadingActivity.books_read}}</li>
        <li>Books Reading: {{ReadingActivity.books_reading}}</li>
        </ul>
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

#flexcontainer {
    display: flex;
    justify-content: space-between;
    margin-top: 5px;
}

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