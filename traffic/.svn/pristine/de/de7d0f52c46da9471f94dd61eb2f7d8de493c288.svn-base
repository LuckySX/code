/*
 * @Description:
 * @Autor: niumiaomiao
 * @Date: 2020-03-31 09:48:36
 * @LastEditors: fuping
 * @LastEditTime: 2020-06-24 16:21:46
 */
import { ddosConfig } from './DDos'
import { interFlowConfig } from './InterFlow'
import { domainConfig } from './DomainSecurity'
import { systemConfig } from './System'

let routerMenuList: any = []
routerMenuList = routerMenuList
  .concat(ddosConfig)
  .concat(interFlowConfig)
  .concat(domainConfig)
  .concat(systemConfig)
const routerLogs = (l: any) => {
  return l.map((it: any) => {
    return { name: it.name, path: it.path }
  })
}

const routes = routerMenuList.map((item: any) => {
  return item
})
routes.push({
  path: '/',
  redirect: 'login'
})

export { routes }
