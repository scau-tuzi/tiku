<template>
  <div>
    <el-form>
      <el-form-item label="权限名称">
        <el-input v-model="name" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item>
        <label>所属父权限</label>
        <PermissionTree :onSelectChanged="onSelectChanged" :selectId="parentId"></PermissionTree>
      </el-form-item>

      <el-form-item label="请求方法">
        <el-select placeholder="请选择请求方法" v-model="selectValue">
          <el-option key="GET" label="GET" value="GET"></el-option>
          <el-option key="POST" label="POST" value="POST"></el-option>
          <el-option key="PUT" label="PUT" value="PUT"></el-option>
          <el-option key="DELETE" label="DELETE" value="DELETE"></el-option>
          <el-option key="无" label="无" value="无"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="请求路径">
        <el-input v-model="path" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <el-button type="primary" round @click="onSubmit()">确定</el-button>
    <el-button @click="OnCleanClick()" round>返回</el-button>
  </div>
</template>

<script>
import PermissionTree from "./PermissionTree";
import { addPermission, updatePermission } from "../api/Permission";
export default {
  name: "PermissionInput",
  components: { PermissionTree },
  props: {
    isAdd: Boolean, //是新增还是编辑
    rowData: Object,
    OnCleanClick: Function
  },
  data() {
    return {
      selectValue: "",
      name: "",
      parentId: 0,
      path: "",
      thisId: 0
    };
  },
  created() {
    this.setAllData();
  },
  watch: {
    rowData: function() {
      this.setAllData();
    }
  },
  methods: {
    //事件函数
    onSelectChanged(id) {
      this.parentId = id;
    },
    onSubmit() {
      let me = this;
      let dto = this.getDtoWithoutId();
      if (!this.isAdd) {
        (dto.id = this.thisId),
          updatePermission(dto, b => {
            //todo
            if (b.code === "ok") {
              me.$router.replace({ path: "PermissionTreePage" });
              // me.$router.go(0)
            } else {
              this.$message({ type: "error", message: b.data });
            }
          });
      } else {
        addPermission(dto, b => {
          //todo
          if (b.code === "ok") {
            me.$router.go(0);
          } else {
            this.$message({ type: "error", message: b.data });
          }
        });
      }
    },

    //数据函数
    setAllData() {
      if (!this.isAdd) {
        this.selectValue = this.rowData.method;
        this.name = this.rowData.name;
        this.parentId = this.rowData.parentPermission;
        this.path = this.rowData.url;
        this.thisId = this.rowData.id;
      }
    },
    //辅助函数
    getDtoWithoutId() {
      return {
        //id:this.thisId,
        name: this.name,
        url: this.path,
        method: this.selectValue,
        parentPermission: this.parentId
      };
    }
  }
};
</script>

<style scoped>
</style>