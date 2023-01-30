import CommuBoard from '../views/Community/CommuBoard.vue';
import CommuWrite from '../views/Community/CommuWrite.vue';
import CommuContent from '../views/Community/CommuContent.vue';

export default [
    {
        path: '/community/:page',
        name: 'CommuBoard',
        component: CommuBoard
    },
    {
        path: '/community/:page/write',
        name: 'CommuWrite',
        component: CommuWrite
    },
    {
        path: '/community/:page/content/:id',
        name: 'CommuContent',
        component: CommuContent
    }
];
