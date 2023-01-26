import StudyStartView from '@/views/Timer/StudyStart.vue';
import MyStatisticsView from '@/views/Timer/MyStatistics.vue';
import FriendStatisticsView from '@/views/Timer/FriendStatistics.vue';
import RankingAllView from '@/views/Timer/RankingAll.vue';
export default [
    {
        path: '/timer/study-start',
        name: 'TimerStudyStart',
        component: StudyStartView
    },
    {
        path: '/timer/my-statistics',
        name: 'TimerMyStatistics',
        component: MyStatisticsView
    },
    {
        path: '/timer/friend-statistics',
        name: 'TimerFriendStatistics',
        component: FriendStatisticsView
    },
    {
        path: '/timer/ranking',
        name: 'TimerRanking',
        component: RankingAllView
    }
];
