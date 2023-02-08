import axios from 'axios';
import port from './port';

export default {
    namespaced: true,
    state: {
        checkId: false,
        checkNickname: false,
        checkSsafyId: false
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
        }
    },
    actions: {
        async signUp(_, user) {
            const API_URL = `${port}member/register`;
            console.log(user);
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
                    console.log(err);
                });
        },
        async checkVal({commit}, type, content) {
            let API_URL = `${port}member/check/?`;
            if (type == 'userid') {
                API_URL += `userid=${content}`;
            } else if (type == 'nickname') {
                API_URL += `nickname=${content}`;
            } else {
                API_URL += `ssafyId=${content}`;
            }
            await axios({
                url: API_URL,
                method: 'GET'
            })
                .then(() => {
                    if (type == 'userid') {
                        commit('CHECK_ID', true);
                        alert('성공');
                    } else if (type == 'nickname') {
                        commit('CHECK_NICKNAME', true);
                    } else {
                        commit('CHECK_SSAFYID', true);
                    }
                })
                .catch(err => {
                    if (err.response.status == 409) {
                        if (type == 'userid') {
                            commit('CHECK_ID', false);
                            alert('실패');
                        } else if (type == 'nickname') {
                            commit('CHECK_NICKNAME', false);
                        } else {
                            commit('CHECK_SSAFYID', false);
                        }
                    } else {
                        console.log(err);
                    }
                });
        }
    }
};
