<template>
  <div class="loginView">
    <from v-if="!store.state.loggedIn" class="loginstuff">
      <input v-model="login" type="text" placeholder="Login" />
      <input v-model="password" type="password" placeholder="Hasło" />
      <button class="button" @click="loginFunction(login, password)">
        Zaloguj się
      </button>
    </from>
    <div v-if="store.state.loggedIn" class="loginstuff">
      <button class="button" @click="logoutFunction()">Wyloguj się</button>
    </div>
    <article class="alert" v-if="msg">
      {{ this.msg }}
      <span
        class="closebtn"
        onclick="this.parentElement.style.display='none';this.msg=undefined;"
      >
        &times;
      </span>
    </article>
  </div>
</template>

<script>
import store from "../store.js";
const API = "http://localhost:8080";

export default {
  name: "LoginView",
  data() {
    return {
      results: [],
      showUsers: false,
      singleUser: false,
      msg: "",
      store,
    };
  },
  methods: {
    loginFunction(login, password) {
      if (login == null || password == null) {
        this.msg = "Aby się zalogować, podaj wartość dla każdego pola";
      } else {
        fetch(`${API}/users?login=${login}&password=${password}`, {
          method: "Put",
        })
          .then((response) => response.json())
          .then((data) => {
            this.msg = "";
            if (data.message === "success") {
              this.msg = "Zalogowano!";
              store.commit("setLoggedIn", true);
              store.commit("setUserId", data.data.id);
            } else {
              this.msg = "Logowanie nieudane";
              store.commit("setLoggedIn", false);
              store.commit("setUserId", "");
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    logoutFunction() {
      store.commit("setLoggedIn", false);
      store.commit("setUserId", "");
      this.msg = "Zostałeś wylogowany";
    },
  },
};
</script>

<style>
.button {
  color: black;
  background: chartreuse;
  border-color: darkgreen;
}
.list {
  list-style-type: none;
  margin: 0;
  padding: 0;
  /* border-bottom: 0; */
}
.alert {
  background-color: #2196f3;
}
</style>
