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
            state.averageMemberWeek = payload;
        },
        getRankStudyWeek(state, payload) {
            state.averageMemberMonth = payload;
        },
        getRankStudyMonth(state, payload) {
            state.studyCategory = payload;
        }
    },
    actions: {
        async getRankMemberYesterday({commit}) {
            await axios.get(`${port}rank/member/yesterday`).then(res => {
                commit('getRankMemberYesterday', res.data);
            });
        },

        async getRankMemberWeek({commit}) {
            await axios.get(`${port}rank/member/week`).then(res => {
                commit('getRankMemberWeek', res.data);
            });
        },
        async getRankMemberMonth({commit}) {
            await axios.get(`${port}rank/member/month`).then(res => {
                commit('getRankMemberMonth', res.data);
            });
        },
        async getRankStudyYesterday({commit}) {
            await axios.get(`${port}rank/study/yesterday`).then(res => {
                commit('getRankStudyYesterday', res.data);
            });
        },

        async getRankStudyWeek({commit}) {
            await axios.get(`${port}rank/study/week`).then(res => {
                commit('getRankStudyWeek', res.data);
            });
        },
        async getRankStudyMonth({commit}) {
            await axios.get(`${port}rank/study/month`).then(res => {
                commit('getRankStudyMonth', res.data);
            });
        }
    }
};
