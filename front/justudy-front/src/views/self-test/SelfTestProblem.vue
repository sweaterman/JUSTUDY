<template>
    <v-app>
        <v-row>
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-row :style="{marginTop: '4%'}">
                    <v-col>
                        <h1>당신의 프로그래밍 수준은?</h1>
                        <hr width="50%" />
                    </v-col>
                </v-row>
                <v-row :style="{marginTop: '9%'}" justify="center" align="center">
                    <div v-if="this.$route.params.number == 1">
                        <img src="@/assets/test01.png" width="450px" height="450px" />
                    </div>
                    <div v-if="this.$route.params.number == 2">
                        <img src="@/assets/test02.png" />
                    </div>
                    <div v-if="this.$route.params.number == 3">
                        <img src="@/assets/test03.png" />
                    </div>
                </v-row>
                <v-row justify="center" align="center">
                    <h2 :style="{marginTop: '4%'}" justify="center" align="center">{{ problem[this.$route.params.number - 1].question }}</h2>
                </v-row>
                <v-row justify="center" align="center">
                    <div style="padding: 2%" v-for="(item, index) in problem[this.$route.params.number - 1].option" v-bind:key="index" padding>
                        <div v-on:click="setSelect(index)">
                            <v-checkbox :label="`${item}`" v-model="selected" color="orange" :value="`${index}`" hide-details></v-checkbox>
                        </div>
                        <!-- 얘는 여의치 않으면 직접 구현 -->
                    </div>
                </v-row>

                <v-row justify="center" align="center">
                    <div class="d-flex" v-on:click="nextOrPrev()">
                        <a :href="`/self-test/problem/${Number(this.$route.params.number) - 1}`" style="text-decoration: none; color: black; margin-right: 10px">
                            <BasicButton :buttonLength="150" :height="60" :fontSize="25" content="뒤로" standard="px" />
                        </a>
                        <a :href="`/self-test/${this.$route.params.number != 3 ? 'problem/' + Number(Number(this.$route.params.number) + 1) : 'confirm'}`" style="text-decoration: none; color: black">
                            <BasicButton :buttonLength="150" :height="60" :fontSize="25" content="다음" standard="px" />
                        </a>
                        <!-- {{ selected }} -->
                    </div>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>
<script>
export default {
    name: 'SelfTestProblem',
    components: {},
    data() {
        return {
            selected: [],
            problem: [
                {
                    question: '1 . 당신은 얼마나 오래 개발을 하셨나요(경력, 공부 모두 포함)',
                    option: ['0일', '0일~1달', '1달~6개월', '6개월~3년', '3년 이상']
                },
                {
                    question: '2 . 사용하실 수 있는 프로그래밍 언어는 몇가지 인가요?',
                    option: ['0가지', '1가지', '2가지', '3가지 이상']
                },
                {
                    question: '3 . 다음 웹페이지를 어느정도 까지 만들 수 있나요?',
                    option: ['할 수 없음', '똑같이 구현 가능', '기능까지 구현 가능', '데이터통신까지 구현가능']
                }
            ]
        };
    },

    methods: {
        setSelect(index) {
            console.log(this.selected[0]);
            this.selected.length = 0;

            // console.log(index)
            let result = localStorage.getItem('result') ? JSON.parse(localStorage.getItem('result')) : new Array(4).fill(0);
            result[this.$route.params.number - 1] = index;
            localStorage.setItem('result', JSON.stringify(result));
        }
    }
};
</script>
