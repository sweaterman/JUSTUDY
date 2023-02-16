<template>
    <v-container>
        <div>
            <ModalComponent :dialog="dialog" @closeModal="close" :data="profile" />
        </div>

        <v-row :style="{marginTop: '2%'}">
            <!-- 좌측 여백 -->
            <v-col cols="12" md="3" />

            <!-- 내용상세 -->
            <v-col cols="12" md="6" align="center">
                <v-row>
                    <v-col align="end">
                        <v-btn outlined text @click="movetoupdate()">
                            <span class="material-icons-outlined"> edit </span>
                            <div>프로필 수정</div>
                        </v-btn>
                    </v-col>
                </v-row>

                <v-row>
                    <!-- 프로필 이미지 있는 경우 -->
                    <v-col cols="5" class="profile">
                        <v-row v-if="user.imageSequence">
                            <v-col cols="12" align="center">
                                <ProfilePicture :diameter="140" :height="140" :fontSize="32" content="LV" standard="px" :src="`${port}images/${user.imageSequence}`" />
                            </v-col>
                        </v-row>

                        <v-row :style="{marginTop: '25%', marginLeft: '5%'}">
                            <h3 :style="{color: '#ffb000'}">Lv.&nbsp;</h3>
                            <h3>{{ level }}</h3>
                            <!-- <TextButton :buttonLength="70" :height="70" :fontSize="1" :content="level" :standard="px" /> -->
                        </v-row>

                        <!-- 자가진단 연결 -->
                        <v-row no-gutters>
                            <v-col cols="12" align="end">
                                <v-btn small text color="#BBBBBB" @click="moveToselfTest()">자가진단 하러가기!</v-btn>
                            </v-col>
                        </v-row>

                        <v-row>
                            <!-- 프로필 상세 -->
                            <v-col cols="12" align="left">
                                <v-row dense align="center">
                                    <v-col cols="12" md="5">
                                        <v-subheader>닉네임</v-subheader>
                                    </v-col>
                                    <v-col cols="12" md="6">
                                        <div>{{ user.nickname }}</div>
                                    </v-col>
                                </v-row>
                                <v-row dense align="center">
                                    <v-col cols="12" md="5">
                                        <v-subheader>관심 스택</v-subheader>
                                    </v-col>
                                    <v-col cols="12" md="6">
                                        <div>{{ user?.category?.join(',') }}</div>
                                    </v-col>
                                </v-row>
                                <v-row dense align="center">
                                    <v-col cols="12" md="5">
                                        <v-subheader>희망 진로</v-subheader>
                                    </v-col>
                                    <v-col cols="12" md="6">
                                        <div>{{ user.dream ? user.dream : '없음' }}</div>
                                    </v-col>
                                </v-row>
                                <v-row dense align="center">
                                    <v-col cols="12" md="5">
                                        <v-subheader>포인트 🏆</v-subheader>
                                    </v-col>
                                    <v-col cols="12" md="6">
                                        <div>{{ '+' + user.badgeCount }}</div>
                                    </v-col>
                                </v-row>
                            </v-col>
                        </v-row>
                    </v-col>

                    <!-- 학습 분석 -->
                    <v-col cols="7" style="padding-left: 10px">
                        <!-- 학습 분석 제목 -->
                        <v-row>
                            <v-col cols="1"> </v-col>
                            <v-col cols="11" align="left">
                                <h2 style="background-color: #e4e4e4">&nbsp;학습 분석</h2>
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col cols="1"></v-col>
                            <v-divider></v-divider>
                        </v-row>
                        <v-row>
                            <v-col cols="12">
                                <v-row>
                                    <v-col>
                                        <Radar :chart-options="chartOptions" :chart-data="chartData" :chart-id="radar - chart" :dataset-id-key="label" :plugins="plugins" v-if="loaded" />
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
                            </v-col>
                        </v-row>
                    </v-col>
                </v-row>

                <!-- 팔로우 N 팔로잉 -->
                <v-row>
                    <v-col cols="12" class="d-flex flex-column justify-center">
                        <v-row>
                            <v-col align="left"> <h2 style="background-color: #e4e4e4">&nbsp;팔로잉 & 팔로워</h2> </v-col>
                        </v-row>
                        <v-row>
                            <v-col>
                                <v-divider></v-divider>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="12" align="center">
                                <v-btn dark color="#EDC300" @click="clicked('following')">팔로잉</v-btn>
                                &nbsp;&nbsp;&nbsp;
                                <v-btn dark color="#EDC300" @click="clicked('follower')">팔로워</v-btn>
                            </v-col>
                        </v-row>
                        <!-- 팔로잉 -->
                        <v-row v-show="isFollowing" class="card_section_follow" justify="center" align="center">
                            <v-col cols="12" md="1" align="left">
                                <v-btn @click="toLeft()" color="white" depressed>
                                    <span class="material-icons-outlined"> chevron_left </span>
                                </v-btn>
                                <!-- <img src="@/assets/arrow.png" style="width: 30px; height: 30px; transform: rotate(0.5turn)" @click="toLeft()" /> -->
                            </v-col>
                            <v-col cols="12" md="10">
                                <div class="d-flex align-center" style="width: 90%; margin-left: 5%; margin-right: 5%; overflow: hidden">
                                    <div :style="`margin-left : ${bannerPosition}px ; transition: 0.5s`"></div>
                                    <!-- 진짜 -->
                                    <!-- <div v-for="item in follow" v-bind:key="item" style="margin: 1%; transition: 0.5s">
                                <ProfileFollow :diameter="200" standard="px" @dialogChangeFromChild="dialogChange()" :src="`${port}images/${item.imageSequence}`" :data="item" />

                                {{ item.nickname }} -->

                                    <div v-for="item in follow" v-bind:key="item" style="padding: 1%; transition: 0.5s">
                                        <Profile :diameter="100" standard="px" @dialogChangeFromChild="dialogChange(item.memberSequence)" :src="`${port}/images/${item.imageSequence}`" />
                                        {{ item.nickname }}
                                    </div>
                                </div>
                            </v-col>
                            <v-col cols="12" md="1" align="right">
                                <v-btn @click="toRight()" color="white" depressed>
                                    <span class="material-icons-outlined"> chevron_right </span>
                                </v-btn>
                                <!-- <img src="@/assets/arrow.png" style="width: 30px; height: 30px" @click="toRight()" /> -->
                            </v-col>
                        </v-row>
                        <!-- 팔로워 -->
                        <v-row v-show="isFollower" class="card_section_follow" justify="center" align="center">
                            <v-col cols="12" md="1" align="left">
                                <v-btn @click="toLeft()" color="white" depressed>
                                    <span class="material-icons-outlined"> chevron_left </span>
                                </v-btn>
                                <!-- <img src="@/assets/arrow.png" style="width: 30px; height: 30px; transform: rotate(0.5turn)" @click="toLeft()" /> -->
                            </v-col>
                            <v-col cols="12" md="10">
                                <div class="d-flex align-center" style="overflow: hidden">
                                    <div :style="`margin-left : ${bannerPosition}px ; transition: 0.5s`"></div>
                                    <!-- <div v-for="item in follower" v-bind:key="item" style="margin: 1%; transition: 0.5s">
                                <Profile :diameter="200" standard="px" @dialogChangeFromChild="dialogChange()" :src="`${port}images/${item.imageSequence}`" :data="item" />

                                {{ item.nickname }} -->
                                    <div v-for="item in follower" v-bind:key="item" style="margin: 1%; transition: 0.5s">
                                        <Profile :diameter="100" standard="px" @dialogChangeFromChild="dialogChange(item.memberSequence)" :src="`${port}/images/${item.imageSequence}`" />
                                        {{ item.nickname }}
                                    </div>
                                </div>
                            </v-col>
                            <v-col cols="12" md="1" align="right">
                                <v-btn @click="toRight()" color="white" depressed>
                                    <span class="material-icons-outlined"> chevron_right </span>
                                </v-btn>
                                <!-- <img src="@/assets/arrow.png" style="width: 30px; height: 30px" @click="toRight()" /> -->
                            </v-col>
                        </v-row>
                    </v-col>
                </v-row>

                <!-- 달력파트 -->
                <v-row>
                    <v-col cols="12">
                        <MyPageCalendar v-if="studyCalendar !== null" :studyCalendar="studyCalendar" />
                    </v-col>
                </v-row>
            </v-col>

            <!-- 우측 여백 -->
            <v-col cols="12" md="3" />
        </v-row>

        <!-- 북마크한 글 -->
        <v-row>
            <v-col cols="12">
                <BoardList boardtitle="북마크한 글" :bookMarkList="bookMarkList" />
            </v-col>
        </v-row>
    </v-container>
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
import Profile from '@/components/mypage/Profile.vue';
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
            first: 'FRONT-END',
            second: 'BACK-END',
            firstValue: 100000,
            secondValue: 100001,
            // store에서 담아올 값
            profile: {},
            follow: [],
            follower: [],
            isFollowing: true,
            isFollower: false,
            bannerPosition: 0,
            studyAnalyzeValue: [],
            studyRecommand: [],
            bookMark: [],
            studyCalendar: null,
            bookMarkList: [],
            timeCategoryData: []
        };
    },
    computed: {
        chartData() {
            return {
                labels: ['FRONT-END', 'BACK-END', 'INFRA', 'MOBILE', 'ALGORITHM', 'CS'],
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
        ModalComponent,
        // TextButton,
        Profile
    },
    methods: {
        clicked(check) {
            if (check == 'following') {
                this.isFollowing = true;
                this.isFollower = false;
                this.bannerPosition = 0;
            } else {
                this.isFollowing = false;
                this.isFollower = true;
                this.bannerPosition = 0;
            }
        },
        movetoupdate() {
            window.location.href = '/mypage/update';
        },
        async dialogChange(memSeq) {
            this.dialog = true;
            console.log(memSeq);
            // this.$emit('dialogChangeFromChild');
            await this.$store.dispatch('moduleMyPage/getProfile', {id: memSeq});
            this.profile = this.$store.state.moduleMyPage.profile;
        },
        close(status) {
            if (status) {
                this.dialog = false;
            }
        },
        toLeft() {
            // if 문 써서 조절 하면 됨
            if (this.bannerPosition <= -200) {
                this.bannerPosition = this.bannerPosition + 200;
            }
        },
        toRight() {
            if (this.isFollowing && this.bannerPosition >= -20 * this.follow.length) {
                this.bannerPosition = this.bannerPosition - 200;
            }
            if (this.isFollower && this.bannerPosition >= -20 * this.follower.length) {
                this.bannerPosition = this.bannerPosition - 200;
            }
        },
        moveToselfTest() {
            window.location.href = '/self-test/problem/1';
        }
    },
    async created() {
        // 로그인한 유저 사진과 유저 정보 (닉네임,희망상태,희망진로)
        await this.$store.dispatch('moduleMyPage/getMyPageUser');
        this.user = this.$store.state.moduleMyPage.user;
        await this.$store.dispatch('moduleStudy/getMyStudies');
        this.studyCalendar = this.$store.state.moduleStudy.myStudies;
        await this.$store.dispatch('moduleMyPage/getBookMarkList');
        this.bookMarkList = this.$store.state.moduleMyPage.bookMarkList;
        //ff

        // 팔로잉
        await this.$store.dispatch('moduleTimer/getFollow');
        this.follow = this.$store.state.moduleTimer.follow;
        // 팔로워
        await this.$store.dispatch('moduleTimer/getFollower');
        this.follower = this.$store.state.moduleTimer.follower;
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

        // 원래 this.category.length 로 해야하지만, 일단 급한대로
        for (let i = 0; i < 6; i++) {
            this.chartData.datasets[0].data[i] = this.category[i].second;

            // this.chartData.datasets[0].data[i] = 300;
            if (this.category[i].second < this.firstValue) {
                this.secondValue = this.firstValue;
                this.second = this.first;
                this.firstValue = this.category[i].second;
                this.first = this.category[i].category;
            } else if (this.category[i].second < this.secondValue) {
                this.secondValue = this.category[i].second;
                this.second = this.category[i].category;
            } else {
                // 하하하
            }
        }

        let fir = false;
        let sec = false;
        for (let i = 0; i < this.chartData.datasets[0].data.length; i++) {
            if (!fir && this.chartData.datasets[0].data[i] == 0) {
                this.first = this.chartData.labels[i];
                fir = true;
            } else if (!sec && this.chartData.datasets[0].data[i] == 0) {
                this.second = this.chartData.labels[i];
                sec = true;
            } else {
                // 하하하
            }
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

<style>
.profile {
    padding: 20px;
    margin-bottom: 20px;
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    border-width: thin;
}
</style>
