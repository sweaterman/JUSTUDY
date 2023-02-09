<template>
    <v-app>
        <v-container>
            <!-- 상단 스터디 정보 -->
            <v-row>
                <!-- 스터디 룸 아이콘 -->
                <v-col cols="12" md="5" :style="{padding: '2%'}" justify="center" align="center">
                    <!-- 스터디 이미지 -->
                    <v-row>
                        <v-col>
                            <img class="img" style="width: 100%" src="@/assets/study_320x180.png" alt="study_image" />
                        </v-col>
                    </v-row>

                    <v-row v-if="!roomOpen" justify="center">
                        <!-- 바로 참여하기 -->
                        <v-col cols="6" align="center">
                            <!-- <v-btn v-if="!checkName" @click="ntc" :style="{color: 'crimson'}"> -->
                            <v-btn v-if="!onAir" disabled :style="{color: 'black'}">
                                <v-col>
                                    <span class="material-icons-outlined"> radio_button_checked </span>
                                </v-col>
                                <v-col cols="12" md="9">
                                    <span color="white">지금 참여하기</span>
                                </v-col>
                            </v-btn>
                            <v-btn v-if="onAir" @click="onAirClicked('on')" :style="{color: 'crimson'}">
                                <v-col>
                                    <span class="material-icons-outlined"> radio_button_checked </span>
                                </v-col>
                                <v-col cols="12" md="9">
                                    <span color="white">지금 참여하기</span>
                                </v-col>
                            </v-btn>
                        </v-col>
                        <!-- 알림보내기 -->
                        <v-col cols="6" align="center">
                            <!-- <v-btn v-if="!checkName" @click="ntc" :style="{color: 'crimson'}"> -->
                            <v-btn @click="ntc" :style="{color: '#00B9FF'}">
                                <v-col>
                                    <span class="material-icons-outlined"> send </span>
                                </v-col>
                                <v-col cols="12" md="9">
                                    <span color="white">단체 문자</span>
                                </v-col>
                            </v-btn>
                        </v-col>
                    </v-row>

                    <v-row v-if="roomOpen" justify="center">
                        <v-col cols="12" align="center">
                            <v-btn rounded color="yellow">스터디방 열기</v-btn>
                        </v-col>
                    </v-row>
                </v-col>
                <v-col cols="12" md="1" />
                <v-col cols="12" md="6">
                    <v-row>
                        <v-col cols="2"><h3>스터디</h3></v-col>
                        <v-col cols="10"> {{ studyInfo.name }} </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="2"> <h3>Level</h3> </v-col>
                        <v-col cols="10"> {{ studyInfo.level }} </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="2"> <h3>팀장</h3> </v-col>
                        <v-col cols="10"> {{ studyInfo.leader }} </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="2"> <h3>on-off</h3> </v-col>
                        <v-col cols="10"> {{ studyInfo.meeting }} </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="2"> <h3>주제</h3> </v-col>
                        <v-col cols="10"> {{ studyInfo.category }} </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="2">
                            <v-img :src="require('@/assets/git.png')" :style="{width: '30px'}" />
                        </v-col>
                        <v-col cols="10"> {{ studyInfo.github }} </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="2">
                            <v-img :src="require('@/assets/notion.png')" :style="{width: '30px'}" />
                        </v-col>
                        <v-col cols="10"> {{ studyInfo.notion }} </v-col>
                    </v-row>
                </v-col>
            </v-row>

            <!-- 구분선 -->
            <v-row>
                <v-col cols="12" md="6">
                    <div class="card_section">
                        <v-row :style="{padding: '4%'}">
                            <v-row :style="{padding: '1%'}"><h2>스터디 상세 설명</h2></v-row>
                            <v-row :style="{padding: '1%'}">
                                {{ studyInfo.information }}
                            </v-row>
                        </v-row>
                    </div>
                </v-col>

                <v-col cols="12" md="6">
                    <!-- 스터디 멤버 표시하는 부분 -->
                    <div class="card_section">
                        <v-row justify="center" align="center">
                            <v-col cols="12" md="3" justify="center" align="center">
                                <h2>멤버</h2>
                            </v-col>
                            <v-col cols="12" md="9" align="left">
                                <h3>{{ studyInfo.member.length }} / {{ studyInfo.population }}</h3>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="12" md="2" :style="{marginTop: '2%'}">
                                <v-row :style="{padding: '10%'}" justify="center" align="center"><v-img :src="require('@/assets/1st.png')" /></v-row>
                                <v-row :style="{padding: '10%'}" justify="center" align="center"><v-img :src="require('@/assets/2nd.png')" /></v-row>
                                <v-row :style="{padding: '10%'}" justify="center" align="center"><v-img :src="require('@/assets/3rd.png')" /></v-row>
                            </v-col>
                            <v-col cols="12" md="10">
                                <v-row v-for="member in studyInfo.member" :key="member.sequence">
                                    <v-col>
                                        <div class="card_section_member">
                                            <v-row justify="center" align="center" :style="{padding: '-12%'}">
                                                <v-col cols="12" md="3" justify="center" align="center">
                                                    <v-avatar size="40">
                                                        <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
                                                    </v-avatar>
                                                </v-col>
                                                <v-col cols="12" md="4" justify="center" align="center">
                                                    <v-row>
                                                        <div :style="{fontSize: 'large'}" justify="center" align="center">
                                                            {{ member.nickname }}
                                                        </div>
                                                    </v-row>
                                                </v-col>
                                                <v-col cols="12" md="5" justify="center" align="center"> {{ member.badge }} 점 </v-col>
                                            </v-row>
                                        </div>
                                    </v-col>
                                </v-row>
                            </v-col>
                        </v-row>
                    </div>
                </v-col>
            </v-row>

            <!-- 스터디 수정하기 -->
            <v-row v-if="studyInfo.isLeader">
                <v-col cols="12" align="end">
                    <v-btn @click="moveToEdit()" :style="{color: 'green'}" depressed>
                        <span class="material-icons-outlined"> edit_note </span>
                    </v-btn>
                </v-col>
            </v-row>

            <!-- 스터디 탈퇴하기 -->
            <v-row v-if="studyInfo.isMember">
                <v-col cols="12" align="end">
                    <v-btn text @click="withdrawDialog = true" :style="{color: 'crimson'}">
                        <span class="material-icons-outlined"> logout </span>
                    </v-btn>
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
            studyInfo: {
                sequence: 1,
                name: '리액트 용자 모임',
                leader: '이연희',
                population: 10,
                category: 'Vue',
                member: [
                    {
                        sequence: 1,
                        nickname: '이연희',
                        name: '이연희이름',
                        badge: 6542
                    },
                    {
                        sequence: 2,
                        nickname: '김싸피임',
                        name: '김싸피',
                        badge: 5422
                    },
                    {
                        sequence: 3,
                        nickname: '박싸피임',
                        name: '박싸피룰루',
                        badge: 546
                    },
                    {
                        sequence: 4,
                        nickname: '사싸피임',
                        name: '사싸피룰루',
                        badge: 246
                    },
                    {
                        sequence: 5,
                        nickname: '오싸피임',
                        name: '오싸피룰루',
                        badge: 146
                    }
                ],
                level: '초보',
                meeting: '온/오프라인',
                isOpen: true,
                github: 'http://this.is.git.link',
                notion: 'http://this.is.notion.link',
                start_time: '2023.01.02',
                information: `주제: 리액트입니다 \n
                목표: 모두 리액트 마스터하기 \n
                예상 커리큘럼: Do it 리액트! 책을 순서대로 공부합니다. \n
                개설 동기: 리액트를 너무 공부하고 싶었어요.. \n
                주의사항: 노쇼 금지! `,
                isLeader: false,
                isMember: true
            },
            onAir: true, // false 로 변경해야
            person: '', // 변경 필수
            room: '1', // 변경 필수

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
        },
        onAirClicked() {
            this.onAir = !this.onAir;
            localStorage.setItem('studyRoomPersonName', this.person);
            localStorage.setItem('studyRoomRoomName', this.room);
            this.$router.push('/meeting/room');
        }
    }
};
</script>

<style scoped>
.card_section {
    padding: 30px;
    /* height: 500px; */
    margin-bottom: 5px;
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
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
.card_section_member {
    padding: 10px;
    /* margin-bottom: 1px; */
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
</style>
