import CreateStudyView from '@/views/Study/CreateStudyView';
import ApplyStudyView from '@/views/Study/ApplyStudyView';
import SearchStudyView from '@/views/Study/SearchStudyView';
import MyStudyView from '@/views/Study/MyStudyView';
import StudyDetailView from '@/views/Study/StudyDetailView';
import StudyCommuMain from '@/views/Study/StudyCommuMain';
import StudyCommuContent from '@/views/Study/StudyCommuContent';
import StudyCommuWrite from '@/views/Study/StudyCommuWrite';

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
    },
    {
        path: '/study/detail/:studySeq',
        name: 'StudyDetailView',
        component: StudyDetailView
    },
    {
        path: '/study/board/:studySeq',
        name: 'StudyCommuMain',
        component: StudyCommuMain
    },
    {
        path: '/study/board/content/:studySeq/:boardSeq',
        name: 'StudyCommuContent',
        component: StudyCommuContent
    },
    {
        path: '/study/board/:studySeq/write',
        name: 'StudyDetailWrite',
        component: StudyCommuWrite
    }
];
