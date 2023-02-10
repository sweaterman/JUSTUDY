<template>
    <v-app>
        <v-container>
            <v-row :style="{padding: '1%'}">
                <!-- 왼쪽 여백 -->
                <v-col cols="12" md="2" />

                <!-- 본문 -->
                <v-col cols="12" md="8">
                    <v-row>
                        <v-col cols="12" md="1">
                            <router-link :to="`/study/${studySeq}/info`">
                                <v-btn :style="{color: 'blue'}">
                                    <span class="material-icons-outlined"> info </span>
                                </v-btn>
                            </router-link>
                        </v-col>

                        <v-col cols="12" md="1">
                            <router-link :to="`/study/${studySeq}/board`">
                                <v-btn :style="{color: 'blue'}">
                                    <span class="material-icons-outlined"> article </span>
                                </v-btn>
                            </router-link>
                        </v-col>

                        <v-col cols="12" md="4" />
                        <v-col cols="12" md="2" justify="center" align="center">
                            <router-link :to="`/study/${studySeq}/calender`"> CALENDER </router-link>
                        </v-col>
                    </v-row>
                    <v-row>
                        <router-view></router-view>
                    </v-row>
                </v-col>

                <!-- 오른쪽 여백 -->
                <v-col cols="12" md="2" />
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
export default {
    name: 'StudyMain',
    created() {
        const pathName = new URL(document.location).pathname.split('/');
        const studySeq = pathName[pathName.length - 2];
        this.studySeq = studySeq;
    },
    data() {
        return {
            studySeq: 1
        };
    },
    methods: {
        moveToEdit() {
            window.location.href = `/study/${this.studyInfo.sequence}/edit`;
        }
    }
};
</script>
