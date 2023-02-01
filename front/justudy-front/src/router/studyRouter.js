import CreateStudyView from '@/views/Study/CreateStudyView';
import ApplyStudyView from '@/views/Study/ApplyStudyView';
import SearchStudyView from '@/views/Study/SearchStudyView';
import MyStudyView from '@/views/Study/MyStudyView';
import StudyCommuMain from '@/views/Study/StudyCommuMain';
import StudyCommuContent from '@/views/Study/StudyCommuContent';
import StudyCommuWrite from '@/views/Study/StudyCommuWrite';
import StudyMain from '@/views/StudyOne/StudyMain';
import StudyBoard from '@/views/StudyOne/StudyBoard';
import StudyInfo from '@/views/StudyOne/StudyInfo';

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

    // 개별 스터디 router
    //임시 board
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
    },

    // 이제부터 수정할 router
    {
        path: '/study/:studySeq',
        name: 'StudyMain',
        component: StudyMain,
        children: [
            {
                path: 'info',
                name: 'StudyInfo',
                component: StudyInfo
            },
            {
                path: 'board',
                name: 'StudyBoard',
                component: StudyBoard
            }
        ]
    }
];
