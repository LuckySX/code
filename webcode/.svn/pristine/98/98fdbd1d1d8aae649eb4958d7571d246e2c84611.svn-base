<template>
  <div :class="UserManage.container">
    <BorderBox title="用户列表" type="table">
      <div :class="UserManage.formBox">
        <VForm :option="formOpt" ref="form"></VForm>
      </div>
      <div :class="UserManage.tableBox">
        <VTable
          :option="tableOpt"
          :height="tableHeight"
          ref="table"
          @handleCurrentPageChange="handleCurrentChange"
          @handleSizePageChange="handleSizeChange"
        ></VTable>
      </div>
    </BorderBox>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Ref } from 'vue-property-decorator'
import BorderBox from '@/components/Common/BorderBox.vue'
import { defaultsDeep, trim, assign } from 'lodash'
import { http } from '@/common/request'
interface IDefaultTableItems {
  username: string
  fullname: string
  sort: string
  order: string
  page: number
  rows: number
}
interface ITable {
  rows: number[]
  total: number
}
@Component({
  components: {
    BorderBox
  }
})
export default class UserManage extends Vue {
  @Ref() readonly form!: VP.VForm
  @Ref() readonly table!: VP.VTable
  // 表格接口参数
  private tableParams: IDefaultTableItems = {
    username: '',
    fullname: '',
    sort: 'username',
    order: 'desc',
    page: 1,
    rows: 10
  }
  private formOpt: any = {
    inline: true,
    btnPos: 'left',
    items: [
      {
        label: '登录名称：',
        type: 'text',
        comOpt: {
          id: 'username',
          width: 150,
          placeholder: '请输入'
        }
      },
      {
        label: '真实姓名：',
        type: 'text',
        comOpt: {
          id: 'fullname',
          width: 150,
          placeholder: '请输入'
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
          id: 'reset',
          value: '重置',
          disabled: false,
          click: this.handleResetBtn
        }
      }
    ]
  }
  private tableOpt: any = {
    stripe: true,
    defaultSort: [{ prop: 'username', order: 'descending' }],
    sortMode: 'single',
    sortChange: this.sortChange,
    column: [
      { name: '序号', value: 'orderNum', width: 55 },
      { name: '登录名称', value: 'username', minWidth: 120, sortable: 'custom', tooltip: true },
      { name: '真实姓名', value: 'fullname', minWidth: 120, sortable: 'custom', tooltip: true },
      { name: '用户角色', value: 'role', minWidth: 120, sortable: 'custom', tooltip: true }
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
  private tableHeight = 'calc(100% - 42px)'
  mounted() {
    this.handleQueryBtn()
  }
  // 表格查询按钮
  handleQueryBtn() {
    const { username, fullname } = this.form.getValue()
    this.tableParams.username = trim(username)
    this.tableParams.fullname = trim(fullname)
    this.tableParams.page = 1
    this.getTableData()
  }
  getTableData() {
    http.post<ITable>('/userManage/selectUser', this.tableParams).then((resp: any) => {
      const {
        data: { rows, total, pageNo }
      } = resp
      this.tableOpt.data = rows
      this.tableOpt.pageOpt.total = total
      this.tableOpt.pageOpt.currentPage = pageNo
    })
  }
  // 表格重置按钮
  handleResetBtn() {
    this.form.clearValue()
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
  // 表格翻页
  handleCurrentChange(page: number) {
    this.tableParams.page = page
    this.getTableData()
  }
  // 表格页码
  handleSizeChange(val: number) {
    this.tableParams.rows = val
    this.tableParams.page = 1
    this.tableOpt.pageOpt.currentPage = 1
    this.getTableData()
  }
}
</script>
<style module="UserManage">
.container {
  width: 100%;
  height: 100%;
}
.tableBox {
  width: 100%;
  height: calc(100% - 40px);
}
</style>
