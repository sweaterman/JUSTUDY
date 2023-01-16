import Vue from 'vue';
import VueRouter from 'vue-router';
import userRouter from '../router/userRouter'


Vue.use(VueRouter);



const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        ...userRouter
    ]
});

export default router;
