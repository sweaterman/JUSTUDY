<template>
    <v-container>
        <v-row>
            <!-- 사이드 -->
            <v-col cols="12" md="4">
                <!-- 이미지 수정 -->
                <v-row>
                    <v-col cols="12">
                        <img class="img" style="width: 100%" src="@/assets/study_320x180.png" alt="study_image" />
                    </v-col>
                </v-row>
                <!-- 이미지 수정부분 추가 -->
                <v-row justify="center" align="center" :style="{marginTop: '-20%'}">
                    <v-btn @click="mo()" color="dark gray">
                        <!-- 이미지 변경 함수 자리 -->
                        <span class="material-icons-outlined"> image </span>
                    </v-btn>
                </v-row>

                <v-row :style="{marginTop: '10%'}">
                    <v-col justify="center" align="center">
                        <div class="card_section" justify="center" align="center">
                            <v-row>
                                <!-- 기본 정보 선택 부분 -->
                                <!-- 상위 카테고리 -->
                                <v-row>
                                    <v-col justify="center" align="center">
                                        <v-combobox
                                            v-model="study.topCategory"
                                            :items="topCategories"
                                            item-text="value"
                                            item-value="key"
                                            label="상위 카테고리 변경"
                                            :style="{padding: '7%'}"
                                            @change="checkTopCategory()"
                                        ></v-combobox>
                                    </v-col>
                                </v-row>

                                <!-- 하위 카테고리 -->
                                <v-row>
                                    <v-col>
                                        <v-combobox
                                            v-model="study.bottomCategory"
                                            :items="bottomCategories"
                                            item-text="value"
                                            item-value="key"
                                            label="하위 카테고리 변경"
                                            :style="{padding: '7%', marginTop: '-10%'}"
                                        ></v-combobox>
                                    </v-col>
                                </v-row>

                                <!-- 모집 인원 -->
                                <v-row>
                                    <v-col>
                                        <v-combobox v-model="studyInfo.population" :items="populationList" label="모집인원 변경" :style="{padding: '7%', marginTop: '-10%'}"></v-combobox>
                                    </v-col>
                                </v-row>

                                <!-- Level -->
                                <v-row>
                                    <v-col>
                                        <v-combobox v-model="studyInfo.level" :items="levelList" label="레벨 변경" :style="{padding: '7%', marginTop: '-10%'}"></v-combobox>
                                    </v-col>
                                </v-row>

                                <!-- 온/오프 -->
                                <v-row>
                                    <v-col>
                                        <v-combobox v-model="studyInfo.meeting" :items="meetingList" label="모임 방식 변경" :style="{padding: '7%', marginTop: '-10%'}"></v-combobox>
                                    </v-col>
                                </v-row>

                                <!-- 시작예정일 -->
                                <v-row>
                                    <v-col>
                                        <v-text-field v-model="studyInfo.start_time" label="시작 날짜 변경" :style="{padding: '7%', marginTop: '-10%'}"></v-text-field>
                                    </v-col>
                                </v-row>

                                <!-- 활동 주기 -->
                                <v-row>
                                    <v-col cols="4">
                                        <v-subheader>**활동 주기**</v-subheader>
                                    </v-col>
                                    <v-col cols="8">
                                        <!-- 활동주기 확인/삭제하는 v-for -->
                                        <v-row v-for="i in frequency_num" :key="i">
                                            <v-col cols="6"> {{ studyInfo.frequency[i - 1].week }} / {{ study.frequency[i - 1].startTime }} ~ {{ studyInfo.frequency[i - 1].endTime }} </v-col>
                                            <v-col cols="6">
                                                <v-btn
                                                    class="mx-2"
                                                    fab
                                                    dark
                                                    x-small
                                                    color="red"
                                                    @click="deleteFrequency(study.frequency[i - 1].week, study.frequency[i - 1].startTime, study.frequency[i - 1].endTime)"
                                                >
                                                    <v-icon dark> mdi-minus </v-icon>
                                                </v-btn>
                                            </v-col>
                                        </v-row>

                                        <!-- 활동주기 추가하는 Dialog -->
                                        <v-row>
                                            <v-col cols="12">
                                                <v-btn @click="frequency_dialog = true">**활동주기 추가**</v-btn>
                                            </v-col>
                                        </v-row>

                                        <!-- 활동주기 모달 -->
                                        <v-dialog v-model="frequency_dialog" width="800">
                                            <v-card>
                                                <v-card-title> 활동 주기 추가 </v-card-title>

                                                <v-card-text>
                                                    <v-row>
                                                        <v-col cols="3">
                                                            <v-subheader>요일 선택</v-subheader>
                                                        </v-col>
                                                        <v-col cols="9">
                                                            <v-combobox v-model="temp_frequency.week" :items="week_option" label="요일 선택"></v-combobox>
                                                        </v-col>
                                                    </v-row>
                                                    <v-row>
                                                        <v-col cols="6">
                                                            <v-subheader>스터디 시작 시간</v-subheader>
                                                        </v-col>
                                                        <v-col>
                                                            <v-subheader>스터디 끝나는 시간</v-subheader>
                                                        </v-col>
                                                    </v-row>
                                                    <v-row>
                                                        <v-col cols="6">
                                                            <v-time-picker v-model="temp_frequency.startTime" ampm-in-title format="ampm"></v-time-picker>
                                                        </v-col>
                                                        <v-col cols="6">
                                                            <v-time-picker v-model="temp_frequency.endTime" ampm-in-title format="ampm"></v-time-picker>
                                                        </v-col>
                                                    </v-row>
                                                </v-card-text>

                                                <v-divider></v-divider>

                                                <v-card-actions>
                                                    <v-spacer></v-spacer>
                                                    <v-btn color="primary" text @click="addFrequency()"> 확인 </v-btn>
                                                    <v-btn color="primary" text @click="frequency_dialog = false"> 취소 </v-btn>
                                                </v-card-actions>
                                            </v-card>
                                        </v-dialog>
                                    </v-col>
                                </v-row>
                                <!-- 기본정보선택끝 -->
                            </v-row>
                        </div>
                    </v-col>
                </v-row>
            </v-col>

            <!-- ////////////////////////////// 본문 ////////////////////////////// -->
            <v-col cols="12" md="8" :style="{padding: '4%'}">
                <!-- 스터디 이름/주소 수정 -->
                <v-row>
                    <v-col cols="12" md="6">
                        <h2>상세정보 수정</h2>
                        <hr width="60%" />
                    </v-col>
                    <v-col cols="12" md="6" align="right">
                        <v-btn v-if="showStudy" @click="showStudybtn" color="sliver">
                            <v-col cols="12" md="4">
                                <span class="material-icons-outlined" :style="{color: 'green'}"> visibility </span>
                            </v-col>
                            <v-col cols="12" md="8">
                                <div :style="{color: 'green'}">스터디 홍보 중</div>
                            </v-col>
                        </v-btn>
                        <v-btn v-if="!showStudy" @click="showStudybtn" color="sliver">
                            <v-col cols="12" md="4">
                                <span class="material-icons-outlined" :style="{color: 'crimson'}"> visibility_off </span>
                            </v-col>
                            <v-col cols="12" md="8">
                                <div :style="{color: 'crimson'}">스터디 비공개</div>
                            </v-col>
                        </v-btn>
                    </v-col>
                    <!-- ////////////////////////////////////////////// -->
                </v-row>
                <v-row justify="center" align="center">
                    <v-col cols="12" md="3">
                        <h3>스터디 이름</h3>
                    </v-col>
                    <v-col cols="12" md="5">
                        <v-text-field v-model="studyInfo.name" label="스터디 이름" :rules="name_rules" hide-details="auto">
                            {{ studyInfo.name }}
                        </v-text-field>
                    </v-col>
                    <v-col cols="12" md="4" align="right">
                        <v-btn v-if="!checkName" @click="checkNamebtn" :style="{color: 'crimson'}">
                            <v-col cols="12" md="4">
                                <span class="material-icons-outlined"> check_circle </span>
                            </v-col>
                            <v-col cols="12" md="8">
                                <div>중복 확인</div>
                            </v-col>
                        </v-btn>
                        <v-btn v-if="checkName" :style="{color: 'green'}">
                            <v-col cols="12" md="4">
                                <span class="material-icons-outlined"> check_circle </span>
                            </v-col>
                            <v-col cols="12" md="8">
                                <div>사용 가능 !</div>
                            </v-col>
                        </v-btn>
                    </v-col>
                </v-row>
                <v-row justify="center" align="center">
                    <v-col cols="12" md="2">
                        <h3>URL 변경</h3>
                    </v-col>
                    <v-col cols="12" md="2" align="right">
                        <v-img :src="require('@/assets/git.png')" :style="{width: '40px'}" />
                    </v-col>
                    <v-col cols="12" md="3" justify="center" align="center">
                        <v-text-field v-model="studyInfo.github"> {{ studyInfo.github }}</v-text-field>
                    </v-col>
                    <v-col cols="12" md="2" align="right">
                        <v-img :src="require('@/assets/notion.png')" :style="{width: '40px'}" />
                    </v-col>
                    <v-col cols="12" md="3" justify="center" align="center">
                        <v-text-field v-model="studyInfo.notion"> {{ studyInfo.notion }} </v-text-field>
                    </v-col>
                </v-row>

                <!-- 글쓰기 수정 -->
                <v-row :style="{marginTop: '5%'}">
                    <v-col>
                        <h2>소개글</h2>
                        <hr width="30%" />
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        <v-textarea v-model="studyInfo.information" outlined rows="13">
                            {{ studyInfo.information }}
                        </v-textarea>
                    </v-col>
                </v-row>

                <!-- 회원 관리 -->
                <v-row :style="{marginTop: '3%'}">
                    <v-col>
                        <h2>회원 관리</h2>
                        <hr width="60%" />
                    </v-col>
                </v-row>
                <v-row v-for="member in studyInfo.member" :key="member.sequence">
                    <v-col>
                        <div class="card_section_member">
                            <v-row justify="center" align="center">
                                <v-col cols="12" md="2" justify="center" align="center">
                                    <v-avatar size="50">
                                        <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
                                    </v-avatar>
                                </v-col>
                                <v-col cols="12" md="6" justify="center" align="center">
                                    <v-row>
                                        <div :style="{fontSize: 'large'}" justify="center" align="center">
                                            {{ member.nickname }}
                                        </div>
                                    </v-row>
                                </v-col>
                                <v-col cols="12" md="2">
                                    <v-btn @click="needtochange" color="white" depressed>
                                        <span class="material-icons-outlined" :style="{color: 'black'}"> stars </span>
                                    </v-btn>
                                </v-col>
                                <v-col cols="12" md="2">
                                    <v-btn @click="memberoutDialog('open')" color="white" depressed>
                                        <span class="material-icons-outlined" :style="{color: 'crimson'}"> cancel </span>
                                    </v-btn>
                                    <v-dialog v-model="memberOutClicked" max-width="600" max-height="70">
                                        <v-card>
                                            <v-row :style="{padding: '15%'}">
                                                <v-col cols="12" md="2" />
                                                <v-col cols="12" md="8" justify="center" align="center">
                                                    <h3>해당 팀원을 탈퇴시킵니다 !!</h3>
                                                </v-col>
                                                <v-col cols="12" md="2" />
                                                <!-- </v-row>

                                            <v-row> -->
                                                <!-- <v-card-actions> -->
                                                <v-col cols="12" md="8" />
                                                <v-col cols="12" md="2">
                                                    <v-btn @click="needtochange" color="white" depressed>
                                                        <span class="material-icons-outlined" :style="{color: 'green'}"> check_circle </span>
                                                    </v-btn>
                                                </v-col>
                                                <v-col cols="12" md="2">
                                                    <v-btn @click="needtochange" color="white" depressed>
                                                        <span class="material-icons-outlined" :style="{color: 'crimson'}"> cancel </span>
                                                    </v-btn>
                                                </v-col>
                                            </v-row>
                                        </v-card>
                                    </v-dialog>
                                </v-col>
                            </v-row>
                        </div>
                    </v-col>
                </v-row>

                <!-- 신청 관리 -->
                <v-row :style="{marginTop: '8%'}">
                    <v-col>
                        <h2>신청 관리</h2>
                        <hr width="60%" />
                    </v-col>
                </v-row>
                <v-row v-for="applicant in applyList" :key="applicant.sequence">
                    <v-col>
                        <div class="card_section_applicant">
                            <v-row justify="center" align="center">
                                <v-col cols="12" md="2">
                                    <v-row>
                                        <v-avatar size="55">
                                            <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
                                        </v-avatar>
                                    </v-row>
                                    <v-row :style="{marginTop: '20%'}">
                                        {{ applicant.name }}
                                    </v-row>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <v-row>
                                        {{ applicant.content }}
                                    </v-row>
                                </v-col>
                                <v-col cols="12" md="2">
                                    <v-btn @click="needtochange" color="white" depressed>
                                        <span class="material-icons-outlined" :style="{color: 'green'}"> check_circle </span>
                                    </v-btn>
                                </v-col>
                                <v-col cols="12" md="2">
                                    <v-btn @click="needtochange" color="white" depressed>
                                        <span class="material-icons-outlined" :style="{color: 'crimson'}"> cancel </span>
                                    </v-btn>
                                </v-col>
                            </v-row>
                            <v-row> </v-row>
                        </div>
                    </v-col>
                </v-row>

                <v-row>
                    <v-col align="right">
                        <v-btn @click="mo()" color="yellow">수정 완료</v-btn>
                    </v-col>
                </v-row>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
// import {mapState} from 'vuex';

export default {
    name: 'StudyEdit',

    // async created() {
    //     const pathName = new URL(document.location).pathname.split('/');
    //     const studySeq = pathName[pathName.length - 2];
    //     await this.$store.dispatch('moduleStudy/getStudyInfo', studySeq);
    //     if (this.studyInfo.isLeader == false) {
    //         window.location.href = '/error';
    //     }
    // }
    data() {
        return {
            study: {
                name: '리엑트 용자 모임',
                introduction: 'test',
                population: '',
                level: '',
                topCategory: 'Front-end',
                bottomCategory: 'React',
                frequency: [],
                meeting: '',
                github: 'https://lab.ssafy.com/s08-webmobile1-sub2/S08P12A104',
                notion: 'https://www.notion.so/',
                start_time: ''
            },
            temp_frequency: {
                week: null,
                startTime: null,
                endTime: null
            },
            studyInfo: {
                sequence: 1,
                name: '리액트 용자 모임',
                leader: '이연희',
                population: 10,
                category: 'Vue',
                member: [
                    {
                        sequence: 1,
                        nickname: '이연희',
                        name: '이연희이름',
                        badge: 6542
                    },
                    {
                        sequence: 2,
                        nickname: '김싸피임',
                        name: '김싸피',
                        badge: 5422
                    },
                    {
                        sequence: 3,
                        nickname: '박싸피임',
                        name: '박싸피룰루',
                        badge: 546
                    }
                ],
                level: '초보',
                meeting: '온/오프라인',
                isOpen: true,
                github: 'http://this.is.git.link',
                notion: 'http://this.is.notion.link',
                start_time: '2023.01.02',
                information: `주제: 리액트입니다 \n
                목표: 모두 리액트 마스터하기 \n
                예상 커리큘럼: Do it 리액트! 책을 순서대로 공부합니다. \n
                개설 동기: 리액트를 너무 공부하고 싶었어요.. \n
                주의사항: 노쇼 금지! `,
                isLeader: true,
                isMember: false
            },
            applyList: [
                {
                    sequence: '1',
                    memberSeq: '101',
                    studySeq: '1',
                    name: '김싸피',
                    content: '일단 지원합니다',
                    created_time: '2023-01-04'
                },
                {
                    sequence: '2',
                    memberSeq: '102',
                    studySeq: '1',
                    name: '이싸피',
                    content: '일단 지원합니다22',
                    created_time: '2023-01-04'
                },
                {
                    sequence: '3',
                    memberSeq: '103',
                    studySeq: '1',
                    name: '박싸피',
                    content:
                        '일단 지원합니다333 정말 긴 글로 지원하기 정말 긴 글로 지원하기 정말 긴 글로 지원하기 정말 긴 글로 지원하기 정말 긴 글로 지원하기 정말 긴 글로 지원하기 정말 긴 글로 지원하기 정말 긴 글로 지원하기 정말 긴 글로 지원하기 정말 긴 글로 지원하기 정말 긴 글로 지원하기 정말 긴 글로 지원하기 정말 긴 글로 지원하기 정말 긴 글로 지원하기 ',
                    created_time: '2023-01-04'
                },
                {
                    sequence: '4',
                    memberSeq: '104',
                    studySeq: '1',
                    name: '곽싸피',
                    content: '일단 지원합니다444',
                    created_time: '2023-01-04'
                }
            ],
            week_option: ['월', '화', '수', '목', '금', '토', '일'],
            frequency_num: 0,
            frequency_dialog: false,
            levelList: ['초보', '중급', '고급'],
            meetingList: ['온라인', '오프라인', '온/오프라인'],
            populationList: ['2', '3', '4', '5', '6', '7', '8', '9', '10'],
            name_rules: [value => !!value || '스터디 이름을 입력해주세요.'],
            introduction_rules: [value => !!value || '스터디 소개를 입력해주세요.'],
            startModal: false,
            endModal: false,
            nameCheckVal: false,
            uploadimageurl: null,

            showStudy: true, // 스터디 공개 여부 버튼
            checkName: false, // 이름 중복 여부 버튼
            memberOutClicked: false
        };
    },
    methods: {
        // 회원 탈퇴 확인 모달
        memberoutDialog(check) {
            if (check == 'open') {
                this.memberOutClicked = true;
            } else if (check == 'close') {
                this.memberOutClicked = false;
            } else if (check == 'confirm') {
                // 회원 탈퇴 api
            }
        },
        // 스터디 공개 여부 버튼
        showStudybtn() {
            this.showStudy = !this.showStudy;
        },
        // 스터디 공개 여부 버튼
        checkNamebtn() {
            this.checkName = !this.checkName;
        },
        //상위 카테고리에 맞게 하위 카테고리 변환
        async checkTopCategory() {
            await this.$store.dispatch('moduleStudy/getBottomCategories', this.study.topCategory.key);
        },
        async nameCheckBtn(name) {
            await this.$store.dispatch('moduleStudy/nameCheck', name);
            this.nameCheckVal = this.nameCheck;
        },
        createStudy() {
            if (
                this.study.name == '' ||
                this.study.introduction == '' ||
                this.study.population == '' ||
                this.study.level == '' ||
                this.study.topCategory == '' ||
                this.study.bottomCategory == '' ||
                this.study.frequency.length == 0 ||
                this.study.meeting == '' ||
                this.study.start_time == ''
            ) {
                console.log(this.study);
                alert('모든 항목을 입력해주세요.');
            } else if (this.nameCheckVal == false) {
                alert('스터디 이름 중복체크를 완료해주세요.');
            } else {
                const sendStudy = {
                    name: this.study.name,
                    introduction: this.study.introduction,
                    population: Number(this.study.population),
                    topCategory: this.study.topCategory.key,
                    bottomCategory: this.study.bottomCategory.key,
                    level: this.study.level,
                    meeting: this.study.meeting,
                    github: this.study.github,
                    notion: this.study.notion,
                    start_time: this.study.start_time,
                    frequency: this.study.frequency
                };

                //등록 요청 보내기.
                this.$store.dispatch('moduleStudy/createStudy', sendStudy);
                console.log(sendStudy);
            }
        },
        addFrequency() {
            this.frequency_num = this.frequency_num + 1;
            this.study.frequency = this.study.frequency.concat({
                week: this.temp_frequency.week,
                startTime: this.temp_frequency.startTime,
                endTime: this.temp_frequency.endTime
            });
            this.temp_frequency.week = null;
            this.temp_frequency.startTime = null;
            this.temp_frequency.endTime = null;
            this.frequency_dialog = false;
        },
        deleteFrequency(w, s, e) {
            this.frequency_num = this.frequency_num - 1;
            const tempStudy = this.study.frequency.filter(o => o.week !== w && o.startTime !== s && o.endTime !== e);
            this.study.frequency = tempStudy;
        }
    }
};
</script>
<style scoped>
.card_section_member {
    padding: 10px;
    /* margin-bottom: 1px; */
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
.card_section_applicant {
    padding: 30px;
    margin-bottom: 5px;
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
</style>
