<template>
    <v-app>
        <v-row style="padding: 10em">
            <!-- 스터디 이미지와 소개란 -->
            <v-col cols="12" md="7">
                <v-row> 스터디 이미지 표시할 곳 </v-row>
                <v-row> {{ study.name }} </v-row>
                <v-row> {{ study.info }}</v-row>
            </v-col>

            <!-- 스터디 상세 정보 -->
            <v-col cols="12" md="5">
                <v-row>
                    {{ study.name }}
                </v-row>

                <!-- 팀장 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>팀장</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-text-field v-model="study.teamLeader" outlined readonly></v-text-field>
                    </v-col>
                </v-row>

                <!-- 카테고리 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>카테고리</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-text-field v-model="study.top" outlined readonly></v-text-field>
                    </v-col>
                </v-row>

                <!-- 상세 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>상세</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-text-field v-model="study.bottom" outlined readonly></v-text-field>
                    </v-col>
                </v-row>

                <!-- 모집인원 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>모집인원</v-subheader>
                    </v-col>
                    <v-col cols="8"> {{ study.currrent_num }} / {{ study.apply_num }} </v-col>
                </v-row>

                <!-- LEVEL -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>LEVEL</v-subheader>
                    </v-col>
                    <v-col cols="8"> <v-text-field v-model="study.level" outlined readonly></v-text-field> </v-col>
                </v-row>

                <!-- 활동 주기 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>활동 주기</v-subheader>
                    </v-col>
                    <v-col cols="8"> {{ study.week }}, {{ study.start_time }} ~ {{ study.end_time }} </v-col>
                </v-row>

                <!-- 모임 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>모임</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-text-field v-model="study.on_off" outlined readonly></v-text-field>
                    </v-col>
                </v-row>

                <!-- 시작예정일 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>시작예정일</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-text-field v-model="study.effective_date" outlined readonly></v-text-field>
                    </v-col>
                </v-row>

                <!-- 지원하기 -->
                <v-row>
                    <v-btn rounded color="yellow" @click="applyDialog('open')"> 지원하기 </v-btn>

                    <!-- 지원 모달창 -->
                    <v-dialog v-model="applyData" persistent max-width="500">
                        <v-card>
                            <v-card-title> 신청하기 </v-card-title>

                            <v-row>
                                <h2>스터디장에게 남기고 싶은 메시지</h2>
                            </v-row>

                            <v-row>
                                <v-textarea outlined rows="15" :rules="message_rules" v-model="user.message"></v-textarea>
                            </v-row>

                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="green darken-1" text @click="applyDialog('T')"> 확인 </v-btn>
                                <v-btn color="green darken-1" text @click="applyDialog('F')"> 취소 </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                </v-row>
            </v-col>
        </v-row>
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
                info: '어서오세요',
                teamLeader: '돌로스원숭숭',
                top: '프론트엔드',
                bottom: '뷰, 리액트',
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
            applyData: false
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
                //지원을한다.
                this.applyData = false;
            } else if (check == 'F') {
                this.applyData = false;
            }
        }
    }
};
</script>
