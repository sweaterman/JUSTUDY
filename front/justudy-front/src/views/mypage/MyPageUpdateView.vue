<template>
    <v-app>
        <!-- 제목............................................................. -->
        <v-row :style="{padding: '1%'}">
            <v-col justify="center" align="center">
                <h2>회원 정보 수정</h2>
            </v-col>
        </v-row>

        <!-- 윗줄............................................................. -->
        <v-row :style="{padding: '1%'}">
            <v-col cols="12" md="3" />
            <v-col class="card_section_profile" cols="12" md="2" :style="{marginLeft: '4%'}">
                <!-- <ProfilePictureUpdate :diameter="300" :buttonLength="200" :height="70" :fontSize="32" content="LV" standard="px" :src="`${port}images/${user.imageSequence}`" @sendData="showData" /> -->
                <!-- <ProfilePictureUpdate :diameter="300" :buttonLength="200" :height="70" :fontSize="32" content="LV" standard="px" :src="require('@/assets/justudy.png')" @sendData="showData" /> -->
                <!-- <div class="card_section_profile"> -->
                <v-row>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="2" :style="{marginLeft: '10%'}">
                        <ProfilePicture :diameter="180" :height="180" :fontSize="32" content="LV" standard="px" :src="require('@/assets/justudy.png')" />
                    </v-col>
                    <v-col cols="12" md="8"> </v-col>
                    <v-col cols="12" md="1" />
                </v-row>
                <!-- 프로필 사진 변경 -->
                <v-row :style="{marginTop: '5%'}" justify="center" align="center">
                    <v-btn outlined text v-on:click="dialogChange()">
                        <span class="material-icons-outlined"> edit </span>
                        <div>프로필 이미지 변경</div>
                    </v-btn>
                </v-row>
                <!-- 아이디 -->
                <v-row>
                    <v-col cols="12" md="5">
                        <v-subheader>아이디</v-subheader>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="6" :style="{marginTop: '4%'}">
                        <div>{{ user.userId }}</div>
                    </v-col>
                </v-row>
                <!-- 이름 -->
                <v-row dense>
                    <v-col cols="12" md="5">
                        <v-subheader>이름</v-subheader>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="6" :style="{marginTop: '4%'}">
                        <div>{{ user.username }}</div>
                    </v-col>
                </v-row>
                <!-- 싸피 학번 -->
                <v-row dense>
                    <v-col cols="12" md="5">
                        <v-subheader>SSAFY 학번</v-subheader>
                    </v-col>
                    <v-col cols="12" md="1" />
                    <v-col cols="12" md="6" :style="{marginTop: '4%'}">
                        <div>{{ user.ssafyId }}</div>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="4">
                <!-- 제목 -->
                <v-form ref="form" lazy-validation>
                    <v-row>
                        <v-col>
                            <!-- 닉네임 -->
                            <v-row dense>
                                <v-col cols="12" md="3">
                                    <v-subheader>닉네임</v-subheader>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <v-text-field v-model="user.nickname" dense outlined label="닉네임" @input="checkVal.nickname = false" :rules="rules.nickname"></v-text-field>
                                </v-col>
                                <v-col cols="12" md="3">
                                    <v-btn color="yellow" rounded @click="check('nickname')"
                                        >중복확인
                                        <v-icon v-if="checkVal.nickname" right> mdi-check-bold </v-icon>
                                        <v-icon v-if="!checkVal.nickname" right> mdi-alert-circle-outline </v-icon>
                                    </v-btn>
                                </v-col>
                            </v-row>

                            <!-- 비밀번호 -->
                            <v-row dense>
                                <v-col cols="12" md="3">
                                    <v-subheader>비밀번호</v-subheader>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <v-text-field type="password" v-model="user.password" dense outlined label="비밀번호" :rules="rules.pwd"></v-text-field>
                                </v-col>
                            </v-row>

                            <!-- 비밀번호 확인 -->
                            <v-row dense>
                                <v-col cols="12" md="3">
                                    <v-subheader>비밀번호 확인</v-subheader>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <v-text-field type="password" v-model="user.passwordCheck" dense outlined label="비밀번호 확인" :rules="rules.pwdCheck"></v-text-field>
                                </v-col>
                            </v-row>

                            <!-- 전화번호 -->
                            <v-row dense>
                                <v-col cols="12" md="3">
                                    <v-subheader>전화 번호</v-subheader>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <v-text-field v-model="user.phone" dense outlined label="전화번호" :rules="rules.phone"></v-text-field>
                                </v-col>
                            </v-row>

                            <!-- 이메일 -->
                            <v-row dense>
                                <v-col cols="12" md="3">
                                    <v-subheader>이메일</v-subheader>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <v-text-field v-model="user.email" dense outlined label="이메일" :rules="rules.email"></v-text-field>
                                </v-col>
                            </v-row>

                            <!-- 지역 -->
                            <v-row dense>
                                <v-col cols="12" md="3">
                                    <v-subheader>지역</v-subheader>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <v-combobox v-model="user.region" :items="regionList" item-text="value" item-value="key" label="지역 선택"></v-combobox>
                                </v-col>
                            </v-row>
                        </v-col>
                    </v-row>
                </v-form>
            </v-col>
            <v-col cols="12" md="3" />
        </v-row>

        <!-- 밑줄 .................................................................... -->
        <v-row :style="{marginTop: '-1%'}">
            <v-col cols="12" md="3" />
            <v-col cols="12" md="6">
                <!-- 선택입력 -->
                <v-row>
                    <!-- 좌측 여백 -->
                    <v-col cols="0" md="1"></v-col>

                    <!-- 본문 -->
                    <v-col cols="0" md="10">
                        <v-row :style="{padding: '3%'}">
                            <v-divider></v-divider>
                        </v-row>

                        <!-- 희망진로 -->
                        <v-row>
                            <v-col cols="12" md="3">
                                <v-subheader>희망 진로</v-subheader>
                            </v-col>
                            <v-col cols="12" md="9">
                                <v-text-field v-model="user.dream" dense outlined label="희망 진로 입력" hint="ex) 백엔드 개발자"></v-text-field>
                            </v-col>
                        </v-row>

                        <!-- 기술 스택 -->
                        <v-row :style="{marginTop: '-3%'}">
                            <v-col cols="12" md="3">
                                <v-subheader>관심있는 기술 스택</v-subheader>
                            </v-col>
                            <v-col cols="12" md="9">
                                <v-combobox v-model="tempCategory" :items="bottomCategories" item-text="value" item-value="key" label="관심 기술" multiple></v-combobox>
                            </v-col>
                        </v-row>

                        <!-- 자기 소개 -->
                        <v-row>
                            <v-col cols="12" md="3">
                                <v-subheader>자기 소개</v-subheader>
                            </v-col>
                            <v-col cols="12" md="9">
                                <v-textarea no-resize v-model="user.introduction" label="자기 소개를 해주세요!" outlined rows="13"></v-textarea>
                            </v-col>
                        </v-row>
                    </v-col>

                    <!-- 우측여백 -->
                    <v-col cols="0" md="1"> </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="3" />
        </v-row>

        <!-- 회원가입 버튼 -->
        <v-row>
            <v-col cols="12" md="4" align="right">
                <v-btn text @click="moveback" :style="{color: 'crimson'}">
                    <span class="material-icons-outlined"> backspace </span>
                </v-btn>
            </v-col>
            <v-col cols="12" md="2" />
            <v-col cols="12" md="2" />
            <v-col cols="12" md="4" align="left">
                <v-btn text @click="update()" :style="{color: 'green'}">
                    <span class="material-icons-outlined"> check_circle </span>
                </v-btn>
            </v-col>
        </v-row>
    </v-app>
</template>

<script>
import ProfilePicture from '@/components/mypage/ProfilePicture.vue';
import port from '@/store/port';
import {mapState} from 'vuex';
export default {
    name: 'MyPageEditView',
    computed: {
        ...mapState('moduleStudy', ['bottomCategories']),
        ...mapState('moduleSignUp', ['checkId']),
        ...mapState('moduleSignUp', ['checkNickname']),
        ...mapState('moduleSignUp', ['checkSsafyId']),
        ...mapState('moduleSignUp', ['checkMM'])
    },
    components: {
        ProfilePicture
    },
    // created() {
    //     this.$store.dispatch('moduleStudy/getBottomCategories', '전체');
    // },
    async created() {
        //  회원정보와 관련된 것 get으로 가져와서 default 에 넣어놓기
        await this.$store.dispatch('moduleMyPage/getModifyUser');
        await this.$store.dispatch('moduleStudy/getBottomCategories', '전체');

        this.user = this.$store.state.moduleMyPage.modifyUser;
        for (let i = 0; i < this.user.category.length; ++i) {
            for (let j = 0; j < this.bottomCategories.length; ++j) {
                this.bottomCategories[j].checked = false;
                if (this.bottomCategories[j].value == this.user.category[i]) {
                    this.bottomCategories[j].checked = true;
                    this.tempCategory.push(this.bottomCategories[j]);
                }
            }
        }
        this.user.category = [];
        for (let i = 0; i < this.cat.length; ++i) {
            this.user.category.push(this.tempCategory[i].value);
        }
        console.log(this.user);
    },
    data() {
        return {
            tempCategory: [],
            // 기존 업데이트 ///////////////////////////////////////////////////////
            port: port,
            // user 데이터
            // get으로 가져온 것 user 에 저장
            // input 창에 칠 때마다 v-model 이용해 값 업데이트 (고쳐야 함)
            // user에 값을 넣어서 (file 포함) 보냄
            file: null,
            result: {},
            ///////////////////////////////////////////////////////

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
                userId: 'SSAFY123',
                password: '123',
                passwordCheck: '123',
                username: '오싸피',
                nickname: '만두',
                ssafyId: '0800500',
                phone: '01012345678',
                email: 'a@a.com',
                region: '서울',
                dream: '베겐드 개발자',
                category: 'frontend',
                introduction: '반갑습닏',
                mmId: 'a@a.com',
                mmPw: 'pw123'
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
        dialogChange() {
            this.$emit('dialogChangeFromChild');
        },
        moveback() {
            window.history.back(); // window.history.back()을 통해 뒤로가기
        },
        // 업데이트 발 ///////////////////////////////////////////////////////
        update() {
            // 일괄 수정 API
            console.log(this.file);
            this.result = {
                nickname: this.user.nickname,
                password: this.user.password,
                passwordCheck: this.user.passwordCheck,
                phone: this.user.phone,
                email: this.user.email,
                region: 'SEOUL',
                dream: this.user.dream,
                category: this.user.category,
                introduction: this.user.introduction
            };

            const formData = new FormData();
            formData.append('file', this.file);
            formData.append(
                'request',
                new Blob([JSON.stringify(this.result)], {
                    type: 'application/json'
                })
            );
            this.$store.dispatch('moduleMyPage/updateUser', {formData: formData});
        },
        showData(data) {
            this.file = data;
            console.log(this.file);
        },
        ///////////////////////////////////////////////////////

        async check(type) {
            if (type == 'id') {
                //id 중복체크
                await this.$store.dispatch('moduleSignUp/checkVal', {
                    type: 'userId',
                    content: this.user.userId
                });
                if (this.checkId == true) {
                    this.checkVal.id = true;
                } else {
                    this.checkVal.id = false;
                }
            } else if (type == 'nickname') {
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
            //MM 확인
            await this.$store.dispatch('moduleSignUp/sendMMAPI', {
                mmId: this.user.mmId,
                mmPassword: this.user.mmPw
            });
            if (this.checkMM) {
                if (this.user.category == '') {
                    await this.$store.dispatch('moduleSignUp/signUp', {
                        userId: this.user.userId,
                        password: this.user.password,
                        passwordCheck: this.user.passwordCheck,
                        username: this.user.username,
                        nickname: this.user.nickname,
                        ssafyId: this.user.ssafyId,
                        phone: this.user.phone,
                        email: this.user.email,
                        region: this.user.region.key,
                        dream: this.user.dream,
                        category: [],
                        introduction: this.user.introduction,
                        mmId: this.user.mmId
                    });
                } else {
                    await this.$store.dispatch('moduleSignUp/signUp', {
                        userId: this.user.userId,
                        password: this.user.password,
                        passwordCheck: this.user.passwordCheck,
                        username: this.user.username,
                        nickname: this.user.nickname,
                        ssafyId: this.user.ssafyId,
                        phone: this.user.phone,
                        email: this.user.email,
                        region: this.user.region.key,
                        dream: this.user.dream,
                        category: this.user.category.map(row => row.key),
                        introduction: this.user.introduction,
                        mmId: this.user.mmId
                    });
                }
            } else {
                alert('올바른 MatterMost ID와 비밀번호를 입력해주세요!');
            }
        }
    }
};
</script>
<style scoped>
.card_section_profile {
    padding: 1%;
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
</style>
