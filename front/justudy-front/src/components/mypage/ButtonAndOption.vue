<template>
    <div>
        <v-row justify="center">
            <v-col cols="8" align="center" md="4">
                <BasicButton :buttonLength="buttonLength" :height="height" :fontSize="fontSize" :content="`${content}`" :standard="`${standard}`" />
            </v-col>
            <v-col cols="16" md="8">
                <v-select :items="items" label="Outlined style" outlined :value="value" @input="change"></v-select>
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
        value: {}
    },
    data() {
        return {
            items: ['서울', '대전', '광주', '구미', '부울경']
        };
    },
    methods: {
        change($event) {
            this.$emit('input', $event);
        }
    }
};
</script>
