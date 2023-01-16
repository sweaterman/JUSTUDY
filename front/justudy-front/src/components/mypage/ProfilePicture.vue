<template>
    <div>
       
    <!-- 여기에 사진들 배너 .... -->
    <div >
       
        
            
        <Profile diameter="300" standard="px"/>
        
           
        
       
           
        
    
       
           

       

    
    
   
    </div>
    <div class="d-flex">

        <YellowButton :buttonLength="200" :height="70" :fontSize="36" content="LV" standard="px"/>
        <h1>초보 개발자</h1>
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
        
    }
</script>