/*
 * @Author: fuping
 * @Date: 2020-04-14 22:22:39
 * @LastEditors: fuping
 * @LastEditTime: 2020-08-14 10:09:43
 * @Description:
 */
import dayjs from 'dayjs'
/**
 * @introduction  把数组中key值相同的那一项提取出来，组成一个对象
 * @param {Array} 传入的数组 [{a:"1",b:"2"},{a:"2",b:"3"}]
 * @param {String} key属性名 a
 * @return {返回类型说明}
 * @exception [违例类型] [违例类型说明]
 */
export const dataFormmater = (array: any, key: string) => {
  const result = {}
  for (let i = 0; i < array.length; i++) {
    result[array[i][key]] = array[i]
  }
  return result
}

/**
 * [获取URL中的参数名及参数值的集合]
 * 示例URL:http://htmlJsTest/getrequest.html?uid=admin&rid=1&fid=2&name=小明
 * @param  {[string]} urlStr [当该参数不为空的时候，则解析该url中的参数集合]
 * @return {[string]}       [参数集合]
 */
export const getQueryVariable = (urlStr: any) => {
  let url = ''
  if (typeof urlStr == 'undefined') {
    url = decodeURI(location.search) //获取url中"?"符后的字符串
  } else {
    url = '?' + urlStr.split('?')[1]
  }
  const theRequest = new Object()
  if (url.indexOf('?') != -1) {
    const str = url.substr(1)
    const strs = str.split('&')
    for (let i = 0; i < strs.length; i++) {
      theRequest[strs[i].split('=')[0]] = decodeURI(strs[i].split('=')[1])
    }
  }
  return theRequest
}

// 格式化参数
export const getParmas = (data: any) => {
  let parmas: any = ''
  for (const key in data) {
    parmas += `${key}=${data[key]}&`
  }
  return parmas.slice(0, -1)
}
