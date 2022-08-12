<template>
    <div>
        <img class="homebackground" src="living-room.jpg">
    <div class="home">
    
    <p>Welcome, user!</p>
    </div>
    <div class="nav">
    <p><router-link class="profilelink" v-bind:to="{name: 'profile'}">Profile</router-link> </p>
    <router-link class="familylink" :to="{ name: 'family'}">Family</router-link>
    <p>Reading List</p>
    <p>Prizes</p>
    </div>
        <div class="familywindow">
        <h3>The {{ familyName }} family</h3>
        <div class="familymembers">
            <p>{{this.$store.state.familyMembers}}</p>
        </div>
        <div>
            <p>Add a child account</p>
            <form>
                <input type="text" placeholder="Child Username">
                <br/>
                <input type="text" placeholder="Password">
                <br/>
                <input type="submit">
            </form>
        </div>
        </div>
    </div>
</template>

<script>
import FamilyService from '../services/FamilyService';

export default {
    name: 'the-family',
    data() {
        return {
        familyName: "Johnson"
    }
},
    props: ["id"],
    
    created() {
            FamilyService.list(this.id).then(response => {
                this.$store.state.familyMembers = response.data;
            })
        }
    }

</script>

<style>

.familywindow {
    text-align: center;
    position:absolute;
    top:50px;
    left:18%;
    background: rgba(114,78,145,0.7);
    height:70vh;
    width: 70vw;
}

h3 {
    font-size: 30px;
}

.familymembers {
    height:50%;
    width: 50%;
    background-color: #22162B;
    position:relative;
    margin: 0 auto;
    color:white;
}

.familylink {
    color: #F8C630;
}

</style>