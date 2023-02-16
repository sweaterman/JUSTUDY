<template>
    <v-container>
        <!-- 상세 글보기 -->
        <v-row style="margin-top: 20px">
            <v-col cols="12" md="3">
                <!-- <v-row>
                    <v-col cols="12" md="2" align="right">
                        <v-btn outlined text @click="moveback">
                            <span class="material-icons-outlined">arrow_back</span>
                        </v-btn>
                    </v-col>
                </v-row> -->
            </v-col>
            <v-col cols="12" md="6">
                <v-form ref="form" @submit.prevent="onSubmitForm">
                    <!-- 제목 -->
                    <v-row>
                        <v-col cols="12" style="background-color: #e4e4e4">
                            <h2>{{ Data.title }}</h2>
                        </v-col>
                    </v-row>

                    <!-- 글쓴이 -->
                    <v-row>
                        <v-col cols="12"> 작성자 : {{ Data.nickname }} </v-col>
                    </v-row>

                    <!-- 작성일 -->
                    <v-row dense>
                        <v-col cols="12"> 작성일 : {{ Data.createdTime }} </v-col>
                    </v-row>

                    <v-row>
                        <v-col cols="12">
                            <v-divider></v-divider>
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col cols="12" align="end">
                            <v-btn v-if="like" outlined text @click="clickLike('liked')" :style="{color: 'red'}">
                                <span class="material-icons-outlined">favorite</span>
                            </v-btn>
                            <v-btn v-if="!like" outlined text @click="clickLike('notliked')">
                                <span class="material-icons-outlined">favorite</span>
                            </v-btn>
                            &nbsp;&nbsp;
                            <v-btn v-if="bookmark" outlined text @click="clickMark('marked')" :style="{color: 'gold'}">
                                <span class="material-icons-outlined">bookmark</span>
                            </v-btn>
                            <v-btn v-if="!bookmark" outlined text @click="clickMark('notmarked')">
                                <span class="material-icons-outlined">bookmark</span>
                            </v-btn>
                            &nbsp;&nbsp;
                            <v-btn
                                text
                                outlined
                                v-if="Data.nickname != user.nickname"
                                v-on:click="
                                    () => {
                                        this.dialog = true;
                                    }
                                "
                            >
                                <v-icon color="error"> mdi-alarm-light </v-icon>
                            </v-btn>
                        </v-col>
                    </v-row>

                    <!-- 내용 -->
                    <v-row>
                        <v-col cols="12">
                            <div style="white-space: pre-wrap">{{ Data.content }}</div>
                        </v-col>
                    </v-row>
                </v-form>
            </v-col>
            <v-col cols="12" md="3" />
        </v-row>

        <!-- 완료/취소 버튼 -->
        <v-row :style="{marginTop: '1%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-row>
                    <v-col cols="12" md="2" align="right"></v-col>

                    <v-col cols="12" md="6" v-if="Data.nickname == user.nickname"></v-col>
                    <v-col cols="12" md="8" v-if="Data.nickname != user.nickname"></v-col>
                    <v-col cols="12" md="2" align="right" v-if="Data.nickname == user.nickname">
                        <v-btn outlined text @click="editcontent">
                            <span class="material-icons-outlined">edit</span>
                        </v-btn>
                    </v-col>
                    <v-col cols="12" md="2" v-if="Data.nickname == user.nickname">
                        <v-btn outlined text @click="deletecontent" color="red">
                            <span class="material-icons-outlined">delete</span>
                        </v-btn>
                    </v-col>
                </v-row>
                <v-row>
                    <v-divider> </v-divider>
                </v-row>
            </v-col>

            <v-col cols="12" md="2" />
        </v-row>

        <!-- 댓글 -->
        <v-row>
            <v-col cols="3"></v-col>
            <v-col cols="6">
                <CommuComment :contentId="parseInt(this.$route.params.id)" />
            </v-col>
            <v-col cols="3"></v-col>
        </v-row>

        <!-- 신고모달창 -->
        <v-row justify="center">
            <v-dialog v-model="dialog" persistent width="800">
                <v-card>
                    <v-card-title>
                        <span class="text-h5">신고하기</span>
                    </v-card-title>
                    <v-card-text>
                        <v-container>
                            <v-row>
                                <v-col cols="4">
                                    <v-select
                                        :items="['욕설/혐오/차별적 표현입니다.', '개인정보 노출 게시물입니다.', '스팸홍보/도배글입니다.']"
                                        label="신고이유"
                                        v-model="report.reason"
                                        required
                                    ></v-select>
                                </v-col>
                            </v-row>

                            <v-row>
                                <v-col cols="12">
                                    <v-text-field label="신고내용" required></v-text-field>
                                </v-col>
                            </v-row>
                        </v-container>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue-darken-1" variant="text" @click="dialog = false"> 취소 </v-btn>
                        <v-btn color="rgba(255, 0, 0, 0.5)" variant="text" @click="doReport()"> 신고 </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>
    </v-container>
</template>

<script>
import CommuComment from './CommuComment.vue';

export default {
    name: 'CommuContent',
    components: {CommuComment},
    data() {
        const index = this.$route.params.id;
        return {
            Data: {},
            index: index,
            like: false,
            bookmark: false,
            // writer: '돌숭이', // 작성자
            // title: '돌숭이의 꿀팁', // 글 제목
            // createdAt: '2023/01/18', // 작성일
            // updatedAt: '2023/01/20', // 최근 수정일
            // text: '그런건 없습니다', // 글 내용
            editable: false, // 수정가능여부 (수정 버튼누르면 true로 바뀜),
            bookMarkList: [],
            loveList: [],
            user: {},
            dialog: false,
            report: {
                type: 'community',
                reason: ''
            }
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
    async created() {
        await this.$store.dispatch('moduleMyPage/getMyPageUser');
        this.user = this.$store.state.moduleMyPage.user;
        await this.$store.dispatch('moduleCommunity/getCommunityContent', {id: this.$route.params.id});
        this.Data = this.$store.state.moduleCommunity.CommunityContent;
        console.log(this.Data);
        await this.$store.dispatch('moduleCommunity/getBookMarkList');
        this.bookMarkList = this.$store.state.moduleCommunity.bookMarkList;
        await this.$store.dispatch('moduleCommunity/getLoveList');
        this.loveList = this.$store.state.moduleCommunity.loveList;
        for (let i = 0; i < this.bookMarkList.length; i++) {
            if (this.bookMarkList[i].sequence == this.$route.params.id) {
                this.bookmark = true;
                break;
            }
        }
        for (let i = 0; i < this.loveList.length; i++) {
            if (this.loveList[i].sequence == this.$route.params.id) {
                this.like = true;
                break;
            }
        }
    },
    methods: {
        async clickLike(check) {
            if (check != 'liked') {
                this.like = true;
                await this.$store.dispatch('moduleCommunity/createLove', {id: this.$route.params.id});
            } else {
                this.like = false;
                await this.$store.dispatch('moduleCommunity/deleteLove', {id: this.$route.params.id});
            }
        },
        async clickMark(check) {
            if (check != 'marked') {
                this.bookmark = true;
                await this.$store.dispatch('moduleCommunity/createBookMark', {id: this.$route.params.id});
            } else {
                this.bookmark = false;
                await this.$store.dispatch('moduleCommunity/deleteBookMark', {id: this.$route.params.id});
            }
        },
        // moveback() {
        //     window.history.back(); // window.history.back()을 통해 뒤로가기
        // },
        async deletecontent() {
            // CommunityData.splice(this.index, 1);
            await this.$store.dispatch('moduleCommunity/getCommunityContentDelete', {id: this.Data.sequence});
            this.$router.push({
                path: window.history.back()
            });
        },
        async doReport() {
            // 여기에 dispatch  신고만들기
            //  "['abuse', 'privacy', 'spam']"
            // "['욕설/혐오/차별적 표현입니다.', '개인정보 노출 게시물입니다.', '스팸홍보/도배글입니다.']"
            if (this.report.reason == '욕설/혐오/차별적 표현입니다.') {
                this.report.reason = 'abuse';
            } else if (this.report.reason == '개인정보 노출 게시물입니다.') {
                this.report.reason = 'privacy';
            } else {
                this.report.reason = 'spam';
            }
            await this.$store.dispatch('moduleCommunity/createReport', {boardId: this.$route.params.id, report: this.report});
            this.dialog = false;
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
            this.$router.push({
                name: 'CommuUpdate',
                path: this.$route.path + 'update',
                query: {
                    category: this.Data.category.key
                }
            });
        },
        editcontentfinish() {},
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
