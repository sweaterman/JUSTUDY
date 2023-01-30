import axios from 'axios';
import port from './port';
export default {
    namespaced: true,
    state: {},
    getters: {},
    mutations: {},
    actions: {
        login(_, {user}) {
            axios.post(
                port + '/login',
                {
                    userId: user.userId,
                    password: user.password
                },
                {
                    withCredentials: true
                }
            );
        }
    }
};
