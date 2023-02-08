<template>
    <v-container :style="{marginTop: '1%', marginBottom: '1%'}">
        <v-row>
            <!-- 좌측 Nav -->
            <v-col cols="12" md="6" align="center">
                <v-row justify="center">
                    <v-col class="nav" cols="12" md="2">
                        <a href="/" class="logo">
                            <v-img alt="Vue logo" :src="require('@/assets/justudy.png')" max-height="40" max-width="40" />
                        </a>
                    </v-col>
                    <v-col class="nav" @click="moveTo('/study/search')" cols="3" md="2"><div class="hover">스터디</div></v-col>
                    <v-col class="nav" @click="moveTo('/community/1?page=1')" cols="3" md="2"><div class="hover">커뮤니티</div></v-col>
                    <v-col class="nav" @click="moveTo('/timer/study-start')" cols="3" md="2"><div class="hover">타이머</div></v-col>
                    <v-col class="nav" @click="moveTo('/ranking/my-ranking')" cols="3" md="2"><div class="hover">랭킹</div></v-col>
                    <v-col class="nav" @click="moveTo('/meeting')" cols="3" md="2"><div class="hover">**RTC 임시**</div></v-col>
                    <!-- <v-col class="nav" cols="12" md="2"></v-col> -->
                </v-row>
            </v-col>

            <!-- 우측 Nav (login X) -->
            <v-col class="nav" cols="12" md="6" v-if="!isLogin.loginCheck">
                <v-row>
                    <v-col cols="0" md="8"></v-col>
                    <v-col class="nav" @click="moveTo('/login')" cols="6" md="2"><div class="hover">로그인</div></v-col>
                    <v-col class="nav" @click="moveTo('/signUp')" cols="6" md="2"><div class="hover">회원가입</div></v-col>
                </v-row>
            </v-col>

            <!-- 우측 Nav (login O) -->
            <v-col class="nav" cols="12" md="6" v-if="isLogin.loginCheck">
                <v-row>
                    <v-col cols="0" md="5"> </v-col>
                    <v-col cols="12" md="7">
                        <v-row>
                            <v-col class="nav" @click="moveTo('/study/mystudy')" cols="4"><div class="hover">마이스터디</div></v-col>
                            <v-col class="nav" @click="moveTo('/mypage')" cols="4"><div class="hover">마이페이지</div></v-col>
                            <v-col class="nav" @click="moveTo('/')" cols="4"><div class="hover">로그아웃</div></v-col>
                        </v-row>
                    </v-col>
                </v-row>
            </v-col>
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
    display: flex;
    justify-content: center;
    align-items: center;
}

.hover {
    cursor: pointer;
}

.hover:hover {
    color: gold;
}
</style>
