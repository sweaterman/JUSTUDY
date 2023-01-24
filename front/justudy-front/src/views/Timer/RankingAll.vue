<template>
    <v-app>
        <div style="padding: 10%">
            <CommonRanking />
            <hr />
            <Ranking contentLeft="전날 Burning 1위" contentRight="전날 Burning top10" />
            <Ranking contentLeft="이번주 1위" contentRight="주간 top10" />
            <Ranking contentLeft="이번주 1위" contentRight="주간 top10" />
        </div>
    </v-app>
</template>
<script>
import Ranking from '@/components/timer/Ranking.vue';
import CommonRanking from '@/components/timer/CommonRanking.vue';
export default {
    name: 'RankingAllView',
    components: {
        CommonRanking,
        Ranking
    }
};
</script>
