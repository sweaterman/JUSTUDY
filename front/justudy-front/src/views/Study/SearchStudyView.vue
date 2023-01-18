<template>
    <v-app>
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

        <!-- 하위 카테고리 chips -->
        <v-row>
            <v-col>
                <v-chip-group v-model="chip" multiple active-class="primary--text">
                    <v-chip v-for="bot in categories[Number(tab)].bottom" :key="bot">
                        {{ bot }}
                    </v-chip>
                </v-chip-group>
            </v-col>
        </v-row>
    </v-app>
</template>

<script>
// import {mapState} from 'vuex';

export default {
    name: 'SearchStudyView',
    data() {
        return {
            tab: null,
            chip: null,
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
