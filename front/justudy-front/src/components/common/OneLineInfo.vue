<template>
    <v-row>
        <v-col cols="12" md="4">{{ category }}</v-col>
        <v-col cols="12" md="8" justify="center" align="center"
            ><h3>{{ detail }}</h3></v-col
        >
    </v-row>
</template>
<script>
export default {
    name: 'BoardList',
    props: {
        category: {
            type: String
        },
        detail: {
            type: String
        }
    }
};
</script>
