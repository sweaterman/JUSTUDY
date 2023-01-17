import Vue from 'vue';
import VueRouter from 'vue-router';
import studyRouter from './studyRouter';
import userRouter from './userRouter';
import CommunityRouter from './CommunityRouter';

Vue.use(VueRouter);

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [...studyRouter, ...userRouter, ...CommunityRouter]
});

export default router;
