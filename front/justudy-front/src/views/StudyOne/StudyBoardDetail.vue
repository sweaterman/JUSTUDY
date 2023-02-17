<template>
    <v-container>
        <!-- 글 삭제 토글 -->
        <v-dialog v-model="boardDialog">
            <v-card>
                <v-card-title>글 삭제</v-card-title>
                <v-card-text>정말 글을 삭제하시겠습니까?</v-card-text>
                <v-card-actions>
                    <v-btn @click="boardDelete()">삭제</v-btn>
                    <v-btn @click="boardDialog = false">취소</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>

        <!-- 글 신고 토글 -->
        <v-dialog v-model="reportDialog">
            <v-card>
                <v-card-title>신고</v-card-title>
                <v-card-text>신고 API 추가</v-card-text>
                <v-card-actions>
                    <v-btn @click="btnReport()">신고</v-btn>
                    <v-btn @click="reportDialog = false">취소</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>

        <!-- 제목 -->
        <v-row>
            <v-col cols="12" style="background-color: #e4e4e4">
                <h2>{{ boardDetail.title }}</h2>
            </v-col>
        </v-row>

        <!-- 글쓴이 -->
        <v-row>
            <v-col cols="12"> 작성자 : {{ boardDetail.nickname }} </v-col>
        </v-row>

        <!-- 작성일 -->
        <v-row dense>
            <v-col cols="12"> 작성일 : {{ boardDetail.createdTime }} </v-col>
        </v-row>

        <v-row>
            <v-col cols="12">
                <v-divider></v-divider>
            </v-col>
        </v-row>

        <!-- 좋아요, 북마크 -->
        <v-row>
            <v-col cols="12" align="end">
                <v-btn v-if="boardDetail.loved" outlined text @click="btnLike()" :style="{color: 'red'}">
                    <span class="material-icons-outlined">favorite</span>
                </v-btn>
                <v-btn v-if="!boardDetail.loved" outlined text @click="btnLike()">
                    <span class="material-icons-outlined">favorite</span>
                </v-btn>
                &nbsp;&nbsp;
                <v-btn v-if="boardDetail.bookmarked" outlined text @click="btnBookmark()" :style="{color: 'gold'}">
                    <span class="material-icons-outlined">bookmark</span>
                </v-btn>
                <v-btn v-if="!boardDetail.bookmarked" outlined text @click="btnBookmark()">
                    <span class="material-icons-outlined">bookmark</span>
                </v-btn>
            </v-col>
        </v-row>

        <!-- 내용 -->
        <v-row>
            <v-col col="12">
                <v-textarea readonly v-model="boardDetail.content" rows="15" solo name="input-7-4" no-resize> </v-textarea>
            </v-col>
        </v-row>

        <!-- 글쓴이 일때 수정, 삭제 -->
        <v-row v-if="boardDetail.writer">
            <v-col cols="12" align="end">
                <v-btn @click="boardUpdate()">수정하기</v-btn>
                &nbsp;&nbsp;
                <v-btn @click="boardDialog = true">삭제하기</v-btn>
            </v-col>
        </v-row>

        <!-- 댓글 목록 -->
        <v-row>
            <v-col v-for="(comment, index) in commentList" :key="index" cols="12">
                <!-- 댓글 한개 -->
                <div v-if="comment.parentSeq == 0">
                    <!-- 작성자 -->
                    <v-row>
                        <v-col cols="12"> <v-icon>mdi-account</v-icon>{{ comment.memberNickName }} </v-col>
                    </v-row>

                    <!-- 댓글 내용 -->
                    <v-row>
                        <v-col cols="12">
                            <v-textarea auto-grow rows="1" readonly background-color="grey lighten-2" v-model="comment.content"></v-textarea>
                        </v-col>
                    </v-row>

                    <!-- 작성 시간 & 신고 & 수정 & 삭제 -->
                    <v-row>
                        <v-col cols="12" v-if="!comment.isWriter"> {{ comment.createdTime }} | 신고버튼 </v-col>
                        <v-col cols="12" v-if="comment.isWriter"> {{ comment.createdTime }} | <v-btn text @click="deleteComment(comment.sequence)">삭제</v-btn> </v-col>
                    </v-row>

                    <!-- 대댓글 버튼 -->
                    <v-row>
                        <v-col cols="12">
                            <v-btn @click="toggle(index)">답글</v-btn>
                        </v-col>
                    </v-row>

                    <!-- 대댓글 작성란 -->
                    <v-row v-if="cocommentToggle[index]">
                        <v-col cols="2"></v-col>
                        <v-col cols="10">
                            <!-- 작성자 -->
                            <v-row>
                                <v-col cols="12"> <v-icon>mdi-account</v-icon>{{ myNickname }} </v-col>
                            </v-row>
                            <!-- 댓글 내용 -->
                            <v-row>
                                <v-col cols="12">
                                    <v-textarea v-model="cocommentContent" no-resize solo name="input-7-4" label="좋은 댓글이 작성자에게 큰 힘이 됩니다."></v-textarea>
                                </v-col>
                            </v-row>
                            <!-- 등록하기 -->
                            <v-row no-gutters>
                                <v-col align="end" cols="12">
                                    <v-btn @click="writeComment(comment.sequence)" color="yellow">작성</v-btn>
                                </v-col>
                            </v-row>
                        </v-col>
                    </v-row>

                    <v-divider></v-divider>
                </div>

                <!-- 대댓글 한개 -->
                <div v-if="comment.parentSeq != 0">
                    <!-- 작성자 -->
                    <v-row>
                        <v-col cols="2"></v-col>
                        <v-col cols="10"> <v-icon>mdi-account</v-icon>{{ comment.memberNickName }} </v-col>
                    </v-row>

                    <!-- 댓글 내용 -->
                    <v-row>
                        <v-col cols="2"></v-col>
                        <v-col cols="10">
                            <v-textarea auto-grow rows="1" readonly background-color="grey lighten-2" v-model="comment.content"></v-textarea>
                        </v-col>
                    </v-row>

                    <!-- 작성 시간 & 신고 & 수정 & 삭제 -->
                    <v-row>
                        <v-col cols="2"></v-col>
                        <v-col cols="10" v-if="!comment.isWriter"> {{ comment.createdTime }} | 신고버튼 </v-col>
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
    name: 'StudyBoardDetail',
    computed: {
        ...mapState('moduleStudy', ['boardDetail']),
        ...mapState('moduleStudy', ['commentList'])
    },
    async created() {
        const studySeq = this.$route.params.studySeq;
        const boardSeq = this.$route.params.boardSeq;
        this.studySeq = studySeq;
        this.boardSeq = boardSeq;
        await this.$store.dispatch('moduleStudy/getBoardDetail', {
            boardSeq: this.boardSeq,
            studySeq: this.studySeq
        });
        await this.$store.dispatch('moduleStudy/getCommentList', {
            boardSeq: this.boardSeq,
            studySeq: this.studySeq
        });
        this.myNickname = localStorage.getItem('nickname');
        this.cocommentToggle = Array.from({length: this.commentList.length}, () => false);
    },
    data() {
        return {
            studySeq: 1,
            boardSeq: 1,
            boardDialog: false, //스터디 삭제 토글
            reportDialog: false,
            myNickname: '',
            commentContent: '',
            cocommentToggle: [],
            cocommentContent: ''
        };
    },
    methods: {
        boardUpdate() {
            //수정하기
            window.location.href = `/study/${this.studySeq}/board/update/${this.boardSeq}`;
        },
        async boardDelete() {
            //삭제하기
            await this.$store.dispatch('moduleStudy/deleteBoard', {
                studySeq: this.studySeq,
                boardSeq: this.boardSeq
            });
            this.boardDialog = false;
        },
        btnLike() {
            //좋아요 누르기
            if (!this.boardDetail.loved) {
                this.$store.dispatch('moduleStudy/doLike', {
                    studySeq: this.studySeq,
                    boardSeq: this.boardSeq
                });
            }
            //좋아요 취소
            else {
                this.$store.dispatch('moduleStudy/undoLike', {
                    studySeq: this.studySeq,
                    boardSeq: this.boardSeq
                });
            }
        },
        btnBookmark() {
            //북마크 하기
            if (!this.boardDetail.bookmarked) {
                this.$store.dispatch('moduleStudy/doBookmarked', {
                    studySeq: this.studySeq,
                    boardSeq: this.boardSeq
                });
            }
            //북마크 취소
            else {
                this.$store.dispatch('moduleStudy/undoBookmarked', {
                    studySeq: this.studySeq,
                    boardSeq: this.boardSeq
                });
            }
        },
        writeComment(parentSeq) {
            //댓글 작성하기
            if (parentSeq == 0) {
                this.$store.dispatch('moduleStudy/writeComment', {
                    studySeq: this.studySeq,
                    boardSeq: this.boardSeq,
                    comment: {
                        content: this.commentContent,
                        parentSeq: parentSeq
                    }
                });
            }

            //대댓글 작성하기
            else {
                this.$store.dispatch('moduleStudy/writeComment', {
                    studySeq: this.studySeq,
                    boardSeq: this.boardSeq,
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
            this.$store.dispatch('moduleStudy/deleteComment', {
                studySeq: this.studySeq,
                boardSeq: this.boardSeq,
                commentSeq: seq
            });
        },
        btnReport() {
            //신고하기 (module에 alert로 신고 접수 완료 띄우기)
        }
    }
};
</script>
