<template>
    <v-container>
        <v-row>
            <v-col cols="1"></v-col>
            <v-col cols="5" align="center">
                <lottie-player src="https://assets9.lottiefiles.com/packages/lf20_fcecpvzp.json" background="transparent" speed="1" style="width: 650px; height: 650px" loop autoplay></lottie-player>
            </v-col>
            <v-col cols="6" align-self="center">
                <v-row>
                    <v-col cols="12">
                        <div class="text1">Study with Us!</div>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12">
                        <div class="text2">JUSTUDY에서 바로 스터디 모집하고, 가입하고!</div>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12">
                        <div class="text2">집에서 편하게 SSAFY 동기들과 스터디 하러가기</div>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12">
                        <br />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                        <v-btn class="button" x-large rounded dark @click="moveTo()">지금 바로 Go!</v-btn>
                    </v-col>
                </v-row>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import {mapState} from 'vuex';

export default {
    name: 'SignInView',
    computed: {
        ...mapState('moduleLogin', ['isLogin'])
    },
    methods: {
        moveTo() {
            if (this.isLogin.loginCheck) {
                window.location.href = '/study/mystudy';
            } else {
                window.location.href = '/login';
            }
        }
    }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:ital,wght@1,900&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap');

.text1 {
    font-family: 'Noto Sans', sans-serif;
    color: #edc300;
    font-size: 70px;
    width: max-content;
    text-shadow: 5px 5px 5px #e4e4e4;
}

.text2 {
    font-family: 'Noto Sans KR', sans-serif;
    font-size: 30px;
}

.button {
    background: linear-gradient(to right, #ffbbbb, #ff5a73);
}
</style>
