<template>
    <v-container>
        <v-row>
            <v-col cols="2"></v-col>
            <!-- 좌측 Nav -->
            <v-col class="nav" cols="4">
                <v-btn text @click="moveToMain()">
                    <v-avatar size="50">
                        <img :src="require('@/assets/imgSource/justudy_chick.png')" alt="mainIcon" />
                    </v-avatar>
                </v-btn>

                <v-btn text @click="moveTo('/study/search')">스터디</v-btn>
                <v-btn text @click="moveTo('/community?page=1&category=all')">커뮤니티</v-btn>
                <v-btn text @click="moveTo('/timer/study-start')">타이머</v-btn>
                <v-btn text @click="moveTo('/ranking/my-ranking')">랭킹</v-btn>

                <v-btn v-if="adminCheck" text @click="moveTo('/admin')">관리자</v-btn>
                <v-btn v-if="!adminCheck" text @click="moveTo('/meeting')">스터디룸</v-btn>
            </v-col>

            <!-- 우측 Nav (login X) -->
            <v-col class="nav_right" cols="4" v-if="!isLogin.loginCheck">
                <v-btn elevation="0" dark color="#EDC300" @click="moveTo('/login')">로그인</v-btn>
                &nbsp;&nbsp;
                <v-btn elevation="0" dark color="#EDC300" @click="moveTo('/signUp')">회원가입</v-btn>
            </v-col>

            <!-- 우측 Nav (login O) -->
            <v-col class="nav_right" cols="4" v-if="isLogin.loginCheck">
                <v-tooltip bottom dark color="#000000">
                    <template v-slot:activator="{on, attrs}">
                        <v-btn text elevation="0" @click="moveTo('/study/mystudy')" v-bind="attrs" v-on="on"><v-icon large> mdi-book-open-blank-variant </v-icon></v-btn>
                    </template>
                    <span>마이스터디</span>
                </v-tooltip>

                <v-tooltip bottom dark color="#000000">
                    <template v-slot:activator="{on, attrs}">
                        <v-btn text elevation="0" @click="moveTo('/mypage')" v-bind="attrs" v-on="on"><v-icon large> mdi-account </v-icon></v-btn>
                    </template>
                    <span>마이페이지</span>
                </v-tooltip>

                <v-tooltip bottom dark color="#000000">
                    <template v-slot:activator="{on, attrs}">
                        <v-btn text elevation="0" @click="moveTo('/')" v-bind="attrs" v-on="on" color="#FF5A73"><v-icon large> mdi-logout-variant </v-icon></v-btn>
                    </template>
                    <span>로그아웃</span>
                </v-tooltip>
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
    data() {
        return {
            adminCheck: false
        };
    },
    created() {
        if (localStorage.getItem('admin')) {
            this.adminCheck = true;
        }
    },
    methods: {
        async moveTo(link) {
            //로그아웃을 한다.
            if (link == '/') {
                await this.$store.dispatch('moduleLogin/logout');
            } else window.location.href = link;
        },
        moveToMain() {
            //메인으로 감
            window.location.href = '/';
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
