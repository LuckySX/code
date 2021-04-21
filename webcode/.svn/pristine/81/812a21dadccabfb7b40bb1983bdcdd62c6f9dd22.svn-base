<!--
 * @Description: 
 * @Autor: niumiaomiao
 * @Date: 2020-06-19 09:29:47
 * @LastEditors: fuping
 * @LastEditTime: 2020-07-20 22:57:51
-->
<template>
  <div :class="TamperSearch.main">
    <div :class="TamperSearch.dateBox">
      <BorderBox title="筛选查询" type="search">
        <VForm :option="formDateOp" ref="formDate"></VForm>
      </BorderBox>
    </div>
    <div :class="TamperSearch.content">
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
const UrlTableList = '/newDomainTamper/getPage'
const UrlTableDiaList = '/newDomainTamper/getRecordWebsiteDetail'
const UrlExport = process.env.VUE_APP_BASE_API + '/newDomainTamper/excelExport'
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
  startDate: string
  endDate: string
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
export default class TamperSearch extends Vue {
  @Ref() readonly formDate!: VP.VForm
  @Ref() readonly table!: VP.VTable
  // 表格接口参数
  private tableParams: IDefaultTableItems = {
    startTime: dayjs().format('YYYY-MM-DD'),
    endTime: dayjs().format('YYYY-MM-DD'),
    domain: '',
    sort: 'time',
    order: 'desc',
    page: 1,
    rows: 10
  }
  private multipleSelection: string
  // 全局日期选择器
  private formItems: IDefaultFormItems = {
    startDate: dayjs().format('YYYY-MM-DD'),
    endDate: dayjs().format('YYYY-MM-DD')
  }
  private formDateOp: any = {
    inline: true,
    btnPos: 'left',
    items: [
      {
        label: '开始时间：',
        type: 'date',
        comOpt: {
          id: 'startTime',
          clearable: false,
          value: dayjs().format('YYYY-MM-DD'),
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
          value: this.formItems.endDate,
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
    defaultSort: [{ prop: 'time', order: 'descending' }],
    sortMode: 'single',
    sortChange: this.sortChange,
    column: [
      { name: '序号', value: 'orderNum', width: 55 },
      { name: '站点名称', value: 'websiteName', minWidth: 120, sortable: 'custom', tooltip: true },
      { name: '域名', value: 'domain', minWidth: 120, sortable: 'custom', tooltip: true },
      { name: '备案IP', value: 'checkip', minWidth: 120, sortable: 'custom', tooltip: true },
      { name: '解析IP', value: 'value', minWidth: 120, sortable: 'custom', tooltip: true },
      { name: '发现时间', value: 'time', minWidth: 150, sortable: 'custom', tooltip: true },
      {
        name: '操作',
        value: '',
        width: 80,
        align: 'center',
        fixed: 'right',
        operations: [
          {
            label: '详情',
            disCallBack() {
              return false
            },
            handlerClick: this.handleTableDetail
          }
        ]
      }
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
    const endDateVal = this.formDate.getValue().endDate
    if (endDateVal) {
      return time.getTime() > new Date(endDateVal).getTime()
    }
  }
  // 根据开始时间联动，只能选择开始时间之前
  endDisable(time: Date) {
    const beginDateVal = this.formDate.getValue().startDate
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
      if (rows && rows.length > 0) {
        this.exportType = exportFlag.success
      } else {
        this.exportType = exportFlag.error
      }
      this.tableOpt.data = rows
      this.tableOpt.pageOpt.total = total
      this.tableOpt.pageOpt.currentPage = pageNo
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
      { id: 'startTime', value: dayjs().format('YYYY-MM-DD') },
      { id: 'endTime', value: dayjs().format('YYYY-MM-DD') }
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
      window.location.href = `${UrlExport}?${this.getParmas(this.tableParams)}&token=${getCookies('szCode')}`
    }
  }
  // 格式化参数
  getParmas(data: any) {
    let parmas: any = ''
    for (const key in data) {
      parmas += `${key}=${data[key]}&`
    }
    return parmas.slice(0, -1)
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
    // 点击查询按钮表格就恢复默认排序
    // this.table.initSort()
    assign(this.tableParams, this.formDate.getValue())
    this.tableParams.domain = trim(this.tableParams.domain)
    this.tableParams.page = 1
    this.handleSearch()
  }
  handleTableDetail(val) {
    http
      .post<ITable>(UrlTableDiaList, {
        domain: val.domain
      })
      .then((resp: any) => {
        this.tableShow = true
        const { data } = resp
        const dataArr: any = []
        this.tableTitle = data.domain + '的备案信息'
        dataArr.push(data)
        this.dialogTableOpt.data = dataArr
      })
  }
  mounted() {
    this.handleSearch()
  }
}
</script>
<style module="TamperSearch">
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
