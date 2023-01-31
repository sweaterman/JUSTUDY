<template>
    <v-app>
        <v-container>
            <v-row align="center">
                <!-- 여백 -->
                <v-col cols="12" md="2"></v-col>

                <!-- 본문 -->
                <v-col cols="12" md="8">
                    <v-container>
                        <v-row>
                            <!-- 스터디방 -->
                            <v-col cols="12" md="4">
                                <StudyRoomIcon :study="studyInfo" />
                            </v-col>

                            <!-- 스터디 정보 -->
                            <v-col cols="12" md="8">
                                <StudyInfo :study="studyInfo" />
                            </v-col>
                        </v-row>

                        <!-- 멤버 목록 -->
                        <v-row>
                            <v-col cols="12">
                                <MemberList :member-list="studyInfo.member" :population="studyInfo.population"></MemberList>
                            </v-col>
                        </v-row>

                        <!-- 스터디 게시판 -->
                        <v-row>
                            <v-col cols="12"> </v-col>
                        </v-row>

                        <!-- 스터디 깃 달력 -->
                        <v-row> </v-row>

                        <v-row>
                            <!-- 스터디왕 -->
                            <v-col>
                                <KingOfStudy :study="studyInfo" />
                            </v-col>
                            <!-- 스터디 랭킹 -->
                        </v-row>
                    </v-container>
                </v-col>

                <!-- 여백 -->
                <v-col cols="12" md="2"></v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
import StudyRoomIcon from '@/components/study/StudyRoomIcon.vue';
import StudyInfo from '@/components/study/StudyInfo.vue';
import KingOfStudy from '@/components/study/KingOfStudy.vue';
import MemberList from '@/components/study/MemberList.vue';
import {mapState} from 'vuex';

export default {
    name: 'SearchStudyView',
    components: {StudyRoomIcon, StudyInfo, KingOfStudy, MemberList},
    computed: {
        ...mapState(['studyInfo'])
    },
    created() {
        const pathName = new URL(document.location).pathname.split('/');
        const studySeq = pathName[pathName.length - 1];
        this.$store.dispatch('getStudyInfo', studySeq);
    },
    data() {
        return {
            studyInfo: {
                sequence: 1,
                name: '리액트 용자 모임',
                leader: '돌로스원숭숭',
                population: 10,
                category: 'vue',
                //후추 수정
                member: [
                    {
                        sequence: 3,
                        nickname: '왕싸피',
                        name: '이연희',
                        badge: 9999
                    },
                    {
                        sequence: 3,
                        nickname: '김싸피',
                        name: '이연희',
                        badge: 8765
                    },
                    {
                        sequence: 3,
                        nickname: '멍싸피',
                        name: '이연희',
                        badge: 4321
                    }
                ],
                level: '초보',
                meeting: '온라인',
                status: true,
                github: 'http://깃허브/어쩌구',
                notion: 'http://노션주소입니다/하하하',
                start_time: '2023.01.02'
            }
        };
    }
};
</script>
