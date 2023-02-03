<template>
    <v-container>
        <OneLineInfo :category="`닉네임`" :detail="user.nickname" />
        <OneLineInfo :category="`희망 스택`" :detail="user?.category?.join(',')" />
        <OneLineInfo :category="`희망 진로`" :detail="user.dream ? user.dream : '없음'" />

        <v-row justify="center" align="center">
            <v-col cols="12" md="4">현재 상태</v-col>
            <v-col cols="12" md="1" />
            <v-col cols="12" md="7">
                <v-select v-model="status" :items="items" menu-props="auto" value="ddd" />
            </v-col>
        </v-row>
        <OneLineInfo :category="`포인트 🏆`" :detail="'+' + user.badgeCount" />
    </v-container>
</template>
<script>
import OneLineInfo from '../common/OneLineInfo.vue';
export default {
    components: {OneLineInfo},
    name: 'ProfileDetail',
    props: {
        user: {
            type: Object
        }
    },
    data() {
        return {
            // 얘네들 user와 관련된 것들로 바꿀 거임
            items: ['온라인🟢', '오프라인⚪', '집중중🔴', '자리비움🟡'],
            nickname: this.user.nickname,

            status: ''
        };
    },
    created() {
        if (this.user.status == '온라인') {
            this.status = '온라인🟢';
        } else if (this.user.status == '오프라인') {
            this.status = '오프라인⚪';
        } else if (this.user.status == '집중중') {
            this.status = '집중중🔴';
        } else if (this.user.status == '자리비움') {
            this.status = '자리비움🟡';
        }
    }
};
</script>
