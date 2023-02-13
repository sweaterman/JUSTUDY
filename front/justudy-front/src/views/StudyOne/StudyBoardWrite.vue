<template>
    <v-container>
        <!-- 글읽기 제목 -->
        <v-row :style="{marginTop: '1%'}">
            <v-col cols="12" md="4" />
            <v-col cols="12" md="4" justify="center" align="center">
                <div><h1>게시판 글쓰기</h1></div>
            </v-col>
            <v-col cols="12" md="4" />
        </v-row>
    </v-container>
</template>

<script>
export default {
    created() {
        localStorage.getItem('nickname');
    },
    data() {},
    methods: {}
};
</script>
<style>
.title_limit {
    width: 150px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    display: inline-block;
}
</style>
