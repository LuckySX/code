/*
 * @Author: fuping
 * @Description:ECHARTS双柱图
 */
import { axisLabelColor, axisLineColor, defaultColor } from './baseUtils'
export const doubleBarOption = {
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
      let tip = `时间: ${data[0].name}<br>`
      for (let i = 0; i < data.length; i++) {
        const unit = i === 0 ? '万次' : '万人'
        tip += `${data[i].seriesName}: ${data[i].value}${unit}<br>`
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
    right: 40,
    bottom: 0,
    top: 30,
    containLabel: true
  },
  xAxis: {
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
    // data: ['1月', '2月', '3月', '4月', '5月']
  },
  yAxis: [
    {
      name: '连接数/万次',
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
      name: '用户数/万人',
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
      name: '连接数',
      type: 'bar',
      yAxisIndex: 0,
      barWidth: 10,
      itemStyle: {
        color: defaultColor[0]
      },
      data: []
      // data: [2.0, 4.9, 7.0, 23.2, 25.6]
    },
    {
      name: '用户数',
      type: 'bar',
      yAxisIndex: 1,
      barWidth: 10,
      itemStyle: {
        color: defaultColor[1]
      },
      data: []
      // data: [2.6, 5.9, 9.0, 26.4, 28.7]
    }
  ]
}
