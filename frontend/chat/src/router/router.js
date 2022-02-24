import Vue from 'vue';
import VueRouter from 'vue-router';
import store from '../store';

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
    {
      path: '/Random',
      name: 'Random',
      component: () => import('@/views/Random.vue'),
    },
  ];

const router = new VueRouter({
    mode:'history', //해쉬값 제거 방식
    routes
});

router.beforeEach(async (to,from,next) => {
  
  if(to.path === '/room'){
    if(store.getters.roomId === ""){
      next('/');
    }
  }
  next();
})

export default router;