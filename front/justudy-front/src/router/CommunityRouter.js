import CommuBoard from '../views/Community/CommuBoard.vue';
import CommuWrite from '../views/Community/CommuWrite.vue';
import CommuContent from '../views/Community/CommuContent.vue';
import CommuUpdate from '../views/Community/CommuUpdate.vue';

export default [
    {
        path: '/community',
        name: 'CommuBoard',
        component: CommuBoard
    },
    {
        path: '/community/write',
        name: 'CommuWrite',
        component: CommuWrite,
        meta: {authorization: ['login']}
    },
    {
        path: '/community/content/:id',
        name: 'CommuContent',
        component: CommuContent
    },
    {
        path: '/community/content/:id/update',
        name: 'CommuUpdate',
        component: CommuUpdate,
        meta: {authorization: ['login']}
    }
];
