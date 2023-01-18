<template>
    <div>
        <v-row justify="center">
            <v-col cols="2" align="center">
                <YellowButton :buttonLength="`${buttonLength}`" :height="`${height}`" :fontSize="`${fontSize}`" :content="`${content}`" :standard="`${standard}`" />
            </v-col>
            <v-col v-if="content === '이름'" cols="5">
                <h1>{{ text }}</h1>
            </v-col>
            <v-col v-else cols="5">
                <h1>{{ text }}</h1>
            </v-col>
        </v-row>
    </div>
</template>
<script>
import YellowButton from './YellowButton.vue';
export default {
    name: 'ButtonAndInput',
    components: {
        YellowButton
    },
    props: {
        buttonLength: {
            type: Number
        },
        height: {
            type: Number
        },
        fontSize: {
            type: Number
        },
        content: {
            type: String
        },
        standard: {
            type: String
        },
        text: {
            type: Number
        }
    }
};
</script>
