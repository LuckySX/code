<!--
 * @Description: 
 * @Autor: niumiaomiao
 * @Date: 2020-06-19 09:29:47
 * @LastEditors: niumiaomiao
 * @LastEditTime: 2020-08-14 10:30:39
-->
<template>
  <div :class="DomainNotfiled.main">
    <div :class="DomainNotfiled.dateBox">
      <VForm :option="formDateOp" ref="formDate"></VForm>
    </div>
    <div :class="DomainNotfiled.content">
      <VTable
        :option="tableOpt"
        :height="tableHeight"
        ref="table"
        @handleCurrentPageChange="handleCurrentChange"
        @handleSizePageChange="handleSizeChange"
      ></VTable>
    </div>
    <el-dialog :title="tableTitle" :visible.sync="tableShow" width="70%">
      <VTable ref="table1" :option="dialogTableOpt" :height="dialogTableHeight"></VTable>
    </el-dialog>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Ref } from 'vue-property-decorator'
import BorderBox from '@/components/Common/BorderBox.vue'
import dayjs from 'dayjs'
import { trim, assign } from 'lodash'
import { http } from '@/common/request'
import { getCookies } from '@/utils/cookiesUtil'
const UrlTableList = '/unDomainDiscovery/getList'
const UrlTableDiaList = '/newDomainTamper/getRecordWebsiteDetail'
const UrlExport = process.env.VUE_APP_BASE_API + '/unDomainDiscovery/getListExcel'
interface IDefaultTableItems {
  startTime: string
  endTime: string
  domain: string
  sort: string
  order: string
  page: number
  rows: number
}
interface IDefaultFormItems {
  startTime: string
  endTime: string
}
interface ITable {
  rows: number[]
  total: number
}
// 导出，error提示没有数据不能导出，success可以导出
enum exportFlag {
  success = 1,
  error = 0
}
@Component({
  components: {
    BorderBox
  }
})
export default class DomainNotfiled extends Vue {
  @Ref() readonly formDate!: VP.VForm
  @Ref() readonly table!: VP.VTable
  // 表格接口参数
  private tableParams: IDefaultTableItems = {
    startTime: dayjs()
      .subtract(1, 'day')
      .format('YYYY-MM-DD'),
    endTime: dayjs()
      .subtract(1, 'day')
      .format('YYYY-MM-DD'),
    domain: '',
    sort: 'date',
    order: 'desc',
    page: 1,
    rows: 10
  }
  // 全局日期选择器
  private formItems: IDefaultFormItems = {
    startTime: dayjs()
      .subtract(1, 'day')
      .format('YYYY-MM-DD'),
    endTime: dayjs()
      .subtract(1, 'day')
      .format('YYYY-MM-DD')
  }
  private formDateOp: any = {
    inline: true,
    btnPos: 'right',
    items: [
      {
        label: '开始时间：',
        type: 'date',
        comOpt: {
          id: 'startTime',
          clearable: false,
          value: this.formItems.startTime,
          type: 'date',
          disabled: false,
          pickOptions: {
            disabledDate: this.startDisable
          }
        }
      },
      {
        label: '结束时间：',
        type: 'date',
        comOpt: {
          id: 'endTime',
          clearable: false,
          value: this.formItems.endTime,
          type: 'date',
          disabled: false,
          pickOptions: {
            disabledDate: this.endDisable
          }
        }
      },
      {
        label: '网站域名：',
        type: 'text',
        comOpt: {
          id: 'domain',
          width: 150,
          disabled: false,
          show: true,
          placeholder: '请输入域名',
          value: ''
        }
      }
    ],
    btns: [
      {
        comOpt: {
          id: 'query',
          value: '查询',
          width: 90,
          disabled: false,
          click: this.handleQueryBtn
        }
      },
      {
        comOpt: {
          id: 'search',
          value: '重置',
          width: 90,
          disabled: false,
          click: this.handleReset
        }
      },
      {
        comOpt: {
          id: 'export',
          value: '导出',
          width: 90,
          disabled: false,
          click: this.handleExport
        }
      }
    ]
  }
  private tableOpt: any = {
    stripe: true,
    defaultSort: [{ prop: 'date', order: 'descending' }],
    sortMode: 'single',
    sortChange: this.sortChange,
    column: [
      { name: '序号', value: 'orderNum', width: 55 },
      { name: '网站域名', value: 'domain', minWidth: 120, sortable: 'custom', tooltip: true },
      { name: '访问次数', value: 'rcount', minWidth: 120, sortable: 'custom', tooltip: true },
      { name: '访问主机数', value: 'scount', minWidth: 120, sortable: 'custom', tooltip: true },
      { name: '访问次数/访问主机数', value: 'ratio', minWidth: 120, sortable: 'custom', tooltip: true },
      { name: '发现时间', value: 'date', minWidth: 150, sortable: 'custom', tooltip: true }
    ],
    data: [],
    pagination: true,
    pageOpt: {
      currentPage: 1,
      total: 0,
      pageSizes: [10, 20, 30, 40, 50],
      pageSize: 10
    }
  }
  private tableTitle = ''
  private tableShow = false
  private dialogTableOpt: any = {
    stripe: true,
    sortMode: 'single',
    column: [
      { name: '域名', value: 'domain' },
      { name: '站点名称', value: 'name', tooltip: true },
      { name: '网址', value: 'netAddr', tooltip: true },
      { name: '主办单位', value: 'organization', tooltip: true },
      { name: '备案许可证号', value: 'regNo', tooltip: true }
    ],
    data: [],
    pagination: false
  }
  private dialogTableHeight = 'calc(100% - 80px)'
  private tableHeight = 'calc(100% - 42px)'
  private exportType: exportFlag = exportFlag.error
  // 根据结束时间联动，不能大于结束时间
  startDisable(time: Date) {
    const endTimeVal = this.formDate.getValue().endTime
    if (endTimeVal) {
      return time.getTime() > new Date(endTimeVal).getTime()
    }
  }
  // 根据开始时间联动，只能选择开始时间之前
  endDisable(time: Date) {
    const beginDateVal = this.formDate.getValue().startTime
    if (beginDateVal) {
      return time.getTime() < new Date(beginDateVal).getTime() - 1 * 24 * 60 * 60 * 1000
    }
  }
  // 表格查询
  private handleSearch() {
    http.post<ITable>(UrlTableList, this.tableParams).then((resp: any) => {
      const {
        data: { rows, total, pageNo }
      } = resp
      if (rows.length > 0) {
        this.exportType = exportFlag.success
      } else {
        this.exportType = exportFlag.error
      }
      this.tableOpt.data = rows
      this.tableOpt.pageOpt.total = total
      this.tableOpt.pageOpt.currentPage = pageNo
    })
  }
  handleTableDetail(val) {
    http
      .post<ITable>(UrlTableDiaList, {
        domain: val.domain
      })
      .then((resp: any) => {
        this.tableShow = true
        const { data } = resp
        if (data !== null) {
          const dataArr: any = []
          this.tableTitle = data.domain + '的备案信息'
          dataArr.push(data)
          this.dialogTableOpt.data = dataArr
        } else {
          this.tableTitle = '查看备案信息'
          this.dialogTableOpt.data = []
        }
      })
  }
  // 表格翻页
  handleCurrentChange(page: number) {
    this.tableParams.page = page
    this.handleSearch()
  }
  // 表格页码
  handleSizeChange(val: number) {
    this.tableParams.rows = val
    this.tableParams.page = 1
    this.tableOpt.pageOpt.currentPage = 1
    this.handleSearch()
  }
  private handleReset() {
    this.formDate.setValue([
      { id: 'domain', value: '' },
      { id: 'startTime', value: this.tableParams.startTime },
      { id: 'endTime', value: this.tableParams.endTime }
    ])
  }
  private handleExport() {
    if (this.exportType === 0) {
      this.$message({
        message: '无数据',
        type: 'warning'
      })
    } else {
      // 导出链接
      const { startTime, endTime, domain, sort } = this.tableParams
      window.location.href = `${UrlExport}?startTime=${startTime}&endTime=${endTime}&domain=${domain}&sort=${sort}&token=${getCookies('szCode')}`
    }
  }
  // 排序
  sortChange(column: object) {
    const name: string = Object.keys(column)[0]
    if (column[name] === 'descending') {
      this.tableParams.order = 'desc'
    } else {
      this.tableParams.order = 'asc'
    }
    this.tableParams.sort = name
    this.handleQueryBtn()
  }
  // 表格查询按钮
  handleQueryBtn() {
    assign(this.tableParams, this.formDate.getValue())
    this.tableParams.domain = trim(this.tableParams.domain)
    this.tableParams.page = 1
    this.handleSearch()
  }
  mounted() {
    this.handleSearch()
  }
}
</script>
<style module="DomainNotfiled">
.main {
  width: 100%;
  height: 100%;
}
.dateBox {
  height: 40px;
}
.content {
  width: 100%;
  height: calc(100% - 40px);
}
</style>
