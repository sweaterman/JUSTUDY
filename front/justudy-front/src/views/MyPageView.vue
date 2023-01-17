<template>
<v-app>
    
    <ProfilePicture :diameter ="300" :buttonLength="200" :height="70" :fontSize="32" content="LV" standard="px"/>
    <ProfileDetail/>
    <Follow buttonContent="팔로잉"/>
    <Follow buttonContent="팔로우"/>
    
    <!-- <YellowButton :buttonLength="300" :height="65" :fontSize="36" content="아카아칸" standard="px"/> -->
    <div style="height : 100px"></div>
    <RadarChart buttonContent="학습 진행도" :hasButton="true"/>
    <StudyAnalyze/>
    <RecommandStudy/>
    <Boundary content="북마크한 글" standard="px" :startPoint="100"></Boundary>
    <ListBookMark/>
    <div style="height : 100px"></div>
    <Boundary content="이번달 달력" standard="px" :startPoint="150"></Boundary>
    <Calendar/>
    <ModalComponent/>
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
import Calendar from "@/components/mypage/Calendar.vue"
import RadarChart from "@/components/mypage/RadarChart.vue"
import ModalComponent from "@/components/mypage/ModalComponent.vue"

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
        ProfilePicture,
        Calendar,
        RadarChart,
        ModalComponent
       
        
    }
}
</script>