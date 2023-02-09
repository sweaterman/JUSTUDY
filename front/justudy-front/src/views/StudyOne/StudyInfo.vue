<template>
    <v-app>
        <v-container>
            <!-- 상단 스터디 정보 -->
            <v-row>
                <!-- 스터디 룸 아이콘 -->
                <v-col col="12" md="5">
                    <!-- 스터디 이미지 -->
                    <v-row>
                        <v-col cols="12">
                            <img class="img" style="width: 100%" src="@/assets/study_320x180.png" alt="study_image" />
                        </v-col>
                    </v-row>

                    <v-row v-if="!roomOpen" justify="center">
                        <!-- 바로 참여하기 -->
                        <v-col cols="6" align="center">
                            <v-btn rounded color="red" dark>바로 참여하기</v-btn>
                        </v-col>
                        <!-- 알림보내기 -->
                        <v-col cols="6" align="center">
                            <v-btn rounded color="yellow">알림 보내기</v-btn>
                        </v-col>
                    </v-row>

                    <v-row v-if="roomOpen" justify="center">
                        <v-col cols="12" align="center">
                            <v-btn rounded color="yellow">스터디방 열기</v-btn>
                        </v-col>
                    </v-row>
                </v-col>

                <v-col col="12" md="7">
                    <v-row>
                        <v-col cols="2"> 스터디 </v-col>
                        <v-col cols="10"> {{ studyInfo.name }} </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="2"> level </v-col>
                        <v-col cols="10"> {{ studyInfo.level }} </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="2"> 팀장 </v-col>
                        <v-col cols="10"> {{ studyInfo.leader }} </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="2"> on-off </v-col>
                        <v-col cols="10"> {{ studyInfo.meeting }} </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="2"> 주제 </v-col>
                        <v-col cols="10"> {{ studyInfo.category }} </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="2"> Github </v-col>
                        <v-col cols="10"> {{ studyInfo.github }} </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="2"> Notion </v-col>
                        <v-col cols="10"> {{ studyInfo.notion }} </v-col>
                    </v-row>
                </v-col>
            </v-row>

            <!-- 구분선 -->
            <v-row>
                <v-col>
                    <v-divider></v-divider>
                </v-col>
            </v-row>

            <v-row>
                <v-col cols="2"> Description </v-col>
                <v-col cols="10"> {{ studyInfo.information }} </v-col>
            </v-row>

            <!-- 구분선 -->
            <v-row><v-divider></v-divider></v-row>

            <!-- 스터디 멤버 표시하는 부분 -->
            <v-row>
                <v-col cols="12" md="2">
                    <v-row>
                        <v-col cols="12" align="center">
                            <h3 class="yellowText">멤버</h3>
                        </v-col>
                        <v-col cols="12" align="center">
                            <h3>{{ studyInfo.member.length }} / {{ studyInfo.population }}</h3>
                        </v-col>
                    </v-row>
                </v-col>
                <v-col cols="12" md="1" v-for="member in studyInfo.member" :key="member.sequence">
                    <v-avatar size="80">
                        <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
                    </v-avatar>
                    <div>
                        {{ member.nickname }}
                    </div>
                </v-col>
            </v-row>
            <v-row>
                <v-divider></v-divider>
            </v-row>

            <!-- 스터디왕 부분 -->
            <v-row>
                <v-col cols="12" align="center">
                    <h3 class="yellowText">스터디왕</h3>
                </v-col>
            </v-row>
            <v-row>
                <!-- 2등 -->
                <v-col cols="4" align="center">
                    <v-avatar size="100" v-if="studyInfo.member.length >= 2">
                        <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
                    </v-avatar>
                    <h1 v-if="studyInfo.member.length >= 2">2위 : {{ studyInfo.member[1].nickname }}</h1>
                    <h2 v-if="studyInfo.member.length >= 2">{{ studyInfo.member[1].badge }}</h2>
                </v-col>
                <!-- 1등 -->
                <v-col cols="4" align="center">
                    <v-avatar size="120">
                        <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
                    </v-avatar>
                    <h1>1위 : {{ studyInfo.member[0].nickname }}</h1>
                    <h2>{{ studyInfo.member[0].badge }}</h2>
                </v-col>
                <!-- 3등 -->
                <v-col cols="4" align="center">
                    <v-avatar size="80" v-if="studyInfo.member.length >= 3">
                        <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
                    </v-avatar>
                    <h1 v-if="studyInfo.member.length >= 3">3위 : {{ studyInfo.member[2].nickname }}</h1>
                    <h2 v-if="studyInfo.member.length >= 3">{{ studyInfo.member[2].badge }}</h2>
                </v-col>
            </v-row>

            <v-row v-if="studyInfo.isLeader">
                <v-col cols="12" align="end">
                    <v-btn text @click="moveToEdit()">스터디 정보 수정하기</v-btn>
                </v-col>
            </v-row>

            <v-row v-if="studyInfo.isMember">
                <v-col cols="12" align="end">
                    <v-btn text @click="withdrawDialog = true">스터디 탈퇴하기</v-btn>
                </v-col>
            </v-row>

            <!-- 스터디 탈퇴하기 모달 -->
            <v-dialog v-model="withdrawDialog" width="800">
                <v-card>
                    <v-card-title> 스터디 탈퇴 </v-card-title>

                    <v-card-text> 스터디 탈퇴 시, 다시 가입하려면 방장에게 가입 요청 메시지를 보내야 합니다. 정말로 탈퇴하시겠습니까? </v-card-text>

                    <v-divider></v-divider>

                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="primary" text @click="withdraw()"> 탈퇴 </v-btn>
                        <v-btn color="primary" text @click="withdrawDialog = false"> 취소 </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-container>
    </v-app>
</template>

<script>
import {mapState} from 'vuex';

export default {
    name: 'StudyInfo',
    computed: {
        //스터디룸 열려있는지 확인하는 부분 추가
        //스터디이미지 받아오는 부분 추가
        ...mapState('moduleStudy', ['studyInfo'])
    },
    async created() {
        const pathName = new URL(document.location).pathname.split('/');
        const studySeq = pathName[pathName.length - 2];
        await this.$store.dispatch('moduleStudy/getStudyInfo', studySeq);
        if (this.studyInfo.isMember == false && this.studyInfo.isLeader == false) {
            window.location.href = '/error';
        }
    },
    data() {
        return {
            // studyInfo: {
            //     sequence: 1,
            //     name: '리액트 용자 모임',
            //     leader: '이연희',
            //     population: 10,
            //     category: 'Vue',
            //     member: [
            //         {
            //             sequence: 1,
            //             nickname: '이연희',
            //             name: '이연희이름',
            //             badge: 6542
            //         },
            //         {
            //             sequence: 2,
            //             nickname: '김싸피임',
            //             name: '김싸피',
            //             badge: 5422
            //         },
            //         {
            //             sequence: 3,
            //             nickname: '박싸피임',
            //             name: '박싸피룰루',
            //             badge: 546
            //         }
            //     ],
            //     level: '초보',
            //     meeting: '온/오프라인',
            //     isOpen: true,
            //     github: 'http://this.is.git.link',
            //     notion: 'http://this.is.notion.link',
            //     start_time: '2023.01.02',
            //     information: `주제: 리액트입니다 \n
            //     목표: 모두 리액트 마스터하기 \n
            //     예상 커리큘럼: Do it 리액트! 책을 순서대로 공부합니다. \n
            //     개설 동기: 리액트를 너무 공부하고 싶었어요.. \n
            //     주의사항: 노쇼 금지! `,
            //     isLeader: true,
            //     isMember: false
            // },
            roomOpen: false, //스터디화상룸이 열려있는지 확인하는 변수
            withdrawDialog: false //탈퇴 모달창
        };
    },
    methods: {
        async withdraw() {
            this.withdrawDialog = false;
            await this.$store.dispatch('moduleStudy/withdrawStudy', this.studyInfo.sequence);
            window.location.href = `/study/myStudy`;
        },
        moveToEdit() {
            window.location.href = `/study/${this.studyInfo.sequence}/edit`;
        }
    }
};
</script>

<style scoped>
.yellowText {
    display: flex;
    align-items: center;
    justify-content: center;
    width: fit-content;
    height: 40px;
    background-color: #ffd200;
    border-radius: 30px;
    padding: 20px;
}
</style>
