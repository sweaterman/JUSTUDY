<template>
    <v-app>
        <v-row :style="{marginTop: '0.5%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <!-- 타이머 메뉴 바 -->
                <v-row justify="center" align="center">
                    <v-col cols="12" md="6" justify="center" align="center">
                        <router-link to="/timer/study-start" style="text-decoration: none; color: black">
                            <span class="material-icons-outlined"> timer </span>
                            <div>타이머</div>
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="6" justify="center" align="center">
                        <router-link to="/timer/friend-statistics" style="text-decoration: none; color: #ffb000">
                            <span class="material-icons-outlined"> show_chart </span>
                            <div>통계</div>
                        </router-link>
                    </v-col>
                </v-row>

                <!-- 달력 -->
                <v-row>
                    <v-col cols="12" md="6">
                        <div class="card_section">
                            <Follow buttonContent="팔로잉" />
                        </div>
                    </v-col>
                    <v-col cols="12" md="6">
                        <div class="card_section">
                            <Follow buttonContent="팔로우" />
                        </div>
                    </v-col>
                </v-row>

                <v-row>
                    <!-- 해당 친구 프로필 -->
                    <v-col cols="12" md="4">
                        <div class="card_section">
                            <v-img :src="require('@/assets/juniorClass.png')" :style="{width: '200px', height: '200px'}" rounded v-on:click="dialogChange()" />
                            <Profile :diameter="200" standard="px" @dialogChangeFromChild="dialogChange()" :src="require('@/assets/juniorClass.png')" />
                            이싸피
                            <img src="../../assets/redHeart.png" />
                        </div>
                    </v-col>
                    <!-- 나의 공부 시간 -->
                    <v-col cols="12" md="4">
                        <v-row>
                            <v-col>
                                <DigitalClockPerDate content="이번주 공부 시간" :allTime="weekTime" />
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col>
                                <DigitalClockPerDate content="이번달 공부 시간" :allTime="monthTime" />
                            </v-col>
                        </v-row>
                    </v-col>
                    <!-- 나의 평균 비교 -->
                    <v-col cols="12" md="4">
                        <v-row>
                            <v-col>
                                <DigitalClockAverage content="THIS WEEK" :studyTime="weekTime - averageWeekTime" :averageTime="averageWeekTime" />
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col>
                                <DigitalClockAverage content="THIS MONTH" :studyTime="monthTime - averageMonthTime" :averageTime="averageMonthTime" />
                            </v-col>
                        </v-row>
                    </v-col>
                </v-row>

                <!-- 나의 과목 비교 -->
                <!-- <v-row>
                    <v-col cols="12" md="4">
                        <TimeAndRadarChart :studyCategory="studyCategory" />
                    </v-col>
                </v-row> -->
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>
<script>
import Follow from '@/components/common/Follow.vue';
import Profile from '@/components/mypage/Profile.vue';
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
        Follow,
        Profile,
        DigitalClockPerDate,
        DigitalClockAverage
    },
    async created() {
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
    }
};
</script>
<style scoped>
.card_section {
    padding: 10px;
    margin-bottom: 20px;
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
</style>
