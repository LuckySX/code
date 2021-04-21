/*
 * @Author: fuping
 * @Date: 2020-04-18 23:23:51
 * @Description:ECHARTS堆叠柱图折线图
 */
import { lineColor, axisLabelColor, axisLineColor, defaultColor } from './baseUtils'
export const stackBarLineOption = {
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
      let tip = `时间: ${data[0].name || '无数据'}<br>`
      for (let i = 0; i < data.length; i++) {
        tip += `${data[i].seriesName}: ${data[i].value || '无数据'}<br>`
      }
      return tip
    }
  },
  legend: {
    itemWidth: 15,
    itemHeight: 3,
    left: 'center'
  },
  grid: {
    left: 30,
    right: 30,
    bottom: 30,
    top: 30,
    containLabel: true
  },
  xAxis: [
    {
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
    }
  ],
  yAxis: [
    {
      name: '',
      type: 'value',
      position: 'left',
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
      name: '',
      type: 'value',
      position: 'right',
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
      name: '上行流量',
      type: 'bar',
      yAxisIndex: 0,
      barWidth: 10,
      itemStyle: {
        color: defaultColor[0]
      },
      // data: [10, 52, 200, 334, 390, 330, 220]
      data: []
    },
    {
      name: '下行流量',
      type: 'bar',
      yAxisIndex: 0,
      barWidth: 10,
      itemStyle: {
        color: '#11AC83'
      },
      // data: [10, 52, 200, 334, 390, 330, 220]
      data: []
    },
    {
      name: '连接数',
      type: 'line',
      yAxisIndex: 1,
      smooth: true, //平滑曲线显示
      showSymbol: false,
      itemStyle: {
        color: lineColor
      },
      lineStyle: {
        color: lineColor
      },
      // data: [10, 52, 200, 334, 390, 330, 220],
      data: []
    }
  ]
}
