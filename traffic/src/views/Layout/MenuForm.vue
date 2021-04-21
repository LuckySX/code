<!--
 * @Author: zhangjipei
 * @Date: 2020-03-20 19:30:20
 * @LastEditors: fuping
 * @LastEditTime: 2020-07-23 11:12:51
 * @Description: 
 -->
<template>
  <div :class="menuForm.container">
    <!-- <router-link to="/DDosOverall">DDosOverall</router-link> -->
    <el-menu :default-active="activeMenu" router unique-opened :collapse="isCollapse" ref="navMenu">
      <template v-for="(menu, index) in mlist">
        <template v-if="menu.sonmenu && menu.sonmenu.length > 0">
          <el-submenu :index="menu.id" :key="index" :class="{ 'is-active': isActive }">
            <template slot="title">
              <i :class="[menuForm.icon, iconStyle(menu.id)]"></i>
              <span slot="title">{{ menu.name }}</span>
            </template>
            <template v-for="(cMenu, cIndex) in menu.sonmenu">
              <template v-if="cMenu.name && cMenu.sonmenu && cMenu.sonmenu.length > 0">
                <el-submenu :index="cMenu.id" :ref="cMenu.url" :key="cIndex">
                  <span slot="title">{{ cMenu.name }}</span>
                  <template v-for="(cCMenu, cCIndex) in cMenu.sonmenu">
                    <el-menu-item :index="`${menu.url}/${cMenu.url}/${cCMenu.url}`" :key="cCIndex">{{ cCMenu.name }}</el-menu-item>
                  </template>
                </el-submenu>
              </template>
              <template v-if="cMenu.name && (!cMenu.sonmenu || (cMenu.sonmenu && cMenu.sonmenu.length <= 0))">
                <el-menu-item :index="`/${cMenu.url}`" :key="cIndex" @click="handleOpen(cIndex, $event, menu.id)">{{ cMenu.name }}</el-menu-item>
              </template>
            </template>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :index="`/${menu.url}`" :key="index" @click="handleClose(index, $event)">
            <template>
              <i :class="[menuForm.icon, iconStyle(menu.id)]"></i>
              <span slot="title">{{ menu.name }}</span>
            </template>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch, Ref } from 'vue-property-decorator'
import { Action, namespace } from 'vuex-class'
import { getCookies } from '@/utils/cookiesUtil'
import { http } from '@/common/request'
import dayjs from 'dayjs'

const appModuleVuex = namespace('app')
interface IUserInfo {
  code: string
  userName: string
  ip: string
  operateTime: string
  operateType: string
  operateContent: string
  menuType: string
  firm: string
}
@Component({})
export default class MenuForm extends Vue {
  @Ref() readonly navMenu!: any | Element
  private activeMenu = '/DDosRealTime'
  private isActive = false
  // private isCollapse : boolean = false
  private menuForm: any
  private criticalWidth = 1440
  private userInfo: IUserInfo = {
    code: getCookies('szCode'),
    userName: getCookies('szUserInfo') != undefined ? JSON.parse(getCookies('szUserInfo')).uname : '',
    ip: '',
    operateTime: dayjs().format('YYYY-MM-DD HH:mm:ss'),
    operateType: '访问',
    operateContent: '',
    menuType: '',
    firm: 'AQJCGZ'
  }

  @Watch('$route', { deep: true })
  private routePathChanged(newVal: any, oldVal: any) {
    const val = newVal.path
    if (val === '/login') {
      this.$ss.remove('userId')
      this.$emit('change', false)
    } else {
      let menuId = 0
      if (val.match('/DDos') != null) {
        menuId = 40000
      } else if (val.match('/Domain') != null) {
        menuId = 30000
      } else if (val.match('/System') != null) {
        menuId = 900000
      } else {
        // 流量流向
        menuId = 10000
      }
      this.setMenuListAction({
        id: menuId,
        roles: getCookies('szUserInfo') != undefined ? JSON.parse(getCookies('szUserInfo')).roles : ''
      })
      this.activeMenu = val
    }
    // if (process.env.NODE_ENV === 'production') {
    //线上日志采集
    if (newVal.name === '首页') {
      return false
    }
    const menu = newVal.meta.parentName
      ? `${newVal.meta.familyName}/${newVal.meta.parentName}/${newVal.name}`
      : `${newVal.meta.familyName}/${newVal.name}`
    this.userInfo.menuType = menu
    this.userInfo.operateTime = dayjs().format('YYYY-MM-DD HH:mm:ss')
    this.userInfo.operateContent = `${this.userInfo.userName}访问${this.userInfo.menuType}`
    this.sendUserInfo()
    // }
  }

  @appModuleVuex.Getter('getCollapse') isCollapse!: boolean
  @appModuleVuex.Getter('getMenuList') mlist!: any

  @Action('app/setCollapseAction') setCollapseAction!: Function
  @Action('app/setMenuListAction') setMenuListAction!: Function

  iconStyle(icon: string) {
    const name = `menu${icon}`
    return this.menuForm[name]
  }
  resizeEventHandler(): void {
    this.setCollapseAction({ isCollapse: document.body.offsetWidth <= this.criticalWidth })
    // this.setCollapseAction({ isCollapse: false })
  }
  mounted() {
    this.setCollapseAction({ isCollapse: document.body.offsetWidth <= this.criticalWidth })
    // this.setCollapseAction({ isCollapse: false })
    if (window.addEventListener) {
      window.addEventListener('resize', this.resizeEventHandler, false)
    } else if (window.attachEvent) {
      window.attachEvent('resize', this.resizeEventHandler)
    }
  }
  handleOpen(index, e, id) {
    // console.log(index, e)
    // console.log(e.parentMenu.$el.classList)
    setTimeout(_ => {
      for (const i in e.parentMenu.rootMenu.submenus) {
        if (i !== id) {
          const clist = e.parentMenu.rootMenu.submenus[i].$el.classList
          if (clist.contains('is-active')) {
            clist.remove('is-active')
          }
        }
        for (const j in e.parentMenu.rootMenu.submenus[i].items) {
          const clist2 = e.parentMenu.rootMenu.submenus[i].items[j].$el.classList
          if (clist2.contains('is-active')) {
            clist2.remove('is-active')
          }
        }
      }
      e.$el.classList.add('is-active')
      e.parentMenu.$el.classList.add('is-active')
    }, 1000)
    // console.log(e.parentMenu.$el.classList)
    // this.isActive = !this.isActive
  }
  handleClose(index, e) {
    // console.log(e.parentMenu)
    for (const i in e.parentMenu.submenus) {
      const clist = e.parentMenu.submenus[i].$el.classList
      if (clist.contains('is-active')) {
        clist.remove('is-active')
      }
      if (clist.contains('is-opened')) {
        clist.remove('is-opened')
        this.navMenu.close(e.parentMenu.submenus[i].index)
      }
      for (const j in e.parentMenu.submenus[i].items) {
        const clist2 = e.parentMenu.submenus[i].items[j].$el.classList
        if (clist2.contains('is-active')) {
          clist2.remove('is-active')
        }
      }
    }
  }
  destoryed() {
    if (window.removeEventListener) {
      window.removeEventListener('resize', this.resizeEventHandler, false)
    } else if (window.detachEvent) {
      window.detachEvent('resize', this.resizeEventHandler)
    }
  }
  sendUserInfo() {
    http.post<IResponse>('/userManage/insetRecord.do', this.userInfo).catch((res: any) => {
      console.log(res)
    })
  }
}
</script>
<style module="menuForm">
.container {
  width: 100%;
  height: 100%;
  background: #012a6c;
}
.icon {
  display: inline-block;
  width: 40px;
  height: 40px;
  margin-left: -20px;
}
.menu9 {
  background: url('~@/assets/images/menu/9.png') no-repeat center;
  background-size: 50%;
}
.menu10001 {
  background: url('~@/assets/images/submenu/10001.png') no-repeat center;
}
.menu10001:hover {
  background: url('~@/assets/images/submenu/10001h.png') no-repeat center;
}
.menu10002 {
  background: url('~@/assets/images/submenu/10002.png') no-repeat center;
}
.menu10002:hover {
  background: url('~@/assets/images/submenu/10002h.png') no-repeat center;
}
.menu10003 {
  background: url('~@/assets/images/submenu/10003.png') no-repeat center;
}
.menu10003:hover {
  background: url('~@/assets/images/submenu/10003h.png') no-repeat center;
}
.menu10004 {
  background: url('~@/assets/images/submenu/10004.png') no-repeat center;
}
.menu10004:hover {
  background: url('~@/assets/images/submenu/10004h.png') no-repeat center;
}
.menu10005 {
  background: url('~@/assets/images/submenu/10005.png') no-repeat center;
}
.menu10005:hover {
  background: url('~@/assets/images/submenu/10005h.png') no-repeat center;
}
.menu10006 {
  background: url('~@/assets/images/submenu/10006.png') no-repeat center;
}
.menu10006:hover {
  background: url('~@/assets/images/submenu/10006h.png') no-repeat center;
}
.menu10007 {
  background: url('~@/assets/images/submenu/10007.png') no-repeat center;
}
.menu10007:hover {
  background: url('~@/assets/images/submenu/10007h.png') no-repeat center;
}
.menu40001 {
  background: url('~@/assets/images/submenu/40001.png') no-repeat center;
}
.menu40001:hover {
  background: url('~@/assets/images/submenu/40001h.png') no-repeat center;
}
.menu40002 {
  background: url('~@/assets/images/submenu/40002.png') no-repeat center;
}
.menu40002:hover {
  background: url('~@/assets/images/submenu/40002h.png') no-repeat center;
}
.menu30001 {
  background: url('~@/assets/images/submenu/30001.png') no-repeat center;
  background-size: 80%;
}
.menu30001:hover {
  background: url('~@/assets/images/submenu/30001h.png') no-repeat center;
  background-size: 80%;
}
.menu30002 {
  background: url('~@/assets/images/submenu/30002.png') no-repeat center;
  background-size: 80%;
}
.menu30002:hover {
  background: url('~@/assets/images/submenu/30002h.png') no-repeat center;
  background-size: 80%;
}
.menu30003 {
  background: url('~@/assets/images/submenu/30003.png') no-repeat center;
  background-size: 80%;
}
.menu30003:hover {
  background: url('~@/assets/images/submenu/30003h.png') no-repeat center;
  background-size: 80%;
}
.menu30004 {
  background: url('~@/assets/images/submenu/30004.png') no-repeat center;
  background-size: 80%;
}
.menu30004:hover {
  background: url('~@/assets/images/submenu/30004h.png') no-repeat center;
  background-size: 80%;
}
.menu30005 {
  background: url('~@/assets/images/submenu/30005.png') no-repeat center;
  background-size: 80%;
}
.menu30005:hover {
  background: url('~@/assets/images/submenu/30005h.png') no-repeat center;
  background-size: 80%;
}
.menu30006 {
  background: url('~@/assets/images/submenu/30006.png') no-repeat center;
  background-size: 80%;
}
.menu30006:hover {
  background: url('~@/assets/images/submenu/30006h.png') no-repeat center;
  background-size: 80%;
}
.menu900001 {
  background: url('~@/assets/images/submenu/900001.png') no-repeat center;
}
.menu900001:hover {
  background: url('~@/assets/images/submenu/900001h.png') no-repeat center;
}
.menu900002 {
  background: url('~@/assets/images/submenu/900002.png') no-repeat center;
}
.menu900002:hover {
  background: url('~@/assets/images/submenu/900002h.png') no-repeat center;
}
.menu900003 {
  background: url('~@/assets/images/submenu/900003.png') no-repeat center;
}
.menu900003:hover {
  background: url('~@/assets/images/submenu/900003h.png') no-repeat center;
}
</style>
