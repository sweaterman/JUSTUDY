<template>
    <div class="d-flex justify-center pt-5">
        <router-link to="/timer/study-start" style="text-decoration: none; color: black">
            <div style="width: 150px"><h2>개인 랭킹</h2></div>
        </router-link>
        <router-link to="/timer/my-statistics" style="text-decoration: none; color: black">
            <div style="width: 150px"><h2>스터디 랭킹</h2></div>
        </router-link>
    </div>
</template>
<script>
export default {
    name: 'CommonView'
};
</script>
<style></style>
