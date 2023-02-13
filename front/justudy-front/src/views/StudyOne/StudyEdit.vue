<template>
    <v-container>
        <v-row :style="{marginTop: '8%'}">
            <v-col>
                <h2>스터디 정보 수정</h2>
                <hr />
            </v-col>
        </v-row>

        <v-row>
            <v-col cols="12">
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
                                <v-text-field v-model="studyInfo.name" @input="nameCheckVal = false" label="스터디 이름" :rules="name_rules" hide-details="auto"></v-text-field>
                            </v-col>
                            <v-col class="center" cols="4">
                                <v-btn color="green darken-1" outlined text @click="nameCheckBtn(studyInfo.name)">
                                    중복체크
                                    <v-icon v-if="nameCheckVal" right> mdi-check-bold </v-icon>
                                    <v-icon v-if="!nameCheckVal" right> mdi-alert-circle-outline </v-icon>
                                </v-btn>
                            </v-col>
                        </v-row>

                        <!-- 스터디 소개 -->
                        <v-row>
                            <v-textarea outlined no-resize rows="15" label="스터디 소개" :rules="introduction_rules" v-model="studyInfo.introduction"></v-textarea>
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
                                <v-combobox
                                    v-model="studyInfo.topCategory"
                                    :items="topCategories"
                                    item-text="value"
                                    item-value="key"
                                    label="상위 카테고리 선택"
                                    @change="checkTopCategory()"
                                ></v-combobox>
                            </v-col>
                        </v-row>

                        <!-- 하위 카테고리 -->
                        <v-row>
                            <v-col cols="4">
                                <v-subheader>하위 카테고리</v-subheader>
                            </v-col>
                            <v-col cols="8">
                                <v-combobox v-model="studyInfo.bottomCategory" :items="bottomCategories" item-text="value" item-value="key" label="하위 카테고리 선택"></v-combobox>
                            </v-col>
                        </v-row>

                        <!-- 모집 인원 -->
                        <v-row>
                            <v-col cols="4">
                                <v-subheader>모집 인원</v-subheader>
                            </v-col>
                            <v-col cols="8">
                                <v-combobox v-model="studyInfo.population" :items="populationList" label="모집인원 선택"></v-combobox>
                            </v-col>
                        </v-row>

                        <!-- Level -->
                        <v-row>
                            <v-col cols="4">
                                <v-subheader>LEVEL</v-subheader>
                            </v-col>
                            <v-col cols="8">
                                <v-combobox v-model="studyInfo.level" :items="levelList" label="레벨 선택"></v-combobox>
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
                                    <v-col cols="6"> {{ studyInfo.frequency[i - 1].week }} / {{ studyInfo.frequency[i - 1].startTime }} ~ {{ studyInfo.frequency[i - 1].endTime }} </v-col>
                                    <v-col cols="6">
                                        <v-btn
                                            class="mx-2"
                                            fab
                                            dark
                                            x-small
                                            color="red"
                                            @click="deleteFrequency(studyInfo.frequency[i - 1].week, studyInfo.frequency[i - 1].startTime, studyInfo.frequency[i - 1].endTime)"
                                        >
                                            <v-icon dark> mdi-minus </v-icon>
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

                        <!-- 온/오프 -->
                        <v-row>
                            <v-col cols="4">
                                <v-subheader>모임</v-subheader>
                            </v-col>
                            <v-col cols="8">
                                <v-combobox v-model="studyInfo.meeting" :items="meetingList" label="모임 방식 선택"></v-combobox>
                            </v-col>
                        </v-row>

                        <!-- 시작예정일 -->
                        <v-row>
                            <v-col cols="4">
                                <v-subheader>시작 예정일</v-subheader>
                            </v-col>
                            <v-col cols="8">
                                <v-text-field v-model="studyInfo.startTime"></v-text-field>
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col cols="4">
                                <v-subheader>Github (선택)</v-subheader>
                            </v-col>
                            <v-col cols="8">
                                <v-text-field v-model="studyInfo.github"></v-text-field>
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col cols="4">
                                <v-subheader>Notion (선택)</v-subheader>
                            </v-col>
                            <v-col cols="8">
                                <v-text-field v-model="studyInfo.notion"></v-text-field>
                            </v-col>
                        </v-row>
                    </v-col>
                    <!-- 기본정보선택끝 -->
                </v-row>

                <!-- 등록 버튼 -->
                <v-row class="text-center">
                    <v-col cols="12">
                        <v-btn rounded color="#FFEB00" style="width: 200px" large @click="modifyStudy()">
                            <h2>수정 완료</h2>
                        </v-btn>
                    </v-col>
                </v-row>

                <!-- 스터디 삭제하기 -->
                <v-row>
                    <v-col cols="12" align="end">
                        <v-btn text @click="deleteStudy = true" :style="{color: 'crimson'}">
                            스터디 삭제하기
                            <span class="material-icons-outlined">logout</span>
                        </v-btn>
                    </v-col>
                </v-row>

                <!-- 스터디 삭제하기 모달 -->
                <v-dialog v-model="deleteStudy" width="800">
                    <v-card>
                        <v-card-title>스터디 삭제</v-card-title>
                        <v-card-text>스터디 삭제 시, 돌이킬 수 없습니다. 정말로 스터디를 삭제하시겠습니까?</v-card-text>
                        <v-divider></v-divider>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="primary" text @click="deleteMyStudy()">삭제</v-btn>
                            <v-btn color="primary" text @click="deleteStudy = false">취소</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import {mapState} from 'vuex';

export default {
    name: 'StudyEdit',
    computed: {
        ...mapState('moduleStudy', ['studyInfo']),
        ...mapState('moduleStudy', ['nameCheck']),
        ...mapState('moduleStudy', ['topCategories']),
        ...mapState('moduleStudy', ['bottomCategories'])
    },
    async created() {
        const studySeq = this.$route.params.studySeq;
        await this.$store.dispatch('moduleStudy/getStudyInfo', studySeq);
        if (this.studyInfo.isLeader == false) {
            window.location.href = '/error';
        }
        await this.$store.dispatch('moduleStudy/getTopCategories');

        this.frequency_num = this.studyInfo.frequency.length;
    },
    data() {
        return {
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
            populationList: ['2', '3', '4', '5', '6', '7', '8', '9', '10'],
            name_rules: [value => !!value || '스터디 이름을 입력해주세요.'],
            introduction_rules: [value => !!value || '스터디 소개를 입력해주세요.'],
            startModal: false,
            endModal: false,
            nameCheckVal: true,
            file: null,
            uploadImageFile: null,
            deleteStudy: false
        };
    },
    methods: {
        async checkTopCategory() {
            await this.$store.dispatch('moduleStudy/getBottomCategories', this.studyInfo.topCategory.key);
        },
        async nameCheckBtn(name) {
            await this.$store.dispatch('moduleStudy/nameCheck', name);
            this.nameCheckVal = this.nameCheck;
        },
        async modifyStudy() {
            if (
                this.studyInfo.name == '' ||
                this.studyInfo.introduction == '' ||
                this.studyInfo.population == '' ||
                this.studyInfo.level == '' ||
                this.studyInfo.topCategory == '' ||
                this.studyInfo.bottomCategory == '' ||
                this.studyInfo.frequency.length == 0 ||
                this.studyInfo.meeting == '' ||
                this.studyInfo.startTime == ''
            ) {
                console.log(this.studyInfo);
                alert('모든 항목을 입력해주세요.');
            } else if (this.nameCheckVal == false) {
                alert('스터디 이름 중복체크를 완료해주세요.');
            } else {
                const sendStudy = {
                    name: this.studyInfo.name,
                    introduction: this.studyInfo.introduction,
                    population: Number(this.studyInfo.population),
                    topCategory: this.studyInfo.topCategory.key,
                    bottomCategory: this.studyInfo.bottomCategory.key,
                    level: this.studyInfo.level,
                    meeting: this.studyInfo.meeting,
                    github: this.studyInfo.github,
                    notion: this.studyInfo.notion,
                    startTime: this.studyInfo.startTime,
                    frequency: this.studyInfo.frequency
                };

                const formData = new FormData();
                formData.append('file', this.file);
                formData.append(
                    'request',
                    new Blob([JSON.stringify(sendStudy)], {
                        type: 'application/json'
                    })
                );

                console.log('보내는 내용', sendStudy);
                //등록 요청 보내기.
                await this.$store.dispatch('moduleStudy/modifyStudy', {
                    formData: formData,
                    seq: this.studyInfo.sequence
                });
                console.log(this.file);
            }
        },
        addFrequency() {
            this.frequency_num = this.frequency_num + 1;
            this.studyInfo.frequency = this.studyInfo.frequency.concat({
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
            const tempStudy = this.studyInfo.frequency.filter(o => o.week !== w && o.startTime !== s && o.endTime !== e);
            this.studyInfo.frequency = tempStudy;
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
        },
        //스터디 삭제하기
        async deleteMyStudy() {
            await this.$store.dispatch('moduleStudy/deleteMyStudy', this.studyInfo.sequence);
            this.deleteStudy = false;
        }
    }
};
</script>
