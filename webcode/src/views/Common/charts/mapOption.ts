/**
 * @Author: niumiaomiao
 * @description: 地图
 * @param {type}
 * @return:
 */
export const mapOption = {
  tooltip: {
    trigger: 'item',
    backgroundColor: '#fff',
    borderWidth: 1,
    padding: [5, 10],
    textStyle: {
      color: '#333'
    }
  },
  geo: {
    map: 'world',
    layoutCenter: ['50%', '50%'],
    layoutSize: '190%',
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
      zlevel: 3,
      rippleEffect: {
        brushType: 'fill',
        period: 6,
        scale: 4
      },
      tooltip: {
        show: false
      },
      symbolSize: 10,
      itemStyle: {
        normal: {
          color: '#fff',
          borderColor: 'rgba(0,80,104,0.5)'
        }
      },
      data: []
    },
    {
      tooltip: {
        show: false
      },
      type: 'effectScatter',
      coordinateSystem: 'geo',
      zlevel: 3,
      rippleEffect: {
        brushType: 'fill',
        period: 6,
        scale: 4
      },
      symbolSize: 1,
      itemStyle: {
        normal: {
          color: '#fff',
          borderColor: 'rgba(0,80,104,0.5)'
        }
      },
      data: []
    },
    {
      name: 'lines',
      type: 'lines',
      zlevel: 2,
      symbolSize: 10,
      effect: {
        show: true,
        period: 3,
        trailLength: 0.5,
        constantSpeed: 140,
        color: '#FDFD02',
        symbol: 'rect', //ECharts 提供的标记类型包括 'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow'
        symbolSize: 4
      },
      lineStyle: {
        normal: {
          color: '#FDFD02',
          width: 0.8,
          type: 'solid',
          opacity: 0.2,
          //shadowBlur:10,
          curveness: 0.2
        }
      },
      tooltip: {
        formatter: (params: any) => {
          return `攻击来源：${params.data.fromName}</br>
                          攻击目标：${params.data.toName}</br>
                          攻击流量: ${params.data.attackflow}<br/>
                          攻击占比：${params.data.proportion}`
        }
      },
      data: []
    }
  ]
}
