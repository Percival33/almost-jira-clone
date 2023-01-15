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
    <div class="addTaskToProject">
      <button
        class="addTaskToProjectButton"
        @click="addTaskToProject(addTaskProjectId, newTask)"
      >
        Dodaj Zadanie
      </button>
      <input v-model="addTaskProjectId" type="text" placeholder="Id projektu" />
      <input
        v-model="newTask"
        type="text"
        defaultValue=""
        style="width: 400px"
        placeholder="Opis Zadania do dodania"
      />
    </div>
    <div class="removeTaskFromProject">
      <button
        class="removeTaskFromProjectButton"
        @click="removeTaskFromProject(removeTaskProjectId, taskToRemoveIndex)"
      >
        Usuń zadanie
      </button>
      <input
        v-model="removeTaskProjectId"
        type="text"
        placeholder="Id projektu"
      />
      <input
        v-model="taskToRemoveIndex"
        type="text"
        placeholder="Numer zadania do usunięcia"
      />
    </div>
    <div class="editTaskInProject">
      <button
        class="editTaskInProjectButton"
        @click="
          editTaskInProject(editTaskProjectId, taskToEditIndex, editedTask)
        "
      >
        Edytuj zadanie
      </button>
      <input
        v-model="editTaskProjectId"
        type="text"
        placeholder="Id projektu"
      />
      <input
        v-model="taskToEditIndex"
        type="text"
        placeholder="Numer zadania do edytowania"
      />
      <input
        v-model="editedTask"
        type="text"
        placeholder="Treść zadania"
        style="width: 400px"
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
.addTaskToProjectButton {
  width: 200px;
  height: 50px;
  color: black;
  background: chartreuse;
  border-color: darkgreen;
  font-size: 18px;
}
.removeTaskFromProjectButton {
  width: 200px;
  height: 50px;
  color: black;
  background: chartreuse;
  border-color: darkgreen;
  font-size: 18px;
}
.editTaskInProjectButton {
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
import store from "../store.js";
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
      fetch(`${API}/projects/${deleteId}?user_id=${store.state.userId}`, {
        method: "DELETE",
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.status === 200) {
            this.msg = "Projekt został usunięty";
          }
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
        fetch(`${API}/projects/${changeId}?user_id=${store.state.userId}`, {
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
    addTaskToProject(addTaskProjectId, newTask) {
      if (newTask === null || newTask === "" || newTask === undefined) {
        this.msg = "Nie można dodać pustego zadania";
      } else {
        newTask = encodeURIComponent(newTask);
        fetch(`${API}/projects/${addTaskProjectId}/tasks?newTask=${newTask}`, {
          method: "POST",
        })
          .then((response) => response.json())
          .then((data) => {
            this.msg = "Zadanie zostało dodane";
            if (data.status === 404) {
              this.msg = "Nie udało się dodać zadania";
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    removeTaskFromProject(removeTaskProjectId, taskToRemoveIndex) {
      fetch(
        `${API}/projects/${removeTaskProjectId}/tasks?taskIndex=${taskToRemoveIndex}`,
        {
          method: "DELETE",
        }
      )
        .then((response) => response.json())
        .then((data) => {
          this.msg = "Zadanie zostało usunięte";
          if (data.status === 404) {
            this.msg = "Nie udało się usunąć zadania";
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    editTaskInProject(editTaskProjectId, taskToEditIndex, editedTask) {
      if (
        editedTask === null ||
        editedTask === "" ||
        editedTask === undefined ||
        taskToEditIndex === undefined ||
        taskToEditIndex === null ||
        taskToEditIndex === ""
      ) {
        this.msg = "Zadanie ani jego numer nie mogą być puste";
      } else {
        editedTask = encodeURIComponent(editedTask);
        fetch(
          `${API}/projects/${editTaskProjectId}/tasks?taskIndex=${taskToEditIndex}&modifiedTask=${editedTask}`,
          {
            method: "PUT",
          }
        )
          .then((response) => response.json())
          .then((data) => {
            this.msg = "Zadanie zostało edytowane";
            if (data.message != "success") {
              this.msg = "Nie udało się edytować zadania";
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
