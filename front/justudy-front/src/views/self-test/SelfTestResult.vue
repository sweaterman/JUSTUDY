<template>
    <v-app>
        <div class="d-flex flex-column align-center pt-16">
            <h1>당신의 레벨은?!?!?</h1>
            <img src="@/assets/juniorClass.png" style="padding: 20px" />
            <h1 style="font-size: 55px">초보 개발자</h1>
            <!-- <img src="@/assets/middleClass.png" />
            <h1 style="font-size: 55px">중급 개발자</h1>
            <img src="@/assets/highClass.png" style="border-radius: 50px" />
            <h1 style="font-size: 55px">고급 개발자</h1> -->
            <div class="d-flex pt-10">
                <router-link :to="`/self-test/problem/1`" style="text-decoration: none; color: black; margin-right: 10px">
                    <BasicButton :buttonLength="150" :height="60" :fontSize="25" content="다시하기" standard="px" />
                </router-link>
                <router-link :to="`/`" style="text-decoration: none; color: black">
                    <BasicButton :buttonLength="150" :height="60" :fontSize="25" content="메인으로" standard="px" />
                </router-link>
            </div>
        </div>
    </v-app>
</template>
<script>
import BasicButton from '@/components/common/BasicButton.vue';
export default {
    name: 'SelfTestProblem',
    components: {
        BasicButton
    },
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
