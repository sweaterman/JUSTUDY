<template>
    <v-app>
        <div class="d-flex flex-column justify-center align-center" style="width: 100%; height: 100%">
            <h1>선택을 바꾸시지 않겠습니까?</h1>
            <div class="d-flex pt-10">
                <router-link :to="`/self-test/problem/3`" style="text-decoration: none; color: black; margin-right: 10px">
                    <BasicButton :buttonLength="150" :height="60" :fontSize="25" content="아니오" standard="px" />
                </router-link>
                <router-link :to="`/self-test/result`" style="text-decoration: none; color: black">
                    <BasicButton :buttonLength="150" :height="60" :fontSize="25" content="예" standard="px" />
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
            // 여기서 localStorage에 있는 정보 넘겨주면 , Api 에서 등급 가지고 오기
        };
    }
};
</script>
