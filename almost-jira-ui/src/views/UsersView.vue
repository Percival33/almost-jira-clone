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
  <div class="singleUser">
    <button class="getUserButton" @click="getUser(Id)">
      Pokaż użytkownika
    </button>
    <input v-model="Id" type="text" placeholder="Podaj ID użytkownika" />
    <p v-if="msg">{{ this.msg }}</p>
    <p v-if="singleUser">
      ID: {{ singleUser.id }}<br />
      Imię: {{ singleUser.firstName }}<br />
      Nazwisko: {{ singleUser.lastName }}<br />
      Data dodania: {{ singleUser.createdAt }}<br />
      Data ostatniej edycji: {{ singleUser.lastModified }}
    </p>
    <ul v-if="singleUser">
      <li v-for="project in singleUser.projects" :key="project.id">
        <p>Nazwa projektu: project.projectName</p>
      </li>
    </ul>
  </div>
  <div class="deleteUser">
    <button class="deleteUserButton" @click="deleteUser(deleteId)">
      Usuń użytkownika
    </button>
    <input
      v-model="deleteId"
      type="text"
      placeholder="Podaj ID użytkownika do usunięcia"
    />
    <p v-if="deleteMsg">{{ this.deleteMsg }}</p>
  </div>
  <div class="addUser">
    <button
      class="addUserButton"
      @click="addUser(firstName, lastName, password)"
    >
      Dodaj Użytkownika
    </button>
    <input v-model="firstName" type="text" placeholder="Imie użytkownika" />
    <input v-model="lastName" type="text" placeholder="Nazwisko użytkownika" />
    <input v-model="password" type="text" placeholder="Hasło użytkownika" />
    <p v-if="addMsg">{{ this.addMsg }}</p>
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
.deleteUserButton {
  width: 200px;
  height: 50px;
  color: black;
  background: chartreuse;
  border-color: darkgreen;
  font-size: 18px;
}
.addUserButton {
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
      singleUser: false,
      msg: "",
      deleteMsg: "",
      addMsg: "",
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
          this.msg = "";
          if (Id == "" || data.data === null) {
            this.msg = "Nie ma użytkownika o takim id";
          }
          this.singleUser = data.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteUser(deleteId) {
      fetch(`${API}/users/${deleteId}`, { method: "DELETE" })
        .then((response) => response.json())
        .then((data) => {
          this.deleteMsg = "Użytkownik został usunięty";
          if (data.status === 404) {
            this.deleteMsg = "Nie ma takiego użytkownika";
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addUser(firstName, lastName, password) {
      // const formData = new FormData();
      // formData.append("firstName", firstName);
      // formData.append("lastName", lastName);
      // formData.append("password", password);
      // form = new UserForm(firstName, lastName, password);
      fetch(`${API}/users`, {
        method: "Post",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          firstName: firstName,
          lastName: lastName,
          password: password,
        }),
      })
        .then((response) => response.json())
        .then((data) => {
          this.addMsg = "";
          if (data.message === "success") {
            this.deleteMsg = "Użytkownik został dodany";
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
