/*
 * @Author       fuping
 * @Date         2020-03-22
 * @Description  ECHARTS柱图
 * @param        { Array } [{name: '', value: 0}]
 */
import { axisLabelColor, axisLineColor, defaultColor } from './baseUtils'
export const baseBarOption = {
  color: defaultColor[0],
  tooltip: {
    trigger: 'axis',
    backgroundColor: '#fff',
    borderWidth: 1,
    padding: [5, 10],
    textStyle: {
      color: '#333'
    },
    formatter: (data: any) => {
      return `ip: ${data[0].data.name}<br>
              攻击总流量: ${data[0].data.info || '无数据'}`
    }
  },
  grid: {
    left: 10,
    right: 100,
    bottom: 0,
    top: 30,
    containLabel: true
  },
  yAxis: [
    {
      type: 'category',
      position: 'left',
      offset: '0',
      // data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
      data: [],
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
        length: 2,
        alignWithLabel: true
      }
    },
    {
      type: 'category',
      position: 'right',
      offset: '0',
      data: [],
      axisLabel: {
        show: false,
        color: axisLabelColor
      },
      axisLine: {
        show: false
      },
      splitLine: {
        show: false
      },
      axisTick: {
        show: false
      }
    }
  ],
  xAxis: [
    {
      type: 'value',
      name: '攻击总流量/GB',
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
      },
      splitNumber: 3
    }
  ],
  series: [
    {
      name: 'IP',
      type: 'bar',
      yAxisIndex: 1,
      barWidth: 10,
      itemStyle: {
        color: defaultColor[0]
      },
      // data: [10, 52, 200, 334, 390, 330, 220],
      data: [],
      z: 1
    },
    {
      name: '背景',
      type: 'bar',
      barWidth: 10,
      barGap: '-100%',
      barCategoryGap: '50%',
      itemStyle: {
        color: '#F2F2F2'
      },
      tooltip: {
        show: false
      },
      // data: [10, 52, 200, 334, 390, 330, 220],
      data: [],
      z: 0
    }
  ]
}
