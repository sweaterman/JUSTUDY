<template>
    <div>
        <NavHeader />
        <v-divider></v-divider>
        <router-view></router-view>
        <NavFooter />
    </div>
</template>

<script>
import NavHeader from '../src/components/common/NavHeader.vue';
import NavFooter from '../src/components/common/NavFooter.vue';

export default {
    name: 'App',
    components: {
        NavHeader,
        NavFooter
    }
};
</script>
