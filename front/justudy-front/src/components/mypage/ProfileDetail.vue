<template>
    <v-container>
        <OneLineInfo :category="`닉네임`" :detail="nickname" />
        <OneLineInfo :category="`희망 스택`" :detail="stack" />
        <OneLineInfo :category="`희망 진로`" :detail="wanttobe" />
        <v-row justify="center" align="center">
            <v-col cols="12" md="4">현재 상태</v-col>
            <v-col cols="12" md="1" />
            <v-col cols="12" md="7">
                <v-select :items="items" menu-props="auto" />
            </v-col>
        </v-row>
        <OneLineInfo :category="`포인트 🏆`" :detail="score" />
    </v-container>
</template>
<script>
import OneLineInfo from '../common/OneLineInfo.vue';
export default {
    components: {OneLineInfo},
    name: 'ProfileDetail',
    props: {
        user: {}
    },
    data() {
        return {
            // 얘네들 user와 관련된 것들로 바꿀 거임
            items: ['온라인🟢', '오프라인⚪', '집중중🔴', '자리비움🟡'],
            nickname: '돌숭숭',
            stack: 'React',
            wanttobe: 'FE 개발자',
            state: '온라인🟢',
            score: '+ 712'
        };
    }
};
</script>
