<template>
    <v-app>
        <!-- 제목 ~ 회원 가입 -->
        <v-row :style="{marginTop: '5%'}">
            <v-col cols="12" md="4" />
            <v-col cols="12" md="4" justify="center" align="center">
                <TextButton :buttonLength="100" :height="70" :fontSize="10" :content="`회원가입`" standard="px" />
            </v-col>
            <v-col cols="12" md="4" />
        </v-row>

        <!-- <v-row>
            <TextBoundary content="필수 입력 사항" standard="px" :startPoint="500"></TextBoundary>
        </v-row> -->

        <v-row>
            <!-- 좌측 여백 -->
            <v-col cols="12" md="3" style="padding: 4em" />

            <!-- 기본 정보 선택 부분 -->
            <v-col cols="12" md="6" style="padding: 4em">
                <!-- 이미지 등록 -->

                <!-- 이름 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>이름</v-subheader>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field v-model="user.username" dense outlined label="이름" :rules="[v => !!v || '이름은 필수입니다.']"></v-text-field>
                    </v-col>
                </v-row>

                <!-- 닉네임 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>닉네임</v-subheader>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field v-model="user.nickname" dense outlined label="닉네임" :rules="[v => !!v || '별명은 필수입니다.']"></v-text-field>
                    </v-col>
                    <v-col cols="2">
                        <v-btn color="yellow" @click="movetoboard1('nickName')" :style="{fontWeight: 'bold', fontSize: 'large'}">중복 확인</v-btn>
                    </v-col>
                </v-row>

                <!-- SSAFY 학번 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>SSAFY 학번</v-subheader>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field v-model="user.ssafyId" dense outlined label="SSAFY 학번" :rules="[v => !!v || 'SSAFY 학번은 필수입니다.']"></v-text-field>
                    </v-col>
                </v-row>

                <!-- 아이디 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>아이디</v-subheader>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field v-model="user.userId" dense outlined label="아이디" :rules="[v => !!v || '아이디는 필수입니다.']"></v-text-field>
                    </v-col>
                    <v-col cols="2">
                        <v-btn color="yellow" @click="movetoboard1('id')" :style="{fontWeight: 'bold', fontSize: 'large'}">중복 확인</v-btn>
                    </v-col>
                </v-row>

                <!-- 비밀번호 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>비밀번호</v-subheader>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field v-model="user.password" dense outlined label="비밀번호" :rules="[v => !!v || '비밀번호는 필수입니다.']"></v-text-field>
                    </v-col>
                </v-row>

                <!-- 비밀번호 재확인 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>비밀번호 재확인</v-subheader>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field v-model="user.passwordCheck" dense outlined label="비밀번호 재확인" :rules="[v => !!v || '비밀번호가 다릅니다.']"></v-text-field>
                    </v-col>
                </v-row>

                <!-- 전화번호 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>전화번호</v-subheader>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field v-model="user.phone" dense outlined label="전화번호" :rules="[v => !!v || '전화번호는 필수입니다.']"></v-text-field>
                    </v-col>
                </v-row>

                <!-- 이메일 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>이메일</v-subheader>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field v-model="user.email" dense outlined label="이메일" :rules="[v => !!v || '이메일은 필수입니다.']"></v-text-field>
                    </v-col>
                </v-row>

                <!-- MatterMost 인증 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>MatterMost 인증하기</v-subheader>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field v-model="user.mmid" dense outlined label="MatterMost 아이디" :rules="[v => !!v || 'MM 인증은 필수입니다.']"></v-text-field>
                    </v-col>
                    <v-col cols="2">
                        <v-btn color="yellow" @click="movetoboard1('mattermost')" :style="{fontWeight: 'bold', fontSize: 'large'}">인증하기</v-btn>
                    </v-col>
                </v-row>
            </v-col>

            <!-- 우측 여백 -->
            <v-col cols="12" md="3" style="padding: 4em" />
        </v-row>

        <!-- 선택 입력 사항 시작 -->
        <v-row>
            <!-- 좌측 여백 -->
            <v-col cols="12" md="3" style="padding: 4em" />

            <v-col cols="12" md="6" style="padding: 4em">
                <!-- 지역 선택 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>지역</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-combobox v-model="user.region" :items="regionList" label="지역 선택"></v-combobox>
                    </v-col>
                </v-row>

                <!-- 희망 진로 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>희망 진로</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-text-field v-model="user.dream" dense outlined label="희망 진로"></v-text-field>
                    </v-col>
                </v-row>

                <!-- 관심 기술 스택 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>관심 기술 스택</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-combobox v-model="user.category" :items="stackList" label="관심있는 기술 선택" multiple></v-combobox>
                    </v-col>
                </v-row>

                <!-- 자기 소개 -->
                <v-row>
                    <v-col cols="4">
                        <v-subheader>자기 소개</v-subheader>
                    </v-col>
                    <v-col cols="8">
                        <v-textarea v-model="user.introduction" label="자기 소개를 해주세요!" outlined rows="13"></v-textarea>
                    </v-col>
                </v-row>
            </v-col>

            <!-- 우측 여백 -->
            <v-col cols="12" md="3" style="padding: 4em" />
        </v-row>

        <v-row :style="{marginBottom: '10%'}">
            <v-col cols="12" md="4" />
            <v-col cols="12" md="4" justify="center" align="center">
                <v-btn color="yellow" @click="selftestDialog('open')" :style="{height: '50px', width: '170px', fontWeight: 'bold', fontSize: 'large'}">가입 완료</v-btn>
                <v-dialog v-model="selfTest" max-width="600" max-height="500">
                    <v-card>
                        <v-row>
                            <v-col cols="12" md="2" />
                            <v-col cols="12" md="8" justify="center" align="center">
                                <v-card-text>
                                    <div class="text-h2 pa-12">반가워요!</div>
                                </v-card-text>
                                <h3>시작하기 전에 자신의 수준을 확인해볼까요?</h3>
                            </v-col>
                            <v-col cols="12" md="2" />
                        </v-row>

                        <v-row style="margin-top: 9%; margin-bottom: 3%">
                            <!-- <v-card-actions> -->
                            <v-col cols="12" md="2" />
                            <v-col cols="12" md="2" justify="center" align="center">
                                <v-btn color="yellow darken-2" text @click="selftestDialog('close')"> 닫기 </v-btn>
                            </v-col>

                            <v-col cols="12" md="1"> </v-col>
                            <v-col cols="12" md="3">
                                <v-btn color="yellow darken-2" text @click="selftestDialog('test')"> 자기진단 하기 </v-btn>
                            </v-col>
                            <v-col cols="12" md="2">
                                <v-btn color="yellow darken-2" text @click="selftestDialog('home')"> 홈으로 </v-btn>
                            </v-col>
                        </v-row>
                    </v-card>
                </v-dialog>
            </v-col>
            <v-col cols="12" md="4" />
        </v-row>
        <!-- 끝 ~ 선택 입력 -->

        <!-- <NavFooter /> -->
    </v-app>
</template>

<script>
import TextButton from '../../components/common/TextButton.vue';

export default {
    name: 'SignUpView',
    components: {
        TextButton
    },

    data() {
        return {
            showmodal: false,
            regionList: ['서울', '대전', '부울경', '광주'],
            stackList: ['Vuejs', 'Nodejs', 'React', 'Spring', 'SpringBoot', 'TensorFlow'],
            selfTest: false,
            // 담아서 store로 보낼 값
            user: {
                userId: '',
                password: '',
                passwordCheck: '',
                username: '',
                nickname: '',
                ssafyId: '',
                phone: '',
                email: '',
                mmId: '',
                region: '',
                dream: '',
                category: '',
                introduction: ''
            }
        };
    },

    methods: {
        movetomain() {
            window.location.href = '/';
        },
        selftestDialog(check) {
            if (check == 'open') {
                // 회원가입 API
                // this.$store.dispatch("user/createUser",{ user : this.user})
                this.selfTest = true;
            } else if (check == 'close') {
                this.selfTest = false;
            } else if (check == 'test') {
                window.location.href = '/self-test/problem/1';
            } else if (check == 'home') {
                window.location.href = '/';
            }
        },
        movetoboard1(check) {
            if (check == 'id') {
                // 아이디 중복 체크 API
                // this.$store.dispatch("user/idCheck",{id : this.user.id})
            } else if (check == 'nickName') {
                // 닉네임 중복 체크 API
                // this.$store.dispatch("user/nickName",{nickname : this.user.nickname})
            } else if (check == 'mattermost') {
                // mattermost 인증
                // this.$store.dispatch("user/mattermost",{mmId : this.user.mmId})
            }
        }
    }
};
</script>
