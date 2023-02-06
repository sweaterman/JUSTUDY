<template>
    <v-app>
        <!-- 버닝 -->
        <v-row :style="{marginTop: '10%'}">
            <!-- 버닝 상단 -->
            <v-row>
                <h1>
                    {{ category }} 버닝 TOP 10
                    <hr style="width: 160%" />
                </h1>
            </v-row>

            <!-- 버닝 데이터 -->
            <v-row>
                <v-col cols="12" md="4">
                    <!-- 버닝 데이터 1등 -->
                    <v-row :style="{marginTop: '-3%'}">
                        <v-col align="left">
                            <div :style="{fontSize: 'large'}">
                                <h2>
                                    <span style="color: black">{{ category }} </span>
                                    <span style="color: #ffb000">공부</span>
                                    <span style="color: black">왕</span>
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
                                :src="require('@/assets/banner.jpg')"
                                style="padding: 5%"
                                justify="center"
                                align="center"
                            />
                        </v-col>
                    </v-row>

                    <v-row :style="{marginTop: '-6%'}">
                        <v-col cols="12" md="1" align="right">
                            <div :style="{fontWeight: 'bold', fontSize: 'large'}">
                                <h1>
                                    <span style="color: black">"</span>
                                </h1>
                            </div>
                        </v-col>
                        <v-col cols="12" md="8" align="left">
                            <div :style="{fontWeight: 'bold', fontSize: 'large'}">
                                <h1>
                                    <span style="color: #ffb000" class="line_limit">{{ data[0].nickName }}</span>
                                </h1>
                            </div>
                        </v-col>
                        <v-col cols="12" md="1" align="left">
                            <div :style="{fontWeight: 'bold', fontSize: 'large'}">
                                <h1>
                                    <span style="color: black">"</span>
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
                    </v-row>
                    <v-row justify="center" align="center" :style="{marginTop: '-12%'}">
                        <DigitalClock :allTime="340" :fontSize="100" />
                    </v-row>
                </v-col>

                <!-- 전날 버닝 데이터 TOP10 -->
                <v-col cols="12" md="8" justify="center" align="center" :style="{paddingLeft: '5%'}">
                    <v-simple-table style="width: 1500px">
                        <tbody>
                            <tr :key="index" v-for="(value, index) in data">
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
    </v-app>
</template>
<script>
// import MyRankingData from '@/data/MyRankingData';
import ProfilePicture from '@/components/mypage/ProfilePicture.vue';
import DigitalClock from '@/components/timer/DigitalClock.vue';
export default {
    name: 'MyRanking',
    data() {
        return {
            // data: MyRankingData
        };
    },

    props: {
        category: {
            type: String
        },
        data: {
            type: Object
        },
        ComponentData: {
            type: String
        }
    },

    components: {ProfilePicture, DigitalClock},
    created() {}
};
</script>
<style>
.line_limit {
    width: 290px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    display: inline-block;
}
</style>
