<template>
    <v-row :style="{marginLeft: '2%', marginBottom: '2%'}">
        <v-row :style="{marginBottom: '2%'}">
            <v-btn color="yellow" :style="{height: '50px', width: '170px', fontWeight: 'bold', fontSize: 'x-large'}">학습 분석</v-btn>
        </v-row>
        <v-row :style="{marginLeft: '3%'}">
            <h1>
                <span style="color: #58a8f7"> "{{ first }}" </span> 공부가 더 필요해요
            </h1>
            <h1>
                <span style="color: #58a8f7"> "{{ second }}" </span> 공부가 더 필요해요
            </h1>
        </v-row>
    </v-row>
</template>
<script>
export default {
    name: 'StudyAnalyze',
    components: {},
    props: {
        first: {
            type: String
        },
        second: {
            type: String
        }
    }
};
</script>
