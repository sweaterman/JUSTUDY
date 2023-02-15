<template>
    <v-app>
        <v-container>
            <!-- 검색 및 글쓰기 -->
            <v-row>
                <v-col cols="12" md="2">
                    <div>스터디 게시판</div>
                </v-col>
                <v-col cols="12" md="2" align="right">
                    <v-select :items="searchoption" item-text="value" item-value="key" v-model="sendData.type" />
                </v-col>
                <v-col cols="12" md="4">
                    <v-text-field v-model="sendData.search" dense outlined label="검색키워드" full-width />
                </v-col>
                <v-col cols="12" md="1">
                    <v-btn @click="searchData()">검색</v-btn>
                </v-col>

                <v-col cols="12" md="1" />
                <v-col cols="12" md="2" align="right">
                    <v-btn @click="moveToWrite()" color="yellow">글작성</v-btn>
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
                        <tr v-for="item in boardList.communityList" :key="item.sequnce">
                            <td @click="moveToDetail(item.sequence)">{{ item.title }}</td>
                            <td>{{ item.nickname }}</td>
                            <td>{{ item.viewCount }}</td>
                            <td>{{ item.createdTime }}</td>
                        </tr>
                    </tbody>
                </v-simple-table>
            </v-row>

            <v-row>
                <v-col cols="12">
                    <v-btn width="5px" @click="movepage('previous')">
                        <!-- 이전페이지로 이동 -->
                        <v-icon color="black" small>mdi-arrow-left-bold-outline</v-icon>
                    </v-btn>
                    <span>{{ sendData.page }}/{{ parseInt(boardList.totalCount / sendData.size) + 1 }} page</span>
                    <v-btn width="5px" @click="movepage('next')">
                        <!-- 다음페이지로 이동 -->
                        <v-icon color="black" small> mdi-arrow-right-bold-outline </v-icon>
                    </v-btn>
                </v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
import {mapState} from 'vuex';

export default {
    name: 'StudyBoard',
    computed: {
        ...mapState('moduleStudy', ['boardList'])
    },
    async created() {
        const studySeq = this.$route.params.studySeq;
        this.sendData.seq = studySeq;
        await this.$store.dispatch('moduleStudy/getBoardList', this.sendData);
    },
    data() {
        return {
            sendData: {
                seq: '',
                page: 1,
                size: 20,
                type: '', //nickname, title, content
                search: '', //검색 내용
                order: '' //view, like
            },
            searchkeyword: null,
            searchoption: [
                {
                    key: 'title',
                    value: '글제목'
                },
                {
                    key: 'content',
                    value: '글내용'
                },
                {
                    key: 'nickname',
                    value: '글작성자'
                }
            ]
        };
    },
    methods: {
        async movepage(type) {
            if (type == 'previous' && this.sendData.page > 1) {
                //이전 페이지로
                this.sendData.page -= 1;
                await this.$store.dispatch('moduleStudy/getBoardList', this.sendData);
            } else if (type == 'next' && this.sendData.page < this.numOfPages) {
                //다음 페이지로
                this.sendData.page += 1;
                await this.$store.dispatch('moduleStudy/getBoardList', this.sendData);
            }
        },
        searchData() {
            //검색하기.
        },
        moveToWrite() {
            window.location.href = `/study/${this.sendData.seq}/board/write`;
        },
        moveToDetail(seq) {
            window.location.href = `/study/${this.sendData.seq}/board/detail/${seq}`;
        }
    }
};
</script>
