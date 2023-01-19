import SignIn from '../views/Login/SignIn.vue';
import LogIn from '../views/Login/LogIn.vue';
export default [
    {
        path: '/signin',
        name: 'SignIn',
        component: SignIn
    },
    {
        path: '/login',
        name: 'LogIn',
        component: LogIn
    }
];
