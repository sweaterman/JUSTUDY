<template>
    <div>
        <YellowButton :buttonLength="200" :height="70" :fontSize="36" :content="`${buttonContent}`" standard="px"/>
    <!-- 여기에 사진들 배너 .... -->
    <div class="d-flex">
        <div v-for="item in 3" v-bind:key="item" >
        
            
        <Profile diameter="100" standard="px"/>
        
           
        
       
           
        
    
       
            이싸피
       
            <img src="../../assets/redHeart.png"/>

       

    
    </div>
    </div>
    
   
    
    </div>
   
</template>
<script>
import YellowButton from './YellowButton.vue';
import Profile from './Profile.vue';
    export default {
        name : "FollowFollowing",
        components : {
            YellowButton,
            Profile 
        },
        props : {
            buttonContent :{
                type : String
            }
        }
    }
</script>