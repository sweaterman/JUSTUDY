import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import vuetify from './plugins/vuetify';
import '@/assets/styles/icons.css';
import VueApexCharts from 'vue-apexcharts'
Vue.use(VueApexCharts)

Vue.component('ApexChart', VueApexCharts)
Vue.config.productionTip = false;

router.beforeEach((to, from, next) => {
    //authenticationState는 유저가 로그인이 되어

    const {authorization} = to.meta;

    if (authorization?.[0] == 'login') {
        // 여기서 로그인 체크 할것임

        console.log(authorization);
        return next({path: '/login'});
    }
    next();
});

new Vue({
    router,
    store,
    vuetify,
    render: h => h(App)
}).$mount('#app');
