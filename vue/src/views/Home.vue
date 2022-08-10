<template>
  <div>
    <img class="homebackground" src="living-room.jpg">
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
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

.home {
  position:absolute;
  top:20px;
  left: 10%;
  color: #724E91;
  text-decoration: none;
  font-family: "Mouse Memoirs", sans-serif;
}



</style>