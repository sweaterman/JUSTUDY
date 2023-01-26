<template>
    <div
        :style="`background-color : ${backgroundColor} ; width : ${buttonLength}${standard}; height: ${height}${standard};  border-radius: ${height / 2}${standard} `"
        class="d-flex justify-center align-center"
        @click="click"
    >
        <h1 :style="`font-size  : ${fontSize};`">{{ content }}</h1>
    </div>
</template>
<script>
export default {
    name: 'BasicButton',
    props: {
        buttonLength: {
            type: Number
        },
        fontSize: {
            type: Number
        },
        height: {
            type: Number
        },
        content: {
            type: String
        },
        standard: {
            type: String
        },
        backgroundColor: {
            type: String,
            default: '#FFD200'
        }
    },
    methods: {
        click() {
            this.$emit('click');
        }
    }
};
</script>
