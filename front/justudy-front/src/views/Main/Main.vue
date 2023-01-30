<template>
    <v-app>
        <v-container>
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

            <!-- 북마크한 글 파트 -->
            <BoardList boardtitle="지금 HOT 한 글" />
        </v-container>
    </v-app>
</template>

<script>
import BoardList from '@/components/common/BoardList.vue';

export default {
    name: 'SignInView',
    components: {
        BoardList
    },

    data() {
        return {
            bannerPosition: 0,
            studyPosition: 0,
            //store 에서 담아올 값
            // 모집중인 스터디 3개(최근)
            studyNew: [],
            boardHot: []
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
    },
    created() {
        //  최신 스터디 3개를 뽑아서 보여주기
        // this.$store.dispatch("study/new")
        // 핫한글 (좋아요 기준)
        // this.$store.dispatch("community/hot")
    }
};
</script>
