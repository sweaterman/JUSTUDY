<template>
    <div>
        <hr />
        <div v-if="hasContent" :style="`margin-top : -33px; margin-bottom : 33px; margin-left : ${startPoint}${standard}`">
            <YellowButton :buttonLength="306" :height="66" standard="px" :content="`${content}`" />
        </div>
    </div>
</template>
<script>
import YellowButton from '../mypage/YellowButton.vue';
export default {
    name: 'BoundaryLine',
    components: {
        YellowButton
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
        },
        buttonLength: {
            type: Number,
            default: 306
        }
    }
};
</script>
