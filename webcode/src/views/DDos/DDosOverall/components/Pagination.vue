<template>
  <el-pagination
    style="margin-top:5px;"
    height="30px"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
    :current-page="currentPage"
    :page-size="option.pageSizes || 5"
    :page-sizes="option.pageSize || [5, 10, 20, 30, 40, 50]"
    layout="total, sizes, prev, pager, next, jumper"
    :total="option.total"
  ></el-pagination>
</template>
<script lang="ts">
import { Component, Prop, Watch, Vue } from 'vue-property-decorator'
@Component({})
export default class Pagination extends Vue {
  @Prop() private option!: any
  private currentPage: any = 1
  @Watch('option.currentPage', { deep: true })
  private dataChanged(newVal: any) {
    this.currentPage = newVal
  }
  // methods
  handleCheck(index: any, row: any) {
    this.$emit('handlerClick', row)
  }
  handleSizeChange(val: number) {
    this.$emit('handleSizeChange', val)
  }
  handleCurrentChange(val: number) {
    this.option.currentPage = val
    this.$emit('handleCurrentChange', val)
  }
}
</script>
