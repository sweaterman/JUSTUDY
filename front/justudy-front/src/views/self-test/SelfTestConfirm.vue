<template>
    <v-app>
        <v-row>
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-row justify="center" align="center">
                    <h1 :style="{marginTop: '25%'}">선택을 바꾸시지 않겠습니까?</h1>
                </v-row>
                <v-row :style="{marginTop: '10%'}" justify="center" align="center">
                    <v-col cols="12" md="3" />
                    <v-col cols="12" md="2">
                        <router-link :to="`/self-test/result`" style="text-decoration: none; color: black">
                            <v-btn block color="yellow" @click="gotoResultPage()" :style="{height: '65px', width: '180px', fontWeight: 'bold', fontSize: 'large'}">예</v-btn>
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="2">
                        <router-link :to="`/self-test/problem/7`" style="text-decoration: none; color: black">
                            <v-btn block color="white" :style="{height: '65px', width: '180px', fontWeight: 'bold', fontSize: 'large'}">아니오</v-btn>
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="3" />
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>
<script>
export default {
    name: 'SelfTestProblem',
    components: {},
    data() {
        return {};
    },
    methods: {
        gotoResultPage() {}
    }
};
</script>
