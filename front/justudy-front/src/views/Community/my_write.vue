<template>
    <v-app>
        <NavHeader />
        <!-- <v-app-bar app color="red" dark>
            <v-spacer></v-spacer>
            <v-app-bar-title>
                <div align="center" :style="{fontSize: 'xx-large'}">게시판 {{ $route.params.id }} 글쓰기</div>
            </v-app-bar-title>
            <v-spacer></v-spacer>
        </v-app-bar> -->

        <!-- 카테고리 목록 "백엔드/프론트/.../기타" ~ 시작 -->
        <v-row :style="{marginTop: '1%'}">
            <!-- 여백 -->
            <v-col cols="12" md="1" />
            <!-- 카테고리 -->
            <v-col cols="12" md="10">
                <v-row>
                    <v-col cols="12" md="2" justify="center" align="center">
                        <v-btn block color="yellow" @click="movetoboard1" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large'}">Back-end</v-btn>
                    </v-col>
                    <v-col cols="12" md="2" justify="center" align="center">
                        <v-btn block color="white" @click="movetoboard2" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large'}">Front-end</v-btn>
                    </v-col>
                    <v-col cols="12" md="2" justify="center" align="center">
                        <v-btn block color="white" @click="movetoboard3" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large'}">Infra</v-btn>
                    </v-col>
                    <v-col cols="12" md="2" justify="center" align="center">
                        <v-btn block color="white" @click="movetoboard1" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large'}">CS</v-btn>
                    </v-col>
                    <v-col cols="12" md="2" justify="center" align="center">
                        <v-btn block color="white" @click="movetoboard2" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large'}">Algorithm</v-btn>
                    </v-col>
                    <v-col cols="12" md="2" justify="center" align="center">
                        <v-btn block color="white" @click="movetoboard3" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large'}">Etc.</v-btn>
                    </v-col>
                </v-row>
            </v-col>
            <!-- 여백 -->
            <v-col cols="12" md="1" />
        </v-row>

        <!-- 글쓰기 -->
        <v-row :style="{marginTop: '2%'}">
            <!-- 여백 -->
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-form ref="form" @submit.prevent="onSubmitForm">
                    <v-card>
                        <v-row>
                            <v-col cols="12" md="2"> </v-col>

                            <v-col cols="12" md="10">
                                <!-- <v-col cols="12" md="1">
    <v-btn color="cyan" @click="movetomain" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">홈으로</v-btn>
</v-col> -->

                                <!-- <v-text-field
                v-model="writer"
                dense
                outlined
                label="작성자"
                style="width: 300px; margin-left: 100px; padding-top: 20px"
                :rules="[v => !!v || '작성자는 필수입니다.']"
            ></v-text-field> -->
                                <!-- rules를 통해 작성자와 제목을 안 쓰면 제출 못하게끔 함 -->
                                <v-text-field
                                    v-model="title"
                                    dense
                                    outlined
                                    label="제목을 입력하세요"
                                    style="width: 80%; margin-right: 10%; margin-left: 10%; padding-top: 5%; margintop: 2%"
                                    :rules="[v => !!v || '제목은 필수입니다.']"
                                ></v-text-field>
                                <v-textarea v-model="text" label="내용" outlined rows="13" style="width: 80%; margin-right: 10%; margin-left: 10%; margintop: 4%"></v-textarea>
                                <!-- <v-btn width="100px" style="margin-left: 90%" @click="moveback">취소</v-btn>
            <v-btn width="100px" style="margin-left: 90%; margin-bottom: 10%" type="submit">제출</v-btn> -->

                                <!-- <v-col cols="12" md="1" /> -->
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="12" md="2"> </v-col>

                            <v-col cols="12" md="10">
                                <!-- <v-col cols="12" md="1">
    <v-btn color="cyan" @click="movetomain" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">홈으로</v-btn>
</v-col> -->

                                <!-- <v-text-field
                v-model="writer"
                dense
                outlined
                label="작성자"
                style="width: 300px; margin-left: 100px; padding-top: 20px"
                :rules="[v => !!v || '작성자는 필수입니다.']"
            ></v-text-field> -->
                                <!-- rules를 통해 작성자와 제목을 안 쓰면 제출 못하게끔 함 -->
                                <v-text-field
                                    v-model="title"
                                    dense
                                    outlined
                                    label="제목을 입력하세요"
                                    style="width: 80%; margin-right: 10%; margin-left: 10%; padding-top: 5%; margintop: 2%"
                                    :rules="[v => !!v || '제목은 필수입니다.']"
                                ></v-text-field>
                                <v-textarea v-model="text" label="내용" outlined rows="13" style="width: 80%; margin-right: 10%; margin-left: 10%; margintop: 4%"></v-textarea>
                                <!-- <v-btn width="100px" style="margin-left: 90%" @click="moveback">취소</v-btn>
            <v-btn width="100px" style="margin-left: 90%; margin-bottom: 10%" type="submit">제출</v-btn> -->

                                <!-- <v-col cols="12" md="1" /> -->
                            </v-col>
                        </v-row>
                    </v-card>
                </v-form>
            </v-col>
            <!-- 여백 -->
            <v-col cols="12" md="2" />
        </v-row>

        <!-- 수정 중입니다 -->
        <v-row>
            <v-col cols="12" md="1">
                <v-btn color="cyan" @click="movetomain" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">홈으로</v-btn>
            </v-col>
            <v-col cols="12" md="9"></v-col>
            <v-col cols="12" md="1">
                <v-btn @click="moveback" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">취소</v-btn>
            </v-col>
            <v-col cols="12" md="1">
                <v-btn type="submit" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">등록</v-btn>
            </v-col>

            <!-- <v-btn width="100px" style="margin-left: 80%" @click="moveback">취소</v-btn>
                    <v-btn width="100px" style="margin-left: 90%" type="submit">제출</v-btn> -->
        </v-row>
        <NavFooter />
    </v-app>
</template>

<script>
    import axios from 'axios';
    import NavFooter from '../../components/common/NavFooter.vue';
    import NavHeader from '../../components/common/NavHeader.vue';

    export default {
        components: {NavHeader, NavFooter},
        data() {
            return {
                writer: '',
                title: '',
                text: ''
            };
        },
        methods: {
            onSubmitForm() {
                if (this.$refs.form.validate()) {
                    // 위에 써준 rules를 만족하면 실행
                    axios({
                        url: 'http://127.0.0.1:52273/content/write/',
                        method: 'POST',
                        data: {
                            boardnum: this.$route.params.id,
                            writer: this.writer,
                            title: this.title,
                            text: this.text
                        }
                    })
                        .then(res => {
                            alert(res.data.message);
                            window.history.back();
                        })
                        .catch(err => {
                            alert(err);
                        });
                }
            },
            moveback() {
                window.history.back();
            },
            movetomain() {
                window.location.href = '/community';
            }
        }
    };
</script>
