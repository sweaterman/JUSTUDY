import RankingMy from '@/views/Ranking/RankingMy.vue';
import RankingStudy from '@/views/Ranking/RankingStudy.vue';
export default [
    {
        path: '/ranking/my-ranking',
        name: 'RankingMy',
        component: RankingMy
    },
    {
        path: '/ranking/study-ranking',
        name: 'RankingStudy',
        component: RankingStudy
    }
];
