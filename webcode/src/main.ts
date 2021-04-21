/*
 * @Author: fuping
 * @Date: 2020-04-13 15:17:09
 * @LastEditors: fuping
 * @LastEditTime: 2020-04-23 14:53:06
 * @Description:
 */
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/store'
import 'normalize.css'
import ElementUI from 'element-ui'
import VuePandora from 'vue-pandora'
import 'vue-pandora/lib/vuepandora.css'
import 'element-ui/lib/theme-chalk/index.css'
import './common/storage'
import '@/styles/index.less'

import BaseCharts from '@/components/Charts/BaseCharts.vue'
Vue.component('BaseCharts', BaseCharts)

import { loadMapsResouces } from '@/components/Charts/map/load/index.ts'
loadMapsResouces('world')
loadMapsResouces('shenzhen')
loadMapsResouces('china')

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(VuePandora)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
