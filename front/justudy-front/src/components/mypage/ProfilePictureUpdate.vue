<template>
    <div>
        <!-- 여기에 사진들 배너 .... -->
        <div>
            <ProfileUpdate :diameter="diameter" :standard="`${standard}`" :src="`${src}`" @sendData="sendData" />
        </div>
        <div class="d-flex">
            <h1>초보 개발자</h1>
        </div>
    </div>
</template>
<script>
// import BasicButton from '@/components/common/BasicButton.vue';
import ProfileUpdate from './ProfileUpdate.vue';
export default {
    name: 'FollowFollowing',
    components: {
        // BasicButton,
        ProfileUpdate
    },
    methods: {
        sendData(data) {
            this.$emit('sendData', data);
        }
    },
    props: {
        diameter: {
            type: Number
        },
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
        src: {}
    }
};
</script>
