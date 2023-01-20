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
                                <StudyRoomIcon />
                            </v-col>

                            <!-- 스터디 정보 -->
                            <v-col cols="12" md="8">
                                <StudyInfo />
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col>
                                <KingOfStudy />
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
        return {};
    }
};
</script>
