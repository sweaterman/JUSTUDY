<template>
    <v-row>
        <v-row>
            <v-col>
                {{ category }}
                <Radar
                    :chart-options="chartOptions"
                    :chart-data="chartData"
                    :chart-id="chartId"
                    :dataset-id-key="datasetIdKey"
                    :plugins="plugins"
                    :css-classes="cssClasses"
                    :styles="styles"
                    :width="width"
                    :height="height"
                />
            </v-col>
        </v-row>
    </v-row>
</template>
<script>
import {Radar} from 'vue-chartjs';
import {Chart as ChartJS, Title, Tooltip, Legend, PointElement, LineElement, RadialLinearScale, registerables} from 'chart.js';
ChartJS.register(Title, Tooltip, Legend, PointElement, RadialLinearScale, LineElement, ...registerables);

export default {
    name: 'RadarChart',

    components: {
        Radar
    },
    props: {
        chartId: {
            type: String,
            default: 'radar-chart'
        },
        datasetIdKey: {
            type: String,
            default: 'label'
        },
        width: {
            type: Number,
            default: 7000
        },
        height: {
            type: Number,
            default: 7000
        },
        cssClasses: {
            default: '',
            type: String
        },
        styles: {
            type: Object,
            default: () => {}
        },
        plugins: {
            type: Array,
            default: () => []
        },
        onedata: {
            type: Number
        },
        hasButton: {
            type: Boolean,
            default: false
        },
        buttonContent: {
            type: String
        },
        category: {}
    },
    data() {
        // 함수형태
        return {
            title: "Hello it's function !",
            user: {},

            chartData: {
                labels: ['CS', 'Algorithm', 'Frontend', 'Backend', 'Database', 'etc'],
                datasets: [
                    {
                        label: 'value',
                        backgroundColor: 'rgba(255,125,0,0.2)',
                        borderColor: 'rgba(255,125,0,1)',
                        pointBackgroundColor: 'rgba(255,125,0,1)',
                        pointBorderColor: '#fff',
                        pointHoverBackgroundColor: '#fff',
                        pointHoverBorderColor: 'rgba(255,125,0,1)',
                        data: [0, 0, 0, 0, 0, 0]
                    }
                ]
            },
            chartOptions: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    r: {
                        suggestedMin: 0,
                        suggestedMax: 6,
                        stepSize: 2,
                        pointLabels: {
                            color: 'orange' // 라벨의 글씨 색상
                        }
                    }
                },
                plugins: {
                    legend: {
                        display: false // 최상단 라벨의 카테고리 안보이기.
                    }
                }
            }
        };
    },
    async created() {
        console.log('아아아' + this.category);
        // console.log(this.chartData.datasets[0].data);
        for (let i = 0; i < 5; i++) {
            // this.chartData.datasets[0].data[i] = this.category[i].second;
            this.chartData.datasets[0].data[i] = 300;
        }
    },
    async beforeUpdate() {
        // await this.$store.dispatch('moduleMyPage/getMyPageUser');

        // this.user = this.$store.state.moduleMyPage.user;

        console.log('아아아' + this.category);
        // console.log(this.chartData.datasets[0].data);
        for (let i = 0; i < this.category.length; i++) {
            this.chartData.datasets[0].data[i] = this.category[i].second;
            // this.chartData.datasets[0].data[i] = 300;
        }
        // this.chartData.datasets[0].data[0] = this.category[0] ? this.category[0]?.second : 0;
        // this.chartData.datasets[0].data[1] = this.category[1] ? this.category[1]?.second : 0;
        // this.chartData.datasets[0].data[2] = this.category[2] ? this.category[2]?.second : 0;
        // this.chartData.datasets[0].data[3] = this.category[3] ? this.category[3]?.second : 0;
        // this.chartData.datasets[0].data[4] = this.category[4] ? this.category[4]?.second : 0;
        // this.chartData.datasets[0].data[5] = this.category[5] ? this.category[5]?.second : 0;
    }
};
</script>
