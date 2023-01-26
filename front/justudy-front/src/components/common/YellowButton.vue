<template>
    <div :style="`background-color : #FFD200 ; width : 200; height: 70;  border-radius: 20 `" class="d-flex justify-center align-center">
        <h1 :style="`font-size  : ${fontSize};`">{{ content }}</h1>
    </div>
</template>
<script>
export default {
    name: 'YellowButton',
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
    }
};
</script>
