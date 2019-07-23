<template>
  <div>
    <el-row>
      <el-button icon="el-icon-arrow-left" round>返回</el-button>
      <el-button @click="onNewButton()" type="primary" icon="el-icon-plus" round>新增</el-button>
      <el-button type="success" icon="el-icon-search" round>搜索</el-button>
      <el-button type="info" icon="el-icon-eleme" round>假装有功能</el-button>
      <el-button type="warning" icon="el-icon-star-off" round>占位符</el-button>
      <el-button type="danger" icon="el-icon-bell" round>装饰品</el-button>
    </el-row>
    <el-row class="bar"></el-row>
    <el-row>
      <PermissionTable :onDelete="this.onDelete" :onEdit="this.onEdit" class="tree"></PermissionTable>
    </el-row>

    <el-dialog
      :before-close="onEditClose"
      ref="dialog"
      title="编辑对话框"
      :visible.sync="this.editDialogFormVisible"
    >
      <PermissionInput
        class="inputFrom"
        :OnCleanClick="this.OnEditCleanClick"
        :rowData="this.rowData"
        ref="inputFrom"
      />
    </el-dialog>

    <el-dialog :before-close="onNewClose" title="新建对话框" :visible.sync="this.newDialogFormVisible">
      <PermissionInput class="inputFrom" :OnCleanClick="this.OnNewCleanClick" isAdd />
    </el-dialog>
  </div>
</template>

<script>
import PermissionTable from "../../components/PermissionTable";
import PermissionInput from "../../components/PermissionInput";
import { deletePermissions } from "../../api/Permission";

export default {
  name: "PermissionTreePage",
  components: { PermissionTable, PermissionInput },
  data() {
    return {
      editDialogFormVisible: false,
      newDialogFormVisible: false,
      rowData: {}
    };
  },
  methods: {
    onEdit(index, row) {
      this.editDialogFormVisible = true;
      this.rowData = row;
    },
    onDelete(index, row) {
      deletePermissions([row.id], b => {
        if (b.code === "ok") {
          this.$message({ type: "success", message: b.data });
          let _this = this;
          setTimeout(() => {
            _this.$router.go(0);
          }, 700);
        } else {
          this.$message({ type: "error", message: b.data });
        }
      });
    },
    onNewButton() {
      this.newDialogFormVisible = true;
    },
    onEditClose(done) {
      this.editDialogFormVisible = false;
    },
    OnEditCleanClick() {
      this.editDialogFormVisible = false;
    },
    onNewClose(done) {
      this.newDialogFormVisible = false;
    },
    OnNewCleanClick() {
      this.newDialogFormVisible = false;
    }
  }
};
</script>

<style scoped>
.bar {
  padding: 10px;
}
.tree {
  padding-left: 10px;
}
.inputFrom {
  padding: 10px;
}
</style>