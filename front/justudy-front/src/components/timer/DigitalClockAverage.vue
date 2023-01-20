<template>
    <div>
        <h2>{{ content }}</h2>
        <h2>
            남들보다 <span v-if="studyTime >= 0" style="color: blue">{{ studyTime }} 더</span><span v-else style="color: red">{{ -studyTime }} 덜</span> 공부하셨네요
        </h2>
    </div>
</template>
<script>
export default {
    name: 'DigitalClockAverage',
    props: {
        content: {
            type: String,
            default: ''
        },
        studyTime: {
            type: Number
        }
    }
};
</script>
