<template>
    <v-container>
        <v-row>
            <!-- 왼쪽 여백 -->
            <v-col cols="12" md="3"></v-col>

            <!-- 본문 -->
            <v-col cols="12" md="6">
                <!-- 검색란 -->
                <v-row style="margin-top: 20px">
                    <v-col col="2"></v-col>
                    <v-col cols="2">
                        <v-select v-model="searchSelect" solo :items="items" label="검색"></v-select>
                    </v-col>
                    <v-col cols="4">
                        <v-text-field v-model="searchContent" label="검색 내용"></v-text-field>
                    </v-col>
                    <v-col cols="4">
                        <v-btn rounded @click="doSearch('search', null)">검색</v-btn>
                    </v-col>
                    <v-col cols="2"></v-col>
                </v-row>

                <!-- 상위 카테고리 tabs -->
                <v-row style="margin-top: 10px; margin-bottom: 10px">
                    <v-col cols="12">
                        <v-tabs color="black" v-model="tab">
                            <v-tabs-slider color="yellow"></v-tabs-slider>
                            <v-tab @click="changeBottom('전체')">
                                <h2>전체</h2>
                            </v-tab>
                            <v-tab v-for="top in topCategories" :key="top.key" @click="changeBottom(top.key)">
                                <h2>{{ top.value }}</h2>
                            </v-tab>
                        </v-tabs>
                    </v-col>
                </v-row>

                <v-row>
                    <v-divider></v-divider>
                </v-row>

                <!-- 하위 카테고리 buttons -->
                <v-row>
                    <v-col v-if="checkAll" cols="12">
                        <div class="btnGroup" v-for="bot in bottomCategories" :key="bot.key">
                            <v-btn outlined class="btnBot" rounded large @click="doSearch('category', bot.key)">
                                <v-avatar size="30">
                                    <img :src="`${port}images/${bot.imageSequence}`" alt="stackIcon" />
                                </v-avatar>
                                &nbsp;&nbsp;{{ bot.value }}
                            </v-btn>
                        </div>
                    </v-col>
                </v-row>

                <!-- 선택한 항목 표기 chips -->
                <v-row>
                    <v-col cols="12">
                        <v-chip-group column>
                            <v-chip close close-icon="mdi-close-outline" @click:close="remove(i)" v-for="i in choice" :key="i">{{ i }}</v-chip>
                        </v-chip-group>
                    </v-col>
                </v-row>

                <!-- 스터디 리스트 -->
                <StudyList :studies="promotionStudies.studyResponse" :type="promotion"></StudyList>

                <!-- 더보기 버튼 -->
                <v-row v-if="checkMore">
                    <v-col cols="3"></v-col>
                    <v-col cols="6">
                        <v-btn outlined color="gold" block @click="getMore">더보기</v-btn>
                    </v-col>
                    <v-col cols="3"></v-col>
                </v-row>

                <!-- 스터디 생성 페이지로 연결 -->
                <v-row class="createCom">
                    <v-col cols="12">
                        <v-row>
                            <v-col dense align="center" cols="12">
                                <h3 style="color: #999999">직접 스터디를 만들어 스터디 멤버를 모집해보세요!</h3>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col dense align="center" cols="12">
                                <v-btn rounded color="#FFEB00" style="height: 50px; width: 50%" @click="moveToCreate">
                                    <h2>1분 만에 스터디 만들기</h2>
                                </v-btn>
                            </v-col>
                        </v-row>
                    </v-col>
                </v-row>
            </v-col>
            <!-- 본문 끝 -->

            <!-- 오른쪽 여백 -->
            <v-col cols="12" md="3"></v-col>
        </v-row>
    </v-container>
</template>

<script>
import StudyList from '@/components/study/StudyList.vue';
import {mapState} from 'vuex';
import port from '@/store/port';

export default {
    name: 'SearchStudyView',
    components: {StudyList},
    created() {
        //초기에 전체 데이터 받아오기
        this.$store.dispatch('moduleStudy/getPromotionStudies', {page: 1, type: null, content: null});
        if (this.promotionStudies.checkMore == false) {
            this.checkMore = false;
        }
        //카테고리 받아오기
        this.$store.dispatch('moduleStudy/getTopCategories');
        this.$store.dispatch('moduleStudy/getBottomCategories', '전체');
    },
    computed: {
        ...mapState('moduleStudy', ['promotionStudies']),
        ...mapState('moduleStudy', ['topCategories']),
        ...mapState('moduleStudy', ['morePromotionStudies']),
        ...mapState('moduleStudy', ['bottomCategories'])
    },
    data() {
        return {
            tab: null,
            button: null,

            //선택한 카테고리가 담기는 곳
            choice: [],

            //검색할 때 제목인지, 스터디장인지 확인 후 검색 내용 담기는 곳
            searchSelect: null,
            searchContent: null,
            searchSend: null,

            pageNum: 1,
            promotion: 'promotion',
            items: ['스터디명', '스터디장'],
            checkMore: true,
            checkAll: false,
            port: port
        };
    },
    methods: {
        async doSearch(type, content) {
            this.pageNum = 0;
            if (type == 'search') {
                if (this.searchSelect != null && this.searchContent != null) {
                    //검색을 클릭했기 때문에 카테고리를 선택했다면 카테고리는 null로 변경
                    this.choice = [];

                    if (this.searchSelect == '스터디명') {
                        this.searchSend = 'name';
                    } else {
                        this.searchSend = 'leader';
                    }
                    //데이터 검색하고 받아오기.
                    this.pageNum = 1;
                    await this.$store.dispatch('moduleStudy/getPromotionStudies', {page: 1, type: this.searchSend, content: this.searchContent});
                }
            } else if (type == 'category') {
                //카테고리를 클릭했기때문에 검색기능은 값이 있을 경우에 null로 만듦
                this.searchSelect = null;
                this.searchContent = null;

                if (content != null) {
                    //기존에 선택한 카테고리가 이미 있다면 합치고 중복제거.
                    this.choice = this.choice.concat(content);
                    this.choice = this.choice.filter((item, pos) => this.choice.indexOf(item) === pos);
                }

                //데이터 검색하고 받아오기.
                this.pageNum = 1;
                if (this.choice == []) {
                    await this.$store.dispatch('moduleStudy/getPromotionStudies', {page: 1, type: 'category', content: ''});
                } else {
                    await this.$store.dispatch('moduleStudy/getPromotionStudies', {page: 1, type: 'category', content: this.choice});
                }
            }
        },
        remove(i) {
            this.pageNum = 1;
            this.choice = this.choice.filter(element => element !== i);
            this.doSearch('category');
        },
        async getMore() {
            this.pageNum = this.pageNum + 1;

            if (this.choice.length != 0) {
                //하부 카테고리 선택한 상태
                await this.$store.dispatch('moduleStudy/getMorePromotionStudies', {page: this.pageNum, type: 'category', content: this.choice});
            } else if (this.searchSelect != null) {
                //검색 버튼을 누른 상태
                await this.$store.dispatch('moduleStudy/getMorePromotionStudies', {page: this.pageNum, type: this.searchSend, content: this.searchContent});
            } else {
                //전체 스터디 더보기
                await this.$store.dispatch('moduleStudy/getMorePromotionStudies', {page: this.pageNum, type: null, content: null});
            }

            //기존에있는 스터디와 추가된 스터디 내용 합쳐주기
            this.promotionStudies.studyResponse = this.promotionStudies.studyResponse.concat(this.morePromotionStudies.studyResponse);

            console.log('더보기 버튼 생성 유무 파악', this.morePromotionStudies);
            //더보기 버튼 생성 유무
            if (this.morePromotionStudies.checkMore == false) {
                this.checkMore = false;
            } else {
                this.checkMore = true;
            }
        },
        moveToCreate() {
            window.location.href = `/study/create`;
        },
        //top에 해당하는 bottom 카테고리 가져오기
        async changeBottom(top) {
            if (top == '전체') {
                this.choice = [];
                this.checkAll = false;
                await this.$store.dispatch('moduleStudy/getBottomCategories', '전체');
            } else {
                this.checkAll = true;
                await this.$store.dispatch('moduleStudy/getBottomCategories', top);
            }
        }
    }
};
</script>

<style scoped>
.btnGroup {
    display: inline-flex;
}

.btnBot {
    margin-top: 10px;
    margin-right: 15px;
}

.createCom {
    padding-top: 10px;
    background-color: #f2f2f2;
    margin-top: 30px;
    border-radius: 20px;
    padding-bottom: 20px;
}
</style>
