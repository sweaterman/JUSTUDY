<template>
    <v-app>
        <v-row>
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-row :style="{marginTop: '4%'}" justify="center" align="center">
                    <img src="@/assets/error.gif" width="450px" height="450px" />
                </v-row>
                <v-row :style="{marginTop: '4%'}" justify="center" align="center">
                    <h1>해당 페이지를 찾을 수 없습니다!</h1>
                </v-row>
                <v-row :style="{marginTop: '8%'}" justify="center" align="center">
                    <v-btn color="white" @click="movetomain" :style="{height: '50px', width: '250px', fontWeight: 'bold', fontSize: 'large'}">메인 페이지로 가기</v-btn>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>
<script>
export default {
    name: 'ErrorPage',
    components: {},
    data() {
        return {};
    },
    methods: {
        movetomain() {
            window.location.href = '/';
        }
    }
};
</script>
