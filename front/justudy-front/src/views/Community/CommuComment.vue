<template>
    <v-app>
        <v-card>
            <v-row>
                <v-col cols="12" md="10">
                    <v-textarea v-model="content" label="생성"></v-textarea>
                    <v-btn @click="createComment" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">생성</v-btn>
                </v-col>
            </v-row>
        </v-card>
        <div :key="item.sequence" v-for="(item, index) in this.comments">
            <v-row :style="`background-color: ${getColor(item.parentSeq)}`">
                <v-col>
                    {{ item.parentSeq != 0 ? 'ㄴㄴㄴ' : '' }}
                </v-col>
                <v-col>
                    {{ item.memberSeq }}
                    <v-textarea v-model="item.content" label="내용"></v-textarea>
                    {{ item.createdTime }}
                    <br />
                    <v-btn v-if="item.isWriter" @click="updateComment(item)" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">수정</v-btn>
                    <v-btn v-if="item.isWriter" @click="deleteComment(item.sequence)" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">삭제</v-btn>
                </v-col>
                <v-col v-if="item.parentSeq == 0">
                    <v-textarea v-model="item.inputValue" label="답글생성"></v-textarea>

                    <v-btn @click="createReply(item.sequence, index)" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">답글생성</v-btn>
                </v-col>
            </v-row>
        </div>
    </v-app>
</template>
<script>
export default {
    name: 'CommuComment',
    props: {
        contentId: {
            type: Number
        }
    },
    data() {
        return {
            comments: [],
            content: [],
            comment: {
                communitySeq: 0,
                content: '',
                parentSeq: 0
            }
        };
    },
    methods: {
        getColor(parentSeq) {
            if (parentSeq == 0) {
                return 'white';
            } else {
                return 'yellow';
            }
        },
        async updateData() {
            console.log('아아' + this.contentId);
            await this.$store.dispatch('moduleCommunity/getCommentList', {id: this.contentId});
            this.comments = this.$store.state.moduleCommunity.commentList;
            console.log(this.comments);
        },
        async createComment() {
            this.comment.content = this.content;
            this.comment.communitySeq = this.contentId;
            //부모 댓글일시 parentSeq 값 부모sequence 값으로 변경해야함!
            this.comment.parentSeq = 0;

            await this.$store.dispatch('moduleCommunity/createComment', {id: this.contentId, comment: this.comment});
            this.comments = this.$store.state.moduleCommunity.commentList;
            console.log(this.comments);
        },
        async createReply(parentSeq, index) {
            this.comment.content = this.comments[index].inputValue;
            console.log('아아' + this.comment.content);
            this.comment.communitySeq = this.contentId;
            //부모 댓글일시 parentSeq 값 부모sequence 값으로 변경해야함!
            this.comment.parentSeq = parentSeq;

            await this.$store.dispatch('moduleCommunity/createComment', {id: this.contentId, comment: this.comment});
            this.comments = this.$store.state.moduleCommunity.commentList;
            console.log(this.comments);
        },
        async updateComment(comment) {
            console.log(comment);
            await this.$store.dispatch('moduleCommunity/updateComment', {id: this.contentId, commentid: comment.sequence, comment: comment});
            this.comments = this.$store.state.moduleCommunity.commentList;
            console.log(this.comments);
        },
        async deleteComment(comment) {
            console.log(comment);
            await this.$store.dispatch('moduleCommunity/deleteComment', {id: this.contentId, commentid: comment});
            this.comments = this.$store.state.moduleCommunity.commentList;
            console.log(this.comments);
        }
    },
    mounted() {
        this.updateData();
        console.log(this.comment);
    }
};
</script>
<style scoped></style>
