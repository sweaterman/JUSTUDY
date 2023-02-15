<template>
    <v-app>
        <NavHeader />
        <v-divider></v-divider>
        <router-view></router-view>
        <NavFooter />
    </v-app>
</template>

<script>
import NavHeader from '../src/components/common/NavHeader.vue';
import NavFooter from './components/common/NavFooter.vue';

export default {
    name: 'App',
    components: {
        NavHeader,
        NavFooter
    }
};
</script>

<style></style>
