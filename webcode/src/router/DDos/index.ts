/*
 * @Author: your name
 * @Date: 2020-03-25 10:38:49
 * @LastEditTime: 2020-06-26 14:33:25
 * @LastEditors: fuping
 * @Description: DDoS攻击监测
 */

export const ddosConfig = [
  {
    path: '/DDosRealTime',
    name: '实时监测',
    meta: { familyName: 'DDoS攻击监测' },
    component: () => import('@/views/DDos/RealTime/index.vue')
  },
  {
    path: '/DDosOverall',
    name: 'DDoS攻击事件',
    meta: {
      keepAlive: true,
      isBack: false,
      familyName: 'DDoS攻击监测'
    },
    component: () => import('@/views/DDos/DDosOverall/index.vue')
  },
  {
    path: '/DDosDetails',
    name: 'DDoS攻击事件详情',
    meta: { familyName: 'DDoS攻击监测' },
    component: () => import('@/views/DDos/DDosDetails/index.vue')
  }
]
