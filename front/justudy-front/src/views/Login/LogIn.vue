<template>
    <v-app>
        <!-- 제목 ~ 로그인 -->
        <v-row :style="{marginTop: '5%'}">
            <v-col cols="12" md="4" />
            <v-col cols="12" md="4" justify="center" align="center">
                <TextButton :buttonLength="100" :height="70" :fontSize="10" :content="`로그인`" :standard="px" />
            </v-col>
            <v-col cols="12" md="4" />
        </v-row>

        <!-- <v-row>
            <TextBoundary content="필수 입력 사항" standard="px" :startPoint="500"></TextBoundary>
        </v-row> -->

        <v-row>
            <!-- 좌측 여백 -->
            <v-col cols="12" md="3" style="padding: 4em" />

            <!-- 기본 정보 선택 부분 -->
            <v-col cols="12" md="6" style="padding: 4em">
                <!-- 아이디 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>아이디</v-subheader>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field v-model="user.userId" dense outlined label="아이디" :rules="[v => !!v || '아이디는 필수입니다.']"></v-text-field>
                    </v-col>
                    <v-col cols="2" />
                </v-row>

                <!-- 비밀번호 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>비밀번호</v-subheader>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field type="password" v-model="user.password" dense outlined label="비밀번호" :rules="[v => !!v || '비밀번호는 필수입니다.']"></v-text-field>
                    </v-col>
                </v-row>
            </v-col>

            <!-- 우측 여백 -->
            <v-col cols="12" md="3" style="padding: 4em" />
        </v-row>

        <!-- 로그인 버튼 ~ 시작 -->
        <v-row :style="{marginTop: '3%', marginBottom: '5%'}">
            <v-col cols="12" md="4" />
            <v-col cols="12" md="2" justify="center" align="center">
                <v-btn color="yellow" @click="movetomain" style="height: 50px; width: 170px; fontweight: bold; fontsize: large">로그인</v-btn>
            </v-col>
            <v-col cols="12" md="2" justify="center" align="center">
                <v-btn color="yellow" @click="movetosignin" style="height: 50px; width: 170px; fontweight: bold; fontsize: large">회원가입</v-btn>
            </v-col>
            <v-col cols="12" md="4" />
        </v-row>
        <!-- 로그인 버튼 ~ 끝 -->
    </v-app>
</template>

<script>
import TextButton from '../../components/common/TextButton.vue';

export default {
    name: 'SignInView',
    components: {
        TextButton
    },

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
