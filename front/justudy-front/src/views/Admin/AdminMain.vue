<template>
    <v-app>
        <v-row :style="{marginTop: '3%'}">
            <v-col cols="12" md="1" >
                <v-row>
                    <v-col cols="12" md="3" ></v-col>
                    <v-col cols="12" md="9" >
                        <TabIcon></TabIcon>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="11">
                <!-- 목록 -->
                <v-row>
                    <v-col cols="12" md="1" ></v-col>
                    <v-col cols="12" md="3" class="boxModel">총 유저수<br/>{{ totalUserNum }}</v-col>
                    <v-col cols="12" md="3" class="boxModel">일일 로그인 횟수<br/>{{ dailyUserNum }}</v-col>
                    <v-col cols="12" md="3" class="boxModel">금주 총 로그인 시간<br/>{{ weeklyLoginTime }}</v-col>
                    <v-col cols="12" md="1" ></v-col>
                </v-row>
                <div style="height:200px"></div>
                <!-- 대시보드  -->
                <v-row>
                    <v-col cols="12" md="4">
                        <ApexChart type="line" :options="chartOptions1" :series="series1"></ApexChart>
                    </v-col>
                    <v-col cols="12" md="4">
                        <ApexChart type="donut" :options="chartOptions2" :series="series2"></ApexChart>
                    </v-col>
                    <v-col cols="12" md="4">
                        <ApexChart type="donut" :options="chartOptions2" :series="series2"></ApexChart>
                    </v-col>
                </v-row>

            </v-col> 
        
        </v-row>
    </v-app>
</template>

<script>
import TabIcon from "@/components/Admin/TabIcon.vue"
export default {
    name: 'AdminMain',
    components: {TabIcon},

    data() {
        return {
            totalUserNum:120,
            dailyUserNum:10,
            weeklyLoginTime:300,
            series1: [{
              name: "Desktops",
              data: [10, 41, 35, 51, 49, 62, 69, 91, 148]
            }],
            chartOptions1: {
                chart: {
                    height: 350,
                    type: 'line',
                    zoom: {
                        enabled: false
                    }
                },
                dataLabels: {
                    enabled: false
                },
                stroke: {
                    curve: 'straight'
                },
                title: {
                    text: '유저 활동 수',
                    align: 'left'
                },
                grid: {
                    row: {
                        colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
                        opacity: 0.5
                    },
                },
                xaxis: {
                    categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep'],
                }
            },
            series2: [44, 55, 13, 33],
            chartOptions2: {
                chart: {
                    width: 380,
                    type: 'donut',
                },
                labels:['q','w','e','r'],
                dataLabels: {
                    enabled: true
                },
                responsive: [{
                    breakpoint: 480,
                    options: {
                        chart: {
                            width: 200
                        },
                        legend: {
                            show: false
                        }
                    }
                }],
                legend: {
                    position: 'right',
                    offsetY: 0,
                    height: 230,
                }
          },
        };
    },

    
    methods: {
    }
};
</script>

<style scoped>
.boxModel {
    margin-left:10px;
    margin-left:10px;
    background-color: yellow;
    height: 140px;
    border-style: solid;
    text-align: center;
    font-size: xx-large;
}

</style>>

