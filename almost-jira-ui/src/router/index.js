import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import AboutView from "../views/AboutView.vue";
import UsersView from "../views/UsersView.vue";
import ProjectsView from "@/views/ProjectsView";
import LoginView from "@/views/LoginView.vue";

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
    path: "/login",
    name: "login",
    component: LoginView,
  },
  {
    path: "/users",
    name: "users",
    component: UsersView,
    meta: { requiresAuth: true },
  },
  {
    path: "/projects",
    name: "projects",
    component: ProjectsView,
    meta: { requiresAuth: true },
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach((to) => {
  if (to.meta.requiresAuth && !checkAuthorization()) {
    return {
      path: "/login",
      query: { redirect: to.fullPath },
    };
  }
});

function checkAuthorization() {
  return true;
}

export default router;
