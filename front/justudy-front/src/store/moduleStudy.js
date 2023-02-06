import axios from 'axios';
import port from '@/store/port';

export default {
    namespaced: true,
    state: {
        promotionStudies: [],
        morePromotionStudies: [],
        topCategories: [],
        bottomCategories: [],
        applyStudyInfo: {}, //공개된 스터디 상세 정보
        applyStudies: [], //내가 지원한 스터디 목록
        myStudies: [], // 내가 가입한 스터디 목록
        studyInfo: {} //스터디 상세 정보
    },
    getters: {},
    mutations: {
        GET_PROMOTIONSTUDY(state, payload) {
            state.promotionStudies = payload;
        },
        GET_MOREPROMOTIONSTUDY(state, payload) {
            state.morePromotionStudies = payload;
        },
        GET_TOPCATEGORIES(state, payload) {
            state.topCategories = payload;
        },
        GET_BOTTOMCATEGORIES(state, payload) {
            state.bottomCategories = payload;
        },
        GET_APPLYSTUDYINFO(state, payload) {
            state.applyStudyInfo = payload;
        },
        GET_APPLYSTUDIES(state, payload) {
            state.applyStudies = payload;
        },
        GET_MYSTUDIES(state, payload) {
            state.myStudies = payload;
        },
        GET_STUDYINFO(state, payload) {
            state.studyInfo = payload;
        }
    },
    actions: {
        //공개된 스터디 받아오기
        async getPromotionStudies({commit}, send) {
            console.log('검색 진행', send);
            const API_URL = `${port}study/?page=${send.page}&type=${send.type}&search=${send.content}`;
            await axios({
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
        //공개된 스터디 목록에서 더보기를 눌렀을 때 처리하는 내용
        async getMorePromotionStudies({commit}, send) {
            const API_URL = `${port}study/?page=${send.page}&type=${send.type}&search=${send.content}`;
            await axios({
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
        //상위 카테고리 받아오기
        async getTopCategories({commit}) {
            const API_URL = `${port}category/main-category`;
            await axios({
                url: API_URL,
                method: 'GET'
            })
                .then(res => {
                    commit('GET_TOPCATEGORIES', res.data);
                })
                .catch(err => {
                    console.log(err);
                });
        },
        //하위 카테고리 받아오기
        async getBottomCategories({commit}, top) {
            let API_URL = `${port}category`;
            if (top != '전체') {
                API_URL += `?main=${top}`;
            }
            await axios({
                url: API_URL,
                method: 'GET'
            })
                .then(res => {
                    commit('GET_BOTTOMCATEGORIES', res.data);
                })
                .catch(err => {
                    console.log(err);
                });
        },
        //스터디 홍보페이지 상세 정보 받아오기
        async getApplyStudyInfo({commit}, studySeq) {
            const API_URL = `${port}study/${studySeq}`;
            await axios({
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
        //스터디 지원 신청
        async applyStudy({commit}, seq, sendData) {
            const API_URL = `${port}study/myStudy/${seq}`;
            await axios({
                url: API_URL,
                method: 'POST',
                params: sendData
            })
                .then(() => {
                    commit;
                    this.$router.push({path: `/study/myStudy`});
                })
                .catch(err => {
                    console.log(err);
                });
        },
        //내가 지원한 스터디 목록 받아오기
        async getApplyStudies({commit}) {
            const API_URL = `${port}study/myStudy/${'여기로그인한유저시퀀스'}`;
            await axios({
                url: API_URL,
                method: 'GET'
            })
                .then(res => {
                    commit('GET_APPLYSTUDIES', res.data);
                })
                .catch(err => {
                    console.log(err);
                });
        },
        //내가 가입한 스터디 목록 받아오기
        async getMyStudies({commit}) {
            // const API_URL = `${port}/study/API안만들어진듯?`;
            await axios({
                // url: API_URL,
                method: 'GET'
            })
                .then(res => {
                    commit('GET_MYSTUDIES', res.data);
                })
                .catch(err => {
                    console.log(err);
                });
        },
        //스터디 생성하기
        async createStudy({commit}, study) {
            const API_URL = `${port}study`;
            await axios({
                url: API_URL,
                method: 'POST',
                params: study
            })
                .then(res => {
                    commit;
                    //이렇게 하는게 맞나?
                    this.$router.push({path: `/study/search/${res.data.sequence}`});
                })
                .catch(err => {
                    console.log(err);
                });
        },
        //스터디 상세페이지 정보 받아오기
        async getStudyInfo({commit}, studySeq) {
            const API_URL = `${port}study/${studySeq}`;
            await axios({
                url: API_URL,
                method: 'GET'
            })
                .then(res => {
                    commit('GET_STUDYINFO', res.data);
                })
                .catch(err => {
                    console.log(err);
                });
        }
    }
};
