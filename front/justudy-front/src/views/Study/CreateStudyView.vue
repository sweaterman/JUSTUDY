<template>
    <v-app>
        <YellowButton :buttonLength="250" :height="70" :fontSize="20" :content="`스터디 생성하기`" standard="px" />

        <v-row>
            <!-- 기본 정보 선택 부분 -->
            <v-col cols="12" md="4" style="padding: 4em">
                <!-- 이미지 등록 -->

                <!-- 팀장 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>팀장</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-text-field v-model="teamLeader" solo readonly></v-text-field>
                    </v-col>
                </v-row>

                <!-- 상위 카테고리 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>상위 카테고리</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-combobox v-model="study.top_category" :items="categoryList.top" label="상위 카테고리 선택" @change="checkTopCategory()"></v-combobox>
                    </v-col>
                </v-row>

                <!-- 하위 카테고리 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>하위 카테고리</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-combobox v-model="study.bottom_category" :items="categoryList.bottom" label="하위 카테고리 선택" multiple></v-combobox>
                    </v-col>
                </v-row>

                <!-- 모집 인원 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>모집 인원</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-combobox v-model="study.personnel" :items="personnelList" label="모집인원 선택"></v-combobox>
                    </v-col>
                </v-row>

                <!-- Level -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>LEVEL</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-combobox v-model="study.level" :items="levelList" label="레벨 선택"></v-combobox>
                    </v-col>
                </v-row>

                <!-- 활동 주기 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>활동 주기</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-row>
                            <v-col cols="12">
                                <v-btn-toggle v-model="study.week" multiple>
                                    <v-btn rounded color="primary"> 월 </v-btn>
                                    <v-btn rounded color="primary"> 화 </v-btn>
                                    <v-btn rounded color="primary"> 수 </v-btn>
                                    <v-btn rounded color="primary"> 목 </v-btn>
                                    <v-btn rounded color="primary"> 금 </v-btn>
                                    <v-btn rounded color="primary"> 토 </v-btn>
                                    <v-btn rounded color="primary"> 일 </v-btn>
                                </v-btn-toggle>
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col cols="6">
                                <v-dialog ref="dialog" v-model="startModal" :return-value.sync="study.startTime" persistent width="290px">
                                    <template v-slot:activator="{on, attrs}">
                                        <v-text-field v-model="study.startTime" label="시작 시간" prepend-icon="mdi-clock-time-four-outline" readonly v-bind="attrs" v-on="on"></v-text-field>
                                    </template>
                                    <v-time-picker v-if="startModal" v-model="study.startTime" full-width>
                                        <v-spacer></v-spacer>
                                        <v-btn text color="primary" @click="startModal = false"> 취소 </v-btn>
                                        <v-btn text color="primary" @click="$refs.dialog.save(study.startTime)"> 확인 </v-btn>
                                    </v-time-picker>
                                </v-dialog>
                            </v-col>
                            <v-col cols="6">
                                <v-dialog ref="dialog2" v-model="endModal" :return-value.sync="study.endTime" persistent width="290px">
                                    <template v-slot:activator="{on, attrs}">
                                        <v-text-field v-model="study.endTime" label="끝나는 시간" prepend-icon="mdi-clock-time-four-outline" readonly v-bind="attrs" v-on="on"></v-text-field>
                                    </template>
                                    <v-time-picker v-if="endModal" v-model="study.endTime" full-width>
                                        <v-spacer></v-spacer>
                                        <v-btn text color="primary" @click="endModal = false"> 취소 </v-btn>
                                        <v-btn text color="primary" @click="$refs.dialog2.save(study.endTime)"> 확인 </v-btn>
                                    </v-time-picker>
                                </v-dialog>
                            </v-col>
                        </v-row>
                    </v-col>
                </v-row>

                <v-row>
                    <v-col cols="4"> </v-col>
                    <v-col cols="8">
                        {{ study.week }}
                        {{ study.startTime }}
                        {{ study.endTime }}
                    </v-col>
                </v-row>

                <!-- 온/오프 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>모임</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-combobox v-model="study.on_off" :items="on_offList" label="모임 방식 선택"></v-combobox>
                    </v-col>
                </v-row>
            </v-col>

            <!-- 스터디 이름, 소개 작성란 -->
            <v-col cols="12" md="8" style="padding: 4em">
                <v-row>
                    <v-col cols="8">
                        <v-text-field v-model="study.name" label="스터디 이름" :rules="name_rules" hide-details="auto"></v-text-field>
                    </v-col>
                    <v-col cols="4">
                        <v-btn color="green darken-1" outlined text @click="nameCheck(study.name)"> 중복체크 </v-btn>
                    </v-col>
                </v-row>

                <v-row>
                    <v-textarea
                        outlined
                        rows="15"
                        label="스터디 소개"
                        :rules="intro_rules"
                        value="스터디 모집글을 아래 양식을 참고해 작성해주세요.

꼼꼼히 작성하면 멋진 스터디 팀원을 만나실 수 있을거예요.
[개발 스터디 모집 내용 예시]
스터디 주제 :
스터디 목표 :
예상 커리큘럼 간략히 :
스터디 소개와 개설 이유 :
스터디 관련 주의사항 :"
                    ></v-textarea>
                </v-row>
            </v-col>
        </v-row>

        <div class="text-center">
            <v-btn rounded color="primary" dark @click="createStudy()"> 등록 </v-btn>
            <v-btn rounded color="primary" dark @click="cancel()"> 취소 </v-btn>
        </div>
    </v-app>
</template>

<script>
import {mapState} from 'vuex';
import YellowButton from '../../components/common/YellowButton.vue';

export default {
    name: 'CreateStudyView',
    components: {
        YellowButton
    },
    computed: {
        ...mapState(['nameCheck', 'loginUser'])
    },
    created() {},
    data() {
        return {
            study: {
                name: '',
                intro: 'test',
                personnel: '',
                teamLeader: '',
                level: '',
                top_category: '',
                bottom_category: '',
                week: '',
                startTime: '',
                endTime: '',
                on_off: ''
            },
            categoryList: {
                top: ['프론트엔드', '백엔드', '모바일', '알고리즘', 'CS', '기타'],
                bottom: []
            },
            levelList: ['초보', '중급', '고급'],
            on_offList: ['온라인', '오프라인', '온/오프라인'],
            teamLeader: '김싸피',
            personnelList: ['2', '3', '4', '5', '6', '7', '8', '9', '10'],
            name_rules: [value => !!value || '스터디 이름을 입력해주세요.'],
            intro_rules: [value => !!value || '스터디 소개를 입력해주세요.'],
            startModal: false,
            endModal: false,
            nameCheckVal: true
        };
    },
    methods: {
        checkTopCategory() {
            if (this.study.top_category == null) {
                this.categoryList.bottom = null;
            } else if (this.study.top_category == '프론트엔드') {
                this.categoryList.bottom = ['Vue.js', 'Node.js'];
            } else {
                this.categoryList.bottom = null;
            }
        },
        nameCheckBtn(name) {
            this.$store.dispatch('nameCheck', name);
            //중복체크 미완성 -> 확인 해봐야함.
            this.nameCheckVal = this.$store.state.nameCheck;
        },
        createStudy() {
            if (
                this.study.name == '' ||
                this.study.intro == '' ||
                this.study.personnel == '' ||
                this.study.level == '' ||
                this.study.top_category == '' ||
                this.study.bottom_category == '' ||
                this.study.week == '' ||
                this.study.startTime == '' ||
                this.study.endTime == '' ||
                this.study.on_off == ''
            ) {
                console.log(this.study);
                alert('모든 항목을 입력해주세요.');
            } else if (this.nameCheckVal == false) {
                alert('스터디 이름 중복체크를 완료해주세요.');
            } else {
                alert('등록이 완료되었습니다.');
                //등록 요청 보내기.
            }
        },
        cancel() {
            //원래 페이지로 돌아가기
        }
    }
};
</script>
