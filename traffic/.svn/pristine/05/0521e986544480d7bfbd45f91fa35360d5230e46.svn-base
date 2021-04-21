// import { getMapKeys } from '../const'
// import { oneOf } from '@/utils/assist'
import echarts from 'echarts'

/**
 * [loadJSON 动态加载指定资源文件]
 */
const loadMap = (map: any) => {
  return Promise.all([
    new Promise(resolve => {
      import(`@/components/Charts/map/coord/${map}.js`)
        .then(resp => {
          if (resp) {
            resolve(resp.default)
          }
        })
        .catch(error => {
          console.error(error)
        })
    }),
    new Promise(resolve => {
      import(`@/components/Charts/map/json/${map}.json`)
        .then(resp => {
          if (resp) {
            if (map === 'world') {
              resp.features.forEach((features: any) => {
                features.geometry.coordinates.forEach((e: any) => {
                  e[0].forEach((e1: number) => {
                    if (e1[0] < -30) {
                      e1[0] += 160
                    } else {
                      e1[0] -= 200
                    }
                  })
                })
              })
            }
            echarts.registerMap(map, resp)
            resolve('ok')
          }
        })
        .catch(error => {
          console.error(error)
        })
    })
  ]).then(result => {
    return new Promise(resolve => {
      resolve(result[0])
    })
  })
}

/**
 * [loadMapsResouces ]
 * @param  {Object}   map : 要加载的地图
 */
export function loadMapsResouces(map: any = 'china') {
  // if (!oneOf(map, getMapKeys())) {
  //   throw new Error(`not load '${map}' map, undefined map type `)
  // } else {
  //   return loadMap(map)
  // }
  return loadMap(map)
}
