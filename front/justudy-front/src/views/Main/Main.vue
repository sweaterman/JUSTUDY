<template>
    <v-app>
        <NavHeader />
        <v-row :style="{marginTop: '1%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8" align="center">
                <h1>Justudy 에서 공부하세요!</h1>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>

        <!-- 배너 자리 -->
        <v-row :style="{marginTop: '1%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <a href="/" class="banner"><img alt="Vue banner" width="100%" height="100%" src="../../assets/banner.jpg" /></a>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>

        <!-- 스터디 모집중! 자리 -->
        <v-row :style="{marginTop: '5%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-row>
                    <v-col cols="12" md="4" align="left">
                        <h1>스터디 모집 중</h1>
                        <hr />
                    </v-col>
                    <v-col cols="12" md="4" />
                    <v-col cols="12" md="4" align="right">
                        <v-text>더보기</v-text>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12" md="3">
                        <a href="/" class="logo"><img alt="Vue logo" width="100%" height="100%" src="../../assets/logo.jpg" /></a>
                    </v-col>
                    <v-col cols="12" md="3">
                        <a href="/" class="logo"><img alt="Vue logo" width="100%" height="100%" src="../../assets/logo.jpg" /></a>
                    </v-col>
                    <v-col cols="12" md="3">
                        <a href="/" class="logo"><img alt="Vue logo" width="100%" height="100%" src="../../assets/logo.jpg" /></a>
                    </v-col>
                    <v-col cols="12" md="3">
                        <a href="/" class="logo"><img alt="Vue logo" width="100%" height="100%" src="../../assets/logo.jpg" /></a>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>

        <!-- 글목록 -->
        <v-row :style="{marginTop: '5%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-row>
                    <v-col cols="12" md="4" align="left">
                        <h1>지금 HOT 한 글</h1>
                        <hr />
                    </v-col>
                    <v-col cols="12" md="4" />
                    <v-col cols="12" md="4" align="right">
                        <v-text>더보기</v-text>
                    </v-col>
                </v-row>
                <v-row :style="{marginTop: '2%'}">
                    <v-simple-table style="width: 100%">
                        <thead>
                            <tr style="font-weight: bolder">
                                <td style="width: 10%; font-size: large">No</td>
                                <td style="width: 50%; font-size: large">제목</td>
                                <td style="width: 15%; font-size: large">글쓴이</td>
                                <td style="width: 15%; font-size: large">작성시간</td>
                                <td style="width: 10%; font-size: large">좋아요</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="item in contentlist" :key="item.id" @click="movetocontent(item.id)">
                                <td>{{ item.writer }}</td>
                                <td>{{ item.title }}</td>
                                <td>{{ item.createdAt.split('T')[0] }}</td>
                                <!-- Sequelize의 createdAt, updatedAt의 날짜 형식이 '2021-12-10T12:38:52.000Z' 이런 식이여서 
                               split('T')[0]을 통해 날짜만 표시 -->
                            </tr>
                        </tbody>
                    </v-simple-table>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>

        <NavFooter />
    </v-app>
</template>

<script>
import NavHeader from '../../components/common/NavHeader.vue';
import NavFooter from '../../components/common/NavFooter.vue';

export default {
    name: 'SignInView',
    components: {
        NavHeader,
        NavFooter
    },

    data() {
        return {};
    },

    methods: {
        movetomain() {
            window.location.href = '/';
        }
    }
};
</script>
