<!--
 * @Description: 
 * @Autor: niumiaomiao
 * @Date: 2020-03-30 22:10:55
 * @LastEditors: niumiaomiao
 * @LastEditTime: 2020-09-16 14:07:50
 -->
<template>
  <div :class="website.container">
    <div :class="website.title">
      <span :class="website.index">{{ data.orderNum }}</span>
      <span :class="website.nameBox" :title="showButton ? data.cnName + data.enName : ''">
        <span :class="website.cnName">{{ data.cnName }}</span>
        <span :class="website.enName">{{ data.enName }}</span>
      </span>
      <span v-if="showButton" :class="website.toBig" @click="showBig"></span>
    </div>
    <div :class="website.numberBox">
      <div :class="website.numberContent">
        <p :class="website.number">{{ data.visiteNum }}</p>
        <p :class="website.type">昨日访问</p>
      </div>
      <div :class="website.numberContent">
        <p :class="[website.number, website.yellow]">{{ data.addNum }}</p>
        <p :class="website.type">较前日新增</p>
      </div>
      <div :class="[website.numberContent, website.numberLast]">
        <p :class="[website.number, website.green]">
          {{ data.trendNum }}
          <span :class="website.gb">{{ data.unit }}</span>
        </p>
        <p :class="website.type">昨日流量</p>
      </div>
    </div>
    <div :class="website.chartsBox">
      <div :class="website.changeBtnBox">
        <span :class="[website.changeBtn, days == 7 ? website.active : '']" @click="changeDays(7, type)">
          近7天
        </span>
        <span :class="[website.changeBtn, days == 30 ? website.active : '']" @click="changeDays(30, type)">
          近30天
        </span>
        <span :class="[website.changeBtn, days == 90 ? website.active : '']" @click="changeDays(90, type)">
          近90天
        </span>
      </div>
      <BaseCharts v-if="loadedLine" :option="lineDoubleOption" :theme="'vintage'"></BaseCharts>
    </div>
  </div>
</template>

<script lang="ts">
import _ from 'lodash'
import { Component, Vue, Prop, Watch } from 'vue-property-decorator'
import { lineDoubleOption } from '@/views/Common/charts/lineDoubleOption'
import { http } from '@/common/request'
@Component({
  components: {}
})
export default class Website extends Vue {
  // 是否显示放大按钮
  @Prop() private showButton: boolean
  @Prop() private data: any
  @Prop() private days: number
  @Prop() private type: string
  private loadedLine = true
  private showDialog = false
  private newOption = {
    xAxis: [
      {
        data: []
      }
    ],
    yAxis: [
      {
        data: []
      },
      {
        data: []
      }
    ],
    series: [
      {
        data: []
      },
      {
        data: []
      }
    ]
  }
  private lineDoubleOption: any = _.defaultsDeep(this.newOption, lineDoubleOption)
  @Watch('days', { deep: true })
  private daysChanged() {
    this.changeDays(this.days, this.type)
  }
  @Watch('data', { deep: true })
  private dataChanged() {
    this.days = 7
    this.changeOption(this.data)
  }
  private showBig() {
    this.showDialog = true
    this.$emit('showBig', this.showDialog)
  }
  private changeOption(val) {
    this.newOption.xAxis[0].data = val.dateArr
    this.newOption.series[0].data = val.flowArr
    this.newOption.series[1].data = val.personArr
  }
  private changeDays(val, type) {
    this.$emit('changeDays', val, type)
    interface TrafficParams<T = any> {
      data: T
      rows: object[]
    }
    http
      .post<TrafficParams>('/interFlow/trafficTrend', {
        type: '',
        domain: this.data.enName,
        name: this.data.cnName,
        time: val
      })
      .then(resp => {
        this.changeOption(resp.data.rows[0])
      })
  }
  mounted() {
    this.changeOption(this.data)
  }
}
</script>

<style module="website">
.container {
  width: 100%;
  height: 100%;
}
.title {
  width: 100%;
  height: 40px;
  line-height: 40px;
  border-bottom: 1px solid #e4e8eb;
  overflow: hidden;
}
.index {
  width: 24px;
  height: 24px;
  display: inline-block;
  background: #2593f2;
  color: #fff;
  line-height: 24px;
  text-align: center;
  border-radius: 2px;
  margin-left: 16px;
  float: left;
  margin-top: 10px;
}
.nameBox {
  display: inline-block;
  float: left;
  width: calc(100% - 80px);
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.cnName {
  font-size: 16px;
  color: #000;
  margin-left: 10px;
}
.enName {
  font-size: 14px;
  color: #666;
  margin-left: 5px;
}
.toBig {
  width: 24px;
  height: 24px;
  line-height: 24px;
  display: block;
  background: url('~@/assets/images/interFlow/toBig.png') no-repeat;
  float: right;
  margin: 10px 10px 0px 0px;
}
.toBig:hover {
  cursor: pointer;
}
.numberBox {
  height: 60px;
  display: flex;
  text-align: center;
  border-bottom: 1px solid #e4e8eb;
}
.numberContent {
  border-right: 1px solid #e4e8eb;
  flex: 1;
  justify-content: center;
  align-items: center;
}
.numberLast {
  border: none;
}
.number {
  font-family: NumFont;
  font-size: 24px;
  color: #0b56a7;
  margin-top: 5px;
}
.yellow {
  color: #ffa514;
}
.green {
  color: #10ac84;
}
.gb {
  font-size: 12px;
}
.type {
  font-size: 14px;
  color: #666;
  margin-top: 5px;
}
.chartsBox {
  background: rgba(243, 244, 248, 0.5);
  width: calc(100% - 20px);
  height: calc(100% - 100px);
  margin: 0 auto;
  position: relative;
}
.changeBtnBox {
  position: absolute;
  right: 33px;
  top: 5px;
  z-index: 1000;
}
.changeBtn {
  display: inline-block;
  width: 50px;
  height: 20px;
  line-height: 20px;
  border: 1px solid #2593f2;
  border-radius: 2px;
  font-size: 12px;
  color: #2593f2;
  text-align: center;
  margin-right: 15px;
}
.changeBtn:hover {
  cursor: pointer;
  color: #fff;
  background: #2593f2;
}
.active {
  color: #fff;
  background: #2593f2;
}
</style>
