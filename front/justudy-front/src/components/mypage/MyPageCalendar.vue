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
                    <v-col v-for="cc in 7" v-bind:key="cc" id="td">
                        <div @mouseover="showToolTip=true" @mouseleave="showToolTip=false" >
                        <!-- </v-col>:style="`background-color: ${cc > 4 ? '#006400' : '#008000'}`"> -->
                        <!-- 날짜별 숫자 -->
                        <v-row :style="{fontWeight: 'bold'}">
                            <div v-if="7 * (cr - 1) + cc - firstDayOfWeek > 0 && 7 * (cr - 1) + cc - firstDayOfWeek <= monthDate[month]">{{ 7 * (cr - 1) + cc - firstDayOfWeek }}</div>
                        </v-row>
                        <!-- 날짜별 시간 -->
                        <v-row :style="{marginTop: '10%', marginLeft: '15%'}">
                            <div style="font-size: 100%" v-if="7 * (cr - 1) + cc - firstDayOfWeek > 0 && 7 * (cr - 1) + cc - firstDayOfWeek <= monthDate[month]">
                                <!-- <v-tooltip v-model="show" location="top center" origin="overlap" >
                                    <template v-slot:activator="{ props }">
                                    <div
                                        v-bind="props"
                                    > {{ studyArray[7 * (cr - 1) + cc - firstDayOfWeek] }}
                                    </div>
                                    </template>
                                    <span>{{ studyDetailArray[7 * (cr - 1) + cc - firstDayOfWeek] }}</span>
                                </v-tooltip> -->
                                {{ studyArray[7 * (cr - 1) + cc - firstDayOfWeek] }}
                            </div>
                            
                        </v-row>
                    </div>
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
            studyArray:[],
            studyDetailArray:[],
            show: false,
        };
    },
    components: {
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
            this.calcStartDay();
        },
        monthAfter() {
            if (this.month == 11) {
                this.year += 1;
                this.month = 0;
            } else {
                this.month++;
            }
            this.firstDayOfWeek = this.WEEKDAY.indexOf(new Date(this.year, this.month, 1).toString().slice(0, 3));
            this.calcStartDay();
        },

        hourMinSecond(data) {
            return (
                (parseInt(data / 3600) >= 10 ? parseInt(data / 3600) : '0' + parseInt(data / 3600)) +
                ':' +
                (parseInt((data % 3600) / 60) >= 10 ? parseInt((data % 3600) / 60) : '0' + parseInt((data % 3600) / 60)) +
                ':' +
                (data % 60 >= 10 ? data % 60 : '0' + (data % 60))
            );
        },
        calcStartDay(){
        //studyCalendar 값을 바탕으로 배열 생성
        // this.studyArray = new Array(40).fill('🟡18:00');
        this.studyArray = new Array(47).fill("");
        this.studyDetailArray = new Array(47).fill("");
        
        let week = {"월":1,"화":2,"수":3,"목":4,"금":5,"토":6,"일":0};
        console.log("this.studyCalendar");
        console.log(this.studyCalendar);
        let studytemp=new Array(47);
        let studyDetailtemp=new Array(47);
        for(let i=0;i<studytemp.length;++i){
            studytemp[i]= new Array();
            studyDetailtemp[i]= new Array();
        }
        //string 형태로 각각에 시작시간 넣어줌
        for(let i=0;i<this.studyCalendar.length;++i){
            let tmp= this.studyCalendar[i].frequency;
            for(let j=0;j<tmp.length;++j){
                let weekIndex = week[tmp[j].week];
                console.log( weekIndex);
                for(let k=weekIndex;k<40;k+=7){
                    studytemp[k].push("🟡" + tmp[j].startTime);
                    studyDetailtemp[k].push("\n🟡" + tmp[j].startTime + "~" + tmp[j].endTime +"\n"+ this.studyCalendar[i].name + "\n" + this.studyCalendar[i].sequence);
                    // this.studyDetailArray[k]+="\n🟡" + tmp[j].startTime + "~" + tmp[j].endTime +"\n"+ this.studyCalendar[i].name + "\n" + this.studyCalendar[i].sequence;
                }
            }
        }
        //시간순으로 정렬
        for(let i=0;i<studytemp.length;++i){
            studytemp[i].sort();
            studyDetailtemp[i].sort();
            this.studyArray[i]=studytemp[i].toString();
            this.studyArray[i] = this.studyArray[i].replace(/,/g, " ");
            this.studyDetailArray[i]=studyDetailtemp[i].toString();
            this.studyDetailArray[i] = this.studyDetailArray[i].replace(/,/g, " ");
            //3개이상일 경우 생략
            if(this.studyArray[i].length>20)this.studyArray[i]="..."
        }
        //시작일 요일별 맞추기
        this.studyArray=this.studyArray.slice(this.firstDayOfWeek==0?this.firstDayOfWeek+6:this.firstDayOfWeek-1)
        this.studyDetailArray=this.studyDetailArray.slice(this.firstDayOfWeek==0?this.firstDayOfWeek+6:this.firstDayOfWeek-1)
        console.log(this.firstDayOfWeek);
        },
        getStudyInfo(){
            
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
    },
    mounted(){
        console.log("aa")
        console.log(this.studyCalendar)
        this.calcStartDay();
    }
    
};
</script>
<style>
#container {
    width: 24%;
    border: 0.2px solid rgb(230, 230, 230);
}

#td {
    border: 0.2px solid rgb(230, 230, 230);

    height: 70px;
}
.v-btn:before {
  opacity: 0 !important;
}

.v-ripple__container {
  opacity: 0 !important;
}
</style>
