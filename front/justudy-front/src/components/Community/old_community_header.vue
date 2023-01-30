<!-- 게시판 카테고리 목록 "백엔드/프론트/.../기타" -->
<template>
    <v-row :style="{marginTop: '1%', marginBottom: '2%'}">
        <v-col cols="12" md="2" />
        <v-col cols="12" md="8">
            <v-row>
                <v-col cols="12" md="2" justify="center" align="center">
                    <v-btn block color="yellow" @click="movetoboard1" :style="{height: '65px', width: '180px', fontWeight: 'bold', fontSize: 'large'}">모든 글</v-btn>
                </v-col>
                <v-col cols="12" md="2" justify="center" align="center">
                    <v-btn block color="white" @click="movetoboard2" :style="{height: '65px', width: '180px', fontWeight: 'bold', fontSize: 'large'}">Web</v-btn>
                </v-col>
                <v-col cols="12" md="2" justify="center" align="center">
                    <v-btn block color="white" @click="movetoboard3" :style="{height: '65px', width: '180px', fontWeight: 'bold', fontSize: 'large'}">DB + Infra</v-btn>
                </v-col>
                <v-col cols="12" md="2" justify="center" align="center">
                    <v-btn block color="white" @click="movetoboard1" :style="{height: '65px', width: '180px', fontWeight: 'bold', fontSize: 'large'}">CS</v-btn>
                </v-col>
                <v-col cols="12" md="2" justify="center" align="center">
                    <v-btn block color="white" @click="movetoboard2" :style="{height: '65px', width: '180px', fontWeight: 'bold', fontSize: 'large'}">새로운 기술</v-btn>
                </v-col>
                <v-col cols="12" md="2" justify="center" align="center">
                    <v-btn block color="white" @click="movetoboard3" :style="{height: '65px', width: '180px', fontWeight: 'bold', fontSize: 'large'}">공지사항</v-btn>
                </v-col>
            </v-row>
        </v-col>
        <v-col cols="12" md="2" />
    </v-row>
</template>

<script>
export default {
    name: 'CommuHeader',
    components: {},
    methods: {}
};
</script>
