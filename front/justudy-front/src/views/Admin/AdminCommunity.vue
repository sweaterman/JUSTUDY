<template>
    <v-app>
        <!-- 자유게시판 / 검색 기능 / 글쓰기 -->
        <v-row :style="{marginTop: '3%'}">
            <v-col cols="12" md="1" >
                <v-row>
                    <v-col cols="12" md="3" ></v-col>
                    <v-col cols="12" md="9" >
                        <TabIcon></TabIcon>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="11" >
                <v-row>
                    <v-col cols="12" md="10">
                        <div align="center" :style="{fontSize: 'xx-large'}">커뮤니티 관리</div>
                        <v-row style="padding-top: 60px">
                            <v-col cols="12" md="2">
                                <!-- <v-select :items="searchTopOption" v-model="searchTopOptionSelected" :style="{width: '150px'}" /> -->
                            </v-col>
                            <v-col cols="12" md="2" >
                                <v-select :items="searchoption" v-model="searchoptionselected" :style="{width: '150px'}" />
                            </v-col>
                            <v-col cols="12" md="4">
                                <v-text-field v-model="searchkeyword" dense outlined label="검색키워드" full-width />
                            </v-col>
                            <v-col cols="12" md="1" />
                            <v-col cols="12" md="2" align="right">
                                <v-btn color="yellow" @click="searchstart" style="width:100%">검색</v-btn>
                            </v-col>

                        </v-row>
                    </v-col>
                </v-row>

                <!-- 글목록 -->
                <v-row >
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="9">
                        <v-row>
                            <v-simple-table style="width: 100%">
                                <thead>
                                    <tr style="font-weight: bolder">
                                        <td style="width: 5%; font-size: x-large">No</td>
                                        <td style="width: 20%; font-size: x-large">제목</td>
                                        <td style="width: 20%; font-size: x-large">글쓴이</td>
                                        <td style="width: 15%; font-size: x-large">조회수</td>
                                        <td style="width: 15%; font-size: x-large">좋아요수</td>
                                        <td style="width: 15%; font-size: x-large">작성일자</td>
                                        <td style="width: 15%; font-size: x-large">삭제</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="item in contentlist" :key="item.id">
                                        <td  @click="movetocontent(item.id)">{{ item.no }}</td>
                                        <td @click="movetocontent(item.id)">{{ item.title }}</td>
                                        <td @click="movetocontent(item.id)">{{ item.writer }}</td>
                                        <td @click="movetocontent(item.id)">{{ item.viewCount }}</td>
                                        <td @click="movetocontent(item.id)">{{ item.loveCount }}</td>
                                        <td @click="movetocontent(item.id)">{{ item.createdAt.split('T')[0] }}</td>
                                        <td @click="deletecontent(item.id)"><v-btn color="yellow">삭제</v-btn></td>
                                        <!-- Sequelize의 createdAt, updatedAt의 날짜 형식이 '2021-12-10T12:38:52.000Z' 이런 식이여서 
                                    split('T')[0]을 통해 날짜만 표시 -->
                                    </tr>
                                </tbody>
                            </v-simple-table>
                        </v-row>
                    </v-col>
                </v-row>

                <!-- 페이지네이션 -->
                <v-row >
                    <v-col cols="12" md="10">
                        <v-row>
                            <v-col cols="12" md="4" />

                            <v-col cols="12" md="1" align="right">
                                <v-btn width="5px" @click="movetopreviouspage"  color="yellow">
                                    <!-- 이전페이지로 이동 -->
                                    <v-icon color="black" small> mdi-arrow-left-bold-outline </v-icon>
                                </v-btn>
                            </v-col>

                            <v-col cols="12" md="2" justify="center" align="center">
                                <div style="margin-top: 5px; margin-right: 10px; margin-left: 10px">{{ page }} page</div>
                                <!-- 위와 같이 해줌으로서 '현재페이지/총페이지 page' 식으로 나타냄 -->
                            </v-col>

                            <v-col cols="12" md="1" align="left">
                                <v-btn width="5px" @click="movetonextpage"  color="yellow">
                                    <!-- 다음페이지로 이동 -->
                                    <v-icon color="black" small> mdi-arrow-right-bold-outline </v-icon>
                                </v-btn>
                            </v-col>

                            <v-col cols="12" md="4" />
                        </v-row>
                    </v-col>
                    <v-col cols="12" md="1" />
                </v-row>
            </v-col>
        </v-row>
    </v-app>
    <!-- <v-app>
        


        <v-row :style="{marginTop: '-8%'}">
            <v-col cols="12" md="2" />

            <v-col cols="12" md="8">
                <v-row>
                    <v-col cols="12" md="2">
                        <div align="left" :style="{fontSize: 'xx-large'}">커뮤니티 관리</div>
                    </v-col>
                    <v-col cols="12" md="2" align="right">
                        <v-select :items="searchoption" v-model="searchoptionselected" :style="{width: '150px'}" />
                    </v-col>
                    <v-col cols="12" md="4">
                        <v-text-field v-model="searchkeyword" dense outlined label="검색키워드" full-width />
                    </v-col>
                    <v-col cols="12" md="1">
                        <v-btn @click="searchstart">검색</v-btn>
                    </v-col>

                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2" align="right">
                        <v-btn color="yellow" @click="movetowrite" :style="{height: '50px', width: '200px', fontWeight: 'bold', fontSize: 'large'}">글작성</v-btn>
                    </v-col>
                </v-row>
            </v-col>

            <v-col cols="12" md="2" />
        </v-row>

        <v-row :style="{marginTop: '-6%'}">
            <v-col cols="12" md="2" />
            <v-col cols="12" md="8">
                <v-row>
                    <v-simple-table style="width: 100%">
                        <thead>
                            <tr style="font-weight: bolder">
                                <td style="width: 10%; font-size: x-large">No</td>
                                <td style="width: 50%; font-size: x-large">제목</td>
                                <td style="width: 15%; font-size: x-large">글쓴이</td>
                                <td style="width: 15%; font-size: x-large">내용</td>
                                <td style="width: 10%; font-size: x-large">분류</td>
                                <td style="width: 10%; font-size: x-large">작성일자</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="item in contentlist" :key="item.id" @click="movetocontent(item.id)">
                                <td>{{ item.writer }}</td>
                                <td>{{ item.title }}</td>
                                <td>{{ item.createdAt.split('T')[0] }}</td>
                            </tr>
                        </tbody>
                    </v-simple-table>
                </v-row>
            </v-col>
            <v-col cols="12" md="2" />
        </v-row>

        <v-row style="padding-top: 10%">
            <v-col cols="12" md="1" />
            <v-col cols="12" md="10">
                <v-row>
                    <v-col cols="12" md="4" />

                    <v-col cols="12" md="1" align="right">
                        <v-btn width="5px" @click="movetopreviouspage">
                            <v-icon color="black" small> mdi-arrow-left-bold-outline </v-icon>
                        </v-btn>
                    </v-col>

                    <v-col cols="12" md="2" justify="center" align="center">
                        <div style="margin-top: 5px; margin-right: 10px; margin-left: 10px">{{ $route.query.page }}/{{ totalpage }} page</div>
                    </v-col>

                    <v-col cols="12" md="1" align="left">
                        <v-btn width="5px" @click="movetonextpage">
                            <v-icon color="black" small> mdi-arrow-right-bold-outline </v-icon>
                        </v-btn>
                    </v-col>

                    <v-col cols="12" md="4" />
                </v-row>
            </v-col>
            <v-col cols="12" md="1" />
        </v-row>
    </v-app> -->
</template>
<script>
import TabIcon from "@/components/Admin/TabIcon.vue"
import axios from 'axios';
import port from '@/store/port';
export default {
    components: {TabIcon},
    data() {
        return {
            port:port,
            page:1,
            size:10,
            searchword:"",
            searchkeyword:"",
            searchTopOption:['frontend','backend','infra','cs','algorithm','leading-edge','bulletinboard'],
            searchTopOptionSelected:"frontend",
            searchoption:['NICKNAME','TITLE',"CONTENT"],
            searchoptionselected:'NICKNAME',
            contentlist: [{
                    id:0,
                    no :1,
                    title :"GOD 어머님께",
                    writer :"짱구",
                    viewCount :"어머님은 탕수육을 좋아하셨어",      
                    loveCount :"frontend",     
                    createdAt: "2012-01-01T"
                }
            ], // 현재 게시판과 페이지에 맞는 글 리스트들
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
    created(){
        this.changeUserData();
    },
    methods: {
        changeUserData(){
            let API_URL = `${this.port}admin/community?page=${this.page}&size=${this.size}`;
            if(this.searchword != null &&  this.searchword != "" ){
                API_URL = `${this.port}admin/community?page=${this.page}&size=${this.size}&search=${this.searchword}&type=${this.searchoptionselected}`;
            }
            console.log("API_URL : "+API_URL);
            this.contentlist = [];
            axios.get(API_URL)
            .then((ret) => {
                    let response = ret.data.communityList;
                    console.log(ret);
                    for(let i = 0; i < response.length; i++){
                        response[i];
                        this.contentlist.push(
                            {
                                id : response[i].sequence,
                                no :i+1,
                                title :response[i].title,
                                writer :response[i].nickname,
                                viewCount :response[i].viewCount,
                                loveCount :response[i].loveCount, 
                                createdAt: response[i].createdTime,
                            }
                        )
                    }
                    console.log(this.contentlist);
                }
            )
            .catch((error) => {
                console.log(error);
            });
        },
        // 페이지 이동시 params로 게시판 구분, query로 페이지 구분
        movetoboard1() {
            // window.location.href = 'admin/community/1/?page=1';
        },
        movetoboard2() {
            // window.location.href = 'admin/community/2/?page=1';
        },
        movetoboard3() {
            // window.location.href = 'admin/community/3/?page=1';
        },
        movetomain() {
            window.location.href = '/admin/community';
        },
        movetowrite() {
            // window.location.href = 'admin/community/3/write';
            // window.location.href = window.location.pathname + 'write';
            // window.location.pathname이 현재 주소를 의미
            // 여기다 write를 붙여주면 글 작성 페이지로 라우팅 되게 됨
        },
        movetocontent(id) {
            // 클릭된 글의 id를 받아와야 라우팅할때 보낼 수 있음
            console.log('/community/content/' + id);
            window.location.href = '/community/content/' + id;
        },
        deletecontent(id){
            let API_URL = `${this.port}admin/community/${id}`;
            axios.delete(API_URL)
            .then((ret) => {
                    console.log(ret);
                    // window.location.reload();
                }
            )
            .catch((error) => {
                console.log(error);
                // window.location.reload();
            });
        },
        movetopreviouspage() {
            if (this.page == 1) {
                alert('첫번째 페이지입니다!');
            } else {
                this.page-=1;
                this.changeUserData();
            }
        },
        movetonextpage() {
                this.page+=1;
                this.changeUserData();
        },
        searchstart(){
            this.searchword =this.searchkeyword; 
            this.page=1;
            this.changeUserData();
        },
    }
};
</script>
