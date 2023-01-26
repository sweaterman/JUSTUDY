<!-- 홍보 중인 스터디 목록을 보여주는 컴포넌트입니다. (더보기 버튼(무한 스크롤?) 적용) -->

<template>
    <v-app>
        <v-row>
            <v-col cols="4" v-for="study in firstStudies" :key="study.seq">
                <v-row justify="center">
                    <img class="hover" style="border: 3px solid black; border-radius: 7px; width: 95%" src="@/assets/study_480x270.png" alt="study_image" @click="moveToStudy(study.seq)" />
                </v-row>
                <v-row>
                    <v-col cols="7">
                        <h3 class="hover" @click="moveToStudy(study.seq)">{{ study.name }}</h3>
                    </v-col>
                    <!-- 초록색 -->
                    <v-col cols="5" v-if="!checkPersonnel(study.current_person, study.personnel)" style="color: #3edf23; font-weight: bold">
                        <div style="float: right">모집현황 {{ study.current_person }} / {{ study.personnel }}</div>
                    </v-col>
                    <!-- 빨간색 -->
                    <v-col cols="5" v-if="checkPersonnel(study.current_person, study.personnel)" style="color: #ff0000; font-weight: bold">
                        <div style="float: right">모집현황 {{ study.current_person }} / {{ study.personnel }}</div>
                    </v-col>
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
                <v-row><v-col cols="12"></v-col></v-row>
            </v-col>
        </v-row>

        <v-row v-if="checkMore">
            <v-col cols="3"></v-col>
            <v-col cols="6"><v-btn outlined color="gold" block @click="getMore"> 더보기 </v-btn></v-col>
            <v-col cols="3"></v-col>
        </v-row>
    </v-app>
</template>

<script>
// import {mapState} from 'vuex';

export default {
    name: 'PromotionStudy',
    data() {
        return {
            //9개씩 끊어서 가져올 데이터
            pageNum: 0,

            //더보기 버튼을 표시할 지 체크
            checkMore: true,

            //처음 9개의 데이터
            firstStudies: [
                {
                    seq: 1,
                    name: '스터디이름들글자테스트',
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
                }
            ],

            //'더보기' 버튼으로 추가되는 데이터
            moreData: [
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
    methods: {
        moveToStudy(seq) {
            var router = this.$router;
            router.push({path: `/study/search/${seq}`});
        },
        getMore() {
            //1. moreData에 9개 더받아온다. (더받아올 데이터가 있는지도 확인)
            //2. moreData를 firstData에 합치고 moreData null 로 만들기
            //pageNum 더하고 넘기기
            this.firstStudies = this.firstStudies.concat(this.moreData);
            this.moreData = null;

            //더받아올 데이터가 없다면 더보기 버튼 비활성화
        },
        checkPersonnel(c, p) {
            //초록색이면 false, 빨간색이면 true
            if (Number(p) - Number(c) > 2) {
                return false;
            } else {
                return true;
            }
        }
    }
};
</script>

<style scoped>
.chip {
    margin: 0.5em;
}

.hover {
    cursor: pointer;
}
</style>
