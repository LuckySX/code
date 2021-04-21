<!--
 * @Author: niumiaomiao
 * @Date: 2020-03-21 21:52:31
 * @LastEditors: fuping
 * @LastEditTime: 2020-07-28 15:30:23
 * @Description: 
 -->
<template>
  <div :class="pageBoxOne.main" class="sz">
    <el-table :data="tableDataOne" stripe style="width: 100%" height="90%" highlight-current-row ref="table" @cell-click="cellClick">
      <el-table-column prop="orderNum" label="序号" width="50px"></el-table-column>
      <el-table-column prop="ip_address" label="IP" width="100px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="injured" label="被攻击流量" width="90px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="begintime" label="开始时间" show-overflow-tooltip></el-table-column>
      <el-table-column prop="attacktypes" label="攻击类型" width="70px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="operator" label="运营商" width="60px" show-overflow-tooltip></el-table-column>
    </el-table>
    <div :class="pageBoxOne.pageBox">
      <span @click="prevPage" :style="curPage === 1 || curPage === 0 ? 'opacity: 0.4;cursor: not-allowed;' : 'opacity: 1;cursor: pointer;'">
        <i class="el-icon-caret-left"></i>
      </span>
      <span>{{ curPage }}</span>
      &nbsp;/&nbsp;
      <span>{{ totalPage }}</span>
      <span @click="nextPage" :style="curPage === totalPage ? 'opacity: 0.4;cursor: not-allowed;' : 'opacity: 1;cursor: pointer;'">
        <i class="el-icon-caret-right"></i>
      </span>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Watch, Vue } from 'vue-property-decorator'
@Component({})
export default class BorderCon extends Vue {
  @Prop() private tableData!: number[]
  private tableDataOne: any = []
  private timer1: any = null
  private pageSize = 6
  private curPage = 0
  private totalPage = 0
  // 监听数据变化
  @Watch('tableData', { deep: true })
  private dataChanged() {
    this.loadData()
  }
  // 监听页码
  @Watch('curPage', { deep: true })
  private pageChanged() {
    if (this.timer1) {
      clearInterval(this.timer1)
    }
    this.tableDataOne = this.tableData.slice((this.curPage - 1) * this.pageSize, this.curPage * this.pageSize)
    if (this.tableData.length > 0) {
      this.handleCurrentRow(this.tableData[0])
      this.startLoop(true)
    }
  }
  beforeDestroy() {
    this.timer1 && clearInterval(this.timer1)
  }
  mounted() {
    this.loadData()
  }
  // private destroyed() {
  //   this.startLoop(false)
  // }
  private loadData() {
    const tableData = this.tableData
    this.totalPage = Math.ceil(tableData.length / this.pageSize)
    this.curPage = tableData.length > 0 ? 1 : 0
    this.tableDataOne = this.tableData.slice(0, this.pageSize)
    this.startLoop(true)
  }
  // 前一页
  private prevPage() {
    if (this.curPage !== 1 && this.curPage !== 0) {
      this.curPage--
      this.tableDataOne = this.tableData.slice((this.curPage - 1) * this.pageSize, this.curPage * this.pageSize)
    }
  }
  // 后一页
  private nextPage() {
    if (this.curPage !== this.totalPage) {
      this.curPage++
      this.tableDataOne = this.tableData.slice((this.curPage - 1) * this.pageSize, this.curPage * this.pageSize)
    }
  }
  private startLoop(isTurnOn, start = 1) {
    if (isTurnOn) {
      if (this.timer1) {
        clearInterval(this.timer1)
      }
      const rowGroupData = this.tableDataOne
      if (this.curPage > 1) {
        if (rowGroupData.length > 1) {
          this.timer1 = setInterval(() => {
            this.handleCurrentRow(rowGroupData[start])
            start++
            // 如果这一页滚到底了，下一页开始
            if (start > rowGroupData.length) {
              this.nextPage()
              start = 1
            }
            // 如果到最后一页了，从头开始滚动
            if (this.curPage > this.totalPage) {
              this.curPage = 1
              start = 1
            }
          }, 3000)
        }
      } else {
        this.timer1 = setInterval(() => {
          if (start === rowGroupData.length) {
            start = 0
          }
          this.handleCurrentRow(rowGroupData[start])
          start++
        }, 3000)
      }
    } else {
      clearInterval(this.timer1)
    }
  }
  private cellClick(target) {
    if (this.timer1) {
      clearInterval(this.timer1)
    }
    // 判断该行在整页的index，从该点开始动画
    // let rowGroupData = this.$refs.table.data
    const rowGroupData = this.tableDataOne
    if (target) {
      let targetIndex = 0
      rowGroupData.forEach((item, index) => {
        if (item.id === target.id) {
          targetIndex = index
        }
      })
      this.startLoop(false, Number(targetIndex))
      this.handleCurrentRow(target)
    }
  }
  private handleCurrentRow(target) {
    const el: any = this.$refs.table
    el.setCurrentRow(target)
    // 传给地图点 数据
    this.$emit('currentRowData', target.id)
  }
}
</script>
<style module="pageBoxOne">
.main {
  font-size: 12px;
  height: 100%;
}
.pageBox {
  width: 100%;
  height: 20px;
  margin-top: 8px;
  display: flex;
  justify-content: center;
  font-size: 16px;
  color: #fff;
}
.pageBox span:first-of-type {
  margin-right: 10px;
}
.pageBox span:last-of-type {
  margin-left: 10px;
}
</style>
