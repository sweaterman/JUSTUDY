import CreateStudyView from '@/views/Study/CreateStudyView';
import ApplyStudyView from '@/views/Study/ApplyStudyView';

export default [
    {
        path: '/study/create',
        name: 'CreateStudyView',
        component: CreateStudyView
    },
    {
        path: '/study/apply/:studySeq',
        name: 'ApplyStudyView',
        component: ApplyStudyView
    }
];
