<template>
    <div :v-model="dialog" v-if="dialog" id="all" @click="close(true)">
        <div id="modal" @click="close(false)" style="padding: 100px 300px">
            <div class="d-flex justify-center">
                <ProfilePicture :diameter="200" :buttonLength="200" :height="70" :fontSize="32" content="LV" standard="px" :src="`${port}images/${data.imageSequence}`" />
                <!-- <v-img :src="src" style="width: 200px; height: 200px; border-radius: 100px" /> -->
                <div class="d-flex flex-column pa-12">
                    <div>
                        <div class="d-flex justify-space-around">
                            <p>닉네임</p>
                            <h1>{{ data.nickname }}</h1>
                        </div>

                        <hr />
                    </div>
                    <!-- <div v-if="loaded">
                        <div class="d-flex justify-space-around">
                            <p>선호하는 기술 스택</p>
                            <h1>{{ profile }}</h1>
                        </div>

                        <hr />
                    </div> -->
                    <div>
                        <div class="d-flex justify-space-around">
                            <p>희망진로</p>
                            <h1>{{ data.dream ? data.dream : '없음' }}</h1>
                        </div>

                        <hr />
                    </div>
                </div>
            </div>
            <div class="d-flex justify-center">
                <div>
                    <h1 style="font-size: 48px">자기소개</h1>
                    <h2>
                        {{ data.introduction ? data.introduction : '소개글이 없습니다.' }}
                    </h2>
                </div>
                <!-- {{ data }} -->
                <RadarChartProfile buttonContent="학습 진행도" :hasButton="true" :nickName="data.nickname" />
            </div>
        </div>
    </div>
</template>
<script>
import ProfilePicture from './ProfilePicture.vue';

import RadarChartProfile from '@/components/common/RadarChartProfile.vue';
import port from '@/store/port';
export default {
    name: 'ModalComponent',
    components: {
        ProfilePicture,
        RadarChartProfile
    },
    data() {
        return {
            block: false,
            profile: {},
            port: port,
            loaded: false
        };
    },
    props: {
        dialog: {
            type: Boolean
        },
        id: {
            type: Number
        },
        data: {},
        src: {}
    },
    methods: {
        close(e) {
            // console.log(e);
            if (this.block) {
                this.block = false;
            } else {
                this.$emit('closeModal', e);
                if (!e) {
                    this.block = true;
                }
            }
        }
    },
    async mounted() {
        this.loaded = false;

        await this.$store.dispatch('moduleMyPage/getProfile', {id: this.id});

        this.profile = this.$store.state.moduleMyPage.profile;
        console.log('dddd' + this.profile);
        this.loaded = true;
    }
};
</script>
<style>
#all {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;

    padding-top: 6%;
    padding-left: 10%;
    padding-right: 10%;
    z-index: 1;
    background-color: rgba(0, 0, 0, 0.5);
    transition: 5s;
}

#modal {
    top: 10%;
    left: 10%;
    right: 10%;
    border-radius: 30px;
    background-color: white;
    z-index: 2;
}
</style>
