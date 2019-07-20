import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import Vuex from "vuex"
import "./style/theme.sass"
Vue.config.productionTip = false
// 如果在模块化构建系统中，请确保在开头调用了 Vue.use(Vuex)
Vue.use(Vuex);
const store = new Vuex.Store({
  state: {
    commits:[],
    allProblem:[],
    paperEditData:{},
    allRole:[],
    allUser:[]
  },
  mutations: {
    setNewCommits (state,newcommits) {
      state.commits=newcommits
    },
    setNewProblems(state,pd){
      state.allProblem=pd;
    },
    setPaperEditData(state,paperEditData){
      state.paperEditData=paperEditData;
    },
    setNewRoles(state,pd){
      state.allRole=pd;
    },
    setNewUsers(state,pd){
      state.allUser=pd;
    }
  }
});
let me= new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');

export default me;