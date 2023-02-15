import SelfTestProblemView from '@/views/self-test/SelfTestProblem.vue';
import SelfTestConfirmView from '@/views/self-test/SelfTestConfirm.vue';
import SelfTestResultView from '@/views/self-test/SelfTestResult.vue';
export default [
    {
        path: '/self-test/problem/:number',
        name: 'SelfTestProblemView',
        component: SelfTestProblemView,
        meta: {authorization: ['login']}
    },
    {
        path: '/self-test/confirm',
        name: 'SelfTestConfirmView',
        component: SelfTestConfirmView,
        meta: {authorization: ['login']}
    },
    {
        path: '/self-test/result',
        name: 'SelfTestResultView',
        component: SelfTestResultView,
        meta: {authorization: ['login']}
    }
];
