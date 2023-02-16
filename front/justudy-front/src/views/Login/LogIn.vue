<template>
    <v-container style="margin-bottom: 30px">
        <!-- 제목 ~ 로그인 -->
        <v-row>
            <v-col cols="12" align="center">
                <div class="title">로그인</div>
            </v-col>
        </v-row>

        <!-- 구분선 -->
        <v-row>
            <v-col cols="3"> </v-col>
            <v-col cols="6">
                <v-divider></v-divider>
            </v-col>
            <v-col cols="3"> </v-col>
        </v-row>

        <v-row style="margin-top: 100px">
            <!-- 좌측 여백 -->
            <v-col cols="12" md="3" />

            <!-- 아이디/비번입력부분 -->
            <v-col cols="6">
                <!-- 아이디 -->
                <v-row>
                    <v-col cols="4" class="d-flex flex-row-reverse">
                        <v-subheader>아이디</v-subheader>
                    </v-col>
                    <v-col cols="5">
                        <v-text-field v-model="user.userId" dense outlined label="아이디" :rules="[v => !!v || '아이디는 필수입니다.']"></v-text-field>
                    </v-col>
                </v-row>

                <!-- 비밀번호 -->
                <v-row>
                    <v-col cols="4" class="d-flex flex-row-reverse">
                        <v-subheader>비밀번호</v-subheader>
                    </v-col>
                    <v-col cols="5">
                        <v-text-field type="password" v-model="user.password" dense outlined label="비밀번호" :rules="[v => !!v || '비밀번호는 필수입니다.']"></v-text-field>
                    </v-col>
                </v-row>
            </v-col>

            <!-- 우측 여백 -->
            <v-col cols="3"> </v-col>
        </v-row>

        <!-- 로그인 버튼 -->
        <v-row style="margin-top: 80px; margin-bottom: 60px">
            <v-col cols="12" align="center">
                <v-btn class="button" x-large color="yellow" elevation="0" @click="movetomain">로그인</v-btn>
                &nbsp;&nbsp;&nbsp;
                <v-btn class="button" x-large color="yellow" elevation="0" @click="movetosignin">회원가입</v-btn>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
    name: 'SignInView',
    data() {
        return {
            user: {
                userId: '',
                password: ''
            }
        };
    },

    methods: {
        async movetomain() {
            await this.$store.dispatch('moduleLogin/login', {
                user: this.user
            });
        },
        movetosignin() {
            window.location.href = '/signUp';
        }
    }
};
</script>

<style scoped>
.title {
    padding-top: 70px;
    font-size: 60px;
    font-weight: bold;
}

.button {
    font-size: 20px;
    height: 50px;
    width: 150px;
}
</style>
