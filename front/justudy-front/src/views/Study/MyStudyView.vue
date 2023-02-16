<template>
    <v-app>
        <v-container>
            <v-row>
                <!-- 왼쪽 여백 -->
                <v-col cols="0" md="3"></v-col>

                <!-- 본문 -->
                <v-col cols="12" md="6">
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
                    <StudyList :studies="applyStudies" :type="apply"></StudyList>

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
                    <StudyList :studies="myStudies" :type="myStudy"></StudyList>
                </v-col>

                <!-- 오른쪽 여백 -->
                <v-col cols="0" md="3"></v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
import {mapState} from 'vuex';
import StudyList from '@/components/study/StudyList.vue';

export default {
    name: 'MyStudyView',
    components: {StudyList},
    created() {
        this.$store.dispatch('moduleStudy/getApplyStudies');
        this.$store.dispatch('moduleStudy/getMyStudies');
    },
    computed: {
        ...mapState('moduleStudy', ['applyStudies']),
        ...mapState('moduleStudy', ['myStudies'])
    },
    data() {
        return {
            deleteNum: null,
            apply: 'apply',
            myStudy: 'myStudy'
        };
    }
};
</script>
