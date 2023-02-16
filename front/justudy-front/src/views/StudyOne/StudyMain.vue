<template>
    <v-app>
        <v-container>
            <v-row :style="{padding: '1%'}">
                <!-- 왼쪽 여백 -->
                <v-col cols="12" md="3" />

                <!-- 본문 -->
                <v-col cols="12" md="6">
                    <v-row>
                        <v-col cols="12" align="center">
                            <router-link :to="`/study/${studySeq}/info`">
                                <v-btn :style="{color: 'blue'}">
                                    <span class="material-icons-outlined"> info </span>
                                </v-btn>
                            </router-link>

                            <router-link :to="`/study/${studySeq}/board/main`">
                                <v-btn :style="{color: 'blue'}">
                                    <span class="material-icons-outlined"> article </span>
                                </v-btn>
                            </router-link>
                        </v-col>
                    </v-row>

                    <v-row style="margin-bottom: 20px">
                        <v-divider></v-divider>
                    </v-row>

                    <v-row>
                        <router-view></router-view>
                    </v-row>
                </v-col>

                <!-- 오른쪽 여백 -->
                <v-col cols="12" md="3" />
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
export default {
    name: 'StudyMain',
    created() {
        const studySeq = this.$route.params.studySeq;
        this.studySeq = studySeq;
    },
    data() {
        return {
            studySeq: 1
        };
    }
};
</script>
