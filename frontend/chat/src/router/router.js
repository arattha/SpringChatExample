import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const routes = [
    {
      path: '/',
      name: 'Main',
      component: () => import('@/views/Main.vue'),
    },
    {
      path: '/room',
      name: 'Room',
      component: () => import('@/views/Room.vue'),
    },
  ];

const router = new VueRouter({
    mode:'history', //해쉬값 제거 방식
    routes
});

export default router;