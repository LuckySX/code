<!--
 * @Author: fuping
 * @Date: 2020-05-19 22:00:35
 * @LastEditors: fuping
 * @LastEditTime: 2020-05-19 22:24:03
 * @Description: 
-->
<template>
  <div :class="error.container">
    <div>Sorry,</div>
    <div>the page you requested was not found.</div>
  </div>
</template>

<script>
export default {
  name: '404'
}
</script>
<style module="error">
.container {
  width: 100%;
  height: 100%;
}
.container div:first-child {
  font-size: 30px;
  margin-bottom: 30px;
  margin-top: 30px;
}
</style>
