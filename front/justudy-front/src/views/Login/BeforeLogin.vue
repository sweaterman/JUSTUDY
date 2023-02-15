<template>
    <v-app> </v-app>
</template>

<script>
export default {
    name: 'BeforeLoginView',

    mounted() {
        this.$store.dispatch('moduleLogin/stateLogout');

        window.location.href = '/login';
    }
};
</script>
