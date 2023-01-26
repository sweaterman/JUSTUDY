<template>
    <v-app>
        <div>
            <ModalComponent :dialog="dialog" @closeModal="close" />
        </div>

        <!-- 프로필 디테일 파트 -->
        <v-row :style="{marginTop: '2%'}">
            <v-col cols="12" md="2" />

            <!-- 내용상세 -->
            <v-col cols="12" md="8">
                <v-row>
                    <!-- 프로필 이미지 -->
                    <v-col cols="12" md="3">
                        <v-row>
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
                        </v-row>
                        <v-row :style="{marginTop: '10%'}">
                            <v-col cols="12" md="4">
                                <v-btn color="yellow" :style="{height: '30px', width: '100%', fontWeight: 'bold', fontSize: 'large'}">Lv.</v-btn>
                            </v-col>
                            <v-col cols="12" md="8" align="left">
                                <h2>{{ level }}</h2>
                                <!-- <TextButton :buttonLength="70" :height="70" :fontSize="1" :content="level" :standard="px" /> -->
                            </v-col>
                        </v-row>
                    </v-col>

                    <!-- 프로필 상세 -->
                    <v-col cols="12" md="3">
                        <ProfileDetail :user="user" />
                    </v-col>

                    <!-- 팔로우 N 팔로잉 -->
                    <v-col cols="12" md="6">
                        <v-row>
                            <!-- <Follow buttonContent="팔로잉" @dialogChangeFromChild="dialogChange()" /> -->
                            <!-- <Follow buttonContent="팔로우" /> -->
                        </v-row>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>

        <!-- 학습 분석 파트 -->
        <v-row>
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-row :style="{marginTop: '1%', marginBottom: '3%'}">
                    <v-col cols="12" md="4" align="left">
                        <h1>학습 분석</h1>
                        <hr />
                    </v-col>
                    <v-col cols="12" md="4" />
                    <v-col cols="12" md="4" align="right" />
                </v-row>

                <v-row>
                    <v-col cols="12" md="5" justify="center" align="center">
                        <RadarChart buttonContent="학습 진행도" :hasButton="true" />
                        <v-row justify="center" align="center">
                            <v-btn color="yellow" :style="{height: '50px', width: '170px', fontWeight: 'bold', fontSize: 'x-large', marginTop: '30%'}">학습 진행도</v-btn>
                        </v-row>
                    </v-col>

                    <v-col cols="12" md="7">
                        <StudyAnalyze first="알고리즘" second="DataBase" :style="{marginLeft: '2%', marginBottom: '10%'}" />
                        <v-row :style="{marginLeft: '2%'}">
                            <v-row :style="{marginBottom: '2%'}">
                                <v-btn color="yellow" :style="{height: '50px', width: '170px', fontWeight: 'bold', fontSize: 'x-large'}">추천 스터디</v-btn>
                            </v-row>
                            <v-row>
                                <div class="d-flex mr-1" v-for="item in 3" v-bind:key="item">
                                    <div>
                                        <img src="../../assets/logo.jpg" width="200px" height="200px" style="padding: 4%" />
                                        <div class="d-flex" :style="{marginLeft: '5%'}">
                                            <div>
                                                <h3>#임시파일입니다</h3>
                                                <h3>월~금 5시</h3>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </v-row>
                        </v-row>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>

        <!-- 북마크한 글 파트 -->
        <v-row :style="{marginBottom: '5%'}">
            <BoardList boardtitle="북마크한 글" />
        </v-row>

        <!-- 달력 파트 -->
        <v-row :style="{marginTop: '1%'}">
            <Calendar />
        </v-row>
    </v-app>
</template>
<script>
import StudyAnalyze from '@/components/mypage/StudyAnalyze.vue';
import ProfileDetail from '@/components/mypage/ProfileDetail.vue';
// import Follow from '@/components/common/Follow.vue';
import ProfilePicture from '@/components/mypage/ProfilePicture.vue';
import Calendar from '@/components/common/Calendar.vue';
import RadarChart from '@/components/common/RadarChart.vue';
import ModalComponent from '@/components/mypage/ModalComponent.vue';
import BoardList from '@/components/common/BoardList.vue';
// import TextButton from '@/components/common/TextButton.vue';

export default {
    name: 'MyPageView',
    data() {
        return {
            level: '초보 개발자',
            dialog: false,
            // store에서 담아올 값
            user: {},
            following: {},
            follow: {},
            studyAnalyzeValue: [],
            studyRecommand: [],
            bookMark: [],
            studyCalendar: []
        };
    },
    computed: {
        // ...mapState(['aaa'])
        // userLoginIdx(){
        // return this.$store.state.user.userIdx;
        // },
    },
    components: {
        BoardList,
        StudyAnalyze,
        ProfileDetail,
        // Follow,
        ProfilePicture,
        Calendar,
        RadarChart,
        ModalComponent
        // TextButton
    },
    methods: {
        dialogChange() {
            this.dialog = true;
        },
        close(status) {
            if (status) {
                this.dialog = false;
            }
        }
    },
    created() {
        // 로그인한 유저 사진과 유저 정보 (닉네임,희망상태,희망진로)
        //     this.$store.dispatch("user/getUser");
        // 팔로잉
        // this.$store.dispatch("user/following");
        // 팔로우
        // this.$store.dispatch("user/follow");
        // 학습 분석 수치  (얘는 타이머를 통해 수치를 가지고 올거임)
        // this.$store.dispatch("timer/categoryTime")
        // 추천 스터디 가지고 오기
        // this.$store.dispatch("study/recommand")
        // 북마크한글 가지고 오기
        // this.$store.dispatch("community/bookmark")
        // 달력 일정 가지고 오기 (이걸 어떻게 구체적으로 할지 상의해야 할듯 )
    }
};
</script>
