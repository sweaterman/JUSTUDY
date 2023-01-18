<!-- 게시판 카테고리 목록 "백엔드/프론트/.../기타" -->
<template>
    <v-row :style="{marginTop: '1%', marginBottom: '2%'}">
        <v-col cols="12" md="1" />
        <v-col cols="12" md="10">
            <v-row>
                <v-col cols="12" md="2" justify="center" align="center">
                    <v-btn block color="yellow" @click="movetoboard1" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large'}">Back-end</v-btn>
                </v-col>
                <v-col cols="12" md="2" justify="center" align="center">
                    <v-btn block color="white" @click="movetoboard2" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large'}">Front-end</v-btn>
                </v-col>
                <v-col cols="12" md="2" justify="center" align="center">
                    <v-btn block color="white" @click="movetoboard3" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large'}">Infra</v-btn>
                </v-col>
                <v-col cols="12" md="2" justify="center" align="center">
                    <v-btn block color="white" @click="movetoboard1" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large'}">CS</v-btn>
                </v-col>
                <v-col cols="12" md="2" justify="center" align="center">
                    <v-btn block color="white" @click="movetoboard2" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large'}">Algorithm</v-btn>
                </v-col>
                <v-col cols="12" md="2" justify="center" align="center">
                    <v-btn block color="white" @click="movetoboard3" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large'}">Etc.</v-btn>
                </v-col>
            </v-row>
        </v-col>
        <v-col cols="12" md="1" />
    </v-row>
</template>

<script>
export default {
    name: 'CommuHeader',
    components: {},
    methods: {}
};
</script>
