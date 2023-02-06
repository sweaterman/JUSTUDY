<template>
    <v-app>
        <TimerHeader />
        <v-row>
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <div class="d-flex justify-center">
                    <DigitalClock :allTime="allTimeMe" :fontSize="300" />
                </div>
                <Boundary content="" standard="px" :startPoint="100" :hasContent="false"></Boundary>
                <v-row>
                    <v-col class="d-flex flex-column align-center mt-10">
                        <BasicButton :buttonLength="500" :height="60" standard="px" :content="`${today} 전날 공부량 1등`" />
                        <h1>
                            {{ firstYesterday }}
                            <span style="color: #ffb000">돌로스원숭숭</span>
                            님
                        </h1>
                        <DigitalClock :allTime="allTimeFirst" :fontSize="150" />
                    </v-col>
                    <v-col class="d-flex flex-column align-center mt-10">
                        <BasicButton :buttonLength="500" :height="60" standard="px" content="지금 같이 공부하는 싸피인들" />
                        <h1 style="color: #ffb000; font-size: 100px">256명</h1>
                    </v-col>
                </v-row>
            </v-col>

            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>
<script>
import TimerHeader from '../../components/timer/TimerHeader.vue';
import DigitalClock from '@/components/timer/DigitalClock.vue';
import Boundary from '@/components/common/Boundary.vue';
import BasicButton from '@/components/common/BasicButton.vue';
export default {
    name: 'StudyStart',
    data() {
        return {
            allTimeMe: 0,
            allTimeFirst: 9000,
            today: '',
            firstYesterday: {}
        };
    },

    components: {
        TimerHeader,
        DigitalClock,
        Boundary,
        BasicButton
    },
    async created() {
        // API 받기
        await this.$store.dispatch('moduleTimer/getFirstYesterday');
        this.firstYesterday = this.$store.state.moduleTimer.firstYesterday;
        // 무언가 하면 개인 공부 시간 저장
        // this.$store.dispatch('moduleTimer/saveIndividualTime');
        // 날짜 받기
        let today = new Date();
        let year = today.getFullYear();
        let month = today.getMonth() >= 9 ? today.getMonth() + 1 : '0' + (today.getMonth() + 1);
        let date = today.getDate() >= 10 ? today.getDate() : '0' + today.getDate();
        this.today = year + '-' + month + '-' + date;

        setInterval(() => {
            this.allTimeMe++;
            // this.allTimeFirst++;
        }, 1000);
    }
};
</script>
