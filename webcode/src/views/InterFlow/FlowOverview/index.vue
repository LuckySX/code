<!--
 * @Author: fuping
 * @Date: 2020-03-30 14:19:21
 * @LastEditors: fuping
 * @LastEditTime: 2020-08-21 11:39:29
 * @Description: 
 -->

<template>
  <div :class="flowOverview.main">
    <div :class="flowOverview.topBox">
      <div :class="flowOverview.tabBox" class="sz">
        <el-tabs v-model="activeName" type="card" @tab-click="handleTabClick">
          <el-tab-pane label="某市" name="2"></el-tab-pane>
        </el-tabs>
      </div>
      <div :class="flowOverview.itemsBox">
        <div :class="flowOverview.itemsInfoBox">
          <!-- <span>{{ time }}</span> -->
          <div v-if="activeName === '2'" :class="flowOverview.itemsInfo">
            <span>总流量：100GB</span>
            <!-- <span>用户IP数：{{ totalInfo.sipCnt || 0 }}</span> -->
            <span>应用流量：{{ totalInfo.appVolume || 0 }}70GB</span>
          </div>
        </div>
        <div :class="flowOverview.itemsSubmitBox" v-if="activeName === '2'" style="display: none">
          <el-select v-model="inputType" class="sz" placeholder="请选择输入类型" style="width: 100px; margin-right: 10px;" @change="handleChange">
            <el-option v-for="item in filedOpt" :key="item.value" :label="item.label" :value="item.value"></el-option>
          </el-select>
          <el-input v-model="inputValue" :placeholder="placeholder" style="width: 160px;"></el-input>
          <el-button type="primary" style="margin: 0 10px;" @click="handleQuery">查询</el-button>
          <el-button type="primary" style="width: 110px" @click="handleJump">
            进入重点资产
          </el-button>
        </div>
      </div>
      <div :class="flowOverview.mapBox">
        <template v-if="activeName === '2'">
          <BaseCharts :option="mapOption" key="2"></BaseCharts>
        </template>
        <template v-else-if="activeName === '1'">
          <BaseCharts :option="chinaOption" key="1"></BaseCharts>
        </template>
        <template v-else>
          <BaseCharts :option="worldOption" key="0"></BaseCharts>
        </template>
      </div>
    </div>
    <div :class="flowOverview.bottomBox">
      <div :class="flowOverview.chartBox">
        <BorderBox title="用户访问 TOP5" type="table">
          <VTable :option="tableOptUser" :height="tableHeight"></VTable>
        </BorderBox>
      </div>
      <div :class="flowOverview.chartBox">
        <BorderBox title="应用流量 TOP5" type="table">
          <VTable :option="tableOptFlow" :height="tableHeight"></VTable>
        </BorderBox>
      </div>
      <div :class="flowOverview.chartBox">
        <BorderBox title="运营商流量占比" type="pie">
          <BaseCharts :option="pieOption"></BaseCharts>
        </BorderBox>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import BorderBox from '@/components/Common/BorderBox.vue'
import { doublePieOption } from '@/views/Common/charts/doublePieOption'
import { scatterMapOption } from '@/views/Common/charts/scatterMap'
import { lineMapOption } from '@/views/Common/charts/lineMap'
import geoCoordSZ from '@/components/Charts/map/coord/shenzhen.js'
import geoCoordChina from '@/components/Charts/map/coord/china.js'
import geoCoordWorld from '@/components/Charts/map/coord/world.js'
import { http } from '@/common/request'
import dayjs from 'dayjs'
import defaultsDeep from 'lodash/defaultsDeep'
import trim from 'lodash/trim'
import _ from 'lodash'
interface IDefaultInputItems {
  time: string
}
interface IParams {
  code: number
  data: number[]
  message: string
}
@Component({
  components: {
    BorderBox
  }
})
export default class FlowOverview extends Vue {
  private time: string = dayjs()
    .subtract(1, 'day')
    .format('YYYY年MM月DD日')
  private time1: string = dayjs()
    .subtract(1, 'day')
    .format('YYYY-MM-DD')
  private inputType = 'dip'
  private inputValue = ''
  private activeName = '2'
  private placeholder = '请输入IP地址'
  private tableOptUser: any = {
    stripe: true,
    defaultSort: [{ prop: 'sumbytes', order: 'descending' }],
    column: [
      { name: '排名', value: 'orderNum', fixed: 'left', width: 50, align: 'center' },
      { name: 'IP', value: 'dip', minWidth: 130, tooltip: true },
      { name: '总流量', value: 'sumbytes', minWidth: 100, tooltip: true }
    ],
    data: [
      {
        orderNum: '1',
        dip: '102.2304.100',
        sumbytes: '2100'
      },
      {
        orderNum: '2',
        dip: '4.2304.00',
        sumbytes: '1003'
      },
      {
        orderNum: '3',
        dip: '15.2304.00',
        sumbytes: '1400'
      },
      {
        orderNum: '5',
        dip: '11.2304.00',
        sumbytes: '1040'
      },
      {
        orderNum: '5',
        dip: '102.2304.100',
        sumbytes: '1040'
      }
    ],
    pagination: false
  }
  private tableOptFlow: any = {
    stripe: true,
    defaultSort: [{ prop: 'sumbytes', order: 'descending' }],
    column: [
      { name: '排名', value: 'orderNum', fixed: 'left', width: 50, align: 'center' },
      { name: '应用名称', value: 'protocol', minWidth: 120, tooltip: true },
      { name: '应用类型', value: 'protocolClass', minWidth: 100, tooltip: true },
      { name: '总流量', value: 'sumbytes', minWidth: 100, tooltip: true }
    ],
    data: [
      {
        orderNum: '1',
        protocol: '102.2304.100',
        protocolClass: '102.2304.100',
        sumbytes: '2100'
      },
      {
        orderNum: '2',
        protocol: '102.2304.100',

        protocolClass: '4.2304.00',
        sumbytes: '1003'
      },
      {
        orderNum: '3',
        protocol: '102.2304.100',

        protocolClass: '15.2304.00',
        sumbytes: '1400'
      },
      {
        orderNum: '5',
        protocol: '102.2304.100',

        protocolClass: '11.2304.00',
        sumbytes: '1040'
      },
      {
        orderNum: '5',
        protocol: '102.2304.100',

        protocolClass: '102.2304.100',
        sumbytes: '1040'
      }
    ],
    pagination: false
  }
  private tableHeight = '100%'
  private totalInfo = {
    dip: 0,
    sipCnt: 0,
    appVolume: 0,
    volume: 0
  }
  // 输入类型
  private filedOpt = [
    { label: 'IP', value: 'dip' },
    { label: '域名', value: 'domain' },
    { label: '单位', value: 'dip_company_name' } //后端命名，不能驼峰，数据库字段是带下划线的
  ]
  private placeHoderGroup = {
    dip: '请输入IP地址',
    domain: '请输入域名',
    dipCompanyName: '请输入单位名称'
  }
  private chinaNewOption = {
    tooltip: {
      formatter: (data: any) => {
        return `流量: ${data.data.volume}MB<br>
              连接数: ${data.data.links}个`
      }
    },
    geo: {
      map: 'china',
      aspectScale: 0.85,
      zoom: 1
    }
  } as any
  private worldNewOption = {
    tooltip: {
      formatter: (data: any) => {
        return `流量: ${data.data.volume}MB<br>
              连接数: ${data.data.links}个`
      }
    },
    geo: {
      map: 'world'
    },
    series: [
      {},
      {},
      {
        lineStyle: {
          normal: {
            width: 0.05,
            color: '#4b93f2'
          }
        }
      }
    ]
  } as any
  private mapOption: any = defaultsDeep(scatterMapOption)
  private chinaOption: any = defaultsDeep(this.chinaNewOption, lineMapOption)
  private worldOption: any = defaultsDeep(this.worldNewOption, lineMapOption)
  private pieOption: any = defaultsDeep(doublePieOption)
  mounted() {
    this.init()
  }
  init() {
    this.getInfoData()
    this.getMapData()
    this.getUserData()
    this.getProtocolData()
    this.getOperatorData()
  }
  initTabMap() {
    this.getTabMapData()
    this.getUserData()
    this.getProtocolData()
    this.getOperatorData()
  }
  handleQuery() {
    this.inputValue = trim(this.inputValue)
    // 输入框不能为空
    if (this.inputValue != '') {
      // 校验ip
      if (this.inputType === 'dip') {
        if (!this.isValidIP(this.inputValue)) {
          this.$message({
            message: '请输入正确的IP',
            type: 'error'
          })
        } else {
          this.getMapData()
        }
      } else {
        this.getMapData()
      }
    } else {
      this.$message({
        message: '请输入查询内容',
        type: 'warning'
      })
    }
  }
  // 获取头部信息
  getInfoData() {
    interface IInterFlowTotalInfo {
      dip: number
      sipCnt: number
      appVolume: number
      volume: number
    }
    http
      .post<IInterFlowTotalInfo>('/interFlow/totalInfo', { time: this.time1 })
      .then(resp => {
        const { data } = resp
        this.totalInfo = data
      })
  }
  // 获取深圳地图数据
  getMapData() {
    type NewType = IDefaultInputItems
    const params: NewType = {
      time: this.time1
    }
    http.post<IParams>('/interFlow/map', params).then((resp: any) => {
      const { data } = resp
      this.mapOption.series[0].data = []
      const scatterData: object[] = []
      // 地图数据拼接
      data.map((item, i) => {
        const geoCoord = geoCoordSZ[item.name]
        if (geoCoord) {
          scatterData.push({
            name: item.name,
            value: geoCoord.concat(item.value),
            time: this.time1
          })
        }
      })
      this.mapOption.series[0].data = scatterData
    })
  }
  // 获取中国世界地图数据
  getTabMapData() {
    http
      .post<IParams>('/interFlow/worldMap', { area: Number(this.activeName), time: this.time1 })
      .then((resp: any) => {
        const { data } = resp
        if (this.activeName === '0') {
          data.length > 0 && this.getWorldMap(data)
        } else {
          data.length > 0 && this.getChinaMap(data)
        }
      })
  }
  // 中国地图数据处理
  getChinaMap(data) {
    this.chinaOption.series[0].data = []
    this.chinaOption.series[1].data = []
    this.chinaOption.series[2].data = []
    const scatterData: object[] = []
    const scatterData1: object[] = []
    const lineData: object[] = []
    const numbers: number[] = []
    const newData = data.sort((a, b) => {
      return b.volume - a.volume
    })
    // 地图数据拼接
    newData.map((item, i) => {
      numbers.push(item.volume)
      scatterData.push({
        name: item.target,
        value: [item.tLongitude, item.tLatitude].concat(item.value),
        symbolSize: item.target === '深圳' ? 3 : 0,
        label: {
          normal: {
            show: i < 1
          }
        }
      })
      scatterData1.push({
        name: item.source,
        value: [item.sLongitude, item.sLatitude].concat(item.value),
        symbolSize: item.source !== '深圳' && i < 3 ? 3 : 1,
        label: {
          normal: {
            show: i < 5
          }
        }
      })
      lineData.push({
        source: item.source,
        target: item.target,
        volume: item.volume,
        links: item.links,
        coords: [
          [item.sLongitude, item.sLatitude],
          [item.tLongitude, item.tLatitude]
        ]
      })
    })
    this.chinaOption.series[0].data = scatterData
    this.chinaOption.series[1].data = scatterData1
    this.chinaOption.series[2].data = lineData
  }
  // 世界地图数据处理
  getWorldMap(data) {
    this.worldOption.series[0].data = []
    this.worldOption.series[1].data = []
    this.worldOption.series[2].data = []
    const scatterData: object[] = []
    const scatterData1: object[] = []
    const lineData: object[] = []
    const sortMapData = data.sort((a, b) => {
      return b.volume - a.volume
    })
    const mapData = sortMapData.map(item => {
      // 地图经纬度处理
      if (item.sLongitude < -30) {
        item.sLongitude += 160
      } else {
        item.sLongitude -= 200
      }
      if (item.tLongitude < -30) {
        item.tLongitude += 160
      } else {
        item.tLongitude -= 200
      }
      return {
        source: item.source,
        target: item.target,
        volume: item.volume,
        links: item.links,
        targetCoords: [Number(item.tLongitude), Number(item.tLatitude)],
        sourceCoords: [Number(item.sLongitude), Number(item.sLatitude)]
      }
    })
    mapData.forEach((item, i) => {
      scatterData.push({
        name: item.target,
        value: item.targetCoords.concat(item.value),
        symbolSize: item.target === '深圳' ? 3 : 0,
        label: {
          normal: {
            show: i < 1
          }
        }
      })
      scatterData1.push({
        name: item.source,
        value: item.sourceCoords.concat(item.value),
        symbolSize: item.source !== '深圳' && i < 3 ? 5 : 1,
        label: {
          normal: {
            show: i < 5
          }
        }
      })
      lineData.push({
        source: item.source,
        target: item.target,
        volume: item.volume,
        links: item.links,
        coords: [item.sourceCoords, item.targetCoords]
      })
    })
    this.worldOption.series[0].data = scatterData
    this.worldOption.series[1].data = scatterData1
    this.worldOption.series[2].data = lineData
  }
  // 获取用户访问TOP5数据
  getUserData() {
    http
      .post<IParams>('/interFlow/userTop', { area: Number(this.activeName), time: this.time1 })
      .then((resp: any) => {
        const { data } = resp
        ///this.tableOptUser.data = data
      })
  }
  // 获取应用流量TOP5数据
  getProtocolData() {
    http
      .post<IParams>('/interFlow/protocolTop', { area: Number(this.activeName), time: this.time1 })
      .then((resp: any) => {
        const { data } = resp
        // this.tableOptFlow.data = data
      })
  }
  // 获取运营商流量占比数据
  getOperatorData() {
    interface IOperatorProtocol {
      operators: any
      protocols: any
    }
    http
      .post<IOperatorProtocol>('/interFlow/operatorProtocol', { area: Number(this.activeName), time: this.time1 })
      .then(resp => {
        const {
          data: { operators, protocols }
        } = resp
        this.pieOption.series[0].data = operators
        this.pieOption.series[1].data = protocols
        const legend1 = operators.map(v => v.name)
        const legend2 = protocols.map(v => v.name)
        this.pieOption.legend.data = [...legend1, ...legend2]
      })
  }
  // 选择输入类型事件
  handleChange(v: string) {
    if (v != 'dip' && v != 'domain') {
      v = 'dipCompanyName'
    }
    this.placeholder = this.placeHoderGroup[v]
    this.inputValue = ''
  }
  // 点击跳转事件
  handleJump() {
    this.$router.push({
      path: '/KeyAsset',
      query: {
        type: this.inputType, // 下拉框类型
        value: this.inputValue // 输入值
      }
    })
  }
  // IP校验
  isValidIP(ip) {
    const reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
    return reg.test(ip)
  }
  // tab事件
  handleTabClick(tab: any) {
    this.activeName = tab.name
    if (this.activeName !== '2') {
      this.initTabMap()
    } else {
      this.init()
    }
  }
}
</script>
<style module="flowOverview">
.main {
  width: 100%;
  height: 100%;
  text-align: left;
}
.topBox {
  position: relative;
  height: calc(100% - 257px - 10px);
  background: #fff;
  margin-bottom: 10px;
}
.bottomBox {
  height: 257px;
  display: flex;
  justify-content: space-between;
}
.itemsBox {
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  z-index: 9;
  background: rgba(255, 255, 255, 0.5);
  padding: 5px 10px 2px;
}
.mapBox {
  width: 100%;
  height: 100%;
}
.itemsInfoBox span {
  color: #666;
  margin-right: 15px;
}
.chartBox {
  flex: 1;
  margin-right: 10px;
}
.chartBox:last-child {
  margin-right: 0;
}
.tabBox {
  position: absolute;
  top: -30px;
  right: 0;
  z-index: 10;
}
.itemsInfo {
  display: inline-block;
}
</style>
