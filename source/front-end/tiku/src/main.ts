import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import Vuex from "vuex"
Vue.config.productionTip = false
// 如果在模块化构建系统中，请确保在开头调用了 Vue.use(Vuex)
Vue.use(Vuex);
const store = new Vuex.Store({
  state: {
    commits:[]
  },
  mutations: {
    setNewCommits (state,newcommits) {
      state.commits=newcommits
    }
  }
});
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
