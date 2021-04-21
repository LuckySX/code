/*
 * @Author: fuping
 * @Date: 2020-04-08 17:37:28
 * @LastEditors: fuping
 * @LastEditTime: 2020-05-11 13:41:23
 * @Description:
 */
export {}
declare global {
  interface Window {
    attachEvent(type: string, callback: any): void
    detachEvent(type: string, callback: any): void
    md5(value: string, key?: string, bol?: boolean): string
  }
  /**
   * 返回结果数据接口格式
   */
  interface IResponse<T = any> {
    code: number
    data: T
    message: string
    success: boolean
    errorMessage: string
    errcode: string
    msg: string
  }
}
