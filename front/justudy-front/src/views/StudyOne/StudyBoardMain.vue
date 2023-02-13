<template>
    <v-container>
        <v-row>
            <router-view></router-view>
        </v-row>
    </v-container>
</template>

<script>
export default {
    name: 'StudyBoardMain',
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
