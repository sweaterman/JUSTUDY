<template>
    <v-app>
        <v-row :style="{marginTop: '2%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <!-- 타이머 메뉴 바 -->
                <v-row justify="center" align="center">
                    <v-col cols="12" md="4" justify="center" align="center">
                        <router-link to="/timer/study-start" style="text-decoration: none; color: #ffb000">
                            <div :style="{fontWeight: 'bold', fontSize: 'x-large'}"><h2>공부 시작</h2></div>
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="4" justify="center" align="center">
                        <router-link to="/timer/my-statistics" style="text-decoration: none; color: black">
                            <div><h2>나의 통계</h2></div>
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="4" justify="center" align="center">
                        <router-link to="/timer/friend-statistics" style="text-decoration: none; color: black">
                            <div><h2>친구 통계</h2></div>
                        </router-link>
                    </v-col>
                </v-row>

                <!-- 카메라 화면 - 미구현 -->
                <TimerCamera />

                <!-- 타이머 구현 -->
                <v-row :style="{marginTop: '5%'}">
                    <hr width="100%" />
                    <v-col cols="12" md="2" justify="center" align="center">
                        <v-img :src="require('@/assets/fire3.gif')" :style="`width: 1200; height: 1000; `" />
                    </v-col>
                    <v-col cols="12" md="8" justify="center" align="center">
                        <DigitalClock :allTime="allTimeMe" :fontSize="150" />
                    </v-col>
                    <v-col cols="12" md="2" justify="center" align="center">
                        <v-img :src="require('@/assets/fire3.gif')" :style="`width: 1200; height: 1000; `" />
                    </v-col>
                    <hr width="100%" />
                </v-row>

                <!-- 기록 내용 -->
                <v-row :style="{marginTop: '6%'}">
                    <!-- 캠프파이어 -->
                    <v-col cols="12" md="4" :style="{marginTop: '3%'}">
                        <v-img :src="require('@/assets/campfire.gif')" max-height="600" max-width="600" />
                    </v-col>
                    <!-- 본 내용 -->
                    <v-col cols="12" md="8" :style="{marginBottom: '11%'}">
                        <!-- 내용 - 공부왕 -->
                        <v-row>
                            <v-col cols="12" md="6" justify="center" align="center">
                                <v-img :src="require('@/assets/king.gif')" max-height="250" max-width="250" />
                                <div :style="{fontWeight: 'bold', fontSize: 'large'}">
                                    <h1>
                                        <span style="color: black">오늘의 </span>
                                        <span style="color: #ffb000">공부</span>
                                        <span style="color: black">왕</span>
                                    </h1>
                                </div>
                            </v-col>
                            <v-col cols="12" md="6" align="right">
                                <div :style="{fontWeight: 'bold', fontSize: 'x-large'}">
                                    <h1>
                                        <span style="color: #ffb000">{{ king_name }} </span>
                                    </h1>
                                </div>
                                <div :style="{fontWeight: 'bold', fontSize: 'xxx-large'}">
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
                            </v-col>
                        </v-row>
                        <!-- 내용 - 오늘 접속자 -->
                        <v-row :style="{marginTop: '-3%'}">
                            <v-col cols="12" md="8" justify="center" align="center">
                                <v-img :src="require('@/assets/people.gif')" max-height="300" max-width="500" />
                                <div :style="{fontWeight: 'bold', fontSize: 'x-large'}">
                                    <h2>
                                        <span style="color: black">지금 공부 중인 </span>
                                        <span style="color: #ffb000">SSAFY</span>
                                        <span style="color: black">교육생</span>
                                    </h2>
                                </div>
                            </v-col>
                            <v-col cols="12" md="4" justify="center" align="center" :style="{marginTop: '7%'}">
                                <div :style="{fontWeight: 'bold', fontSize: 'xxx-large'}">
                                    <h1>
                                        <span style="color: #ffb000">254</span>
                                        <span style="color: black">명</span>
                                    </h1>
                                </div>
                            </v-col>
                        </v-row>
                    </v-col>
                </v-row>
            </v-col>

            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>
<script>
import DigitalClock from '@/components/timer/DigitalClock.vue';
import TimerCamera from '../../components/timer/TimerCamera.vue';
export default {
    name: 'StudyStart',
    data() {
        return {
            king_name: '돌로스원숭숭',
            king_time: '00:53:23',
            allTimeMe: 0,
            allTimeFirst: 9000,
            today: '',
            firstYesterday: this.$store.state.moduleTimer.firstYesterday
        };
    },

    components: {
        DigitalClock,
        TimerCamera
    },
    async created() {
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
