<!--
 * @Author: fuping
 * @Date: 2020-06-19 09:59:49
 * @LastEditors: fuping
 * @LastEditTime: 2020-08-18 12:38:28
 * @Description: 
-->
<template>
  <div :class="domainServer.container">
    <div :class="domainServer.tablePart">
      <BorderBox title="RDNS访问次数" type="table">
        <div :class="domainServer.tableForm">
          <VForm :option="tableFormOp" ref="tableForm"></VForm>
        </div>
        <div :class="domainServer.tableContent">
          <VTable
            class="domainServer"
            :option="tableOpt"
            :height="tableHeight"
            ref="table"
            @handleCurrentPageChange="handleCurrentChange"
            @handleSizePageChange="handleSizeChange"
          ></VTable>
        </div>
      </BorderBox>
    </div>
    <div :class="domainServer.chartPart">
      <div :class="domainServer.chartBox">
        <BorderBox :title="activeTitle + '-访问次数分布趋势'" type="bar">
          <BaseCharts :option="lineOpt"></BaseCharts>
        </BorderBox>
      </div>
      <div :class="domainServer.chartBox">
        <BorderBox :title="activeTitle + '-地域访问分布'" type="pie">
          <BaseCharts :option="pieOption"></BaseCharts>
        </BorderBox>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { pieOption } from '@/views/Common/charts/pieOption'
import { lineOption } from '@/views/Common/charts/chartsOption'
import { Component, Vue, Ref } from 'vue-property-decorator'
import BorderBox from '@/components/Common/BorderBox.vue'
import { getCookies } from '@/utils/cookiesUtil'
import { defaultsDeep, trim, assign } from 'lodash'
import { http } from '@/common/request'
import dayjs from 'dayjs'
const UrlTable = '/newDomainServer/todayRndsVist'
const UrlLine = '/newDomainServer/visitTrend'
const UrlPie = '/newDomainServer/cityDistribution'
interface IDefaultTableItems {
  time: string
  sort: string
  order: string
  page: number
  rows: number
}
interface IDomainBar {
  xAxisData: string[]
  data: []
}
interface IDomainPie {
  name: string
  value: number
}
interface ITable {
  rows: number[]
  total: number
}
// 导出，error提示没有数据不能导出，success可以导出
enum exportFlag {
  success = 1,
  error = 0
}
@Component({
  components: {
    BorderBox
  }
})
export default class Overall extends Vue {
  @Ref() readonly tableForm!: VP.VForm
  @Ref() readonly table!: VP.VTable
  private activeTitle = ''
  private pieLegendData: any = []
  private tableData: number[] = []
  private tableFormOp: any = {
    inline: true,
    btnPos: 'left',
    items: [
      {
        label: '',
        type: 'date',
        comOpt: {
          id: 'time',
          clearable: false,
          value: dayjs().format('YYYY-MM-DD'),
          type: 'date',
          disabled: false,
          pickOptions: {
            disabledDate: this.startDisable
          },
          change: this.handleTableQueryBtn
        }
      }
    ]
  }
  // 表格条件选择器
  private tableformItems: IDefaultTableItems = {
    time: '',
    sort: 'reqcount',
    order: 'desc',
    page: 1,
    rows: 5
  }
  private tableOpt: any = {
    stripe: true,
    sortMode: 'single',
    defaultSort: [{ prop: 'reqcount', order: 'descending' }],
    sortChange: this.sortChange,
    rowClick: this.tableRowClick,
    highlightCurrentRow: true,
    column: [
      { name: '序号', value: 'orderNum', width: 55 },
      { name: 'IP', value: 'ip', minWidth: 120, sortable: 'custom', tooltip: true },
      { name: '解析次数', value: 'reqcount', minWidth: 100, sortable: 'custom', tooltip: true },
      { name: '访问主机数量', value: 'sipcount', minWidth: 130, sortable: 'custom', tooltip: true },
      { name: '位置', value: 'address', minWidth: 100, sortable: 'custom', tooltip: true },
      { name: '运营商', value: 'isp', minWidth: 100, sortable: 'custom', tooltip: true },
      { name: '备注', value: 'reg', minWidth: 100, tooltip: true }
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
  private tableHeight = 'calc(100% - 32px)'
  private newPeiOp = {
    tooltip: {
      show: false
    },
    legend: {
      show: true,
      itemWidth: 3,
      itemHeight: 15,
      left: 'auto',
      right: '10',
      top: 'top',
      formatter: this.legendFormat
    },
    series: [
      {
        type: 'pie',
        center: ['30%', '50%'],
        radius: ['40%', '60%'],
        avoidLabelOverlap: false,
        itemStyle: {
          normal: {
            borderColor: '#fff',
            borderWidth: 2
          }
        },
        label: {
          show: false,
          position: 'center'
        },
        emphasis: {
          label: {
            show: true,
            formatter: params => {
              const value = params.data.info ? params.data.info.split('：')[2] : params.value
              return value + '\n' + params.name
            }
          }
        },
        data: []
      }
    ]
  } as any
  private newLineOp = {
    tooltip: {
      formatter: (params: any) => {
        return `时间：${params.name}</br>
                访问量：${params.value}万次`
      }
    },
    yAxis: {
      name: '访问量/万次'
    }
  } as any
  private lineOpt: any = defaultsDeep(this.newLineOp, lineOption)
  private pieOption: any = defaultsDeep(this.newPeiOp, pieOption)
  mounted() {
    this.getTableData()
  }
  // methods
  getChartData() {
    this.getLineData()
    this.getPieData()
  }
  // 表格查询按钮
  handleTableQueryBtn() {
    // 点击查询按钮表格就恢复默认排序
    setTimeout(_ => {
      this.table.initSort()
    }, 0)
    this.getTableData()
  }
  // 表格重置按钮
  handleTableResetBtn() {
    this.tableForm.clearValue()
  }
  getTableData() {
    this.tableOpt.data = []
    assign(this.tableformItems, this.tableForm.getValue())
    http.post<ITable>(UrlTable, this.tableformItems).then((resp: any) => {
      const {
        data: { rows, total, pageNo }
      } = resp
      this.activeTitle = rows[0] ? rows[0].ip : ''
      this.tableOpt.data = rows
      this.tableOpt.pageOpt.total = total
      this.getChartData()
    })
  }
  // 表格点击事件
  tableRowClick(e) {
    this.activeTitle = e.ip
    this.getChartData()
  }
  // 获取柱图数据
  getLineData() {
    http
      .post<IDomainBar>(UrlLine, {
        ip: this.activeTitle,
        time: this.tableForm.getValue().time
      })
      .then((resp: any) => {
        const { data } = resp
        if (data.length === 0) {
          data.push({
            name: '无数据',
            value: 0
          })
        }
        const xAxisData: string[] = []
        data.forEach((v: any) => {
          xAxisData.push(v.name)
        })
        this.lineOpt.xAxis.data = xAxisData
        this.lineOpt.series[0].data = data
      })
  }
  getPieData() {
    http
      .post<IDomainPie>(UrlPie, {
        ip: this.activeTitle,
        time: this.tableForm.getValue().time
      })
      .then((resp: any) => {
        const { data } = resp
        if (data.length === 0) {
          data.push({
            name: '无数据',
            value: 0
          })
        }
        let sum = 0
        const legend: any = {}
        const legendData: any = []
        // 计算饼图数据合
        data.forEach(v => {
          sum += Number(v.value)
        })
        // 求饼图legend数据，及计算legend百分比
        data.map(v => {
          legendData.push(v.name)
          return (legend[v.name] = Number(v.value) ? ((Number(v.value) / sum) * 100).toFixed(2) + '%' : '')
        })
        this.pieLegendData = legend
        this.pieOption.legend.data = legendData
        this.pieOption.series[0].data = data
      })
  }
  legendFormat(v) {
    return v + ' ' + this.pieLegendData[v]
  }
  // 点击页码
  handleCurrentChange(page: number) {
    this.tableformItems.page = page
    this.getTableData()
  }
  handleSizeChange(val: number) {
    this.tableformItems.rows = val
    this.tableformItems.page = 1
    this.tableOpt.pageOpt.currentPage = 1
    this.getTableData()
  }
  // 排序
  sortChange(column: object) {
    const name: string = Object.keys(column)[0]
    if (column[name] === 'descending') {
      this.tableformItems.order = 'desc'
    } else {
      this.tableformItems.order = 'asc'
    }
    this.tableformItems.sort = name
    this.getTableData()
  }
  // 时间选择范围，30天内
  startDisable(time: Date) {
    const beginDateVal = Date.now()
    if (beginDateVal) {
      return time.getTime() < new Date(beginDateVal).getTime() - 30 * 24 * 60 * 60 * 1000 || time.getTime() > new Date(beginDateVal).getTime()
    }
  }
  // 格式化参数
  getParmas(data: any) {
    let parmas: any = ''
    for (const key in data) {
      parmas += `${key}=${data[key]}&`
    }
    return parmas.slice(0, -1)
  }
}
</script>
<style module="domainServer">
.container {
  width: 100%;
  height: 100%;
  text-align: left;
}
.chartPart {
  display: flex;
  width: 100%;
  height: calc(50% - 5px);
  justify-content: space-between;
}
.chartBox:nth-child(1) {
  width: calc(65% - 5px);
  height: 100%;
}
.chartBox:nth-child(2) {
  width: calc(35% - 5px);
  height: 100%;
}
.tablePart {
  position: relative;
  width: 100%;
  height: calc(50% - 5px);
  margin-bottom: 10px;
}
.tableForm {
  position: absolute;
  top: 0;
  right: 0;
}
.tableContent {
  height: calc(100% - 0px);
}
</style>
