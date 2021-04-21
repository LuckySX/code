<!--
 * @Author: fuping
 * @Date: 2020-03-30 15:26:09
 * @LastEditors: fuping
 * @LastEditTime: 2020-04-03 16:28:37
 * @Description: 
 -->
<template>
  <el-table :data="tableData" stripe style="width: 100%" height="100%" class="sz">
    <el-table-column prop="orderNum" label="排名" width="50px"></el-table-column>
    <el-table-column prop="dip" label="IP" width="130px" show-overflow-tooltip></el-table-column>
    <el-table-column prop="dipCounty" label="区/县" show-overflow-tooltip></el-table-column>
    <el-table-column prop="sumbytes" label="总流量(MB)" show-overflow-tooltip></el-table-column>
  </el-table>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
@Component({})
export default class Table extends Vue {
  @Prop() private tableData!: number[]
}
</script>
