/**
  权限树
**/
<template>
  <div>
    <el-table
            :data="tableData"
            style="width: 100%;margin-bottom: 20px;"
            row-key="id"
            border
            stripe
            height="800"
            max-height="800"
            default-expand-all
            :tree-props="{children: 'childPermissions', hasChildren: 'hasChildren'}">
      <el-table-column prop="name" label="名字" width="250"/>
      <el-table-column prop="method" label="方法" width="180"/>
      <el-table-column prop="url" label="路径"/>
      <el-table-column label="操作" :width="400" fixed="right">
        <template slot-scope="scope" >
         <el-button @click="onEdit(scope.$index, scope.row)" type="success" icon="el-icon-edit" round>编辑</el-button>
            <el-button @click="onDelete(scope.$index, scope.row)" type="danger" icon="el-icon-delete" round>删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>

  import {getPermissionTree} from "../api/Permission";

  function loadData() {
    let me=this;
    getPermissionTree((b=>{
      me.tableData=b;
    }))
  }
  export default {
    name: "PermissionTable",
    data() {
      return {
        tableData: []
      }
    },
    props:{
      onEdit:Function,// (index,row)
      onDelete:Function
    },
    methods: {
      loadData
    },
    mounted(){
      this.loadData()
    }
  }
</script>