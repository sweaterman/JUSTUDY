import axios from 'axios';
import port from '@/store/port';

export default {
    namespaced: true,
    state: {
        CommunityBoard: [],
        topCategory: [],
        CommunityContent: {},
        bookMarkList: [],

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

        GET_COMMENTLIST(state, payload) {
            state.commentList = payload;
        }
    },
    actions: {
        async getCommunityBoard({commit}, {number, category, type, search, order}) {
            let API_URL = `${port}community/board?page=${number}&category=${category}`;

            if (!(typeof type == 'undefined' || type == null || type == '')) API_URL += `&type=${type}`;
            if (!(typeof search == 'undefined' || search == null || search == '')) API_URL += `&search=${search}`;
            if (!(typeof order == 'undefined' || order == null || order == '')) API_URL += `&order=${order}`;

            await axios({
                url: API_URL,
                method: 'GET'
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
        async createBookMark({commit}, {id, bookMark}) {
            const API_URL = `${port}community/board/${id}/bookmark`;
            await axios({
                url: API_URL,
                method: 'POST',
                data: bookMark,
                withCredentials: true
            })
                .then(() => {
                    commit;
                })
                .catch(err => {
                    console.log(err);
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
                });
        },
        //카테고리 불러오기
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
        //공지 불러오기
        async getCommunityNoticeBoard({commit}, {number}) {
            const API_URL = `${port}community/board/notice?page=${number}`;
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
        //     const API_URL = `${port}community/board/popular?page=${number}`;
        //     await axios({
        //         url: API_URL,
        //         method: 'GET'
        //     })
        //         .then(res => {
        //             commit('', res.data);
        //         })
        //         .catch(err => {
        //             console.log(err);
        //         });
        // },
    }
};
