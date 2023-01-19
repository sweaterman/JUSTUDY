<template>
    <v-app>
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
                                <img src="@/assets/studyImage.png" alt="study_image" />
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="9">
                                <h3>{{ study.name }}</h3>
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
                                <img src="@/assets/studyImage.png" alt="study_image" />
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="9">
                                <h3>{{ study.name }}</h3>
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
    </v-app>
</template>

<script>
// import {mapState} from 'vuex';

export default {
    name: 'CreateStudyView',
    computed: {},
    created() {},
    data() {
        return {
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
        deleteApply(seq) {
            console.log(seq);
        }
    }
};
</script>
