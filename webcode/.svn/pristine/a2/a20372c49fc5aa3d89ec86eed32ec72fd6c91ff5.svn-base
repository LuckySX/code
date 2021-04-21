<!--
 * @Author: niumiaomiao
 * @Date: 2020-03-23 13:31:22
 * @Description: 
 -->
<template>
  <div :class="Header.container">
    <div :class="Header.logo">
      <!-- LOGO -->
      <span :class="Header.logoTitle">{{ headerName }}</span>
      <span :class="Header.userName">您好，{{ userName }}</span>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import { getCookies } from '@/utils/cookiesUtil'

@Component({})
export default class Header extends Vue {
  private headerName = ''
  private userName = getCookies('szUserInfo') != undefined ? JSON.parse(getCookies('szUserInfo')).uname : ''
  @Watch('$route', { deep: true })
  private routePathChanged(val: any) {
    this.headerName = val.meta.familyName
    const defaultTitle = '安全监测感知平台'
    document.title = this.headerName ? this.headerName : defaultTitle
  }
}
</script>
<style module="Header">
.container {
  width: 100%;
  height: 100%;
  text-align: left;
}
.logo {
  display: flex;
  justify-content: space-between;
  margin-left: 10px;
  margin-right: 20px;
  color: #113c70;
  font-weight: bold;
}
.logoTitle {
  margin-left: 20px;
}
.userName {
  color: #022568;
  font-size: 14px;
  font-weight: normal;
  opacity: 0.75;
}
</style>
