import axios from 'axios';
import port from './port';
export default {
    namespaced: true,
    state: {
        isLogin: {}
    },
    getters: {},
    mutations: {
        SET_ISLOGIN(state, payload) {
            state.isLogin = payload;
        },
        SET_LOGOUT(state, payload) {
            state.isLogin = payload;
        }
    },
    actions: {
        login({commit}, {user}) {
            axios
                .post(
                    port + 'login',
                    {
                        userId: user.userId,
                        password: user.password
                    },
                    {
                        withCredentials: true
                    }
                )
                .then(() => {
                    commit('SET_ISLOGIN', true);
                    window.location.href = '/';
                })
                .catch(err => {
                    console.log(err);
                    alert('아이디/비밀번호가 일치하지 않습니다.');
                });
        },
        logout({commit}) {
            const API_URL = `${port}logout`;
            axios({
                url: API_URL,
                method: 'POST'
            })
                .then(() => {
                    commit('SET_LOGOUT', false);
                })
                .catch(err => {
                    console.log(err);
                });
        }
    }
};
