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
                            <img class="hover" style="width: 95%" src="@/assets/test_study.jpg" alt="study_image" />
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col cols="12">
                            <h1>{{ study.name }}</h1>
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col cols="12">
                            <div class="studyInfo">
                                <h3>{{ study.info }}</h3>
                            </div>
                        </v-col>
                    </v-row>
                </v-col>

                <!-- 스터디 상세 정보 -->
                <v-col cols="12" md="3">
                    <!-- 스터디 정보 전체를 감싸고 있는 div -->
                    <div class="infoDiv">
                        <v-row>
                            <v-col align="center" style="margin-bottom: 20px; margin-top: 20px">
                                <h2>{{ study.name }}</h2>
                            </v-col>
                        </v-row>

                        <!-- 팀장 -->
                        <v-row>
                            <v-col cols="5">
                                <v-subheader>팀장</v-subheader>
                            </v-col>
                            <v-col align-self="center" cols="7">
                                {{ study.teamLeader }}
                            </v-col>
                        </v-row>

                        <!-- 카테고리 -->
                        <v-row>
                            <v-col cols="5">
                                <v-subheader>카테고리</v-subheader>
                            </v-col>
                            <v-col align-self="center" cols="7">
                                {{ study.top }}
                            </v-col>
                        </v-row>

                        <!-- 상세 -->
                        <v-row>
                            <v-col cols="5">
                                <v-subheader>상세</v-subheader>
                            </v-col>
                            <v-col align-self="center" cols="7">
                                {{ study.bottom }}
                            </v-col>
                        </v-row>

                        <!-- 모집인원 -->
                        <v-row>
                            <v-col cols="5">
                                <v-subheader>모집인원</v-subheader>
                            </v-col>
                            <v-col cols="7" align-self="center"> {{ study.currrent_num }} / {{ study.apply_num }} </v-col>
                        </v-row>

                        <!-- LEVEL -->
                        <v-row>
                            <v-col cols="5">
                                <v-subheader>LEVEL</v-subheader>
                            </v-col>
                            <v-col cols="7" align-self="center">
                                {{ study.level }}
                            </v-col>
                        </v-row>

                        <!-- 활동 주기 -->
                        <v-row>
                            <v-col cols="5">
                                <v-subheader>활동 주기</v-subheader>
                            </v-col>
                            <v-col cols="7" align-self="center"> {{ study.week }} / {{ study.start_time }} ~ {{ study.end_time }} </v-col>
                        </v-row>

                        <!-- 모임 -->
                        <v-row>
                            <v-col cols="5">
                                <v-subheader>모임</v-subheader>
                            </v-col>
                            <v-col cols="7" align-self="center">
                                {{ study.on_off }}
                            </v-col>
                        </v-row>

                        <!-- 시작예정일 -->
                        <v-row>
                            <v-col cols="5">
                                <v-subheader>시작예정일</v-subheader>
                            </v-col>
                            <v-col cols="7" align-self="center">
                                {{ study.effective_date }}
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
                                            <v-textarea outlined rows="10" :rules="message_rules" v-model="user.message"></v-textarea>
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
import {mapState} from 'vuex';

export default {
    name: 'ApplyStudyView',
    data() {
        return {
            study: {
                seq: '',
                name: 'React 용자모임',
                info: `주제: 리액트입니다 \n
                목표: 모두 리액트 마스터하기 \n
                예상 커리큘럼: Do it 리액트! 책을 순서대로 공부합니다. \n
                개설 동기: 리액트를 너무 공부하고 싶었어요.. \n
                주의사항: 노쇼 금지! `,
                teamLeader: '돌로스원숭숭',
                top: '프론트엔드',
                bottom: '뷰, 리액트 이외 기타등등 모든 프론트엔드를 섭렵하는 스터디이이이이이',
                currrent_num: '2',
                apply_num: '4',
                level: '초급',
                week: '월,화',
                start_time: '04:00',
                end_time: '06:00',
                on_off: '온/오프라인',
                effective_date: '협의가능'
            },
            message_rules: [value => !!value || '보낼 메시지를 입력해주세요.'],
            user: {
                message: '안녕하세용'
            },
            applyData: false, //모달창
            applyDisplay: true //이미 보냈거나 가입했는지 확인
        };
    },
    computed: {
        ...mapState(['studyInfo']), //study로 추후에 바꿀 예정(하드코딩)
        ...mapState(['loginUser'])
    },
    created() {
        const pathName = new URL(document.location).pathname.split('/');
        const studySeq = pathName[pathName.length - 1];
        this.study.seq = studySeq;
        this.$store.dispatch('getStudy', studySeq);

        //내가 지원한 스터디인지 확인하는 과정 필요하다.
    },
    methods: {
        applyDialog(check) {
            if (check == 'open') {
                this.applyData = true;
            } else if (check == 'T') {
                //지원을한다. -> 마이페이지로 이동함.
                if (this.user.message == '') {
                    alert('보낼 메시지를 입력해주세요!');
                } else {
                    this.applyData = false;
                    this.$router.push({path: `/study/myStudy`});
                }
            } else if (check == 'F') {
                this.applyData = false;
            }
        }
    }
};
</script>

<style scoped>
.studyInfo {
    display: flex;
    /* 왜글자가 왼쪽에 안붙지? */
    justify-content: flex-start;
    align-items: center;
    border-radius: 20px;
    border-style: solid;
    height: 300px;
}

.infoDiv {
    padding: 30px;
    border-radius: 20px;
    border-style: solid;
    border-color: #eeeeee;
    border-width: thin;
}
</style>
