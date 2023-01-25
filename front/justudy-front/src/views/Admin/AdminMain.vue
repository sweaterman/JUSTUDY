<template>
    <v-app>
        <v-row :style="{marginTop: '3%'}">
            <v-col cols="12" md="3"></v-col>
            <v-col cols="12" md="3">
                <v-row>
                    <v-col @click="moveTo('/admin')">대시보드</v-col>
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
            </v-col>
        </v-row>
    </v-app>
</template>

<script>
export default {
    name: 'AdminMain',
    components: {},

    data() {
        return {};
    },

    methods: {}
};
</script>
