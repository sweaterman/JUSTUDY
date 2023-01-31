<template>
    <v-app>
        <v-container>
            <!-- 자유게시판 / 검색 기능 / 글쓰기 -->
            <v-row>
                <v-col cols="12" md="2" />

                <v-col cols="12" md="8">
                    <v-row>
                        <v-col cols="12" md="2">
                            <div align="left">스터디 게시판</div>
                        </v-col>
                        <v-col cols="12" md="2" align="right">
                            <v-select :items="searchoption" v-model="searchoptionselected" />
                        </v-col>
                        <v-col cols="12" md="4">
                            <v-text-field v-model="searchkeyword" dense outlined label="검색키워드" full-width />
                        </v-col>
                        <v-col cols="12" md="1">
                            <v-btn @click="searchstart">검색</v-btn>
                        </v-col>

                        <v-col cols="12" md="1" />
                        <v-col cols="12" md="2" align="right">
                            <v-btn color="yellow" @click="movetowrite">글작성</v-btn>
                        </v-col>
                    </v-row>
                </v-col>

                <v-col cols="12" md="2" />
            </v-row>

            <!-- 글목록 -->
            <v-row>
                <v-col cols="12" md="2" />
                <v-col cols="12" md="8">
                    <v-row>
                        <v-simple-table style="width: 100%">
                            <thead>
                                <tr style="font-weight: bolder">
                                    <td style="width: 10%; font-size: x-large">No</td>
                                    <td style="width: 50%; font-size: x-large">제목</td>
                                    <td style="width: 15%; font-size: x-large">글쓴이</td>
                                    <td style="width: 15%; font-size: x-large">작성시간</td>
                                    <td style="width: 10%; font-size: x-large">좋아요</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="item in contentlist" :key="item.id" @click="movetocontent(item.id)">
                                    <td>{{ item.writer }}</td>
                                    <td>{{ item.title }}</td>
                                    <td>{{ item.createdAt.split('T')[0] }}</td>
                                    <!-- Sequelize의 createdAt, updatedAt의 날짜 형식이 '2021-12-10T12:38:52.000Z' 이런 식이여서 
                               split('T')[0]을 통해 날짜만 표시 -->
                                </tr>
                            </tbody>
                        </v-simple-table>
                    </v-row>
                </v-col>
                <v-col cols="12" md="2" />
            </v-row>

            <!-- 페이지네이션 -->
            <v-row style="padding-top: 10%">
                <v-col cols="12" md="1" />
                <v-col cols="12" md="10">
                    <v-row>
                        <v-col cols="12" md="4" />

                        <v-col cols="12" md="1" align="right">
                            <v-btn width="5px" @click="movetopreviouspage">
                                <!-- 이전페이지로 이동 -->
                                <v-icon color="black" small> mdi-arrow-left-bold-outline </v-icon>
                            </v-btn>
                        </v-col>

                        <v-col cols="12" md="2" justify="center" align="center">
                            <div style="margin-top: 5px; margin-right: 10px; margin-left: 10px">{{ $route.query.page }}/{{ totalpage }} page</div>
                            <!-- 위와 같이 해줌으로서 '현재페이지/총페이지 page' 식으로 나타냄 -->
                        </v-col>

                        <v-col cols="12" md="1" align="left">
                            <v-btn width="5px" @click="movetonextpage">
                                <!-- 다음페이지로 이동 -->
                                <v-icon color="black" small> mdi-arrow-right-bold-outline </v-icon>
                            </v-btn>
                        </v-col>

                        <v-col cols="12" md="4" />
                    </v-row>
                </v-col>
                <v-col cols="12" md="1" />
            </v-row>
        </v-container>
    </v-app>
</template>
<script>
export default {
    components: {},
    data() {
        return {
            contentlist: [], // 현재 게시판과 페이지에 맞는 글 리스트들
            cnt: 0 // 현재 게시판의 총 글 개수
        };
    },
    computed: {
        // // computed는 계산 목적으로 사용된다고 보면 됨
        // totalpage() {
        //     if (this.cnt == 0) {
        //         // 현재 게시판 글 갯수가 0개일때 총 페이지가 0이 되는거 방지
        //         return 1;
        //     } else {
        //         return Math.ceil(this.cnt / 10); // (글 갯수/10)한 후 올림 연산을 통해 총 페이지 계산
        //     }
        // }
    },
    methods: {
        // 페이지 이동시 params로 게시판 구분, query로 페이지 구분
        movetoboard1() {
            window.location.href = '/community/1/?page=1';
        },
        movetoboard2() {
            window.location.href = '/community/2/?page=1';
        },
        movetoboard3() {
            window.location.href = '/community/3/?page=1';
        },
        movetomain() {
            window.location.href = '/community';
        },
        movetowrite() {
            window.location.href = '/community/3/write';
            // window.location.href = window.location.pathname + 'write';
            // window.location.pathname이 현재 주소를 의미
            // 여기다 write를 붙여주면 글 작성 페이지로 라우팅 되게 됨
        },
        movetocontent(id) {
            // 클릭된 글의 id를 받아와야 라우팅할때 보낼 수 있음
            window.location.href = window.location.pathname + 'content?id=' + id;
        },
        movetopreviouspage() {
            if (this.$route.query.page == 1) {
                alert('첫번째 페이지입니다!');
            } else {
                var pp = parseInt(this.$route.query.page) - 1;
                window.location.href = window.location.pathname + '?page=' + pp;
            }
        },
        movetonextpage() {
            if (this.$route.query.page == Math.ceil(this.cnt / 10)) {
                alert('마지막 페이지입니다!');
            } else {
                var pp = parseInt(this.$route.query.page) + 1;
                window.location.href = window.location.pathname + '?page=' + pp;
            }
        }
    }
};
</script>
