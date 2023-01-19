<template>
    <v-app>
        <Common />
        <Calendar />
        <div style="margin-bottom: 100px"></div>
        <Boundary content="TOTAL" standard="%" :startPoint="40"></Boundary>
        <Boundary content="AVERAGE" standard="%" :startPoint="40"></Boundary>
        <Boundary content="SUBJECT" standard="%" :startPoint="40"></Boundary>
    </v-app>
</template>
<script>
import Calendar from '@/components/common/Calendar.vue';
import Common from '@/components/timer/Common.vue';
import Boundary from '@/components/mypage/Boundary.vue';
export default {
    name: 'MyStatistics',
    components: {
        Common,
        Calendar,
        Boundary
    }
};
</script>
