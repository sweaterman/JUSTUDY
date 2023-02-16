<template>
    <v-container>
        <!-- 년도와 월 -->
        <v-row justify="center" align="center">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="2" justify="center" align="center">
                <v-btn icon class="ma-2" @click="monthBefore()">
                    <v-icon>mdi-chevron-left</v-icon>
                </v-btn>
            </v-col>
            <v-col cols="12" md="2" justify="center" align="center">
                <div :style="{fontWeight: 'bold', fontSize: 'x-large'}">
                    <h4>
                        <span style="color: #ffb000">{{ year }}</span>
                        <span style="color: black"> 년 </span>
                    </h4>
                </div>
            </v-col>
            <v-col cols="12" md="2" justify="center" align="center">
                <div :style="{fontWeight: 'bold', fontSize: 'x-large'}">
                    <h4>
                        <span style="color: #ffb000">{{ month + 1 }}</span>
                        <span style="color: black"> 월</span>
                    </h4>
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
        <v-row justify="center" align="center">
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
                    <v-col v-for="cc in 7" v-bind:key="cc" id="td" :style="`background-color : ${greenValue(cr, cc, firstDayOfWeek)}`">
                        <!-- </v-col>:style="`background-color: ${cc > 4 ? '#006400' : '#008000'}`"> -->
                        <!-- 날짜별 숫자 -->
                        <v-row :style="{fontWeight: 'bold'}">
                            <div v-if="7 * (cr - 1) + cc - firstDayOfWeek > 0 && 7 * (cr - 1) + cc - firstDayOfWeek <= monthDate[month]">{{ 7 * (cr - 1) + cc - firstDayOfWeek }}</div>
                        </v-row>
                        <!-- 날짜별 시간 -->
                        <v-row :style="{marginTop: '10%', marginLeft: '15%'}">
                            <div style="font-size: 100%" v-if="7 * (cr - 1) + cc - firstDayOfWeek > 0 && 7 * (cr - 1) + cc - firstDayOfWeek <= monthDate[month]">
                                {{ hourMinSecond(studyCalendar[7 * (cr - 1) + cc - firstDayOfWeek]) }}
                            </div>
                        </v-row>
                    </v-col>
                </v-row>
            </v-container>
        </v-row>
    </v-container>
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
            date: 0,
            studyCategory: [],
            studyCalendar: []
        };
    },
    props: {
        nickName: {
            type: String
        }
    },
    methods: {
        greenValue(cr, cc, firstDayOfWeek) {
            if (7 * (cr - 1) + cc - firstDayOfWeek <= 0 || 7 * (cr - 1) + cc - firstDayOfWeek > this.monthDate[this.month]) {
                return 'white';
            } else if (this.studyCalendar[7 * (cr - 1) + cc - firstDayOfWeek] < 1800) {
                return '#FFFFF4';
            } else if (this.studyCalendar[7 * (cr - 1) + cc - firstDayOfWeek] < 3600) {
                return '#FFF9E3';
            } else if (this.studyCalendar[7 * (cr - 1) + cc - firstDayOfWeek] < 5400) {
                return '#FAF0DD';
            } else if (this.studyCalendar[7 * (cr - 1) + cc - firstDayOfWeek] < 7200) {
                return '#FFE4B5';
            } else {
                return '#FFDAB9';
            }
        },
        // letter(cr, cc, firstDayOfWeek) {
        //     if (7 * (cr - 1) + cc - firstDayOfWeek <= 0 || 7 * (cr - 1) + cc - firstDayOfWeek > this.monthDate[this.month]) {
        //         return 'white';
        //     } else if (this.studyCalendar[7 * (cr - 1) + cc - firstDayOfWeek] == 0) {
        //         return 'black';
        //     } else if (this.studyCalendar[7 * (cr - 1) + cc - firstDayOfWeek] < 40) {
        //         return 'white';
        //     } else {
        //         return 'white';
        //     }
        // },
        async monthBefore() {
            if (this.month == 0) {
                this.year -= 1;
                this.month = 11;
            } else {
                this.month--;
            }
            this.firstDayOfWeek = this.WEEKDAY.indexOf(new Date(this.year, this.month, 1).toString().slice(0, 3));
            // 값 가져오기
            await this.$store.dispatch('moduleTimer/getStudyCalendar', {nickName: this.nickName, year: this.year, month: this.month + 1});

            let studyCalendar = new Array(32).fill(0);
            let data = this.$store.state.moduleTimer.studyCalendar;
            for (let i = 0; i < data.length; i++) {
                studyCalendar[parseInt(data[i].day)] = data[i].second;
            }

            this.studyCalendar = studyCalendar;
        },
        async monthAfter() {
            if (this.month == 11) {
                this.year += 1;
                this.month = 0;
            } else {
                this.month++;
            }
            this.firstDayOfWeek = this.WEEKDAY.indexOf(new Date(this.year, this.month, 1).toString().slice(0, 3));
            // 값 가져오기
            await this.$store.dispatch('moduleTimer/getStudyCalendar', {nickName: this.nickName, year: this.year, month: this.month + 1});

            let studyCalendar = new Array(32).fill(0);
            let data = this.$store.state.moduleTimer.studyCalendar;
            for (let i = 0; i < data.length; i++) {
                studyCalendar[parseInt(data[i].day)] = data[i].second;
            }

            this.studyCalendar = studyCalendar;
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
    async mounted() {
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

        // 값 가져오기
        await this.$store.dispatch('moduleTimer/getStudyCalendar', {nickName: this.nickName, year: this.year, month: this.month + 1});

        let studyCalendar = new Array(32).fill(0);
        let data = this.$store.state.moduleTimer.studyCalendar;
        for (let i = 0; i < data.length; i++) {
            studyCalendar[parseInt(data[i].day)] = data[i].second;
        }

        this.studyCalendar = studyCalendar;
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
    height: 70px;
}
</style>
