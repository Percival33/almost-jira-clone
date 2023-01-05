<template>
  <div class="projects">
    <button class="getProjectsButton" @click="getProjects">Pokaż projekty</button>
    <ul>
      <li v-for="project in results" :key="project.project_id">
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
    };
  },
  methods: {
    getProjects() {
      fetch(`${API}/projects`)
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
