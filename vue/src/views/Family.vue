<template>
  <div>
    <img class="homebackground" src="living-room.jpg" />
    <div class="home">
      <p>Welcome, {{ this.username }}!</p>
    </div>
    <div class="nav">
      <p>
        <router-link class="profilelink" v-bind:to="{ name: 'profile' }"
          >Profile</router-link
        >
      </p>
      <router-link class="familylink" :to="{ name: 'family' }"
        >Family</router-link
      >
      <p>
        <router-link class="readinglistlink" :to="{ name: 'readingList' }"
          >Reading List</router-link
        >
      </p>
      <p>Prizes</p>
    </div>
    <div class="familywindow">
      <h3 v-show="familyName == ''">Tell us about your family...</h3>
      <h3 v-show="familyName != ''">The {{ familyName }} family</h3>
      <div class="familymembers">
        <p
          v-for="member in this.$store.state.familyMembers"
          v-bind:key="member.user_id"
        >
          {{ member.username }}
        </p>
      </div>
      <div v-if="familyName == ''">
        <p>Register your family!</p>
        <input
          type="text"
          placeholder="Family Name"
          v-model="familyName"
          v-show="disabled"
        />
        <input
          type="submit"
          v-on:click.prevent="registerFamily(), (disabled = false)"
          v-show="disabled"
        />
      </div>

      <div>
        <p>Add a child account</p>
        <form>
          <input
            type="text"
            placeholder="Child Username"
            v-model="child.username"
          />
          <br />
          <input type="text" placeholder="Password" v-model="child.password" />
          <br />
          <input
            type="text"
            placeholder="Confirm Password"
            v-model="child.confirmPassword"
          />
          <input type="submit" v-on:click.prevent="registerChild()" />
        </form>
      </div>

      <div>
        <p>Add a family member</p>
        <form>
          <input
            type="text"
            placeholder="ID of Family Member"
            v-model="newFamilyMember.username"
          />
          <!-- <input type="text" placeholder="ID of Family" v-model="newFamilyMember.family_id"> -->
          <input type="submit" v-on:click.prevent="addFamilyMember()" />
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import FamilyService from "../services/FamilyService";

export default {
  name: "the-family",
  data() {
    return {
      username: this.$store.state.user.username,
      familyName: "",
      child: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "ROLE_USER",
      },
      disabled: true,
      newFamilyMember: {
        username: "",
      },
    };
  },
  props: ["id"],

  created() {
    FamilyService.getFamilyName().then((response) => {
      this.familyName = response.data;
      if (this.familyName) {
        FamilyService.list(this.user).then((response) => {
          this.$store.state.familyMembers = response.data;
        });
      }
    });
  },

  methods: {
    registerChild() {
      FamilyService.registerChild(this.child).then((response) => {
        if (response.status === 201) {
          this.$router.push("/actioncompleted");
        }
      });
    },

    registerFamily() {
      FamilyService.registerFamily(this.familyName).then((response) => {
        if (response.status === 201) {
          this.$router.push("/actioncompleted");
        }
      });
    },

    addFamilyMember() {
      FamilyService.addToFamilyAccount(this.newFamilyMember).then(
        (response) => {
          if (response.status === 201) {
            this.$router.push("/actioncompleted");
          }
        }
      );
    },
  },
};
</script>

<style>
.familywindow {
  text-align: center;
  position: absolute;
  top: 50px;
  left: 18%;
  background: rgba(114, 78, 145, 0.7);
  height: 70vh;
  width: 70vw;
}

h3 {
  font-size: 30px;
}

.familymembers {
  height: 50%;
  width: 50%;
  background-color: #22162b;
  position: relative;
  margin: 0 auto;
  color: white;
}

.familylink {
  color: #f8c630;
}
</style>