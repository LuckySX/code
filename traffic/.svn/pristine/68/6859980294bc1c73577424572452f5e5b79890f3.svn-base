/*
 * @Author       fuping
 * @Date         2020-03-31
 * @Description  双pie
 * @param        { Array } [{name: '', value: 0}]
 */
import { colorGroupDouble } from './baseUtils'
export const doublePieOption = {
  tooltip: {
    trigger: 'item',
    backgroundColor: '#fff',
    borderWidth: 1,
    padding: [5, 10],
    textStyle: {
      color: '#333'
    },
    formatter: (data: any) => {
      return `${data.seriesName}<br>
              ${data.name}: ${data.data.info}（${data.percent}%）`
    }
  },
  color: colorGroupDouble,
  legend: {
    orient: 'vertical',
    itemWidth: 15,
    itemHeight: 3,
    right: 10
  },
  series: [
    {
      type: 'pie',
      name: '运营商',
      center: ['35%', '50%'],
      radius: [0, '45%'],
      itemStyle: {
        normal: {
          borderColor: '#fff',
          borderWidth: 2
        }
      },
      label: {
        show: false
      },
      data: []
    },
    {
      type: 'pie',
      name: '协议TOP5',
      center: ['35%', '50%'],
      radius: ['55%', '70%'],
      label: {
        show: false
      },
      data: []
    }
  ]
}
