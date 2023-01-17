import Vue from 'vue';
import VueRouter from 'vue-router';
import CommunityRouter from './CommunityRouter';

Vue.use(VueRouter);

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [...CommunityRouter]
});

export default router;
