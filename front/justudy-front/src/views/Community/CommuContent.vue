<template>
    <v-app>
        <!-- 상세 글보기 -->
        <v-row :style="{marginTop: '4%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-form ref="form" @submit.prevent="onSubmitForm">
                    <!-- 제목 -->
                    <v-row>
                        <div style="margin-left: 9%; margin-top: 1%">
                            <h1>{{ Data.title }}</h1>
                        </div>
                    </v-row>
                    <v-row>
                        <hr style="margin-left: 9%; margin-top: 1%; width: 100%" />
                    </v-row>

                    <!-- 글쓴이 -->
                    <v-row>
                        <v-col cols="12" md="6">
                            <div style="width: 300px; margin-left: 17%; margin-top: 1.7%; margin-bottom: 0.5%">
                                <h3>작성자 : {{ Data.nickname }}</h3>
                            </div>
                        </v-col>
                        <v-col cols="12" md="2" />
                        <v-col cols="12" md="2" align="right">
                            <v-btn v-if="like" outlined text @click="clickLike('liked')" :style="{color: 'red'}">
                                <span class="material-icons-outlined"> favorite </span>
                            </v-btn>
                            <v-btn v-if="!like" outlined text @click="clickLike('notliked')">
                                <span class="material-icons-outlined"> favorite </span>
                            </v-btn>
                        </v-col>
                        <v-col cols="12" md="2" align="left">
                            <v-btn v-if="bookmark" outlined text @click="clickMark('marked')" :style="{color: 'gold'}">
                                <span class="material-icons-outlined"> bookmark </span>
                            </v-btn>
                            <v-btn v-if="!bookmark" outlined text @click="clickMark('notmarked')">
                                <span class="material-icons-outlined"> bookmark </span>
                            </v-btn>
                        </v-col>
                    </v-row>

                    <!-- 작성일 -->
                    <v-row>
                        <div style="width: 300px; margin-left: 9%; padding-top: 4px; padding-bottom: 25px">작성일 : {{ Data.createdTime }}</div>
                    </v-row>
                    <!-- 수정일 기능 -->
                    <!-- <v-row>
                        <div style="width: 300px; margin-left: 30px; padding-top: 2px; padding-bottom: 30px">수정일 : {{ updatedAt }}</div>
                    </v-row> -->

                    <!-- 내용 -->
                    <v-row>
                        <div style="width: 300px; margin-left: 9%; margin-top: 2%; margin-bottom: 15%">{{ Data.content }}</div>
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
                        <v-btn outlined text @click="moveback">
                            <span class="material-icons-outlined"> arrow_back </span>
                        </v-btn>
                    </v-col>

                    <v-col cols="12" md="6" v-if="Data.nickname == user.nickname"> </v-col>
                    <v-col cols="12" md="8" v-if="Data.nickname != user.nickname"> </v-col>
                    <v-col cols="12" md="2" align="right" v-if="Data.nickname == user.nickname">
                        <v-btn outlined text @click="editcontent">
                            <span class="material-icons-outlined"> edit </span>
                        </v-btn>
                    </v-col>
                    <v-col cols="12" md="2" v-if="Data.nickname == user.nickname">
                        <v-btn outlined text @click="deletecontent" color="red">
                            <span class="material-icons-outlined"> delete </span>
                        </v-btn>
                    </v-col>
                    <v-col cols="12" md="2" v-if="Data.nickname != user.nickname">
                        <v-btn
                            outlined
                            text
                            color="red"
                            v-on:click="
                                () => {
                                    this.dialog = true;
                                }
                            "
                        >
                            <span class="material-icons-outlined"> bug_report </span>
                        </v-btn>
                    </v-col>
                </v-row>
                <v-row>
                    <hr style="margin-left: 9%; margin-top: 1%; width: 100%" />
                </v-row>
            </v-col>

            <v-col cols="12" md="2" />
        </v-row>

        <CommuComment :contentId="parseInt(this.$route.params.id)" />
        <!-- 신고모달창 -->
        <v-row justify="center">
            <v-dialog v-model="dialog" persistent width="1024">
                <template v-slot:activator="{props}">
                    <v-btn color="primary" v-bind="props"> Open Dialog </v-btn>
                </template>
                <v-card>
                    <v-card-title>
                        <span class="text-h5">신고하기</span>
                    </v-card-title>
                    <v-card-text>
                        <v-container>
                            <v-row>
                                <!-- <v-col cols="12" sm="6" md="4">
                                    <v-text-field label="Legal middle name" hint="example of helper text only on focus"></v-text-field>
                                </v-col>
                                <v-col cols="12" sm="6" md="4">
                                    <v-text-field label="Legal last name*" hint="example of persistent helper text" persistent-hint required></v-text-field>
                                </v-col>
                                <v-col cols="12">
                                    <v-text-field label="Email*" required></v-text-field>
                                </v-col>
                                <v-col cols="12">
                                    <v-text-field label="Password*" type="password" required></v-text-field>
                                </v-col> -->
                                <v-col cols="12" sm="6" md="4">
                                    <v-select :items="['abuse', 'privacy', 'spam']" label="신고이유" v-model="report.reason" required></v-select>
                                </v-col>
                                <v-col cols="12" sm="6" md="8">
                                    <v-text-field label="신고내용" v-model="report.content" required></v-text-field>
                                </v-col>
                                <!-- <v-col cols="12" sm="6">
                                    <v-autocomplete
                                        :items="['Skiing', 'Ice hockey', 'Soccer', 'Basketball', 'Hockey', 'Reading', 'Writing', 'Coding', 'Basejump']"
                                        label="Interests"
                                        multiple
                                    ></v-autocomplete>
                                </v-col> -->
                            </v-row>
                        </v-container>
                        <!-- <small>*indicates required field</small> -->
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue-darken-1" variant="text" @click="dialog = false"> 취소 </v-btn>
                        <v-btn color="rgba(255, 0, 0, 0.5)" variant="text" @click="doReport()"> 신고 </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>
    </v-app>
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
                reason: '',
                content: ''
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
        moveback() {
            window.history.back(); // window.history.back()을 통해 뒤로가기
        },
        async deletecontent() {
            // CommunityData.splice(this.index, 1);
            await this.$store.dispatch('moduleCommunity/getCommunityContentDelete', {id: this.Data.sequence});
            this.$router.push({
                path: window.history.back()
            });
        },
        doReport() {
            // 여기에 dispatch  신고만들기
            // this.$store.dispatch('moduleCommunity/report')
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
