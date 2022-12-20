<template>
  <div class="home">
    <button @click="getUsers">Pokaz uzytkonikow</button>
    <ul>
      <li v-for="user in results" :key="user.id">
        <p>{{ user }}</p>
      </li>
    </ul>
  </div>
</template>

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
