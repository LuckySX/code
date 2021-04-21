/*
 * @Author: fuping
 * @Date: 2020-04-07 16:24:56
 * @LastEditors: fuping
 * @LastEditTime: 2020-07-14 18:38:45
 * @Description:
 */
/**
 * Created by zhao on 2017/7/6.
 */
import Cookies from 'js-cookie'

// cookies有限期、单位天
const time = 1
const _setCookies = (key: any, value: any) => {
  Cookies.set(key, value, { expires: time, path: '/' })
}

export const setCookies = (obj: any) => {
  if (typeof obj === 'object') Object.keys(obj).forEach(item => _setCookies(item, obj[item]))
}

export const getCookies = (key: any) => {
  return Cookies.get(key)
}

// export const removeCookies = (key: any) => {
//   // return Cookies.remove(key)
// }

export const removeCookies = (obj: any) => {
  if (Array.isArray(obj)) obj.forEach(item => Cookies.remove(item, { path: '/' }))
  if (typeof obj === 'string') return Cookies.remove(obj)
}

export const getSession = (key: any) => {
  return window.sessionStorage.getItem(key)
}

export const setSession = (obj: any) => {
  if (typeof obj === 'object') Object.keys(obj).forEach(item => window.sessionStorage.setItem(item, obj[item]))
}

export const removeSession = (key: any) => {
  return window.sessionStorage.removeItem(key)
}

export const getLocalStorage = (key: any) => {
  return window.localStorage.getItem(key)
}

export const setLocalStorage = (obj: any) => {
  if (typeof obj === 'object') Object.keys(obj).forEach(item => window.localStorage.setItem(item, obj[item]))
}

export const removeLocalStorage = (key: any) => {
  return window.localStorage.removeItem(key)
}
