<template>
    <v-container>
        <v-row>
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-row :style="{marginTop: '2%'}">
                    <v-col>
                        <h1>당신의 프로그래밍 수준은?</h1>
                        <hr width="50%" />
                    </v-col>
                </v-row>
                <v-row :style="{marginTop: '2%'}" justify="center" align="center">
                    <div v-if="this.$route.params.number == 1">
                        <img src="@/assets/testimages/test01.png" width="450px" height="450px" />
                    </div>
                    <div v-if="this.$route.params.number == 2">
                        <img src="@/assets/testimages/test02.png" width="450px" height="450px" />
                    </div>
                    <div v-if="this.$route.params.number == 3">
                        <img src="@/assets/testimages/test03.png" width="450px" height="450px" />
                    </div>
                    <div v-if="this.$route.params.number == 4">
                        <img src="@/assets/testimages/test04.jpg" width="450px" height="450px" />
                    </div>
                    <div v-if="this.$route.params.number == 5">
                        <img src="@/assets/testimages/test05.png" width="450px" height="450px" />
                    </div>
                    <div v-if="this.$route.params.number == 6">
                        <img src="@/assets/testimages/test06.jpg" width="450px" height="450px" />
                    </div>
                    <div v-if="this.$route.params.number == 7">
                        <img src="@/assets/testimages/test07.jpg" width="450px" height="450px" />
                    </div>
                </v-row>

                <v-row justify="center" align="center">
                    <h2 :style="{marginTop: '2%'}" justify="center" align="center">
                        {{ problem[this.$route.params.number - 1].question }}
                    </h2>
                </v-row>
                <v-row justify="center" align="center">
                    <div style="padding: 2%" v-for="(item, index) in problem[this.$route.params.number - 1].option" v-bind:key="index" padding>
                        <div v-on:click="setSelect(index)">
                            <v-checkbox :style="{fontWeight: 'bold', fontSize: 'xx-large'}" :label="`${item}`" v-model="selected" color="yellow" :value="`${index}`" hide-details></v-checkbox>
                        </div>
                        <!-- 얘는 여의치 않으면 직접 구현 -->
                    </div>
                </v-row>

                <v-row justify="center" align="center">
                    <v-col cols="12" md="3" />
                    <v-col cols="12" md="3">
                        <!-- <div class="d-flex" v-on:click="nextOrPrev()"> -->
                        <router-link
                            :to="`/self-test/${this.$route.params.number != 1 ? 'problem/' + Number(Number(this.$route.params.number) - 1) : 'problem/1'}`"
                            style="text-decoration: none; color: black"
                        >
                            <v-btn v-on:click="nextOrPrev()" color="yellow" :style="{fontWeight: 'bold', fontSize: 'large'}">뒤로</v-btn>
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="2" />
                    <v-col cols="12" md="3">
                        <router-link
                            :to="`/self-test/${this.$route.params.number != 7 ? 'problem/' + Number(Number(this.$route.params.number) + 1) : 'confirm'}`"
                            style="text-decoration: none; color: black"
                        >
                            <v-btn v-on:click="nextOrPrev()" color="yellow" :style="{fontWeight: 'bold', fontSize: 'large'}">다음</v-btn>
                            <!-- <BasicButton :buttonLength="150" :height="60" :fontSize="25" content="다음" standard="px" /> -->
                        </router-link>
                    </v-col>
                    <v-col cols="12" md="1" />

                    <!-- {{ selected }} -->
                    <!-- </div> -->
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>
    </v-container>
</template>
<script>
// import BasicButton from '@/components/common/BasicButton.vue';
export default {
    name: 'SelfTestProblem',
    components: {
        // BasicButton
    },
    data() {
        return {
            selected: [],
            problem: [
                {
                    question: '1 . 당신은 얼마나 오래 개발을 하셨나요(경력, 공부 모두 포함)',
                    option: ['아직...', '1년 이상', '2년 이상', '3년 이상']
                },
                {
                    question: '2 . 협업 툴(ex. Git)을 사용한 팀 프로젝트를 경험해봤나요?',
                    option: ['아직...', '1 번', '2 번', '3 번 이상']
                },
                {
                    question: '3 . 다음 웹페이지를 어느 정도 까지 만들 수 있나요?',
                    option: ['아직...', '똑같이 구현 가능', '기능까지 구현 가능', '데이터통신까지 구현가능']
                },
                {
                    question: '4 . 백준 알고리즘 티어가 어떻게 되나요?',
                    option: ['브론즈', '실버', '골드', '플레티넘 이상']
                },
                {
                    question: '5 . 관계형 데이터베이스 사용할 수 있나요?',
                    option: ['아직...', '단순한 조회는 가능', 'Join 을 활용해 원하는 조회 가능', '원하는대로 ERD 구성 가능']
                },
                {
                    question: '6 . 프로젝트 아키텍쳐 아시나요?',
                    option: ['아직...', '들어는 봤어요', '읽을 수는 있어요', '직접 작성할 수 있어요']
                },
                {
                    question: '7 . 하루 최대 몇 시간까지 코딩 해봤나요?',
                    option: ['1시간', '3시간', '6시간', '12시간']
                }
            ]
        };
    },

    methods: {
        nextOrPrev() {
            this.selected.length = 0;
            this.selected = [...this.selected];
        },
        setSelect(index) {
            console.log(this.selected[0]);
            this.selected.length = 0;

            // console.log(index)
            let result = localStorage.getItem('result') ? JSON.parse(localStorage.getItem('result')) : new Array(7).fill(0);
            result[this.$route.params.number - 1] = index;
            localStorage.setItem('result', JSON.stringify(result));
        }
    }
};
</script>
