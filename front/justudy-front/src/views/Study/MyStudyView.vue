<template>
    <v-container>
        <!-- 스터디 지원목록 -->
        <v-row>
            <v-col>
                <h2>지원한 스터디 목록</h2>
            </v-col>
        </v-row>
        <v-row>
            <v-col>
                <v-divider></v-divider>
            </v-col>
        </v-row>

        <v-row>
            <v-col cols="3"></v-col>
            <v-col cols="6">
                <v-row>
                    <v-col cols="4" v-for="study in studies" :key="study.seq">
                        <v-row justify="center">
                            <v-col align-self="center">
                                <img @click="moveToApply(study.seq)" src="@/assets/study_480x270.png" alt="study_image" />
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="9">
                                <h3 @click="moveToApply(study.seq)">{{ study.name }}</h3>
                            </v-col>
                            <v-col cols="3"> 모집현황 {{ study.current_person }} / {{ study.personnel }}</v-col>
                        </v-row>
                        <v-row align="center">
                            <v-chip class="chip" color="yellow lighten-4">시작 예정일</v-chip>
                            {{ study.starting_day }}
                        </v-row>
                        <v-row align="center">
                            <v-chip class="chip" color="yellow lighten-4">스터디장</v-chip>
                            {{ study.teamLeader }}
                        </v-row>
                        <v-row>
                            <v-chip class="chip" color="yellow lighten-4">{{ study.weekday }}</v-chip>
                            <v-chip class="chip" color="yellow lighten-4">{{ study.on_off }}</v-chip>
                            <v-chip class="chip" color="yellow lighten-4">{{ study.level }}</v-chip>
                        </v-row>

                        <v-row>
                            <v-col>
                                <v-btn class="mx-2" fab dark small color="primary" @click="deleteApply(study.seq)">
                                    <v-icon dark> X </v-icon>
                                </v-btn>
                            </v-col>
                        </v-row>

                        <v-dialog v-model="deleteModal" width="500">
                            <v-card>
                                <v-card-title class="text-h5 grey lighten-2"> 지원 취소 </v-card-title>
                                <v-card-text> 지원을 취소하시면, 스터디장에게 간 메세지가 사라집니다. 정말로 취소하시겠습니까? </v-card-text>
                                <v-divider></v-divider>
                                <v-card-actions>
                                    <v-spacer></v-spacer>
                                    <v-btn color="primary" text @click="deleteApply('T')"> 확인 </v-btn>
                                    <v-btn color="primary" text @click="deleteApply('F')"> 취소 </v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-dialog>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="3"></v-col>
        </v-row>

        <!-- 가입한 스터디 목록 -->
        <v-row>
            <v-col>
                <h2>가입한 스터디 목록</h2>
            </v-col>
        </v-row>
        <v-row>
            <v-col>
                <v-divider></v-divider>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="3"></v-col>
            <v-col cols="6">
                <!-- 시작 -->
                <v-row>
                    <v-col cols="4" v-for="study in studies" :key="study.seq">
                        <v-row justify="center">
                            <v-col align-self="center">
                                <img @click="moveToDetail(study.seq)" src="@/assets/study_480x270.png" alt="study_image" />
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="9">
                                <h3 @click="moveToDetail(study.seq)">{{ study.name }}</h3>
                            </v-col>
                            <v-col cols="3"> 모집현황 {{ study.current_person }} / {{ study.personnel }}</v-col>
                        </v-row>
                        <v-row align="center">
                            <v-chip class="chip" color="yellow lighten-4">스터디장</v-chip>
                            {{ study.teamLeader }}
                        </v-row>
                        <v-row>
                            <v-chip class="chip" color="yellow lighten-4">{{ study.weekday }}</v-chip>
                            <v-chip class="chip" color="yellow lighten-4">{{ study.on_off }}</v-chip>
                            <v-chip class="chip" color="yellow lighten-4">{{ study.level }}</v-chip>
                        </v-row>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="3"></v-col>
        </v-row>
    </v-container>
</template>

<script>
// import {mapState} from 'vuex';

export default {
    name: 'CreateStudyView',
    computed: {},
    created() {},
    data() {
        return {
            deleteModal: false,
            deleteNum: null,
            studies: [
                {
                    seq: 1,
                    name: '리액트 용자 모임',
                    current_person: 2,
                    personnel: 4,
                    starting_day: '너만오면고',
                    teamLeader: '이연희',
                    weekday: '월, 화',
                    on_off: '온/오프라인',
                    level: '초보'
                },
                {
                    seq: 2,
                    name: '리액트 용자 모임',
                    current_person: 4,
                    personnel: 4,
                    starting_day: '너만오면고',
                    teamLeader: '이연희',
                    weekday: '월, 화',
                    on_off: '오프라인',
                    level: '중급'
                },
                {
                    seq: 3,
                    name: '리액트 용자 모임',
                    current_person: 2,
                    personnel: 5,
                    starting_day: '너만오면고',
                    teamLeader: '이연희',
                    weekday: '월, 화, 수, 목, 금',
                    on_off: '온/오프라인',
                    level: '초보'
                },
                {
                    seq: 4,
                    name: '리액트 용자 모임',
                    current_person: 2,
                    personnel: 5,
                    starting_day: '너만오면고',
                    teamLeader: '이연희',
                    weekday: '월, 화, 수, 목, 금',
                    on_off: '온/오프라인',
                    level: '초보'
                },
                {
                    seq: 5,
                    name: '리액트 용자 모임',
                    current_person: 2,
                    personnel: 5,
                    starting_day: '너만오면고',
                    teamLeader: '이연희',
                    weekday: '월, 화, 수, 목, 금',
                    on_off: '온/오프라인',
                    level: '초보'
                },
                {
                    seq: 6,
                    name: '리액트 용자 모임',
                    current_person: 2,
                    personnel: 5,
                    starting_day: '너만오면고',
                    teamLeader: '이연희',
                    weekday: '월, 화, 수, 목, 금',
                    on_off: '온/오프라인',
                    level: '초보'
                },
                {
                    seq: 7,
                    name: '리액트 용자 모임',
                    current_person: 2,
                    personnel: 5,
                    starting_day: '너만오면고',
                    teamLeader: '이연희',
                    weekday: '월, 화, 수, 목, 금',
                    on_off: '온/오프라인',
                    level: '초보'
                },
                {
                    seq: 8,
                    name: '리액트 용자 모임',
                    current_person: 2,
                    personnel: 5,
                    starting_day: '너만오면고',
                    teamLeader: '이연희',
                    weekday: '월, 화, 수, 목, 금',
                    on_off: '온/오프라인',
                    level: '초보'
                }
            ]
        };
    },
    methods: {
        moveToApply(seq) {
            var router = this.$router;
            router.push({path: `/study/search/${seq}`});
        },
        moveToDetail(seq) {
            var router = this.$router;
            router.push({path: `/study/detail/${seq}`});
        },
        deleteApply(seq) {
            if (seq == 'T') {
                //deleteNum 을 서버에 보내기
                this.deleteModal = false;
            } else if (seq == 'F') {
                this.deleteNum = null;
                this.deleteModal = false;
            } else {
                this.deleteNum = seq;
                this.deleteModal = true;
            }
        }
    }
};
</script>
