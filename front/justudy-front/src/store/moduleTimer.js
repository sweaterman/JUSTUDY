import axios from 'axios';
import port from './port';
export default {
    namespaced: true,
    state: {
        firstYesterday: {}
    },
    getters: {},
    mutations: {
        getFirstYesterday(state, payload) {
            state.firstYesterday = payload;
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
        getStudyTimeWeek(_, {seq}) {
            axios.get(port + `timer/member/week?seq=${seq}`);
        },
        getStudyTimeMonth(_, {seq}) {
            axios.get(port + `timer/member/month?seq=${seq}`);
        },
        getStudyCategory(_, {seq}) {
            axios.get(port + `timer/member/category?seq=${seq}`);
        },
        getAverageTimeWeek() {
            axios.get(port + 'timer/members/week');
        },
        getAverageTimeMonth() {
            axios.get(port + 'timer/members/month');
        },
        getStudyCalendar(_, {seq}) {
            axios.get(port + `timer/study-calendar?seq=${seq}`);
        }
    }
};
