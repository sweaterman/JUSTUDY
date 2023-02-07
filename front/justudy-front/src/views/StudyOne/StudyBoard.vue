<template>
    <v-app>
        <v-container>
            <!-- 검색 및 글쓰기 -->
            <v-row>
                <v-col cols="12" md="2">
                    <div>스터디 게시판</div>
                </v-col>
                <v-col cols="12" md="2" align="right">
                    <v-select :items="searchoption" v-model="searchoptionselected" />
                </v-col>
                <v-col cols="12" md="4">
                    <v-text-field v-model="searchkeyword" dense outlined label="검색키워드" full-width />
                </v-col>
                <v-col cols="12" md="1">
                    <v-btn>검색</v-btn>
                </v-col>

                <v-col cols="12" md="1" />
                <v-col cols="12" md="2" align="right">
                    <v-btn color="yellow">글작성</v-btn>
                </v-col>
            </v-row>

            <!-- 글 목록 -->
            <v-row>
                <v-simple-table style="width: 100%">
                    <thead>
                        <tr style="font-weight: bolder">
                            <th style="width: 50%">제목</th>
                            <th style="width: 20%">글쓴이</th>
                            <th style="width: 15%">조회수</th>
                            <th style="width: 15%">작성일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="item in contentlist" :key="item.id">
                            <td>{{ item.title }}</td>
                            <td>{{ item.writer }}</td>
                            <td>{{ item.view_count }}</td>
                            <td>{{ item.created_time }}</td>
                        </tr>
                    </tbody>
                </v-simple-table>
            </v-row>

            <v-row>
                <v-col cols="12">
                    <v-btn width="5px" @click="movetopreviouspage">
                        <!-- 이전페이지로 이동 -->
                        <v-icon color="black" small> mdi-arrow-left-bold-outline </v-icon>
                    </v-btn>
                    <span> {{ curPageNum }}/{{ numOfPages }} page </span>
                    <v-btn width="5px" @click="movetonextpage">
                        <!-- 다음페이지로 이동 -->
                        <v-icon color="black" small> mdi-arrow-right-bold-outline </v-icon>
                    </v-btn>
                </v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
export default {
    name: 'StudyBoard',
    computed: {
        //스터디게시판에 글 몇개인지 받아오기 & 첫번째 페이지 글 가져오기
        //검색했을 때도 글 총몇개인지가져오고 페이지네이션 적용
    },
    created() {
        // const pathName = new URL(document.location).pathname.split('/');
        // const studySeq = pathName[pathName.length - 2];
    },
    data() {
        return {
            searchoptionselected: null,
            searchkeyword: null,
            searchoption: ['글제목', '글내용', '글작성자'],
            numOfPages: 10, //총 페이지 수 (데이터 받아서 수정)
            curPageNum: 1
        };
    },
    methods: {
        movetopreviouspage() {
            if (this.curPageNum != 1) {
                this.curPageNum = this.curPageNum - 1;
            }
        },
        movetonextpage() {
            if (this.curPageNum != this.numOfPages) {
                this.curPageNum = this.curPageNum + 1;
            }
        }
    }
};
</script>
