<!-- 게시판 카테고리 목록 "백엔드/프론트/.../기타" -->
<template>
    <v-row>
        <v-col cols="12" md="2" />
        <v-col cols="12" md="8">
            <!-- 카테고리 지정 -->
            <v-row style="margin-top: 2%; margin-bottom: 2%; padding: 3%" justify="center" align="center">
                <v-tabs color="black" v-model="tab" justify="center" align="center">
                    <v-tabs-slider color="yellow"></v-tabs-slider>
                    <v-tab v-for="top in top_categories" :key="top">
                        <h1>{{ top }}</h1>
                    </v-tab>
                </v-tabs>
            </v-row>
        </v-col>
        <v-col cols="12" md="2" />
    </v-row>
</template>

<script>
export default {
    name: 'CommuHeader',
    components: {},
    methods: {},
    data() {
        return {
            top_categories: ['Front-end', 'Back-end', 'Infra', 'CS', 'Algorithm', 'Leading-edge', 'Bulletin board']
        };
    }
};
</script>
