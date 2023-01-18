import SignIn from '../views/Login/SignIn.vue';
import LogIn from '../views/Login/LogIn.vue';
import temp from '../views/Login/temp_dialog.vue';

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
    },
    {
        path: '/temp',
        name: 'temp',
        component: temp
    }
];
