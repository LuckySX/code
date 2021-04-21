<!-- 
 * @Author       fuping
 * @Date         2020-03-21
 * @LastEditors: fuping
 * @LastEditTime: 2020-04-21 13:44:48
 * @Description  ECHARTS散点图
 * @param        { Array } renderData [详细类型数据，{name,value}]
 *
-->
<template>
  <div style="width:100%;height:100%" ref="ele"></div>
</template>

<script lang="ts">
import { Component, Prop, Watch, Vue } from 'vue-property-decorator'
import echarts, { ECharts, EChartOption } from 'echarts'
import _ from 'lodash'
declare global {
  interface Window {
    attachEvent(type: string, callback: any): void
    detachEvent(type: string, callback: any): void
  }
}
@Component
export default class EchartsScatter extends Vue {
  @Prop() private renderData!: any
  @Prop() private option!: any
  private EC!: ECharts
  private op!: any
  @Watch('renderData', { deep: true })
  private dataChanged(val: any, oldVal: any) {
    this.renderEcharts()
  }
  private resizeEventHandler(): void {
    this.EC.resize()
  }
  private mounted() {
    this.initChart()
  }
  private initChart() {
    this.EC = echarts.init(this.$refs.ele as HTMLDivElement)
    this.renderEcharts()
  }
  private renderEcharts() {
    this.op = {
      color: '#2593F2',
      tooltip: {
        show: true
        // formatter: (data: any) => {
        // }
      },
      grid: {
        left: 10,
        right: '4%',
        bottom: 0,
        top: 30,
        containLabel: true
      },
      xAxis: {
        axisLabel: {
          color: '#666'
        },
        axisLine: {
          lineStyle: {
            color: '#2648BE',
            opacity: 0.5
          }
        },
        splitLine: {
          show: false
        },
        axisTick: {
          length: 2
        }
      },
      yAxis: {
        name: 'MBP/S',
        axisLabel: {
          color: '#666'
        },
        axisLine: {
          lineStyle: {
            color: '#2648BE',
            opacity: 0.5
          }
        },
        splitLine: {
          lineStyle: {
            color: '#2648BE',
            opacity: 0.1
          }
        },
        axisTick: {
          length: 2
        }
      },
      series: [
        {
          type: 'scatter',
          symbolSize: 10,
          data: this.renderData
          // data: [
          //   [10.0, 8.04],
          //   [8.0, 6.95],
          //   [13.0, 7.58]
          // ]
        }
      ]
    }
    const option = _.defaultsDeep(this.option, this.op)
    this.EC.setOption(option, true)
    this.resize()
  }
  private resize() {
    if (window.addEventListener) {
      window.addEventListener('resize', this.resizeEventHandler, false)
    } else if (window.attachEvent) {
      const _this = this
      window.attachEvent('resize', _this.resizeEventHandler)
    }
  }
  private destoryed() {
    if (window.removeEventListener) {
      window.removeEventListener('resize', this.resizeEventHandler, false)
    } else if (window.detachEvent) {
      const that = this
      window.detachEvent('resize', that.resizeEventHandler)
    }
    this.EC.dispose()
    delete this.EC
  }
}
</script>

<style module></style>
