/*
 * @Author: zhangjipei
 * @Date: 2020-03-19 11:21:33
 * @LastEditors: fuping
 * @LastEditTime: 2020-07-20 16:01:19
 * @Description:
 */
import { Module, MutationTree, ActionTree, ActionContext, GetterTree } from 'vuex'
import { RootState } from './../store'
import { http } from '@/common/request'

export interface AppState {
  isCollapse: boolean
  name?: string
  menuList: any[]
}
interface IResponse<T = any> {
  code: number
  data: T
  message: string
}

const appState: AppState = {
  isCollapse: false,
  name: '',
  menuList: []
}

const mutations: MutationTree<AppState> = {
  collapse(state: AppState, appinfo: any) {
    // state.isCollapse = !state.isCollapse
    state.isCollapse = appinfo.isCollapse
  },
  appName(state: AppState, appinfo: any) {
    state.name = appinfo.name
  },
  menuList(state: AppState, data: any) {
    state.menuList = data
  }
}

const actions: ActionTree<AppState, RootState> = {
  /**
   * 测试 设置App Name
   * @param context
   * @param param
   */
  setAppNameAction(context: ActionContext<AppState, RootState>, param: any) {
    context.commit('appName', param)
  },
  /**
   * 测试 设置isCollapse
   * @param context
   * @param param
   */
  setCollapseAction(context: ActionContext<AppState, RootState>, param: any) {
    context.commit('collapse', param)
  },
  /**
   * 测试 设置menuList
   * @param context
   * @param param
   */
  setMenuListAction(context: ActionContext<AppState, RootState>, param: any) {
    http.post<IResponse>(`/Menu/newNenuList`, param).then((res: any) => {
      const listData: any = []
      res.data.forEach((i: any) => {
        listData.push(i)
      })
      context.commit('menuList', listData)
      return listData
    })
  }
}

const getters: GetterTree<AppState, RootState> = {
  getName(state: AppState) {
    return state.name
  },
  getCollapse(state: AppState) {
    return state.isCollapse
  },
  getMenuList(state: AppState) {
    return state.menuList
  }
}

const app: Module<AppState, RootState> = {
  namespaced: true,
  state: appState,
  getters,
  actions,
  mutations
}

export default app
