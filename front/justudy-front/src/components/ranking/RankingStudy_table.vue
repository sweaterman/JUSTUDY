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
<!-- 
                <v-row class="card_section_1st" justify="center" align="center" @click="moveToStudy(rankStudyYesterday[0])" >
                    <v-col cols="12" md="2">
                        <v-avatar size="40">
                            <v-img :src="require('@/assets/1st.png')" />
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2"> -->
                        <!-- 받아온 이미지 src에 넣기 -->
                        <!-- <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankStudyYesterday[0].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <span align-self="center" class="line_limit_ranking" style="color: #ffb000; vertical-align: middle;">{{ rankStudyYesterday[0].nickName }}</span>
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
                        <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankStudyYesterday[1].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #bbbbbb; vertical-align: middle;">{{ rankStudyYesterday[1].nickName }}</span>
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
                    <v-col cols="12" md="2"> -->
                        <!-- 받아온 이미지 src에 넣기 -->
                        <!-- <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankStudyYesterday[2].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #503000; vertical-align: middle;">{{ rankStudyYesterday[2].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankStudyYesterday[2].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row> -->

                <!-- 오늘 버닝 데이터 TOP10 -->
                <v-row justify="center" align="center">
                    <v-col>
                        <v-row class="card_section_each" :key="index" v-for="(value, index) in rankStudyYesterday.slice(3)">
                            <v-col  align-self="center" cols="12" md="2"> {{ value.order }} </v-col>
                            <v-col  align-self="center" class="line_limit_ranking" cols="12" md="6">
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

                <v-row class="card_section_1st" justify="center" align="center" @click="moveToStudy(rankStudyWeek[0].seq)">
                    <v-col cols="12" md="2">
                        <v-avatar size="40">
                            <v-img :src="require('@/assets/1st.png')" />
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2">
                        <!-- 받아온 이미지 src에 넣기 -->
                        <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankStudyWeek[0].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #ffb000; vertical-align: middle;">{{ rankStudyWeek[0].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankStudyWeek[0].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <v-row class="card_section_1st" justify="center" align="center" @click="moveToStudy(rankStudyWeek[1].seq)">
                    <v-col cols="12" md="2">
                        <v-avatar size="40">
                            <v-img :src="require('@/assets/2nd.png')" />
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2">
                        <!-- 받아온 이미지 src에 넣기 -->
                        <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankStudyWeek[1].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #bbbbbb; vertical-align: middle;">{{ rankStudyWeek[1].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankStudyWeek[1].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <v-row class="card_section_1st" justify="center" align="center" @click="moveToStudy(rankStudyWeek[2].seq)">
                    <v-col cols="12" md="2">
                        <v-avatar size="40">
                            <v-img :src="require('@/assets/3rd.png')" />
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2">
                        <!-- 받아온 이미지 src에 넣기 -->
                        <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankStudyWeek[2].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #503000; vertical-align: middle;">{{ rankStudyWeek[2].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankStudyWeek[2].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <!-- 이번주 버닝 데이터 TOP10 -->
                <v-row justify="center" align="center" >
                    <v-col>
                        <v-row class="card_section_each" :key="index" v-for="(value, index) in rankStudyWeek.slice(3)">
                            <v-col @click="moveToStudy(rankStudyWeek[index].seq)" align-self="center" cols="12" md="2"> {{ value.order }} </v-col>
                            <v-col @click="moveToStudy(rankStudyWeek[index].seq)"  align-self="center" class="line_limit_ranking" cols="12" md="6">
                                {{ value.nickName }}
                            </v-col>
                            <v-col cols="12" md="4" @click="moveToStudy(rankStudyWeek[index].seq)" >
                                <DigitalClock :allTime="value.time" :fontSize="25" />
                            </v-col>
                        </v-row>
                    </v-col>
                </v-row>
            </v-col>

            <!-- 이번달 버닝 TOP 10 -->
            <v-col class="card_section_board" cols="12" md="4" >
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

                <v-row class="card_section_1st" justify="center" align="center" @click="moveToStudy(rankStudyMonth[0].seq)">
                    <v-col cols="12" md="2">
                        <v-avatar size="40">
                            <v-img :src="require('@/assets/1st.png')" />
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2">
                        <!-- 받아온 이미지 src에 넣기 -->
                        <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankStudyMonth[0].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #ffb000; vertical-align: middle;">{{ rankStudyMonth[0].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankStudyMonth[0].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <v-row class="card_section_1st" justify="center" align="center" @click="moveToStudy(rankStudyMonth[1].seq)">
                    <v-col cols="12" md="2">
                        <v-avatar size="40">
                            <v-img :src="require('@/assets/2nd.png')" />
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2">
                        <!-- 받아온 이미지 src에 넣기 -->
                        <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankStudyMonth[1].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #bbbbbb; vertical-align: middle;">{{ rankStudyMonth[1].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankStudyMonth[1].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <v-row class="card_section_1st" justify="center" align="center"  @click="moveToStudy(rankStudyMonth[2].seq)">
                    <v-col cols="12" md="2">
                        <v-avatar size="40">
                            <v-img :src="require('@/assets/3rd.png')" />
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2">
                        <!-- 받아온 이미지 src에 넣기 -->
                        <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankStudyMonth[2].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #503000; vertical-align: middle;">{{ rankStudyMonth[2].nickName }}</span>
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
                        <v-row class="card_section_each" :key="index" v-for="(value, index) in rankStudyMonth.slice(3)">
                            <v-col  @click="moveToStudy(rankStudyMonth[index].seq)" align-self="center" cols="12" md="2"> {{ value.order }} </v-col>
                            <v-col  @click="moveToStudy(rankStudyMonth[index].seq)"  align-self="center" class="line_limit_ranking" cols="12" md="6">
                                {{ value.nickName }}
                            </v-col>
                            <v-col cols="12" md="4"  @click="moveToStudy(rankStudyMonth[index].seq)" >
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
        // RankingForm,
        DigitalClock
        // DigitalClockRankingFom
    },
    async created() {
        await this.$store.dispatch('moduleRanking/getRankMemberYesterday');
        console.log(this.$store.state.moduleRanking.rankStudyYesterday)
        this.rankStudyYesterday = this.$store.state.moduleRanking.rankStudyYesterday;
        console.log(this.$store.state.moduleRanking.rankStudyYesterday)
        await this.$store.dispatch('moduleRanking/getRankMemberWeek');
        this.rankStudyWeek = this.$store.state.moduleRanking.rankStudyWeek;
        await this.$store.dispatch('moduleRanking/getRankMemberMonth');
        this.rankStudyMonth = this.$store.state.moduleRanking.rankStudyMonth;
    },
    methods:{
        moveToStudy(seq) {
            this.$router.push({path: `/study/search/${seq}`});
        },
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
    height: 1110px !important;
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
