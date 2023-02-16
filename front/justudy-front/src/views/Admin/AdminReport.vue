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
                        <div align="center" :style="{fontSize: 'xx-large'}">신고 게시판</div>
                        <v-row style="padding-top: 60px">
                            <!-- <v-col cols="12" md="2">
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
                            </v-col> -->

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
                                        <td style="width: 10%; font-size: x-large">No</td>
                                        <td style="width: 10%; font-size: x-large">신고자</td>
                                        <!-- <td style="width: 10%; font-size: x-large">피신고자</td> -->
                                        <td style="width: 25%; font-size: x-large">신고이유</td>
                                        <td style="width: 10%; font-size: x-large">종류</td>
                                        <!-- <td style="width: 10%; font-size: x-large">식별ID</td> -->
                                        <td style="width: 15%; font-size: x-large">신고시간</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="item in contentlist" :key="item.id" @click="movetocontent(item.targetSequence,item.type )">
                                        <td>{{ item.no }}</td>
                                        <td>{{ item.reporter }}</td>
                                        <!-- <td>{{ item.report }}</td> -->
                                        <td>{{ item.reason }}</td>
                                        <td>{{ item.type }}</td>
                                        <td>{{ item.createdAt }}</td>
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
</template>
<script>
import TabIcon from '@/components/Admin/TabIcon.vue';
import axios from 'axios';
import port from '@/store/port';
export default {
    components: {TabIcon},
    data() {
        return {
            page:1,
            pageSize:10,
            port:port,
            searchkeyword:"",
            searchoption:['스터디명','스터디장'],
            searchoptionselected:'스터디명',
            contentlist: [{
                    no :1,
                    report :"놀자",
                    reporter :"olleh",
                    reason :"온라인이 진리",      
                    type :"3/4",     
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
            
            // let API_URL = `${this.port}study?pageNumber=${this.page}&pageSize=${this.size}&paged=true`;
            let API_URL = `${this.port}admin/report?pageNumber=${this.page}&pageSize=${this.pageSize}`;
           
            console.log("API_URL : "+API_URL);
            this.contentlist = [];
            axios.get(API_URL)
            .then((ret) => {
                    let response = ret.data.reportList;
                    console.log(ret);
                    for(let i = 0; i < response.length; i++){
                        
                        this.contentlist.push(
                            {
                                id : response[i].reportSequence,
                                no :i+1,


                            }
                        );
                    }
                    console.log(this.contentlist);
                }
            )
            .then( () => {
                let temp = [];
                for(let i = 0; i < this.contentlist.length; i++){
                    let TARGET_API_URL = `${this.port}admin/report/${this.contentlist[i].id}`;
                    axios.get(TARGET_API_URL)
                    .then((ret)=>{
                        temp.push(
                            {
                                no : i+1,
                                report :"놀자",
                                reporter : ret.data.reporterName,
                                reason :ret.data.content,
                                type :ret.data.type,   
                                createdAt: ret.data.createdTime,
                                targetSequence : ret.data.targetSequence,
                            }
                        )
                    })
                }
                this.contentlist=temp;
                console.log( this.contentlist);
            })
            .catch((error) => {
                console.log(error);
            });
        },
        // 페이지 이동시 params로 게시판 구분, query로 페이지 구분
        movetoboard1() {
            // window.location.href = 'admin/study/1/?page=1';
        },
        movetoboard2() {
            // window.location.href = 'admin/study/2/?page=1';
        },
        movetoboard3() {
            // window.location.href = 'admin/study/3/?page=1';
        },
        movetomain() {
            window.location.href = '/admin/study';
        },
        movetowrite() {
            // window.location.href = 'admin/study/3/write';
            // window.location.href = window.location.pathname + 'write';
            // window.location.pathname이 현재 주소를 의미
            // 여기다 write를 붙여주면 글 작성 페이지로 라우팅 되게 됨
        },
        movetocontent(id,type) {
            console.log(id);
            console.log(type);
            if(type=="community"){
                window.location.href = '/community/content/' + id;
            }
            // else if(type ==""){

            // }
            
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
    }
};
</script>
