'
<template>
  <Framework v-bind:menu-info="mockData">
    <router-view></router-view>
  </Framework>
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
  },
  components: {
    Framework,
  }
}
</script>

<style>
  body{
    margin: 0;

  }
</style>
