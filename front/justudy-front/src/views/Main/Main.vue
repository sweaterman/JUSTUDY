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
                            <v-text @click="moveTo('/study/search')" >더보기</v-text>
                        </v-col>
                    </v-row>

                    <v-row>
                        <div class="d-flex align-center" style="width: 100%; margin: 2%; overflow: hidden">
                            <div :style="`margin-left : ${studyPosition}px ; transition: 0.5s`"></div>
                            <div v-for="study in studyNew.studyResponse" v-bind:key="study" style="transition: 0.5s" >
                                <div class="singleStudy"  >
                                    <v-row justify="center">
                                        <div v-if="type != 'apply'" wid>
                                            <img class="hover" style="width: 100%" :src="`${port}images/${study.imageSequence}`" alt="study_image" @click="moveToStudy(study.sequence)" />
                                        </div>

                                        <div v-if="type == 'apply'" class="container">
                                            <img class="hover" style="width: 100%" :src="`${port}images/${study.imageSequence}`" alt="study_image" @click="moveToStudy(study.sequence)" />
                                            <v-btn rounded color="error" class="cancleApply" @click="deleteApply('open', study.sequence)">X</v-btn>
                                        </div>
                                    </v-row>
                                    <v-row style="margin-bottom: 5px">
                                        <v-col cols="12">
                                            <h3 class="hover" @click="moveToStudy(study.sequence)">{{ study.name }}</h3>
                                        </v-col>
                                    </v-row>

                                    <v-row no-gutters align="center">
                                        <v-col cols="4">
                                            <v-subheader style="height: fit-content; padding: 0px">모집 현황</v-subheader>
                                        </v-col>
                                        <v-col cols="8" v-if="checkPersonnel(study.member.length, study.population)" style="color: #ff0000; font-weight: bold">
                                            {{ study.member.length }} / {{ study.population }}
                                        </v-col>
                                        <v-col cols="8" v-if="!checkPersonnel(study.member.length, study.population)" style="color: #3edf23; font-weight: bold">
                                            {{ study.member.length }} / {{ study.population }}
                                        </v-col>
                                    </v-row>
                            <!-- <v-row no-gutters align="center">
                                <v-col cols="4">
                                    <v-subheader style="height: fit-content; padding: 0px">시작 예정일</v-subheader>
                                </v-col>
                                <v-col cols="8">
                                    {{ study.startTime }}
                                </v-col>
                            </v-row> -->
                            <!-- <v-row no-gutters align="center">
                                <v-col cols="4">
                                    <v-subheader style="height: fit-content; padding: 0px">스터디장</v-subheader>
                                </v-col>
                                <v-col cols="8">
                                    {{ study.leader }}
                                </v-col>
                            </v-row> -->
                            <!-- <v-row dense>
                                <v-chip class="chip" color="yellow lighten-4">{{ study.frequency.map(row => row.week).join() }}</v-chip>
                                <v-chip class="chip" color="yellow lighten-4">{{ study.meeting }}</v-chip>
                                <v-chip class="chip" color="yellow lighten-4">{{ study.level }}</v-chip>
                            </v-row> -->
                                </div>
                                <!-- <a href="/study/apply/1"> <img src="../../assets/logo.jpg" width="300" height="100%" style="padding: 1%" /> </a> -->
                            </div>
                        </div>
                    </v-row>
                </v-col>
                <v-col cols="12" md="2" align="left" :style="{marginTop: '11%'}">
                    <img src="@/assets/arrow.png" style="width: 30px; height: 30px" @click="toMidRight()" />
                </v-col>
            </v-row>

            <!-- 북마크한 글 파트 -->
            <BoardList boardtitle="지금 HOT 한 글" :bookMarkList="boardHot" />
        </v-container>
    </v-app>
</template>

<script>
import BoardList from '@/components/common/BoardList.vue';
import port from '@/store/port';

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
            boardHot: [],
            port: port
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
        },
        checkPersonnel(c, p) {
            //초록색이면 false, 빨간색이면 true
            if (Number(p) - Number(c) > 2) {
                return false;
            } else {
                return true;
            }
        },
        moveToStudy(seq) {
            //홍보 중 스터디이거나 지원한 스터디이면?
            if (this.type == 'promotion' || this.type == 'apply') {
                this.$router.push({path: `/study/search/${seq}`});
            }
            //내가 가입한 스터디이면?
            else {
                this.$router.push({path: `/study/${seq}/info`});
            }
        },
        async moveTo(link) {
             window.location.href = link;
        }
    },
    async created() {
        //  최신 스터디 3개를 뽑아서 보여주기
        await this.$store.dispatch("moduleStudy/getPromotionStudies",{send: ''});
        this.studyNew= this.$store.state.moduleStudy.promotionStudies;
        console.log(this.studyNew.studyResponse)
        // 핫한글 (좋아요 기준)
        await this.$store.dispatch('moduleCommunity/getPopularCommunityBoard', {number: 1});
        this.boardHot = this.$store.state.moduleCommunity.hotBoard;
        console.log(this.hotBoard)
        
    },     
};
</script>
