import SignUp from '../views/Login/SignUp.vue';
import LogIn from '../views/Login/LogIn.vue';
import beforeLogin from '../views/Login/BeforeLogin.vue';

export default [
    {
        path: '/signUp',
        name: 'SignUp',
        component: SignUp
    },
    {
        path: '/login',
        name: 'LogIn',
        component: LogIn
    },
    {
        path: '/beforeLogin',
        name: 'beforeLogin',
        component: beforeLogin
    }
];
