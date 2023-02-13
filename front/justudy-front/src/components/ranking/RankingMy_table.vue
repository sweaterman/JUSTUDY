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
                            <span style="color: #ffb000">공부</span>
                            <span style="color: black">왕!</span>
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
                            :src="require('@/assets/justudy.png')"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #ffb000">{{ rankMemberYesterday[0].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankMemberYesterday[0].time" :fontSize="30" />
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
                        <!-- <ProfilePicture
                            :diameter="30"
                            :height="70"
                            :fontSize="32"
                            content="LV"
                            standard="px"
                            
                            :src="`${port}images/${rankMemberYesterday[0].img}`"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        /> -->
                        <ProfilePicture
                            :diameter="70"
                            :height="70"
                            :fontSize="32"
                            content="LV"
                            standard="px"
                            :src="require('@/assets/justudy.png')"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #bbbbbb">{{ rankMemberYesterday[1].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankMemberYesterday[1].time" :fontSize="30" />
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
                            :src="require('@/assets/justudy.png')"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #503000">{{ rankMemberYesterday[2].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankMemberYesterday[2].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <!-- 오늘 버닝 데이터 TOP10 -->
                <v-row justify="center" align="center">
                    <v-col>
                        <v-row class="card_section_each" :key="index" v-for="(value, index) in rankMemberYesterday">
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
                            <span style="color: black">이번주 </span>
                            <span style="color: #ffb000">공부</span>
                            <span style="color: black">왕!</span>
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
                            :src="require('@/assets/justudy.png')"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #ffb000">{{ rankMemberWeek[0].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankMemberWeek[0].time" :fontSize="30" />
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
                            :src="require('@/assets/justudy.png')"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #bbbbbb">{{ rankMemberWeek[1].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankMemberWeek[1].time" :fontSize="30" />
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
                            :src="require('@/assets/justudy.png')"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #503000">{{ rankMemberWeek[2].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankMemberWeek[2].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <!-- 이번주 버닝 데이터 TOP10 -->
                <v-row justify="center" align="center">
                    <v-col>
                        <v-row class="card_section_each" :key="index" v-for="(value, index) in rankMemberWeek">
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
                            <span style="color: black">이번달 </span>
                            <span style="color: #ffb000">공부</span>
                            <span style="color: black">왕!</span>
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
                            :src="require('@/assets/justudy.png')"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #ffb000">{{ rankMemberMonth[0].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankMemberMonth[0].time" :fontSize="30" />
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
                            :src="require('@/assets/justudy.png')"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #bbbbbb">{{ rankMemberMonth[1].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankMemberMonth[1].time" :fontSize="30" />
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
                            :src="require('@/assets/justudy.png')"
                            style="padding: 5%"
                            justify="center"
                            align="center"
                        />
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="4">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #503000">{{ rankMemberMonth[2].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankMemberMonth[2].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <!-- 이번달 버닝 데이터 TOP10 -->
                <v-row justify="center" align="center">
                    <v-col>
                        <v-row class="card_section_each" :key="index" v-for="(value, index) in rankMemberMonth">
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
