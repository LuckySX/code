<!--
 * @Author: shixiao
 * @Date: 2020-03-20 16:57:30
 * @LastEditTime: 2020-08-14 10:26:32
 * @LastEditors: fuping
 * @LastEditTime: 2020-05-07 16:02:29
 * @LastEditors: fuping
 * @Description: In User Settings Edit
 * @FilePath: \sz-new-FE\src\views\DDos\DDosDetails\index.vue
 -->
<template>
  <div :class="details.mainBox">
    <el-button :class="details.exitButton" type="primary" @click="handleExitButton">返回</el-button>
    <div :class="details.chartBox">
      <div :class="details.lineBox">
        <BorderBox :title="activeTitle + '-48小时流量回溯'" type="line">
          <BaseCharts :option="lineStockOption" :theme="'vintage'"></BaseCharts>
        </BorderBox>
      </div>
      <div :class="details.pieBox">
        <div :class="details.pieTop">
          <BorderBox title="协议类型占比" type="pie">
            <BaseCharts :option="pieTypeOption" :theme="'vintage'"></BaseCharts>
          </BorderBox>
        </div>
        <div :class="details.pieBottom">
          <BorderBox title="被攻击端口分布TOP5" type="pie">
            <BaseCharts :option="peiTopOption" :theme="'vintage'"></BaseCharts>
          </BorderBox>
        </div>
      </div>
    </div>
    <div :class="details.tabaleBox">
      <BorderBox title="攻击源列表" type="table">
        <div :class="details.tableForm">
          <VForm :option="tableFormOp" ref="tableForm"></VForm>
        </div>
        <div :class="details.tableContent">
          <VTable
            :option="tableOpt"
            ref="table"
            :height="tableHeight"
            @handleCurrentPageChange="handleCurrentChange"
            @handleSizePageChange="handleSizeChange"
            @handleSelectionChange="handlerSelctAllClick"
          ></VTable>
        </div>
      </BorderBox>
    </div>
  </div>
</template>
<script lang="ts">
// @ is an alias to /src
import { Component, Vue, Ref } from 'vue-property-decorator'
import BorderBox from '@/components/Common/BorderBox.vue'
import Table from './components/Table.vue'
import { http } from '@/common/request'
import { pieOption } from '@/views/Common/charts/pieOption'
import { lineOption } from '@/views/Common/charts/chartsOption'
import replace from 'lodash/replace'
import trim from 'lodash/trim'
import defaultsDeep from 'lodash/defaultsDeep'
import { getCookies } from '@/utils/cookiesUtil'
import { getParmas } from '@/utils/assist'
Component.registerHooks(['beforeRouteEnter'])
const UrlProtocol = '/newDDoS/getProtocolProportion'
const UrlDportTop5 = '/newDDoS/getDportTop5'
const UrlFlowCurve = '/newDDoS/getFlowCurve'
const UrlAttackers = '/newDDoS/getAttackers'
const UrlExport = '/newDDoS/excelExport'
interface TableItems {
  endTime: string | (string | null)[]
  beginTime: string | (string | null)[]
  sIp: string
  protocol: string
  page: number
  sort: string
  order: string
  rows: number
  eventId: string | (string | null)[]
  ip?: string | (string | null)[]
}
interface ChartsItems {
  endTime: string | (string | null)[]
  beginTime: string | (string | null)[]
  eventId: string | (string | null)[]
  ip?: string | (string | null)[]
}
interface ChartsDataParams {
  code: number
  data: number[]
  message: string
}
@Component({
  components: {
    BorderBox,
    Table
  }
})
export default class Details extends Vue {
  @Ref() readonly tableForm!: VP.VForm
  @Ref() readonly table!: VP.VTable
  private lineOption = lineOption
  private pieOption = pieOption
  private isExport = true
  private tableData: number[] = []
  private exportTitle: string
  private tableHeight = '80%'
  private activeTitle: string | (string | null)[] = ''
  private paramIp: string | (string | null)[]
  private chartsParme: ChartsItems = {
    //事件结束时间
    endTime: '',
    //事件开始时间
    beginTime: '',
    //被攻击id
    eventId: ''
  }
  private tableParme: TableItems = {
    endTime: '',
    beginTime: '',
    eventId: '',
    sIp: '',
    protocol: '',
    sort: 'volume',
    order: 'desc',
    page: 1,
    rows: 5
  }
  // 协议类型
  private protocolOpt = [
    { name: '全部', value: '' },
    { name: 'TCP SYN', value: 1 },
    { name: 'ICMP', value: 2 },
    { name: 'DNS Flood', value: 3 },
    { name: 'UDP Flood', value: 4 },
    { name: 'HTTP Flood', value: 5 },
    { name: 'TCP Flood', value: 6 },
    { name: 'NTP Flood', value: 7 }
  ]
  // 导出 参数
  private exportParams: string
  private pieBaseOption = {
    series: [
      {
        radius: '50%',
        data: []
      }
    ]
  } as any
  private pieBaseTopOption = {
    series: [
      {
        radius: '50%',
        data: []
      }
    ]
  } as any
  private lineBaseTopOption = {
    grid: {
      left: 30
    },
    yAxis: {
      name: 'GB',
      data: []
    }
  } as any
  private pieTypeOption: any = defaultsDeep(this.pieBaseOption, pieOption)
  private peiTopOption: any = defaultsDeep(this.pieBaseTopOption, pieOption)
  private lineStockOption: any = defaultsDeep(this.lineBaseTopOption, lineOption)
  private tableFormOp: any = {
    inline: true,
    btnPos: 'left',
    items: [
      {
        label: '攻击者IP：',
        type: 'text',
        comOpt: {
          id: 'sIp',
          width: 150,
          disabled: false,
          show: true,
          placeholder: '请输入IP',
          value: ''
        }
      },
      {
        label: '协议类型：',
        type: 'select',
        comOpt: {
          id: 'protocol',
          width: 150,
          value: '',
          disabled: false,
          placeholder: '请选择',
          data: this.protocolOpt
        }
      }
    ],
    btns: [
      {
        comOpt: {
          id: 'query',
          value: '查询',
          width: 90,
          disabled: false,
          click: this.handleQueryBtn
        }
      },
      {
        comOpt: {
          id: 'reset',
          value: '重置',
          disabled: false,
          click: this.handleTableResetBtn
        }
      },
      {
        comOpt: {
          id: 'export',
          value: '导出',
          width: 90,
          disabled: false,
          click: this.handleExportBtn
        }
      }
    ]
  }
  private tableOpt: any = {
    stripe: true,
    selection: true,
    sortMode: 'single',
    defaultSort: [{ prop: 'volume', order: 'descending' }],
    sortChange: this.sortchange,
    column: [
      { name: '序号', value: 'orderNum', width: 55 },
      { name: '攻击者IP', value: 'sourceIp', sortable: 'custom', minWidth: 180, tooltip: true },
      { name: '起始时间', value: 'beginTime', sortable: 'custom', minWidth: 180, tooltip: true },
      { name: '攻击流量', value: 'volume', sortable: 'custom', tooltip: true },
      { name: '峰值速率(KB/S)', value: 'volumeRate', width: 150, sortable: 'custom', tooltip: true },
      { name: '协议类型', value: 'protocol', tooltip: true },
      { name: '包数', value: 'packets', sortable: 'custom', tooltip: true },
      { name: '连接数', value: 'concurrency', sortable: 'custom', tooltip: true },
      { name: '被攻击端口', value: 'dport', sortable: 'custom', tooltip: true, formatter: this.tooltipFormatter }
    ],
    data: [],
    pagination: true,
    pageOpt: {
      currentPage: 1,
      total: 0,
      pageSizes: [5, 10, 20, 30, 40, 50],
      pageSize: 5
    }
  }
  mounted() {
    // 获取router传参
    this.getRouteParam()
    // 获取图表表格数据
    this.getDatas()
  }
  handlerSelctAllClick(val: any) {
    const arr: any = []
    val.forEach(v => {
      arr.push(v.sourceIp)
    })
    this.exportParams = arr.toString()
  }
  sortchange(column, prop, order) {
    const name: string = Object.keys(column)[0]
    if (column[name] === 'descending') {
      this.tableParme.order = 'desc'
    } else {
      this.tableParme.order = 'asc'
    }
    this.tableParme.sort = name
    this.getTableDatas()
  }
  // 查询按钮
  handleQueryBtn() {
    this.tableParme = defaultsDeep(this.tableForm.getValue(), this.tableParme)
    this.tableParme.sIp = trim(this.tableParme.sIp)
    this.tableParme.page = 1
    // 点击查询按钮表格就恢复默认排序
    setTimeout(_ => {
      this.table.initSort()
    }, 0)
    this.getTableDatas()
  }
  // 表格重置按钮
  handleTableResetBtn() {
    this.tableForm.clearValue()
  }
  // 导出按钮
  handleExportBtn() {
    if (this.isExport) {
      const el: any = this.$refs.table
      const url =
        process.env.VUE_APP_BASE_API +
        `${UrlExport}?${getParmas(this.tableParme)}&type=2&eIp=${this.exportParams}&token=${getCookies('szCode')}&exportTitle=${this.exportTitle}`
      window.location.href = url
      this.$message({
        message: '导出成功',
        type: 'success'
      })
      // 清空被选状态
      el.$refs.multipleTable.clearSelection()
    } else {
      this.$message({
        message: '无数据',
        type: 'warning'
      })
    }
  }
  // 获取页面数据
  getDatas() {
    http.post<ChartsDataParams>(UrlProtocol, this.chartsParme).then((res: any) => {
      const { data } = res
      this.pieTypeOption.series[0].data = data
      if (data.length === 0) {
        this.pieTypeOption.series[0].data.push({
          name: '无数据',
          value: 0
        })
      }
    })
    http.post<ChartsDataParams>(UrlDportTop5, this.chartsParme).then((res: any) => {
      const { data } = res
      this.peiTopOption.series[0].data = data
      if (data.length === 0) {
        this.peiTopOption.series[0].data.push({
          name: '无数据',
          value: 0
        })
      }
    })
    http
      .post<ChartsDataParams>(UrlFlowCurve, {
        beginTime: this.chartsParme.beginTime,
        endTime: this.chartsParme.endTime,
        ip: this.paramIp
      })
      .then((res: any) => {
        const { data } = res
        const lineValue: any = []
        const lineTime: string[] = []
        data.forEach((i: any) => {
          lineValue.push({
            name: i.time,
            value: i.value,
            info: i.info
          })
          lineTime.push(i.time)
        })
        this.lineStockOption.xAxis.data = lineTime
        this.lineStockOption.series[0].data = lineValue
        if (data.length === 0) {
          this.lineStockOption.series[0].data.push({
            name: '无数据',
            value: 0
          })
        }
      })
    this.getTableDatas()
  }
  // 获取表格数据
  getTableDatas() {
    this.tableOpt.data = []
    http.post<ChartsDataParams>(UrlAttackers, this.tableParme).then((res: any) => {
      const { data } = res
      if (data.rows.length > 0) {
        this.isExport = true
      } else {
        this.isExport = false
      }
      this.tableOpt.data = data.rows
      // this.tableOpt.pageOpt.currentPage = data.pageNo
      this.tableOpt.pageOpt.total = data.total
    })
  }
  handleSizeChange(val) {
    this.tableParme.rows = val
    this.tableParme.page = 1
    this.tableOpt.pageOpt.currentPage = 1
    this.getTableDatas()
  }
  handleCurrentChange(val) {
    this.tableParme.page = val
    this.getTableDatas()
  }
  // 概览页面跳转传参
  getRouteParam() {
    const { beginTime, endTime, ip, id, number } = this.$route.query
    this.chartsParme.endTime = endTime
    this.tableParme.endTime = endTime
    this.chartsParme.beginTime = beginTime
    this.tableParme.beginTime = beginTime
    this.chartsParme.eventId = id
    this.tableParme.eventId = id
    this.paramIp = ip
    this.exportTitle = `${ip}-${number}`
    this.activeTitle = ip
  }
  // 返回按钮
  handleExitButton() {
    this.$router.push({
      path: '/DDosOverall',
      query: {
        tableShow: 'true'
      }
    })
    // this.$route.meta.isBack = true
  }
  //从攻击事件页面过来时缓存攻击页面
  beforeRouteEnter(to, from, next) {
    from.meta.keepAlive = true
    next()
  }
  // 格式化tooltip
  tooltipFormatter(row: any, column: any) {
    const data = row[column.property]
    return data.split(',').join('\n')
  }
}
</script>
<style module="details">
.mainBox {
  box-sizing: border-box;
  height: 100%;
}
.chartBox {
  display: flex;
  height: calc(50% - 5px);
}
.tabaleBox {
  margin-top: 10px;
  height: calc(50% - 5px);
}
.lineBox {
  flex: 0 0 60%;
  padding-right: 10px;
  height: 100%;
}
.tableContent {
  height: calc(90% - 10px);
}
.pieBox {
  flex: 1;
  padding-left: 10px;
  height: 100%;
}
.pieTop {
  margin-bottom: 10px;
  height: 49%;
}
.pieBottom {
  height: 48%;
}
.tableForm {
  text-align: left;
}
.exitButton {
  position: absolute;
  top: 48px;
  right: 23px;
}
</style>
