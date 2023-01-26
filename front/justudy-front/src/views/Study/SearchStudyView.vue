<template>
    <v-app>
        <v-container>
            <v-row>
                <!-- 왼쪽 여백 -->
                <v-col cols="12" md="2"></v-col>

                <!-- 본문 -->
                <v-col cols="12" md="8">
                    <!-- 상위 카테고리 tabs -->
                    <v-row style="margin-top: 10px; margin-bottom: 10px">
                        <v-col cols="12">
                            <v-tabs color="black" v-model="tab">
                                <v-tabs-slider color="yellow"></v-tabs-slider>
                                <v-tab v-for="top in top_categories" :key="top">
                                    <h1>{{ top }}</h1>
                                </v-tab>
                            </v-tabs>
                        </v-col>
                    </v-row>

                    <v-row><v-divider></v-divider></v-row>

                    <!-- 하위 카테고리 buttons -->
                    <v-row>
                        <v-col cols="12">
                            <div class="btnGroup" v-for="bot in bottom" :key="bot.name">
                                <v-btn outlined class="btnBot" rounded x-large v-if="top_categories[tab] == bot.top" @click="doSearch(bot.name)">
                                    <!-- 추후 SVG 아이콘으로 수정예정 -->
                                    <v-avatar size="50"><img src="@/assets/icon_70x70.png" alt="stackIcon" /></v-avatar>
                                    {{ bot.name }}
                                </v-btn>
                            </div>
                        </v-col>
                    </v-row>

                    <!-- 선택한 항목 표기 chips -->
                    <v-row>
                        <v-col cols="12">
                            <v-chip-group column multiple>
                                <v-chip close close-icon="mdi-close-outline" v-for="i in choice" :key="i" @click:close="remove(i)"> {{ i }}</v-chip>
                            </v-chip-group>
                        </v-col>
                    </v-row>

                    <!-- 스터디 리스트 -->
                    <v-row>
                        <v-col cols="12">
                            <v-row>
                                <v-col cols="4" v-for="study in firstStudies" :key="study.seq">
                                    <!-- 한 개의 스터디를 감싸고 있는 div -->
                                    <div class="singleStudy">
                                        <v-row justify="center">
                                            <img class="hover" style="width: 95%" src="@/assets/test_study.jpg" alt="study_image" @click="moveToStudy(study.seq)" />
                                        </v-row>
                                        <v-row style="margin-bottom: 5px">
                                            <v-col cols="12">
                                                <h3 class="hover" @click="moveToStudy(study.seq)">{{ study.name }}</h3>
                                            </v-col>
                                            <!-- <v-col cols="5" v-if="!checkPersonnel(study.current_person, study.personnel)" style="color: #3edf23; font-weight: bold">
                                                <div style="float: right">모집현황 {{ study.current_person }} / {{ study.personnel }}</div>
                                            </v-col>
                                            <v-col cols="5" v-if="checkPersonnel(study.current_person, study.personnel)" style="color: #ff0000; font-weight: bold">
                                                <div style="float: right">모집현황 {{ study.current_person }} / {{ study.personnel }}</div>
                                            </v-col> -->
                                        </v-row>

                                        <v-row no-gutters align="center">
                                            <v-col cols="4">
                                                <v-subheader style="height: fit-content; padding: 0px">모집 현황</v-subheader>
                                                <!-- <v-chip class="chip" color="yellow lighten-4">시작 예정일</v-chip> -->
                                            </v-col>
                                            <v-col cols="8" v-if="checkPersonnel(study.current_person, study.personnel)" style="color: #ff0000; font-weight: bold">
                                                {{ study.current_person }} / {{ study.personnel }}
                                            </v-col>
                                            <v-col cols="8" v-if="!checkPersonnel(study.current_person, study.personnel)" style="color: #3edf23; font-weight: bold">
                                                {{ study.current_person }} / {{ study.personnel }}
                                            </v-col>
                                        </v-row>
                                        <v-row no-gutters align="center">
                                            <v-col cols="4">
                                                <v-subheader style="height: fit-content; padding: 0px">시작 예정일</v-subheader>
                                                <!-- <v-chip class="chip" color="yellow lighten-4">시작 예정일</v-chip> -->
                                            </v-col>
                                            <v-col cols="8">
                                                {{ study.starting_day }}
                                            </v-col>
                                        </v-row>
                                        <v-row no-gutters align="center">
                                            <v-col cols="4">
                                                <v-subheader style="height: fit-content; padding: 0px">스터디장</v-subheader>
                                                <!-- <v-chip class="chip" color="yellow lighten-4">스터디장</v-chip> -->
                                            </v-col>
                                            <v-col cols="8">
                                                {{ study.teamLeader }}
                                            </v-col>
                                        </v-row>
                                        <v-row dense>
                                            <v-chip class="chip" color="yellow lighten-4">{{ study.weekday }}</v-chip>
                                            <v-chip class="chip" color="yellow lighten-4">{{ study.on_off }}</v-chip>
                                            <v-chip class="chip" color="yellow lighten-4">{{ study.level }}</v-chip>
                                        </v-row>
                                    </div>
                                </v-col>
                            </v-row>

                            <v-row v-if="checkMore">
                                <v-col cols="3"></v-col>
                                <v-col cols="6"><v-btn outlined color="gold" block @click="getMore"> 더보기 </v-btn></v-col>
                                <v-col cols="3"></v-col>
                            </v-row>
                        </v-col>
                    </v-row>

                    <!-- 스터디 생성 페이지로 연결 -->
                    <v-row class="createCom">
                        <v-col cols="12">
                            <v-row>
                                <v-col dense align="center" cols="12">
                                    <h3 style="color: #999999">직접 스터디를 만들어 스터디 멤버를 모집해보세요!</h3>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col dense align="center" cols="12">
                                    <v-btn rounded color="#FFEB00" style="height: 50px; width: 50%" @click="moveToCreate">
                                        <h2>1분 만에 스터디 만들기</h2>
                                    </v-btn>
                                </v-col>
                            </v-row>
                        </v-col>
                    </v-row>
                </v-col>
                <!-- 본문 끝 -->

                <!-- 오른쪽 여백 -->
                <v-col cols="12" md="2"></v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
// import {mapState} from 'vuex';

export default {
    name: 'SearchStudyView',
    updated: {},
    data() {
        return {
            tab: null,
            button: null,
            choice: [],
            top_categories: ['프론트엔드', '백엔드', '모바일', 'CS'],
            bottom: [
                {
                    name: 'React',
                    top: '프론트엔드'
                },
                {
                    name: 'Vue.js',
                    top: '프론트엔드'
                },
                {
                    name: 'Angular',
                    top: '프론트엔드'
                },
                {
                    name: 'Spring',
                    top: '백엔드'
                },
                {
                    name: 'node.js',
                    top: '백엔드'
                },
                {
                    name: 'Django',
                    top: '백엔드'
                },
                {
                    name: 'php',
                    top: '백엔드'
                },
                {
                    name: 'Flutter',
                    top: '모바일'
                },
                {
                    name: 'Kotiln',
                    top: '모바일'
                },
                {
                    name: 'ReactNative',
                    top: '모바일'
                },
                {
                    name: '컴퓨터구조',
                    top: 'CS'
                },
                {
                    name: '운영체제',
                    top: 'CS'
                },
                {
                    name: '알고리즘',
                    top: 'CS'
                },
                {
                    name: '네트워크',
                    top: 'CS'
                }
            ],
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
                    name: '이 스터디는 이름이 깁니다.',
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
                    name: '하지만 얘는 쟤보다 이름이 조금 더더더 깁니다.',
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
        doSearch(bc) {
            this.choice = this.choice.concat(bc);
            this.choice = this.choice.filter((item, pos) => this.choice.indexOf(item) === pos);
        },
        remove(i) {
            this.choice = this.choice.filter(element => element !== i);
        },
        moveToStudy(seq) {
            this.$router.push({path: `/study/search/${seq}`});
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
        },
        moveToCreate() {
            this.$router.push({path: `/study/create`});
        }
    }
};
</script>

<style scoped>
.btnGroup {
    display: inline-flex;
}

.btnBot {
    margin-top: 10px;
    margin-right: 15px;
}

.chip {
    margin: 5px 5px 0px 0px;
}

.hover {
    cursor: pointer;
}

.singleStudy {
    padding: 20px;
    margin-bottom: 20px;
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}

.createCom {
    padding-top: 10px;
    background-color: #f2f2f2;
    margin-top: 30px;
    border-radius: 20px;
    padding-bottom: 20px;
}
</style>
