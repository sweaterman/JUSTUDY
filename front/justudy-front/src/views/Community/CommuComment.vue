<template>
    <v-container>
        <!-- 내ㄱ꺾//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
        <!-- 댓글 목록 -->
        <v-row>
            <v-col v-for="(comment, index) in commentList" :key="index" cols="12">
                <!-- 댓글 한개 -->
                <div v-if="comment.parentSeq == 0">
                    <!-- 작성자 -->
                    <v-row dense>
                        <v-col cols="12"> <v-icon>mdi-account</v-icon> {{ comment.memberNickName }} </v-col>
                    </v-row>

                    <!-- 댓글 내용 -->
                    <v-row dense>
                        <v-col cols="12">
                            <v-textarea auto-grow rows="1" readonly v-model="comment.content"></v-textarea>
                        </v-col>
                    </v-row>

                    <!-- 작성 시간 & 신고 & 수정 & 삭제 -->
                    <v-row no-gutters>
                        <v-col cols="12" v-if="!comment.isWriter">
                            {{ comment.createdTime }} |
                            <v-btn text @click="btnReport()">
                                <v-icon color="error"> mdi-alarm-light </v-icon>
                            </v-btn></v-col
                        >
                        <v-col cols="12" v-if="comment.isWriter"> {{ comment.createdTime }} | <v-btn text @click="deleteComment(comment.sequence)">삭제</v-btn> </v-col>
                    </v-row>

                    <!-- 대댓글 버튼 -->
                    <v-row no-gutters>
                        <v-col cols="12" align="end">
                            <v-btn @click="toggle(index)" style="margin-bottom: 5px">답글달기</v-btn>
                        </v-col>
                    </v-row>

                    <!-- 대댓글 작성란 -->
                    <v-row v-if="cocommentToggle[index]">
                        <v-col cols="2"></v-col>
                        <v-col cols="10">
                            <!-- 작성자 -->
                            <v-row dense>
                                <v-col cols="12"> <v-icon>mdi-account</v-icon>{{ myNickname }} </v-col>
                            </v-row>
                            <!-- 댓글 내용 -->
                            <v-row dense>
                                <v-col cols="12">
                                    <v-textarea v-model="cocommentContent" no-resize solo name="input-7-4" label="좋은 댓글이 작성자에게 큰 힘이 됩니다."></v-textarea>
                                </v-col>
                            </v-row>
                            <!-- 등록하기 -->
                            <v-row no-gutters>
                                <v-col align="end" cols="12">
                                    <v-btn @click="writeComment(comment.sequence)" color="yellow" style="margin-bottom: 5px">작성</v-btn>
                                </v-col>
                            </v-row>
                        </v-col>
                    </v-row>

                    <v-divider></v-divider>
                </div>

                <!-- 대댓글 한개 -->
                <div v-if="comment.parentSeq != 0">
                    <!-- 작성자 -->
                    <v-row dense>
                        <v-col cols="2"></v-col>
                        <v-col cols="10"> <v-icon>mdi-account</v-icon>{{ comment.memberNickName }} </v-col>
                    </v-row>

                    <!-- 댓글 내용 -->
                    <v-row dense>
                        <v-col cols="2"></v-col>
                        <v-col cols="10">
                            <v-textarea auto-grow rows="1" readonly v-model="comment.content"></v-textarea>
                        </v-col>
                    </v-row>

                    <!-- 작성 시간 & 신고 & 수정 & 삭제 -->
                    <v-row align-content="center" no-gutters>
                        <v-col cols="2"></v-col>
                        <v-col cols="10" v-if="!comment.isWriter">
                            {{ comment.createdTime }} |
                            <v-btn text @click="btnReport()">
                                <v-icon color="error"> mdi-alarm-light </v-icon>
                            </v-btn>
                        </v-col>
                        <v-col cols="10" v-if="comment.isWriter"> {{ comment.createdTime }} |<v-btn text @click="deleteComment(comment.sequence)">삭제</v-btn> </v-col>
                    </v-row>
                    <v-divider></v-divider>
                </div>
            </v-col>
        </v-row>

        <!-- 댓글 쓰기 -->
        <v-row>
            <v-col cols="12">
                <!-- 작성자 -->
                <v-row>
                    <v-col cols="12"> <v-icon>mdi-account</v-icon>{{ myNickname }} </v-col>
                </v-row>
                <!-- 댓글 내용 -->
                <v-row>
                    <v-col cols="12">
                        <v-textarea v-model="commentContent" no-resize solo name="input-7-4" label="좋은 댓글이 작성자에게 큰 힘이 됩니다."></v-textarea>
                    </v-col>
                </v-row>
                <!-- 등록하기 -->
                <v-row no-gutters>
                    <v-col align="end" cols="12">
                        <v-btn @click="writeComment(0)" color="yellow">작성</v-btn>
                    </v-col>
                </v-row>
            </v-col>
        </v-row>
    </v-container>
</template>
<script>
import {mapState} from 'vuex';

export default {
    name: 'CommuComment',
    props: {
        contentId: {
            type: Number
        }
    },
    computed: {
        ...mapState('moduleCommunity', ['commentList'])
    },
    async created() {
        await this.$store.dispatch('moduleCommunity/getCommentList', {
            id: this.contentId
        });
        this.myNickname = localStorage.getItem('nickname');
        this.cocommentToggle = Array.from({length: this.commentList.length}, () => false);
    },
    data() {
        return {
            myNickname: '',
            commentContent: '',
            cocommentToggle: [],
            cocommentContent: ''
        };
    },
    methods: {
        writeComment(parentSeq) {
            //댓글 작성하기
            if (parentSeq == 0) {
                this.$store.dispatch('moduleCommunity/createComment', {
                    id: this.contentId,
                    comment: {
                        content: this.commentContent,
                        parentSeq: parentSeq
                    }
                });
            } else {
                this.$store.dispatch('moduleCommunity/createComment', {
                    id: this.contentId,
                    comment: {
                        content: this.cocommentContent,
                        parentSeq: parentSeq
                    }
                });
            }
        },
        toggle(index) {
            //토글 온
            if (!this.cocommentToggle[index]) {
                let tempArray = Array.from({length: this.commentList.length}, () => false);
                tempArray[index] = true;
                this.cocommentToggle = tempArray;
            }
            //토글 오프
            else {
                let tempArray = Array.from({length: this.commentList.length}, () => false);
                this.cocommentToggle = tempArray;
            }
        },
        deleteComment(seq) {
            this.$store.dispatch('moduleCommunity/deleteComment', {
                id: this.contentId,
                commentid: seq
            });
        },
        btnReport() {
            //신고하기 (module에 alert로 신고 접수 완료 띄우기)
        }
    }
};
</script>
