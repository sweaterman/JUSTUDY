<template>
    <v-app>
        <v-row>
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <!-- 랭킹 메뉴 바 -->
                <v-row :style="{padding: '2%'}">
                    <v-col cols="12" md="6" justify="center" align="center">
                        <router-link to="/ranking/my-ranking" style="text-decoration: none; color: black">
                            <span class="material-icons-outlined"> person </span>
                            <div>개인 랭킹</div>
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="6" justify="center" align="center">
                        <router-link to="/ranking/study-ranking" style="text-decoration: none; color: #ffb000">
                            <span class="material-icons-outlined"> group </span>
                            <div>스터디 랭킹</div>
                        </router-link>
                    </v-col>
                </v-row>

                <StudyRanking_table />
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>
<script>
import StudyRanking_table from '@/components/ranking/RankingStudy_table.vue';
import port from '@/store/port';
import MyRankingData from '@/data/MyRankingData';
// import ProfilePicture from '@/components/mypage/ProfilePicture.vue';
// import DigitalClock from '@/components/timer/DigitalClock.vue';
// import RankingForm from '../../components/timer/RankingForm.vue';
export default {
    name: 'MyRanking',
    data() {
        return {
            data: MyRankingData,
            rankStudyYesterday: [],
            rankStudyWeek: [],
            rankStudyMonth: [],
            port: port
        };
    },

    components: {
        StudyRanking_table
        // RankingForm,
        // DigitalClockRankingFom
    },
    async created() {
        await this.$store.dispatch('moduleRanking/getRankStudyYesterday');
        this.rankStudyYesterday = this.$store.state.moduleRanking.rankStudyYesterday;
        await this.$store.dispatch('moduleRanking/getRankStudyWeek');
        this.rankStudyWeek = this.$store.state.moduleRanking.rankStudyWeek;
        await this.$store.dispatch('moduleRanking/getRankStudyMonth');
        this.rankStudyMonth = this.$store.state.moduleRanking.rankStudyMonth;
    }
};
</script>
<style>
.line_limit {
    width: 300px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    display: inline-block;
}
</style>
