<template>
    <div>
        <ProfilePicture :diameter="200" :buttonLength="200" :height="70" :fontSize="32" content="LV" standard="px"/>
        
        <RadarChart />


    </div>

</template>
<script>
import ProfilePicture from './ProfilePicture.vue';
import RadarChart from './RadarChart.vue';
    export default {
        name : "ModalComponent",
        components : {
            ProfilePicture,
            RadarChart
        }
    }
</script>