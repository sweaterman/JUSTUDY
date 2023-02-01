<template>
    <div class="d-flex flex-column align-center">
        <h2>{{ content }}</h2>
        <div style="margin-top: 10px"></div>
        <div style="color: white; background-color: black; font-size: 30px; width: 20%" class="d-flex justify-center">00 : 15</div>
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
