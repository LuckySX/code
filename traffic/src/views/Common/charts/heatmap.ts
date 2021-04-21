/*
 * @Author: zhangjipei
 * @Date: 2020-06-22 16:35:46
 * @LastEditors: fuping
 * @LastEditTime: 2020-06-23 14:35:28
 * @Description:
 */
export const heatmapOption = {
  grid: {
    show: true,
    borderColor: '#fff',
    borderWidth: 10,
    top: 10,
    bottom: 30,
    left: 60,
    right: 10,
    containLabel: false
  },
  tooltip: {
    backgroundColor: '#fff',
    borderWidth: 1,
    padding: [5, 10],
    textStyle: {
      color: '#333'
    },
    formatter: params => {
      let s = ''
      if (params.data) {
        s += params.data[3]
      }
      return s
    }
  },
  xAxis: {
    show: true,
    type: 'category',
    axisLabel: {
      textStyle: {
        color: '#666',
        fontSize: 14
      }
    },
    axisLine: {
      show: true,
      lineStyle: {
        color: '#b6c1e4',
        width: 1
      }
    },
    axisTick: {
      show: true,
      length: 2,
      lineStyle: {
        color: '#4c69ff'
      }
    },
    data: [],
    splitArea: {
      show: false,
      areaStyle: {
        color: ['rgba(11,86,167,0.2)']
      }
    },
    splitLine: {
      show: true,
      lineStyle: {
        color: '#fff',
        width: 0.5,
        type: 'solid'
      }
    }
  },
  yAxis: {
    type: 'category',
    axisLabel: {
      textStyle: {
        color: '#666',
        fontSize: 14
      }
    },
    axisLine: {
      show: true,
      lineStyle: {
        color: '#b6c1e4',
        width: 1
      }
    },
    axisTick: {
      show: true,
      length: 2,
      lineStyle: {
        color: '#4c69ff'
      }
    },
    data: [],
    splitArea: {
      show: true,
      areaStyle: {
        color: ['rgba(11,86,167,0.2)']
      }
    },
    splitLine: {
      show: true,
      lineStyle: {
        color: '#fff',
        width: 0.5,
        type: 'solid'
      }
    }
  },
  visualMap: {
    show: false,
    type: 'continuous',
    inRange: {
      color: ['#0b56a7', '#ff00e2']
    },
    textStyle: {
      color: '#fff'
    },
    min: 0,
    max: 1,
    dimension: 2
  },
  series: [
    {
      name: 'HEATMAP',
      type: 'heatmap',
      label: {
        show: false
      },
      itemStyle: {
        borderColor: '#fff',
        borderWidth: 0.5,
        borderType: 'solid'
      },
      zlevel: 2,
      data: []
    },
    {
      name: 'SCATTER',
      type: 'scatter',
      zlevel: 3,
      hoverAnimation: false,
      symbolSize: 16,
      symbol:
        'image://data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAQAAADYBBcfAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QA/4ePzL8AAAAHdElNRQfkBhcUJSNSNMOLAAABbUlEQVQ4y52VsU4CQRRFD4slfIASCzWhl55ISyw0UT4B/SD5ECUxfgCFVNqKiY3LbiOdnYk5FutG2Z1V4E01897Z3Jm587YmpehwQpcWO0BKwoRrHktVLo+BM0Px5Nly5e/JvlNVY0f2bduwadu+I+eq3rsXAnsu1MShdYs66g5N1TePimDPD3VsswTlo+lY/cjRXORCvTKqxBAjr9S3THC2NFXHBaxv7Kv9AjpW73NwoCYlkbGqryXBiXqegTN1WBKWR3F9qD4JdtQ4cJJVYN25ehhxAtzwyarxyTVwGtEFblfG+K7uRrSAl7XAF6CF72ojcGtVe8SG+h4BUAt8dw5AHMjUACJSYDuQvmBOzGUgsw2kWyS0OeC5lL5jt2KPB0ASMQGO1zqcY2BSbYCQV38M0Km2XNirmeVmm5g8VQebPavpZg954f5mraO3brNK1EWO/dceG0vtcZqJLIN/NeSZg+XK2kq/gBseikVfCNzJI2PVxSoAAAAldEVYdGRhdGU6Y3JlYXRlADIwMjAtMDYtMjNUMTI6Mzc6MzUrMDg6MDDz2mA6AAAAJXRFWHRkYXRlOm1vZGlmeQAyMDIwLTA2LTIzVDEyOjM3OjM1KzA4OjAwgofYhgAAACB0RVh0c29mdHdhcmUAaHR0cHM6Ly9pbWFnZW1hZ2ljay5vcme8zx2dAAAAGHRFWHRUaHVtYjo6RG9jdW1lbnQ6OlBhZ2VzADGn/7svAAAAF3RFWHRUaHVtYjo6SW1hZ2U6OkhlaWdodAAyONE6IKsAAAAWdEVYdFRodW1iOjpJbWFnZTo6V2lkdGgAMjgpleAmAAAAGXRFWHRUaHVtYjo6TWltZXR5cGUAaW1hZ2UvcG5nP7JWTgAAABd0RVh0VGh1bWI6Ok1UaW1lADE1OTI4ODcwNTX/u7h7AAAAEXRFWHRUaHVtYjo6U2l6ZQA4MjRCQiPd1ZIAAABHdEVYdFRodW1iOjpVUkkAZmlsZTovLy9hcHAvdG1wL2ltYWdlbGMvaW1ndmlldzJfOV8xNTkyNTU4NTQwODA2NzUyOV8zMDRfWzBdRq3NpwAAAABJRU5ErkJggg==',
      data: []
    }
  ]
}
