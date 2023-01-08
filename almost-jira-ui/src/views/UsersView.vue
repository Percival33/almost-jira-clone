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
    <button class="getUserButton" @click="getUser(Id)">
      Pokaż użytkownika
    </button>
    <input v-model="Id" type="text" placeholder="Podaj ID użytkownika" />
    <p v-if="single_user">
      ID: {{ single_user.id }}<br />
      Imię: {{ single_user.firstName }}<br />
      Nazwisko: {{ single_user.lastName }}<br />
      Data dodania: {{ single_user.createdAt }}<br />
      Data ostatniej edycji: {{ single_user.lastModified }}
    </p>
    <ul v-if="single_user">
      <li v-for="project in single_user.projects" :key="project.id">
        <p>Nazwa projektu: project.projectName</p>
      </li>
    </ul>
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
input {
  border-style: solid;
  border-width: 3px;
  border-color: darkgreen;
  padding: 5px;
  background: lightgreen;
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
      single_user: false,
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
          this.single_user = data.data;
        })
        .catch((error) => {
          console.log("siema siema", error);
        });
    },
  },
};
</script>
