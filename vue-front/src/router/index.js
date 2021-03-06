import { createRouter, createWebHistory } from 'vue-router'
import LoginPage from '@/views/LoginPage'

const routes = [
  {
    path: '/login',
    name: 'LoginPage',
    component: LoginPage
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
