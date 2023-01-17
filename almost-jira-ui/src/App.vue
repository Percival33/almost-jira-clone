<template>
  <nav>
    <router-link to="/">Projekt Almost Jira</router-link> |
    <!-- <router-link to="/about">About</router-link> | -->
    <router-link to="/users">Użytkownicy</router-link> |
    <router-link to="/projects">Projekty</router-link> |
    <router-link to="/login">Logowanie</router-link>
  </nav>
  <router-view />
  <notifications />
</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
