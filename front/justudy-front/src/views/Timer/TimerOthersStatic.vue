<template>
    <v-app>
        <v-row :style="{marginTop: '2%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <!-- 타이머 메뉴 바 -->
                <v-row justify="center" align="center">
                    <v-col cols="12" md="4" justify="center" align="center">
                        <router-link to="/timer/study-start" style="text-decoration: none; color: black">
                            <div><h2>공부 시작</h2></div>
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="4" justify="center" align="center">
                        <router-link to="/timer/my-statistics" style="text-decoration: none; color: black">
                            <div><h2>나의 통계</h2></div>
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="4" justify="center" align="center">
                        <router-link to="/timer/friend-statistics" style="text-decoration: none; color: #ffb000">
                            <div :style="{fontWeight: 'bold', fontSize: 'large'}"><h2>친구 통계</h2></div>
                        </router-link>
                    </v-col>
                </v-row>
                <v-row>
                    <Follow buttonContent="팔로잉" />
                    <Follow buttonContent="팔로우" />
                </v-row>

                <!-- 나의 공부 시간 -->
                <v-row :style="{marginTop: '5%', marginBottom: '1%'}">
                    <v-col cols="12" md="4" align="left">
                        <h1>친구의 공부 시간</h1>
                        <hr />
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12" md="6">
                        <DigitalClockPerDate content="THIS WEEK" />
                    </v-col>
                    <v-col cols="12" md="6">
                        <DigitalClockPerDate content="THIS MONTH" />
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
                        <DigitalClockAverage content="THIS WEEK" :studyTime="15" />
                    </v-col>
                    <v-col>
                        <DigitalClockAverage content="THIS MONTH" :studyTime="-15" />
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
                        <TimeAndRadarChart />
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>
<script>
import Follow from '@/components/common/Follow.vue';
import TimeAndRadarChart from '@/components/timer/TimeAndRadarChart.vue';
import DigitalClockPerDate from '@/components/timer/DigitalClockPerDate.vue';
import DigitalClockAverage from '@/components/timer/DigitalClockAverage.vue';

export default {
    name: 'FriendStatistics',
    components: {
        Follow,
        TimeAndRadarChart,
        DigitalClockPerDate,
        DigitalClockAverage
    }
};
</script>
