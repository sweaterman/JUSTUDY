<template>
    <div>
        <v-row justify="center">
            <v-col cols="2" align="center">
                <YellowButton :buttonLength="`${buttonLength}`" :height="`${height}`" :fontSize="`${fontSize}`" :content="`${content}`" :standard="`${standard}`" />
            </v-col>
            <v-col cols="5">
                <v-text-field label="8-16자 사이로 넣어주세요" outlined />
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
        }
    }
};
</script>
