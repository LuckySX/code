<!--
 * @Author: fuping
 * @Date: 2020-03-30 15:29:47
 * @LastEditors: fuping
 * @LastEditTime: 2020-04-13 15:48:11
 * @Description: 
 -->
<template>
  <el-table :data="tableData" stripe style="width: 100%" height="100%" class="sz">
    <el-table-column prop="orderNum" label="排名" width="50px"></el-table-column>
    <el-table-column prop="protocol" label="应用名称" width="120px" show-overflow-tooltip></el-table-column>
    <el-table-column prop="protocolClass" label="应用类型" show-overflow-tooltip></el-table-column>
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
