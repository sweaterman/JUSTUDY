import Vue from 'vue'
import Vuex from 'vuex'

import moduleUser from './moduleUser'
import moduleCommunity from './moduleCommunity'
import moduleStudy from './moduleStudy'
Vue.use(Vuex)

export default new Vuex.Store({
  // state: {
  // },
  // getters: {
  // },
  // mutations: {
  // },
  // actions: {
  // },
  modules: {
    moduleUser : moduleUser,
    moduleCommunity : moduleCommunity,
    moduleStudy : moduleStudy
  }
})
