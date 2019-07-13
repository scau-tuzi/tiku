<!--/**页面框架**/-->
<template>
  <el-container>
    <el-header>
      <Logo class="homeLink"/>
      <div >
        <h1 class="mainTitle">Test title</h1>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: '/about' }">题目列表</el-breadcrumb-item>
          <el-breadcrumb-item>活动列表</el-breadcrumb-item>
          <el-breadcrumb-item>活动详情</el-breadcrumb-item>
        </el-breadcrumb>
      </div>

    </el-header>
    <el-container>

      <el-aside>
        <el-row class="tac">
          <el-col :span=24>
            <el-menu :router=true :default-active="$route.name" class="el-menu-vertical-demo">
              <el-submenu v-for="(menu,index) in menuInfo.items"
                          v-bind:index="index+1+''" v-bind:key="menu.title">
                <template slot="title">
                  <i class="el-icon-location"></i>
                  <span>{{menu.title}} </span>
                </template>
                <!--                                todo index-->
                <el-menu-item  v-bind:index="toString(index+1)+'-'+toString(indexs+1)"
                               v-for="(item,indexs) in menu.subItems" v-bind:key="item.title"
                               v-bind:route="item.path">
                  {{item.title}}
                  <!-- v-bind:index="toString(index+1)+'-'+toString(indexs+1)" -->
                  <!-- v-for="(item,index) in menu.subItems" -->
                </el-menu-item>
              </el-submenu>
            </el-menu>
          </el-col>
        </el-row>
      </el-aside>
      <el-main>

        <slot></slot>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  import Logo from "./Logo";
  import mockData from "../data/mock/MenuInfoMock";
  import MenuInfo from "../data/model/MenuInfo";
  export default {
    name: "Framework",
    components: {
      Logo
    },
    props:{
      menuInfo:MenuInfo,
    }
  }
</script>

<style lang="scss" scoped>
  $sideBarWidth: 200px;
  .el-header {
    text-align: left;
    margin-top: auto;
    display: flex;
    flex-direction: row;
    padding: 0;
    /*height: 30px;*/
  }

  .el-aside {

    text-align: left;
    display: flex;
    flex-direction: column;
    width: $sideBarWidth !important; //默认被注入300px了
  }

  .homeLink {
    width: $sideBarWidth !important;
    margin: 0;
  }
  .mainTitle{
    margin-top: 5px;
    margin-bottom: 5px;
  }


  .el-main {
    /*background-color: #E9EEF3;*/
    /*color: #333;*/
  }
  .el-menu-vertical-demo{
    min-height: 100%;
  }

</style>
