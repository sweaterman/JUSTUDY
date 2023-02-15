import MyPageView from '../views/mypage/MyPageView.vue';
import MyPageUpdateView from '../views/mypage/MyPageUpdateView.vue';
export default [
    {
        path: '/mypage',
        name: 'MyPageView',
        component: MyPageView,
        meta: {authorization: ['login']}
    },
    {
        path: '/mypage/update',
        name: 'MyPageUpdate',
        component: MyPageUpdateView,
        meta: {authorization: ['login']}
    }
];
