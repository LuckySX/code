<!--
 * @Description: 
 * @Autor: niumiaomiao
 * @Date: 2020-03-24 10:15:24
 * @LastEditors: niumiaomiao
 * @LastEditTime: 2020-08-14 10:27:52
 -->
<template>
  <div :class="MenuIndex.cotainer">
    <div :class="MenuIndex.content">
      <div :class="MenuIndex.title">
        安全监测感知平台
      </div>
      <div :class="MenuIndex.menuBox">
        <a
          v-for="(item, index) in menuData"
          :key="index"
          @click="goNewPage(item.url, item.id, item.isInternal)"
          :class="MenuIndex.labelBox"
          class="menuBg"
          target="_blank"
        >
          <div :class="'menuImg' + (index + 1)"></div>
          <p :class="MenuIndex.titleBox">{{ item.name }}</p>
        </a>
      </div>
    </div>
    <div :class="MenuIndex.infoGroup">
      <div>您好，{{ userName }}</div>
      <div :class="MenuIndex.logout" @click="logout"></div>
      <div :class="MenuIndex.system" @click="goNewPage(sItem.url, sItem.id, sItem.isInternal)" v-if="hasSystemManage"></div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import { Action, namespace } from 'vuex-class'
import { http } from '@/common/request'
import { getCookies, getSession } from '@/utils/cookiesUtil'

interface IMenuItem {
  id: string
  name: string
  url: string
  isInternal: number
  img: string
}
interface IUserInfo {
  roles: string
  uname: string
  userIp: string
}
const userModuleVuex = namespace('user')
@Component({})
export default class MenuIndex extends Vue {
  private menuData: any = []
  private isShowLogout = process.env.NODE_ENV
  private hasSystemManage = false
  private sItem: any = {}
  private userInfo: IUserInfo = getCookies('szUserInfo') != undefined ? JSON.parse(getCookies('szUserInfo')) : ''
  private userName = ''
  @userModuleVuex.Getter('getHostUrl') hostUrl!: any
  @userModuleVuex.Getter('getClientId') clientId!: any
  @Action('user/RESET_TOKEN') resetToken!: Function
  getMenuData() {
    interface MenuParams {
      box: any
    }
    http.get<MenuParams>(`/Menu/homePageList?roles=${this.userInfo.roles}`).then(resp => {
      const mData: Array<IMenuItem> = []
      resp.data.box.forEach((item: IMenuItem) => {
        if (item.id !== '900000') {
          mData.push(item)
        } else {
          this.hasSystemManage = true
          this.sItem = item
        }
      })
      // this.menuData = resp.data.box
      this.menuData = mData
    })
  }
  goInterPage(url, id) {
    http
      .post<IResponse>(`/Menu/newNenuList`, {
        id: id,
        roles: this.userInfo.roles
      })
      .then((res: any) => {
        const routeData = this.$router.resolve({
          path: '/' + res.data[0].url,
          query: {
            id: id
          }
        })
        window.open(routeData.href, '_blank')
      })
  }
  goNewPage(url, id, isInternal) {
    // isInternal 0:内部菜单；1：其它厂商
    if (isInternal === 0) {
      this.goInterPage(url, id)
    } else {
      if (id === '20000' || id === '60000') {
        // 融合联动和网络安全大数据中心不需要传参
        window.open(url, '_blank')
      } else {
        this.goOutPage(url)
      }
    }
  }
  goOutPage(url) {
    const codeUrl = `${url}?code=${getCookies('szCode')}&uName=${this.userInfo.uname}`
    window.open(codeUrl, '_blank')
  }
  logout() {
    this.$confirm('确定要注销登录吗？', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
      .then(() => {
        this.resetToken()
      })
      .catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        })
      })
  }
  mounted() {
    this.userName = this.userInfo.uname
    this.getMenuData()
  }
}
</script>
<style module="MenuIndex">
.cotainer {
  position: absolute;
  left: 0;
  top: 0;
  right: 0;
  bottom: 0;
  background: #1c273d url('~@/assets/images/sys/menuBg.jpg') 50% 50% no-repeat;
  background-size: cover;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  z-index: 99;
}
.content {
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
}
.title {
  margin-bottom: 10vh;
  color: #fff;
  font-size: 40px;
  text-align: center;
}
.menuBox {
  max-width: 1100px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  margin: 0 auto;
}
.labelBox {
  display: flex;
  position: relative;
  width: 160px;
  height: 160px;
  margin: 0 10px 15px;
  background-color: rgba(0, 135, 255, 0.4);
  justify-content: center;
}
.labelBox:hover {
  cursor: pointer;
  background-color: rgba(0, 135, 255, 1);
}
.titleBox {
  position: absolute;
  bottom: 20px;
  font-size: 14px;
  color: #fff;
  opacity: 0.6;
}
.infoGroup {
  display: flex;
  position: absolute;
  top: 30px;
  right: 30px;
  color: #2593f2;
  font-size: 15px;
  align-items: center;
}
.system {
  display: inline-block;
  width: 20px;
  height: 20px;
  cursor: pointer;
  background: url('~@/assets/images/menu/system.png') no-repeat;
}
.system:hover {
  background: url('~@/assets/images/menu/systemH.png') no-repeat;
}
.logout {
  display: inline-block;
  margin: 0 10px;
  width: 20px;
  height: 20px;
  cursor: pointer;
  background: url('~@/assets/images/menu/exit.png') no-repeat;
}
.logout:hover {
  background: url('~@/assets/images/menu/exitH.png') no-repeat;
}
</style>
