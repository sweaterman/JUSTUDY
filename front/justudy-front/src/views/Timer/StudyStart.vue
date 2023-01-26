<template>
    <v-app style="padding-left: 10%; padding-right: 10%">
        <div>
            <Common />
        </div>
        <div class="d-flex justify-center">
            <DigitalClock :allTime="allTimeMe" :fontSize="300" />
        </div>
        <Boundary content="" standard="px" :startPoint="100" :hasContent="false"></Boundary>
        <BasicButton :buttonLength="500" :height="60" standard="px" :content="`${today} 현재 공부량 1등`" />
        <h1>
            <span style="color: #ffb000">돌로스원숭숭</span>
            님
        </h1>
        <DigitalClock :allTime="allTimeFirst" :fontSize="150" />
        <BasicButton :buttonLength="500" :height="60" standard="px" content="지금 같이 공부하는 싸피인들" />
        <h1 style="color: #ffb000; font-size: 100px">256명</h1>
    </v-app>
</template>
<script>
import Common from '@/components/timer/Common.vue';
import DigitalClock from '@/components/timer/DigitalClock.vue';
import Boundary from '@/components/common/Boundary.vue';
import BasicButton from '@/components/common/BasicButton.vue';
export default {
    name: 'StudyStart',
    data() {
        return {
            allTimeMe: 3582,
            allTimeFirst: 9000,
            today: ''
        };
    },

    components: {
        Common,
        DigitalClock,
        Boundary,
        BasicButton
    },
    created() {
        let today = new Date();
        let year = today.getFullYear();
        let month = today.getMonth() >= 9 ? today.getMonth() + 1 : '0' + (today.getMonth() + 1);
        let date = today.getDate() >= 10 ? today.getDate() : '0' + today.getDate();
        this.today = year + '-' + month + '-' + date;

        setInterval(() => {
            this.allTimeMe++;
            this.allTimeFirst++;
        }, 1000);
    }
};
</script>
