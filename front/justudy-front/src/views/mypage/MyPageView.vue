<template>
    <v-app>
        <div>
            <ModalComponent :dialog="dialog" @closeModal="close" />
        </div>
        <div style="margin: 10%">
            <v-row>
                <v-col cols="12" md="4">
                    <ProfilePicture :diameter="300" :buttonLength="200" :height="70" :fontSize="32" content="LV" standard="px" :src="require('@/assets/middleClass.png')" />
                </v-col>
                <v-col cols="12" md="8">
                    <ProfileDetail />
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    <Boundary content="북마크한 글" standard="px" :startPoint="100" :hasContent="false"></Boundary>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12">
                    <Follow buttonContent="팔로잉" @dialogChangeFromChild="dialogChange()" />
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12">
                    <Follow buttonContent="팔로우" />
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    <Boundary content="북마크한 글" standard="px" :startPoint="100" :hasContent="false"></Boundary>
                </v-col>
            </v-row>
            <!-- <YellowButton :buttonLength="300" :height="65" :fontSize="36" content="아카아칸" standard="px"/> -->
            <v-row>
                <v-col>
                    <div style="height: 40px"></div>
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    <RadarChart buttonContent="학습 진행도" :hasButton="true" />
                </v-col>
                <v-col>
                    <v-row>
                        <v-col>
                            <StudyAnalyze />
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col>
                            <RecommandStudy />
                        </v-col>
                    </v-row>
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    <div style="height: 40px"></div>
                    <Boundary content="북마크한 글" standard="px" :startPoint="100"></Boundary>
                </v-col>
            </v-row>
            <ListBookMark />
            <div style="height: 100px"></div>
            <Boundary content="이번달 달력" standard="px" :startPoint="150"></Boundary>
            <Calendar />
        </div>
    </v-app>
</template>
<script>
// import YellowButton from "../components/mypage/YellowButton.vue"
import Boundary from '@/components/mypage/Boundary.vue';
import ListBookMark from '@/components/mypage/ListBookMark.vue';
import StudyAnalyze from '@/components/mypage/StudyAnalyze.vue';
import RecommandStudy from '@/components/mypage/RecommandStudy.vue';
import ProfileDetail from '@/components/mypage/ProfileDetail.vue';
import Follow from '@/components/mypage/Follow.vue';
import ProfilePicture from '@/components/mypage/ProfilePicture.vue';
import Calendar from '@/components/mypage/Calendar.vue';
import RadarChart from '@/components/mypage/RadarChart.vue';
import ModalComponent from '@/components/mypage/ModalComponent.vue';

export default {
    name: 'MyPageView',
    data() {
        return {
            dialog: false
        };
    },
    computed: {
        // ...mapState(['aaa'])
        // userLoginIdx(){
        // return this.$store.state.user.userIdx;
        // },
    },
    components: {
        // YellowButton,
        Boundary,
        ListBookMark,
        StudyAnalyze,
        RecommandStudy,
        ProfileDetail,
        Follow,
        ProfilePicture,
        Calendar,
        RadarChart,
        ModalComponent
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
        //     this.$store.dispatch("follow/getFollowingList", {
        //   followingIdx: this.$route.params.idx,
        // });
    }
};
</script>
