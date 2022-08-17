<template>
<div>
    <img class="homebackground" src="living-room.jpg">
        <div class="home">
            <h1>Home</h1>
            <p>Welcome, {{username}}!</p>
        </div>
        <div class="nav">
            <p><router-link class="profilelink" v-bind:to="{name: 'profile'}">Profile</router-link> </p>
            <router-link class="familylink" :to="{ name: 'family'}">Family</router-link>
            <p><router-link class="readinglistlink" :to="{ name: 'readingList' }">Reading List</router-link></p>
            <p><router-link class="prizeslink" :to="{ name: 'prizes' }">Prizes</router-link></p>
        </div>
         <div class="prizewindow">
        </div>
        <div class="familymembers">
        </div>
</div>
</template>   

<script>   
    export default {   
        data() {
            return {
            username: this.$store.state.user.username,
             }
        }
    };
</script>

<style scoped>
    .homebackground {
        width: 90vw;
        height: 100vh;
        padding-left: 4%;
    } 

    .home {
  position:absolute;
  top:20px;
  left: 10%;
  color: #724E91;
  text-decoration: none;
  font-family: "Mouse Memoirs", sans-serif;
  font-size: 150%;
}

.familymembers {
  height: 50%;
  width: 50%;
  background-color: #22162b;
  position: relative;
  margin: 0 auto;
  color: white;
}

.prizewindow {
  text-align: center;
  position: absolute;
  top: 50px;
  left: 18%;
  background: rgba(114, 78, 145, 0.7);
  height: 70vh;
  width: 70vw;
}

</style>