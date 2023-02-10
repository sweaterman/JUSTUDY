<template>
    <v-app style="padding-left: 10%; padding-right: 10%">
        <Common />
        <Follow buttonContent="팔로잉" />
        <Follower buttonContent="팔로우" />
    </v-app>
</template>
<script>
import Common from '@/components/timer/Common.vue';
import Follow from '@/components/common/Follow.vue';
import Follower from '@/components/common/Follower.vue';

export default {
    name: 'FriendStatistics',
    components: {
        Common,
        Follow,
        Follower
    }
};
</script>
