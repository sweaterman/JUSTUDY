<template>
    <v-app>
        <v-row>
            <v-col class="card_section_board" cols="12" md="4">
                오늘 버닝 상단
                <v-row>
                    <v-col><h1>오늘의 버닝 TOP 10</h1></v-col>
                </v-row>

                <!-- 오늘 버닝 데이터 1등 -->
                <!-- <v-row>
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
                    <v-col cols="12" md="2"> -->
                        <!-- 받아온 이미지 src에 넣기 -->
                        <!-- 
                        <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankMemberYesterday[0].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <v-tooltip text="Tooltip">
                                <template v-slot:activator="{ props }">
                                    <span v-bind="props" class="line_limit_ranking" style="color: #ffb000; vertical-align: middle;">{{ rankMemberYesterday[0].nickName }}</span>
                                </template>
                                </v-tooltip>
  
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
                    <v-col cols="12" md="2"> -->
                        <!-- 받아온 이미지 src에 넣기 -->
                        <!--  
                        <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankMemberYesterday[1].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #bbbbbb; vertical-align: middle;">{{ rankMemberYesterday[1].nickName }}</span>
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
                    <v-col cols="12" md="2"> -->
                        <!-- 받아온 이미지 src에 넣기 -->
                        <!--  
                        <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankMemberYesterday[2].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #503000; vertical-align: middle;">{{ rankMemberYesterday[2].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankMemberYesterday[2].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row> -->

                <!-- 오늘 버닝 데이터 TOP10 -->
                <v-row justify="center" align="center">
                    <v-col>
                        <v-row class="card_section_each" :key="index" v-for="(value, index) in rankMemberYesterday.slice(3)">
                            <v-col align-self="center" cols="12" md="2"> {{ value.order }} </v-col>
                            <v-col align-self="center" class="line_limit_ranking" cols="12" md="6">
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
                        <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankMemberWeek[0].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #ffb000; vertical-align: middle;">{{ rankMemberWeek[0].nickName }}</span>
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
                        <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankMemberWeek[1].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #bbbbbb; vertical-align: middle;">{{ rankMemberWeek[1].nickName }}</span>
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
                        <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankMemberWeek[2].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #503000; vertical-align: middle;">{{ rankMemberWeek[2].nickName }}</span>
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
                        <v-row class="card_section_each" :key="index" v-for="(value, index) in rankMemberWeek.slice(3)">
                            <v-col align-self="center" cols="12" md="2"> {{ value.order }} </v-col>
                            <v-col align-self="center" class="line_limit_ranking" cols="12" md="6">
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
                        <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankMemberMonth[0].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #ffb000; vertical-align: middle;">{{ rankMemberMonth[0].nickName }}</span>
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
                        <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankMemberMonth[1].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #bbbbbb; vertical-align: middle;">{{ rankMemberMonth[1].nickName }}</span>
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
                        <v-avatar
                        color="primary"
                        size="56"
                        ><img
                        :src="`${port}images/${rankMemberMonth[2].img}`" 
                        ></v-avatar>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <v-row>
                            <h3>
                                <span class="line_limit_ranking" style="color: #503000; vertical-align: middle;">{{ rankMemberMonth[2].nickName }}</span>
                                <span style="color: black">님!</span>
                            </h3>
                        </v-row>
                        <v-row>
                            <DigitalClock :allTime="rankMemberMonth[2].time" :fontSize="30" />
                        </v-row>
                    </v-col>
                </v-row>

                <!-- 이번달 버닝 데이터 TOP10 -->
                <v-row justify="center" >
                    <v-col align-self="center">
                        <v-row class="card_section_each" :key="index" v-for="(value, index) in rankMemberMonth.slice(3)">
                            <v-col align-self="center" cols="12" md="2"> {{ value.order  }} </v-col>
                            <v-col align-self="center" class="line_limit_ranking" cols="12" md="6">
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
        // RankingForm,
        DigitalClock
        // DigitalClockRankingFom
    },
    async created() {
        await this.$store.dispatch('moduleRanking/getRankMemberYesterday');
        this.rankMemberYesterday = this.$store.state.moduleRanking.rankMemberYesterday;
        console.log(this.rankMemberYesterday)
        await this.$store.dispatch('moduleRanking/getRankMemberWeek');
        this.rankMemberWeek = this.$store.state.moduleRanking.rankMemberWeek;
        console.log(this.rankMemberWeek)
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
