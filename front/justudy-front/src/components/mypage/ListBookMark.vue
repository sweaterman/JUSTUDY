<template>
    <v-container>
        <div v-for="item in 10" v-bind:key="item">
            <v-row>
                <v-col cols="2"><b>백엔드</b></v-col>
                <v-col cols="7"><b>저 오늘 아파서 결석입니다</b></v-col>
                <v-col cols="1">왕싸피</v-col>
                <v-col cols="2" class="d-flex justify-end">23/01/05</v-col>
            </v-row>
            <hr />
        </div>
    </v-container>
</template>
<script>
export default {
    name: 'ListVue'
};
</script>
