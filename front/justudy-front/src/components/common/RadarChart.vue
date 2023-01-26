<template>
    <v-row>
        <v-row>
            <v-col>
                <Radar
                    :chart-options="chartOptions"
                    :chart-data="chartData"
                    :chart-id="chartId"
                    :dataset-id-key="datasetIdKey"
                    :plugins="plugins"
                    :css-classes="cssClasses"
                    :styles="styles"
                    :width="{width}"
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
        }
    },
    data() {
        // 함수형태
        return {
            title: "Hello it's function !",
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
    created() {
        console.log(this.chartData.datasets[0].data);
        this.chartData.datasets[0].data[0] = 4;
        this.chartData.datasets[0].data[1] = 6;
        this.chartData.datasets[0].data[2] = 6;
        this.chartData.datasets[0].data[3] = 6;
        this.chartData.datasets[0].data[4] = 6;
        this.chartData.datasets[0].data[5] = 6;
    }
};
</script>
