/*
 * @Author: zhangjipei
 * @Date: 2020-05-09 14:48:03
 * @LastEditors: fuping
 * @LastEditTime: 2020-07-23 11:24:29
 * @Description:
 */
import { getSession, getCookies } from '@/utils/cookiesUtil'
export const mountRouter = router => {
  if (router.beforeEach) {
    router.beforeEach((to, from, next) => {
      const hasToken = getCookies('szCode')
      if (hasToken) {
        // next()
        if (to.matched.length === 0) {
          from.name
            ? next({
                name: from.name
              })
            : next('/ErrorInfo')
        } else {
          next() //如果匹配到正确跳转
        }
      } else {
        // 线上环境
        if (process.env.NODE_ENV === 'production') {
          next({
            path: '/login'
          })
        } else {
          next()
        }
      }
    })
  }
}
