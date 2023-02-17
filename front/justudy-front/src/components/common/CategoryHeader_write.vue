<!-- 게시판 카테고리 목록 "백엔드/프론트/.../기타" -->
<template>
    <v-container>
        <v-row>
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8" justify="center" align="center">
                <!-- 카테고리 지정 -->
                <v-row :style="{padding: '1%'}" justify="center" align="center">
                    <v-tabs color="black" v-model="tab" justify="center" align="center">
                        <v-tabs-slider color="yellow"></v-tabs-slider>
                        <v-tab v-for="top in topCategories" :key="top.key">
                            <h2 v-on:click="updateData(top.key)">{{ top.value }}</h2>
                        </v-tab>
                    </v-tabs>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>
    </v-container>
</template>

<script>
import {mapState} from 'vuex';

export default {
    name: 'CommuHeader',
    computed: {
        ...mapState('moduleStudy', ['topCategories'])
    },
    created() {
        this.$store.dispatch('moduleStudy/getTopCategories');
    },
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
                ['FRONT-END', 'frontend'],
                ['BACK-END', 'backend'],
                ['INFRA', 'infra'],
                ['MOBIlE', 'mobile'],
                ['ALGORITHM', 'algorithm'],
                ['CS', 'computer-science'],
                ['PROJECT', 'project'],
                ['ETC', 'etc']
            ]
        };
    }
};
</script>
