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
import StudyCalender from '@/views/StudyOne/StudyCalender';
import StudyEdit from '@/views/StudyOne/StudyEdit';
import StudyMemberManage from '@/views/StudyOne/StudyMemberManage';
import StudyBoardDetail from '@/views/StudyOne/StudyBoardDetail';
import StudyBoardWrite from '@/views/StudyOne/StudyBoardWrite';
import StudyBoardUpdate from '@/views/StudyOne/StudyBoardUpdate';
import StudyBoardMain from '@/views/StudyOne/StudyBoardMain';

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
                name: 'StudyBoardMain',
                component: StudyBoardMain,
                children: [
                    {
                        path: 'main',
                        name: 'StudyBoard',
                        component: StudyBoard
                    },
                    {
                        path: 'detail/:boardSeq',
                        name: 'StudyBoardDetail',
                        component: StudyBoardDetail
                    },
                    {
                        path: 'update/:boardSeq',
                        name: 'StudyBoardUpdate',
                        component: StudyBoardUpdate
                    },
                    {
                        path: 'write',
                        name: 'StudyBoardWrite',
                        component: StudyBoardWrite
                    }
                ]
            },
            {
                path: 'calender',
                name: 'StudyCalender',
                component: StudyCalender
            },
            {
                path: 'edit',
                name: 'StudyEdit',
                component: StudyEdit
            },
            {
                path: 'memberManage',
                name: 'StudyMemberManage',
                component: StudyMemberManage
            }
        ]
    }
];
