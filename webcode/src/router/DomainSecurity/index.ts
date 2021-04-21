/*
 * @Author: fuping
 * @Date: 2020-03-30 14:21:00
 * @LastEditTime: 2020-06-29 13:47:58
 * @LastEditors: niumiaomiao
 * @Description: 域名安全监测
 */

export const domainConfig = [
  {
    path: '/DomainOverview',
    name: '总体态势',
    meta: { parentName: '恶意域名监测', familyName: '域名安全监测' },
    component: () => import('@/views/DomainSecurity/DomainMalicious/DomainOverview/index.vue')
  },
  {
    path: '/DomainDetail',
    name: '恶意域名访问详情',
    meta: { parentName: '恶意域名监测', familyName: '域名安全监测' },
    component: () => import('@/views/DomainSecurity/DomainMalicious/DomainDetail/index.vue')
  },
  {
    path: '/DomainUserQuery',
    name: '用户访问查询',
    meta: { parentName: '恶意域名监测', familyName: '域名安全监测' },
    component: () => import('@/views/DomainSecurity/DomainMalicious/DomainUserQuery/index.vue')
  },
  {
    path: '/DomainNotfiled',
    name: '未备案域名发现',
    meta: { familyName: '域名安全监测' },
    component: () => import('@/views/DomainSecurity/DomainNotfiled/index.vue')
  },
  {
    path: '/DomainTamperSearch',
    name: '域名篡改查询',
    meta: { parentName: '域名篡改监测', familyName: '域名安全监测' },
    component: () => import('@/views/DomainSecurity/DomainTamper/TamperSearch.vue')
  },
  {
    path: '/DomainTamperEvent',
    name: '域名篡改事件',
    meta: { parentName: '域名篡改监测', familyName: '域名安全监测' },
    component: () => import('@/views/DomainSecurity/DomainTamper/TamperEvent.vue')
  },
  {
    path: '/DomainAccess',
    name: '域名访问统计',
    meta: { familyName: '域名安全监测' },
    component: () => import('@/views/DomainSecurity/DomainAccess/index.vue')
  },
  {
    path: '/DomainOverall',
    name: '域名安全概览',
    meta: { familyName: '域名安全监测' },
    component: () => import('@/views/DomainSecurity/DomainOverall/index.vue')
  },
  {
    path: '/DomainServer',
    name: '域名服务器监测',
    meta: { familyName: '域名安全监测' },
    component: () => import('@/views/DomainSecurity/DomainServer/index.vue')
  }
]
