import SelfTestProblemView from '@/views/self-test/SelfTestProblem.vue';
import SelfTestConfirmView from '@/views/self-test/SelfTestConfirm.vue';
import SelfTestResultView from '@/views/self-test/SelfTestResult.vue';
export default [
    {
        path: '/self-test/problem/:number',
        name: 'SelfTestProblemView',
        component: SelfTestProblemView
    },
    {
        path: '/self-test/confirm',
        name: 'SelfTestConfirmView',
        component: SelfTestConfirmView
    },
    {
        path: '/self-test/result',
        name: 'SelfTestResultView',
        component: SelfTestResultView
    }
];
