/*
 * @Author: fuping
 * @Date: 2020-06-30 13:24:26
 * @LastEditors: fuping
 * @LastEditTime: 2020-07-14 17:22:34
 * @Description:点地图
 */

export const scatterMapOption = {
  tooltip: {
    trigger: 'item',
    triggerOn: 'click',
    alwaysShowContent: false,
    backgroundColor: '#fff',
    borderWidth: 1,
    padding: [5, 10],
    textStyle: {
      color: '#333'
    },
    enterable: true,
    confine: true,
    formatter: (params: any) => {
      const data = params.data
      const url = window.location.href
      const goToUrl = url.split('#')[0] + `#/KeyAsset?type=area&value=${data.name}&time=${data.time}`
      const tooltipDom = `<div id='tooltipBox' box-sizing: border-box;'>
        <div>所在区：${data.name}</div>
        <div>重点资产总流量：${data.value[2]}GB</div>
        <div style='text-align: left;'><a style='cursor: pointer;' href='${goToUrl}'>点击查看详情</a></div>
      </div>`
      return tooltipDom
    }
  },
  geo: {
    map: 'shenzhen',
    layoutCenter: ['50%', '55%'],
    layoutSize: '150%',
    aspectScale: 1,
    zoom: 1.5,
    // zlevel: 3,
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
      z: 3,
      rippleEffect: {
        brushType: 'fill',
        period: 6,
        scale: 4
      },
      symbolSize: (val: any) => {
        const max = 15
        const min = 3
        let value = val[2] / 8
        if (value > max) {
          value = max
        } else if (value < min) {
          value = min
        } else {
          value = value
        }
        return value
      },
      itemStyle: {
        color: '#FFE100'
      },
      data: [
        // { name: '罗湖区', value: 300 },
        // { name: '福田区', value: 100 }
      ]
    }
    // ,
    // {
    //   type: 'lines',
    //   zlevel: 2,
    //   tooltip: {
    //     show: false
    //   },
    //   data: []
    // }
  ]
}
