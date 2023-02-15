<template>
    <v-container>
        <v-row>
            <v-col cols="2"></v-col>
            <!-- 좌측 Nav -->
            <v-col class="nav" cols="4">
                <v-avatar size="50">
                    <a href="/" class="logo">
                        <img :src="require('@/assets/imgSource/justudy.jpg')" alt="mainIcon" />
                    </a>
                </v-avatar>

                <v-btn text @click="moveTo('/study/search')">스터디</v-btn>
                <v-btn text @click="moveTo('/community?page=1&category=all')">커뮤니티</v-btn>
                <v-btn text @click="moveTo('/timer/study-start')">타이머</v-btn>
                <v-btn text @click="moveTo('/ranking/my-ranking')">랭킹</v-btn>
                <v-btn text @click="moveTo('/meeting')">스터디룸</v-btn>
            </v-col>

            <!-- 우측 Nav (login X) -->
            <v-col class="nav_right" cols="4" v-if="!isLogin.loginCheck">
                <v-btn elevation="0" dark color="#EDC300" @click="moveTo('/login')">로그인</v-btn>
                &nbsp;&nbsp;
                <v-btn elevation="0" dark color="#EDC300" @click="moveTo('/signUp')">회원가입</v-btn>
            </v-col>

            <!-- 우측 Nav (login O) -->
            <v-col class="nav_right" cols="4" v-if="isLogin.loginCheck">
                <v-btn text elevation="0" @click="moveTo('/study/mystudy')"><v-icon large> mdi-book-open-blank-variant </v-icon></v-btn>
                <v-btn text elevation="0" @click="moveTo('/mypage')"><v-icon large> mdi-account </v-icon></v-btn>
                <v-btn text elevation="0" @click="moveTo('/')"><v-icon large color="#FF5A73"> mdi-logout-variant </v-icon></v-btn>
            </v-col>

            <v-col cols="2"></v-col>
        </v-row>
    </v-container>
</template>

<script>
import {mapState} from 'vuex';

export default {
    name: 'NavHeaderBar',
    components: {},
    computed: {
        ...mapState('moduleLogin', ['isLogin'])
    },
    methods: {
        async moveTo(link) {
            //로그아웃을 한다.
            if (link == '/') {
                await this.$store.dispatch('moduleLogin/logout');
            } else window.location.href = link;
        }
    }
};
</script>

<style scoped>
.nav {
    display: inline-flex;
    justify-content: flex-start;
    align-items: center;
}

.nav_right {
    display: flex;
    justify-content: flex-end;
    align-items: center;
}
</style>
