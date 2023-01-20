import MainPage from '../views/Community/MainPage.vue';
import CommuWrite from '../views/Community/CommuWrite.vue';
import CommuContent from '../views/Community/CommuContent.vue';

export default [
    {
        path: '/community',
        name: 'MainPage',
        component: MainPage
    },
    {
        path: '/community/:id',
        name: 'CommuBoard',
        component: MainPage
    },
    {
        path: '/community/:id/write',
        name: 'CommuWrite',
        component: CommuWrite
    },
    {
        path: '/community/:id/content',
        name: 'CommuContent',
        component: CommuContent
    }
];
