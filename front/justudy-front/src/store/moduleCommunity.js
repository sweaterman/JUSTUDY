// import axios from "axios";

export default {
    namespaced: true,
    state: {},
    getters: {},
    mutations: {},
    actions: {}
    // main page
    // mounted() {
    //     // mounted는 페이지가 켜질때 실행됨, 페이지가 켜질때 글 리스트들을 db에서 불러옴
    //     axios({
    //         // 게시글 작성자, 제목, 작성일 가져오기
    //         url: 'http://127.0.0.1:52273/content/boardlist/',
    //         method: 'POST',
    //         data: {
    //             // back 서버에 현재 게시판번호와 페이지번호를 넘겨줘야 해당하는 글 리스트 불러올 수 있음
    //             boardnum: this.$route.params.id,
    //             page: this.$route.query.page
    //         }
    //     })
    //         .then(res => {
    //             this.contentlist = res.data;
    //         })
    //         .catch(err => {
    //             alert(err);
    //         });
    //     axios({
    //         // 현재 게시판 글 개수 가져오기
    //         url: 'http://127.0.0.1:52273/content/boardlistcnt/',
    //         method: 'POST',
    //         data: {
    //             boardnum: this.$route.params.id
    //         }
    //     })
    //         .then(res => {
    //             this.cnt = res.data;
    //         })
    //         .catch(err => {
    //             alert(err);
    //         });
    // },
};
