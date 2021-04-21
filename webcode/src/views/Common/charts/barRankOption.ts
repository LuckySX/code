/**
 * @Author: fuping
 * @description: 柱图排名
 * @param {type}
 * @return:
 */
import { axisLabelColor, defaultColor } from './baseUtils'
export const barRankOption = {
  color: defaultColor[0],
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
      return `IP: ${data[0].data.ip}<br>
              域名: ${data[0].data.domain}<br>
              访问次数: ${data[0].data.value || '无数据'}`
    }
  },
  grid: {
    left: -100,
    right: 10,
    bottom: 0,
    top: 10,
    containLabel: true
  },
  xAxis: [
    {
      axisTick: {
        show: false
      },
      axisLine: {
        show: false
      },
      axisLabel: {
        show: false
      },
      splitLine: {
        show: false
      }
    }
  ],
  yAxis: [
    {
      type: 'category',
      position: 'left',
      offset: 0,
      data: [],
      axisLabel: {
        color: axisLabelColor,
        align: 'left',
        // lineHeight: 15,
        verticalAlign: 'top',
        margin: 160,
        interval: 0,
        formatter: (params: any) => {
          const param = params.split(',')
          const web = param[2].length > 15 ? param[2].substring(0, 15) + '...' : param[2]
          return '{a|' + param[0] + '}' + '   ' + '{d|' + param[1] + '}\n{b|' + param[0] + '}{c|' + web + '}'
        },
        rich: {
          a: {
            width: 10,
            fontSize: 13
          },
          d: {
            width: 150,
            fontSize: 16
          },
          b: {
            fontSize: 16,
            color: '#fff',
            padding: [0, 5]
          },
          c: {
            width: 150,
            fontSize: 11,
            align: 'left',
            padding: [0, 0]
          }
        }
      },
      axisTick: {
        show: false
      },
      axisLine: {
        show: false
      }
    },
    {
      type: 'category',
      position: 'right',
      offset: '0',
      data: [],
      axisLabel: {
        show: true,
        color: '#2747BE'
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
  series: [
    {
      name: '',
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
      name: '',
      type: 'bar',
      barWidth: 10,
      barGap: '-100%',
      barCategoryGap: '50%',
      silent: true,
      itemStyle: {
        color: '#F2F2F2'
      },
      tooltip: {
        show: false
      },
      data: [],
      z: 0
    }
  ]
}
