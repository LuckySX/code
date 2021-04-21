/*
 * @Description:
 * @Autor: niumiaomiao
 * @Date: 2020-07-22 09:33:53
 * @LastEditors: niumiaomiao
 * @LastEditTime: 2020-07-22 10:02:12
 */
import { colorGroup } from './baseUtils'
export const pieOption = {
  tooltip: {
    trigger: 'item',
    backgroundColor: '#fff',
    borderWidth: 1,
    padding: [5, 10],
    textStyle: {
      color: '#333'
    },
    formatter: '{b} : {d}%'
  },
  grid: {
    top: 20,
    containLabel: true
  },
  legend: {
    show: false,
    orient: 'vertical',
    itemWidth: 15,
    itemHeight: 3,
    itemGap: 10,
    right: 0,
    top: 10,
    bottom: 0,
    // formatter: (name: any) => {
    //   console.log(name)
    // },
    data: []
  },
  color: colorGroup,
  series: [
    {
      type: 'pie',
      radius: '55%',
      center: ['50%', '50%'],
      data: [
        {
          name: '无数据',
          value: 0
        }
      ],
      labelLine: {
        length: 2
      },
      emphasis: {
        itemStyle: {
          // shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
}
