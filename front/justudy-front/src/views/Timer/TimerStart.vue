<template>
    <v-app>
        <v-row :style="{marginTop: '0.5%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <!-- <TimerHeader /> -->

                <!-- 타이머 메뉴 바 -->
                <v-row justify="center" align="center">
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
                <TimerCamera />

                <!-- 기록 내용 -->
                <v-row>
                    <!-- 캠프파이어 -->
                    <!-- <v-col cols="12" md="2" justify="center" align="center">
                        <v-img :src="require('@/assets/campfire.gif')" max-height="160px" max-width="160px" />
                    </v-col> -->

                    <!-- 본 내용 -->
                    <v-col cols="12" md="6">
                        <!-- 내용 - 공부왕 -->
                        <div class="card_section">
                            <v-row>
                                <v-col cols="12" md="4" justify="center" align="center">
                                    <v-img :src="require('@/assets/king.gif')" max-height="100%" />
                                </v-col>
                                <v-col cols="12" md="8" justify="center" align="center">
                                    <v-row justify="center" align="center">
                                        <div :style="{fontWeight: 'bold', fontSize: 'large'}">
                                            <h2>
                                                <span style="color: black">오늘의 </span>
                                                <span style="color: #ffb000">공부</span>
                                                <span style="color: black">왕</span>
                                            </h2>
                                        </div>
                                    </v-row>
                                    <v-row justify="center" align="center">
                                        <div :style="{fontWeight: 'bold', fontSize: 'large'}">
                                            <h2>
                                                <span style="color: #ffb000">{{ king_name }} </span>
                                            </h2>
                                        </div>
                                        <div :style="{fontWeight: 'bold', fontSize: 'large'}"><h3>님!</h3></div>
                                    </v-row>
                                    <v-row justify="center" align="center">
                                        <div :style="{fontWeight: 'bold', fontSize: 'large'}">
                                            <h1>
                                                <span style="color: black"
                                                    >{{ parseInt(firstYesterday.second / 3600) >= 10 ? parseInt(firstYesterday.second / 3600) : '0' + parseInt(firstYesterday.second / 3600) }}:{{
                                                        parseInt((firstYesterday.second % 3600) / 60) >= 10
                                                            ? parseInt((firstYesterday.second % 3600) / 60)
                                                            : '0' + parseInt((firstYesterday.second % 3600) / 60)
                                                    }}:{{ firstYesterday.second % 60 >= 10 ? firstYesterday.second % 60 : '0' + (firstYesterday.second % 60) }}</span
                                                >
                                            </h1>
                                        </div>
                                    </v-row>
                                </v-col>
                            </v-row>
                        </div>
                    </v-col>

                    <!-- 내용 - 오늘 접속자 -->
                    <v-col cols="12" md="6">
                        <div class="card_section">
                            <v-row>
                                <v-img :src="require('@/assets/people.gif')" max-height="30" max-width="30" />
                            </v-row>
                            <v-row>
                                <div :style="{fontWeight: 'bold', fontSize: 'large'}">
                                    <h2>
                                        <span style="color: black">지금 공부 중인 </span>
                                        <span style="color: #ffb000">SSAFY</span>
                                        <span style="color: black">교육생</span>
                                    </h2>
                                </div>
                            </v-row>
                            <v-row>
                                <div :style="{fontWeight: 'bold', fontSize: 'large'}">
                                    <h1>
                                        <span style="color: #ffb000">254</span>
                                        <span style="color: black">명</span>
                                    </h1>
                                </div>
                            </v-row>
                        </div>
                    </v-col>
                </v-row>

                <!-- 나의 공부 시간 -->
                <v-row>
                    <v-col cols="12" md="6">
                        <DigitalClockPerDate content="이번주 공부 시간" :allTime="weekTime" />
                    </v-col>
                    <v-col cols="12" md="6">
                        <DigitalClockPerDate content="이번달 공부 시간" :allTime="monthTime" />
                    </v-col>
                </v-row>

                <v-row> </v-row>
            </v-col>

            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>
<script>
// import TimerHeader from '../../components/timer/TimerHeader.vue';
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
            studyCalendar: []
        };
    },

    components: {
        // DigitalClockAverage,
        // TimerHeader,
        DigitalClockPerDate,
        TimerCamera
    },
    async created() {
        // 타이머 정보 부분
        await this.$store.dispatch('moduleTimer/getStudyTimeWeek', {seq: 50});
        this.weekTime = this.$store.state.moduleTimer.studyTimeWeek.time;
        await this.$store.dispatch('moduleTimer/getStudyTimeMonth', {seq: 50});
        this.monthTime = this.$store.state.moduleTimer.studyTimeMonth.time;
        await this.$store.dispatch('moduleTimer/getAverageMembersWeek');
        this.averageWeekTime = this.$store.state.moduleTimer.averageMemberWeek.time;
        await this.$store.dispatch('moduleTimer/getAverageMembersMonth');
        this.averageMonthTime = this.$store.state.moduleTimer.averageMemberMonth.time;
        await this.$store.dispatch('moduleTimer/getStudyCategory', {seq: 50});
        this.studyCategory = this.$store.state.moduleTimer.studyCategory;
        await this.$store.dispatch('moduleTimer/getStudyCalendar', {seq: 50, year: 2023, month: 1});

        let studyCalendar = new Array(32).fill(0);
        let data = this.$store.state.moduleTimer.studyCalendar;
        for (let i = 0; i < data.length; i++) {
            studyCalendar[parseInt(data[i].day)] = data[i].second;
        }

        this.studyCalendar = studyCalendar;

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
