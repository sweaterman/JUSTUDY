<template>
    <v-app>
        <v-row>
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <!-- 랭킹 메뉴 바 -->
                <v-row :style="{padding: '2%'}">
                    <v-col cols="12" md="6" justify="center" align="center">
                        <router-link to="/ranking/my-ranking" style="text-decoration: none; color: #ffb000">
                            <span class="material-icons-outlined"> person </span>
                            <div>개인 랭킹</div>
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="6" justify="center" align="center">
                        <router-link to="/ranking/study-ranking" style="text-decoration: none; color: black">
                            <span class="material-icons-outlined"> group </span>
                            <div>스터디 랭킹</div>
                        </router-link>
                    </v-col>
                </v-row>

                <MyRanking_table />
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>
<script>
import MyRanking_table from '@/components/ranking/RankingMy_table.vue';
import MyRankingData from '@/data/MyRankingData';
// import ProfilePicture from '@/components/mypage/ProfilePicture.vue';
// import DigitalClock from '@/components/timer/DigitalClock.vue';
import port from '@/store/port';
// import RankingForm from '../../components/timer/RankingForm.vue';
export default {
    name: 'MyRanking',
    data() {
        return {
            data: MyRankingData,
            // rankMemberYesterday: [],
            rankMemberWeek: [],
            rankMemberMonth: [],
            port: port,

            // 더미
            rankMemberYesterday: [
                {
                    img: '',
                    nickName: '',
                    time: ''
                },
                {}
            ]
        };
    },

    components: {
        MyRanking_table
        // ProfilePicture,
        // RankingForm,
        // DigitalClock
        // DigitalClockRankingFom
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
<style>
/* 임시 alarm */
/* .alarm {
    width: 30%;
    background-color: rgb(132, 220, 230);
    position: absolute;
    top: 30px;
    height: 200px;
    z-index: 2;
} */
.line_limit_alarm {
    padding: 15px;
    width: 80%;
    height: 400px;
    margin-top: 20px;
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
.line_limit {
    width: 40px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    display: inline-block;
}
.simple_table {
    min-width: 500px !important;
}
</style>
