import Vue from 'vue';
import VueRouter from 'vue-router';
import studyRouter from './studyRouter';
import userRouter from './userRouter';
import CommunityRouter from './CommunityRouter';
import selfTestRouter from './selfTestRouter';
import loginRouter from './loginRouter';

Vue.use(VueRouter);

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [...studyRouter, ...userRouter, ...CommunityRouter, ...selfTestRouter, ...loginRouter]
});

export default router;
