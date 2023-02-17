<template>
    <v-container>
        <!-- 자유게시판 / 검색 기능 / 글쓰기 -->
        <v-row :style="{marginTop: '1%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-row>
                    <v-col cols="12" md="2">
                        <div align="left" :style="{fontSize: 'xx-large'}"></div>
                    </v-col>
                    <v-col cols="12" md="2">
                        <v-select :items="searchoption" item-text="value" item-value="key" v-model="searchoptionselected" label="항목선택" :style="{width: '150px'}" />
                    </v-col>
                    <v-col cols="12" md="4">
                        <v-text-field v-model="searchkeyword" dense outlined label="검색키워드" full-width @keyup.enter="searchstart" />
                    </v-col>
                    <v-col cols="12" md="1">
                        <v-btn @click="searchstart">검색</v-btn>
                    </v-col>

                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2" justify="center" align="center">
                        <v-btn text @click="movetowrite()" :style="{fontSize: 'large'}">
                            <span class="material-icons-outlined">edit_note</span>
                            <div>글쓰기</div>
                        </v-btn>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>

        <v-row style="margin-top: 10px">
            <v-col cols="3"></v-col>
            <v-col cols="6" align-content="center">
                <CategoryHeader @click="updateData" />
            </v-col>
            <v-col cols="3"></v-col>
        </v-row>

        <!-- 글목록 -->
        <v-row>
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-row>
                    <v-divider style="margin-bottom: 10px"></v-divider>
                </v-row>
                <v-row>
                    <v-simple-table style="width: 100%">
                        <!-- 
                                    정렬 클릭시 updateData(category,type,search,order) 실행하면 됩니다
                                    order에 ""값이면 번호, "like" 면 좋아요 "view"면 조회수
                        -->
                        <thead>
                            <tr style="font-weight: bolder">
                                <td style="width: 15%; font-size: large">No</td>
                                <td style="width: 25%; font-size: large">제목</td>
                                <td style="width: 15%; font-size: large">글쓴이</td>
                                <td style="width: 15%; font-size: large">작성시간</td>
                                <td style="width: 10%; font-size: large">조회수</td>
                                <td style="width: 10%; font-size: large">좋아요</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(value, index) in Data.communityList" :key="index" @click="movetocontent(value.sequence)">
                                <td>{{ index + 1 }}</td>
                                <td>
                                    <div class="line_limit">{{ value.title }}</div>
                                </td>
                                <td>
                                    <div class="line_limit">{{ value.nickname }}</div>
                                </td>
                                <td>{{ value.createdTime }}</td>
                                <td>{{ value.viewCount }}</td>
                                <td>{{ value.loveCount }}</td>
                                <!-- Sequelize의 createdAt, updatedAt의 날짜 형식이 '2021-12-10T12:38:52.000Z' 이런 식이여서 
                                split('T')[0]을 통해 날짜만 표시-->
                            </tr>
                        </tbody>
                    </v-simple-table>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>

        <!-- 페이지네이션 -->
        <v-row>
            <v-col cols="12" md="1" />
            <v-col cols="12" md="10">
                <v-row>
                    <v-col cols="12" md="4" />

                    <v-col cols="12" md="1" align="right">
                        <v-btn outlined text width="5px" @click="movetopreviouspage">
                            <!-- 이전페이지로 이동 -->
                            <span class="material-icons-outlined">arrow_back</span>
                        </v-btn>
                    </v-col>

                    <v-col cols="12" md="2" justify="center" align="center">
                        <div style="margin-top: 5px; margin-right: 10px; margin-left: 10px">{{ $route.query.page }}/{{ totalpage }} page</div>
                        <!-- 위와 같이 해줌으로서 '현재페이지/총페이지 page' 식으로 나타냄 -->
                    </v-col>

                    <v-col cols="12" md="1" align="left">
                        <v-btn outlined text width="5px" @click="movetonextpage">
                            <!-- 다음페이지로 이동 -->
                            <span class="material-icons-outlined">arrow_forward</span>
                        </v-btn>
                    </v-col>

                    <v-col cols="12" md="4" />
                </v-row>
            </v-col>
            <v-col cols="12" md="1" />
        </v-row>
    </v-container>
</template>
<script>
import CategoryHeader from '../../components/common/CategoryHeader.vue';
// import {mapState} from 'vuex';

export default {
    name: 'CommuBoard',
    components: {CategoryHeader},
    data() {
        return {
            Data: [],
            cnt: 0,
            // searchoption : [{key :"작성자",value :"title"},{key :"작성자",value :"title"},{key :"작성자",value :"title"}],
            searchoption: [
                {key: 'nickname', value: '작성자'},
                {key: 'title', value: '제목'},
                {key: 'content', value: '내용'}
            ],
            searchoptionselected: '',
            searchkeyword: '',
            category: '',
            type: '',
            search: ''
            //contentlist: [], // 현재 게시판과 페이지에 맞는 글 리스트들
            //cnt: 0 // 현재 게시판의 총 글 개수
        };
    },
    async created() {
        this.updateData('all');
    },
    computed: {
        // // computed는 계산 목적으로 사용된다고 보면 됨
        totalpage() {
            if (this.cnt == 0) {
                // 현재 게시판 글 갯수가 0개일때 총 페이지가 0이 되는거 방지
                return 1;
            } else {
                return Math.ceil(this.cnt / 20); // (글 갯수/10)한 후 올림 연산을 통해 총 페이지 계산
            }
        }
    },
    methods: {
        // 페이지 이동시 params로 게시판 구분, query로 페이지 구분
        // movetoboard1() {
        //     window.location.href = '/community/1/?page=1';
        // },
        // movetoboard2() {
        //     window.location.href = '/community/2/?page=1';
        // },
        // movetoboard3() {
        //     window.location.href = '/community/3/?page=1';
        // },
        async updateData(data, type, search, order) {
            await this.$store.dispatch('moduleCommunity/getCommunityBoard', {number: this.$route.query.page, category: data, type: type, search: search, order: order});
            this.Data = this.$store.state.moduleCommunity.CommunityBoard;
            this.category = data;
            this.type = this.searchoptionselected;
            this.search = this.searchkeyword;
            this.cnt = this.Data.totalCount;
        },

        movetomain() {
            window.location.href = '/community';
        },
        movetowrite() {
            // window.location.href = '/community/1/write';
            this.$router.push({
                name: 'CommuWrite',
                // params: {
                //     id: index
                // },
                query: {
                    category: this.$route.query.category
                }
            });
            // window.location.href = window.location.pathname + '/write/' + id;
            // window.location.href = window.location.pathname + 'write';
            // window.location.pathname이 현재 주소를 의미
            // 여기다 write를 붙여주면 글 작성 페이지로 라우팅 되게 됨
        },
        movetocontent(index) {
            // 클릭된 글의 id를 받아와야 라우팅할때 보낼 수 있음
            this.$router.push({
                // path: window.location.pathname + '/content/' + id,
                name: 'CommuContent',
                params: {
                    id: index
                }
            });
            // window.location.href = window.location.pathname + '/content/' + id;
        },
        async movetopreviouspage() {
            if (this.$route.query.page == 1) {
                alert('첫번째 페이지입니다!');
            } else {
                var pp = parseInt(this.$route.query.page) - 1;
                // window.location.href = '/community/' + pp;
                await this.$store.dispatch('moduleCommunity/getCommunityBoard', {
                    number: pp,
                    category: this.$route.query.category,
                    type: this.$route.query.type,
                    search: this.$route.query.search,
                    order: this.$route.query.order
                });
                this.Data = this.$store.state.moduleCommunity.CommunityBoard;
                this.$router.push({
                    name: 'CommuBoard',
                    query: {page: pp, category: this.$route.query.category}
                    // query: {page: pp, category: this.$route.query.category, type: this.$route.query.type, search: this.$route.query.search, order: this.$route.query.order}
                });
            }
        },
        async movetonextpage() {
            if (this.$route.query.page >= Math.ceil(this.cnt / 20)) {
                alert('마지막 페이지입니다!');
            } else {
                var pp = parseInt(this.$route.query.page) + 1;

                await this.$store.dispatch('moduleCommunity/getCommunityBoard', {
                    number: pp,
                    category: this.$route.query.category,
                    type: this.$route.query.type,
                    search: this.$route.query.search,
                    order: this.$route.query.order
                });
                this.Data = this.$store.state.moduleCommunity.CommunityBoard;

                this.$router.push({
                    name: 'CommuBoard',
                    query: {page: pp, category: this.$route.query.category}
                    // query: {page: pp, category: this.$route.query.category, type: this.$route.query.type, search: this.$route.query.search, order: this.$route.query.order}
                });
            }
        },
        searchstart() {
            this.updateData(this.category, this.searchoptionselected, this.searchkeyword);
        }
    }
};
</script>
<style>
.line_limit {
    width: 150px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    display: inline-block;
}
</style>
