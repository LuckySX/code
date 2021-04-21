<!--
 * @Description: 
 * @Autor: niumiaomiao
 * @Date: 2020-03-24 10:15:24
 * @LastEditors: fuping
 * @LastEditTime: 2020-06-01 11:52:56
 -->
<template>
  <div :class="MenuIndex.cotainer">
    <div :class="MenuIndex.title">
      安全监测感知平台
    </div>
    <div :class="MenuIndex.menuBox">
      <a
        v-for="(item, index) in menuData"
        :key="index"
        @click="goNewPage(item.url, item.id)"
        :class="MenuIndex.labelBox"
        class="menuBg"
        target="_blank"
      >
        <div :class="'menuImg' + (index + 1)"></div>
        <p :class="MenuIndex.titleBox">{{ item.name }}</p>
      </a>
    </div>
    <div :class="MenuIndex.logout" v-if="isShowLogout === 'production'" @click="logout">
      <span>退出</span>
      <i></i>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { Action, namespace } from 'vuex-class'
import { http } from '@/common/request'
import { getCookies, getSession } from '@/utils/cookiesUtil'
const userModuleVuex = namespace('user')
@Component({})
export default class MenuIndex extends Vue {
  private menuData: any = []
  private isShowLogout = process.env.NODE_ENV
  @userModuleVuex.Getter('getHostUrl') hostUrl!: any
  @userModuleVuex.Getter('getClientId') clientId!: any
  @Action('user/RESET_TOKEN') resetToken!: Function
  getMenuData() {
    interface MenuParams {
      box: any
    }
    const menuId = getCookies('menuId') != undefined ? getCookies('menuId') : ''
    http.get<MenuParams>(`/Menu/homePageList?menuId=${menuId}`).then(resp => {
      this.menuData = resp.data.box
    })
  }
  goInterPage(url, id) {
    http
      .post<IResponse>(`/Menu/newNenuList`, {
        id: id,
        roles: getCookies('roles') != undefined ? getCookies('roles') : ''
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
  goNewPage(url, id) {
    switch (id) {
      case '10000':
      case '30000':
      case '40000':
        this.goInterPage(url, id)
        break
      case '20000':
      case '60000':
        window.open(url, '_blank')
        break
      default:
        this.goOutPage(url, id)
    }
  }
  goOutPage(url, id) {
    const codeUrl = `${url}?code=${getCookies('token')}&uname=${getCookies('uname')}`
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
    this.getMenuData()
  }
}
</script>
<style module="MenuIndex">
.cotainer {
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: url('~@/assets/images/sys/menuBg.jpg') no-repeat;
  background-size: cover;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  z-index: 1;
}
.title {
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
  margin: 0 10px 10px;
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
.logout {
  display: flex;
  position: absolute;
  top: 30px;
  right: 30px;
  color: #2593f2;
  font-size: 15px;
  align-items: center;
  cursor: pointer;
}
.logout:hover {
  color: #80bdf3;
}
.logout i {
  display: inline-block;
  margin-left: 10px;
  width: 14px;
  height: 14px;
  background: url('~@/assets/images/menu/exit.png') no-repeat;
}
</style>
