<template>
    <v-app>
        <NavHeader />
        <CommuHeader />

        <!-- 상세 글보기 -->
        <!-- 글읽기 제목 -->
        <v-row :style="{marginBottom: '2%'}">
            <v-col cols="12" md="4" />
            <v-col cols="12" md="4" justify="center" align="center">
                <TextButton :buttonLength="100" :height="70" :fontSize="10" :content="`게시판 글읽기`" :standard="px" />
            </v-col>
            <v-col cols="12" md="4" />
        </v-row>

        <v-row>
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-form ref="form" @submit.prevent="onSubmitForm">
                    <v-card>
                        <!-- 글쓴이 -->
                        <v-row>
                            <v-col cols="12" md="2" :style="{color: 'white'}">
                                <v-btn depressed color="white" :style="{height: '65px', width: '165px', fontWeight: 'bold', fontSize: 'large', marginTop: '20%'}">글쓴이</v-btn>
                            </v-col>
                            <v-col cols="12" md="10">
                                <v-text-field v-model="writer" solo readonly outlined depressed label="글쓴이" style="width: 80%; height: 20px; margin-right: 10%; margin-top: 4%"></v-text-field>
                                <!-- <v-btn color="white" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large', marginTop: '3%', marginLeft: '10%'}"></v-btn> -->
                            </v-col>
                        </v-row>

                        <!-- 제목 -->
                        <v-row>
                            <v-col cols="12" md="2">
                                <v-btn depressed color="white" :style="{height: '65px', width: '165px', fontWeight: 'bold', fontSize: 'large'}">제목</v-btn>
                            </v-col>
                            <v-col cols="12" md="10">
                                <v-text-field v-model="title" solo readonly depressed outlined label="제목" style="width: 80%; margin-right: 10%; margin-top: 0.5%"></v-text-field>
                            </v-col>
                        </v-row>

                        <!-- 작성일 -->
                        <v-row>
                            <v-col cols="12" md="2">
                                <v-btn depressed color="white" :style="{height: '65px', width: '165px', fontWeight: 'bold', fontSize: 'large'}">작성일</v-btn>
                            </v-col>
                            <v-col cols="12" md="10">
                                <v-text-field v-model="createdAt" solo readonly depressed outlined label="작성일" style="width: 80%; margin-right: 15%"></v-text-field>
                            </v-col>
                        </v-row>

                        <!-- 수정일 -->
                        <v-row>
                            <v-col cols="12" md="2">
                                <v-btn depressed color="white" :style="{height: '65px', width: '165px', fontWeight: 'bold', fontSize: 'large'}">수정일</v-btn>
                            </v-col>
                            <v-col cols="12" md="10">
                                <v-text-field v-model="updatedAt" solo readonly depressed outlined label="수정일" style="width: 80%; margin-right: 10%"></v-text-field>
                            </v-col>
                        </v-row>

                        <!-- 내용 -->
                        <v-row>
                            <v-col cols="12" md="2">
                                <v-btn depressed color="white" :style="{height: '65px', width: '165px', fontWeight: 'bold', fontSize: 'large'}">내용</v-btn>
                            </v-col>
                            <v-col cols="12" md="10">
                                <v-textarea v-model="text" solo readonly depressed outlined label="내용" rows="13" style="width: 80%; margin-right: 10%"></v-textarea>
                            </v-col>
                        </v-row>

                        <!-- 내용 -->
                        <!-- <v-row>
                            <v-col cols="12" md="2" justify="center" align="center">
                                <v-btn depressed color="white" :style="{height: '65px', width: '100px', fontWeight: 'bold', fontSize: 'large', marginLeft: '15%'}">내용</v-btn>
                            </v-col>
                            <v-col cols="12" md="10">
                                <v-textarea v-model="text" outlined rows="13" style="width: 730px; margin-left: 100px; padding-top: 10px" :disabled="editable === false"></v-textarea>
                                <v-text-field v-model="text" solo readonly depressed outlined label="내용" style="width: 80%; margin-right: 10%; margin-left: 5%; margin-top: 0.5%"></v-text-field>
                            </v-col>
                        </v-row> -->
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
                        <v-btn @click="moveback" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">뒤로가기</v-btn>
                    </v-col>
                    <v-col cols="12" md="7"></v-col>
                    <v-col cols="12" md="2" align="right">
                        <v-btn @click="editcontent" v-if="editable === false" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">수정하기</v-btn>
                        <v-btn @click="editcontentfinish" v-if="editable === true" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">수정완료</v-btn>
                    </v-col>
                    <v-col cols="12" md="2">
                        <v-btn @click="deletecontent" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">삭제</v-btn>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>

        <NavFooter />
    </v-app>
</template>

<script>
import axios from 'axios';
import NavFooter from '../../components/common/NavFooter.vue';
import NavHeader from '../../components/common/NavHeader.vue';
import CommuHeader from '../../components/Community/CommuHeader.vue';
import TextButton from '../../components/common/TextButton.vue';

export default {
    components: {NavHeader, NavFooter, CommuHeader, TextButton},

    data() {
        return {
            writer: '돌숭이', // 작성자
            title: '돌숭이의 꿀팁', // 글 제목
            createdAt: '2023/01/18', // 작성일
            updatedAt: '2023/01/20', // 최근 수정일
            text: '그런건 없습니다', // 글 내용
            editable: false // 수정가능여부 (수정 버튼누르면 true로 바뀜)
        };
    },
    mounted() {
        axios({
            url: 'http://127.0.0.1:52273/content/content/',
            method: 'POST',
            data: {
                id: this.$route.query.id
            }
        })
            .then(res => {
                this.writer = res.data.writer;
                this.title = res.data.title;
                this.createdAt = res.data.createdAt.split('T')[0];
                this.updatedAt = res.data.updatedAt.split('T')[0];
                this.text = res.data.text;
            })
            .catch(err => {
                alert(err);
            });
    },
    methods: {
        moveback() {
            window.history.back(); // window.history.back()을 통해 뒤로가기
        },
        deletecontent() {
            // 글에 들어가서 삭제버튼 눌렀을 때
            axios({
                url: 'http://127.0.0.1:52273/content/delete/',
                method: 'POST',
                data: {
                    id: this.$route.query.id
                }
            })
                .then(res => {
                    alert(res.data.message);
                    window.location.href = window.location.pathname.slice(0, -8) + '/?page=1';
                    // 삭제 후 그 게시판의 1페이지로 이동
                })
                .catch(err => {
                    alert(err);
                });
        },
        editcontent() {
            this.editable = true;
        },
        editcontentfinish() {
            // 수정완료 버튼을 눌렀을 때, 수정된 내용이 저장되야 되기 때문에 back서버와 통신 필요
            axios({
                url: 'http://127.0.0.1:52273/content/edit/',
                method: 'POST',
                data: {
                    id: this.$route.query.id,
                    text: this.text
                }
            })
                .then(res => {
                    alert(res.data.message);
                    this.editable = false;
                })
                .catch(err => {
                    alert(err);
                });
        },
        movetomain() {
            window.location.href = '/';
        }
    }
};
</script>
