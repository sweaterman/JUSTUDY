<template>
    <div>
        <v-row align="center">
            <v-col align="center" justify="space-between">
                <BasicButton :buttonLength="300" :height="60" :fontSize="36" :content="`${contentLeft}`" standard="px" />
                <Profile :diameter="300" standard="px" :src="require('@/assets/juniorClass.png')" />
                <h1>돌로스원숭숭님</h1>
                <DigitalClock :allTime="3300" :fontSize="100" />
            </v-col>
            <v-col>
                <BasicButton :buttonLength="300" :height="60" :fontSize="36" :content="`${contentRight}`" standard="px" />
                <v-row v-for="item in data" v-bind:key="item" align="center">
                    <v-col cols="2"
                        ><h2>{{ item + 1 }}</h2></v-col
                    >
                    <v-col cols="7"><h2>알고리즘을 매일 풀고싶은 사람 나야 나</h2></v-col>
                    <v-col><DigitalClock :allTime="3000" :fontSize="30" /></v-col>
                </v-row>
            </v-col>
        </v-row>
        <hr />
        {{ data }}
    </div>
</template>
<script>
import BasicButton from '../common/BasicButton.vue';
import Profile from '../mypage/Profile.vue';
import DigitalClock from './DigitalClock.vue';

export default {
    name: 'RankingView',
    components: {
        BasicButton,
        DigitalClock,
        Profile
    },
    props: {
        contentLeft: {
            type: String
        },
        contentRight: {
            type: String
        },
        data: {}
    }
};
</script>
