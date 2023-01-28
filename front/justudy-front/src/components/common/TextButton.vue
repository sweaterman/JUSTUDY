<template>
    <div :style="`width : ${buttonLength}${standard}; height: 1;  `" class="d-flex justify-center align-center">
        <h1 :style="`font-size  : ${fontSize};`">{{ content }}</h1>
    </div>
</template>
<script>
export default {
    name: 'TextButton',
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
            default: '#ffffff'
        }
    }
};
</script>
