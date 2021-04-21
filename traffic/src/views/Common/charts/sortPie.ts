/*
 * @Description:
 * @Autor: niumiaomiao
 * @Date: 2020-06-04 09:23:28
 * @LastEditors: niumiaomiao
 * @LastEditTime: 2020-06-23 17:32:24
 */
export const sortPieOption = {
  title: {
    show: false
  },
  color: ['#0B56A7', '#0A62BB', '#0975DA', '#0880ED', '#078BFF'],
  grid: {
    top: 5,
    bottom: 5,
    left: 5,
    containLabel: false
  },
  tooltip: {
    show: false
  },
  legend: {
    top: -5,
    x: 'right',
    left: '40%',
    itemWidth: 0,
    itemHeight: 0,
    data: [],
    itemGap: 2,
    textStyle: {
      color: '#000',
      align: 'right',
      x: 'right',
      textAlign: 'right'
    },
    selectedMode: true,
    orient: 'vertical'
  },
  series: []
}

export const getSortPieData = function (data: any): any {
  const seriesData: any[] = []
  const rotate: any[] = [20, 50, 75, 95, 140]
  if (data && data != undefined && data.length > 0) {
    const maxData = data[0].value + data[0].value / 10
    data.forEach((item: any, index: any) => {
      seriesData.push({
        name: item.name,
        type: 'pie',
        z: 2,
        hoverAnimation: false,
        radius: [100 - index * 20 + '%', 85 - index * 20 + '%'],
        center: ['50%', '50%'],
        label: {
          show: true,
          fontSize: 10,
          lineHeight: 12,
          color: '#fff',
          textAlign: 'right',
          position: 'inner',
          rotate: rotate[index],
          formatter: function (a) {
            return `${a.value}次`
          }
        },
        labelLine: {
          show: false
        },
        data: [
          {
            value: item.value,
            name: item.name
          },
          {
            value: maxData - item.value,
            name: '',
            itemStyle: {
              color: 'rgba(0,0,0,0)',
              borderWidth: 0
            },
            tooltip: {
              show: false
            },
            label: {
              show: false
            },
            hoverAnimation: false
          }
        ]
      })
    })
  }
  return { seriesData }
}
