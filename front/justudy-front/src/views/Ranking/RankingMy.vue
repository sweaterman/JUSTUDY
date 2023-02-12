<template>
    <v-app>
        <v-row :style="{marginTop: '2%', marginBottom: '5%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <!-- 랭킹 메뉴 바 -->
                <v-row justify="center" align="center">
                    <v-col cols="12" md="3" />
                    <v-col cols="12" md="3" justify="center" align="center">
                        <router-link to="/ranking/my-ranking" style="text-decoration: none; color: #ffb000">
                            <div :style="{fontWeight: 'bold', fontSize: 'x-large'}"><h2>개인 랭킹</h2></div>
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="3" justify="center" align="center">
                        <router-link to="/ranking/study-ranking" style="text-decoration: none; color: black">
                            <div><h2>스터디 랭킹</h2></div>
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="3" />
                </v-row>

                <!-- 전날? 오늘? 버닝 -->
                <!-- 전날 버닝 상단 -->
                <v-row :style="{marginTop: '5%'}">
                    <v-row>
                        <h1>
                            1. 오늘의 버닝 TOP 10
                            <hr style="width: 160%" />
                        </h1>
                    </v-row>
                    <!-- 전날 버닝 데이터 1등 -->
                    <v-row>
                        <v-col cols="12" md="4">
                            <v-row :style="{marginTop: '-3%'}">
                                <v-col align="left">
                                    <div :style="{fontSize: 'large'}">
                                        <h2>
                                            <span style="color: black">오늘의 </span>
                                            <span style="color: #ffb000">공부</span>
                                            <span style="color: black">왕!</span>
                                        </h2>
                                    </div>
                                </v-col>
                            </v-row>

                            <v-row justify="center" align="center">
                                <v-col>
                                    <!-- 받아온 이미지 src에 넣기 -->
                                    <ProfilePicture
                                        :diameter="230"
                                        :height="70"
                                        :fontSize="32"
                                        content="LV"
                                        standard="px"
                                        :src="`${port}images/${rankMemberYesterday[0].img}`"
                                        style="padding: 5%"
                                        justify="center"
                                        align="center"
                                    />
                                </v-col>
                            </v-row>

                            <v-row :style="{marginTop: '-6%'}">
                                <v-col cols="12" md="8" align="left">
                                    <div :style="{fontWeight: 'bold', fontSize: 'large'}">
                                        <h1 class="line_limit">
                                            <span style="color: #ffb000">{{ rankMemberYesterday[0].nickName }}</span>
                                        </h1>
                                    </div>
                                </v-col>
                                <v-col cols="12" md="2" align="left">
                                    <div :style="{fontWeight: 'bold', fontSize: 'large'}">
                                        <h1>
                                            <span style="color: black">님!</span>
                                        </h1>
                                    </div>
                                </v-col>
                                <v-col cols="12" md="2" />
                            </v-row>
                            <v-row justify="center" align="center" :style="{marginTop: '-9%'}">
                                <DigitalClock :allTime="rankMemberYesterday[0].time" :fontSize="100" />
                            </v-row>
                        </v-col>

                        <!-- 전날 버닝 데이터 TOP10 -->
                        <v-col cols="12" md="8" justify="center" align="center" :style="{paddingLeft: '5%'}">
                            <v-simple-table style="width: 1500px">
                                <tbody>
                                    <tr :key="index" v-for="(value, index) in rankMemberYesterday">
                                        <td style="width: 10px">
                                            <div class="line_limit">
                                                {{ value.order }}
                                            </div>
                                        </td>
                                        <td :style="{fontWeight: 'bold', fontSize: 'large'}">
                                            <div class="line_limit">
                                                {{ value.nickName }}
                                            </div>
                                        </td>
                                        <td>
                                            <DigitalClock :allTime="value.time" :fontSize="20" />
                                        </td>
                                    </tr>
                                </tbody>
                            </v-simple-table>
                        </v-col>
                    </v-row>
                </v-row>

                <!-- <RankingForm category="오늘의" :componentData="'@/data/MyRankingData'" /> -->
                <!-- 이번주 버닝 -->
                <!-- 이번주 버닝 상단 -->
                <v-row :style="{marginTop: '8%'}">
                    <v-row>
                        <h1>
                            2. 이번주 버닝 TOP 10
                            <hr style="width: 160%" />
                        </h1>
                    </v-row>
                    <!-- 이번주 버닝 데이터 1등 -->
                    <v-row>
                        <v-col cols="12" md="4">
                            <v-row :style="{marginTop: '-3%'}">
                                <v-col align="left">
                                    <div :style="{fontSize: 'large'}">
                                        <h2>
                                            <span style="color: black">이번주의 </span>
                                            <span style="color: #ffb000">공부</span>
                                            <span style="color: black">왕!</span>
                                        </h2>
                                    </div>
                                </v-col>
                            </v-row>

                            <v-row justify="center" align="center">
                                <v-col>
                                    <!-- 받아온 이미지 src에 넣기 -->
                                    <ProfilePicture
                                        :diameter="230"
                                        :height="70"
                                        :fontSize="32"
                                        content="LV"
                                        standard="px"
                                        :src="`${port}images/${rankMemberWeek[0].img}`"
                                        style="padding: 5%"
                                        justify="center"
                                        align="center"
                                    />
                                </v-col>
                            </v-row>

                            <v-row :style="{marginTop: '-6%'}">
                                <v-col cols="12" md="8" align="left">
                                    <div :style="{fontWeight: 'bold', fontSize: 'large'}">
                                        <h1 class="line_limit">
                                            <span style="color: #ffb000">{{ rankMemberWeek[0].nickName }}</span>
                                        </h1>
                                    </div>
                                </v-col>
                                <v-col cols="12" md="2" align="left">
                                    <div :style="{fontWeight: 'bold', fontSize: 'large'}">
                                        <h1>
                                            <span style="color: black">님!</span>
                                        </h1>
                                    </div>
                                </v-col>
                                <v-col cols="12" md="2" />
                            </v-row>
                            <v-row justify="center" align="center" :style="{marginTop: '-9%'}">
                                <DigitalClock :allTime="rankMemberWeek[0].time" :fontSize="100" />
                            </v-row>
                        </v-col>

                        <!-- 이번주 버닝 데이터 TOP10 -->
                        <v-col cols="12" md="8" justify="center" align="center" :style="{paddingLeft: '5%'}">
                            <v-simple-table style="width: 1500px">
                                <tbody>
                                    <tr :key="index" v-for="(value, index) in rankMemberWeek">
                                        <td style="width: 10px">
                                            <div class="line_limit">
                                                {{ value.order }}
                                            </div>
                                        </td>
                                        <td :style="{fontWeight: 'bold', fontSize: 'large'}">
                                            <div class="line_limit">
                                                {{ value.nickName }}
                                            </div>
                                        </td>
                                        <td>
                                            <DigitalClock :allTime="value.time" :fontSize="20" />
                                        </td>
                                    </tr>
                                </tbody>
                            </v-simple-table>
                        </v-col>
                    </v-row>
                </v-row>

                <!-- 이번달 버닝 -->
                <!-- 이번달 버닝 상단 -->
                <v-row :style="{marginTop: '8%'}">
                    <v-row>
                        <h1>
                            3. 이번달 버닝 TOP 10
                            <hr style="width: 160%" />
                        </h1>
                    </v-row>
                    <!-- 이번달 버닝 데이터 1등 -->
                    <v-row>
                        <v-col cols="12" md="4">
                            <v-row :style="{marginTop: '-3%'}">
                                <v-col align="left">
                                    <div :style="{fontSize: 'large'}">
                                        <h2>
                                            <span style="color: black">이번달의 </span>
                                            <span style="color: #ffb000">공부</span>
                                            <span style="color: black">왕!</span>
                                        </h2>
                                    </div>
                                </v-col>
                            </v-row>

                            <v-row justify="center" align="center">
                                <v-col>
                                    <!-- 받아온 이미지 src에 넣기 -->
                                    <ProfilePicture
                                        :diameter="230"
                                        :height="70"
                                        :fontSize="32"
                                        content="LV"
                                        standard="px"
                                        :src="`${port}images/${rankMemberMonth[0].img}`"
                                        style="padding: 5%"
                                        justify="center"
                                        align="center"
                                    />
                                </v-col>
                            </v-row>

                            <v-row :style="{marginTop: '-6%'}">
                                <v-col cols="12" md="8" align="left">
                                    <div :style="{fontWeight: 'bold', fontSize: 'large'}">
                                        <h1 class="line_limit">
                                            <span style="color: #ffb000">{{ rankMemberMonth[0].nickName }}</span>
                                        </h1>
                                    </div>
                                </v-col>
                                <v-col cols="12" md="2" align="left">
                                    <div :style="{fontWeight: 'bold', fontSize: 'large'}">
                                        <h1>
                                            <span style="color: black">님!</span>
                                        </h1>
                                    </div>
                                </v-col>
                                <v-col cols="12" md="2" />
                            </v-row>
                            <v-row justify="center" align="center" :style="{marginTop: '-9%'}">
                                <DigitalClock :allTime="rankMemberMonth[0].time" :fontSize="100" />
                            </v-row>
                        </v-col>

                        <!-- 이번달 버닝 데이터 TOP10 -->
                        <v-col cols="12" md="8" justify="center" align="center" :style="{paddingLeft: '5%'}">
                            <v-simple-table style="width: 1500px">
                                <tbody>
                                    <tr :key="index" v-for="(value, index) in rankMemberMonth">
                                        <td style="width: 10px">
                                            <div class="line_limit">
                                                {{ value.order }}
                                            </div>
                                        </td>
                                        <td :style="{fontWeight: 'bold', fontSize: 'large'}">
                                            <div class="line_limit">
                                                {{ value.nickName }}
                                            </div>
                                        </td>
                                        <td>
                                            <DigitalClock :allTime="value.time" :fontSize="20" />
                                        </td>
                                    </tr>
                                </tbody>
                            </v-simple-table>
                        </v-col>
                    </v-row>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>
<script>
import MyRankingData from '@/data/MyRankingData';
import ProfilePicture from '@/components/mypage/ProfilePicture.vue';
import DigitalClock from '@/components/timer/DigitalClock.vue';
import port from '@/store/port';
// import RankingForm from '../../components/timer/RankingForm.vue';
export default {
    name: 'MyRanking',
    data() {
        return {
            data: MyRankingData,
            rankMemberYesterday: [],
            rankMemberWeek: [],
            rankMemberMonth: [],
            port: port
        };
    },

    components: {
        ProfilePicture,
        // RankingForm,
        DigitalClock
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
.line_limit {
    width: 40px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    display: inline-block;
}
</style>
