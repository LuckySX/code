/*
 * @Author: fuping
 * @Date: 2020-03-19 14:26:18
 * @LastEditors: niumiaomiao
 * @LastEditTime: 2020-09-27 10:45:25
 * @Description:
 */
import { Module, MutationTree, ActionTree, ActionContext, GetterTree } from 'vuex'
import { setCookies, getCookies, removeCookies, removeSession, setSession, getSession } from '@/utils/cookiesUtil'
import { http } from '@/common/request'
import { RootState } from './../store'
import { Message } from 'element-ui'
import router from '@/router'

export interface IUserState {
  token: string
  loginState: boolean
  clientId: string
  STATE: string
  hostUrl: string
}
const userState: IUserState = {
  token: '',
  loginState: false,
  clientId: 'AQJCGZ',
  STATE: 'RANDOM_0a185',
  hostUrl: 'https://10.253.241.138/netSecur/index.html'
}

const mutations: MutationTree<IUserState> = {
  tokenState(state: IUserState, payload: any) {
    state.token = payload
  },
  loginState(state: IUserState, payload: any) {
    state.loginState = payload
  }
}

const actions: ActionTree<IUserState, RootState> = {
  /**
   * 设置loginState状态
   * @param context
   * @param param
   */
  SET_TOKEN(context: ActionContext<IUserState, RootState>, param: any) {
    context.commit('tokenState', param.token)
  },
  RESET_TOKEN(context: ActionContext<IUserState, RootState>, param: any) {
    // 如果有token
    if (getCookies('szCode')) {
      http
        .post<IResponse>(`/login/revokeToken`, { ip: '' })
        .then((res: any) => {
          if (res.data) {
            context.commit('tokenState', '')
            removeCookies(['szCode', 'szUserInfo'])
            removeSession('loginState')
            // 仅线上环境
            if (process.env.NODE_ENV === 'production') {
              // 重定向至竹云
              window.location.href = `https://iam.bamboocloud.com/idp/profile/OAUTH2/Redirect/GLO?redirctToUrl=https://iam.bamboocloud.com/apphub&redirectToLogin=true&entityId=${context.state.clientId}`
            } else {
              router.push('/login')
              location.reload()
            }
          } else {
            Message({
              message: '请求超时',
              type: 'warning'
            })
          }
        })
    } else {
      removeCookies(['szCode', 'szUserInfo'])
      removeSession('loginState')
      // 仅线上环境
      if (process.env.NODE_ENV === 'production') {
        // 重定向至竹云
        window.location.href = `https://iam.bamboocloud.com/idp/profile/OAUTH2/Redirect/GLO?redirctToUrl=https://iam.bamboocloud.com/apphub&redirectToLogin=true&entityId=${context.state.clientId}`
      } else {
        router.push('/login')
        location.reload()
      }
    }
  },
  setLoginState(context: ActionContext<IUserState, RootState>, param: any) {
    context.commit('loginState', param.loginState)
    setSession({ loginState: param.loginState })
  }
}

const getters: GetterTree<IUserState, RootState> = {
  getTokenState(state: IUserState) {
    return state.token
  },
  getClientId(state: IUserState) {
    return state.clientId
  },
  getState(state: IUserState) {
    return state.STATE
  },
  getLoginState(state: IUserState) {
    if (getSession('loginState')) {
      state.loginState = Boolean(getSession('loginState'))
    }
    return state.loginState
  },
  getHostUrl(state: IUserState) {
    return state.hostUrl
  }
}

const user: Module<IUserState, RootState> = {
  namespaced: true,
  state: userState,
  getters,
  actions,
  mutations
}

export default user
