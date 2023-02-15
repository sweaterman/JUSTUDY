<template>
    <div class="d-flex flex-column align-center">
        <h2>{{ content }}</h2>
        <div style="margin-top: 10px"></div>
        <div style="color: white; background-color: black; font-size: 30px; width: 40%" class="d-flex justify-center">
            {{ parseInt(averageTime / 3600) >= 10 ? parseInt(averageTime / 3600) : '0' + parseInt(averageTime / 3600) }}:{{
                parseInt((averageTime % 3600) / 60) >= 10 ? parseInt((averageTime % 3600) / 60) : '0' + parseInt((averageTime % 3600) / 60)
            }}:{{ averageTime % 60 >= 10 ? averageTime % 60 : '0' + (averageTime % 60) }}
        </div>
        <h2>
            남들보다
            <span v-if="studyTime >= 0" style="color: blue">
                {{ parseInt(studyTime / 3600) >= 10 ? parseInt(studyTime / 3600) : '0' + parseInt(studyTime / 3600) }}:{{
                    parseInt((studyTime % 3600) / 60) >= 10 ? parseInt((studyTime % 3600) / 60) : '0' + parseInt((studyTime % 3600) / 60)
                }}:{{ studyTime % 60 >= 10 ? studyTime % 60 : '0' + (studyTime % 60) }} 더</span
            ><span v-else style="color: red">
                {{ parseInt(-studyTime / 3600) >= 10 ? parseInt(-studyTime / 3600) : '0' + parseInt(-studyTime / 3600) }}:{{
                    parseInt((-studyTime % 3600) / 60) >= 10 ? parseInt((-studyTime % 3600) / 60) : '0' + parseInt((-studyTime % 3600) / 60)
                }}:{{ -studyTime % 60 >= 10 ? -studyTime % 60 : '0' + (-studyTime % 60) }} 덜</span
            >
            공부하셨네요
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
        averageTime: {
            type: Number
        },
        studyTime: {
            type: Number
        }
    }
};
</script>
