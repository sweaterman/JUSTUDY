<template>
    <v-app>
        <!-- 글읽기 제목 -->

        <v-row :style="{marginTop: '3%'}">
            <v-col cols="12" md="4" />
            <v-col cols="12" md="4" justify="center" align="center">
                <TextButton :buttonLength="100" :height="70" :fontSize="10" :content="`게시판 수정`" :standard="px" />
            </v-col>
            <v-col cols="12" md="4" />
        </v-row>

        <!-- 카테고리 지정 -->
        <CategoryHeader />

        <!-- 글쓰기 -->
        <v-row :style="{marginTop: '1%', marginBottom: '1%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-form v-model="isFormValid" ref="form" @submit.prevent="onSubmitForm">
                    <v-card>
                        <!-- 작성자 -->
                        <!-- <v-row>
                            <v-col cols="12" md="2" justify="center" align="center" :style="{color: 'white'}">
                                <v-btn depressed color="white" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large', marginTop: '20%', marginLeft: '15%'}">작성자</v-btn>
                            </v-col>
                            <v-col cols="12" md="10">
                                <v-text-field v-model="writer" solo readonly outlined depressed style="width: 80%; height: 20px; margin-right: 10%; margin-left: 5%; margin-top: 4%"></v-text-field>
                                <v-btn color="white" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large', marginTop: '3%', marginLeft: '10%'}"></v-btn>
                            </v-col>
                        </v-row> -->

                        <!-- 제목 -->
                        <v-row>
                            <v-col cols="12" md="2" justify="center" align="center">
                                <v-btn depressed color="white" :style="{height: '65px', width: '200px', fontWeight: 'bold', fontSize: 'large', marginLeft: '15%'}">제목</v-btn>
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
                            <v-col cols="12" md="2">
                                <v-btn depressed color="white" :style="{height: '40px', width: '200px', fontWeight: 'bold', fontSize: 'large', marginTop: '1%', marginLeft: '15%'}">내용</v-btn>
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
                        <v-btn @click="moveback" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}">취소</v-btn>
                    </v-col>
                    <v-col cols="12" md="9"></v-col>
                    <v-col cols="12" md="2" align="right">
                        <v-btn :disabled="board.title.length >= 30 || board.title.length < 1" @click="write()" :style="{height: '50px', width: '90px', fontWeight: 'bold', fontSize: 'large'}"
                            >작성</v-btn
                        >
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>
    </v-app>
</template>

<script>
import CategoryHeader from '../../components/common/CategoryHeader.vue';
import TextButton from '../../components/common/TextButton.vue';

export default {
    components: {TextButton, CategoryHeader},
    data() {
        // const index = this.$route.params.id;
        return {
            Data: {},
            board: {
                title: '',
                content: '',
                category: this.$route.query.category,
                isHighlighted: false
            }
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
            this.board.category = this.$route.query.category.key;
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
