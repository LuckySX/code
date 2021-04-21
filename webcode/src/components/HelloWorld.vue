<!--
 * @Author: renxiaofan
 * @Date: 2020-03-19 14:44:47
 * @LastEditors: zhangjipei
 * @LastEditTime: 2020-04-21 17:03:13
 * @Description: 
 -->
<template>
  <div :class="hello.container">
    <h1>{{ msg }}</h1>
    <h2>{{ appName }}</h2>
  </div>
</template>
<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { http } from '@/common/request'
import { Action, namespace } from 'vuex-class'

const appModuleVuex = namespace('app')
@Component({})
export default class HelloWorld extends Vue {
  @Prop() private msg!: string

  @appModuleVuex.Getter('getName') appName!: string

  @Action('app/setAppNameAction') setAppAction!: Function

  mounted() {
    console.log(this)
    http.get('/demo/getData').then(resp => {
      console.log(resp)
    })

    const key = this.$ls.get('demoKey')
    if (key === null) {
      this.$ls.set('demoKey', 'xiaofan')
    } else {
      console.log(key)
    }

    this.setAppAction({ name: 'sz' })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style module="hello">
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  /* margin: 0 10px; */
}
a {
  color: #42b983;
}
.container {
  margin: 10px;
}
</style>
