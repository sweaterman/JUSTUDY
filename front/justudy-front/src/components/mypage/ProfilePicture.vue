<template>
    <Profile :diameter="diameter" :standard="`${standard}`" :src="`${src}`" />
</template>
<script>
import Profile from './Profile.vue';
export default {
    name: 'FollowFollowing',
    components: {
        Profile
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
