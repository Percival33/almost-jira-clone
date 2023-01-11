<template>
  <div class="loginView">
    <div v-if="!store.state.loggedIn" class="loginstuff">
      <button class="loginButton" @click="loginFunction(login, password)">
        Zaloguj się
      </button>
      <input v-model="login" type="text" placeholder="Login" />
      <input v-model="password" type="password" placeholder="Hasło" />
    </div>
    <div v-if="store.state.loggedIn" class="loginstuff">
      <button class="logoutButton" @click="logoutFunction()">
        Wyloguj się
      </button>
    </div>
    <div>
      <p v-if="msg">{{ this.msg }}</p>
    </div>
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
              this.msg = "Logowanie się powiodło";
              store.commit("setLoggedIn", true);
            } else {
              this.msg = "Logowanie nieudane";
              store.commit("setLoggedIn", false);
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    logoutFunction() {
      store.commit("setLoggedIn", false);
      this.msg = "Zostałeś wylogowany";
    },
  },
};
</script>

<style>
.loginButton {
  width: 200px;
  height: 50px;
  color: black;
  background: chartreuse;
  border-color: darkgreen;
  font-size: 18px;
}
.loginView {
  display: inline-flex;
  flex-direction: column;
  align-items: flex-start;
}
.logoutButton {
  width: 200px;
  height: 50px;
  color: black;
  background: chartreuse;
  border-color: darkgreen;
  font-size: 18px;
}
ul {
  border-style: solid;
  border-width: 3px;
  border-color: darkgreen;
  padding: 5px;
  background: lightgreen;
}
li {
  color: black;
  list-style: none;
  padding: 5px;
  border-bottom-color: black;
  border-bottom-width: 3px;
  border-bottom-style: solid;
}
input {
  border-style: solid;
  border-width: 3px;
  border-color: darkgreen;
  padding: 5px;
  background: lightgreen;
}
</style>
