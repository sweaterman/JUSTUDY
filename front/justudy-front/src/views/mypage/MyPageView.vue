<template>
    <v-app>
        <div>
            <ModalComponent :dialog="dialog" @closeModal="close" />
        </div>

        <!-- 프로필 디테일 파트 ............................................................................-->
        <v-row :style="{marginTop: '2%'}">
            <v-col cols="12" md="2" />

            <!-- 내용상세 -->
            <v-col cols="12" md="8">
                <v-row>
                    <!-- 프로필 이미지 있는 경우 -->
                    <v-col cols="12" md="2" :style="{marginTop: '1%'}">
                        <v-row v-if="user.imageSequence" :style="{marginLeft: '4%'}">
                            <ProfilePicture :diameter="140" :height="140" :fontSize="32" content="LV" standard="px" :src="`${port}images/${user.imageSequence}`" />
                        </v-row>
                        <!-- 프로필 이미지 없는 경우 ....... 기본 이미지 -->
                        <v-row v-if="!user.imageSequence" :style="{marginLeft: '4%'}">
                            <ProfilePicture :diameter="140" :height="140" :fontSize="32" content="LV" standard="px" :src="require('@/assets/justudy.png')" />
                        </v-row>
                        <v-row :style="{marginTop: '25%', marginLeft: '5%'}">
                            <h3 :style="{color: '#ffb000'}">Lv.</h3>
                            <h3>{{ level }}</h3>
                            <!-- <TextButton :buttonLength="70" :height="70" :fontSize="1" :content="level" :standard="px" /> -->
                        </v-row>
                        <v-row>
                            <v-col>
                                <v-btn outlined text @click="movetoupdate()">
                                    <span class="material-icons-outlined"> edit </span>
                                    <div>프로필 수정</div>
                                </v-btn>
                            </v-col>
                        </v-row>
                    </v-col>

                    <!-- 프로필 상세 -->
                    <v-col cols="12" md="3">
                        <v-row dense>
                            <v-col cols="12" md="5" :style="{marginTop: '-3%'}">
                                <v-subheader>닉네임</v-subheader>
                            </v-col>
                            <v-col cols="12" md="1" />
                            <v-col cols="12" md="6">
                                <div>{{ user.nickname }}</div>
                            </v-col>
                        </v-row>
                        <v-row dense>
                            <v-col cols="12" md="5">
                                <v-subheader>희망 스텍</v-subheader>
                            </v-col>
                            <v-col cols="12" md="1" />
                            <v-col cols="12" md="6">
                                <div>{{ user?.category?.join(',') }}</div>
                            </v-col>
                        </v-row>
                        <v-row dense>
                            <v-col cols="12" md="5">
                                <v-subheader>희망 진로</v-subheader>
                            </v-col>
                            <v-col cols="12" md="1" />
                            <v-col cols="12" md="6">
                                <div>{{ user.dream ? user.dream : '없음' }}</div>
                            </v-col>
                        </v-row>
                        <v-row dense>
                            <v-col cols="12" md="5">
                                <v-subheader>포인트 🏆</v-subheader>
                            </v-col>
                            <v-col cols="12" md="1" />
                            <v-col cols="12" md="6">
                                <div>{{ '+' + user.badgeCount }}</div>
                            </v-col>
                        </v-row>
                        <v-row dense>
                            <v-col cols="12" md="5">
                                <v-subheader>현재 상태</v-subheader>
                            </v-col>
                            <v-col cols="12" md="1" />
                            <v-col cols="12" md="6">
                                <v-select v-model="status" :items="items" menu-props="auto" value="ddd" />
                            </v-col>
                        </v-row>
                        <!-- <OneLineInfo :category="`닉네임`" :detail="user.nickname" /> -->
                        <!-- <OneLineInfo :category="`희망 스택`" :detail="user?.category?.join(',')" /> -->
                        <!-- <OneLineInfo :category="`희망 진로`" :detail="user.dream ? user.dream : '없음'" /> -->
                        <!-- <OneLineInfo :category="`포인트 🏆`" :detail="'+' + user.badgeCount" /> -->
                    </v-col>

                    <!-- 회원 수정 -->
                    <!-- <v-col>
                        <router-link to="/mypage/update" style="text-decoration: none; color: black">
                            <v-btn color="yellow" :style="{height: '50px', width: '170px', fontWeight: 'bold', fontSize: 'x-large', marginTop: '30%'}">회원 수정</v-btn>
                        </router-link>
                    </v-col> -->
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

        <!-- 학습 분석 파트 ................................................................................-->
        <v-row>
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-row>
                    <v-col cols="12" md="4" align="left">
                        <h2>학습 분석</h2>
                        <hr />
                    </v-col>
                    <v-col cols="12" md="4" />
                    <v-col cols="12" md="4" align="right" />
                </v-row>

                <v-row>
                    <v-col cols="12" md="3">
                        <v-row>
                            <v-col>
                                <h2 :style="{color: '#ffb000'}">학습 진행도</h2>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col>
                                <Radar :chart-options="chartOptions" :chart-data="chartData" :chart-id="radar - chart" :dataset-id-key="label" :plugins="plugins" />
                            </v-col>
                            <!-- <RadarChart buttonContent="학습 진행도" :hasButton="true" :category="category" /> -->
                        </v-row>
                        <v-row dense>
                            <v-col>
                                <h3>
                                    <span style="color: #58a8f7"> "{{ first }}" </span> 공부가 더 필요해요
                                </h3>
                            </v-col>
                        </v-row>
                        <v-row dense>
                            <v-col>
                                <h3>
                                    <span style="color: #58a8f7"> "{{ second }}" </span> 공부가 더 필요해요
                                </h3>
                            </v-col>
                        </v-row>
                        <!-- <StudyAnalyze first="알고리즘" second="DataBase" /> -->
                        <!-- <v-row :style="{marginBottom: '2%'}">
                                <v-btn color="yellow" :style="{height: '50px', width: '170px', fontWeight: 'bold', fontSize: 'x-large'}">추천 스터디</v-btn>
                            </v-row> -->
                        <!-- <v-row>
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
                            </v-row> -->
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="8">
                        <!-- 달력 파트 -->
                        <v-row :style="{marginTop: '-2%'}">
                            <MyPageCalendar :studyCalendar="studyCalendar" />
                        </v-row>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>

        <!-- 북마크한 글 파트 -->
        <v-row :style="{marginBottom: '5%'}">
            <BoardList boardtitle="북마크한 글" :bookMarkList="bookMarkList" />
            {{ bookMarkList }}
        </v-row>
    </v-app>
</template>
<script>
import {Radar} from 'vue-chartjs';
import {Chart as ChartJS, Title, Tooltip, Legend, PointElement, LineElement, RadialLinearScale, registerables} from 'chart.js';
ChartJS.register(Title, Tooltip, Legend, PointElement, RadialLinearScale, LineElement, ...registerables);
// import OneLineInfo from '@/components/common/OneLineInfo.vue';
// import StudyAnalyze from '@/components/mypage/StudyAnalyze.vue';
// import ProfileDetail from '@/components/mypage/ProfileDetail.vue';
// import Follow from '@/components/common/Follow.vue';
import ProfilePicture from '@/components/mypage/ProfilePicture.vue';
// import Calendar from '@/components/common/Calendar.vue';
import MyPageCalendar from '@/components/mypage/MyPageCalendar.vue';
// import RadarChart from '@/components/common/RadarChart.vue';
import ModalComponent from '@/components/mypage/ModalComponent.vue';
import BoardList from '@/components/common/BoardList.vue';
// import TextButton from '@/components/common/TextButton.vue';
// import {mapState} from 'vuex';
import port from '@/store/port';
export default {
    name: 'MyPageView',
    data() {
        return {
            title: "Hello it's function !",
            user: {},
            category: [],
            loaded: false,
            chartOptions: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    r: {
                        suggestedMin: 0,
                        suggestedMax: 6,
                        stepSize: 2,
                        pointLabels: {
                            color: 'orange' // 라벨의 글씨 색상
                        }
                    }
                },
                plugins: {
                    legend: {
                        display: false // 최상단 라벨의 카테고리 안보이기.
                    }
                }
            },
            level: '초보 개발자',
            dialog: false,
            port: port,
            first: '알고리즘',
            second: '데이터베이스',
            // store에서 담아올 값

            following: {},
            follow: {},
            studyAnalyzeValue: [],
            studyRecommand: [],
            bookMark: [],
            studyCalendar: [],
            bookMarkList: [],
            timeCategoryData: []
        };
    },
    computed: {
        chartData() {
            return {
                labels: ['Frontend', 'Backend', 'Algorithm', 'CS', 'Database', 'etc'],
                datasets: [
                    {
                        label: 'value',
                        backgroundColor: 'rgba(255,125,0,0.2)',
                        borderColor: 'rgba(255,125,0,1)',
                        pointBackgroundColor: 'rgba(255,125,0,1)',
                        pointBorderColor: '#fff',
                        pointHoverBackgroundColor: '#fff',
                        pointHoverBorderColor: 'rgba(255,125,0,1)',
                        data: [0, 0, 0, 0, 0, 0]
                    }
                ]
            };
        }
        // userLoginIdx(){
        // return this.$store.state.user.userIdx;
        // },
    },
    components: {
        Radar,
        // OneLineInfo,
        BoardList,
        // StudyAnalyze,
        // ProfileDetail,
        // Follow,
        ProfilePicture,
        MyPageCalendar,
        // RadarChart,
        ModalComponent
        // TextButton
    },
    methods: {
        movetoupdate() {
            window.location.href = '/mypage/update';
        },
        dialogChange() {
            this.dialog = true;
        },
        close(status) {
            if (status) {
                this.dialog = false;
            }
        }
    },
    async created() {
        // 로그인한 유저 사진과 유저 정보 (닉네임,희망상태,희망진로)
        await this.$store.dispatch('moduleMyPage/getMyPageUser');
        this.user = this.$store.state.moduleMyPage.user;
        let studyCalendar = new Array(32).fill('🟡18:00');
        this.studyCalendar = studyCalendar;
        console.log(studyCalendar);
        await this.$store.dispatch('moduleMyPage/getBookMarkList');
        this.bookMarkList = this.$store.state.moduleMyPage.bookMarkList;
        //ff

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
    },
    async mounted() {
        this.loaded = false;
        await this.$store.dispatch('moduleMyPage/getMyPageUser');

        this.user = this.$store.state.moduleMyPage.user;
        await this.$store.dispatch('moduleTimer/getStudyCategory', {nickName: this.user.nickname});
        this.category = this.$store.state.moduleTimer.studyCategory;
        // this.category = this.$store.state.moduleTimer.studyCategory;
        console.log('아아아' + this.category);
        // console.log(this.chartData.datasets[0].data);
        for (let i = 0; i < this.category.length; i++) {
            this.chartData.datasets[0].data[i] = this.category[i].second;
            // this.chartData.datasets[0].data[i] = 300;
        }
        // this.chartData.datasets[0].data[0] = this.category[0] ? this.category[0]?.second : 0;
        // this.chartData.datasets[0].data[1] = this.category[1] ? this.category[1]?.second : 0;
        // this.chartData.datasets[0].data[2] = this.category[2] ? this.category[2]?.second : 0;
        // this.chartData.datasets[0].data[3] = this.category[3] ? this.category[3]?.second : 0;
        // this.chartData.datasets[0].data[4] = this.category[4] ? this.category[4]?.second : 0;
        // this.chartData.datasets[0].data[5] = this.category[5] ? this.category[5]?.second : 0;
        this.loaded = true;
    }
};
</script>
