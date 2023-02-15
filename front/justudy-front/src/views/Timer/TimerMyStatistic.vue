<template>
    <v-app>
        <v-row :style="{marginTop: '0.5%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <!-- 타이머 메뉴 바 -->
                <v-row justify="center" align="center">
                    <v-col cols="12" md="4" justify="center" align="center">
                        <router-link to="/timer/my-statistics" style="text-decoration: none; color: #ffb000">
                            <div :style="{fontWeight: 'bold', fontSize: 'large'}"><h2>나의 통계</h2></div>
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="4" justify="center" align="center">
                        <router-link to="/timer/study-start" style="text-decoration: none; color: black">
                            <div>공부 시작</div>
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="4" justify="center" align="center">
                        <router-link to="/timer/friend-statistics" style="text-decoration: none; color: black">
                            <div>친구 통계</div>
                        </router-link>
                    </v-col>
                </v-row>

                <!-- 달력 -->
                <TimerCalendar :studyCalendar="studyCalendar" />

                <!-- 나의 공부 시간 -->
                <v-row :style="{marginTop: '5%', marginBottom: '1%'}">
                    <v-col cols="12" md="4" align="left">
                        <h1>나의 공부 시간</h1>
                        <hr />
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12" md="6">
                        <DigitalClockPerDate content="THIS WEEK" :allTime="weekTime" />
                    </v-col>
                    <v-col cols="12" md="6">
                        <DigitalClockPerDate content="THIS MONTH" :allTime="monthTime" />
                    </v-col>
                </v-row>

                <!-- 나의 평균 비교 -->
                <v-row :style="{marginTop: '5%', marginBottom: '1%'}">
                    <v-col cols="12" md="4" align="left">
                        <h1>SSAFY 공부 시간 비교</h1>
                        <hr />
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        <DigitalClockAverage content="THIS WEEK" :studyTime="weekTime - averageWeekTime" :averageTime="averageWeekTime" />
                    </v-col>
                    <v-col>
                        <DigitalClockAverage content="THIS MONTH" :studyTime="monthTime - averageMonthTime" :averageTime="averageMonthTime" />
                    </v-col>
                </v-row>

                <!-- 나의 과목 비교 -->
                <v-row :style="{marginTop: '5%', marginBottom: '1%'}">
                    <v-col cols="12" md="4" align="left">
                        <h1>공부 진행도</h1>
                        <hr />
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12" md="4">
                        <TimeAndRadarChart :studyCategory="studyCategory" />
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>
<script>
import TimerCalendar from '@/components/timer/TimerCalendar.vue';
import TimeAndRadarChart from '@/components/timer/TimeAndRadarChart.vue';
import DigitalClockPerDate from '@/components/timer/DigitalClockPerDate.vue';
import DigitalClockAverage from '@/components/timer/DigitalClockAverage.vue';
export default {
    name: 'MyStatistics',
    data() {
        return {
            weekTime: 0,
            monthTime: 0,
            averageWeekTime: 0,
            averageMonthTime: 0,
            studyCategory: [],
            studyCalendar: []
        };
    },
    components: {
        TimerCalendar,
        TimeAndRadarChart,
        DigitalClockPerDate,
        DigitalClockAverage
    },
    async created() {
        await this.$store.dispatch('moduleTimer/getStudyTimeWeek', {nickName: '테스트 봇1'});
        this.weekTime = this.$store.state.moduleTimer.studyTimeWeek.time;
        await this.$store.dispatch('moduleTimer/getStudyTimeMonth', {nickName: '테스트 봇1'});
        this.monthTime = this.$store.state.moduleTimer.studyTimeMonth.time;
        await this.$store.dispatch('moduleTimer/getAverageMembersWeek');
        this.averageWeekTime = this.$store.state.moduleTimer.averageMemberWeek.time;
        await this.$store.dispatch('moduleTimer/getAverageMembersMonth');
        this.averageMonthTime = this.$store.state.moduleTimer.averageMemberMonth.time;
        await this.$store.dispatch('moduleTimer/getStudyCategory', {nickName: '테스트 봇1'});
        this.studyCategory = this.$store.state.moduleTimer.studyCategory;
        await this.$store.dispatch('moduleTimer/getStudyCalendar', {nickName: '테스트 봇1', year: 2023, month: 1});

        let studyCalendar = new Array(32).fill(0);
        let data = this.$store.state.moduleTimer.studyCalendar;
        for (let i = 0; i < data.length; i++) {
            studyCalendar[parseInt(data[i].day)] = data[i].second;
        }

        this.studyCalendar = studyCalendar;
    }
};
</script>
