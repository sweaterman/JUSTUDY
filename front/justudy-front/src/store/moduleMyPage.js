import axios from 'axios';
import port from './port';

export default {
    namespaced: true,
    state: {
        user: {},
        modifyUser: {},
        bookMarkList: [],
        profile: {}
    },
    getters: {
        // dataComputed : function(state){
        //     return state.user.money+1000
        // }
    },
    mutations: {
        getMyPageUser(state, payload) {
            state.user = payload;
        },
        getModifyUser(state, payload) {
            state.modifyUser = payload;
        },
        getBookMarkList(state, payload) {
            state.bookMarkList = payload;
        },
        getProfile(state, payload) {
            state.profile = payload;
        }
    },
    actions: {
        //북마크 리스트 커뮤니티쪽 작업 아닌듯
        async getBookMarkList({commit}) {
            const API_URL = `${port}member/bookmarks`;
            await axios
                .get(API_URL, {
                    withCredentials: true
                })
                .then(res => {
                    commit('getBookMarkList', res.data);
                });
        },
        async getMyPageUser({commit}) {
            await axios
                .get(port + 'member/mypage', {
                    withCredentials: true
                })
                .then(res => {
                    commit('getMyPageUser', res.data);
                });
        },

        async getModifyUser({commit}) {
            await axios
                .get(port + 'member/mypage/modify', {
                    withCredentials: true
                })
                .then(res => {
                    commit('getModifyUser', res.data);
                });
        },

        updateUser(_, {formData}) {
            axios.patch(port + 'member/mypage/modify', formData, {
                withCredentials: true,
                headers: {
                    'Content-Type': ' multipart/form-data'
                }
            });
        },

        getProfile({commit}, {id}) {
            axios.get(port + 'member/profiles/' + id).then(res => {
                commit('getProfile', res.data);
            });
        }
    }
};
