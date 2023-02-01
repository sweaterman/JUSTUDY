<template>
    <v-row :style="{marginTop: '5%', padding: '1%'}" justify="center" align="center">
        <v-img
            :src="require('@/assets/timercamera.jpg')"
            :style="`
          width: 1200;
          height: 1000;
        `"
        />
    </v-row>
</template>
<script>
export default {
    name: 'ProfilePicture',
    data() {
        return {};
    },
    props: {
        diameter: {
            type: Number
        },
        standard: {
            type: String
        },
        src: {}
    },
    methods: {}
};
</script>
