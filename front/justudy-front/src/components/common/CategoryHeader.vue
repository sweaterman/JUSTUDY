<!-- 게시판 카테고리 목록 "백엔드/프론트/.../기타" -->
<template>
    <v-row>
        <v-col cols="12" md="2" />
        <v-col cols="12" md="8" justify="center" align="center">
            <!-- 카테고리 지정 -->
            <v-row :style="{padding: '1%'}" justify="center" align="center">
                <v-tabs color="black" v-model="tab" justify="center" align="center">
                    <v-tabs-slider color="yellow"></v-tabs-slider>
                    <v-tab v-for="top in top_categories" :key="top[0]">
                        <h1 v-on:click="updateData(top[1])">{{ top[0] }}</h1>
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
    methods: {
        updateData(data) {
            // console.log('aaaa');
            this.$emit('click', data);
            console.log(this.$route.path);
            if (this.$route.path.includes('/community/write') || this.$route.path.includes('/community/content')) {
                this.$router.push(`${this.$route.path}?category=${data}`).catch(() => {});
            } else {
                this.$router.push(`/community?page=1&category=${data}`).catch(() => {});
            }
        }
    },
    data() {
        return {
            tab: '',
            top_categories: [
                ['Front-end', 'frontend'],
                ['Back-end', 'backend'],
                ['Infra', 'infra'],
                ['CS', 'cs'],
                ['Algorithm', 'algorithm'],
                ['Leading-edge', 'leading-edge'],
                ['Bulletin board', 'bulletinboard']
            ]
        };
    }
};
</script>
