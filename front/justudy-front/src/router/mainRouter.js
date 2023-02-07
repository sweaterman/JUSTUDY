import Main from '../views/Main/Main.vue';
import Error from '../views/Main/Error.vue';

export default [
    {
        path: '/',
        name: 'Main',
        component: Main
    },
    {
        path: '/error',
        name: 'Error',
        component: Error
    }
];
