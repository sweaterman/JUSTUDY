<template>
    <v-app>
        <!-- 년도와 월 -->
        <v-row :style="{marginTop: '2%'}" justify="center" align="center">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="2" justify="center" align="center">
                <v-btn icon class="ma-2" @click="monthBefore()">
                    <v-icon>mdi-chevron-left</v-icon>
                </v-btn>
            </v-col>
            <v-col cols="12" md="2" justify="center" align="center">
                <div :style="{fontWeight: 'bold', fontSize: 'x-large'}">
                    <h1>
                        <span style="color: #ffb000">{{ year }}</span>
                        <span style="color: black"> 년 </span>
                    </h1>
                </div>
            </v-col>
            <v-col cols="12" md="2" justify="center" align="center">
                <div :style="{fontWeight: 'bold', fontSize: 'x-large'}">
                    <h1>
                        <span style="color: #ffb000">{{ month + 1 }}</span>
                        <span style="color: black"> 월</span>
                    </h1>
                </div>
            </v-col>
            <v-col cols="12" md="2" justify="center" align="center">
                <v-btn icon class="ma-2" @click="monthAfter()">
                    <v-icon>mdi-chevron-right</v-icon>
                </v-btn>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>

        <!-- 날짜 -->
        <v-row :style="{marginTop: '2%', marginBottom: '2%'}" justify="center" align="center">
            <v-container id="container">
                <v-row id="thead" justify="center" align="center">
                    <v-col style="color: tomato" justify="center" align="center">일</v-col>
                    <v-col justify="center" align="center">월</v-col>
                    <v-col justify="center" align="center">화</v-col>
                    <v-col justify="center" align="center">수</v-col>
                    <v-col justify="center" align="center">목</v-col>
                    <v-col justify="center" align="center">금</v-col>
                    <v-col style="color: #00008b" justify="center" align="center">토</v-col>
                </v-row>

                <v-row v-for="cr in Math.ceil((firstDayOfWeek + monthDate[month]) / 7)" :key="cr" class="d-flex justify-center">
                    <v-col v-for="cc in 7" v-bind:key="cc" id="td">
                        <!-- </v-col>:style="`background-color: ${cc > 4 ? '#006400' : '#008000'}`"> -->
                        <!-- 날짜별 숫자 -->
                        <v-row :style="{marginTop: '1%', marginLeft: '1%'}">
                            <div v-if="7 * (cr - 1) + cc - firstDayOfWeek > 0 && 7 * (cr - 1) + cc - firstDayOfWeek <= monthDate[month]">{{ 7 * (cr - 1) + cc - firstDayOfWeek }}</div>
                        </v-row>
                        <!-- 날짜별 시간 -->
                        <v-row :style="{marginTop: '15%', marginLeft: '15%'}">
                            <div style="font-size: 100%" v-if="7 * (cr - 1) + cc - firstDayOfWeek > 0 && 7 * (cr - 1) + cc - firstDayOfWeek <= monthDate[month]">
                                {{ studyCalendar[7 * (cr - 1) + cc - firstDayOfWeek] }}
                            </div>
                        </v-row>
                    </v-col>
                </v-row>
            </v-container>
        </v-row>
    </v-app>
</template>
<script>
import CalendarData from '@/data/CalendarData';

export default {
    name: 'TimerCalendar',
    data() {
        return {
            recorded: CalendarData,

            today: '',
            firstDayOfWeek: '',
            WEEKDAY: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
            monthDate: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31],
            year: 0,
            month: 0,
            date: 0
        };
    },
    props: {
        studyCalendar: {}
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
        },

        hourMinSecond(data) {
            return (
                (parseInt(data / 3600) >= 10 ? parseInt(data / 3600) : '0' + parseInt(data / 3600)) +
                ':' +
                (parseInt((data % 3600) / 60) >= 10 ? parseInt((data % 3600) / 60) : '0' + parseInt((data % 3600) / 60)) +
                ':' +
                (data % 60 >= 10 ? data % 60 : '0' + (data % 60))
            );
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
    border: 0.2px solid rgb(230, 230, 230);
}

#td {
    border: 0.2px solid rgb(230, 230, 230);

    height: 130px;
}
</style>
