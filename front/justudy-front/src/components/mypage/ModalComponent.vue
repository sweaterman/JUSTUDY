<template>
    <div>
        <div class="d-flex justify-center" >
            <ProfilePicture :diameter="200" :buttonLength="200" :height="70" :fontSize="32" content="LV" standard="px" />
            <div class="d-flex flex-column pa-12 ">
                <div v-for="item in 3" v-bind:key="item"  >
            <div class="d-flex justify-space-around" >
                <p>닉네임</p>
            <h1>박싸피</h1>
            </div>
           
            <hr/>
        </div>
            </div>
        
        </div>
        <div class="d-flex justify-center">
            <div >
            <h1 style="font-size : 64px">자기소개</h1>
            <h1>안녕하세요 </h1>
            <h1>만능 개발자 입니다.</h1>
            <h1>타이머 대결 환영합니다.</h1>
           </div>
        <RadarChart />
        </div>
        


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