import MainPage from '../views/Community/MainPage.vue';
import CommuBoard from '../views/Community/CommuBoard.vue';
import CommuWrite from '../views/Community/CommuWrite.vue';
import CommuContent from '../views/Community/CommuContent.vue';

export default [
    {
        path: '/',
        name: 'MainPage',
        component: MainPage
    },
    {
        path: '/board/:id',
        name: 'CommuBoard',
        component: CommuBoard
    },
    {
        path: '/board/:id/write',
        name: 'CommuWrite',
        component: CommuWrite
    },
    {
        path: '/board/:id/content',
        name: 'CommuContent',
        component: CommuContent
    }
];
