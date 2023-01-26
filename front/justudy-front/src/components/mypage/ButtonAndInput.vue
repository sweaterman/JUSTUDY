<template>
    <div>
        <v-row justify="center">
            <v-col cols="8" align="center" md="4">
                <BasicButton :buttonLength="buttonLength" :height="height" :fontSize="fontSize" :content="`${content}`" :standard="`${standard}`" />
            </v-col>
            <v-col cols="16" md="8">
                <v-text-field :label="`${placeholder}`" outlined :value="value" @input="myChange" />
            </v-col>
        </v-row>
    </div>
</template>
<script>
import BasicButton from '@/components/common/BasicButton.vue';
export default {
    name: 'ButtonAndInput',

    components: {
        BasicButton
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
        },
        value: {}
    },
    methods: {
        myChange($event) {
            this.$emit('input', $event);
        }
    }
};
</script>
