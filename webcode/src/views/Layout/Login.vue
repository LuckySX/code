<!--
 * @Author: zhangjipei
 * @Date: 2020-03-25 09:27:42
 * @LastEditors: fuping
 * @LastEditTime: 2020-08-07 13:50:55
 * @Description: 
 -->
<template>
  <div :class="Login.wrap">
    <loginPage v-if="isShow" @change="loginPageChange"></loginPage>
    <div :class="Login.container" v-loading.fullscreen.lock="fullscreenLoading" v-else></div>
  </div>
</template>
<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { State, Getter, Action, Mutation, namespace } from 'vuex-class'
import { setSession, getSession, setCookies, getCookies } from '@/utils/cookiesUtil'
import loginPage from '@/views/Layout/LoginPage.vue'
import { http } from '@/common/request'
import axios from 'axios'

const userModuleVuex = namespace('user')
interface IUserInfo {
  roles: string
  uname: string
  userIp: string
}
@Component({
  components: {
    loginPage
  }
})
export default class Login extends Vue {
  private userState: any = ''
  private userCode: any = ''
  private fullscreenLoading = false
  private isShow = false
  @Prop() private isLogin!: any
  @userModuleVuex.Getter('getTokenState') token!: any
  @userModuleVuex.Getter('getClientId') clientId!: any
  @userModuleVuex.Getter('getLoginState') loginState!: any
  @userModuleVuex.Getter('getState') STATE!: any
  @userModuleVuex.Getter('getHostUrl') hostUrl!: any
  @Action('user/setLoginState') setLoginState!: Function
  mounted() {
    this.fullscreenLoading = true
    this.userCode = this.getQueryString('code')
    this.userState = this.getQueryString('state')
    if (process.env.NODE_ENV === 'production') {
      this.isShowLogin()
    } else {
      // 本地和226
      // this.isShowLogin()
      this.isShow = true
    }
  }
  // 20200525新增需求如果竹云系统崩溃，调内部登录页---先与竹云会话，catch为竹云系统崩溃
  isShowLogin() {
    axios
      .post('https://iam.bamboocloud.com/idp/oauth2/getToken')
      .then((res: any) => {
        if (res) {
          // res存在，表示竹云系统正常，可走正常接口
          this.check()
        } else {
          // 否则表示竹云系统崩溃，调内部登录接口
          this.isShow = true
        }
      })
      .catch((error: any) => {
        console.log(error)
        // 否则表示竹云系统崩溃，调内部登录接口
        this.isShow = true
      })
  }
  loginPageChange(status: boolean) {
    this.isShow = status
    this.loginSuccess()
  }
  // 逻辑处理
  check() {
    // 如果cookie没有用户信息，重定向认证平台
    // 如果cookie有用户信息和token，直接走loginSuccess
    // 226测试，直接走loginSuccess
    if (
      getCookies('szUserInfo') &&
      JSON.parse(getCookies('szUserInfo')).roles &&
      JSON.parse(getCookies('szUserInfo')).roles != undefined &&
      JSON.parse(getCookies('szUserInfo')).roles != null
    ) {
      this.loginSuccess()
    } else {
      if (this.userCode === null) {
        // 重定向
        this.getAuthUrl()
      } else {
        // 单点登录，获取code，发给后端，获取用户信息
        if (this.userState !== this.STATE) {
          this.$message('无效请求，请联系管理员')
        } else {
          this.getUserInfo()
        }
      }
    }
  }
  // 无用户信息重定向
  getAuthUrl() {
    const autherUrl = `https://iam.bamboocloud.com/idp/oauth2/authorize?redirect_uri=${this.hostUrl}&client_id=${this.clientId}&state=${this.STATE}&response_type=code`
    window.location.href = autherUrl
  }
  getUserInfo() {
    http
      .post<IResponse>('/login/authorize2', {
        code: this.userCode,
        ip: ''
      })
      .then((res: any) => {
        const {
          data: { token, roles, uname }
        } = res
        const szUserInfo: IUserInfo = {
          roles: roles || '',
          uname: uname || '',
          userIp: ''
        }
        setCookies({ szCode: token, szUserInfo: szUserInfo })
        // 登录成功进入导航页
        this.loginSuccess()
      })
      .catch((error: any) => {
        console.log(error)
        if (error.code === 20001) {
          // 调内部登录接口
          this.isShow = true
        }
      })
  }
  getUserInfo1() {
    http.post<IResponse>('/login/authorize').then((res: any) => {
      const {
        data: { token, roles, uname }
      } = res
      const szUserInfo: IUserInfo = {
        roles: roles || '',
        uname: uname || '',
        userIp: ''
      }
      setCookies({ szCode: token, szUserInfo: szUserInfo })
      // 登录成功进入导航页
      this.loginSuccess()
    })
  }
  // 登录成功，进入menuIndex导航页
  loginSuccess() {
    this.fullscreenLoading = false
    this.$emit('change', true)
    this.setLoginState({
      loginState: true
    })
    this.$router.push({
      path: '/menuIndex'
    })
    this.$ss.set('userId', 'admin')
  }
  // 获取url参数
  getQueryString(name) {
    const winUrl: any = window.location.href
    const searchUrl = winUrl.match(/([^?]+)$/)[1]
    const reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)')
    const r = searchUrl.match(reg)
    if (r != null) {
      let a = unescape(r[2])
      //过滤#/xxx
      a = a.indexOf('#') != -1 ? a.split('#')[0] : a
      return a
    }
    return null
  }
}
</script>

<style module="Login">
.wrap {
  width: 100%;
  height: 100%;
}
.container {
  width: 100%;
  height: 100%;
  overflow: hidden;
  /* background: url('~@/assets/images/sys/loginBg.jpg') no-repeat; */
  background-size: 100% 120%;
  background-position: 0;
  position: relative;
}
</style>
