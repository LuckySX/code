/*
 * @Author       fuping
 * @Date         2020-03-22
 * @Description  ECHARTS散点图
 * @param        { Array } renderData [{ name: '80', value: 30 }]
 *
 */
import { axisLabelColor, axisLineColor } from './baseUtils'
export const baseScatterOption = {
  color: '#2593F2',
  tooltip: {
    show: true,
    trigger: 'axis',
    backgroundColor: '#fff',
    borderWidth: 1,
    padding: [5, 10],
    textStyle: {
      color: '#333'
    },
    formatter: (data: any) => {
      return `高峰攻击速率: ${data[0].data[1]}KB/S<br>
              连接数: ${data[0].data[0]}个`
    }
  },
  grid: {
    left: 20,
    right: 70,
    bottom: 10,
    top: 30,
    containLabel: true
  },
  xAxis: {
    name: '连接数/个',
    min: 'dataMin',
    max: 'dataMax',
    axisLabel: {
      color: axisLabelColor
    },
    axisLine: {
      lineStyle: {
        color: axisLineColor,
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
  yAxis: [
    {
      name: 'KB/S',
      type: 'value',
      position: 'left',
      offset: '0',
      min: 'dataMin',
      max: 'dataMax',
      axisLabel: {
        color: axisLabelColor
      },
      axisLine: {
        lineStyle: {
          color: axisLineColor,
          opacity: 0.5
        }
      },
      splitLine: {
        lineStyle: {
          color: axisLineColor,
          opacity: 0.1
        }
      },
      axisTick: {
        length: 2
      }
    }
  ],
  series: [
    {
      type: 'scatter',
      name: 'left',
      symbolSize: 8,
      data: ['无数据']
      // data: [
      //   { name: '80', value: 30 },
      //   { name: '90', value: 80 }
      // ]
    }
  ]
}
