<template>
    <v-app>
        <v-container>
            <v-row>
                <v-col cols="5">
                    <!-- 이미지 수정 -->
                    <v-row>
                        <v-col cols="12">
                            <img class="img" style="width: 100%" src="@/assets/study_320x180.png" alt="study_image" />
                        </v-col>
                    </v-row>
                    <!-- 이미지 수정부분 추가 -->
                </v-col>

                <v-col cols="7">
                    <!-- 이름 수정 -->
                    <v-row>
                        <v-col cols="3">
                            <v-subheader>이름</v-subheader>
                        </v-col>
                        <v-col cols="6"> </v-col>
                        <v-col cols="3">
                            <v-btn>중복확인</v-btn>
                        </v-col>
                    </v-row>

                    <!-- 인원/모집인원 -->
                    <v-row>
                        <v-col cols="3">
                            <v-subheader>인원/모집인원</v-subheader>
                        </v-col>
                        <v-col cols="9"> </v-col>
                    </v-row>

                    <!-- 상위 카테고리 -->
                    <v-row>
                        <v-col cols="3">
                            <v-subheader>상위 카테고리</v-subheader>
                        </v-col>
                        <v-col cols="9"> </v-col>
                    </v-row>

                    <!-- 하위 카테고리 -->
                    <v-row>
                        <v-col cols="3">
                            <v-subheader>하위 카테고리</v-subheader>
                        </v-col>
                        <v-col cols="9"> </v-col>
                    </v-row>

                    <!-- 온/오프 -->
                    <v-row>
                        <v-col cols="3">
                            <v-subheader>온/오프</v-subheader>
                        </v-col>
                        <v-col cols="9"> </v-col>
                    </v-row>

                    <!-- 레벨 -->
                    <v-row>
                        <v-col cols="3">
                            <v-subheader>LEVEL</v-subheader>
                        </v-col>
                        <v-col cols="9"> </v-col>
                    </v-row>
                </v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
// import {mapState} from 'vuex';

export default {
    name: 'StudyEdit'
    // async created() {
    //     const pathName = new URL(document.location).pathname.split('/');
    //     const studySeq = pathName[pathName.length - 2];
    //     await this.$store.dispatch('moduleStudy/getStudyInfo', studySeq);
    //     if (this.studyInfo.isLeader == false) {
    //         window.location.href = '/error';
    //     }
    // }
};
</script>
