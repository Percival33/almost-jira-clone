<template>
  <div class="projectsView">
    <div class="projects">
      <button class="getProjectsButton" @click="toggleProjects">
        {{ showProjects ? "Ukryj projekty" : "Pokaż projekty" }}
      </button>
      <ul v-if="showProjects">
        <li v-for="project in results" :key="project.id">
          <p>ID: {{ project.id }}</p>
          <p>Nazwa: {{ project.projectName }}</p>
          <p>Właściciel: {{ project.overseerId }}</p>
          <ul v-if="!project.tasks.isEmpty">
            <li v-for="(task, index) in project.tasks" :key="index">
              <p>Zadanie {{ index + 1 }}: {{ task }}</p>
            </li>
          </ul>
        </li>
      </ul>
    </div>
    <div class="singleProject">
      <button class="getProjectButton" @click="getProject(Id)">
        Pokaż projekt
      </button>
      <input v-model="Id" type="text" placeholder="Podaj id projektu" />
      <p v-if="singleProject">
        ID: {{ singleProject.id }}<br />
        Nazwa: {{ singleProject.projectName }}<br />
        Właściciel: {{ singleProject.overseerId }}<br />
        Data dodania: {{ singleProject.createdAt }}<br />
        Data ostatniej edycji: {{ singleProject.lastModified }}
      </p>
      <ul v-if="singleProject">
        <h4>Zadania</h4>
        <li v-for="(task, index) in singleProject.tasks" :key="index">
          <p>Zadanie {{ index + 1 }}: {{ task }}</p>
        </li>
      </ul>
    </div>
    <div class="deleteProject">
      <button class="deleteProjectButton" @click="deleteProject(deleteId)">
        Usuń projekt
      </button>
      <input
        v-model="deleteId"
        type="text"
        placeholder="Podaj ID projektu do usunięcia"
      />
    </div>
    <div class="addProject">
      <button
        class="addProjectButton"
        @click="addProject(overseerId, projectName, firstTask)"
      >
        Dodaj Projekt
      </button>
      <input v-model="overseerId" type="text" placeholder="Id właściciela" />
      <input v-model="projectName" type="text" placeholder="Nazwa projektu" />
      <input v-model="firstTask" type="text" placeholder="Pierwsze zadanie" />
    </div>
    <div class="changeProject">
      <button
        class="changeProjectButton"
        @click="changeProject(changeId, changeOverseerId, changeProjectName)"
      >
        Edytuj Projekt
      </button>
      <input
        v-model="changeId"
        type="text"
        placeholder="Id projektu do zmiany"
      />
      <input
        v-model="changeOverseerId"
        type="text"
        placeholder="Nowe id właściciela"
      />
      <input
        v-model="changeProjectName"
        type="text"
        placeholder="Nowa nazwa projektu"
      />
    </div>
    <div>
      <p v-if="msg">{{ this.msg }}</p>
    </div>
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
.getProjectButton {
  width: 200px;
  height: 50px;
  color: black;
  background: chartreuse;
  border-color: darkgreen;
  font-size: 18px;
}
.deleteProjectButton {
  width: 200px;
  height: 50px;
  color: black;
  background: chartreuse;
  border-color: darkgreen;
  font-size: 18px;
}
.addProjectButton {
  width: 200px;
  height: 50px;
  color: black;
  background: chartreuse;
  border-color: darkgreen;
  font-size: 18px;
}
.changeProjectButton {
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
.projectsView {
  display: inline-flex;
  flex-direction: column;
  align-items: flex-start;
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
      singleProject: false,
      msg: "",
      changeProjectBody: null,
    };
  },
  methods: {
    toggleProjects() {
      this.showProjects = !this.showProjects;
      if (this.showProjects) {
        this.getProjects();
      }
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
    getProject(Id) {
      fetch(`${API}/projects/${Id}`)
        .then((response) => response.json())
        .then((data) => {
          this.msg = "";
          this.singleProject = data.data;
          if (Id === "" || data.data === null) {
            this.msg = "Nie ma projektu o takim id";
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteProject(deleteId) {
      fetch(`${API}/projects/${deleteId}`, { method: "DELETE" })
        .then((response) => response.json())
        .then((data) => {
          this.msg = "Projekt został usunięty";
          if (data.status === 404) {
            this.msg = "Nie można usunąć projektu o nieistniejącym id";
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addProject(overseerId, projectName, firstTask) {
      if (firstTask == null) {
        firstTask = [];
      } else {
        firstTask = [firstTask];
      }
      if (overseerId == null || projectName == null) {
        this.msg = "Aby dodać projekt podaj nazwę i właściciela";
      } else {
        fetch(`${API}/projects`, {
          method: "Post",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            overseerId: overseerId,
            projectName: projectName,
            tasks: firstTask,
          }),
        })
          .then((response) => response.json())
          .then((data) => {
            this.msg = "";
            if (data.message === "success") {
              this.msg = "Projekt został dodany";
            } else {
              this.msg = "Nie udało się dodać projektu";
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    changeProject(changeId, changeOverseerId, changeProjectName) {
      if (changeOverseerId == null && changeProjectName == null) {
        this.msg = "Podaj wartość w polu które chcesz zedytować";
      } else {
        if (changeOverseerId == null) {
          this.changeProjectBody = JSON.stringify({
            projectName: changeProjectName,
          });
        } else if (changeProjectName == null) {
          this.changeProjectBody = JSON.stringify({
            overseerId: changeOverseerId,
          });
        } else {
          this.changeProjectBody = JSON.stringify({
            overseerId: changeOverseerId,
            projectName: changeProjectName,
          });
        }
        fetch(`${API}/projects/${changeId}`, {
          method: "Put",
          headers: {
            "Content-Type": "application/json",
          },
          body: this.changeProjectBody,
        })
          .then((response) => response.json())
          .then((data) => {
            this.msg = "";
            if (data.message === "success") {
              this.msg = "Projekt został edytowany";
            } else {
              this.msg =
                "Nie udało się edytować projektu. Sprawdź poprawność Id";
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
  },
};
</script>
