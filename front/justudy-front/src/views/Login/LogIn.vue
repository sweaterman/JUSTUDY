<template>
    <v-app>
        <BasicButton :buttonLength="250" :height="70" :fontSize="20" :content="`스터디 생성하기`" standard="px" />

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
                        <v-combobox v-model="top_category" :items="topCategoryList" label="상위 카테고리 선택" @change="checkTopCategory()"></v-combobox>
                    </v-col>
                </v-row>

                <!-- 하위 카테고리 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>하위 카테고리</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-combobox v-model="bottom_category" :items="bottomCategoryList" label="하위 카테고리 선택" multiple></v-combobox>
                    </v-col>
                </v-row>

                <!-- 모집 인원 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>모집 인원</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-combobox v-model="personnel" :items="personnelList" label="모집인원 선택"></v-combobox>
                    </v-col>
                </v-row>

                <!-- Level -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>LEVEL</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-combobox v-model="level" :items="levelList" label="레벨 선택"></v-combobox>
                    </v-col>
                </v-row>

                <!-- 활동 주기 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>활동 주기</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <div>미완</div>
                    </v-col>
                </v-row>

                <!-- 온/오프 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>모임</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-combobox v-model="on_off" :items="on_offList" label="모임 방식 선택"></v-combobox>
                    </v-col>
                </v-row>
            </v-col>

            <!-- 스터디 이름, 소개 작성란 -->
            <v-col cols="12" md="8" style="padding: 4em">
                <v-text-field label="스터디 이름" :rules="name_rules" hide-details="auto"></v-text-field>
                <br />
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
            </v-col>
        </v-row>

        <!-- 스터디 생성버튼 눌렀을 때 모든 조건 확인하기 (입력되었는지) -->
    </v-app>
</template>

<script>
import BasicButton from '../../components/common/BasicButton.vue';

export default {
    name: 'CreateStudyView',
    components: {
        BasicButton
    },

    data() {
        return {
            teamLeader: '김싸피',
            top_category: '',
            bottom_category: '',
            name_rules: [value => !!value || '스터디 이름을 입력해주세요.'],
            intro_rules: [value => !!value || '스터디 소개를 입력해주세요.'],
            topCategoryList: ['프론트엔드', '백엔드', '모바일', '알고리즘', 'CS', '기타'],
            bottomCategoryList: [],
            personnelList: ['2', '3', '4', '5', '6', '7', '8', '9', '10'],
            levelList: ['초보', '중급', '고급'],
            on_offList: ['온라인', '오프라인', '온/오프라인']
        };
    },
    methods: {
        checkTopCategory() {
            if (this.top_category == null) {
                this.bottomCategoryList = null;
            } else if (this.top_category == '프론트엔드') {
                this.bottomCategoryList = ['Vue.js', 'Node.js'];
            } else {
                this.bottomCategoryList = null;
            }
        }
    }
};
</script>
