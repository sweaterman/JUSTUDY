<template>
    <v-app>
        <v-container>
            <v-row>
                <!-- 왼쪽 여백 -->
                <v-col cols="0" md="2"></v-col>

                <!-- 본문 -->
                <v-col cols="12" md="8">
                    <!-- 지원한 스터디 목록 -->
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
                    <StudyList :studies="studies" :type="apply"></StudyList>

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
                    <StudyList :studies="studies" :type="myStudy"></StudyList>
                </v-col>

                <!-- 오른쪽 여백 -->
                <v-col cols="0" md="2"></v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
// import {mapState} from 'vuex';
import StudyList from '@/components/study/StudyList.vue';

export default {
    name: 'MyStudyView',
    components: {StudyList},
    data() {
        return {
            deleteNum: null,
            apply: 'apply',
            myStudy: 'myStudy',
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
    }
};
</script>
