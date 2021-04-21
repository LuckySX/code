<template>
  <div class="test">
    <h1>this is test page</h1>
    <button @click="change">change</button>
    <div class="box">
      <BaseCharts v-if="loaded" :option="option" class="ec" :theme="'vintage'"></BaseCharts>
      <VForm :option="formOption" ref="form"></VForm>
      <VTable :option="tableOption" :height="400"></VTable>
    </div>
  </div>
</template>

<script lang="ts">
// @ is an alias to /src
import { Component, Vue, Ref } from 'vue-property-decorator'
import { ECharts } from 'echarts'
import { http } from '@/common/request'
import '@/mockjs/index'
import axios from 'axios'
@Component({})
export default class Test extends Vue {
  private loaded = false
  @Ref() readonly form!: VP.VForm
  private formOption: any = {
    inline: true,
    labelPosition: 'right',
    labelWidth: '100',
    btnPos: 'right',
    items: [
      {
        label: '任务名称',
        type: 'text',
        comOpt: {
          id: 'taskName',
          width: 210,
          disabled: false,
          show: true,
          placeholder: '',
          value: ''
        }
      },
      {
        label: '任务内容',
        type: 'text',
        comOpt: {
          id: 'taskContent',
          width: 210,
          disabled: false,
          show: false,
          placeholder: '',
          value: ''
        }
      },
      {
        label: '目标',
        type: 'text',
        comOpt: {
          id: 'targetIpInfo',
          width: 210,
          disabled: false,
          show: true,
          placeholder: '',
          value: ''
        }
      },
      {
        label: '创建日期',
        type: 'date',
        comOpt: {
          id: 'queryCreateTime',
          clearable: false,
          value: '',
          type: 'datetimerange',
          disabled: false,
          width: '210',
          pickOptions: {}
        }
      },
      {
        label: '更新日期',
        type: 'date',
        comOpt: {
          id: 'queryUpdateTime',
          clearable: false,
          value: '',
          type: 'datetimerange',
          disabled: false,
          width: '210',
          pickOptions: {}
        }
      },
      {
        label: '任务状态',
        type: 'select',
        comOpt: {
          id: 'taskStatusId',
          value: '0',
          width: 210,
          disabled: false,
          data: [
            { name: '全部', value: '0' },
            { name: '未提交', value: '1' },
            { name: '已提交', value: '2' },
            { name: '查询中', value: '3' },
            { name: '已完成', value: '4' }
          ]
        }
      },
      {
        label: '文件状态',
        type: 'select',
        wrap: true,
        comOpt: {
          id: 'fileStatusId',
          value: '0',
          width: 210,
          disabled: false,
          show: true,
          data: [
            { name: '全部', value: '0' },
            { name: '未下载', value: '1' },
            { name: '已下载', value: '2' }
          ]
        }
      }
    ],
    btns: [
      {
        comOpt: {
          id: 'query',
          value: '查询',
          width: 210,
          disabled: false,
          click: this.querySearchAction
        }
      },
      {
        comOpt: {
          id: 'query',
          value: '新建',
          width: 210,
          disabled: false,
          click: this.addSearchAction
        }
      }
    ]
  }
  private tableOption: any = {
    stripe: true,
    column: [
      { name: '序号', value: 'index', fixed: 'left', width: 50, align: 'center' },
      { name: '任务名称', value: 'taskName', fixed: 'left', align: 'center' },
      { name: '创建时间', value: 'createTime', align: 'center' },
      { name: '更新时间', value: 'updateTime', align: 'center' },
      { name: '任务状态', value: 'taskStatusName', align: 'center' },
      { name: '任务内容', value: 'taskContent', align: 'center' },
      { name: '任务结果', value: 'jobResult', align: 'center' },
      {
        name: '操作',
        value: '',
        align: 'center',
        fixed: 'right',
        width: 150,
        operations: [
          {
            label: '详情',
            disCallBack() {
              return false
            },
            handlerClick: (row: any) => {
              console.log(row)
            }
          },
          {
            label: '编辑',
            handlerClick: (row: any) => {
              console.log(row)
            }
          },
          {
            label: '删除',
            disCallBack(row: any) {},
            handlerClick: (row: any) => {}
          }
        ]
      }
    ],
    data: [],
    // 是否分页
    pagination: true,
    // 分页参数
    pageOpt: {
      currentPage: 1,
      total: 0,
      pageSizes: [10, 20, 30, 40, 50],
      pageSize: 10
    }
  }
  private option = {
    title: {
      text: '测试饼图'
    },
    legend: {},
    series: [
      {
        type: 'pie',
        radius: '55%',
        center: ['50%', '50%'],
        roseType: 'radius',
        label: {
          color: 'rgba(255, 255, 255, 0.3)'
        },
        labelLine: {
          lineStyle: {
            color: 'rgba(255, 255, 255, 0.3)'
          },
          smooth: 0.2,
          length: 10,
          length2: 20
        },
        itemStyle: {
          color: '#c23531',
          shadowBlur: 200,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        },

        animationType: 'scale',
        animationEasing: 'elasticOut',
        animationDelay: function() {
          return Math.random() * 200
        }
      }
    ]
  } as any
  private ei: any | ECharts = {}
  get key() {
    return this.$route.path
  }
  private change() {
    this.option = Object.assign({}, this.option)
    this.$set(this.option.series[0], 'data', [
      { name: '小猫', value: 30 },
      { name: '小狗', value: 50 }
    ])
    this.$set(this.option.legend, 'data', ['小猫', '小狗'])
  }

  querySearchAction() {
    const v = this.form.getValue()
    console.log(v)
  }
  addSearchAction() {}

  mounted() {
    // http.get('/demo/pie').then((res: any) => {
    //   console.log(res)
    //   this.option.series[0].data = res.data
    //   this.option.legend.data = res.name
    //   this.loaded = true
    // })

    axios.get('/test/tablelist').then((resp: any) => {
      const list = resp.data.data.list as Array<object>
      this.tableOption.data = list
    })
  }
}
</script>
<style lang="less">
.box {
  margin: 10px auto;
  width: 1000px;
  height: 300px;
}
</style>
