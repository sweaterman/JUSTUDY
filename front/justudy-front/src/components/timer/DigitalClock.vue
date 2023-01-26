<template>
    <div :style="`font-family: 'Digital Dismay'; font-size : ${fontSize}px`">
        {{ parseInt(allTime / 3600) >= 10 ? parseInt(allTime / 3600) : '0' + parseInt(allTime / 3600) }}:{{
            parseInt((allTime % 3600) / 60) >= 10 ? parseInt((allTime % 3600) / 60) : '0' + parseInt((allTime % 3600) / 60)
        }}:{{ allTime % 60 >= 10 ? allTime % 60 : '0' + (allTime % 60) }}
    </div>
    <!-- 디지털클락 -->
</template>
<script>
export default {
    name: 'DigitalClock',

    props: {
        allTime: {
            type: Number
        },
        fontSize: {
            type: Number
        }
    }
};
</script>
<style>
@font-face {
    font-family: 'Digital Dismay';

    src: url('Digital Dismay.otf');

    font-weight: normal;

    font-style: normal;
}
</style>
