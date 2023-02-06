import Vue from 'vue';
import VueRouter from 'vue-router';
import studyRouter from './studyRouter';
import userRouter from './userRouter';
import CommunityRouter from './CommunityRouter';
import selfTestRouter from './selfTestRouter';
import loginRouter from './loginRouter';
import mainRouter from './mainRouter';
import timerRouter from './timerRouter';
import AdminRouter from './AdminRouter';
import rankingRouter from './rankingRouter';
import webRTCRouter from './webRTCRouter';

Vue.use(VueRouter);

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [...studyRouter, ...userRouter, ...CommunityRouter, ...selfTestRouter, ...loginRouter, ...mainRouter, ...timerRouter, ...AdminRouter, ...rankingRouter, ...webRTCRouter]
});

export default router;
