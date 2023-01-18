<template>
    <div>
        <!-- 여기에 사진들 배너 .... -->
        <div>
            <Profile :diameter="diameter" :standard="`${standard}`" />
        </div>
        <div class="d-flex">
            <YellowButton :buttonLength="buttonLength" :height="height" :fontSize="fontSize" :content="`${content}`" :standard="`${standard}`" />
            <h1>초보 개발자</h1>
        </div>
    </div>
</template>
<script>
import YellowButton from './YellowButton.vue';
import Profile from './Profile.vue';
export default {
    name: 'FollowFollowing',
    components: {
        YellowButton,
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
        }
    }
};
</script>
