<template>
    <v-container>
        <v-row>
            <v-col cols="4" class="d-flex align-center"><h2>닉네임</h2></v-col>
            <v-col cols="8" class="d-flex align-center"><h1>김싸피</h1></v-col>
        </v-row>
        <hr />
        <v-row>
            <v-col cols="4" class="d-flex align-center"><h2>선호하는 기술스택</h2></v-col>
            <v-col cols="8" class="d-flex align-center"><h1>SpringBoot</h1></v-col>
        </v-row>
        <hr />
        <v-row>
            <v-col cols="4" class="d-flex align-center"><h2>희망 진로</h2></v-col>
            <v-col cols="8" class="d-flex align-center"><h1>프론트엔드 개발자</h1></v-col>
        </v-row>
        <hr />
        <v-row justify="center">
            <v-col cols="4" class="d-flex align-center"><h2>현재 상태 표시</h2></v-col>
            <v-col cols="2" class="d-flex align-center"><h1>온라인🟢</h1></v-col>
            <v-col cols="6" class="d-flex align-center"><v-select :items="items"></v-select></v-col>
        </v-row>
        <hr />
        <v-row>
            <v-col cols="4" class="d-flex align-center"><h2>스터디왕🏆</h2></v-col>
            <v-col cols="8" class="d-flex align-center"><h1>+712점</h1></v-col>
        </v-row>
        <hr />
    </v-container>
</template>
<script>
export default {
    name: 'ProfileDetail',
    data() {
        return {
            items: ['온라인🟢', '오프라인🔴']
        };
    }
};
</script>
