<template>
    <v-app>
        <!-- 글읽기 제목 -->

        <v-row :style="{marginTop: '1%'}">
            <v-col cols="12" md="4" />
            <v-col cols="12" md="4" justify="center" align="center">
                <div><h1>게시판 글 수정하기</h1></div>
            </v-col>
            <v-col cols="12" md="4" />
        </v-row>

        <!-- 카테고리 지정 -->
        <CategoryHeader_write />

        <!-- 글쓰기 -->
        <v-row :style="{marginTop: '1%', marginBottom: '1%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-form v-model="isFormValid" ref="form" @submit.prevent="onSubmitForm">
                    <v-card>
                        <!-- 작성자 -->
                        <v-row>
                            <v-col cols="12" md="2" align="right" :style="{marginTop: '1%'}">
                                <h2>작성자</h2>
                            </v-col>
                            <v-col cols="12" md="10">
                                <h3>{{ writer }}</h3>
                            </v-col>
                        </v-row>

                        <!-- 제목 -->
                        <v-row>
                            <v-col cols="12" md="2" align="right">
                                <h2>제목</h2>
                            </v-col>
                            <v-col cols="12" md="10">
                                <v-text-field
                                    v-model="board.title"
                                    outlined
                                    label="제목을 입력하세요"
                                    style="width: 80%; margin-right: 10%; margin-left: 5%"
                                    :rules="[v => (!!(v.length > 0) && !(v.length >= 30)) || '제목 글자수를 지켜주세요.']"
                                >
                                </v-text-field>
                            </v-col>
                        </v-row>

                        <!-- 내용 -->
                        <v-row>
                            <v-col cols="12" md="2" align="right">
                                <h2>내용</h2>
                            </v-col>
                            <v-col cols="12" md="10">
                                <v-textarea v-model="board.content" label="내용" outlined rows="13" style="width: 80%; margin-right: 10%; margin-left: 5%; margintop: 4%"></v-textarea>
                            </v-col>
                        </v-row>
                    </v-card>
                </v-form>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>
        <!-- 완료/취소 버튼 -->
        <v-row :style="{marginBottom: '10%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-row>
                    <v-col cols="12" md="1">
                        <v-btn outlined text @click="moveback">
                            <span class="material-icons-outlined"> arrow_back </span>
                        </v-btn>
                    </v-col>
                    <v-col cols="12" md="9"></v-col>
                    <v-col cols="12" md="2" align="right">
                        <v-btn :style="{color: 'green'}" outlined text :disabled="board.title.length >= 30 || board.title.length < 1" @click="write()">
                            <span class="material-icons-outlined"> done </span>
                        </v-btn>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>

<script>
import CategoryHeader_write from '../../components/common/CategoryHeader_write.vue';

export default {
    components: {CategoryHeader_write},
    data() {
        // const index = this.$route.params.id;
        return {
            Data: {},
            board: {
                title: '',
                content: '',
                category: this.$route.query.category,
                isHighlighted: false
            },
            isFormValid: true
        };
    },
    methods: {
        moveback() {
            window.history.back(); // window.history.back()을 통해 뒤로가기
        },
        async write() {
            // this.data.push({
            //     index: 1,
            //     title: this.title,
            //     created_time: '2022-01-22',
            //     view_count: 0,
            //     love_count: 0,
            //     content: this.content,
            //     writer: this.writer
            // });
            this.board.category = this.$route.query.category;
            this.board.isHighlighted = false;
            console.log(this.board);
            await this.$store.dispatch('moduleCommunity/getCommunityContentUpdate', {id: this.Data.sequence, board: this.board});
            this.$router.push({
                path: window.history.back()
            });
        }
    },
    async created() {
        await this.$store.dispatch('moduleCommunity/getCommunityContent', {id: this.$route.params.id});
        this.Data = this.$store.state.moduleCommunity.CommunityContent;
        this.board.title = this.Data.title;
        this.board.content = this.Data.content;
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
