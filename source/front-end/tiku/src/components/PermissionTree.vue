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
            default-expand-all
            :tree-props="{children: 'childPermissions', hasChildren: 'hasChildren'}">
      <el-table-column
              prop="name"
              label="名字"
              sortable
              width="180">
      </el-table-column>
      <el-table-column
              prop="url"
              label="路径"
              sortable
              width="180">
      </el-table-column>
      <el-table-column
              prop="method"
              label="方法">
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
    name: "PermissionTree",
    data() {
      return {
        tableData: []
      }
    },
    methods: {
      loadData
    },
    mounted(){
      this.loadData()
    }
  }
</script>