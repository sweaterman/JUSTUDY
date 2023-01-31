<template>
    <v-app>
        <v-container>
            <v-row style="margin-top: 20px">
                <!-- 왼쪽 여백 -->
                <v-col cols="0" md="2"></v-col>

                <!-- 본문 -->
                <v-col cols="12" md="8">
                    <v-row>
                        <v-col cols="12" md="6">
                            <!-- 이미지 등록 부분 (후추 수정) -->
                            <v-row>
                                <v-col class="center" cols="12">
                                    <!-- <v-img v-model="uploadimageurl" src="uploadimageurl.url" contain height="270px" width="480px" style="border: 2px solid black" /> -->
                                    <img style="width: 95%" src="@/assets/test_study.jpg" alt="study_image" />
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col class="center" cols="12">
                                    <v-file-input class="input" type="file" counter show-size label="이미지 제출" outlined dense prepend-icon="mdi-camera" style="width: 400px" />
                                </v-col>
                            </v-row>

                            <!-- 스터디 이름 -->
                            <v-row>
                                <v-col class="center" cols="8">
                                    <v-text-field v-model="study.name" label="스터디 이름" :rules="name_rules" hide-details="auto"></v-text-field>
                                </v-col>
                                <v-col class="center" cols="4">
                                    <v-btn color="green darken-1" outlined text @click="nameCheck(study.name)"> 중복체크 </v-btn>
                                </v-col>
                            </v-row>

                            <!-- 스터디 소개 -->
                            <v-row>
                                <v-textarea
                                    outlined
                                    no-resize
                                    rows="15"
                                    label="스터디 소개"
                                    :rules="introduction_rules"
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

                        <!-- 기본 정보 선택 부분 -->
                        <v-col cols="12" md="6" style="padding: 3em">
                            <!-- 팀장 -->
                            <v-row>
                                <v-col cols="4">
                                    <v-subheader no-gutters>팀장</v-subheader>
                                </v-col>
                                <v-col cols="8">
                                    <v-text-field v-model="leader" solo readonly></v-text-field>
                                </v-col>
                            </v-row>

                            <!-- 상위 카테고리 -->
                            <v-row>
                                <v-col cols="4">
                                    <v-subheader>상위 카테고리</v-subheader>
                                </v-col>
                                <v-col cols="8">
                                    <v-combobox v-model="study.topCategory" :items="topCategories" label="상위 카테고리 선택" @change="checkTopCategory()"></v-combobox>
                                </v-col>
                            </v-row>

                            <!-- 하위 카테고리 -->
                            <v-row>
                                <v-col cols="4">
                                    <v-subheader>하위 카테고리</v-subheader>
                                </v-col>
                                <v-col cols="8">
                                    <v-combobox v-model="study.bottomCategory" :items="bottomCategories" label="하위 카테고리 선택"></v-combobox>
                                </v-col>
                            </v-row>

                            <!-- 모집 인원 -->
                            <v-row>
                                <v-col cols="4">
                                    <v-subheader>모집 인원</v-subheader>
                                </v-col>
                                <v-col cols="8">
                                    <v-combobox v-model="study.population" :items="populationList" label="모집인원 선택"></v-combobox>
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
                                            <v-btn-toggle v-model="study.frequency.frequency_week" multiple>
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
                                            <v-dialog ref="dialog" v-model="startModal" :return-value.sync="study.frequency.frequency_start" persistent width="290px">
                                                <template v-slot:activator="{on, attrs}">
                                                    <v-text-field
                                                        v-model="study.frequency.frequency_start"
                                                        label="시작 시간"
                                                        prepend-icon="mdi-clock-time-four-outline"
                                                        readonly
                                                        v-bind="attrs"
                                                        v-on="on"
                                                    ></v-text-field>
                                                </template>
                                                <v-time-picker v-if="startModal" v-model="study.frequency.frequency_start" full-width>
                                                    <v-spacer></v-spacer>
                                                    <v-btn text color="primary" @click="startModal = false"> 취소 </v-btn>
                                                    <v-btn text color="primary" @click="$refs.dialog.save(study.frequency.frequency_start)"> 확인 </v-btn>
                                                </v-time-picker>
                                            </v-dialog>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-dialog ref="dialog2" v-model="endModal" :return-value.sync="study.frequency.frequency_end" persistent width="290px">
                                                <template v-slot:activator="{on, attrs}">
                                                    <v-text-field
                                                        v-model="study.frequency.frequency_end"
                                                        label="끝나는 시간"
                                                        prepend-icon="mdi-clock-time-four-outline"
                                                        readonly
                                                        v-bind="attrs"
                                                        v-on="on"
                                                    ></v-text-field>
                                                </template>
                                                <v-time-picker v-if="endModal" v-model="study.frequency.frequency_end" full-width>
                                                    <v-spacer></v-spacer>
                                                    <v-btn text color="primary" @click="endModal = false"> 취소 </v-btn>
                                                    <v-btn text color="primary" @click="$refs.dialog2.save(study.frequency.frequency_end)"> 확인 </v-btn>
                                                </v-time-picker>
                                            </v-dialog>
                                        </v-col>
                                    </v-row>
                                </v-col>
                            </v-row>

                            <!-- 온/오프 -->
                            <v-row>
                                <v-col cols="4">
                                    <v-subheader>모임</v-subheader>
                                </v-col>
                                <v-col cols="8">
                                    <v-combobox v-model="study.meeting" :items="meetingList" label="모임 방식 선택"></v-combobox>
                                </v-col>
                            </v-row>

                            <v-row>
                                <v-col cols="4">
                                    <v-subheader>Github (선택)</v-subheader>
                                </v-col>
                                <v-col cols="8">
                                    <v-text-field v-model="study.github"></v-text-field>
                                </v-col>
                            </v-row>

                            <v-row>
                                <v-col cols="4">
                                    <v-subheader>Notion (선택)</v-subheader>
                                </v-col>
                                <v-col cols="8">
                                    <v-text-field v-model="study.notion"></v-text-field>
                                </v-col>
                            </v-row>
                        </v-col>
                        <!-- 기본정보선택끝 -->
                    </v-row>

                    <!-- 등록 버튼 -->
                    <v-row class="text-center">
                        <v-col cols="12">
                            <v-btn rounded color="#FFEB00" style="width: 200px" large @click="createStudy()">
                                <h2>등록 완료하기</h2>
                            </v-btn>
                        </v-col>
                    </v-row>
                </v-col>
            </v-row>

            <!-- 오른쪽 여백 -->
            <v-col cols="0" md="2"></v-col>
        </v-container>
    </v-app>
</template>

<script>
import {mapState} from 'vuex';

export default {
    name: 'CreateStudyView',
    computed: {
        ...mapState(['nameCheck']),
        ...mapState(['topCategories']),
        ...mapState(['bottomCategories'])
    },
    created() {
        this.$store.dispatch('getTopCategories');
    },
    data() {
        return {
            study: {
                name: '',
                introduction: 'test',
                population: '',
                leader: '',
                level: '',
                topCategory: '',
                bottomCategory: '',
                frequency: {
                    frequency_week: '',
                    frequency_start: '',
                    frequency_end: ''
                },
                meeting: '',
                github: '',
                notion: '',
                start_time: ''
            },
            levelList: ['초보', '중급', '고급'],
            meetingList: ['온라인', '오프라인', '온/오프라인'],
            leader: '김싸피',
            populationList: ['2', '3', '4', '5', '6', '7', '8', '9', '10'],
            name_rules: [value => !!value || '스터디 이름을 입력해주세요.'],
            introduction_rules: [value => !!value || '스터디 소개를 입력해주세요.'],
            startModal: false,
            endModal: false,
            nameCheckVal: true,
            uploadimageurl: null
        };
    },
    methods: {
        //상위 카테고리에 맞게 하위 카테고리 변환
        checkTopCategory() {
            this.$store.dispatch('getBottomCategories');
        },
        nameCheckBtn(name) {
            this.$store.dispatch('nameCheck', name);
            //중복체크 미완성 -> 확인 해봐야함.
            this.nameCheckVal = this.$store.state.nameCheck;
        },
        createStudy() {
            if (
                this.study.name == '' ||
                this.study.introduction == '' ||
                this.study.population == '' ||
                this.study.level == '' ||
                this.study.topCategory == '' ||
                this.study.bottomCategory == '' ||
                this.study.frequency.frequency_week == '' ||
                this.study.frequency.frequency_start == '' ||
                this.study.frequency.frequency_end == '' ||
                this.study.meeting == ''
            ) {
                console.log(this.study);
                alert('모든 항목을 입력해주세요.');
            } else if (this.nameCheckVal == false) {
                alert('스터디 이름 중복체크를 완료해주세요.');
            } else {
                //등록 요청 보내기.
                this.$store.dispatch('createStudy');
            }
        }
    }
};
</script>

<style scoped>
.center {
    display: flex;
    align-items: center;
    justify-content: center;
}
</style>
