<!-- 홍보 중인 스터디 목록을 보여주는 컴포넌트입니다. (페이지네이션 적용) -->

<template>
    <v-app>
        <v-row>
            <v-col cols="4" v-for="study in calData" :key="study.seq">
                <v-row justify="center">
                    <img src="@/assets/studyImage.png" alt="study_image" @click="moveToStudy(study.seq)" />
                </v-row>
                <v-row>
                    <v-col cols="9">
                        <h3 @click="moveToStudy(study.seq)">{{ study.name }}</h3>
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
            </v-col>
        </v-row>

        <div class="text-center">
            <v-container>
                <v-row justify="center">
                    <v-col cols="8">
                        <v-container class="max-width">
                            <v-pagination v-model="page" class="my-4" :length="numOfPages"></v-pagination>
                        </v-container>
                    </v-col>
                </v-row>
            </v-container>
        </div>
    </v-app>
</template>

<script>
// import {mapState} from 'vuex';

export default {
    name: 'PromotionStudy',
    data() {
        return {
            page: 1,
            dataPerPage: 6,
            searchStudies: [
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
                },
                {
                    seq: 9,
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
                    seq: 10,
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
                    seq: 11,
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
                    seq: 12,
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
                    seq: 13,
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
                    seq: 14,
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
                    seq: 15,
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
    computed: {
        startOffset() {
            return (this.page - 1) * this.dataPerPage;
        },
        endOffset() {
            return this.startOffset + this.dataPerPage;
        },
        numOfPages() {
            return Math.ceil(this.searchStudies.length / this.dataPerPage);
        },
        calData() {
            return this.searchStudies.slice(this.startOffset, this.endOffset);
        }
    },
    methods: {
        moveToStudy(seq) {
            var router = this.$router;
            router.push({path: `/study/search/${seq}`});
        }
    }
};
</script>

<style scoped>
.chip {
    margin: 0.5em;
}
</style>
