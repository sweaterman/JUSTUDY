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
                    <v-col cols="12" md="3" class="boxModel">금주 커뮤니티 생성 수<br/>{{ weekCommunityNum }}</v-col>
                    <v-col cols="12" md="3" class="boxModel">금주 회원가입 수 <br/>{{ weeklyLoginTime }}</v-col>
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
import axios from 'axios';
import port from '@/store/port';
export default {
    name: 'AdminMain',
    components: {TabIcon},

    data() {
        return {
            port:port,
            totalUserNum:120,
            weekCommunityNum:10,
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

    created(){
        this.getTotalMember();
        this.getWeekcommunity();
        this.getSignup();
    },  
    
    methods: {
        getTotalMember(){
            let API_URL = `${this.port}admin/total-member`;
            
            axios.get(API_URL)
            .then((ret) => {
                    this.totalUserNum = ret.data.total;
                    console.log(this.totalUserNum);
                }
            )
            .catch((error) => {
                console.log(error);
            });
        },
        getWeekcommunity(){
            let API_URL = `${this.port}admin/week/community`;
            
            axios.get(API_URL)
            .then((ret) => {
                    this.weekCommunityNum = ret.data;
                    console.log(this.weekCommunityNum);
                }
            )
            .catch((error) => {
                console.log(error);
            });
        },
        getSignup(){
            let API_URL = `${this.port}admin/week/signup`;
            
            axios.get(API_URL)
            .then((ret) => {
                    this.weeklyLoginTime = ret.data;
                    console.log(this.weeklyLoginTime);
                }
            )
            .catch((error) => {
                console.log(error);
            });
        },
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

