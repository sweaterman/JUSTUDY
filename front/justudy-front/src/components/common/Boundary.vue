<template>
    <div>
        <hr />
        <div v-if="hasContent" :style="`margin-top : -33px; margin-bottom : 33px; margin-left : ${startPoint}${standard}`">
            <BasicButton :buttonLength="306" :height="66" standard="px" :content="`${content}`" />
        </div>
    </div>
</template>
<script>
import BasicButton from '@/components/common/BasicButton.vue';
export default {
    name: 'BoundaryLine',
    components: {
        BasicButton
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
