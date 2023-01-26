<template>
    <v-app>
        <div class="d-flex align-center">
            <!-- 좀 수정예정 -->
            <img src="@/assets/arrow.png" style="width: 20px; height: 20px; transform: rotate(0.5turn)" v-on:click="monthBefore()" />
            <h1>{{ year }}</h1>
            <h1>{{ month + 1 }}</h1>
            <img src="@/assets/arrow.png" style="width: 20px; height: 20px" v-on:click="monthAfter()" />
        </div>
        <h1>{{ firstDayOfWeek }}</h1>
        <v-container id="container">
            <v-row id="thead">
                <v-col style="color: red" class="d-flex justify-center">일</v-col>
                <v-col class="d-flex justify-center">월</v-col>
                <v-col class="d-flex justify-center">화</v-col>
                <v-col class="d-flex justify-center">수</v-col>
                <v-col class="d-flex justify-center">목</v-col>
                <v-col class="d-flex justify-center">금</v-col>
                <v-col style="color: blue" class="d-flex justify-center">토</v-col>
            </v-row>

            <v-row v-for="cr in Math.ceil((firstDayOfWeek + monthDate[month]) / 7)" :key="cr" class="d-flex justify-center">
                <v-col v-for="cc in 7" v-bind:key="cc" id="td" :style="`background-color: ${cc > 4 ? '#006400' : '#008000'}`">
                    <div v-if="7 * (cr - 1) + cc - firstDayOfWeek > 0 && 7 * (cr - 1) + cc - firstDayOfWeek <= monthDate[month]">{{ 7 * (cr - 1) + cc - firstDayOfWeek }}</div>
                    <div
                        style="font-size: 25px; background-color: yellowgreen; border-radius: 25px"
                        class="d-flex justify-center"
                        v-if="7 * (cr - 1) + cc - firstDayOfWeek > 0 && 7 * (cr - 1) + cc - firstDayOfWeek <= monthDate[month]"
                    >
                        55:55:03
                    </div>
                </v-col>
            </v-row>
        </v-container>
    </v-app>
</template>
<script>
export default {
    name: 'TimerCalendar',
    data() {
        return {
            today: '',
            firstDayOfWeek: '',
            WEEKDAY: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
            monthDate: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31],
            year: 0,
            month: 0,
            date: 0
        };
    },

    methods: {
        monthBefore() {
            if (this.month == 0) {
                this.year -= 1;
                this.month = 11;
            } else {
                this.month--;
            }
            this.firstDayOfWeek = this.WEEKDAY.indexOf(new Date(this.year, this.month, 1).toString().slice(0, 3));
        },
        monthAfter() {
            if (this.month == 11) {
                this.year += 1;
                this.month = 0;
            } else {
                this.month++;
            }
            this.firstDayOfWeek = this.WEEKDAY.indexOf(new Date(this.year, this.month, 1).toString().slice(0, 3));
        }
    },
    created() {
        let today = new Date();
        let year = today.getFullYear();
        let month = today.getMonth();
        let date = today.getDate();
        this.year = year;
        this.month = month;
        this.date = date;
        this.today = year + '-' + month + '-' + date;

        this.today = today;
        this.firstDayOfWeek = this.WEEKDAY.indexOf(new Date(year, month, 1).toString().slice(0, 3));

        if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) {
            this.monthDate[1] = 29;
        }
    }
};
</script>
<style>
#container {
    width: 100%;
    border: 0.2px solid black;
}

#td {
    border: 0.2px solid black;

    height: 120px;
}
#thead {
    font-weight: bold;
}
</style>
