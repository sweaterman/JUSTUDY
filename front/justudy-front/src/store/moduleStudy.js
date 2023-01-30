import axios from 'axios';
import port from '@/store/port';

export default {
    namespaced: true,
    state: {
        promotionStudies: [],
        morePromotionStudies: [],
        topCategory: [],
        bottomCategory: [],
        applyStudyInfo: {}
    },
    getters: {},
    mutations: {
        GET_PROMOTIONSTUDY(state, payload) {
            state.promotionStudies = payload;
        },
        GET_MOREPROMOTIONSTUDY(state, payload) {
            state.morePromotionStudies = payload;
        },
        GET_TOPCATEGORY(state, payload) {
            state.topCategory = payload;
        },
        GET_BOTTOMCATEGORY(state, payload) {
            state.bottomCategory = payload;
        },
        GET_APPLYSTUDYINFO(state, payload) {
            state.applyStudyInfo = payload;
        }
    },
    actions: {
        getPromotionStudies({commit}, page, type, search) {
            const API_URL = `${port}/study?page=${page}&type=${type}&search=${search}`;
            axios({
                url: API_URL,
                method: 'GET'
            })
                .then(res => {
                    commit('GET_PROMOTIONSTUDY', res.data);
                })
                .catch(err => {
                    console.log(err);
                });
        },
        getMorePromotionStudies({commit}, page, type, search) {
            const API_URL = `${port}/study?page=${page}&type=${type}&search=${search}`;
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
        getTopCategory({commit}) {
            const API_URL = `${port}/study/topCategory`;
            axios({
                url: API_URL,
                method: 'GET'
            })
                .then(res => {
                    commit('GET_TOPCATEGORY', res.data);
                })
                .catch(err => {
                    console.log(err);
                });
        },
        getBottomCategory({commit}) {
            const API_URL = `${port}/study/bottomCategory`;
            axios({
                url: API_URL,
                method: 'GET'
            })
                .then(res => {
                    commit('GET_BOTTOMCATEGORY', res.data);
                })
                .catch(err => {
                    console.log(err);
                });
        },
        getApplyStudyInfo({commit}, studySeq) {
            const API_URL = `${port}/study/${studySeq}`;
            axios({
                url: API_URL,
                method: 'GET'
            })
                .then(res => {
                    commit('GET_APPLYSTUDYINFO', res.data);
                })
                .catch(err => {
                    console.log(err);
                });
        },
        applyStudy(seq, sendData) {
            const API_URL = `${port}/study/myStudy/${seq}`;
            axios({
                url: API_URL,
                method: 'POST',
                params: sendData
            })
                .then(() => {
                    this.$router.push({path: `/study/myStudy`});
                })
                .catch(err => {
                    console.log(err);
                });
        }
    }
};
