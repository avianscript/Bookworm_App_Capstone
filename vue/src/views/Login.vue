<template>
  <div id="login" class="text-center">
    <div id="imagediv">

    <img class="background" src="pexels-janko-ferlic-590493.jpg">
    <form class="form-signin" @submit.prevent="login">
      <h2 class="h3 mb-3 font-weight-normal">Please Sign In</h2>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      
      <router-link id="links" :to="{ name: 'register' }">Need an account?</router-link>
      <button type="submit">Sign in</button>
    </form>

    <h1>Name of App!</h1>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>

.background{
height: 90vh;
  width: 100vw;
}

#imagediv{
  
  background-color: rgb(46,55,80);
}

.form-signin{
  position:absolute;
  top:20px;
  left: 10%;
  color: white;
  text-decoration: none;
}

#links{
  text-decoration: none;
  color:white;
}

h1 {
  position:absolute;
  top:50%;
  left: 10%;
  color:white;
  padding: 0,0,0,20px;
}


</style>
