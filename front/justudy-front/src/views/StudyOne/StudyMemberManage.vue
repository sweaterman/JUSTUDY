<template>
    <v-container>
        <v-row>
            <v-col cols="12">
                <!-- 회원 관리 -->
                <v-row>
                    <v-col>
                        <h2>회원 관리</h2>
                        <hr width="60%" />
                    </v-col>
                </v-row>
                <v-row v-for="member in studyInfo.member" :key="member.sequence">
                    <!-- 스터디 방장은 빼고 보여줌 -->
                    <v-col v-if="member.nickName != studyInfo.leader">
                        <div class="card_section_member">
                            <v-row justify="center" align="center">
                                <v-col cols="12" md="2" justify="center" align="center">
                                    <v-avatar size="50">
                                        <img :src="`${port}images/${member.imageSequence}`" alt="memberImg" />
                                    </v-avatar>
                                </v-col>
                                <v-col cols="12" md="6" justify="center" align="center">
                                    <v-row>
                                        <div :style="{fontSize: 'large'}" justify="center" align="center">
                                            {{ member.nickName }}
                                        </div>
                                    </v-row>
                                </v-col>

                                <!-- 스터디장 위임 버튼 -->
                                <v-col cols="12" md="2">
                                    <v-btn @click="openDialog('giveLeader', member.sequence, 0)" color="white" depressed>
                                        <span class="material-icons-outlined" :style="{color: 'black'}">stars</span>
                                    </v-btn>
                                </v-col>

                                <!-- 스터디 탈퇴시키기 버튼 -->
                                <v-col cols="12" md="2">
                                    <v-btn @click="openDialog('withDraw', member.sequence, 0)" color="white" depressed>
                                        <span class="material-icons-outlined" :style="{color: 'crimson'}">cancel</span>
                                    </v-btn>
                                </v-col>
                            </v-row>
                        </div>
                    </v-col>
                </v-row>

                <!-- 신청 관리 -->
                <v-row :style="{marginTop: '8%'}">
                    <v-col>
                        <h2>신청 관리</h2>
                        <hr width="60%" />
                    </v-col>
                </v-row>
                <v-row v-for="applicant in applyList" :key="applicant.sequence">
                    <v-col>
                        <div class="card_section_applicant">
                            <v-row justify="center" align="center">
                                <v-col cols="12" md="2">
                                    <v-row>
                                        <v-avatar size="55">
                                            <img :src="`${port}images/${applicant.imageSequence}`" alt="memberImg" />
                                        </v-avatar>
                                    </v-row>
                                    <v-row :style="{marginTop: '20%'}">
                                        {{ applicant.nickName }}
                                    </v-row>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <v-row>
                                        {{ applicant.content }}
                                    </v-row>
                                </v-col>
                                <v-col cols="12" md="2">
                                    <v-btn @click="openDialog('accept', applicant.memberSeq, applicant.sequence)" color="white" depressed>
                                        <span class="material-icons-outlined" :style="{color: 'green'}">check_circle</span>
                                    </v-btn>
                                </v-col>
                                <v-col cols="12" md="2">
                                    <v-btn @click="openDialog('reject', applicant.memberSeq, applicant.sequence)" color="white" depressed>
                                        <span class="material-icons-outlined" :style="{color: 'crimson'}">cancel</span>
                                    </v-btn>
                                </v-col>
                            </v-row>
                        </div>
                    </v-col>
                </v-row>

                <!-- 스터디장 위임 모달 -->
                <v-dialog v-model="giveLeader" width="600">
                    <v-card>
                        <v-card-title>스터디장 위임하기</v-card-title>
                        <v-card-text>해당 스터디원에게 스터디장을 위임하시겠습니까?</v-card-text>
                        <v-card-actions>
                            <v-btn @click="changeStudyLeader()">위임하기</v-btn>
                            <v-btn @click="giveLeader = false">취소</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>

                <!-- 스터디 탈퇴 모달 -->
                <v-dialog v-model="memberOutClicked" width="600">
                    <v-card>
                        <v-row>
                            <v-col cols="12" md="2" />
                            <v-col cols="12" md="8" justify="center" align="center">
                                <h3>해당 팀원을 탈퇴시킵니다 !!</h3>
                            </v-col>
                            <v-col cols="12" md="2" />

                            <v-col cols="12" md="8" />
                            <v-col cols="12" md="2">
                                <v-btn @click="withDrawMember()" color="white" depressed>
                                    <span class="material-icons-outlined" :style="{color: 'green'}">check_circle</span>
                                </v-btn>
                            </v-col>
                            <v-col cols="12" md="2">
                                <v-btn @click="memberOutClicked = false" color="white" depressed>
                                    <span class="material-icons-outlined" :style="{color: 'crimson'}">cancel</span>
                                </v-btn>
                            </v-col>
                        </v-row>
                    </v-card>
                </v-dialog>

                <!-- 스터디 신청받는 모달 -->
                <v-dialog v-model="studyAcceptDialog" width="600">
                    <v-card>
                        <v-row>
                            <v-col cols="12" md="2" />
                            <v-col cols="12" md="8" justify="center" align="center">
                                <h3>해당 멤버를 스터디원으로 받아들이시겠습니까?</h3>
                            </v-col>
                            <v-col cols="12" md="2" />

                            <v-col cols="12" md="8" />
                            <v-col cols="12" md="2">
                                <v-btn @click="acceptApply(true)" color="white" depressed>
                                    <span class="material-icons-outlined" :style="{color: 'green'}">check_circle</span>
                                </v-btn>
                            </v-col>
                            <v-col cols="12" md="2">
                                <v-btn @click="studyAcceptDialog = false" color="white" depressed>
                                    <span class="material-icons-outlined" :style="{color: 'crimson'}">cancel</span>
                                </v-btn>
                            </v-col>
                        </v-row>
                    </v-card>
                </v-dialog>

                <!-- 스터디 신청거절 모달 -->
                <v-dialog v-model="studyRejectDialog" width="600">
                    <v-card>
                        <v-row>
                            <v-col cols="12" md="2" />
                            <v-col cols="12" md="8" justify="center" align="center">
                                <h3>해당 멤버의 신청을 거절하시겠습니까?</h3>
                            </v-col>
                            <v-col cols="12" md="2" />

                            <v-col cols="12" md="8" />
                            <v-col cols="12" md="2">
                                <v-btn @click="acceptApply(false)" color="white" depressed>
                                    <span class="material-icons-outlined" :style="{color: 'green'}">check_circle</span>
                                </v-btn>
                            </v-col>
                            <v-col cols="12" md="2">
                                <v-btn @click="studyRejectDialog = false" color="white" depressed>
                                    <span class="material-icons-outlined" :style="{color: 'crimson'}">cancel</span>
                                </v-btn>
                            </v-col>
                        </v-row>
                    </v-card>
                </v-dialog>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import {mapState} from 'vuex';
import port from '@/store/port';

export default {
    name: 'StudyEdit',
    computed: {
        ...mapState('moduleStudy', ['studyInfo']),
        ...mapState('moduleStudy', ['applyList'])
    },
    data() {
        return {
            port: port,
            giveLeader: false,
            memberOutClicked: false,
            studyAcceptDialog: false,
            studyRejectDialog: false,
            memSeq: '',
            applySeq: ''
        };
    },
    async created() {
        const studySeq = this.$route.params.studySeq;
        await this.$store.dispatch('moduleStudy/getStudyInfo', studySeq);
        if (this.studyInfo.isLeader == false) {
            window.location.href = '/error';
        }
        await this.$store.dispatch('moduleStudy/getApplyList', studySeq);
    },
    methods: {
        async changeStudyLeader() {
            //스터디장 위임하기
            this.giveLeader = false;
            await this.$store.dispatch('moduleStudy/changeLeader', {
                studySeq: this.studyInfo.sequence,
                memSeq: this.memSeq
            });
        },
        //모달 함수
        openDialog(type, memSeq, applySeq) {
            if (type == 'accept') {
                this.studyAcceptDialog = true;
            } else if (type == 'reject') {
                this.studyRejectDialog = true;
            } else if (type == 'giveLeader') {
                this.giveLeader = true;
            } else if (type == 'withDraw') {
                this.memberOutClicked = true;
            }
            this.memSeq = memSeq;
            this.applySeq = applySeq;
        },
        async acceptApply(result) {
            if (result) {
                //스터디 신청 허락
                this.studyAcceptDialog = false;
                const sendData = {
                    studySeq: this.studyInfo.sequence,
                    applySeq: this.applySeq,
                    result: {
                        isAccept: true
                    }
                };
                await this.$store.dispatch('moduleStudy/applyAcceptStudy', sendData);
            } else {
                //스터디 신청 거절
                this.studyRejectDialog = false;
                const sendData = {
                    studySeq: this.studyInfo.sequence,
                    applySeq: this.applySeq,
                    result: {
                        isAccept: false
                    }
                };
                await this.$store.dispatch('moduleStudy/applyAcceptStudy', sendData);
            }
        },
        async withDrawMember() {
            //탈퇴 시키기
            this.memberOutClicked = false;
            await this.$store.dispatch('moduleStudy/deleteMember', {
                studySeq: this.studyInfo.sequence,
                memSeq: this.memSeq
            });
        }
    }
};
</script>

<style scoped>
.card_section_member {
    padding: 10px;
    /* margin-bottom: 1px; */
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
.card_section_applicant {
    padding: 30px;
    margin-bottom: 5px;
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
</style>
