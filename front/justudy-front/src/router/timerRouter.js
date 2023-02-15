import TimerStart from '@/views/Timer/TimerStart.vue';
import TimerMyStatistic from '@/views/Timer/TimerMyStatistic.vue';
import TimerFriendStatistic from '@/views/Timer/TimerFriendStatistic.vue';

import RankingAllView from '@/views/Timer/RankingAll.vue';

import Aaaa from '@/views/Timer/FriendStatistics2.vue';
export default [
    {
        path: '/timer/study-start',
        name: 'TimerStart',
        component: TimerStart,
        meta: {authorization: ['login']}
    },
    {
        path: '/timer/my-statistics',
        name: 'TimerMyStatistic',
        component: TimerMyStatistic,
        meta: {authorization: ['login']}
    },
    {
        path: '/timer/friend-statistics',
        name: 'TimerFriendStatistic',
        component: TimerFriendStatistic,
        meta: {authorization: ['login']}
    },
    {
        path: '/timer/ranking',
        name: 'TimerRanking',
        component: RankingAllView
    },
    {
        path: '/aaa',
        component: Aaaa,
        meta: {authorization: ['login']}
    }
];
