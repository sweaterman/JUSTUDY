<template>
    <v-app style="padding-left: 10%; padding-right: 10%">
        <Common />

        <TimerCalendar />
        <div style="margin-bottom: 100px"></div>
        <Boundary content="TOTAL" standard="%" :startPoint="40"></Boundary>
        <v-row>
            <v-col>
                <DigitalClockPerDate content="THIS WEEK" />
            </v-col>
            <v-col>
                <DigitalClockPerDate content="THIS MONTH" />
            </v-col>
        </v-row>
        <div class="mt-16"></div>
        <Boundary content="AVERAGE" standard="%" :startPoint="40"></Boundary>
        <v-row>
            <v-col>
                <DigitalClockAverage content="THIS WEEK" :studyTime="15" />
            </v-col>
            <v-col>
                <DigitalClockAverage content="THIS MONTH" :studyTime="-15" />
            </v-col>
        </v-row>
        <div style="margin-top: 100px"></div>
        <Boundary content="SUBJECT" standard="%" :startPoint="40"></Boundary>
        <TimeAndRadarChart />
    </v-app>
</template>
<script>
import TimerCalendar from '@/components/common/TimerCalendar.vue';
import Common from '@/components/timer/Common.vue';
import Boundary from '@/components/common/Boundary.vue';
import TimeAndRadarChart from '@/components/timer/TimeAndRadarChart.vue';
import DigitalClockPerDate from '@/components/timer/DigitalClockPerDate.vue';
import DigitalClockAverage from '@/components/timer/DigitalClockAverage.vue';
export default {
    name: 'MyStatistics',
    components: {
        Common,
        TimerCalendar,
        Boundary,
        TimeAndRadarChart,
        DigitalClockPerDate,
        DigitalClockAverage
    }
};
</script>
