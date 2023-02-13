<template>
    <v-app>
        <div style="padding: 10%">
            <CommonRanking />
            <hr />
            <Ranking contentLeft="전날 Burning 1위" contentRight="전날 Burning top10" :data="rankMemberYesterday" />
            <Ranking contentLeft="이번주 1위" contentRight="주간 top10" :data="rankMemberWeek" />
            <Ranking contentLeft="이번주 1위" contentRight="주간 top10" :data="rankMemberMonth" />
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
    },
    data() {
        return {
            rankMemberYesterday: [],
            rankMemberWeek: [],
            rankMemberMonth: []
        };
    },
    async created() {
        await this.$store.dispatch('moduleRanking/getRankMemberYesterday');
        this.rankMemberYesterday = this.$store.state.moduleRanking.rankMemberYesterday;
        await this.$store.dispatch('moduleRanking/getRankMemberWeek');
        this.rankMemberWeek = this.$store.state.moduleRanking.rankMemberWeek;
        await this.$store.dispatch('moduleRanking/getRankMemberMonth');
        this.rankMemberMonth = this.$store.state.moduleRanking.rankMemberMonth;
    }
};
</script>
