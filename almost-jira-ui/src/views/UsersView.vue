<template>
  <div class="users">
    <button class="getUsersButton" @click="toggleUsers">
      {{ showUsers ? "Ukryj użytkowników" : "Pokaż użytkowników" }}
    </button>
    <ul v-if="showUsers">
      <li v-for="user in results" :key="user.id">
        <p>ID: {{ user.id }}</p>
        <p>Imię: {{ user.firstName }}</p>
        <p>Nazwisko: {{ user.lastName }}</p>
      </li>
    </ul>
  </div>
  <div class="single-user">
    <input v-model="Id" type="text" placeholder="Podaj ID użytkownika" />
    <button class="getUserButton" @click="getUser(Id)">
      Pokaż użytkownika
    </button>
    <p v-if="user">
      ID: {{ user.id }}<br />
      Imię: {{ user.firstName }}<br />
      Nazwisko: {{ user.lastName }} /
    </p>
  </div>
</template>

<style>
.getUsersButton {
  width: 200px;
  height: 50px;
  color: black;
  background: chartreuse;
  border-color: darkgreen;
  font-size: 18px;
}
.getUserButton {
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
</style>

<script>
const API = "http://localhost:8080";

export default {
  name: "UsersView",
  data() {
    return {
      results: [],
      showUsers: false,
    };
  },
  methods: {
    toggleUsers() {
      this.showUsers = !this.showUsers;
      if (this.showUsers) {
        this.getUsers();
      }
    },
    getUsers() {
      fetch(`${API}/users`)
        .then((response) => response.json())
        .then((data) => {
          this.results = data.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getUser(Id) {
      fetch(`${API}/users/${Id}`)
        .then((response) => response.json())
        .then((data) => {
          this.user = data.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
