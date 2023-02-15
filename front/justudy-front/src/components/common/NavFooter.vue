<template>
    <div class="footer_div">
        <v-container>
            <v-row>
                <v-col cols="0" md="2" />

                <!-- 이미지 부분  -->
                <v-col class="footer_center" cols="12" md="2">
                    <img style="width: 110px; height: 90px" src="@/assets/ssafy.png" alt="ssafy" />
                </v-col>

                <!-- 글 부분 -->
                <v-col cols="12" md="6">
                    <v-row>
                        <v-col cols="12" style="color: white"> 이용 약관 | 개인정보처리방침 </v-col>
                    </v-row>
                    <v-row dense>
                        <v-col cols="12" style="color: #bdbdbd">본 사이트의 콘텐츠는 저작권법의 보호를 받는 바 무단 전제, 복사, 배포 등을 금합니다.</v-col>
                    </v-row>
                    <v-row dense>
                        <v-col cols="12" style="color: white">Copyright © SAMSUNG All Rights Reserved.</v-col>
                    </v-row>
                </v-col>
                <v-col cols="0" md="2" />
            </v-row>
        </v-container>
    </div>
</template>

<script>
export default {
    name: 'NavFooter'
};
</script>

<style scoped>
.footer_div {
    margin-top: 50px;
    background-color: #363636;
    height: 150px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.footer_center {
    display: flex;
    align-items: center;
    justify-content: center;
}
</style>
