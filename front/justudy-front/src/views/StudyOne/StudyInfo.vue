<template>
    <!-- <v-app> -->
    <v-container>
        <!-- 상단 스터디 정보 -->
        <v-row>
            <!-- 스터디 룸 아이콘 -->
            <v-col cols="12" md="5" justify="center" align="center">
                <!-- 스터디 이미지 -->
                <v-row>
                    <v-col>
                        <img class="img" style="width: 100%; border: 1px solid" :src="`${port}images/${studyInfo.imageSequence}`" alt="study_image" />
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="1" />
            <v-col cols="12" md="6">
                <v-row>
                    <v-col cols="4"><h3>스터디</h3></v-col>
                    <v-col cols="8"> {{ studyInfo.name }} </v-col>
                </v-row>
                <v-row>
                    <v-col cols="4"> <h3>Level</h3> </v-col>
                    <v-col cols="8"> {{ studyInfo.level }} </v-col>
                </v-row>
                <v-row>
                    <v-col cols="4"> <h3>팀장</h3> </v-col>
                    <v-col cols="8"> {{ studyInfo.leader }} </v-col>
                </v-row>
                <v-row>
                    <v-col cols="4"> <h3>on-off</h3> </v-col>
                    <v-col cols="8"> {{ studyInfo.meeting }} </v-col>
                </v-row>
                <v-row>
                    <v-col cols="4"> <h3>주제</h3> </v-col>
                    <v-col cols="8"> {{ studyInfo.bottomCategory }} </v-col>
                </v-row>
                <v-row>
                    <v-col cols="4">
                        <v-img :src="require('@/assets/git.png')" :style="{width: '30px'}" />
                    </v-col>
                    <v-col cols="8"> {{ studyInfo.github }} </v-col>
                </v-row>
                <v-row>
                    <v-col cols="4">
                        <v-img :src="require('@/assets/notion.png')" :style="{width: '30px'}" />
                    </v-col>
                    <v-col cols="8"> {{ studyInfo.notion }} </v-col>
                </v-row>
            </v-col>
        </v-row>

        <!-- 스터디룸 참가/단체알림 -->
        <v-row>
            <v-col cols="12" md="6" align="center">
                <!-- 스터디 닫혀있을 때 -->
                <v-btn v-if="!studyInfo.onAir" @click="onAirClicked('off')" :style="{color: 'black'}">
                    <v-col>
                        <span class="material-icons-outlined"> radio_button_checked </span>
                    </v-col>
                    <v-col cols="12" md="9">
                        <span color="white">스터디 시작하기</span>
                    </v-col>
                </v-btn>

                <!-- 스터디 열려있을때 -->
                <v-btn v-if="studyInfo.onAir" @click="onAirClicked('on')" :style="{color: 'crimson'}">
                    <v-col>
                        <span class="material-icons-outlined"> radio_button_checked </span>
                    </v-col>
                    <v-col cols="12" md="9">
                        <span color="white">스터디 참여하기</span>
                    </v-col>
                </v-btn>
            </v-col>
            <v-col cols="12" md="6" align="center">
                <!-- 단체 문자?알림? -->
                <v-btn @click="sendAllMessage" :style="{color: '#00B9FF'}">
                    <v-col>
                        <span class="material-icons-outlined"> send </span>
                    </v-col>
                    <v-col cols="12" md="9">
                        <span color="white">단체 알림보내기</span>
                    </v-col>
                </v-btn>
            </v-col>
        </v-row>

        <!-- 스터디 Description -->
        <v-row>
            <v-col cols="12">
                <div class="card_section">
                    <v-row :style="{padding: '1%'}"><h3 class="yellowText">Description</h3></v-row>
                    <v-row :style="{padding: '1%'}">
                        <v-col cols="12">
                            <div style="white-space: pre">
                                {{ studyInfo.introduction }}
                            </div>
                        </v-col>
                    </v-row>
                </div>
            </v-col>
        </v-row>

        <!-- 스터디 멤버 -->
        <v-row>
            <v-col cols="12">
                <div class="card_section">
                    <!-- 멤버 표시 -->
                    <v-row justify="center" align="center">
                        <v-col cols="12" style="display: inline-flex; align-items: center">
                            <h3 class="yellowText">멤버</h3>
                            <h3 style="padding: 10px">{{ studyInfo.member.length }} / {{ studyInfo.population }}</h3>
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col cols="12" md="6" v-for="member in studyInfo.member" :key="member.sequence">
                            <div class="card_section_member">
                                <v-row justify="center" align="center" :style="{padding: '-12%'}">
                                    <v-col cols="12" md="2">
                                        <!-- 1등 -->
                                        <v-avatar v-if="studyInfo.member.length > 0 && studyInfo.member[0] == member" size="40">
                                            <v-img :src="require('@/assets/1st.png')" />
                                        </v-avatar>

                                        <!-- 2등 -->
                                        <v-avatar v-if="studyInfo.member.length > 1 && studyInfo.member[1] == member" size="40">
                                            <v-img :src="require('@/assets/2nd.png')" />
                                        </v-avatar>

                                        <!-- 3등 -->
                                        <v-avatar v-if="studyInfo.member.length > 2 && studyInfo.member[2] == member" size="40">
                                            <v-img :src="require('@/assets/3rd.png')" />
                                        </v-avatar>
                                    </v-col>
                                    <v-col cols="12" md="3" justify="center" align="center">
                                        <v-avatar size="40">
                                            <img :src="`${port}images/${member.imageSequence}`" alt="member" />
                                        </v-avatar>
                                    </v-col>
                                    <v-col cols="12" md="3" justify="center" align="center">
                                        <v-row>
                                            <div :style="{fontSize: 'large'}" justify="center" align="center">
                                                {{ member.nickName }}
                                            </div>
                                        </v-row>
                                    </v-col>
                                    <v-col cols="12" md="4" justify="center" align="center"> {{ member.badge }}점 </v-col>
                                </v-row>
                            </div>
                        </v-col>
                    </v-row>
                </div>
            </v-col>
        </v-row>

        <!-- 스터디 수정하기 -->
        <v-row v-if="studyInfo.isLeader">
            <v-col cols="12" align="end">
                <v-btn text @click="moveToEdit()" :style="{color: 'green'}" depressed> 정보 수정하기 <span class="material-icons-outlined"> edit_note </span> </v-btn>
            </v-col>
        </v-row>

        <!-- 스터디 멤버 관리 -->
        <v-row v-if="studyInfo.isLeader">
            <v-col cols="12" align="end">
                <v-btn text @click="moveToManager()" :style="{color: 'green'}" depressed> 스터디 멤버/신청 관리 <span class="material-icons-outlined"> edit_note </span> </v-btn>
            </v-col>
        </v-row>

        <!-- 스터디 탈퇴하기 -->
        <v-row v-if="studyInfo.isMember">
            <v-col cols="12" align="end">
                <v-btn text @click="withdrawDialog = true" :style="{color: 'crimson'}"> 탈퇴하기 <span class="material-icons-outlined"> logout </span> </v-btn>
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
    <!-- </v-app> -->
</template>

<script>
import port from '@/store/port';
import {mapState} from 'vuex';

export default {
    name: 'StudyInfo',
    computed: {
        ...mapState('moduleStudy', ['studyInfo'])
    },
    async created() {
        const studySeq = this.$route.params.studySeq;
        await this.$store.dispatch('moduleStudy/getStudyInfo', studySeq);
        if (this.studyInfo.isMember == false && this.studyInfo.isLeader == false) {
            window.location.href = '/error';
        }
    },
    data() {
        return {
            withdrawDialog: false, //탈퇴 모달창
            port: port
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
            localStorage.setItem('studyRoomPersonName', localStorage.getItem('nickname'));
            localStorage.setItem('studyRoomRoomName', this.studyInfo.roomSequence);
            this.$router.push('/meeting/room');
        },
        sendAllMessage() {
            //알림을 보낸다.
        },
        moveToManager() {
            //스터디 멤버/신청관리로 이동
            window.location.href = `/study/${this.studyInfo.sequence}/memberManage`;
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
    background-color: #ffed77;
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
