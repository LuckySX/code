<!--
 * @Description: 
 * @Autor: niumiaomiao
 * @Date: 2020-03-24 10:15:24
 * @LastEditors: fuping
 * @LastEditTime: 2020-05-25 23:26:37
 -->
<template>
  <div id="app">
    <!-- <router-link to="/home">home</router-link>
    |
    <router-link to="/test">test</router-link> -->
    <Layout />
  </div>
</template>
<script lang="ts">
// @ is an alias to /src
import { Component, Vue } from 'vue-property-decorator'
import Layout from '@/views/Layout.vue'
@Component({
  components: {
    Layout
  }
})
export default class App extends Vue {}
</script>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  background-color: #fafbff;
  height: 100%;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
