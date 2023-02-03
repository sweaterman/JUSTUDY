import axios from 'axios';

import port from './port';
export default {
    namespaced: true,
    state: {
        firstYesterday: {},
        studyTimeWeek: {},
        studyTimeMonth: {},
        averageMemberWeek: {},
        averageMemberMonth: {},
        studyCategory: [],
        studyCalendar: []
    },
    getters: {},
    mutations: {
        getFirstYesterday(state, payload) {
            state.firstYesterday = payload;
        },
        getStudyTimeWeek(state, payload) {
            state.studyTimeWeek = payload;
        },
        getStudyTimeMonth(state, payload) {
            state.studyTimeMonth = payload;
        },
        getAverageMembersWeek(state, payload) {
            state.averageMemberWeek = payload;
        },
        getAverageMembersMonth(state, payload) {
            state.averageMemberMonth = payload;
        },
        getStudyCategory(state, payload) {
            state.studyCategory = payload;
        },
        getStudyCalendar(state, payload) {
            state.studyCalendar = payload;
        }
    },
    actions: {
        saveIndividualTime() {
            axios.post(port + 'timer/member');
        },
        async getFirstYesterday({commit}) {
            await axios.get(port + 'timer/member/yesterday-top').then(res => {
                commit('getFirstYesterday', res.data);
            });
        },
        async getStudyTimeWeek({commit}, {seq}) {
            await axios.get(port + `timer/member/week?seq=${seq}`).then(res => {
                commit('getStudyTimeWeek', res.data);
            });
        },
        async getStudyTimeMonth({commit}, {seq}) {
            await axios.get(port + `timer/member/month?seq=${seq}`).then(res => {
                commit('getStudyTimeMonth', res.data);
            });
        },
        async getStudyCategory({commit}, {seq}) {
            await axios.get(port + `timer/member/category?seq=${seq}`).then(res => {
                commit('getStudyCategory', res.data);
            });
        },

        async getAverageMembersWeek({commit}) {
            await axios.get(port + 'timer/members/week').then(res => {
                commit('getAverageMembersWeek', res.data);
            });
        },
        async getAverageMembersMonth({commit}) {
            await axios.get(port + 'timer/members/month').then(res => {
                commit('getAverageMembersMonth', res.data);
            });
        },
        async getStudyCalendar({commit}, {seq, year, month}) {
            await axios
                .post(port + `timer/member-calendar`, {
                    seq: seq,
                    year: year,
                    month: month
                })
                .then(res => {
                    commit('getStudyCalendar', res.data);
                });
        }
    }
};
