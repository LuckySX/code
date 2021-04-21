/*
 * @Author: fuping
 * @Date: 2020-04-13 15:17:08
 * @Description:ECHARTS折柱图
 * @param        { Array } [{name: '', value: 0}]
 */
import { lineColor, axisLabelColor, axisLineColor, defaultColor } from './baseUtils'
export const barLineOption = {
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
      let tip = `ip: ${data[0].name}<br>`
      for (let i = 0; i < data.length; i++) {
        tip += `${data[i].seriesName}: ${data[i].value}${data[i].seriesType === 'bar' ? 'GB' : '万次'}<br>`
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
    left: 35,
    right: 35,
    bottom: 0,
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
      data: ['无数据']
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
      name: '流量',
      type: 'bar',
      yAxisIndex: 0,
      barWidth: 10,
      itemStyle: {
        color: defaultColor[0]
      },
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
      data: []
    }
  ]
}
