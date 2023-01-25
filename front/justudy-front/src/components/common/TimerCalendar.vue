<template>
    <div>
        <h1>{{ today }}</h1>
        <h1>{{ firstDayOfWeek }}</h1>
        <v-container id="container">
            <v-row id="thead">
                <v-col style="color: red">일</v-col>
                <v-col>월</v-col>
                <v-col>화</v-col>
                <v-col>수</v-col>
                <v-col>목</v-col>
                <v-col>금</v-col>
                <v-col style="color: blue">토</v-col>
            </v-row>
            <v-row class="d-flex justify-center">
                <v-col v-for="item in firstDayOfWeek" v-bind:key="item" id="td">
                    <div></div>
                    <div style="font-size: 30px" class="d-flex justify-center"></div>
                </v-col>
                <v-col v-for="item in 7 - firstDayOfWeek" v-bind:key="item" id="td">
                    <div>1</div>
                    <div style="font-size: 30px" class="d-flex justify-center">55:55:03</div>
                </v-col>
            </v-row>
            <v-row v-for="item in 4" v-bind:key="item" class="d-flex justify-center">
                <v-col v-for="colItem in 7" v-bind:key="colItem" id="td">
                    <div v-if="item * 7 + colItem - firstDayOfWeek <= 30">1</div>
                    <div style="font-size: 30px" class="d-flex justify-center" v-if="item * 7 + colItem - firstDayOfWeek <= 30">55:55:03</div>
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>
<script>
export default {
    name: 'TimerCalendar',
    data() {
        return {
            today: '',
            firstDayOfWeek: '',
            WEEKDAY: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
        };
    },
    created() {
        let today = new Date();
        let year = today.getFullYear();
        let month = today.getMonth();
        let date = today.getDate();

        this.today = year + '-' + month + '-' + date;

        this.today = today;
        this.firstDayOfWeek = this.WEEKDAY.indexOf(new Date(year, month - 1, 1).toString().slice(0, 3));
    }
};
</script>
<style>
#container {
    width: 100%;
    border: 0.5px solid gray;
}

#td {
    border: 0.5px solid gray;

    height: 120px;
}
#thead {
    background-color: #dddddd;
    font-weight: bold;
}
</style>
