import TimerStart from '@/views/Timer/TimerStart.vue';
import TimerMyStatistic from '@/views/Timer/TimerMyStatistic.vue';
// import FriendStatisticsView from '@/views/Timer/FriendStatistics.vue';
import RankingAllView from '@/views/Timer/RankingAll.vue';
import TimerOthersStatic from '@/views/Timer/TimerOthersStatic';
export default [
    {
        path: '/timer/study-start',
        name: 'TimerStart',
        component: TimerStart
    },
    {
        path: '/timer/my-statistics',
        name: 'TimerMyStatistic',
        component: TimerMyStatistic
        // meta: {authorization: ['login']}
    },
    {
        path: '/timer/friend-statistics',
        name: 'TimerOthersStatic',
        component: TimerOthersStatic
    },
    {
        path: '/timer/ranking',
        name: 'TimerRanking',
        component: RankingAllView
    }
];
