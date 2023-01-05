import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import AboutView from "../views/AboutView.vue";
import UsersView from "../views/UsersView.vue";
import ProjectsView from "@/views/ProjectsView";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/about",
    name: "about",
    component: AboutView,
  },
  {
    path: "/users",
    name: "users",
    component: UsersView,
  },
  {
    path: "/projects",
    name: "projects",
    component: ProjectsView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
