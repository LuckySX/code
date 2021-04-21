/*
 * @Description:
 * @Autor: niumiaomiao
 * @Date: 2020-06-04 09:23:28
 * @LastEditors: niumiaomiao
 * @LastEditTime: 2020-06-28 09:42:40
 */
export const sortBarOption = {
  title: {
    show: false
  },
  tooltip: {
    show: false,
    trigger: 'item',
    backgroundColor: '#fff',
    borderWidth: 1,
    padding: [5, 10],
    textStyle: {
      color: '#333'
    }
  },
  legend: {
    show: false
  },
  grid: {
    left: 10,
    right: 10,
    bottom: 10,
    top: 10,
    containLabel: true
  },
  xAxis: {
    type: 'value',
    axisTick: {
      show: false
    },
    axisLine: {
      show: false
    },
    splitLine: {
      show: false
    },
    axisLabel: {
      show: false
    }
  },
  yAxis: [
    {
      type: 'category',
      show: false,
      data: [],
      inverse: true,
      axisLine: {
        show: false
      },
      splitLine: {
        show: false
      },
      axisTick: {
        show: false
      }
    },
    {
      type: 'category',
      show: true,
      inverse: true,
      data: [],
      axisLine: {
        show: false
      },
      splitLine: {
        show: false
      },
      axisTick: {
        show: false
      },
      axisLabel: {
        show: true,
        inside: false,
        verticalAlign: 'top',
        lineHeight: 22,
        textStyle: {
          fontSize: '12'
        },
        formatter: function (val) {
          return `${val}万次   `
        }
      }
    }
  ],
  series: []
}

export const getSortBarData = function (data: any, indexNum: any): any {
  const seriesData: any[] = []
  const totalData: any[] = []
  if (data && data != undefined && data.length > 0) {
    data.forEach((item: any, index: any) => {
      totalData.push(data[0])
    })
  }
  seriesData.push(
    {
      name: 'bar',
      type: 'bar',
      yAxisIndex: 0,
      zlevel: 2,
      showBackground: true,
      backgroundColor: {
        color: 'f1f1f1'
      },
      data: data.map((item, i) => {
        const name = item.dip ? item.dip : item.domain
        const itemStyle = {
          color: name === indexNum ? '#FFA514' : '#0B56A7'
        }
        return {
          name: name,
          value: item.count,
          itemStyle: itemStyle
        }
      }),
      barWidth: 10,
      label: {
        normal: {
          show: true,
          position: [0, '13px'],
          textStyle: {
            fontSize: 12
          },
          formatter: function (a) {
            const num = a.dataIndex + 1
            return `${num}  ${a.name}`
          }
        }
      }
    },
    {
      name: 'total',
      type: 'bar',
      yAxisIndex: 1,
      data: totalData,
      barWidth: 10,
      itemStyle: {
        normal: {
          color: '#f1f1f1'
        }
      }
    }
  )
  return { seriesData }
}
