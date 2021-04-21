<!--
 * @Author: fuping
 * @Date: 2020-06-19 09:59:49
 * @LastEditors: niumiaomiao
 * @LastEditTime: 2020-07-22 09:59:00
 * @Description: 域名安全概览
-->
<template>
  <div :class="domainOverall.container">
    <div :class="domainOverall.chartPart">
      <BorderBox title="近两周DNS访问统计" type="bar">
        <BaseCharts :option="barOption"></BaseCharts>
      </BorderBox>
    </div>
    <div :class="domainOverall.chartPart">
      <div :class="domainOverall.chartBox">
        <BorderBox title="今日DNS访问统计" type="line">
          <BaseCharts :option="lineDoubleOpt"></BaseCharts>
        </BorderBox>
      </div>
      <div :class="domainOverall.chartBox">
        <BorderBox title="今日访问地市分布TOP10" type="pie">
          <BaseCharts :option="pieOption"></BaseCharts>
        </BorderBox>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { doubleBarOption } from '@/views/Common/charts/doubleBarOption'
import { pieOption } from '@/views/Common/charts/pieOption'
import { lineDoubleOpt } from '@/views/Common/charts/lineDoubleOpt'
import { Component, Vue, Ref } from 'vue-property-decorator'
import BorderBox from '@/components/Common/BorderBox.vue'
import { defaultsDeep, trim, assign } from 'lodash'
import { http } from '@/common/request'
import dayjs from 'dayjs'
const UrlBar = '/domainSafe/twoWeeks'
const UrlLine = '/domainSafe/selectAllProvince'
const UrlPie = '/domainSafe/selectProvince'
interface IDomainBar {
  xAxisData: string[]
  data: []
}
interface IDomainPie {
  name: string
  value: number
}
interface IDomainTable {
  rows: number[]
  total: number
}
@Component({
  components: {
    BorderBox
  }
})
export default class Overall extends Vue {
  private time: any = dayjs().format('YYYY-MM-DD')
  private pieLegendData: any = []
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
        itemStyle: {
          normal: {
            borderColor: '#fff',
            borderWidth: 2
          }
        },
        avoidLabelOverlap: false,
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
  private newbarOp = {
    tooltip: {
      formatter: (data: any) => {
        let tip = `时间: ${data[0].name}<br>`
        for (let i = 0; i < data.length; i++) {
          const unit = i === 0 ? '万次' : '万个'
          tip += `${data[i].seriesName}: ${data[i].value}${unit}<br>`
        }
        return tip
      }
    },
    yAxis: [
      {
        name: '访问次数/万次'
      },
      {
        name: 'IP量/万个'
      }
    ],
    series: [
      {
        name: '访问次数'
      },
      {
        name: 'IP量'
      }
    ]
  } as any
  private barOption: any = defaultsDeep(this.newbarOp, doubleBarOption)
  private pieOption: any = defaultsDeep(this.newPeiOp, pieOption)
  private lineDoubleOpt: any = defaultsDeep(lineDoubleOpt)
  mounted() {
    this.init()
  }
  // methods
  init() {
    this.getBarData()
    this.getLineData()
    this.getPieData()
  }
  getBarData() {
    http.post<IDomainBar>(UrlBar).then((resp: any) => {
      const { data } = resp
      const { legendData, xAxisData, seriesData } = this.getBarStackData(this.dataFormatLine(data))
      this.barOption.series[0].data = seriesData[0]
      this.barOption.series[1].data = seriesData[1]
      this.barOption.xAxis.data = xAxisData
      // this.barOption.legend.data = legendData
    })
  }
  getLineData() {
    http
      .post(UrlLine, {
        time: this.time
      })
      .then((resp: any) => {
        const { data } = resp
        const { legendData, xAxisData, seriesData } = this.getBarStackData(this.dataFormatLine(data))
        // this.lineDoubleOpt.legend.data = legendData
        this.lineDoubleOpt.xAxis.data = xAxisData
        this.lineDoubleOpt.series[0].data = seriesData[0]
        this.lineDoubleOpt.series[1].data = seriesData[1]
        if (data.length === 0) {
          resp.data = [{ name: '无数据', value: 0 }]
        }
      })
  }
  getPieData() {
    http
      .post<IDomainPie>(UrlPie, { time: this.time })
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
  // 面积图数据处理
  dataFormatLine(data: any, unit = '万次') {
    const arr1: string[] = []
    const arr2: any[] = []
    data = data.map((i: any) => {
      arr1.push(i.category)
      return {
        name: i.name,
        value: i.value,
        category: i.category,
        unit: unit
      }
    })
    const arr3 = Array.from(new Set(arr1))
    arr3.forEach((i: any) => {
      const arr4: any[] = []
      data.forEach((v: any) => {
        if (v.category === i) {
          arr4.push(v)
        }
      })
      arr2.push(arr4)
    })
    return arr2
  }
  getBarStackData(data: any) {
    const seriesData: any[] = []
    const legendData: Array<string> = []
    const xAxisData: Array<string> = []
    if (data && data != undefined && data.length > 0) {
      data[0].forEach((i: any) => {
        xAxisData.push(i.name)
      })
      data.forEach((item: any, index: any) => {
        const arry: any = []
        legendData.push(item[0].category)
        item.forEach((value: any) => {
          arry.push(value.value)
        })
        seriesData.push(arry)
      })
    }
    return { legendData, xAxisData, seriesData }
  }
}
</script>
<style module="domainOverall">
.container {
  width: 100%;
  height: 100%;
  text-align: left;
}
.chartPart {
  display: flex;
  width: 100%;
  height: calc(50% - 5px);
  margin-bottom: 10px;
  justify-content: space-between;
}
.chartPart:nth-child(1) {
  margin-bottom: 10px;
}
.chartBox:nth-child(1) {
  width: calc(65% - 5px);
  height: 100%;
}
.chartBox:nth-child(2) {
  width: calc(35% - 5px);
  height: 100%;
}
</style>
