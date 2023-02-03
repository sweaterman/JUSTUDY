<template>
    <v-app>
        <v-row>
            <v-col cols="12" md="4" />
            <v-col cols="12" md="4">
                <v-row>
                    <v-cols>
                        <v-row justify="center" align="center">
                            <h1 :style="{marginTop: '8%'}">당신의 레벨은?!?!?</h1>
                        </v-row>
                        <v-row justify="center" align="center">
                            <h1 style="font-size: 55px">초보 개발자</h1>
                        </v-row>
                        <v-row justify="center" align="center">
                            <img src="@/assets/juniorClass.png" style="padding: 20px" />
                        </v-row>
                    </v-cols>
                </v-row>
                <v-row :style="{marginTop: '5%'}">
                    <v-col cols="12" md="5">
                        <router-link :to="`/self-test/problem/1`" style="text-decoration: none; color: black" justify="center" align="center">
                            <v-btn block color="yellow" @click="gotoResultPage()" :style="{height: '65px', width: '180px', fontWeight: 'bold', fontSize: 'x-large'}">다시하기</v-btn>
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="5">
                        <router-link :to="`/`" style="text-decoration: none; color: black" justify="center" align="center">
                            <v-btn block color="white" :style="{height: '65px', width: '180px', fontWeight: 'bold', fontSize: 'x-large'}">메인으로</v-btn>
                        </router-link>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="4" />
        </v-row>
    </v-app>
</template>
<script>
export default {
    name: 'SelfTestProblem',
    components: {},
    data() {
        return {
            // localStorage 연결
            response: [],
            result: ''
        };
    },
    created() {
        // 여기서 localStorage에 있는 정보 넘겨주면 , Api 에서 등급 가지고 오기
        // this.$store.dispatch("self-test/updateResult",{ response : this.response})
    }
};
</script>
