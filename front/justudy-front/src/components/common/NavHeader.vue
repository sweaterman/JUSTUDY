<!-- <template>
    <header :style="{marginTop: '5%', marginBottom: '5%'}">
        <h1>
            <a href="#" class="logo"><img alt="Vue logo" src="../../assets/logo.jpg" width="80" /></a>
        </h1>
        <div class="menuWrap">
            <ul class="menu">
                <li><a href="javascript:;">메뉴1</a></li>
                <li><a href="javascript:;">메뉴2</a></li>
                <li><a href="javascript:;">메뉴3</a></li>
                <li><a href="javascript:;">메뉴4</a></li>
            </ul>
        </div>
    </header>
</template> -->
<template>
    <v-row :style="{marginTop: '5%'}">
        <v-col cols="12" md="1" />

        <v-col cols="12" md="1" justify="center" align="center">
            <a href="#" class="logo"><img alt="Vue logo" width="80" height="80" src="../../assets/logo.jpg" /></a>
        </v-col>

        <v-col cols="12" md="1" justify="center" align="center"> 스터디 </v-col>
        <v-col @click="movetocommunity" cols="12" md="1" justify="center" align="center"> 커뮤니티 </v-col>
        <v-col cols="12" md="1" justify="center" align="center"> 타이머 </v-col>
        <v-col cols="12" md="1" justify="center" align="center"> 랭킹 </v-col>

        <v-col cols="12" md="2" />

        <v-col cols="12" md="1" justify="center" align="center"> 마이스터디 </v-col>
        <v-col cols="12" md="1" justify="center" align="center"> 마이페이지 </v-col>
        <v-col @click="movetosignin" cols="12" md="1" justify="center" align="center"> 로그아웃 </v-col>

        <v-col cols="12" md="1" />
    </v-row>
</template>

<script>
// import TextButton from '../../components/common/TextButton.vue';

export default {
    name: 'NavHeaderBar',
    components: {},
    methods: {
        movetocommunity() {
            window.location.href = ' /community';
        },
        movetosignin() {
            window.location.href = ' /signin';
        }
    }
};
</script>

<style scoped>
header {
    width: 100%;
    text-align: center;
    position: relative;
    height: 120px;
    border-bottom: 1px solid #35495e;
}
header h1 {
    position: absolute;
    top: 0;
    left: 100px;
}
header ul.menu:after {
    display: block;
    clear: both;
    content: '';
}
header ul.menu {
    position: absolute;
    top: 20px;
    right: 50px;
}
header ul.menu li {
    float: left;
    padding: 10px 20px;
    list-style: none;
}

a {
    text-decoration: none;
    color: #333;
}
</style>
