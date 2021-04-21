/*
 * @Author: renxiaofan
 * @Date: 2020-03-21 17:26:51
 * @LastEditors: fuping
 * @LastEditTime: 2020-06-24 16:22:20
 * @Description:
 */
import Vue from 'vue'
import VueRouter from 'vue-router'
import { routes } from './router.config'
import { mountRouter } from './routerPermissions'

Vue.use(VueRouter)

// const VueRouterPush = VueRouter.prototype.push
// VueRouter.prototype.push = function push(to) {
//   return VueRouterPush.call(this, to).catch(err => err)
// }
// 静态路由
routes.push({
  path: '/ErrorInfo',
  name: 'ErrorInfo',
  component: () => import('@/views/Error/error404.vue')
})

const router = new VueRouter({
  scrollBehavior: (to, from, savedPosition) => {
    if (savedPosition) {
      return savedPosition
    } else {
      return { x: 0, y: 0 }
    }
  },
  routes
})

// 挂载菜单权限
mountRouter(router)

export default router
