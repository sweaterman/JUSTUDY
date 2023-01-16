import Vue from 'vue';
import VueRouter from 'vue-router';
import studyRouter from './studyRouter';

Vue.use(VueRouter);

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [...studyRouter]
});

export default router;
