<template>
    <v-app>
        <!-- 상세 글보기 -->
        <v-row>
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-form ref="form" @submit.prevent="onSubmitForm">
                    <!-- 제목 -->
                    <v-row>
                        <!-- <v-btn depressed color="white" :style="{height: '65px', width: '165px', fontWeight: 'bold', fontSize: 'large', marginTop: '20%'}">글쓴이</v-btn> -->
                        <div style="width: 300px; margin-left: 9%; margin-top: 1%">
                            <h1>{{ title }}</h1>
                        </div>
                        <!-- <v-text-field v-model="title" solo readonly depressed outlined label="제목" style="width: 80%; margin-right: 10%; margin-top: 0.5%"></v-text-field> -->
                    </v-row>
                    <v-row>
                        <hr style="margin-left: 9%; margin-top: 1%; width: 100%" />
                    </v-row>

                    <!-- 글쓴이 -->
                    <v-row>
                        <div style="width: 300px; margin-left: 9%; margin-top: 1.7%; margin-bottom: 0.5%">
                            <h3>{{ writer }}</h3>
                        </div>
                        <!-- <v-text-field v-model="writer" solo readonly outlined depressed label="글쓴이" style="width: 80%; height: 20px; margin-right: 10%; margin-top: 4%"></v-text-field> -->
                        <!-- <v-btn color="white" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large', marginTop: '3%', marginLeft: '10%'}"></v-btn> -->
                    </v-row>

                    <!-- 작성일 -->
                    <v-row>
                        <div style="width: 300px; margin-left: 9%; padding-top: 4px; padding-bottom: 25px">작성일 : {{ createdAt }}</div>
                        <!-- <v-text-field v-model="createdAt" solo readonly depressed outlined label="작성일" style="width: 80%; margin-right: 15%"></v-text-field> -->
                    </v-row>
                    <!-- 수정일 기능 -->
                    <!-- <v-row>
                        <div style="width: 300px; margin-left: 30px; padding-top: 2px; padding-bottom: 30px">수정일 : {{ updatedAt }}</div>
                    </v-row> -->

                    <!-- 내용 -->
                    <v-row>
                        <div style="width: 300px; margin-left: 9%; margin-top: 2%; margin-bottom: 15%">{{ text }}</div>
                    </v-row>
                    <v-col cols="12" md="2" />
                </v-form>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>

        <!-- 완료/취소 버튼 -->
        <v-row :style="{marginTop: '1%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-row>
                    <v-col cols="12" md="2" align="right">
                        <v-btn @click="moveback" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large', marginLeft: '55%'}">뒤로가기</v-btn>
                    </v-col>
                    <v-col cols="12" md="6"> </v-col>
                    <v-col cols="12" md="2" align="right">
                        <v-btn @click="editcontent" v-if="editable === false" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">수정</v-btn>
                        <v-btn @click="editcontentfinish" v-if="editable === true" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">수정완료</v-btn>
                    </v-col>
                    <v-col cols="12" md="2">
                        <v-btn @click="deletecontent" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">삭제</v-btn>
                    </v-col>
                </v-row>
                <v-row>
                    <hr style="margin-left: 9%; margin-top: 1%; width: 100%" />
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>

        <v-row>
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-row>
                    <v-textarea outlined readonly value="text" style="width: 100%; margin-left: 9%; margin-top: 2%; margin-bottom: 15%">여기는 아닌가</v-textarea>
                </v-row>
            </v-col>

            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>

<script>
export default {
    components: {},

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
        // axios({
        //     url: 'http://127.0.0.1:52273/content/content/',
        //     method: 'POST',
        //     data: {
        //         id: this.$route.query.id
        //     }
        // })
        //     .then(res => {
        //         this.writer = res.data.writer;
        //         this.title = res.data.title;
        //         this.createdAt = res.data.createdAt.split('T')[0];
        //         this.updatedAt = res.data.updatedAt.split('T')[0];
        //         this.text = res.data.text;
        //     })
        //     .catch(err => {
        //         alert(err);
        //     });
    },
    methods: {
        moveback() {
            window.history.back(); // window.history.back()을 통해 뒤로가기
        },
        // deletecontent() {
        //     // 글에 들어가서 삭제버튼 눌렀을 때
        //     axios({
        //         url: 'http://127.0.0.1:52273/content/delete/',
        //         method: 'POST',
        //         data: {
        //             id: this.$route.query.id
        //         }
        //     })
        //         .then(res => {
        //             alert(res.data.message);
        //             window.location.href = window.location.pathname.slice(0, -8) + '/?page=1';
        //             // 삭제 후 그 게시판의 1페이지로 이동
        //         })
        //         .catch(err => {
        //             alert(err);
        //         });
        // },
        editcontent() {
            this.editable = true;
        },
        // editcontentfinish() {
        //     // 수정완료 버튼을 눌렀을 때, 수정된 내용이 저장되야 되기 때문에 back서버와 통신 필요
        //     axios({
        //         url: 'http://127.0.0.1:52273/content/edit/',
        //         method: 'POST',
        //         data: {
        //             id: this.$route.query.id,
        //             text: this.text
        //         }
        //     })
        //         .then(res => {
        //             alert(res.data.message);
        //             this.editable = false;
        //         })
        //         .catch(err => {
        //             alert(err);
        //         });
        // },
        movetomain() {
            window.location.href = '/';
        }
    }
};
</script>
