<template>
    <div class="d-flex flex-column align-center">
        <h2 :style="{color: '#ffb000'}">{{ content }}</h2>
        <DigitalClock :allTime="allTime" :fontSize="80" />
    </div>
</template>
<script>
import DigitalClock from './DigitalClock.vue';
export default {
    name: 'DigitalClockPerDate',
    components: {
        DigitalClock
    },
    props: {
        content: {
            type: String,
            default: ''
        },
        allTime: {
            type: Number
        }
    }
};
</script>
