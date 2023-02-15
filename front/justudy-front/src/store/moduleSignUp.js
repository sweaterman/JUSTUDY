import axios from 'axios';
import port from './port';

export default {
    namespaced: true,
    state: {
        checkId: false,
        checkNickname: false,
        checkSsafyId: false,
        checkMM: false,
        memberCategory: []
    },
    getters: {},
    mutations: {
        CHECK_ID(state, payload) {
            state.checkId = payload;
        },
        CHECK_NICKNAME(state, payload) {
            state.checkNickname = payload;
        },
        CHECK_SSAFYID(state, payload) {
            state.checkSsafyId = payload;
        },
        CHECK_MM(state, payload) {
            state.checkMM = payload;
        },
        GET_MEMBERCATEGORY(state, payload) {
            state.memberCategory = payload;
        }
    },
    actions: {
        async signUp(_, user) {
            const API_URL = `${port}member/register`;
            await axios({
                url: API_URL,
                method: 'POST',
                data: user
            })
                .then(() => {
                    alert('회원가입 완료! 로그인 해주세요.');
                    window.location.href = '/';
                })
                .catch(err => {
                    if (err.response.status == 409) {
                        alert('중복된 값이 존재합니다.');
                    } else if (err.response.status == 401) {
                        alert('유효하지 않은 MM 정보입니다.');
                    } else {
                        console.log(err);
                    }
                });
        },
        async checkVal({commit}, value) {
            let API_URL = `${port}member/check/?`;
            if (value.type == 'userId') {
                API_URL += `userid=${value.content}`;
            } else if (value.type == 'nickname') {
                API_URL += `nickname=${value.content}`;
            } else {
                API_URL += `ssafyid=${value.content}`;
            }
            await axios({
                url: API_URL,
                method: 'GET'
            })
                .then(() => {
                    if (value.type == 'userId') {
                        commit('CHECK_ID', true);
                    } else if (value.type == 'nickname') {
                        commit('CHECK_NICKNAME', true);
                    } else {
                        commit('CHECK_SSAFYID', true);
                    }
                    alert('사용할 수 있습니다!');
                })
                .catch(err => {
                    if (err.response.status == 409) {
                        if (value.type == 'userId') {
                            commit('CHECK_ID', false);
                        } else if (value.type == 'nickname') {
                            commit('CHECK_NICKNAME', false);
                        } else {
                            commit('CHECK_SSAFYID', false);
                        }
                        alert('이미 사용하고 있는 값입니다!');
                    } else {
                        console.log(err);
                    }
                });
        },
        async sendMMAPI({commit}, user) {
            const API_URL = `${port}member/matter-most`;
            await axios({
                url: API_URL,
                method: 'POST',
                data: user
            })
                .then(() => {
                    commit('CHECK_MM', true);
                })
                .catch(err => {
                    console.log(err);
                });
        },
        async getMemberCategory({commit}) {
            const API_URL = `${port}category/member-category`;
            await axios({
                url: API_URL,
                method: 'GET'
            })
                .then(res => {
                    commit('GET_MEMBERCATEGORY', res.data);
                })
                .catch(err => {
                    console.log(err);
                });
        }
    }
};
