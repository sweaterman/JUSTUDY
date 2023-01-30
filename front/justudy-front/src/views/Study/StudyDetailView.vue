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

                        <v-row>
                            <v-col>
                                <KingOfStudy :study="studyInfo" />
                            </v-col>
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
//스터디 정보 한번에 다받아서 하부 컴포넌트로 보내기
// import {mapState} from 'vuex';
import StudyRoomIcon from '@/components/study/StudyRoomIcon.vue';
import StudyInfo from '@/components/study/StudyInfo.vue';
import KingOfStudy from '@/components/study/KingOfStudy.vue';

export default {
    name: 'SearchStudyView',
    components: {StudyRoomIcon, StudyInfo, KingOfStudy},
    data() {
        return {
            studyInfo: {
                sequence: 1,
                name: '리액트 용자 모임',
                leader: '돌로스원숭숭',
                population: 10,
                category: 'vue',
                //후추 수정
                member: [1, 2, 3],
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
