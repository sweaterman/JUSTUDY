import axios from 'axios';
// import port from './port';
export default {
    namespaced: true,
    state: {
        user: {}
    },
    getters: {
        // dataComputed : function(state){
        //     return state.user.money+1000
        // }
    },
    mutations: {
        getMyPageUser(state, payload) {
            state.user = payload;
        }
    },
    actions: {
        async getMyPageUser({commit}) {
            await axios
                .get('http://localhost:8080/api/mypage/member', {
                    withCredentials: true
                })
                .then(res => {
                    console.log('aaa', res.data);
                    commit('getMyPageUser', res.data);
                });
        }
    }
};
