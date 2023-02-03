<template>
    <v-app>
        <v-container>
            <v-row>
                <!-- 왼쪽 여백 -->
                <v-col col="0" md="2"></v-col>

                <!-- 본문 -->
                <v-col col="12" md="8">
                    <router-link :to="`/study/${studySeq}/info`">INFO</router-link> | <router-link :to="`/study/${studySeq}/board`">BOARD</router-link> |
                    <router-view></router-view>
                </v-col>

                <!-- 오른쪽 여백 -->
                <v-col col="0" md="2"></v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
export default {
    name: 'StudyMain',
    data() {
        return {
            studySeq: 1
        };
    }
};
</script>
