<!-- 스터디방이 열려있으면 참가할 수 있게 연결하고, 닫혀있으면 열 수 있는 컴포넌트입니다. -->
<template>
    <v-container>
        <!-- 스터디 이미지 -->
        <v-row>
            <v-col cols="12">
                <img class="img" src="@/assets/study_320x180.png" alt="study_image" />
            </v-col>
        </v-row>

        <v-row v-if="!roomOpen" justify="center">
            <!-- 바로 참여하기 -->
            <v-col cols="6" align="center">
                <v-btn rounded color="red" dark>바로 참여하기</v-btn>
            </v-col>
            <!-- 알림보내기 -->
            <v-col cols="6" align="center">
                <v-btn rounded color="yellow">알림 보내기</v-btn>
            </v-col>
        </v-row>

        <v-row v-if="roomOpen" justify="center">
            <v-col cols="12" align="center">
                <v-btn rounded color="yellow">스터디방 열기</v-btn>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
// import {mapState} from 'vuex';

export default {
    name: 'StudyRoomIcon',
    data() {
        return {
            roomOpen: false
        };
    }
};
</script>

<style>
.img {
    width: 100%;
}
</style>
