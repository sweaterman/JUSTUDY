<template>
    <v-app>
        <!-- 기본 정보 선택 부분 -->
        <v-row>
            <v-col cols="12" md="6">
                <div>이미지 등록 부분</div>
                <v-combobox v-model="top_category" :items="topCategoryList" label="상위 카테고리 선택" @change="checkTopCategory()"></v-combobox>
                <v-combobox v-model="bottom_category" :items="bottomCategoryList" label="하위 카테고리 선택" multiple></v-combobox>
            </v-col>

            <v-col cols="12" md="6">
                <v-text-field label="스터디 이름" :rules="name_rules" hide-details="auto"></v-text-field>
                <v-textarea
                    rows="13"
                    label="스터디 소개"
                    :rules="intro_rules"
                    value="스터디 모집글을 아래 양식을 참고해 작성해주세요.

꼼꼼히 작성하면 멋진 스터디 팀원을 만나실 수 있을거예요.
[개발 스터디 모집 내용 예시]
스터디 주제 :
스터디 목표 :s
예상 커리큘럼 간략히 :
스터디 소개와 개설 이유 :
스터디 관련 주의사항 :"
                ></v-textarea>
            </v-col>
        </v-row>

        <!-- 스터디 이름, 소개 작성란 -->

        <!-- 스터디 생성버튼 눌렀을 때 모든 조건 확인하기 (입력되었는지) -->
    </v-app>
</template>

<script>
export default {
    name: 'CreateStudyView',

    data() {
        return {
            top_category: '',
            bottom_category: '',
            name_rules: [value => !!value || '스터디 이름을 입력해주세요.'],
            intro_rules: [value => !!value || '스터디 소개를 입력해주세요.'],
            topCategoryList: ['프론트엔드', '백엔드', '모바일', '알고리즘', 'CS', '기타'],
            bottomCategoryList: []
        };
    },
    methods: {
        checkTopCategory() {
            if (this.top_category == null) {
                this.bottomCategoryList = null;
            } else if (this.top_category == '프론트엔드') {
                this.bottomCategoryList = ['Vue.js', 'Node.js'];
            }
        }
    }
};
</script>
