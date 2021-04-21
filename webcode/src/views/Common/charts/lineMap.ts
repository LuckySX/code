/*
 * @Description:
 * @Autor: niumiaomiao
 * @Date: 2020-06-04 09:23:28
 * @LastEditors: fuping
 * @LastEditTime: 2020-07-14 17:22:25
 */

export const lineMapOption = {
  tooltip: {
    trigger: 'item',
    backgroundColor: '#fff',
    borderWidth: 1,
    padding: 10,
    textStyle: {
      color: '#000'
    },
    enterable: true,
    confine: true
  },
  geo: {
    map: 'china',
    layoutCenter: ['50%', '55%'],
    layoutSize: '150%',
    aspectScale: 1,
    // zlevel: 2,
    zoom: 1.5,
    label: {
      emphasis: {
        show: false
      }
    },
    roam: true,
    itemStyle: {
      normal: {
        borderColor: '#0290ff',
        borderWidth: 1,
        areaColor: {
          type: 'linear',
          x: 0,
          y: 0,
          x2: 0,
          y2: 1,
          colorStops: [
            {
              offset: 0,
              color: '#1864B6' // 0% 处的颜色
            },
            {
              offset: 1,
              color: '#6AC1F9' // 100% 处的颜色
            }
          ],
          global: false // 缺省为 false
        }
      },
      emphasis: {
        areaColor: '#389BB7',
        borderWidth: 1,
        borderColor: '#1497fc'
      }
    },
    data: [],
    silent: true
  },
  series: [
    {
      type: 'effectScatter',
      coordinateSystem: 'geo',
      tooltip: {
        show: false
      },
      z: 3,
      rippleEffect: {
        brushType: 'fill',
        period: 6,
        scale: 4
      },
      symbolSize: 1,
      itemStyle: {
        color: '#FFE100'
      },
      label: {
        normal: {
          show: true,
          position: 'bottom',
          formatter: (params: any) => {
            return params.name
          }
        }
      },
      data: []
    },
    {
      type: 'effectScatter',
      coordinateSystem: 'geo',
      tooltip: {
        show: false
      },
      z: 3,
      rippleEffect: {
        brushType: 'fill',
        period: 6,
        scale: 4
      },
      symbolSize: 1,
      itemStyle: {
        color: '#FFE100'
      },
      label: {
        normal: {
          show: true,
          position: 'bottom',
          formatter: (params: any) => {
            return params.name
          }
        }
      },
      data: []
    },
    {
      name: '深圳',
      type: 'lines',
      z: 2,
      symbolSize: 10,
      effect: {
        show: true,
        period: 6,
        symbol: 'arrow',
        trailLength: 0.1,
        symbolSize: 3,
        constantSpeed: 30,
        color: '#fff'
      },
      lineStyle: {
        normal: {
          color: '#d3f584',
          width: 1,
          opacity: 0.8,
          curveness: -0.3
        }
      },
      tooltip: {
        formatter: (params: any) => {
          const data = params.data
          return `源地域：${data.source}<br>
                    目的地域：${data.target}<br>
                    流量：${data.volume}GB<br>
                    连接数：${data.links}万个`
        }
      },
      data: []
    }
  ]
}
