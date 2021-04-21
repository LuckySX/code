/*
 * @Description:
 * @Autor: niumiaomiao
 * @Date: 2020-07-22 09:57:28
 * @LastEditors: niumiaomiao
 * @LastEditTime: 2020-07-22 09:58:20
 */
import { axisLabelColor, axisLineColor, defaultColor } from './baseUtils'
export const lineDoubleOpt = {
  tooltip: {
    trigger: 'axis',
    backgroundColor: '#fff',
    borderWidth: 1,
    padding: [5, 10],
    textStyle: {
      color: '#333'
    },
    formatter: (data: any) => {
      let tip = `时间: ${data[0].name}<br>`
      for (let i = 0; i < data.length; i++) {
        const unit = i === 0 ? '万次' : '万个'
        tip += `${data[i].seriesName}: ${data[i].value}${unit}<br>`
      }
      return tip
    }
  },
  legend: {
    show: true,
    itemWidth: 15,
    itemHeight: 3,
    left: 'center'
  },
  grid: {
    left: 40,
    right: 30,
    bottom: 5,
    top: 30,
    containLabel: true
  },
  xAxis: {
    boundaryGap: false,
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
    splitNumber: 3,
    data: []
  },
  yAxis: [
    {
      type: 'value',
      name: '访问次数/万次',
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
      type: 'value',
      name: 'IP量/万个',
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
    }
  ],
  series: [
    {
      name: '访问次数',
      type: 'line',
      yAxisIndex: 0,
      smooth: true, //平滑曲线显示
      showSymbol: false,
      itemStyle: {
        color: defaultColor[0]
      },
      data: []
    },
    {
      name: 'IP量',
      type: 'line',
      yAxisIndex: 1,
      smooth: true, //平滑曲线显示
      showSymbol: false,
      itemStyle: {
        color: defaultColor[1]
      },
      lineStyle: {
        color: defaultColor[1]
      },
      data: []
    }
  ]
}
