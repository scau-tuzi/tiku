<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>试卷名称</span>
    </div>
    <div v-for="o in 4" :key="o" class="text item">
      {{o + "、题目\n" }}
    </div>
    <el-checkbox v-for="f in AllFieldInfo">
      {{f.title}}
    </el-checkbox>
  </el-card>
</template>

<script>
  import {AllFieldInfo} from "@/data/mock/FiledInfoMock"
    export default {
        name: "ViewPaper",
      data(){
          return{
            AllFieldInfo
          }
      }
    }
</script>

<style scoped>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 1000px;
  }

</style>
