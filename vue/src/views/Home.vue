<template>
  <div>
    <img class="homebackground" src="living-room.jpg">
  <div class="home">
    <h1>Home</h1>
    <p>Welcome, user!</p>
  </div>
  <div class="nav">
    <p>Profile</p>
    <p>Family</p>
    <p>Reading List</p>
    <p>Prizes</p>
  </div>
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>

<style>
  .homebackground{
height: 90vh;
  width: 100vw;
  }

  h1 {
  margin: 10px;
  margin-left: 0px;
  
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

.nav {
  margin-top: 20px;
  position: absolute;
  top: 150px;
  left: 10%;
  color: #F8C630;
  font-family: "Mouse Memoirs", sans-serif;

}



</style>