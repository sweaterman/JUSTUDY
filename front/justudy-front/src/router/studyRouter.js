import CreateStudyView from '@/views/Study/CreateStudyView';
import ApplyStudyView from '@/views/Study/ApplyStudyView';
import SearchStudyView from '@/views/Study/SearchStudyView';
import MyStudyView from '@/views/Study/MyStudyView';

export default [
    {
        path: '/study/search',
        name: 'SearchStudyView',
        component: SearchStudyView
    },
    {
        path: '/study/create',
        name: 'CreateStudyView',
        component: CreateStudyView
    },
    {
        path: '/study/search/:studySeq',
        name: 'ApplyStudyView',
        component: ApplyStudyView
    },
    {
        path: '/study/myStudy',
        name: 'MyStudyView',
        component: MyStudyView
    }
];
