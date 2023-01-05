<template>
  <div class="home">
    <button class="getUsersButton" @click="getUsers">Pokaż użytkowników</button>
    <ul>
      <li v-for="user in results" :key="user.id">
        <p>ID: {{ user.id }}</p>
        <p>Name: {{ user.firstName }}</p>
        <p>Email: {{ user.lastName }}</p>
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
    };
  },
  methods: {
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
  },
};
</script>
