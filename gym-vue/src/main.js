import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'


Vue.prototype.$http = axios
// axios.defaults.baseURL = "http://localhost:9000"

//导入qs工具
import qs from 'qs'
Vue.prototype.$qs = qs


Vue.config.productionTip = false
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

