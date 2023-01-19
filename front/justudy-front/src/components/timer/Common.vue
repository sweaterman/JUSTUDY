<template>
    <div class="d-flex justify-center pt-5">
        <router-link to="/timer/study-start" style="text-decoration: none; color: black">
            <div style="width: 150px"><h2>공부 시작</h2></div>
        </router-link>
        <router-link to="/timer/my-statistics" style="text-decoration: none; color: black">
            <div style="width: 150px"><h2>나의 통계</h2></div>
        </router-link>
        <router-link to="/timer/friend-statistics" style="text-decoration: none; color: black">
            <div style="width: 150px"><h2>친구 통계</h2></div>
        </router-link>
    </div>
</template>
<script>
export default {
    name: 'CommonView'
};
</script>
<style></style>
