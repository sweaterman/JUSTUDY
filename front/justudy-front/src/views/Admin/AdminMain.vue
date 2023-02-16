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
                        <ApexChart type="donut" :options="chartOptions3" :series="series3"></ApexChart>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12" md="4">
                        <div style="text-align: center;">신고비율</div>
                    </v-col>
                    <v-col cols="12" md="4">
                        <div style="text-align: center;">스터디공부비율</div>
                    </v-col>
                    <v-col cols="12" md="4">
                        <div style="text-align: center;">멤버공부비율</div>
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
              name: "신고횟수",
              data: [10, 41, 35, 51]
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
                    text: '신고비율',
                    align: 'left'
                },
                grid: {
                    row: {
                        colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
                        opacity: 0.5
                    },
                },
                xaxis: {
                    categories: ['멤버', '커뮤니티', '댓글', '스터디'],
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
            series3: [44, 55, 13, 33],
            chartOptions3: {
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
        this.getReportRate();
        this.getStudyRate();
        this.getMemberRate();
    },  
    
    methods: {
        getTotalMember(){
            let API_URL = `${this.port}admin/total-member`;
            
            axios.get(API_URL)
            .then((ret) => {
                    this.totalUserNum = ret.data.total;
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
                }
            )
            .catch((error) => {
                console.log(error);
            });
        },
        getReportRate(){
            let API_URL = `${this.port}admin/report-count`;
            
            axios.get(API_URL)
            .then((ret) => {
                    let tmp = [ret.data.member,ret.data.community,ret.data.comment,ret.data.study];
                    this.series1 = [ {data:tmp}];
                }
            )
            .catch((error) => {
                console.log(error);
            });
        },
        getStudyRate(){
            let API_URL = `${this.port}timer/study/all-category`;
            let category =[];
            let second =[];
            axios.get(API_URL)
            .then((ret) => {
                    // console.log(ret.data);
                    ret.data.forEach(function(item,){
                        category.push(item.category);
                        second.push(item.second);
                    })
                    this.chartOptions2 = {
                            chart: {
                                width: 380,
                                type: 'donut',
                            },
                            labels:category,
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
                    };
                    this.series2 = second;

                }
            )
            .catch((error) => {
                console.log(error);
            });
        },
        getMemberRate(){
            let API_URL = `${this.port}timer/member/all-category`;
            let category =[];
            let second =[];
            axios.get(API_URL)
            .then((ret) => {
                    // console.log(ret.data);
                    ret.data.forEach(function(item){
                        category.push(item.category);
                        second.push(item.second);
                    })
                    this.chartOptions3 = {
                            chart: {
                                width: 380,
                                type: 'donut',
                            },
                            labels:category,
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
                    };
                    this.series3 = second;

                }
            )
            .catch((error) => {
                console.log(error);
            });
        }

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

