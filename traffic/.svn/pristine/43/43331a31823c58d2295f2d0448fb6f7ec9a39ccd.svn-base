<!--
 * @Description: 
 * @Autor: niumiaomiao
 * @Date: 2020-03-30 21:29:28
 -->
<template>
  <div :class="TrafficTrend.container">
    <VForm :option="formObj" ref="form" :class="TrafficTrend.formBox"></VForm>
    <div
      class="el-icon-sort"
      :class="[TrafficTrend.sortIconBox, showSortBox ? TrafficTrend.sortActive : '']"
      @click="showSortBox = !showSortBox"
    ></div>
    <div :class="TrafficTrend.formSortBox" v-show="showSortBox">
      <VForm :option="formSortObj" ref="sortForm"></VForm>
      <span :class="[TrafficTrend.designBtn, TrafficTrend.configBtn]" @click="handleSortData">确认</span>
      <span :class="[TrafficTrend.designBtn, TrafficTrend.cancelBtn]" @click="handleCancleData">取消</span>
    </div>
    <div :class="TrafficTrend.listContent">
      <div :class="TrafficTrend.listBox" v-for="(item, index) in webList" :key="index">
        <websiteCom :data="item"></websiteCom>
      </div>
    </div>
    <div :class="TrafficTrend.pageBox">
      <el-pagination
        small
        background
        layout="prev, pager, next"
        :total="pageTotal"
        :page-size="6"
        :current-page="currentPage"
        @current-change="handleCurrentChange"
      ></el-pagination>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Ref } from 'vue-property-decorator'
import websiteCom from './components/webList.vue'
import { http } from '@/common/request'
import trim from 'lodash/trim'
import dayjs from 'dayjs'
import BorderBox from '@/components/Common/BorderBox.vue'
const UrlItraffic = '/interFlow/trafficTrend'
interface ITraffic {
  addNum: string
  cnName: string
  dateArr: object[]
  enName: string
  flowArr: object[]
  personArr: object[]
  trendNum: number
  visiteNum: number
}
interface ITrafficPage {
  rows: object[]
  pageNo: number
  total: number
}
@Component({
  components: {
    websiteCom,
    BorderBox
  }
})
export default class TrafficTrend extends Vue {
  @Ref() readonly form!: VP.VForm
  @Ref() readonly sortForm!: VP.VForm
  private formObj: any = {
    inline: true,
    btnPos: 'left',
    items: [
      {
        label: '',
        type: 'text',
        comOpt: {
          id: 'name',
          width: 300,
          placeholder: '请输入网站名称或网站域名'
        }
      }
    ],
    btns: [
      {
        comOpt: {
          id: 'query',
          value: '查询',
          disabled: false,
          click: this.getTrafficData
        }
      },
      {
        comOpt: {
          id: 'query',
          value: '重置',
          disabled: false,
          click: this.resetTrafficData
        }
      }
    ]
  }
  private formSortObj: any = {
    inline: false,
    btnPos: 'left',
    items: [
      {
        label: '开始时间：',
        type: 'date',
        comOpt: {
          id: 'startTime',
          clearable: false,
          value: dayjs()
            .add(-1, 'day')
            .format('YYYY-MM-DD'),
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
          value: dayjs()
            .add(-1, 'day')
            .format('YYYY-MM-DD'),
          type: 'date',
          disabled: false,
          pickOptions: {
            disabledDate: this.endDisable
          }
        }
      },
      {
        label: '排序方式：',
        type: 'select',
        comOpt: {
          id: 'type',
          value: 1,
          width: 140,
          data: [
            { name: '访问人数', value: 1 },
            { name: '流量', value: 2 }
          ]
        }
      }
    ]
  }
  private webList: any = []
  private showSortBox = false
  private currentPage = 1
  private pageTotal = 1
  private formValue: any = {
    name: '',
    time: 7,
    search: 1,
    startTime: '',
    endTime: '',
    type: '',
    page: 1,
    rows: 6
  }
  // 根据结束时间联动，不能大于结束时间
  startDisable(time: Date) {
    const endDateVal = this.sortForm.getValue().endTime
    if (endDateVal) {
      return time.getTime() > new Date(endDateVal).getTime()
    }
  }
  // 根据开始时间联动，只能选择开始时间之前
  endDisable(time: Date) {
    const beginDateVal = this.sortForm.getValue().startTime
    if (beginDateVal) {
      return time.getTime() < new Date(beginDateVal).getTime() - 1 * 24 * 60 * 60 * 1000
    }
  }
  // 查询参数
  private getTrafficData() {
    this.formValue.name = trim(this.form.getValue().name)
    this.formValue.page = 1
    if (this.formValue.name !== '') {
      this.formValue.search = 1
    }
    this.getSearchData()
  }
  // 排序
  private handleSortData() {
    const { startTime, endTime, type } = this.sortForm.getValue()
    this.formValue.startTime = startTime
    this.formValue.endTime = endTime
    this.formValue.type = type
    this.getSearchData()
    this.showSortBox = false
  }
  // 清空排序
  private handleCancleData() {
    this.sortForm.setValue([
      { id: 'type', value: 1 },
      { id: 'startTime', value: dayjs().format('YYYY-MM-DD') },
      { id: 'endTime', value: dayjs().format('YYYY-MM-DD') }
    ])
    this.showSortBox = false
  }
  // 获取列表
  private getSearchData() {
    http.post<ITrafficPage>(UrlItraffic, this.formValue).then(resp => {
      const {
        data: { rows, total, pageNo }
      } = resp
      this.webList = rows
      this.pageTotal = pageNo
      this.pageTotal = total
    })
  }
  private resetTrafficData() {
    this.form.clearValue()
    this.form.setValue([
      { id: 'type', value: 1 },
      { id: 'name', value: '' }
    ])
  }
  // 表格翻页
  handleCurrentChange(val: number) {
    this.formValue.page = val
    this.getTrafficData()
  }
  // 表格页码
  handleSizeChange(val: number) {
    // this.tableParams.rows = val
    // this.tableParams.page = 1
    // this.tableOpt.pageOpt.currentPage = 1
    // this.getTrafficData()
  }
  mounted() {
    this.getTrafficData()
  }
}
</script>

<style module="TrafficTrend">
.container {
  width: 100%;
  height: 100%;
  position: relative;
}
.formBox {
  float: left;
}
.formSortBox {
  width: 280px;
  background: #fff;
  padding: 10px 15px;
  right: 10px;
  top: 30px;
  position: absolute;
  z-index: 1001;
  box-shadow: 0px 0px 5px 1px rgba(0, 0, 0, 0.1);
}
.designBtn {
  display: inline-block;
  padding: 0 10px;
  height: 24px;
  line-height: 24px;
  color: #fff;
  background: #3a8ee6;
  font-size: 12px;
  text-align: center;
  border-radius: 2px;
  margin-top: 15px;
}
.designBtn:hover {
  cursor: pointer;
}
.cancelBtn {
  margin-left: 10px;
}
.configBtn {
  margin-left: 70px;
}
.sortIconBox {
  float: right;
  margin-right: 20px;
  margin-top: 10px;
}
.sortActive {
  color: #3a8ee6;
}
.sortIconBox:hover {
  cursor: pointer;
}
.searchBox {
  width: 100%;
  height: 46px;
  line-height: 46px;
  background: #fff;
  padding-left: 20px;
}
.title {
  font-size: 18px;
  color: #2747be;
  padding-left: 30px;
  background: url('~@/assets/images/border/search.png') no-repeat;
}
.search {
  float: right;
  width: 630px;
}
.searchText {
  width: 300px;
  margin: 0 20px;
}
.listContent {
  width: 100%;
  height: calc(100% - 56px);
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  margin: 0 auto;
  overflow-y: scroll;
}
.listBox {
  margin-top: 10px;
  margin-right: 10px;
  width: calc(33.3% - 10px);
  height: calc(50% - 25px);
  background: #fff;
}
.pageBox {
  width: 100%;
  height: 30px;
  text-align: center;
  margin: 0 auto;
}
</style>
