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
        }
    },
    actions: {
        async login({commit}, {user}) {
            await axios
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
                    commit('SET_ISLOGIN', {loginCheck: true});
                    window.location.href = '/';
                })
                .catch(err => {
                    console.log(err);
                    alert('아이디/비밀번호가 일치하지 않습니다.');
                });
        },
        async logout({commit}) {
            const API_URL = `${port}logout`;
            await axios({
                url: API_URL,
                method: 'POST',
                withCredentials: true
            })
                .then(() => {
                    commit('SET_ISLOGIN', {loginCheck: false});
                    window.location.href = '/';
                })
                .catch(err => {
                    console.log(err);
                });
        },

        async signUp(_, {user}) {
            const API_URL = `${port}member/register`;
            console.log(user.password);
            await axios.post(API_URL, user);
        }
    }
};
