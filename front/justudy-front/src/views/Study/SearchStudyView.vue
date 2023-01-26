<template>
    <v-app>
        <v-container>
            <v-row>
                <!-- 여백 -->
                <v-col cols="12" md="2"></v-col>

                <!-- 본문 -->
                <v-col cols="12" md="8">
                    <!-- 상위 카테고리 tabs -->
                    <v-row style="margin-top: 10px; margin-bottom: 10px">
                        <v-col cols="12">
                            <v-tabs color="black" v-model="tab">
                                <v-tabs-slider color="yellow"></v-tabs-slider>
                                <v-tab v-for="top in top_categories" :key="top">
                                    <h1>{{ top }}</h1>
                                </v-tab>
                            </v-tabs>
                        </v-col>
                    </v-row>

                    <v-row><v-divider></v-divider></v-row>

                    <!-- 하위 카테고리 buttons -->
                    <v-row>
                        <v-col cols="12">
                            <div class="btnGroup" v-for="bot in bottom" :key="bot.name">
                                <v-btn x-large v-if="top_categories[tab] == bot.top" @click="doSearch(bot.name)">
                                    <v-avatar size="50"><img src="@/assets/icon_70x70.png" alt="stackIcon" /></v-avatar>
                                    {{ bot.name }}
                                </v-btn>
                            </div>
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col cols="12">
                            {{ choice }}
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col cols="12">
                            <PromotionStudy></PromotionStudy>
                        </v-col>
                    </v-row>
                </v-col>

                <v-col cols="12" md="2"></v-col>
            </v-row>
        </v-container>
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
            choice: [],
            top_categories: ['프론트엔드', '백엔드', '모바일'],
            bottom: [
                {
                    name: 'React',
                    top: '프론트엔드'
                },
                {
                    name: 'Vue.js',
                    top: '프론트엔드'
                },
                {
                    name: 'Angular',
                    top: '프론트엔드'
                },
                {
                    name: 'Spring',
                    top: '백엔드'
                },
                {
                    name: 'node.js',
                    top: '백엔드'
                },
                {
                    name: 'Django',
                    top: '백엔드'
                },
                {
                    name: 'php',
                    top: '백엔드'
                },
                {
                    name: 'Flutter',
                    top: '모바일'
                },
                {
                    name: 'Kotiln',
                    top: '모바일'
                },
                {
                    name: 'ReactNative',
                    top: '모바일'
                }
            ]
        };
    },
    methods: {
        doSearch(bc) {
            this.choice = this.choice.concat(bc);
            this.choice = this.choice.filter((item, pos) => this.choice.indexOf(item) === pos);
        }
    }
};
</script>

<style scoped>
.btnGroup {
    display: inline-flex;
}
</style>
