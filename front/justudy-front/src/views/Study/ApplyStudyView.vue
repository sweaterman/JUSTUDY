<template>
    <v-app>
        <v-container>
            <v-row style="margin-top: 20px">
                <!-- 왼쪽 여백 -->
                <v-col cols="0" md="2"></v-col>

                <!-- 스터디 이미지와 소개란 -->
                <v-col align="center" justify="center" cols="12" md="5">
                    <v-row>
                        <v-col cols="12">
                            <img class="hover" style="width: 95%" :src="`${port}images/${applyStudyInfo.imageSequence}`" alt="study_image" />
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col cols="12">
                            <h1>{{ applyStudyInfo.name }}</h1>
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col cols="12">
                            <v-textarea no-resize rows="20" solo color="#000000" v-model="applyStudyInfo.introduction" outlined></v-textarea>
                        </v-col>
                    </v-row>
                </v-col>

                <!-- 스터디 상세 정보 -->
                <v-col cols="12" md="3">
                    <!-- 스터디 정보 전체를 감싸고 있는 div -->
                    <div class="infoDiv">
                        <v-row>
                            <v-col align="center" style="margin-bottom: 20px; margin-top: 20px">
                                <h2>{{ applyStudyInfo.name }}</h2>
                            </v-col>
                        </v-row>

                        <!-- 팀장 -->
                        <v-row>
                            <v-col cols="5">
                                <v-subheader>팀장</v-subheader>
                            </v-col>
                            <v-col align-self="center" cols="7">
                                {{ applyStudyInfo.leader }}
                            </v-col>
                        </v-row>

                        <!-- 카테고리 -->
                        <v-row>
                            <v-col cols="5">
                                <v-subheader>카테고리</v-subheader>
                            </v-col>
                            <v-col align-self="center" cols="7">
                                {{ applyStudyInfo.topCategory }}
                            </v-col>
                        </v-row>

                        <!-- 상세 -->
                        <v-row>
                            <v-col cols="5">
                                <v-subheader>상세</v-subheader>
                            </v-col>
                            <v-col align-self="center" cols="7">
                                {{ applyStudyInfo.bottomCategory }}
                            </v-col>
                        </v-row>

                        <!-- 모집인원 -->
                        <v-row>
                            <v-col cols="5">
                                <v-subheader>모집인원</v-subheader>
                            </v-col>
                            <v-col cols="7" align-self="center"> {{ applyStudyInfo.member.length }} / {{ applyStudyInfo.population }} </v-col>
                        </v-row>

                        <!-- LEVEL -->
                        <v-row>
                            <v-col cols="5">
                                <v-subheader>LEVEL</v-subheader>
                            </v-col>
                            <v-col cols="7" align-self="center">
                                {{ applyStudyInfo.level }}
                            </v-col>
                        </v-row>

                        <!-- 활동 주기 -->
                        <v-row>
                            <v-col cols="5">
                                <v-subheader>활동 주기</v-subheader>
                            </v-col>
                            <v-col cols="7" align-self="center"> {{ applyStudyInfo.frequency.week }} / {{ applyStudyInfo.frequency.startTime }} ~ {{ applyStudyInfo.frequency.endTime }} </v-col>
                        </v-row>

                        <!-- 모임 -->
                        <v-row>
                            <v-col cols="5">
                                <v-subheader>모임</v-subheader>
                            </v-col>
                            <v-col cols="7" align-self="center">
                                {{ applyStudyInfo.meeting }}
                            </v-col>
                        </v-row>

                        <!-- 시작예정일 -->
                        <v-row>
                            <v-col cols="5">
                                <v-subheader>시작예정일</v-subheader>
                            </v-col>
                            <v-col cols="7" align-self="center">
                                {{ applyStudyInfo.startTime }}
                            </v-col>
                        </v-row>

                        <!-- 지원하기 -->
                        <v-row>
                            <v-col style="display: flex; justify-content: center">
                                <v-btn v-if="applyDisplay" style="width: 200px" x-large rounded color="yellow" @click="applyDialog('open')"> 지원하기 </v-btn>
                                <v-btn v-if="!applyDisplay" style="width: 200px" x-large rounded color="gray" disabled> 지원완료 </v-btn>

                                <!-- 지원 모달창 -->
                                <v-dialog v-model="applyData" persistent max-width="700">
                                    <v-card>
                                        <v-card-title class="text-h5 grey lighten-2"> 신청하기 </v-card-title>

                                        <v-card-text>
                                            <br />
                                            스터디장에게 남기고 싶은 메시지
                                            <v-textarea outlined rows="10" :rules="message_rules" v-model="sendData.content"></v-textarea>
                                        </v-card-text>

                                        <v-card-actions>
                                            <v-spacer></v-spacer>
                                            <v-btn color="green darken-1" text @click="applyDialog('T')"> 확인 </v-btn>
                                            <v-btn color="green darken-1" text @click="applyDialog('F')"> 취소 </v-btn>
                                        </v-card-actions>
                                    </v-card>
                                </v-dialog>
                            </v-col>
                        </v-row>
                    </div>
                </v-col>

                <!-- 오른쪽 여백 -->
                <v-col cols="0" md="2"></v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
import port from '@/store/port';
import {mapState} from 'vuex';

export default {
    name: 'ApplyStudyView',
    computed: {
        ...mapState('moduleStudy', ['applyStudyInfo']),
        ...mapState('moduleLogin', ['isLogin'])
    },
    async created() {
        const studySeq = this.$route.params.studySeq;
        await this.$store.dispatch('moduleStudy/getApplyStudyInfo', studySeq);
        this.sendData.studySeq = studySeq;

        //스터디 멤버에 내가 포함되어있거나 지원을 완료한 상태라면? applyDisplay 바꿔야함
        if (this.applyStudyInfo.isApply == true || this.applyStudyInfo.isMember == true || this.applyStudyInfo.isLeader == true) {
            this.applyDisplay = false;
        }
    },
    data() {
        return {
            message_rules: [value => !!value || '보낼 메시지를 입력해주세요.'],
            sendData: {
                studySeq: null,
                content: null
            },
            applyData: false, //모달창
            applyDisplay: true, //이미 보냈거나 가입했는지 확인
            port: port
        };
    },

    methods: {
        applyDialog(check) {
            if (check == 'open') {
                if (this.isLogin == false) {
                    alert('로그인 먼저 해주세요!');
                } else {
                    this.applyData = true;
                }
            } else if (check == 'T') {
                //지원을한다. -> 마이스터디페이지로 이동함.
                this.$store.dispatch('moduleStudy/applyStudy', this.sendData);
                if (this.sendData.content == '') {
                    alert('보낼 메시지를 입력해주세요!');
                } else {
                    this.applyData = false;
                }
            } else if (check == 'F') {
                this.applyData = false;
            }
        }
    }
};
</script>

<style scoped>
.infoDiv {
    padding: 30px;
    border-radius: 20px;
    border-style: solid;
    border-color: #eeeeee;
    border-width: thin;
}
</style>
