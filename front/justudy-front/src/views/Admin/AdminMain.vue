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
                    <v-col cols="12" md="3" class="boxModel">총 유저수<br/>120</v-col>
                    <v-col cols="12" md="3" class="boxModel">일일 로그인 횟수<br/>120</v-col>
                    <v-col cols="12" md="3" class="boxModel">금주 총 로그인 시간<br/>120</v-col>
                    <v-col cols="12" md="1" ></v-col>
                </v-row>
                <!-- 대시보드  -->
                <v-row>
                    <v-col cols="12" md="4">
                        <ApexChart width="500" type="bar" :options="chartOptions" :series="series"></ApexChart>
                    </v-col>
                    <v-col cols="12" md="4">

                    </v-col>
                    <v-col cols="12" md="4">


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
            chartOptions: {
                chart: {
                    id: 'vuechart-example'
                },
                xaxis: {
                    categories: [1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998]
                }
            },
            series: [{
                name: 'series-1',
                data: [30, 40, 35, 50, 49, 60, 70, 91]
            }]
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
    background-color: violet;
    height: 140px;
    border-style: solid;
    text-align: center;
    font-size: xx-large;
}

</style>>

