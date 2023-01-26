<template>
    <v-app>
        <!-- 글읽기 제목 -->
        <v-row :style="{marginBottom: '2%'}">
            <v-col cols="12" md="4" />
            <v-col cols="12" md="4" justify="center" align="center">
                <TextButton :buttonLength="100" :height="70" :fontSize="10" :content="`게시판 글쓰기`" :standard="px" />
            </v-col>
            <v-col cols="12" md="4" />
        </v-row>

        <!-- 글쓰기 -->
        <v-row>
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-form ref="form" @submit.prevent="onSubmitForm">
                    <v-card>
                        <!-- 작성자 -->
                        <v-row>
                            <v-col cols="12" md="2" justify="center" align="center" :style="{color: 'white'}">
                                <v-btn depressed color="white" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large', marginTop: '20%', marginLeft: '15%'}">작성자</v-btn>
                            </v-col>
                            <v-col cols="12" md="10">
                                <v-text-field
                                    v-model="writer"
                                    solo
                                    readonly
                                    outlined
                                    depressed
                                    label="제목을 입력하세요"
                                    style="width: 80%; height: 20px; margin-right: 10%; margin-left: 5%; margin-top: 4%"
                                ></v-text-field>
                                <!-- <v-btn color="white" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large', marginTop: '3%', marginLeft: '10%'}"></v-btn> -->
                            </v-col>
                        </v-row>

                        <!-- 제목 -->
                        <v-row>
                            <v-col cols="12" md="2" justify="center" align="center">
                                <v-btn depressed color="white" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large', marginLeft: '15%'}">제목</v-btn>
                            </v-col>
                            <v-col cols="12" md="10">
                                <v-text-field
                                    v-model="title"
                                    outlined
                                    label="제목을 입력하세요"
                                    style="width: 80%; margin-right: 10%; margin-left: 5%"
                                    :rules="[v => !!v || '제목은 필수입니다.']"
                                ></v-text-field>
                            </v-col>
                        </v-row>

                        <!-- 내용 -->
                        <v-row>
                            <v-col cols="12" md="2">
                                <v-btn depressed color="white" :style="{height: '40px', width: '200px', fontWeight: 'bold', fontSize: 'large', marginTop: '1%', marginLeft: '15%'}">내용</v-btn>
                            </v-col>
                            <v-col cols="12" md="10">
                                <v-textarea v-model="text" label="내용" outlined rows="13" style="width: 80%; margin-right: 10%; margin-left: 5%; margintop: 4%"></v-textarea>
                            </v-col>
                        </v-row>
                    </v-card>
                </v-form>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>

        <!-- 완료/취소 버튼 -->
        <v-row :style="{marginTop: '1%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-row>
                    <v-col cols="12" md="1">
                        <v-btn @click="movetomain" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">홈으로</v-btn>
                    </v-col>
                    <v-col cols="12" md="7"></v-col>
                    <v-col cols="12" md="2" align="right">
                        <v-btn @click="moveback" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">취소</v-btn>
                    </v-col>
                    <v-col cols="12" md="2">
                        <v-btn type="submit" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">등록</v-btn>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>

<script>
import TextButton from '../../components/common/TextButton.vue';

export default {
    components: {TextButton},
    data() {
        return {
            writer: '돌숭이',
            title: '',
            text: ''
        };
    },
    methods: {
        // onSubmitForm() {
        //     if (this.$refs.form.validate()) {
        //         // 위에 써준 rules를 만족하면 실행
        //         axios({
        //             url: 'http://127.0.0.1:52273/content/write/',
        //             method: 'POST',
        //             data: {
        //                 boardnum: this.$route.params.id,
        //                 writer: this.writer,
        //                 title: this.title,
        //                 text: this.text
        //             }
        //         })
        //             .then(res => {
        //                 alert(res.data.message);
        //                 window.history.back();
        //             })
        //             .catch(err => {
        //                 alert(err);
        //             });
        //     }
        // },
        // moveback() {
        //     window.history.back();
        // },
        // movetomain() {
        //     window.location.href = '/community';
        // }
    }
};
</script>
