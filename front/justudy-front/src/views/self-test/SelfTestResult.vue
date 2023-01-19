<template>
    <v-app>
        <div class="d-flex flex-column align-center pt-16">
            <h1>당신의 레벨은?!?!?</h1>
            <img src="@/assets/juniorClass.png" style="padding: 20px" />
            <h1 style="font-size: 55px">초보 개발자</h1>
            <img src="@/assets/middleClass.png" />
            <h1 style="font-size: 55px">중급 개발자</h1>
            <img src="@/assets/highClass.png" style="border-radius: 50px" />
            <h1 style="font-size: 55px">고급 개발자</h1>
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
            problem: [
                {
                    question: '당신은 얼마나 오래 개발을 하셨나요(경력, 공부 모두 포함)',
                    option: ['0일', '0일~1달', '1달~6개월', '6개월~3년', '3년 이상']
                },
                {
                    question: '다음 중, 사용하실 수 있는 프로그래밍 언어는 몇가지 인가요?',
                    option: ['0가지', '1가지', '2가지', '3가지 이상']
                },
                {
                    question: '다음 웹페이지를 어느정도 까지 만들 수 있나요?',
                    option: ['할 수 없음', '똑같이 구현 가능', '기능까지 구현 가능', '데이터통신까지 구현가능']
                }
            ]
        };
    }
};
</script>
