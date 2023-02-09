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
                <v-row justify="center" align="center">
                    <v-btn text @click="mo()">스터디 이미지 수정하기</v-btn>
                </v-row>

                <v-row>
                    <v-col justify="center" align="center">
                        <div class="card_section" justify="center" align="center">
                            <!-- 카테고리 -->
                            <v-row>
                                <v-col cols="12" md="4">
                                    <v-subheader>카테고리</v-subheader>
                                </v-col>
                                <v-col cols="12" md="8"> </v-col>
                            </v-row>

                            <!-- 상세 -->
                            <v-row>
                                <v-col cols="12" md="4">
                                    <v-subheader>상세</v-subheader>
                                </v-col>
                                <v-col cols="12" md="8"> </v-col>
                            </v-row>

                            <!-- 모집인원 -->
                            <v-row>
                                <v-col cols="12" md="4">
                                    <v-subheader>모집인원</v-subheader>
                                </v-col>
                                <v-col cols="12" md="8"> </v-col>
                            </v-row>

                            <!-- LeveL -->
                            <v-row>
                                <v-col cols="12" md="4">
                                    <v-subheader>LeveL</v-subheader>
                                </v-col>
                                <v-col cols="12" md="8"> </v-col>
                            </v-row>

                            <!-- 활동주기 -->
                            <v-row>
                                <v-col cols="12" md="4">
                                    <v-subheader>활동주기</v-subheader>
                                </v-col>
                                <v-col cols="12" md="8"> </v-col>
                            </v-row>

                            <!-- 모임 -->
                            <v-row>
                                <v-col cols="12" md="4">
                                    <v-subheader>모임</v-subheader>
                                </v-col>
                                <v-col cols="12" md="8"> </v-col>
                            </v-row>

                            <v-row>
                                <v-col>
                                    <v-btn text @click="mo()">수정 완료</v-btn>
                                </v-col>
                            </v-row>
                        </div>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        <v-btn text @click="mo()">스터디 공개</v-btn>
                    </v-col>
                </v-row>
            </v-col>

            <!-- 본문 -->
            <v-col cols="12" md="8" :style="{padding: '4%'}">
                <!-- 스터디 이름/주소 수정 -->
                <v-row>
                    <v-col cols="12" md="6">
                        <h2>스터디 이름/주소</h2>
                        <hr width="60%" />
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12" md="6">
                        <h3>스터디 이름</h3>
                    </v-col>
                    <v-col cols="12" md="4">{{ study_name }}</v-col>
                    <v-col cols="12" md="2">
                        <v-btn>중복확인</v-btn>
                    </v-col>
                </v-row>
                <v-row justify="center" align="center">
                    <v-col cols="12" md="2" align="right">
                        <v-img :src="require('@/assets/git.png')" :style="{width: '40px'}" />
                    </v-col>
                    <v-col cols="12" md="4" justify="center" align="center">
                        {{ study_git }}
                    </v-col>
                    <v-col cols="12" md="2" align="right">
                        <v-img :src="require('@/assets/notion.png')" :style="{width: '40px'}" />
                    </v-col>
                    <v-col cols="12" md="4" justify="center" align="center">
                        {{ study_notion }}
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
                        <v-textarea label="스터디 소개하기" outlined rows="13"></v-textarea>
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
                                <v-col cols="12" md="2">
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
                                    <v-btn @click="needtochange" color="white" depressed>
                                        <span class="material-icons-outlined" :style="{color: 'crimson'}"> cancel </span>
                                    </v-btn>
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
                <v-row v-for="member in studyInfo.member" :key="member.sequence">
                    <v-col>
                        <div class="card_section_member">
                            <v-row justify="center" align="center">
                                <v-col cols="12" md="2">
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
                                    <v-btn @click="needtochange" color="white" depressed>
                                        <span class="material-icons-outlined" :style="{color: 'crimson'}"> cancel </span>
                                    </v-btn>
                                </v-col>
                            </v-row>
                        </div>
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
            study_name: '리엑트 용자 모임',
            study_git: 'https://lab.ssafy.com/s08-webmobile1-sub2/S08P12A104',
            study_notion: 'https://www.notion.so/',
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
            }
        };
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
.card_section {
    padding: 20px;
    margin-bottom: 5px;
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
</style>
