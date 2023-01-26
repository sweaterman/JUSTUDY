<template>
    <v-app style="padding: 10%">
        <v-row style="height: 70%">
            <v-col cols="12" md="4">
                <!-- 임시로 해놓음 -->
                <ProfilePictureUpdate :diameter="300" :buttonLength="200" :height="70" :fontSize="32" content="LV" standard="px" :src="require('../../assets/jjanggu.jpg')" />
            </v-col>
            <v-col cols="12" md="8">
                <ButtonAndDefault :button-length="200" :height="70" :fontSize="20" content="이름" standard="px" text="김싸피" />
                <ButtonAndDefault :button-length="300" :height="70" :fontSize="20" content="SSAFY 학번" standard="px" text="0812345" />
                <ButtonAndDefault :button-length="200" :height="70" :fontSize="20" content="ID" standard="px" text="kimssafy1234" />
                <ButtonAndOption :button-length="200" :height="70" :fontSize="20" content="지역" standard="px" />
                <ButtonAndInput :button-length="200" :height="70" :fontSize="20" content="닉네임" standard="px" />
                <ButtonAndInput :button-length="200" :height="70" :fontSize="20" content="비밀번호" standard="px" placeholder="8~16자를 입력하세요" />
                <ButtonAndInput :button-length="300" :height="70" :fontSize="20" content="비밀번호 재확인" standard="px" placeholder="8~16자를 입력하세요" />
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
                <ButtonAndInput :button-length="300" :height="70" :fontSize="20" content="선호하는 기술 스택" standard="px" />
                <ButtonAndInput :button-length="200" :height="70" :fontSize="20" content="희망 진로" standard="px" />
                <ButtonAndInput :button-length="200" :height="70" :fontSize="20" content="자기소개" standard="px" />
            </v-col>
        </v-row>
    </v-app>
</template>
<script>
import ButtonAndInput from '@/components/mypage/ButtonAndInput.vue';
import ButtonAndDefault from '@/components/mypage/ButtonAndDefault.vue';
import ButtonAndOption from '@/components/mypage/ButtonAndOption.vue';
import ProfilePictureUpdate from '@/components/mypage/ProfilePictureUpdate.vue';
export default {
    name: 'MyPageUpdateView',
    components: {
        ButtonAndInput,
        ButtonAndDefault,
        ButtonAndOption,
        ProfilePictureUpdate
    }
};
</script>
