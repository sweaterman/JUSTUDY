<template>
    <div>
        <v-row justify="center">
            <v-col cols="8" align="center" md="4">
                <YellowButton :buttonLength="`${buttonLength}`" :height="`${height}`" :fontSize="`${fontSize}`" :content="`${content}`" :standard="`${standard}`" />
            </v-col>
            <v-col cols="16" md="8">
                <v-text-field :label="`${placeholder}`" outlined />
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
        placeholder: {
            type: String,
            default: ''
        }
    }
};
</script>
