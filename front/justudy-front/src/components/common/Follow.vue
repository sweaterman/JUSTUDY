<template>
    <div>
        <BasicButton :buttonLength="150" :height="60" :fontSize="36" :content="`${buttonContent}`" standard="px" />

        <!-- 여기에 사진들 배너 .... -->
        <div class="d-flex align-center" style="width: 90%; margin-left: 5%; margin-right: 5%; overflow: hidden">
            <div :style="`margin-left : ${bannerPosition}px ; transition: 0.5s`"></div>

            <div v-for="item in follow" v-bind:key="item" style="margin: 1%; transition: 0.5s">
                <ProfileFollow :diameter="200" standard="px" @dialogChangeFromChild="dialogChange()" :src="`${port}images/${item.imageSequence}`" :data="item" />

                {{ item.nickname }}

                <!-- <div v-for="item in 50" v-bind:key="item" style="margin: 1%; transition: 0.5s">
                <Profile :diameter="200" standard="px" @dialogChangeFromChild="dialogChange()" :src="require('@/assets/juniorClass.png')" />
                이싸피 -->

                <img src="../../assets/redHeart.png" />
            </div>
        </div>
        <div class="d-flex justify-space-between align-center" style="width: 100%; margin-top: -200px; margin-bottom: 200px">
            <img src="@/assets/arrow.png" style="width: 30px; height: 30px; transform: rotate(0.5turn)" @click="toLeft()" />
            <img src="@/assets/arrow.png" style="width: 30px; height: 30px" @click="toRight()" />
        </div>
    </div>
</template>
<script>
import BasicButton from '@/components/common/BasicButton.vue';
import ProfileFollow from '@/components/common/ProfileFollow.vue';
import port from '@/store/port';
export default {
    name: 'FollowFollowing',
    components: {
        BasicButton,
        ProfileFollow
    },
    data() {
        return {
            bannerPosition: 0,
            port: port,
            follow: []
        };
    },
    props: {
        buttonContent: {
            type: String
        }
    },
    methods: {
        dialogChange() {
            this.$emit('dialogChangeFromChild');
        },
        toLeft() {
            // if 문 써서 조절 하면 됨
            this.bannerPosition = this.bannerPosition + 200;
        },
        toRight() {
            this.bannerPosition = this.bannerPosition - 200;
        }
    },
    async created() {
        await this.$store.dispatch('moduleTimer/getFollow');
        this.follow = this.$store.state.moduleTimer.follow;
    }
};
</script>
