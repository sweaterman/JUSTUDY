import axios from 'axios';
import port from '@/store/port';

export default {
    namespaced: true,
    state: {
        CommunityBoard: [],
        topCategory: [],
        CommunityContent: {}
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
        }
    },
    actions: {
        getCommunityBoard({commit}, number, category) {
            const API_URL = `${port}/community/boards?page=${number}&category=${category}`;
            axios({
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
        getCommunityContent({commit}, id) {
            const API_URL = `${port}/community/board/${id}`;
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
        getCommunityContentUpdate({commit}, id) {
            const API_URL = `${port}/community/board/${id}`;
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
        getTopCategory() {}
    }
};
