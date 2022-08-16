<template>
  <div id="register" class="text-center">
    <div class="imagediv">
        <img class="background" src="pexels-janko-ferlic-590493.jpg">
    <form class="form-register" @submit.prevent="register">
      <h2 class="h3 mb-3 font-weight-normal">Create Account</h2>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only">Username: &nbsp;</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <div id="registerpassword">
      <label for="password" class="sr-only">Password: &nbsp;</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      </div>
      <label for="confirmPassword" class="sr-only">Confirm Password: &nbsp;</label>
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <div id="createaccountbtn">
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
      </div>
      <div id="haveanaccountlink">
      <router-link id="links" :to="{ name: 'login' }">Have an account?</router-link>
      </div>
    </form>
    <h1>The Bookworm App!</h1>
    </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        
        // set ADMIN role by default
        this.user.role = 'ROLE_ADMIN';

        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.background{
  width: 100vw;
}

.imagediv{
  
  background-color: rgb(46,55,80);
}

.form-register{
  position:absolute;
  top:30%;
  left: 8%;
  color: white;
  border: solid;
  border-color: white;
  border-width: 1px;
  border-radius: 3px;
  padding: 10px;
  text-decoration: none;
  background-color: rgba(69, 31, 85, 0.5);
}

#links{
  text-decoration: none;
  color:white;
}

h1 {
  position:absolute;
  top: 25%;
  left: 8%;
  color:white;
  padding: 0,0,0,20px;
  font-family: "Pangolin", sans-serif;
}

h2 {
  margin-top:10px;
  margin-bottom: 10px;
  font-size: 2em;
  font-family: "Pangolin", sans-serif;
}

#password {
  margin-left: 58px;
}

#username {
  margin-left: 54px;
}

#createaccountbtn {
  margin-left: 128px;
}

#links {
  margin-left: 128px;
}

</style>
