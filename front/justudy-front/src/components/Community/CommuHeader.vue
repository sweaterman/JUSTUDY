<!-- 게시판 카테고리 목록 "백엔드/프론트/.../기타" -->
<template>
    <v-row>
        <v-col cols="12" md="1" />
        <v-col cols="12" md="10">
            <!-- 카테고리 지정 -->
            <v-row>
                <v-tabs color="black" v-model="tab" justify="center" align="center">
                    <v-tabs-slider color="yellow"></v-tabs-slider>
                    <v-tab v-for="top in top_categories" :key="top">
                        <h1 v-on:click="updateData(top[1])">{{ top[0] }}</h1>
                    </v-tab>
                </v-tabs>
            </v-row>
        </v-col>
        <v-col cols="12" md="1" />
    </v-row>
</template>

<script>
export default {
    name: 'CommuHeader',
    components: {},
    methods: {
        updateData(data) {
            // console.log('aaaa');
            this.$emit('click', data);
            this.$router.push(`${this.$route.path}?category=${data}&page=1`);
        }
    },
    data() {
        return {
            top_categories: [
                ['FRONT-END', 'frontend'],
                ['BACK-END', 'backend'],
                ['INFRA', 'infra'],
                ['MOBIlE', 'mobile'],
                ['ALGORITHM', 'algorithm'],
                ['CS', 'cs'],
                ['PROJECT', 'project'],
                ['ETC', 'etc']
            ]
        };
    }
};
</script>
