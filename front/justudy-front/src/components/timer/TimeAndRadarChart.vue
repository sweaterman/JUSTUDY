<template>
    <div style="padding-left: 25%; padding-right: 25%">
        <v-row>
            <v-col>
                <div v-for="item in 6" v-bind:key="item">
                    <div class="d-flex align-center">
                        <h1>Algorithm</h1>
                        <div style="margin-right: 20px"></div>
                        <DigitalClock :allTime="aaa" :fontSize="50" />
                    </div>
                </div>
            </v-col>
            <v-col>
                <RadarChart />
            </v-col>
        </v-row>
    </div>
</template>
<script>
import RadarChart from '../common/RadarChart.vue';
import DigitalClock from './DigitalClock.vue';
export default {
    name: 'TimeAndRadarChart',
    components: {
        DigitalClock,
        RadarChart
    },
    data() {
        return {
            aaa: 3000
        };
    }
};
</script>
