<template>
    <v-app style="padding-left: 10%; padding-right: 10%">
        <Common />
        <TimerCalendar />
        <div style="margin-bottom: 100px"></div>
        <Boundary content="TOTAL" standard="%" :startPoint="40"></Boundary>
        <DigitalClockPerDate content="THIS WEEK" />
        <DigitalClockPerDate content="THIS MONTH" />
        <Boundary content="AVERAGE" standard="%" :startPoint="40"></Boundary>
        <DigitalClockAverage content="THIS WEEK" :studyTime="15" />
        <DigitalClockAverage content="THIS MONTH" :studyTime="-15" />
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
