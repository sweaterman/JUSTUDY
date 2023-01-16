<template>
<v-app>
    
    <ProfilePicture/>
    <ProfileDetail/>
    <Follow buttonContent="팔로잉"/>
    <Follow buttonContent="팔로우"/>
    
    <!-- <YellowButton :buttonLength="300" :height="65" :fontSize="36" content="아카아칸" standard="px"/> -->
    <div style="height : 100px"></div>
    <StudyAnalyze/>
    <RecommandStudy/>
    <Boundary content="북마크한 글" standard="px" :startPoint="100"></Boundary>
    <ListBookMark/>
    <div style="height : 100px"></div>
    <Boundary content="이번달 달력" standard="px" :startPoint="150"></Boundary>
    
</v-app>

</template>
<script>
// import YellowButton from "../components/mypage/YellowButton.vue"
import Boundary from "../components/mypage/Boundary.vue"
import ListBookMark from "@/components/mypage/ListBookMark.vue"
import StudyAnalyze from "@/components/mypage/StudyAnalyze.vue"
import RecommandStudy from "@/components/mypage/RecommandStudy.vue"
import ProfileDetail from "@/components/mypage/ProfileDetail.vue"
import Follow from "@/components/mypage/Follow.vue"
import ProfilePicture from "@/components/mypage/ProfilePicture.vue"

export default {
    name : "MyPageView",
    components : {
        // YellowButton,
        Boundary,
        ListBookMark,
        StudyAnalyze,
        RecommandStudy,
        ProfileDetail,
        Follow,
        ProfilePicture
       
        
    }
}
</script>