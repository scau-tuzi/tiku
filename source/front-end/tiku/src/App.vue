'
<template>
  <router-view></router-view>
</template>

<script>

import Framework from "./components/Framework";
import mockData from "./data/mock/MenuInfoMock";
export default {
  name: 'app',
  data:function() {
    return {
      mockData
    };
  }

}
</script>

<style>
  body{
    margin: 0;

  }
</style>
