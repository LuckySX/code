/**
 * @description:  双折线
 * @param {type}
 * @return:
 * @author: niumiaomiao
 */
import { defaultColor } from './baseUtils'
export const lineDoubleOption = {
  tooltip: {
    trigger: 'axis',
    backgroundColor: '#fff',
    borderWidth: 1,
    padding: [5, 10],
    textStyle: {
      color: '#333'
    },
    formatter: (data: any) => {
      return ` ${data[0].name}<br>
              访问流量: ${data[0].data}KB<br>
              访问人数: ${data[1].data}人`
    }
  },
  legend: {
    show: false,
    itemWidth: 15,
    itemHeight: 3,
    left: 'center'
  },
  grid: {
    left: 10,
    right: 10,
    bottom: 5,
    top: 30,
    containLabel: true
  },
  xAxis: [
    {
      boundaryGap: false,
      axisLine: {
        show: true,
        lineStyle: {
          color: 'rgba(0,0,0,0.1)'
        }
      },
      axisLabel: {
        color: '#666'
      },
      axisTick: {
        show: false
      },
      splitLine: {
        show: true,
        lineStyle: {
          color: 'rgba(0,0,0,0.1)'
        }
      },
      data: []
    }
  ],
  yAxis: [
    {
      type: 'value',
      name: '(KB)',
      nameTextStyle: {
        color: 'rgba(11, 86, 167, 0.3)',
        padding: [0, 40, 0, 0]
      },
      axisLine: {
        lineStyle: {
          color: defaultColor[0]
        }
      },
      splitLine: {
        show: false
      }
    },
    {
      type: 'value',
      name: '(人)',
      nameTextStyle: {
        color: 'rgba(255,165,20,0.3)',
        padding: [0, 0, 0, 40]
      },
      axisLabel: {
        formatter: '{value}',
        textStyle: {
          color: '#FFA514'
        }
      },
      axisLine: {
        lineStyle: {
          color: '#FFA514'
        }
      },
      splitLine: {
        show: false
      }
    }
  ],
  series: [
    {
      name: '攻击流量',
      type: 'line',
      yAxisIndex: 0,
      symbol: 'circle',
      symbolSize: 0,
      itemStyle: {
        normal: {
          color: defaultColor[0],
          lineStyle: {
            color: defaultColor[0],
            width: 1
          }
        }
      },
      data: []
    },
    {
      name: '攻击人数',
      type: 'line',
      yAxisIndex: 1,
      symbol: 'circle',
      symbolSize: 0,
      itemStyle: {
        normal: {
          color: '#FFA514',
          lineStyle: {
            color: '#FFA514',
            width: 1
          }
        }
      },
      data: []
    }
  ]
}
