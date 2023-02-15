<template>
    <v-container>
        <!-- 작성자 -->
        <v-row>
            <v-col cols="4">
                <v-subheader>작성자</v-subheader>
            </v-col>
            <v-col cols="8">
                <v-text-field :value="boardDetail.nickname" solo readonly></v-text-field>
            </v-col>
        </v-row>

        <!-- 제목 -->
        <v-row>
            <v-col cols="4">
                <v-subheader>TITLE</v-subheader>
            </v-col>
            <v-col cols="8">
                <v-text-field v-model="sendData.title"></v-text-field>
            </v-col>
        </v-row>

        <!-- 내용 -->
        <v-row>
            <v-col col="12" align="center"> CONTENT </v-col>
        </v-row>
        <v-row>
            <v-col col="12">
                <v-textarea v-model="sendData.content" rows="15" solo name="input-7-4" no-resize> </v-textarea>
            </v-col>
        </v-row>

        <!-- 수정 완료 -->
        <v-row>
            <v-col cols="12">
                <v-btn color="yellow" @click="boardUpdate()">수정 완료</v-btn>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import {mapState} from 'vuex';

export default {
    name: 'StudyBoardDetail',
    computed: {
        ...mapState('moduleStudy', ['boardDetail'])
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
        this.sendData.title = this.boardDetail.title;
        this.sendData.content = this.boardDetail.content;
    },
    data() {
        return {
            studySeq: 1,
            boardSeq: 1,
            sendData: {
                title: '',
                content: ''
            }
        };
    },
    methods: {
        boardUpdate() {
            //수정 완료
            this.$store.dispatch('moduleStudy/modifyBoard', {
                studySeq: this.studySeq,
                boardSeq: this.boardSeq,
                board: {
                    title: this.sendData.title,
                    content: this.sendData.content
                }
            });
        }
    }
};
</script>
