import axios from 'axios';
import port from '@/store/port';

export default {
    namespaced: true,
    state: {
        CommunityBoard: [],
        topCategory: [],
        CommunityContent: {},
        bookMarkList: [],

        hotBoard: [],
        loveList: [],

        commentList: []
    },
    getters: {},
    mutations: {
        GET_COMMUNITYBOARD(state, payload) {
            state.CommunityBoard = payload;
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
        },
        getLoveList(state, payload) {
            state.loveList = payload;
        },
        GET_COMMENTLIST(state, payload) {
            state.commentList = payload;
        }
    },
    actions: {
        async createReport(_, {boardId, report}) {
            let API_URL;
            API_URL = `${port}community/board/${boardId}/report`;
            console.log(report);
            await axios.post(API_URL, report, {
                withCredentials: true
            });
        },
        async getCommunityBoard({commit}, {number, category, type, search, order}) {
            let API_URL;
            if (category == 'all') API_URL = `${port}community/board?page=${number}`;
            else API_URL = `${port}community/board?page=${number}&category=${category}`;
            console.log(API_URL);
            if (!(typeof type == 'undefined' || type == null || type == '')) API_URL += `&type=${type}`;
            if (!(typeof search == 'undefined' || search == null || search == '')) API_URL += `&search=${search}`;
            if (!(typeof order == 'undefined' || order == null || order == '')) API_URL += `&order=${order}`;

            await axios({
                url: API_URL,
                method: 'GET',
                withCredentials: true
            })
                .then(res => {
                    commit('GET_COMMUNITYBOARD', res.data);
                })
                .catch(err => {
                    commit('GET_COMMUNITYBOARD', null);
                    console.log(err);
                });
        },
        async getCommunityContent({commit}, {id}) {
            const API_URL = `${port}community/board/${id}`;
            await axios({
                url: API_URL,
                method: 'GET',
                withCredentials: true
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
        //한민 작업
        //북마크 생성
        //reload 필요?
        async createBookMark({commit}, {id}) {
            const API_URL = `${port}community/board/${id}/bookmark`;
            await axios({
                url: API_URL,
                method: 'POST',

                withCredentials: true
            })
                .then(() => {
                    commit;
                })
                .catch(err => {
                    console.log(err);
                    if (err == '401') {
                        alert('로그인이 필요합니다.');
                        window.location.href = '/login';
                    }
                });
        },
        //북마크 삭제
        //reload 필요?
        async deleteBookMark({commit}, {id}) {
            const API_URL = `${port}community/board/${id}/bookmark`;
            await axios({
                url: API_URL,
                method: 'DELETE',
                withCredentials: true
            })
                .then(() => {
                    commit;
                })
                .catch(err => {
                    console.log(err);
                    if (err == '401') {
                        alert('로그인이 필요합니다.');
                        window.location.href = '/login';
                    }
                });
        },
        //카테고리 불러오기
        async getTopCategories({commit}) {
            const API_URL = `${port}category/main-category`;
            await axios({
                url: API_URL,
                method: 'GET',
                withCredentials: true
            })
                .then(res => {
                    commit('GET_TOPCATEGORIES', res.data);
                })
                .catch(err => {
                    console.log(err);
                });
        },
        //공지 불러오기
        async getCommunityNoticeBoard({commit}, {number}) {
            const API_URL = `${port}community/board/notice?page=${number}`;
            await axios({
                url: API_URL,
                method: 'GET',
                withCredentials: true
            })
                .then(res => {
                    commit('GET_COMMUNITYBOARD', res.data);
                })
                .catch(err => {
                    console.log(err);
                });
        },
        //좋아요 생성
        //reload 필요?
        async createLove({commit}, {id, love}) {
            const API_URL = `${port}community/board/${id}/love`;
            await axios({
                url: API_URL,
                method: 'POST',
                data: love,
                withCredentials: true
            })
                .then(() => {
                    commit;
                })
                .catch(err => {
                    console.log(err);
                    if (err == '401') {
                        alert('로그인이 필요합니다.');
                        window.location.href = '/login';
                    }
                });
        },
        //좋아요 삭제
        //reload 필요?
        async deleteLove({commit}, {id}) {
            const API_URL = `${port}community/board/${id}/love`;
            await axios({
                url: API_URL,
                method: 'DELETE',
                withCredentials: true
            })
                .then(() => {
                    commit;
                })
                .catch(err => {
                    console.log(err);
                    if (err == '401') {
                        alert('로그인이 필요합니다.');
                        window.location.href = '/login';
                    }
                });
        },

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
        //좋아요 리스트
        async getLoveList({commit}) {
            const API_URL = `${port}member/loves`;
            await axios
                .get(API_URL, {
                    withCredentials: true
                })
                .then(res => {
                    commit('getLoveList', res.data);
                });
        },

        //게시글 별 댓글 불러오기
        async getCommentList({commit}, {id}) {
            const API_URL = `${port}community/board/${id}/comments`;
            await axios({
                url: API_URL,
                method: 'GET',
                withCredentials: true
            })
                .then(res => {
                    commit('GET_COMMENTLIST', res.data);
                })
                .catch(err => {
                    console.log(err);
                });
        },
        //댓글 생성
        //reload 필요?
        async createComment({commit}, {id, comment}) {
            const API_URL = `${port}community/board/${id}/comments`;
            await axios({
                url: API_URL,
                method: 'POST',
                data: comment,
                withCredentials: true
            })
                .then(() => {
                    commit;
                    window.location.reload();
                })
                .catch(err => {
                    console.log(err);
                });
        },
        //댓글 삭제
        //reload 필요?
        async deleteComment({commit}, {id, commentid}) {
            const API_URL = `${port}community/board/${id}/comments/${commentid}`;
            await axios({
                url: API_URL,
                method: 'DELETE',
                withCredentials: true
            })
                .then(() => {
                    commit;
                    window.location.reload();
                })
                .catch(err => {
                    console.log(err);
                });
        },
        //댓글 수정
        //reload 필요?
        async updateComment({commit}, {id, commentid, comment}) {
            const API_URL = `${port}community/board/${id}/comments/${commentid}`;
            await axios({
                url: API_URL,
                method: 'PUT',
                data: comment,
                withCredentials: true
            })
                .then(() => {
                    commit;
                    window.location.reload();
                })
                .catch(err => {
                    console.log(err);
                });
        }

        //인기글 불러오기 메인 페이지 기능
        // async getPopularCommunityBoard({commit}, {number}) {
        //     const API_URL = `${port}community/board/popular?page=${number}&size=5`;
        //     await axios({
        //         url: API_URL,
        //         method: 'GET'
        //     })
        //         .then(res => {
        //             commit('GET_HOTBOARD', res.data);
        //         })
        //         .catch(err => {
        //             console.log(err);
        //         });
        // }
    }
};
