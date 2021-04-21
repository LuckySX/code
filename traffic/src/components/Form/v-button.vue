<template>
  <el-button
    v-if="option.popover"
    :type="option.btnType ? option.btnType : 'primary'"
    :size="option.btnSize ? option.btnSize : ''"
    v-popover:option.popover
    :disabled="option.disabled"
    @click.native="clickHandler"
  >
    {{ option.value.replace(/^(\W{1})(\W{1})$/, '$1 $2') }}
  </el-button>
  <el-button
    v-else
    :type="option.btnType ? option.btnType : 'primary'"
    :size="option.btnSize ? option.btnSize : ''"
    :disabled="option.disabled"
    @click.native="clickHandler"
  >
    {{ option.value.replace(/^(\W{1})(\W{1})$/, '$1 $2') }}
  </el-button>
</template>
<script lang="ts">
import { Component, Prop, Vue, Ref } from 'vue-property-decorator'
import { IFormItemCompOpt } from './FormTypes'
import * as _ from 'lodash'
@Component({})
export default class VButton extends Vue {
  @Prop() opt!: IFormItemCompOpt

  clickHandler() {
    if (this.opt.click && _.isFunction(this.opt.click)) {
      this.opt.click()
    }
  }
  getValue() {
    return { [this.opt.id]: this.opt.value }
  }

  @Ref('rel') readonly rel!: VButton
}
</script>
