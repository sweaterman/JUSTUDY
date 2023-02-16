<template>
    <v-container>
        <v-row style="margin-top: 20px">
            <!-- 왼쪽 여백 -->
            <v-col cols="0" md="2"></v-col>

            <!-- 본문 -->
            <v-col cols="12" md="8">
                <v-row>
                    <v-col cols="12" md="6">
                        <!-- 이미지 preview -->
                        <v-row>
                            <v-col class="center" cols="12">
                                <img style="width: 95%" :src="uploadImageFile" />
                            </v-col>
                        </v-row>

                        <!-- 이미지 업로드 -->
                        <v-row>
                            <v-col cols="12" align="center">
                                <v-file-input v-model="file" show-size label="이미지 선택" @change="onFileSelected(file)"></v-file-input>
                                <!-- <input type="file" onchange="readURL(this);" /> -->
                            </v-col>
                        </v-row>

                        <!-- 스터디 이름 -->
                        <v-row>
                            <v-col class="center" cols="8">
                                <v-text-field v-model="study.name" @input="nameCheckVal = false" label="스터디 이름" :rules="name_rules" hide-details="auto"></v-text-field>
                            </v-col>
                            <v-col class="center" cols="4">
                                <v-btn color="green darken-1" outlined text @click="nameCheckBtn(study.name)">
                                    중복체크
                                    <v-icon v-if="nameCheckVal" right>mdi-check-bold</v-icon>
                                    <v-icon v-if="!nameCheckVal" right>mdi-alert-circle-outline</v-icon>
                                </v-btn>
                            </v-col>
                        </v-row>

                        <!-- 스터디 소개 -->
                        <v-row>
                            <v-textarea
                                outlined
                                no-resize
                                rows="15"
                                label="스터디 소개"
                                v-model="study.introduction"
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
                        <!-- 상위 카테고리 -->
                        <v-row>
                            <v-col cols="4">
                                <v-subheader>상위 카테고리</v-subheader>
                            </v-col>
                            <v-col cols="8">
                                <v-select v-model="study.topCategory" :items="topCategories" item-text="value" item-value="key" label="상위 카테고리 선택" @change="checkTopCategory()"></v-select>
                            </v-col>
                        </v-row>

                        <!-- 하위 카테고리 -->
                        <v-row>
                            <v-col cols="4">
                                <v-subheader>하위 카테고리</v-subheader>
                            </v-col>
                            <v-col cols="8">
                                <v-select v-model="study.bottomCategory" :items="bottomCategories" item-text="value" item-value="key" label="하위 카테고리 선택"></v-select>
                            </v-col>
                        </v-row>

                        <!-- 모집 인원 -->
                        <v-row>
                            <v-col cols="4">
                                <v-subheader>모집 인원</v-subheader>
                            </v-col>
                            <v-col cols="8">
                                <v-select v-model="study.population" :items="populationList" label="모집인원 선택"></v-select>
                            </v-col>
                        </v-row>

                        <!-- Level -->
                        <v-row>
                            <v-col cols="4">
                                <v-subheader>LEVEL</v-subheader>
                            </v-col>
                            <v-col cols="8">
                                <v-select v-model="study.level" :items="levelList" label="레벨 선택"></v-select>
                            </v-col>
                        </v-row>

                        <!-- 활동 주기 -->
                        <v-row>
                            <v-col cols="4">
                                <v-subheader>활동 주기</v-subheader>
                            </v-col>
                            <v-col cols="8">
                                <!-- 활동주기 확인/삭제하는 v-for -->
                                <v-row v-for="i in frequency_num" :key="i">
                                    <v-col cols="6">{{ study.frequency[i - 1].week }} / {{ study.frequency[i - 1].startTime }} ~ {{ study.frequency[i - 1].endTime }}</v-col>
                                    <v-col cols="6">
                                        <v-btn
                                            class="mx-2"
                                            fab
                                            dark
                                            x-small
                                            color="red"
                                            @click="deleteFrequency(study.frequency[i - 1].week, study.frequency[i - 1].startTime, study.frequency[i - 1].endTime)"
                                        >
                                            <v-icon dark>mdi-minus</v-icon>
                                        </v-btn>
                                    </v-col>
                                </v-row>

                                <!-- 활동주기 추가하는 Dialog -->
                                <v-row>
                                    <v-col cols="12">
                                        <v-btn @click="frequency_dialog = true">활동주기 추가</v-btn>
                                    </v-col>
                                </v-row>

                                <!-- 활동주기 모달 -->
                                <v-dialog v-model="frequency_dialog" width="800">
                                    <v-card>
                                        <v-card-title>활동 주기 추가</v-card-title>

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
                                            <v-btn color="primary" text @click="addFrequency()">확인</v-btn>
                                            <v-btn color="primary" text @click="frequency_dialog = false">취소</v-btn>
                                        </v-card-actions>
                                    </v-card>
                                </v-dialog>
                            </v-col>
                        </v-row>

                        <!-- 온/오프 -->
                        <v-row>
                            <v-col cols="4">
                                <v-subheader>모임</v-subheader>
                            </v-col>
                            <v-col cols="8">
                                <v-select v-model="study.meeting" :items="meetingList" label="모임 방식 선택"></v-select>
                            </v-col>
                        </v-row>

                        <!-- 시작예정일 -->
                        <v-row>
                            <v-col cols="4">
                                <v-subheader>시작 예정일</v-subheader>
                            </v-col>
                            <v-col cols="8">
                                <v-text-field v-model="study.startTime" hint="ex) 2월 초 쯤 시작"></v-text-field>
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
</template>

<script>
import {mapState} from 'vuex';

export default {
    name: 'CreateStudyView',
    computed: {
        ...mapState('moduleStudy', ['nameCheck']),
        ...mapState('moduleStudy', ['topCategories']),
        ...mapState('moduleStudy', ['bottomCategories'])
    },
    created() {
        this.$store.dispatch('moduleStudy/getTopCategories');
    },
    data() {
        return {
            study: {
                name: '',
                introduction: '',
                population: '',
                level: '',
                topCategory: '',
                bottomCategory: '',
                frequency: [],
                meeting: '',
                github: '',
                notion: '',
                startTime: ''
            },
            temp_frequency: {
                week: null,
                startTime: null,
                endTime: null
            },
            week_option: ['월', '화', '수', '목', '금', '토', '일'],
            frequency_num: 0,
            frequency_dialog: false,
            levelList: ['초보', '중급', '고급'],
            meetingList: ['온라인', '오프라인', '온/오프라인'],
            populationList: [2, 3, 4, 5, 6, 7, 8, 9, 10],
            name_rules: [value => !!value || '스터디 이름을 입력해주세요.'],
            introduction_rules: [value => !!value || '스터디 소개를 입력해주세요.'],
            startModal: false,
            endModal: false,
            nameCheckVal: false,
            file: null,
            uploadImageFile: null
        };
    },
    methods: {
        //상위 카테고리에 맞게 하위 카테고리 변환
        async checkTopCategory() {
            await this.$store.dispatch('moduleStudy/getBottomCategories', this.study.topCategory);
        },
        async nameCheckBtn(name) {
            await this.$store.dispatch('moduleStudy/nameCheck', name);
            this.nameCheckVal = this.nameCheck;
        },
        async createStudy() {
            if (
                this.study.name == '' ||
                this.study.introduction == '' ||
                this.study.population == '' ||
                this.study.level == '' ||
                this.study.topCategory == '' ||
                this.study.bottomCategory == '' ||
                this.study.frequency.length == 0 ||
                this.study.meeting == '' ||
                this.study.startTime == ''
            ) {
                console.log(this.study);
                alert('모든 항목을 입력해주세요.');
            } else if (this.nameCheckVal == false) {
                alert('스터디 이름 중복체크를 완료해주세요.');
            } else {
                const sendStudy = this.study;
                const formData = new FormData();
                formData.append('file', this.file);
                formData.append(
                    'request',
                    new Blob([JSON.stringify(sendStudy)], {
                        type: 'application/json'
                    })
                );

                //등록 요청 보내기.
                await this.$store.dispatch('moduleStudy/createStudy', {formData: formData});
                console.log(this.file);
            }
        },
        addFrequency() {
            if (this.temp_frequency.week == null || this.temp_frequency.startTime == null || this.temp_frequency.endTime == null) {
                alert('모든 항목을 선택해주세요!');
            } else {
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
            }
        },
        deleteFrequency(w, s, e) {
            this.frequency_num = this.frequency_num - 1;
            const tempStudy = this.study.frequency.filter(o => o.week !== w && o.startTime !== s && o.endTime !== e);
            this.study.frequency = tempStudy;
        },
        onFileSelected(file) {
            const fileData = data => {
                this.uploadImageFile = data;
            };
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.addEventListener(
                'load',
                function () {
                    fileData(reader.result);
                },
                false
            );
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
