<template>
  <div class="users">
    <button class="getProjectsButton" @click="toggleProjects">
      {{ showProjects ? "Ukryj projekty" : "Pokaż projekty" }}
    </button>
    <ul v-if="showProjects">
      <li v-for="project in results" :key="project.id">
        <p>{{ project }}</p>
      </li>
    </ul>
  </div>
</template>

<style>
.getProjectsButton {
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
  name: "ProjectsView",
  data() {
    return {
      results: [],
      showProjects: false,
    };
  },
  methods: {
    toggleProjects() {
      this.showProjects = !this.showProjects;
      this.getProjects();
      // if (!this.results.length) {
      // }
    },
    getProjects() {
      fetch(`${API}/projects`)
        .then((response) => response.json())
        .then((data) => {
          // console.log(data);
          this.results = data.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
