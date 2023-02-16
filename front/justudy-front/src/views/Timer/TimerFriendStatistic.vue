<template>
    <v-app>
        <v-row :style="{marginTop: '0.5%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <!-- 타이머 메뉴 바 -->
                <v-row justify="center" align="center">
                    <v-col cols="12" md="6" justify="center" align="center">
                        <router-link to="/timer/study-start" style="text-decoration: none; color: black">
                            <span class="material-icons-outlined"> timer </span>
                            <div>타이머</div>
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="6" justify="center" align="center">
                        <router-link to="/timer/friend-statistics" style="text-decoration: none; color: #ffb000">
                            <span class="material-icons-outlined"> show_chart </span>
                            <div>통계</div>
                        </router-link>
                    </v-col>
                </v-row>

                <v-row>
                    <v-btn @click="clicked('following')">팔로잉</v-btn>
                    <v-btn @click="clicked('follower')">팔로워</v-btn>
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
                        <img src="@/assets/arrow.png" style="width: 30px; height: 30px; transform: rotate(0.5turn)" @click="toLeft()" />
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

                <v-row :style="{marginTop: '3%'}">
                    <!-- 해당 친구 프로필 -->
                    <v-col class="card_section_profile" cols="12" md="4">
                        <v-row>
                            <v-col justify="center" align="center">
                                <Profile :diameter="100" standard="px" :src="`${port}/images/${user.imageSequence}`" />
                                이름 : {{ user.nickname }}
                                <br />
                                꿈 : {{ user.dream ? user.dream : '없음' }}
                                <br />
                                뱃지카운트 : +{{ user.badgeCount }}
                                <br />
                                레벨 : {{ user.level }}
                                <h2>자기소개</h2>

                                {{ user.introduction ? user.introduction : '소개글이 없습니다.' }}
                            </v-col>
                        </v-row>

                        <v-row>
                            <!-- <div id="modal" @click="close(false)" style="padding: 100px 300px">
                                <div class="d-flex justify-center">
                                    <div class="d-flex flex-column pa-12">
                                        <div>
                                            <div class="d-flex justify-space-around">
                                                <p>닉네임</p>
                                                <h1>{{ data.nickname }}</h1>
                                            </div>

                                            <hr />
                                        </div>
                                        <div v-if="loaded">
                                            <div class="d-flex justify-space-around">
                                                <p>선호하는 기술 스택</p>
                                                <h1>{{ profile }}</h1>
                                            </div>

                                            <hr />
                                        </div>
                                        <div>
                                            <div class="d-flex justify-space-around">
                                                <p>희망진로</p>
                                                <h1>{{ data.dream ? data.dream : '없음' }}</h1>
                                            </div>

                                            <hr />
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex justify-center">
                                    <div>
                                        <h1 style="font-size: 64px">자기소개</h1>
                                        <h1>안녕하세요</h1>
                                        <h1>만능 개발자 입니다.</h1>
                                        <h1>타이머 대결 환영합니다.</h1>
                                    </div>
                                    <RadarChart buttonContent="학습 진행도" :hasButton="true" />
                                </div>
                            </div> -->

                            <ModalComponent :dialog="dialog" @closeModal="close" :id="seq" :src="src" :data="data" />
                        </v-row>
                    </v-col>
                    <!-- 나의 공부 시간 -->
                    <v-col cols="12" md="4">
                        <v-row>
                            <v-col>
                                <DigitalClockPerDate content="이번주 공부 시간" :allTime="weekTime" />
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col>
                                <DigitalClockPerDate content="이번달 공부 시간" :allTime="monthTime" />
                            </v-col>
                        </v-row>
                    </v-col>
                    <!-- 나의 평균 비교 -->
                    <v-col cols="12" md="4">
                        <v-row>
                            <v-col>
                                <DigitalClockAverage content="THIS WEEK" :studyTime="weekTime - averageWeekTime" :averageTime="averageWeekTime" />
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col>
                                <DigitalClockAverage content="THIS MONTH" :studyTime="monthTime - averageMonthTime" :averageTime="averageMonthTime" />
                            </v-col>
                        </v-row>
                    </v-col>
                </v-row>
            </v-col>
            <!-- {{ user }} -->
            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>
<script>
// import ProfilePicture from '@/components/mypage/ProfilePicture.vue';
// import RadarChart from '@/components/common/RadarChart.vue';
// import Follow from '@/components/common/Follow.vue';
// import Follower from '@/components/common/Follower.vue';
import ModalComponent from '@/components/mypage/ModalComponent.vue';
import Profile from '@/components/mypage/Profile.vue';
import DigitalClockPerDate from '@/components/timer/DigitalClockPerDate.vue';
import DigitalClockAverage from '@/components/timer/DigitalClockAverage.vue';
import port from '@/store/port';
export default {
    name: 'MyStatistics',
    data() {
        return {
            isFollowing: true,
            isFollower: false,
            bannerPosition: 0,
            weekTime: 0,
            monthTime: 0,
            averageWeekTime: 0,
            averageMonthTime: 0,
            studyCategory: [],
            port: port,
            user: {},
            profile: {},
            follow: [],
            follower: []
        };
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
        async dialogChange(memSeq) {
            console.log(memSeq);
            // this.$emit('dialogChangeFromChild');
            await this.$store.dispatch('moduleMyPage/getProfile', {id: memSeq});

            this.user = this.$store.state.moduleMyPage.profile;

            await this.$store.dispatch('moduleTimer/getStudyTimeWeek', {nickName: this.user.nickname});
            this.weekTime = this.$store.state.moduleTimer.studyTimeWeek.time;
            await this.$store.dispatch('moduleTimer/getStudyTimeMonth', {nickName: this.user.nickname});
            this.monthTime = this.$store.state.moduleTimer.studyTimeMonth.time;
            await this.$store.dispatch('moduleTimer/getAverageMembersWeek');
            this.averageWeekTime = this.$store.state.moduleTimer.averageMemberWeek.time;
            await this.$store.dispatch('moduleTimer/getAverageMembersMonth');
            this.averageMonthTime = this.$store.state.moduleTimer.averageMemberMonth.time;
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
        }
    },
    components: {
        // ProfilePicture,
        // RadarChart,
        // Follow,
        // Follower,
        ModalComponent,
        Profile,
        DigitalClockPerDate,
        DigitalClockAverage
    },
    async created() {
        await this.$store.dispatch('moduleMyPage/getMyPageUser');
        this.user = this.$store.state.moduleMyPage.user;
        await this.$store.dispatch('moduleTimer/getStudyTimeWeek', {nickName: this.user.nickname});
        this.weekTime = this.$store.state.moduleTimer.studyTimeWeek.time;
        await this.$store.dispatch('moduleTimer/getStudyTimeMonth', {nickName: this.user.nickname});
        this.monthTime = this.$store.state.moduleTimer.studyTimeMonth.time;
        await this.$store.dispatch('moduleTimer/getAverageMembersWeek');
        this.averageWeekTime = this.$store.state.moduleTimer.averageMemberWeek.time;
        await this.$store.dispatch('moduleTimer/getAverageMembersMonth');
        this.averageMonthTime = this.$store.state.moduleTimer.averageMemberMonth.time;
        // await this.$store.dispatch('moduleTimer/getStudyCategory', {seq: 50});
        // this.studyCategory = this.$store.state.moduleTimer.studyCategory;
        await this.$store.dispatch('moduleTimer/getFollow');
        this.follow = this.$store.state.moduleTimer.follow;
        await this.$store.dispatch('moduleTimer/getFollower');
        this.follower = this.$store.state.moduleTimer.follower;
    }
};
</script>
<style scoped>
.card_section_follow {
    padding: 5px;
    margin-bottom: 2px;
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
.card_section_profile {
    padding: 5px;
    margin-bottom: 2px;
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
</style>
