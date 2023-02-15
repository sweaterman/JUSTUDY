<template>
    <v-app>
        <v-row>
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <!-- 타이머 메뉴 바 -->
                <v-row :style="{padding: '2%'}">
                    <v-col cols="12" md="6" justify="center" align="center">
                        <router-link to="/timer/study-start" style="text-decoration: none; color: #ffb000">
                            <span class="material-icons-outlined"> timer </span>
                            <div>타이머</div>
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="6" justify="center" align="center">
                        <router-link to="/timer/friend-statistics" style="text-decoration: none; color: black">
                            <span class="material-icons-outlined"> show_chart </span>
                            <div>통계</div>
                        </router-link>
                    </v-col>
                </v-row>

                <!-- 카메라 화면 - 미구현 -->
                <v-row>
                    <v-col>
                        <TimerCamera />
                    </v-col>
                </v-row>

                <v-row justify="center" align="center">
                    <v-col cols="12" md="4" justify="center" align="center">
                        <!-- 나의 공부 시간 -->
                        <v-row :style="{marginTop: '20%'}">
                            <v-col>
                                <DigitalClockPerDate content="이번주 공부 시간" :allTime="weekTime" />
                            </v-col>
                        </v-row>
                        <v-row :style="{marginTop: '15%'}">
                            <v-col>
                                <DigitalClockPerDate content="이번달 공부 시간" :allTime="monthTime" />
                            </v-col>
                        </v-row>
                    </v-col>
                    <!-- 달력 -->
                    <v-col cols="12" md="8">
                        <TimerCalendar :studyCalendar="studyCalendar" :nickName="user.nickname" v-if="loaded" />
                    </v-col>
                </v-row>
            </v-col>

            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>
<script>
// import TimerHeader from '../../components/timer/TimerHeader.vue';
import TimerCalendar from '@/components/timer/TimerCalendar.vue';
import TimerCamera from '../../components/timer/TimerCamera.vue';
import DigitalClockPerDate from '@/components/timer/DigitalClockPerDate.vue';
// import DigitalClockAverage from '@/components/timer/DigitalClockAverage.vue';
export default {
    name: 'StudyStart',
    data() {
        return {
            king_name: '돌로스원숭숭',
            king_time: '00:53:23',
            allTimeMe: 0,
            allTimeFirst: 9000,
            today: '',
            firstYesterday: this.$store.state.moduleTimer.firstYesterday,

            //my statics
            weekTime: 0,
            monthTime: 0,
            averageWeekTime: 0,
            averageMonthTime: 0,
            studyCategory: [],
            studyCalendar: [],
            user: {},
            loaded: false
        };
    },

    components: {
        // DigitalClockAverage,
        // TimerHeader,
        TimerCalendar,
        DigitalClockPerDate,
        TimerCamera
    },
    async created() {
        this.loaded = false;
        await this.$store.dispatch('moduleMyPage/getMyPageUser');
        this.user = this.$store.state.moduleMyPage.user;
        // 타이머 정보 부분
        await this.$store.dispatch('moduleTimer/getStudyTimeWeek', {nickName: this.user.nickname});
        this.weekTime = this.$store.state.moduleTimer.studyTimeWeek.time;
        await this.$store.dispatch('moduleTimer/getStudyTimeMonth', {nickName: this.user.nickname});
        this.monthTime = this.$store.state.moduleTimer.studyTimeMonth.time;
        await this.$store.dispatch('moduleTimer/getAverageMembersWeek');
        this.averageWeekTime = this.$store.state.moduleTimer.averageMemberWeek.time;
        await this.$store.dispatch('moduleTimer/getAverageMembersMonth');
        this.averageMonthTime = this.$store.state.moduleTimer.averageMemberMonth.time;
        await this.$store.dispatch('moduleTimer/getStudyCategory', {nickName: this.user.nickname});
        this.studyCategory = this.$store.state.moduleTimer.studyCategory;

        // API 받기
        await this.$store.dispatch('moduleTimer/getFirstYesterday');
        this.firstYesterday = this.$store.state.moduleTimer.firstYesterday;
        // 무언가 하면 개인 공부 시간 저장
        // this.$store.dispatch('moduleTimer/saveIndividualTime');
        // 날짜 받기
        let today = new Date();
        let year = today.getFullYear();
        let month = today.getMonth() >= 9 ? today.getMonth() + 1 : '0' + (today.getMonth() + 1);
        let date = today.getDate() >= 10 ? today.getDate() : '0' + today.getDate();
        this.today = year + '-' + month + '-' + date;

        setInterval(() => {
            this.allTimeMe++;
            // this.allTimeFirst++;
        }, 1000);

        this.loaded = true;
    }
};
</script>
<style scoped>
.card_section {
    padding: 10px;
    height: 170px;
    margin-bottom: 20px;
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
</style>
