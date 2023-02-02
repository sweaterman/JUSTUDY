import axios from 'axios';
import port from '@/store/port';

export default {
    namespaced: true,
    state: {
        CommunityBoard: [],
        topCategory: [],
        CommunityContent: {},
        bookMarkList: []
    },
    getters: {},
    mutations: {
        GET_COMMUNITYBOARD(state, payload) {
            state.CommunityBoard = payload;
        },
        GET_COMMUNITYSEARCH(state, payload) {
            state.CommunitySearch = payload;
        },
        GET_COMMUNITYCONTENT(state, payload) {
            state.CommunityContent = payload;
        },
        GET_COMMUNITYCONTENTUPDATE(state, payload) {
            state.CommunityContentUpdate = payload;
        },
        GET_TOPCATEGORY(state, payload) {
            state.topCategory = payload;
        },
        getBookMarkList(state, payload) {
            state.bookMarkList = payload;
        }
    },
    actions: {
        async getCommunityBoard({commit}, {number, category}) {
            const API_URL = `${port}community/board?page=${number}&category=${category}`;
            await axios({
                url: API_URL,
                method: 'GET'
            })
                .then(res => {
                    commit('GET_COMMUNITYBOARD', res.data);
                })
                .catch(err => {
                    console.log(err);
                });
        },
        getCommunitySearch({commit}, number, category, type, search) {
            const API_URL = `${port}/community/boards?page=${number}&search=${search}&type=${type}&search=${search}`;
            axios({
                url: API_URL,
                method: 'GET'
            })
                .then(res => {
                    commit('GET_MOREPROMOTIONSTUDY', res.data);
                })
                .catch(err => {
                    console.log(err);
                });
        },
        async getCommunityContent({commit}, {id}) {
            const API_URL = `${port}community/board/${id}`;
            await axios({
                url: API_URL,
                method: 'GET'
            })
                .then(res => {
                    commit('GET_COMMUNITYCONTENT', res.data);
                })
                .catch(err => {
                    console.log(err);
                });
        },
        async getCommunityContentWrite(_, {board}) {
            const API_URL = `${port}community/board`;
            await axios.post(
                API_URL,

                board,
                {
                    withCredentials: true
                }
            );
        },
        async getCommunityContentUpdate(_, {id, board}) {
            const API_URL = `${port}community/board/${id}`;
            await axios.put(
                API_URL,

                board,
                {
                    withCredentials: true
                }
            );
        },
        async getCommunityContentDelete(_, {id}) {
            const API_URL = `${port}community/board/${id}`;
            await axios.delete(
                API_URL,

                {
                    withCredentials: true
                }
            );
        },
        async getBookMarkList({commit}, {id}) {
            const API_URL = `${port}community/board/bookmark/${id}`;
            await axios.get(API_URL).then(res => {
                commit('getBookMarkList', res.data);
            });
        },
        getTopCategory() {}
    }
};
