import Vue from 'vue'

import Cookies from 'js-cookie'

import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.less'

import '@/styles/index.less' // global css

import App from './App'
import store from './store'
import router from './router'

import './icons' // icon
import './permission' // permission control

import * as filters from './filters' // global filters

Vue.use(Antd, {
  size: Cookies.get('size') || 'medium' // set default size
})

// register global utility filters
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
