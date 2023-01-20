<template>
    <div>
        <BasicButton :buttonLength="200" :height="70" :fontSize="36" content="학습분석" standard="px" />
        <h1><span style="color: #58a8f7">알고리즘 DP </span> 공부가 더 필요해요</h1>
        <h1><span style="color: #58a8f7">DataBase </span> 공부가 더 필요해요</h1>
    </div>
</template>
<script>
import BasicButton from '@/components/common/BasicButton.vue';
export default {
    name: 'StudyAnalyze',
    components: {
        BasicButton
    }
};
</script>
