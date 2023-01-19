<template>
    <v-app>
        <v-row :style="{marginTop: '1%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8" align="center">
                <h1>Justudy 에서 공부하세요!</h1>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>

        <!-- 배너 자리 -->
        <v-row :style="{marginTop: '1%'}">
            <v-col cols="12" md="2" align="right" :style="{marginTop: '18%'}">
                <img src="@/assets/arrow.png" style="width: 30px; height: 30px; transform: rotate(0.5turn)" @click="toTopLeft()" />
            </v-col>
            <v-col cols="12" md="8">
                <div class="d-flex align-center" style="width: 90%; margin: 2%; overflow: hidden">
                    <div :style="`margin-left : ${bannerPosition}px ; transition: 0.5s`"></div>
                    <div v-for="item in 5" v-bind:key="item" style="transition: 0.5s">
                        <a href="/">
                            <img src="../../assets/banner.jpg" width="1200px" height="100%" />
                            <!-- <img alt="Vue banner" width="100%" height="100%" src="../../assets/banner.jpg" /></a> -->
                        </a>
                    </div>
                </div>
            </v-col>
            <v-col cols="12" md="2" align="left" :style="{marginTop: '18%'}">
                <img src="@/assets/arrow.png" style="width: 30px; height: 30px" @click="toTopRight()" />
            </v-col>
        </v-row>

        <!-- 스터디 모집중! 자리 -->
        <v-row :style="{marginTop: '5%'}">
            <v-col cols="12" md="2" align="right" :style="{marginTop: '11%'}">
                <img src="@/assets/arrow.png" style="width: 30px; height: 30px; transform: rotate(0.5turn)" @click="toMidLeft()" />
            </v-col>
            <v-col cols="12" md="8">
                <v-row>
                    <v-col cols="12" md="4" align="left">
                        <h1>스터디 모집 중</h1>
                        <hr />
                    </v-col>
                    <v-col cols="12" md="4" />
                    <v-col cols="12" md="4" align="right">
                        <v-text>더보기</v-text>
                    </v-col>
                </v-row>

                <v-row>
                    <div class="d-flex align-center" style="width: 90%; margin: 2%; overflow: hidden">
                        <div :style="`margin-left : ${studyPosition}px ; transition: 0.5s`"></div>
                        <div v-for="item in 20" v-bind:key="item" style="transition: 0.5s">
                            <a href="/study/apply/1"> <img src="../../assets/logo.jpg" width="300" height="100%" style="padding: 1%" /> </a>
                        </div>
                    </div>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" align="left" :style="{marginTop: '11%'}">
                <img src="@/assets/arrow.png" style="width: 30px; height: 30px" @click="toMidRight()" />
            </v-col>
        </v-row>

        <!-- 글목록 -->
        <v-row :style="{marginTop: '5%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-row>
                    <v-col cols="12" md="4" align="left">
                        <h1>지금 HOT 한 글</h1>
                        <hr />
                    </v-col>
                    <v-col cols="12" md="4" />
                    <v-col cols="12" md="4" align="right">
                        <v-text>더보기</v-text>
                    </v-col>
                </v-row>
                <v-row :style="{marginTop: '2%'}">
                    <v-simple-table style="width: 100%">
                        <thead>
                            <tr style="font-weight: bolder">
                                <td style="width: 10%; font-size: large">No</td>
                                <td style="width: 50%; font-size: large">제목</td>
                                <td style="width: 15%; font-size: large">글쓴이</td>
                                <td style="width: 15%; font-size: large">작성시간</td>
                                <td style="width: 10%; font-size: large">좋아요</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="item in contentlist" :key="item.id" @click="movetocontent(item.id)">
                                <td>{{ item.writer }}</td>
                                <td>{{ item.title }}</td>
                                <td>{{ item.createdAt.split('T')[0] }}</td>
                                <!-- Sequelize의 createdAt, updatedAt의 날짜 형식이 '2021-12-10T12:38:52.000Z' 이런 식이여서 
                               split('T')[0]을 통해 날짜만 표시 -->
                            </tr>
                        </tbody>
                    </v-simple-table>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>

<script>
export default {
    name: 'SignInView',
    components: {},

    data() {
        return {
            bannerPosition: 0,
            studyPosition: 0
        };
    },

    methods: {
        movetomain() {
            window.location.href = '/';
        },
        toTopLeft() {
            if (this.bannerPosition < 0) {
                this.bannerPosition = this.bannerPosition + 1225;
            }
        },
        toTopRight() {
            if (this.bannerPosition > -1225 * 4) {
                this.bannerPosition = this.bannerPosition - 1225;
            }
        },
        toMidLeft() {
            if (this.studyPosition < 0) {
                this.studyPosition = this.studyPosition + 1225;
            }
        },
        toMidRight() {
            if (this.studyPosition > -1225 * 3.5) {
                this.studyPosition = this.studyPosition - 1225;
            }
        }
    }
};
</script>
