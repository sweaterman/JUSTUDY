<template>
    <v-container>
        <v-row>
            <!-- 좌측 여백 -->
            <v-col cols="0" md="3"></v-col>

            <!-- 본문 -->
            <v-col cols="12" md="6">
                <!-- 제목 -->
                <v-row>
                    <v-col cols="12" align="center">
                        <h2>회원 가입</h2>
                    </v-col>
                </v-row>

                <v-form ref="form" lazy-validation>
                    <!-- 필수 입력 내용 -->
                    <v-row>
                        <!-- 좌측 여백 -->
                        <v-col cols="0" md="2"></v-col>

                        <v-col cols="12" md="8">
                            <v-row>
                                <v-col cols="12">
                                    <h4>필수 입력란</h4>
                                    <v-divider></v-divider>
                                </v-col>
                            </v-row>

                            <!-- 아이디 -->
                            <v-row dense>
                                <v-col cols="12" md="3">
                                    <v-subheader>아이디</v-subheader>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <v-text-field
                                        v-model="user.userId"
                                        dense
                                        outlined
                                        label="ID"
                                        @input="checkVal.id = false"
                                        :rules="rules.id"
                                    ></v-text-field>
                                </v-col>
                                <v-col cols="12" md="3">
                                    <v-btn color="yellow" rounded @click="check('id')">
                                        중복확인
                                        <v-icon v-if="checkVal.id" right>mdi-check-bold</v-icon>
                                        <v-icon v-if="!checkVal.id" right>mdi-alert-circle-outline</v-icon>
                                    </v-btn>
                                </v-col>
                            </v-row>

                            <!-- 비밀번호 -->
                            <v-row dense>
                                <v-col cols="12" md="3">
                                    <v-subheader>비밀번호</v-subheader>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <v-text-field
                                        type="password"
                                        v-model="user.password"
                                        dense
                                        outlined
                                        label="비밀번호"
                                        :rules="rules.pwd"
                                    ></v-text-field>
                                </v-col>
                            </v-row>

                            <!-- 비밀번호 확인 -->
                            <v-row dense>
                                <v-col cols="12" md="3">
                                    <v-subheader>비밀번호 확인</v-subheader>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <v-text-field
                                        type="password"
                                        v-model="user.passwordCheck"
                                        dense
                                        outlined
                                        label="비밀번호 확인"
                                        :rules="rules.pwdCheck"
                                    ></v-text-field>
                                </v-col>
                            </v-row>

                            <!-- 이름 -->
                            <v-row dense>
                                <v-col cols="12" md="3">
                                    <v-subheader>이름</v-subheader>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <v-text-field
                                        v-model="user.username"
                                        dense
                                        outlined
                                        label="이름"
                                        :rules="rules.name"
                                    ></v-text-field>
                                </v-col>
                            </v-row>

                            <!-- 닉네임 -->
                            <v-row dense>
                                <v-col cols="12" md="3">
                                    <v-subheader>닉네임</v-subheader>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <v-text-field
                                        v-model="user.nickname"
                                        dense
                                        outlined
                                        label="닉네임"
                                        @input="checkVal.nickname = false"
                                        :rules="rules.nickname"
                                    ></v-text-field>
                                </v-col>
                                <v-col cols="12" md="3">
                                    <v-btn color="yellow" rounded @click="check('nickname')">
                                        중복확인
                                        <v-icon v-if="checkVal.nickname" right>mdi-check-bold</v-icon>
                                        <v-icon
                                            v-if="!checkVal.nickname"
                                            right
                                        >mdi-alert-circle-outline</v-icon>
                                    </v-btn>
                                </v-col>
                            </v-row>

                            <!-- 싸피 학번 -->
                            <v-row dense>
                                <v-col cols="12" md="3">
                                    <v-subheader>SSAFY 학번</v-subheader>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <v-text-field
                                        v-model="user.ssafyId"
                                        dense
                                        outlined
                                        label="SSAFY 학번"
                                        @input="checkVal.ssafyId = false"
                                        :rules="rules.ssafyId"
                                    ></v-text-field>
                                </v-col>
                                <v-col cols="12" md="3">
                                    <v-btn color="yellow" rounded @click="check('ssafyId')">
                                        중복확인
                                        <v-icon v-if="checkVal.ssafyId" right>mdi-check-bold</v-icon>
                                        <v-icon
                                            v-if="!checkVal.ssafyId"
                                            right
                                        >mdi-alert-circle-outline</v-icon>
                                    </v-btn>
                                </v-col>
                            </v-row>

                            <!-- 전화번호 -->
                            <v-row dense>
                                <v-col cols="12" md="3">
                                    <v-subheader>전화 번호</v-subheader>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <v-text-field
                                        v-model="user.phone"
                                        dense
                                        outlined
                                        label="전화번호"
                                        :rules="rules.phone"
                                    ></v-text-field>
                                </v-col>
                            </v-row>

                            <!-- 이메일 -->
                            <v-row dense>
                                <v-col cols="12" md="3">
                                    <v-subheader>이메일</v-subheader>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <v-text-field
                                        v-model="user.email"
                                        dense
                                        outlined
                                        label="이메일"
                                        :rules="rules.email"
                                    ></v-text-field>
                                </v-col>
                            </v-row>

                            <!-- 지역 -->
                            <v-row dense>
                                <v-col cols="12" md="3">
                                    <v-subheader>지역</v-subheader>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <v-select
                                        v-model="user.region"
                                        :items="regionList"
                                        item-text="value"
                                        item-value="key"
                                        label="지역 선택"
                                    ></v-select>
                                </v-col>
                            </v-row>
                        </v-col>

                        <!-- 우측여백 -->
                        <v-col cols="0" md="2"></v-col>
                    </v-row>
                </v-form>

                <!-- 선택입력 -->
                <v-row>
                    <!-- 좌측 여백 -->
                    <v-col cols="0" md="1"></v-col>

                    <!-- 본문 -->
                    <v-col cols="0" md="10">
                        <v-row>
                            <v-col cols="12">
                                <h4>선택 입력란</h4>
                                <v-divider></v-divider>
                            </v-col>
                        </v-row>

                        <!-- 희망진로 -->
                        <v-row>
                            <v-col cols="12" md="3">
                                <v-subheader>희망 진로</v-subheader>
                            </v-col>
                            <v-col cols="12" md="9">
                                <v-text-field
                                    v-model="user.dream"
                                    dense
                                    outlined
                                    label="희망 진로 입력"
                                    hint="ex) 백엔드 개발자"
                                ></v-text-field>
                            </v-col>
                        </v-row>

                        <!-- 기술 스택 -->
                        <v-row>
                            <v-col cols="12" md="3">
                                <v-subheader>관심있는 기술 스택</v-subheader>
                            </v-col>
                            <v-col cols="12" md="9">
                                <v-select
                                    v-model="user.category"
                                    :items="memberCategory"
                                    item-text="value"
                                    item-value="key"
                                    label="관심 기술"
                                    multiple
                                ></v-select>
                            </v-col>
                        </v-row>

                        <!-- 자기 소개 -->
                        <v-row>
                            <v-col cols="12" md="3">
                                <v-subheader>자기 소개</v-subheader>
                            </v-col>
                            <v-col cols="12" md="9">
                                <v-textarea
                                    no-resize
                                    v-model="user.introduction"
                                    label="자기 소개를 해주세요!"
                                    outlined
                                    rows="13"
                                ></v-textarea>
                            </v-col>
                        </v-row>

                        <!-- 회원가입 버튼 -->
                        <v-row>
                            <v-col cols="12" align="center">
                                <v-btn color="yellow" @click="mmOpen()">MattertMost 인증하고 회원 가입 완료하기!</v-btn>
                            </v-col>
                        </v-row>
                    </v-col>

                    <!-- 우측여백 -->
                    <v-col cols="0" md="1"></v-col>
                </v-row>
            </v-col>

            <!-- 우측 여백 -->
            <v-col cols="0" md="3"></v-col>
        </v-row>

        <v-dialog v-model="mmdialog" width="600">
            <v-card>
                <v-card-title>MatterMost 인증</v-card-title>

                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="5">
                                <v-subheader>MatterMost ID</v-subheader>
                            </v-col>
                            <v-col cols="7">
                                <v-text-field
                                    v-model="user.mmId"
                                    dense
                                    outlined
                                    label="ID"
                                    :rule="v => !!v || '필수 입력 사항입니다.'"
                                ></v-text-field>
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col cols="5">
                                <v-subheader>MatterMost PW</v-subheader>
                            </v-col>
                            <v-col cols="7">
                                <v-text-field
                                    v-model="user.mmPw"
                                    type="password"
                                    dense
                                    outlined
                                    label="PW"
                                    :rule="v => !!v || '필수 입력 사항입니다.'"
                                ></v-text-field>
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col cols="12" align="center">
                                <v-btn @click="signUp()">회원가입</v-btn>
                                <v-btn @click="mmdialog = false">취소</v-btn>
                            </v-col>
                        </v-row>
                    </v-container>
                </v-card-text>
            </v-card>
        </v-dialog>
    </v-container>
</template>

<script>
import {mapState} from 'vuex';

export default {
    name: 'SignUpView',
    computed: {
        ...mapState('moduleSignUp', ['memberCategory']),
        ...mapState('moduleSignUp', ['checkId']),
        ...mapState('moduleSignUp', ['checkNickname']),
        ...mapState('moduleSignUp', ['checkSsafyId']),
        ...mapState('moduleSignUp', ['checkMM'])
    },
    created() {
        this.$store.dispatch('moduleSignUp/getMemberCategory');
    },
    data() {
        return {
            regionList: [
                {
                    key: 'SEOUL',
                    value: '서울'
                },
                {
                    key: 'DAEJEON',
                    value: '대전'
                },
                {
                    key: 'GUMI',
                    value: '구미'
                },
                {
                    key: 'GWANGJU',
                    value: '광주'
                },
                {
                    key: 'BUWOOLKYOUNG',
                    value: '부울경'
                }
            ],
            user: {
                userId: '',
                password: '',
                passwordCheck: '',
                username: '',
                nickname: '',
                ssafyId: '',
                phone: '',
                email: '',
                region: '',
                dream: '',
                category: '',
                introduction: '',
                mmId: '',
                mmPw: ''
            },
            checkVal: {
                id: false,
                nickname: false,
                ssafyId: false
            },
            rules: {
                //영어or숫자 15자이내
                id: [
                    v => !!v || '필수 입력 사항입니다.',
                    v => /^[a-z0-9]+$/.test(v) || '아이디는 영소문자와 숫자로 구성해주세요.',
                    v => !(v.length > 15) || '아이디는 15글자 이하로 작성해주세요.',
                    v => !(v.length < 6) || '아이디는 최소 6자 이상 작성해주세요.'
                ],

                //(영어 대문자 or 영어 소문자) and 숫자 => 최소 8자리
                pwd: [v => !!v || '필수 입력 사항입니다.', v => /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(v) || '패스워드는 최소 8자 이상, 영어 대문자 or 소문자 or 숫자를 사용해주세요.'],

                //입력한 비밀번호와 일치하기
                pwdCheck: [v => !!v || '필수 입력 사항입니다.', v => this.user.password == v || '입력한 패스워드와 일치하지 않습니다.'],

                //한글만 입력
                name: [v => !!v || '필수 입력 사항입니다.', v => /^[가-힣]+$/.test(v) || '올바른 이름으로 입력해주세요.'],

                //10자이내 자유형식
                nickname: [v => !!v || '필수 입력 사항입니다.', v => v.length <= 10 || '10자 이내로 입력해주세요.'],

                //7자리 숫자인지 확인
                ssafyId: [v => !!v || '필수 입력 사항입니다.', v => /^[0-9]+$/.test(v) || '숫자로 입력해주세요.', v => v.length == 7 || '7자리 숫자를 입력해주세요.'],

                //숫자인지 확인
                phone: [v => !!v || '필수 입력 사항입니다.', v => /^[0-9]+$/.test(v) || '숫자로만 입력해주세요.'],

                //이메일 형식
                email: [v => !!v || '필수 입력 사항입니다.', v => /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i.test(v) || '올바른 이메일 형식으로 입력해주세요.']
            },
            mmdialog: false,
            dialogTest: false
        };
    },

    methods: {
        async check(type) {
            if (type == 'id') {
                //id 중복체크
                if (this.user.userId == '') {
                    alert('정보를 먼저 입력해주세요!');
                } else {
                    await this.$store.dispatch('moduleSignUp/checkVal', {
                        type: 'userId',
                        content: this.user.userId
                    });
                    if (this.checkId == true) {
                        this.checkVal.id = true;
                    } else {
                        this.checkVal.id = false;
                    }
                }
            } else if (type == 'nickname') {
                if (this.user.nickname == '') {
                    alert('정보를 먼저 입력해주세요!');
                } else {
                    //닉네임 중복체크
                    await this.$store.dispatch('moduleSignUp/checkVal', {
                        type: 'nickname',
                        content: this.user.nickname
                    });
                    if (this.checkNickname == true) {
                        this.checkVal.nickname = true;
                    } else {
                        this.checkVal.nickname = false;
                    }
                }
            } else {
                if (this.user.ssafyId == '') {
                    alert('정보를 먼저 입력해주세요!');
                } else {
                    //싸피 학번 중복체크
                    await this.$store.dispatch('moduleSignUp/checkVal', {
                        type: 'ssafyId',
                        content: this.user.ssafyId
                    });
                    if (this.checkSsafyId == true) {
                        this.checkVal.ssafyId = true;
                    } else {
                        this.checkVal.ssafyId = false;
                    }
                }
            }
        },
        async mmOpen() {
            const validate = this.$refs.form.validate();
            if (!validate || this.user.region == '' || this.checkVal.id == false || this.checkVal.nickname == false || this.checkVal.ssafyId == false) {
                alert('필수 항목을 다시 확인해주세요.');
            } else {
                this.mmdialog = true;
            }
        },
        async signUp() {
            console.log(this.user);
            //MM 확인
            await this.$store.dispatch('moduleSignUp/sendMMAPI', {
                mmId: this.user.mmId,
                mmPassword: this.user.mmPw
            });
            if (this.checkMM) {
                // await this.$store.dispatch('moduleSignUp/signUp', {
                //     userId: this.user.userId,
                //     password: this.user.password,
                //     passwordCheck: this.user.passwordCheck,
                //     username: this.user.username,
                //     nickname: this.user.nickname,
                //     ssafyId: this.user.ssafyId,
                //     phone: this.user.phone,
                //     email: this.user.email,
                //     region: this.user.region.key,
                //     dream: this.user.dream,
                //     category: [],
                //     introduction: this.user.introduction,
                //     mmId: this.user.mmId
                // });
                await this.$store.dispatch('moduleSignUp/signUp', this.user);
            } else {
                alert('올바른 MatterMost ID와 비밀번호를 입력해주세요!');
            }
        }
    }
};
</script>
