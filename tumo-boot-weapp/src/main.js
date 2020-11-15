import Vue from 'vue'
import App from './App'
import store from './store'
import router from './router'
import '@/permission.js'

//global css
import '@/static/app.css'

//引入mock js
import '@/mock/index'

Vue.config.productionTip = false

Vue.prototype.towXmlUtil = require('@/wxcomponents/towxml/index.js')
Vue.prototype.$store = store
App.mpType = 'app'

const app = new Vue({
  store,
  router,
  ...App
})
app.$mount()
