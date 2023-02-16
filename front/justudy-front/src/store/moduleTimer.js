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
        studyCalendar: [],
        follow: [],
        follower: []
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
        },
        getFollow(state, payload) {
            state.follow = payload;
        },
        getFollower(state, payload) {
            state.follower = payload;
        }
    },
    actions: {
        saveIndividualTime() {
            axios.post(port + 'timer/member');
        },
        async getFirstYesterday({commit}) {
            await axios
                .get(port + 'timer/member/yesterday-top')
                .then(res => {
                    commit('getFirstYesterday', res.data);
                })
                .catch(() => {});
        },
        async getStudyTimeWeek({commit}, {nickName}) {
            await axios
                .get(port + `timer/member/week?nickName=${nickName}`)
                .then(res => {
                    commit('getStudyTimeWeek', res.data);
                })
                .catch(() => {});
        },
        async getStudyTimeMonth({commit}, {nickName}) {
            await axios
                .get(port + `timer/member/month?nickName=${nickName}`)
                .then(res => {
                    commit('getStudyTimeMonth', res.data);
                })
                .catch(() => {});
        },
        async getStudyCategory({commit}, {nickName}) {
            await axios
                .get(port + `timer/member/category?nickName=${nickName}`)
                .then(res => {
                    commit('getStudyCategory', res.data);
                })
                .catch(() => {});
        },

        async getAverageMembersWeek({commit}) {
            await axios
                .get(port + 'timer/members/week')
                .then(res => {
                    commit('getAverageMembersWeek', res.data);
                })
                .catch(() => {});
        },
        async getAverageMembersMonth({commit}) {
            await axios
                .get(port + 'timer/members/month')
                .then(res => {
                    commit('getAverageMembersMonth', res.data);
                })
                .catch(() => {});
        },
        async getStudyCalendar({commit}, {nickName, year, month}) {
            await axios
                .post(port + `timer/member-calendar`, {
                    nickName: nickName,
                    year: year,
                    month: month
                })
                .then(res => {
                    commit('getStudyCalendar', res.data);
                })
                .catch(() => {});
        },
        async getFollow({commit}) {
            await axios
                .get(port + 'follow/my-follow', {
                    withCredentials: true
                })
                .then(res => {
                    commit('getFollow', res.data);
                })
                .catch(() => {});
        },
        async getFollower({commit}) {
            await axios
                .get(port + 'follow/my-follower', {
                    withCredentials: true
                })
                .then(res => {
                    commit('getFollower', res.data);
                })
                .catch(() => {});
        }
    }
};
