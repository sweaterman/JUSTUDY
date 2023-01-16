<template>
    <div>
        <YellowButton :buttonLength="200" :height="70" :fontSize="36" content="추천 스터디" standard="px"/>
        
    <v-row class="mt-3">
        <div  class="d-flex mr-1" v-for="item in 3" v-bind:key="item">
            <div >
            <img src="../../assets/jjanggu.jpg" width="200px" height ="200px"/>
            <div class="d-flex">
                <div>
                    <h3>#알고리즘 # 초보</h3>
            <h3>월~금 5시</h3>
                </div>
                <div>
                    <img src="../../assets/redHeart.png"/>
                </div>
            </div>
            
            
            </div>
        </div>
       
       
    </v-row>
    </div>
   
</template>
<script>
    import YellowButton from './YellowButton.vue';
    export default {
        name : "RecommandStudy"
        ,components : {
            YellowButton
        }
    }
</script>