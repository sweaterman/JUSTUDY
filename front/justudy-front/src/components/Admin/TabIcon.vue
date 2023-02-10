<template>
    <v-app>
        <div v-if="isTab" >
            <v-row>
                <v-col @click="onClickTab">X</v-col>
            </v-row>
            <v-row>
                <v-col @click="moveTo('/admin')">대시보드</v-col>
            </v-row>
            <v-row>
                <v-col @click="moveTo('/admin/report/1')">신고 게시판</v-col>
            </v-row>
            <v-row>
                <v-col @click="moveTo('/admin/user/1')">사용자 관리</v-col>
            </v-row>
            <v-row>
                <v-col @click="moveTo('/admin/study/1')">스터디 관리</v-col>
            </v-row>
            <v-row>
                <v-col @click="moveTo('/admin/community/1')">게시판 관리</v-col>
            </v-row>
        </div>
        <v-btn @click="onClickTab" v-if="!isTab" fab color="yellow"></v-btn>

    </v-app>
</template>

<script>
export default {
    name: 'AdminMain',
    components: {},

    data() {
        return {
            isTab:false,
        };
    },

    
    methods: {
        moveTo(link) {
            window.location.href = link;
        },
        onClickTab(){
            this.isTab = !this.isTab;
        },
    }
};
</script>
