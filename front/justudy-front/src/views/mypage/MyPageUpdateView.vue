<template>
    <v-app style="padding: 10%">
        <v-row style="height: 70%">
            <v-col cols="12" md="4">
                <!-- 임시로 해놓음 -->
                {{ user }}
                <ProfilePictureUpdate :diameter="300" :buttonLength="200" :height="70" :fontSize="32" content="LV" standard="px" :src="`${port}images/${user.imageSequence}`" />
            </v-col>
            <!-- v-model 사용해서 user 정보랑 연결 -->
            <v-col cols="12" md="8">
                <ButtonAndDefault :button-length="200" :height="70" :fontSize="20" content="이름" standard="px" :text="`${user.username}`" />
                <ButtonAndDefault :button-length="300" :height="70" :fontSize="20" content="SSAFY 학번" standard="px" :text="`${user.ssafyId}`" />
                <ButtonAndDefault :button-length="200" :height="70" :fontSize="20" content="ID" standard="px" :text="`${user.userId}`" />
                <ButtonAndOption :button-length="200" :height="70" :fontSize="20" content="지역" standard="px" v-model="user.region" />
                <ButtonAndInput :button-length="200" :height="70" :fontSize="20" content="닉네임" standard="px" v-model="user.nickname" />
                <ButtonAndInput :button-length="200" :height="70" :fontSize="20" content="비밀번호" standard="px" placeholder="8~16자를 입력하세요" v-model="user.password" />
                <ButtonAndInput :button-length="300" :height="70" :fontSize="20" content="비밀번호 재확인" standard="px" placeholder="8~16자를 입력하세요" v-model="user.rePassword" />
                <ButtonAndInput :button-length="200" :height="70" :fontSize="20" content="전화번호" standard="px" />
                <ButtonAndInput :button-length="200" :height="70" :fontSize="20" content="E-mail" standard="px" />
            </v-col>
        </v-row>
        <v-row>
            <v-col>
                <hr style="background: #ffd200; height: 1px; border: 0" />
            </v-col>
        </v-row>
        <v-row>
            <v-col>
                <!-- v-model 사용해서 user 정보랑 연결 -->
                <ButtonAndInput :button-length="300" :height="70" :fontSize="20" content="선호하는 기술 스택" standard="px" />
                <ButtonAndInput :button-length="200" :height="70" :fontSize="20" content="희망 진로" standard="px" />
                <ButtonAndInput :button-length="200" :height="70" :fontSize="20" content="자기소개" standard="px" v-model="user.intro" />
            </v-col>
        </v-row>
        <v-row>
            <v-col>
                <BasicButton :buttonLength="300" :height="50" :fontSize="20" content="수정" standard="px" @click="update()" />
            </v-col>
        </v-row>
    </v-app>
</template>
<script>
import ButtonAndInput from '@/components/mypage/ButtonAndInput.vue';
import ButtonAndDefault from '@/components/mypage/ButtonAndDefault.vue';
import ButtonAndOption from '@/components/mypage/ButtonAndOption.vue';
import ProfilePictureUpdate from '@/components/mypage/ProfilePictureUpdate.vue';
import BasicButton from '@/components/common/BasicButton.vue';
import port from '@/store/port';
export default {
    name: 'MyPageUpdateView',
    data() {
        return {
            port: port,
            // user 데이터
            // get으로 가져온 것 user 에 저장
            // input 창에 칠 때마다 v-model 이용해 값 업데이트 (고쳐야 함)
            // user에 값을 넣어서 (file 포함) 보냄
            user: {}
        };
    },
    methods: {
        update() {
            // 일괄 수정 API
            // this.$store.dispatch("user/updateUser",{ user : this.user})
        }
    },
    components: {
        ButtonAndInput,
        ButtonAndDefault,
        ButtonAndOption,
        ProfilePictureUpdate,
        BasicButton
    },
    created() {
        //  회원정보와 관련된 것 get으로 가져와서 default 에 넣어놓기
        this.$store.dispatch('moduleMyPage/getModifyUser');
        this.user = this.$store.state.moduleMyPage.modifyUser;
    }
};
</script>
