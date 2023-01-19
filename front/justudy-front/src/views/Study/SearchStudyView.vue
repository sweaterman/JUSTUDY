<template>
    <v-app>
        <PromotionStudy></PromotionStudy>

        <!-- 상위 카테고리 tabs -->
        <v-row>
            <v-col>
                <v-tabs v-model="tab" align-with-title>
                    <v-tabs-slider color="yellow"></v-tabs-slider>
                    <v-tab v-for="top in categories" :key="top.name">
                        {{ top.name }}
                    </v-tab>
                </v-tabs>
            </v-col>
        </v-row>

        <!-- 하위 카테고리 buttons -->
        <v-row>
            <v-col>
                <v-btn-toggle v-mode="button" multiple>
                    <v-btn rounded v-for="bot in categories[Number(tab)].bottom" :key="bot">
                        {{ bot }}
                    </v-btn>
                </v-btn-toggle>
            </v-col>
        </v-row>

        <!-- 선택한 카테고리 띄우기 chips -->
        <v-row>
            <v-col>
                <v-chip-group v-model="chip" multiple active-class="primary--text">
                    <v-chip v-for="choice in categories[Number(tab)].bottom" :key="choice">
                        {{ bot }}
                    </v-chip>
                </v-chip-group>
            </v-col>
        </v-row>
    </v-app>
</template>

<script>
// import {mapState} from 'vuex';
import PromotionStudy from '@/components/study/PromotionStudy.vue';

export default {
    name: 'SearchStudyView',
    components: {PromotionStudy},
    data() {
        return {
            tab: null,
            button: null,
            categories: [
                {
                    name: '프론트엔드',
                    bottom: ['React', 'vue', 'angular']
                },
                {
                    name: '백엔드',
                    bottom: ['spring', 'node.js', 'django', 'php']
                }
            ]
        };
    }
};
</script>
