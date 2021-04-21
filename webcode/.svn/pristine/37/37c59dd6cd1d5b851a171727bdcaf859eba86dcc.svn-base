<!--
 * @Author: zhangjipei
 * @Date: 2020-03-30 14:38:34
 * @LastEditors: fuping
 * @LastEditTime: 2020-08-21 11:42:32
 * @Description: 
 -->
<template>
  <div :class="keyAsset.container">
    <div :class="keyAsset.topBox">
      <!-- <span :class="keyAsset.timeBox">{{ currentTime }}</span> -->
      <BorderBox title="昨日深圳市流量监测情况" type="info2">
        <VTable :option="tableOpt" :height="tableHeight"></VTable>
      </BorderBox>
    </div>
    <div :class="keyAsset.bottomBox">
      <div :class="keyAsset.leftBox">
        <BorderBox title="单位目录" type="table">
          <div :class="keyAsset.tableForm">
            <VForm :option="formObj1" ref="form1" style="height: 100%;" class="keyAssetForm"></VForm>
          </div>
          <div :class="keyAsset.tableContent" ref="table2">
            <VTable :option="tableOpt2" :height="tableHeight2" @handleCurrentPageChange="handleCurrentChange" class="keyAssetTable"></VTable>
          </div>
        </BorderBox>
      </div>
      <div :class="keyAsset.rightBox">
        <BorderBox title="基本信息" type="table">
          <div :class="keyAsset.infoTitle">
            <el-tooltip effect="dark" :content="companyInfo.companyName" placement="top">
              <div :class="keyAsset.titleLeft">
                {{ companyInfo.companyName }}
              </div>
            </el-tooltip>
            <div :class="keyAsset.titleRight">
              <span>所属区域：{{ companyInfo.county }}</span>
              <span>所属行业：{{ companyInfo.categories }}</span>
              <span>所属IP情况：共有IP {{ companyInfo.ips }}个，共有网站{{ companyInfo.domains }}个</span>
            </div>
          </div>
          <div :class="keyAsset.infoForm">
            <VForm :option="formObj2" ref="form2" style="width: 100%;"></VForm>
          </div>
          <div :class="keyAsset.infoContent">
            <VTable
              :option="tableOpt3"
              :height="tableHeight2"
              @handleCurrentPageChange="handleCurrentChange2"
              @handleSizePageChange="handleSizeChange"
            ></VTable>
          </div>
        </BorderBox>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Ref } from 'vue-property-decorator'
import { http } from '@/common/request'
import { getCookies } from '@/utils/cookiesUtil'
import { getParmas } from '@/utils/assist'
import replace from 'lodash/replace'
import BorderBox from '@/components/Common/BorderBox.vue'
import dayjs from 'dayjs'
import trim from 'lodash/trim'
interface IAssetParams<T = any> {
  code: number
  message: string
  data: number[]
}
@Component({
  components: {
    BorderBox
  }
})
export default class KeyAsset extends Vue {
  @Ref() readonly form1!: VP.VForm
  @Ref() readonly form2!: VP.VForm
  @Ref('table2') readonly table2!: HTMLElement
  private currentTime = dayjs().format('YYYY年MM月DD日')
  private tableHeight = 68
  private tableOpt: any = {
    stripe: true,
    column: [
      { name: '单位IP数', value: 'assetips', align: 'left' },
      { name: '启用数量', value: 'usedips', align: 'left' },
      { name: '总流量', value: 'sumBytes', align: 'left' },
      { name: '网站数', value: 'domains', align: 'left' },
      { name: '网站请求流量', value: 'webDownbytes', align: 'left' },
      { name: '网站响应流量', value: 'webUpbytes', align: 'left' },
      { name: '访问用户IP数', value: 'sips', align: 'left' }
    ],
    data: [],
    defaultSort: [{ prop: 'assetips', order: 'descending' }],
    pagination: false,
    pageOpt: {
      currentPage: 1,
      total: 0,
      pageSizes: [10, 20, 30, 40, 50],
      pageSize: 10
    }
  }
  private tableformItems: any = {
    county: null,
    categories: null,
    companyName: '',
    ip: '',
    domain: '',
    sort: 'companyName',
    order: 'desc',
    page: 1,
    rows: 10
  }
  private areaOpt: any = []
  private industryOpt: any = []
  private formObj1: any = {
    inline: true,
    btnPos: 'right',
    items: [
      {
        label: '',
        type: 'select',
        comOpt: {
          id: 'county',
          value: null,
          width: 140,
          disabled: false,
          placeholder: '请选择区',
          data: this.areaOpt
        }
      },
      {
        label: '',
        type: 'select',
        comOpt: {
          id: 'categories',
          value: null,
          width: 140,
          disabled: false,
          placeholder: '请选择行业',
          data: this.industryOpt
        }
      },
      {
        label: '',
        type: 'select',
        comOpt: {
          id: 'companySelect',
          value: 0,
          width: 105,
          disabled: false,
          data: [
            { name: '单位名称', value: 0 },
            { name: 'IP', value: 1 },
            { name: '域名', value: 2 }
          ]
        }
      },
      {
        label: '',
        type: 'text',
        comOpt: {
          id: 'companyValue',
          width: 185,
          disabled: false,
          show: true,
          placeholder: '请输入',
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
          click: this.handleTableQueryBtn
        }
      }
    ]
  }
  private tableHeight2 = 'calc(100% - 42px)'
  private tableOpt2: any = {
    stripe: true,
    isHeader: false,
    column: [{ name: '单位名称', value: 'companyName', align: 'left' }],
    data: [],
    defaultSort: [{ prop: 'companyName', order: 'descending' }],
    pagination: true,
    pageOpt: {
      currentPage: 1,
      total: 0,
      pageSizes: [10, 20, 30, 40, 50],
      pageSize: 10,
      layout: 'prev, pager, next, total',
      pageCount: 5
    },
    rowClick: this.handleRowClick
  }
  private companyInfo: any = {
    companyName: '',
    county: '',
    categories: '',
    ips: 0,
    domains: 0
  }
  private infoformItems: any = {
    startTime: dayjs()
      .subtract(1, 'day')
      .format('YYYY-MM-DD'),
    endTime: dayjs()
      .subtract(1, 'day')
      .format('YYYY-MM-DD'),
    companyId: '',
    companyName: '',
    domain: '',
    ip: '',
    page: 1,
    rows: 10,
    sort: 'sipCnt',
    order: 'desc'
  }
  private radioTimeList = [0, 7, 30, 90]
  private formObj2: any = {
    inline: true,
    btnPos: 'right',
    items: [
      {
        label: '查询时间：',
        type: 'date',
        comOpt: {
          id: 'startTime',
          clearable: false,
          value: this.infoformItems.startTime,
          type: 'date',
          disabled: false,
          pickOptions: {
            disabledDate: this.startDisable
          }
        }
      },
      {
        label: '',
        type: 'date',
        comOpt: {
          id: 'endTime',
          clearable: false,
          value: this.infoformItems.endTime,
          type: 'date',
          disabled: false,
          pickOptions: {
            disabledDate: this.endDisable
          }
        }
      }
      // {
      //   label: '',
      //   type: 'radio',
      //   comOpt: {
      //     id: 'timeSelect',
      //     data: [
      //       { name: '当天', value: 0 },
      //       { name: '7天', value: 1 },
      //       { name: '30天', value: 2 },
      //       { name: '90天', value: 3 }
      //     ],
      //     value: 0,
      //     change: this.timeSelectChange
      //   }
      // }
    ],
    btns: [
      {
        comOpt: {
          id: 'query2',
          value: '查询',
          width: 90,
          disabled: false,
          click: this.handleInfoTableQueryBtn
        }
      },
      {
        comOpt: {
          id: 'export',
          value: '导出',
          width: 90,
          disabled: false,
          click: this.handleInfoTableExportBtn
        }
      }
    ]
  }
  private tableOpt3: any = {
    stripe: true,
    column: [
      { name: '序号', value: 'orderNum', width: 60, align: 'center' },
      { name: '单位IP', value: 'ip', width: 130, align: 'left' },
      // { name: '网段', value: 'segment', width: 130, align: 'left' },
      { name: '网站域名', value: 'domain', minWidth: 130, align: 'left', tooltip: true },
      // { name: '对公IP', value: 'globalIp', width: 130, align: 'left' },
      // { name: '域名操作系统', value: 'domainOs', minWidth: 105, align: 'left' },
      { name: '设备类型', value: 'deviceType', minWidth: 105, align: 'left' },
      { name: '操作系统', value: 'deviceOs', minWidth: 105, align: 'left' },
      { name: '所属运营商', value: 'operator', minWidth: 90, align: 'left' },
      { name: '活跃度', value: 'webLinks', align: 'left', minWidth: 90, sortable: 'custom' },
      { name: '网站请求量', value: 'webDownbytes', minWidth: 120, align: 'left', sortable: 'custom' },
      { name: '网站响应速率', value: 'webRate', minWidth: 135, align: 'left', sortable: 'custom' },
      { name: '访问用户IP数', value: 'sipCnt', minWidth: 135, align: 'left', sortable: 'custom' }
    ],
    data: [],
    sortMode: 'single',
    defaultSort: [{ prop: 'sipCnt', order: 'descending' }],
    pagination: true,
    pageOpt: {
      currentPage: 1,
      total: 0,
      pageSizes: [10, 20, 30, 40, 50],
      pageSize: 10
    },
    sortChange: this.handleSortChange
  }
  // 根据结束时间联动，不能大于结束时间
  startDisable(time: Date) {
    const endDateVal = this.form2.getValue().endTime
    if (endDateVal) {
      return time.getTime() > new Date(endDateVal).getTime()
    }
  }
  // 根据开始时间联动，只能选择开始时间之前
  endDisable(time: Date) {
    const beginDateVal = this.form2.getValue().startTime
    if (beginDateVal) {
      return time.getTime() < new Date(beginDateVal).getTime() - 1 * 24 * 60 * 60 * 1000
    }
  }
  mounted() {
    this.getCityData()
    this.getDropList()
    // this.form2.setValue([{ id: 'timeSelect', value: 0 }])
    this.getCompanyList(true)
  }
  // 表格排序按钮事件
  handleSortChange(column: object) {
    const data = column
    let sortName = ''
    let sortType = ''
    for (const key in column) {
      if (Object.prototype.hasOwnProperty.call(column, key)) {
        sortName = key
        sortType = column[key]
      }
    }
    this.infoformItems.sort = sortName
    this.infoformItems.order = replace(sortType, 'ending', '')
    this.getBasicTable()
  }
  // 获取当前深圳市流量检测情况数据
  getCityData() {
    interface ICityData {
      assetips: number
      usedips: number
      sumBytes: string
      domains: string
      webDownbytes: string
      webUpbytes: string
      sips: number
    }
    http.post<ICityData>('/KeyAsset/keyAssetsStatistic').then(resp => {
      this.tableOpt.data = [resp.data]
    })
  }
  // 获取单位目录的区域和行业的下拉列表数据
  getDropList() {
    interface IDropList {
      categories: any[]
      countis: any[]
    }
    http.post<IDropList>('/KeyAsset/dropList').then(resp => {
      const {
        data: { categories, countis }
      } = resp
      this.industryOpt.push({ name: '全部', value: '' })
      categories.forEach(item => {
        this.industryOpt.push({
          name: item.dropName,
          value: item.dropName
        })
      })
      this.areaOpt.push({ name: '全部', value: '' })
      countis.forEach(item => {
        this.areaOpt.push({
          name: item.dropName,
          value: item.dropName
        })
      })
    })
  }
  // 获取单位目录数据
  getCompanyList(firstFlag: boolean) {
    const query = this.$route.query
    if (firstFlag && query.type === 'area') {
      this.form1.setValue([{ id: 'county', value: query.value }])
      this.tableformItems.county = query.value
    }
    http.post<IAssetParams>('/KeyAsset/companyInfoList', this.tableformItems).then((resp: any) => {
      this.tableOpt2.data = resp.data.rows
      this.tableOpt2.pageOpt.total = resp.data.total
      if (firstFlag) {
        this.getSearchCompany()
      }
    })
  }
  // 根据路由参数获取基本信息查询条件，若无参数，默认取单位目录表格第一行查询
  getSearchCompany() {
    // const query = this.$route.query
    this.infoformItems.companyId = ''
    this.infoformItems.companyName = ''
    this.infoformItems.domain = ''
    this.infoformItems.ip = ''
    // const enum QUERY_TYPE {
    //   IP = 'dip',
    //   DOMAIN = 'domain',
    //   COMPANY_NAME = 'dip_company_name',
    //   COMPANY_ID = 'companyId'
    // }
    // switch (query.type) {
    //   case QUERY_TYPE.IP:
    //     this.infoformItems.ip = query.value
    //     break
    //   case QUERY_TYPE.DOMAIN:
    //     this.infoformItems.domain = query.value
    //     break
    //   case QUERY_TYPE.COMPANY_NAME:
    //     this.infoformItems.companyName = query.value
    //     break
    //   case QUERY_TYPE.COMPANY_ID:
    //     this.infoformItems.companyId = query.value
    //     break
    //   default:
    //     break
    // }
    if (this.tableOpt2.data.length > 0) {
      this.infoformItems.companyId = this.tableOpt2.data[0].companyId
    }
    // }
    this.getBasicInfo(true)
  }
  // 获取基本信息数据
  getBasicInfo(firstFlag: boolean) {
    const query = this.$route.query
    if (firstFlag && query.time) {
      this.infoformItems.startTime = query.time
      this.infoformItems.endTime = query.time
      this.form2.setValue([
        { id: 'startTime', value: this.infoformItems.startTime },
        { id: 'endTime', value: this.infoformItems.endTime }
      ])
    }
    http.post<IAssetParams>('/KeyAsset/keyAssetTesting', this.infoformItems).then((resp: any) => {
      this.companyInfo = resp.data
      this.getBasicTable()
    })
  }
  // 获取基本信息表格数据
  getBasicTable() {
    http.post<IAssetParams>('/KeyAsset/KeyAssetTestingPage', this.infoformItems).then((resp: any) => {
      this.tableOpt3.data = resp.data.rows
      this.tableOpt3.pageOpt.total = resp.data.total
    })
  }
  // 单位目录查询按钮
  handleTableQueryBtn() {
    const form1Value = this.form1.getValue()
    this.tableformItems.county = form1Value.county
    this.tableformItems.categories = form1Value.categories
    // this.tableformItems.companyName = trim(form1Value.companyName)
    const selectList = ['companyName', 'ip', 'domain']
    selectList.forEach(item => {
      if (item !== selectList[form1Value.companySelect]) {
        this.tableformItems[item] = ''
      } else {
        this.tableformItems[item] = trim(form1Value.companyValue)
      }
    })
    this.tableformItems.page = 1
    this.tableOpt2.pageOpt.currentPage = 1
    this.getCompanyList(false)
  }
  // 单位目录表格翻页
  handleCurrentChange(page: number) {
    this.tableformItems.page = page
    this.getCompanyList(false)
  }
  // 单位目录行点击事件
  handleRowClick(row: any) {
    this.infoformItems.companyId = row.companyId
    this.infoformItems.companyName = ''
    this.infoformItems.domain = ''
    this.infoformItems.ip = ''
    this.infoformItems.page = 1
    this.tableOpt3.pageOpt.currentPage = 1
    this.getBasicInfo(false)
  }
  // 基本信息日期选择处理
  timeSelectChange(label: number) {
    this.infoformItems.startTime = dayjs()
      .subtract(this.radioTimeList[label], 'day')
      .format('YYYY-MM-DD')
    this.infoformItems.endTime = dayjs().format('YYYY-MM-DD')
    this.form2.setValue([
      { id: 'startTime', value: this.infoformItems.startTime },
      { id: 'endTime', value: this.infoformItems.endTime }
    ])
  }
  // 基本信息查询按钮
  handleInfoTableQueryBtn() {
    this.infoformItems.startTime = this.form2.getValue().startTime
    this.infoformItems.endTime = this.form2.getValue().endTime
    this.infoformItems.page = 1
    this.tableOpt3.pageOpt.currentPage = 1
    this.getBasicTable()
  }
  // 基本信息导出按钮
  handleInfoTableExportBtn() {
    const params: any = {
      startTime: this.infoformItems.startTime,
      endTime: this.infoformItems.endTime,
      companyId: this.infoformItems.companyId,
      companyName: this.infoformItems.companyName,
      domain: this.infoformItems.domain,
      ip: this.infoformItems.ip
    }
    http.post<IAssetParams>('/KeyAsset/getExportCount', params).then((resp: any) => {
      if (resp.data <= 0) {
        this.$message('无数据')
      } else {
        window.location.href =
          process.env.VUE_APP_BASE_API +
          `/KeyAsset/excelExport?${getParmas(params)}&sort=` +
          this.infoformItems.sort +
          `&order=` +
          this.infoformItems.order +
          `&token=${getCookies('szCode')}`
      }
    })
  }
  // 基本信息表格翻页
  handleCurrentChange2(page: number) {
    this.infoformItems.page = page
    this.getBasicTable()
  }
  // 基本信息表格个数选择器
  handleSizeChange(val: number) {
    this.infoformItems.rows = val
    this.infoformItems.page = 1
    this.tableOpt3.pageOpt.currentPage = 1
    this.getBasicTable()
  }
}
</script>

<style module="keyAsset">
.container {
  width: 100%;
  height: 100%;
  text-align: left;
}
.topBox {
  width: 100%;
  height: 120px;
  margin-bottom: 10px;
  position: relative;
}
.timeBox {
  position: absolute;
  right: 18px;
  top: 10px;
  font-size: 16px;
  z-index: 2;
}
.bottomBox {
  width: 100%;
  height: calc(100% - 130px);
  display: flex;
}
.leftBox {
  width: 340px;
  height: 100%;
  margin-right: 10px;
}
.tableForm {
  position: relative;
  height: 120px;
}
.tableContent {
  width: 100%;
  height: calc(100% - 120px);
}
.tableBox {
  height: calc(100% - 37px);
}
.rightBox {
  width: calc(100% - 350px);
  height: 100%;
}
.infoTitle {
  width: 100%;
  height: 30px;
  display: flex;
  line-height: 30px;
  justify-content: space-between;
}
.titleLeft {
  font-size: 18px;
  color: #000000;
  max-width: 300px;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}
.titleRight {
  font-size: 16px;
  color: rgba(0, 0, 0, 0.5);
}
.titleRight span {
  margin-right: 15px;
}
.titleRight span:last-child {
  margin-right: 0;
}
.infoForm {
  width: 100%;
  height: 40px;
  display: flex;
  justify-content: space-between;
}
.buttonBox {
  line-height: 40px;
}
.infoContent {
  width: 100%;
  height: calc(100% - 70px);
}
</style>
