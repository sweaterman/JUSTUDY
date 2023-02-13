<template>
    <v-app>
        <v-row>
            <v-col class="card_section_board" cols="12" md="4">
                <!-- 오늘 버닝 상단 -->
                <v-row>
                    <v-col><h1>오늘의 버닝 TOP 10</h1></v-col>
                </v-row>

                <!-- 오늘 버닝 데이터 1등 -->
                <v-row>
                    <v-col>
                        <h3>
                            <span style="color: black">오늘의 </span>
                            <span style="color: #ffb000">스터디</span>
                        </h3>
                    </v-col>
                </v-row>

                <v-row class="card_section_1st" justify="center" align="center">
                    <v-col cols="12" md="2">
                        <v-avatar size="40">
                            <v-img :src="require('@/assets/1st.png')" />
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2">
                        <!-- 받아온 이미지 src에 넣기 -->
                        <ProfilePicture
                            :diameter="70"
                            :height="70"
                            :fontSize="32"
                            content="LV"
                            standard="px"
                            :src="`${port}images/${rankStudyYesterday[0].img}`"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #ffb000">{{ rankStudyYesterday[0].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankStudyYesterday[0].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <v-row class="card_section_1st" justify="center" align="center">
                    <v-col cols="12" md="2">
                        <v-avatar size="40">
                            <v-img :src="require('@/assets/2nd.png')" />
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2">
                        <ProfilePicture
                            :diameter="70"
                            :height="70"
                            :fontSize="32"
                            content="LV"
                            standard="px"
                            :src="`${port}images/${rankStudyYesterday[1].img}`"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #bbbbbb">{{ rankStudyYesterday[1].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankStudyYesterday[1].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <v-row class="card_section_1st" justify="center" align="center">
                    <v-col cols="12" md="2">
                        <v-avatar size="40">
                            <v-img :src="require('@/assets/3rd.png')" />
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2">
                        <!-- 받아온 이미지 src에 넣기 -->
                        <ProfilePicture
                            :diameter="70"
                            :height="70"
                            :fontSize="32"
                            content="LV"
                            standard="px"
                            :src="`${port}images/${rankStudyYesterday[2].img}`"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #503000">{{ rankStudyYesterday[2].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankStudyYesterday[2].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <!-- 오늘 버닝 데이터 TOP10 -->
                <v-row justify="center" align="center">
                    <v-col>
                        <v-row class="card_section_each" :key="index" v-for="(value, index) in rankStudyYesterday">
                            <v-col cols="12" md="2"> {{ value.order + 2 }} </v-col>
                            <v-col class="line_limit_ranking" cols="12" md="6">
                                {{ value.nickName }}
                            </v-col>
                            <v-col cols="12" md="4">
                                <DigitalClock :allTime="value.time" :fontSize="25" />
                            </v-col>
                        </v-row>
                    </v-col>
                </v-row>
            </v-col>

            <v-col class="card_section_board" cols="12" md="4">
                <!-- 이번주 버닝 상단 -->
                <v-row>
                    <v-col><h1>이번주 버닝 TOP 10</h1></v-col>
                </v-row>

                <!-- 이번주 버닝 데이터 1등 -->
                <v-row>
                    <v-col>
                        <h3>
                            <span style="color: black">이번주의 </span>
                            <span style="color: #ffb000">스터디</span>
                        </h3>
                    </v-col>
                </v-row>

                <v-row class="card_section_1st" justify="center" align="center">
                    <v-col cols="12" md="2">
                        <v-avatar size="40">
                            <v-img :src="require('@/assets/1st.png')" />
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2">
                        <!-- 받아온 이미지 src에 넣기 -->
                        <ProfilePicture
                            :diameter="70"
                            :height="70"
                            :fontSize="32"
                            content="LV"
                            standard="px"
                            :src="`${port}images/${rankStudyWeek[0].img}`"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #ffb000">{{ rankStudyWeek[0].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankStudyWeek[0].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <v-row class="card_section_1st" justify="center" align="center">
                    <v-col cols="12" md="2">
                        <v-avatar size="40">
                            <v-img :src="require('@/assets/2nd.png')" />
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2">
                        <!-- 받아온 이미지 src에 넣기 -->
                        <ProfilePicture
                            :diameter="70"
                            :height="70"
                            :fontSize="32"
                            content="LV"
                            standard="px"
                            :src="`${port}images/${rankStudyWeek[1].img}`"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #bbbbbb">{{ rankStudyWeek[1].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankStudyWeek[1].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <v-row class="card_section_1st" justify="center" align="center">
                    <v-col cols="12" md="2">
                        <v-avatar size="40">
                            <v-img :src="require('@/assets/3rd.png')" />
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2">
                        <!-- 받아온 이미지 src에 넣기 -->
                        <ProfilePicture
                            :diameter="70"
                            :height="70"
                            :fontSize="32"
                            content="LV"
                            standard="px"
                            :src="`${port}images/${rankStudyWeek[2].img}`"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #503000">{{ rankStudyWeek[2].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankStudyWeek[2].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <!-- 이번주 버닝 데이터 TOP10 -->
                <v-row justify="center" align="center">
                    <v-col>
                        <v-row class="card_section_each" :key="index" v-for="(value, index) in rankStudyWeek">
                            <v-col cols="12" md="2"> {{ value.order + 2 }} </v-col>
                            <v-col class="line_limit_ranking" cols="12" md="6">
                                {{ value.nickName }}
                            </v-col>
                            <v-col cols="12" md="4">
                                <DigitalClock :allTime="value.time" :fontSize="25" />
                            </v-col>
                        </v-row>
                    </v-col>
                </v-row>
            </v-col>

            <!-- 이번달 버닝 TOP 10 -->
            <v-col class="card_section_board" cols="12" md="4">
                <v-row>
                    <v-col><h1>이번달 버닝 TOP 10</h1></v-col>
                </v-row>

                <!-- 이번달 버닝 데이터 1등 -->
                <v-row>
                    <v-col>
                        <h3>
                            <span style="color: black">이번달의 </span>
                            <span style="color: #ffb000">스터디</span>
                        </h3>
                    </v-col>
                </v-row>

                <v-row class="card_section_1st" justify="center" align="center">
                    <v-col cols="12" md="2">
                        <v-avatar size="40">
                            <v-img :src="require('@/assets/1st.png')" />
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2">
                        <!-- 받아온 이미지 src에 넣기 -->
                        <ProfilePicture
                            :diameter="70"
                            :height="70"
                            :fontSize="32"
                            content="LV"
                            standard="px"
                            :src="`${port}images/${rankStudyMonth[0].img}`"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #ffb000">{{ rankStudyMonth[0].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankStudyMonth[0].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <v-row class="card_section_1st" justify="center" align="center">
                    <v-col cols="12" md="2">
                        <v-avatar size="40">
                            <v-img :src="require('@/assets/2nd.png')" />
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2">
                        <!-- 받아온 이미지 src에 넣기 -->
                        <ProfilePicture
                            :diameter="70"
                            :height="70"
                            :fontSize="32"
                            content="LV"
                            standard="px"
                            :src="`${port}images/${rankStudyMonth[1].img}`"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #bbbbbb">{{ rankStudyMonth[1].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankStudyMonth[1].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <v-row class="card_section_1st" justify="center" align="center">
                    <v-col cols="12" md="2">
                        <v-avatar size="40">
                            <v-img :src="require('@/assets/3rd.png')" />
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2">
                        <!-- 받아온 이미지 src에 넣기 -->
                        <ProfilePicture
                            :diameter="70"
                            :height="70"
                            :fontSize="32"
                            content="LV"
                            standard="px"
                            :src="`${port}images/${rankStudyMonth[2].img}`"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #503000">{{ rankStudyMonth[2].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankStudyMonth[2].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <!-- 이번달 버닝 데이터 TOP10 -->
                <v-row justify="center" align="center">
                    <v-col>
                        <v-row class="card_section_each" :key="index" v-for="(value, index) in rankStudyMonth">
                            <v-col cols="12" md="2"> {{ value.order + 2 }} </v-col>
                            <v-col class="line_limit_ranking" cols="12" md="6">
                                {{ value.nickName }}
                            </v-col>
                            <v-col cols="12" md="4">
                                <DigitalClock :allTime="value.time" :fontSize="25" />
                            </v-col>
                        </v-row>
                    </v-col>
                </v-row>
            </v-col>
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
    name: 'MyRanking_table',
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
        ProfilePicture,
        // RankingForm,
        DigitalClock
        // DigitalClockRankingFom
    },
    async created() {
        await this.$store.dispatch('moduleRanking/getRankMemberYesterday');
        this.rankStudyYesterday = this.$store.state.moduleRanking.rankStudyYesterday;
        await this.$store.dispatch('moduleRanking/getRankMemberWeek');
        this.rankStudyWeek = this.$store.state.moduleRanking.rankStudyWeek;
        await this.$store.dispatch('moduleRanking/getRankMemberMonth');
        this.rankStudyMonth = this.$store.state.moduleRanking.rankStudyMonth;
    }
};
</script>
<style>
.line_limit_ranking {
    width: 95px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    display: inline-block;
}
.simple_table {
    min-width: 500px !important;
}
.card_section_board {
    padding: 30px;
    height: 1200px;
    /* margin-bottom: 20px; */
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
.card_section_each {
    padding: 5px;
    margin-bottom: 10px;
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
.card_section_1st {
    padding: 5px;
    height: 90px;
    margin-bottom: 10px;
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
</style>
