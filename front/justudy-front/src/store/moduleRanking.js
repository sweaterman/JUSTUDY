import axios from 'axios';

import port from './port';
export default {
    namespaced: true,
    state: {
        rankMemberYesterday: [],
        rankMemberWeek: [],
        rankMemberMonth: [],
        rankStudyYesterday: [],
        rankStudyWeek: [],
        rankStudyMonth: []
    },
    getters: {},
    mutations: {
        getRankMemberYesterday(state, payload) {
            state.rankMemberYesterday = payload;
        },
        getRankMemberWeek(state, payload) {
            state.rankMemberWeek = payload;
        },
        getRankMemberMonth(state, payload) {
            state.rankMemberMonth = payload;
        },
        getRankStudyYesterday(state, payload) {
            state.rankStudyYesterday = payload;
        },
        getRankStudyWeek(state, payload) {
            state.rankStudyWeek = payload;
        },
        getRankStudyMonth(state, payload) {
            state.rankStudyMonth = payload;
        }
    },
    actions: {
        async getRankMemberYesterday({commit}) {
            await axios
                .get(`${port}rank/members/yesterday`, {withCredentials: true})
                .then(res => {
                    commit('getRankMemberYesterday', res.data);
                })
                .catch(() => {});
        },

        async getRankMemberWeek({commit}) {
            await axios
                .get(`${port}rank/members/week`, {withCredentials: true})
                .then(res => {
                    commit('getRankMemberWeek', res.data);
                })
                .catch(() => {});
        },
        async getRankMemberMonth({commit}) {
            await axios
                .get(`${port}rank/members/month`, {withCredentials: true})
                .then(res => {
                    commit('getRankMemberMonth', res.data);
                })
                .catch(() => {});
        },
        async getRankStudyYesterday({commit}) {
            await axios
                .get(`${port}rank/study/yesterday`, {withCredentials: true})
                .then(res => {
                    commit('getRankStudyYesterday', res.data);
                })
                .catch(() => {});
        },

        async getRankStudyWeek({commit}) {
            await axios
                .get(`${port}rank/study/week`, {withCredentials: true})
                .then(res => {
                    commit('getRankStudyWeek', res.data);
                })
                .catch(() => {});
        },
        async getRankStudyMonth({commit}) {
            await axios
                .get(`${port}rank/study/month`, {withCredentials: true})
                .then(res => {
                    commit('getRankStudyMonth', res.data);
                })
                .catch(() => {});
        }
    }
};
