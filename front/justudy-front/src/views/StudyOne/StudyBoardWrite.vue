<template>
    <v-container>
        <!-- 작성자 -->
        <v-row>
            <v-col cols="4">
                <v-subheader>작성자</v-subheader>
            </v-col>
            <v-col cols="8">
                <v-text-field :value="writer" solo readonly></v-text-field>
            </v-col>
        </v-row>

        <!-- 제목 -->
        <v-row>
            <v-col cols="4">
                <v-subheader>TITLE</v-subheader>
            </v-col>
            <v-col cols="8">
                <v-text-field v-model="title"></v-text-field>
            </v-col>
        </v-row>

        <!-- 내용 -->
        <v-row>
            <v-col col="12" align="center"> CONTENT </v-col>
        </v-row>
        <v-row>
            <v-col col="12">
                <v-textarea v-model="content" rows="15" solo name="input-7-4" no-resize> </v-textarea>
            </v-col>
        </v-row>

        <!-- 방장이면 공지글 체크 버튼 -->
        <v-row v-if="studyInfo.isLeader">
            <v-col cols="12">
                <v-checkbox color="orange" v-model="isHighlighted" label="스터디 공지글로 등록하기"> </v-checkbox>
            </v-col>
        </v-row>

        <!-- 작성 완료 -->
        <v-row>
            <v-col cols="12" align="center">
                <v-btn color="yellow" rounded @click="writeBoard()">작성 완료</v-btn>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import {mapState} from 'vuex';

export default {
    computed: {
        ...mapState('moduleStudy', ['studyInfo'])
    },
    async created() {
        const studySeq = this.$route.params.studySeq;
        await this.$store.dispatch('moduleStudy/getStudyInfo', studySeq);
        this.writer = localStorage.getItem('nickname');
    },
    data() {
        return {
            writer: '',
            title: '',
            content: '',
            isHighlighted: false
        };
    },
    methods: {
        writeBoard() {
            if (this.title == '' || this.content == '') {
                alert('모든 항목을 입력해주세요.');
            } else {
                this.$store.dispatch('moduleStudy/writeBoard', {
                    studySeq: this.$route.params.studySeq,
                    board: {
                        title: this.title,
                        content: this.content,
                        isHighlighted: this.isHighlighted
                    }
                });
            }
        }
    }
};
</script>
<style>
.title_limit {
    width: 150px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    display: inline-block;
}
</style>
