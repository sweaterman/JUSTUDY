<template>
    <div>
        <hr />
        <div v-if="hasContent" :style="`margin-top : -20px; margin-bottom : 33px; margin-left : ${startPoint}${standard}`">
            <TextButton :button-length="10" :height="10" :standard="px" :content="`${content}`" />
            <!-- <BasicButton backgroundColor="None" :buttonLength="50" :height="70" :fontSize="20" content="회원가입" :standard="px" /> -->
            <!-- <BasicButton: backgroundColor="black" :buttonLength="150" :height="40" :standard="px" :content="`${content}`"/> -->
        </div>
    </div>
</template>
<script>
import TextButton from './TextButton.vue';

export default {
    name: 'BoundaryLine',
    components: {
        TextButton
    },
    props: {
        hasContent: {
            type: Boolean,
            default: true
        },
        content: {
            type: String,
            default: ''
        },
        standard: {
            type: String
        },
        startPoint: {
            type: Number
        }
    }
};
</script>
