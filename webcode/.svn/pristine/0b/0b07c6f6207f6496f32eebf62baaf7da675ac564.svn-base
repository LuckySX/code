<template>
  <el-input
    v-model="value"
    :disabled="option.disabled"
    :placeholder="placeholder"
    auto-complete="on"
    :style="widthStyle"
    :type="option.iptType"
    :rows="option.rows"
    :class="'input' + option.id"
    @focus="focusFuc(option.id)"
    @blur="blurFuc(option.id)"
  ></el-input>
</template>
<script lang="ts">
import { Component, Prop, Vue, Watch, Ref } from 'vue-property-decorator'
import { IFormItemCompOpt } from './FormTypes'
@Component({})
export default class VInput extends Vue {
  @Prop() opt!: IFormItemCompOpt
  // 内部属性
  value: string
  placeholder: string

  mounted() {
    const { value, placeholder = '请输入内容' } = this.opt
    this.value = value
    this.placeholder = placeholder
  }

  getValue() {
    return { [this.opt.id]: this.value || '' }
  }
  setValue(val: string) {
    this.value = val
  }

  get widthStyle() {
    return {
      width: this.opt.width + 'px'
    }
  }

  @Watch('option.value', { deep: true })
  onValueChange(val: string) {
    this.value = val
  }

  @Ref('rel') readonly rel!: VInput
}
</script>
