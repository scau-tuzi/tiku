<template>
  <el-container style="border: 1px solid #eee">
    <el-main>
      <el-row gutter="0">
        <el-col span="20">
          <el-button class="el-button" align="left" plain @click="dialogFormVisible_add = true">添加角色</el-button>
          <!-- <el-button type="primary" plain>全选</el-button> -->
          <el-button type="success" plain @click="batchDelete(tableChecked)">批量删除</el-button>
        </el-col>
        <el-col span="4">
          <el-input
            v-model="search"
            style="display: inline-block;width: 180px"
            placeholder="请输入搜索内容"
          ></el-input>
        </el-col>
      </el-row>
      <el-row>
        <el-col span="24">
          <div></div>
        </el-col>
      </el-row>
      <el-row>
        <el-table
          :data="tables"
          border
          stripe
          style="width: 100%"
          @selection-change="handleSelectionChange"
        >
          <el-table-column prop="id" type="selection" width="55"></el-table-column>
          <el-table-column prop="role" label="角色名称" width="325"></el-table-column>
          <el-table-column prop="authority" label="拥有权限" width="500">
            <template slot-scope="scope">
              <el-tag
                v-for="(author,index) in scope.row.authority"
                v-bind:key="index"
                disable-transitions
              >{{author}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="180">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="dialogFormVisible_edit = true;editRole(scope.row,scope.column,scope.$index)"
              >编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.row,scope.column, scope.$index)"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
    </el-main>
    <!-- 添加角色窗口 -->
    <el-dialog title="添加角色" :visible.sync="dialogFormVisible_add">
      <el-form :model="form">
        <el-form-item label="角色名称" >
          <el-input v-model="form.name" ></el-input>
        </el-form-item>
        <el-form-item>
          <label>选择权限</label>
          <!-- <PermissionTree :onSelectChanged=onSelectChanged :selectId="parentId"></PermissionTree> -->
          <template>
            <el-tree
              :data="treeData"
              show-checkbox
              ref="tree"
              node-key="id"
              :props="defaultProps"
              @check-change="handleCheckChange"
            ></el-tree>
          </template>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible_add = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible_add = false;onSubmit()">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 编辑角色窗口 -->
    <el-dialog title="编辑角色" :visible.sync="dialogFormVisible_edit">
      <el-form :model="form_edit">
        <el-form-item label="角色名称" >
          <el-input v-model="form_edit.name" ></el-input>
        </el-form-item>
        <el-form-item>
          <label>选择权限</label>
          <!-- <PermissionTree :onSelectChanged=onSelectChanged :selectId="parentId"></PermissionTree> -->
          <template>
            <el-tree
              :data="treeData_edit"
              show-checkbox
              ref="tree_edit"
              node-key="id"
              :props="defaultProps"
              @check-change="handleCheckChange"
            ></el-tree>
          </template>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible_edit = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible_edit = false;editSubmit()">确 定</el-button>
      </div>
    </el-dialog>
    <el-footer align="center">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="this.listSize"
        @current-change="this.handlerchange"
      ></el-pagination>
    </el-footer>
  </el-container>
</template>
<script>
import { getRoles, changeRole, delRole, addRole } from "../../api/Role";
import { getPermissionTree } from "../../api/Permission";
export default {
  props: {
    onSelectChanged: Function, //(id)
    selectId: Number
  },
  // components:{PermissionTree},
  data() {
    return {
      /**
       *  @id 权限id
       *  @name 权限名字
       *  @cId 子权限数组
       */
      permissionArray: {
        id: -1,
        name: "name",
        cId: []
      },
      treeData: [], //添加角色的权限树
      treeData_edit: [], //编辑角色的权限树
      defaultProps: {
        label: "name",
        children: "childPermissions"
      },
      parentId: 0,
      listSize: 0,
      listPageNumber: 0,
      tableChecked: [], //被选中的记录数据
      search: "",
      id_tmp: "", //临时存放选中行的roleId
      dialogFormVisible_add: false,
      dialogFormVisible_edit: false,
      form: {},
      form_edit: {
        name:""
      },
      options: [],
      value: [],
      tableData: [
      ],
      permissionId: [],
      permissionName: []
      // auth_id_tmp:[],
      // auth_name_tmp:[],
      // auth_child_tmp:[]
    };
  },
  methods: {
    // 加载权限树数据
    loadData() {
      let me = this;
      getPermissionTree(b => {
        me.treeData = b;
        me.treeData_edit = b;
      });
    },
    handleSelectionChange(val) {
      this.tableChecked = val;
    },
    //获取角色列表（名称、权限）
    handlerchange: function(currentpage) {
      this.getData(currentpage - 1);
    },
    getData: function(currentpage) {
      this.listPageNumber = currentpage;
      var _this = this;
      let callback = (pd, size) => {
        this.listSize = size * 10;
        var res = [];
        this.$store.commit("setNewRoles", pd);
        pd.filter(v => {
          let author_id = [];
          let author_tmp = [];
          if (v.permissionList !== null) {
            for (let i = 0; i < v.permissionList.length; i++) {
              author_id.push(v.permissionList[i]);
            }
          }
          //跟所有的权限id对比，取权限名称
          for (let j = 0; j < author_id.length; j++) {
            for (let k = 0; k < _this.permissionId.length; k++) {
              if (author_id[j] === _this.permissionId[k]) {
                author_tmp.push(_this.permissionName[k]);
                break;
              }
            }
          }
          let ress = {
            id: v.id,
            role: v.roleName,
            authority: author_tmp
          };
          res.push(ress);
        });
        _this.tableData = res;
      };
      getRoles(currentpage, callback);
    },
    //提交编辑
    editSubmit: function() {
      let newRole = {
        id: this.id_tmp,
        roleName: this.form_edit.name,
        permissionList: this.$refs.tree_edit.getCheckedKeys()
      };
      changeRole(newRole, b => {
        if (b.code === "ok") {
          this.getData(this.listPageNumber);
          this.$message({ type: "success", message: b.data });
        } else {
          this.$message({ type: "error", message: b.data });
        }
      });
    },

    //编辑，显示已有角色信息
    editRole: function(row, column, index) {
      this.form_edit.name = row.role;
      this.$refs.tree_edit.setCheckedKeys(
        this.$store.state.allRole[index].permissionList
      );
      this.id_tmp = this.$store.state.allRole[index].id;
    },

    //单行删除（待测试）Request method 'POST' not supported
    handleDelete: function(row, column, index) {
      this.$confirm("确定删除该角色?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          let roleId = [];
          roleId.push(this.$store.state.allRole[index].id);
          // let roleId=this.$store.state.allRole[index].id;
          delRole(roleId, b => {
            if (b.code === "ok") {
              this.getData(this.listPageNumber);
              this.$message({ type: "success", message: b.data });
            }
          });
        })
        .catch(() => {
          this.$message({ type: "info", message: "已取消删除" });
        });
    },
    //批量删除，待测试 Request method 'POST' not supported
    batchDelete: function(rows) {
      this.$confirm("确定批量删除角色?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        let batchDelId = [];
        rows.forEach(row => {
          batchDelId.push({
            id: row.id
          });
        });
        delRole(batchDelId, b => {
          if (b.code === "ok") {
            this.getData(this.listPageNumber);
            this.$message({ type: "success", message: b.data });
          }
        }).catch(() => {
          this.$message({ type: "info", message: "已取消删除" });
        });
      });
    },
    // modifyAuthor:function(row,column,index){
    //   console.log("拿到角色的id吗？----");
    //   console.log(this.$store.state.allRole[index].id);
    //   this.$router.push({
    //     path: "/ModifyProblem",//跳到权限列表
    //     //query对象获取问题和答案
    //     query: {
    //       modifyAuthorId: this.$store.state.allRole[index].id
    //     }
    //   });
    // },
    //获取所有权限（这里只能取两级权限）
    getPermission: function() {
      var _this = this;
      let callback = pd => {
        var res = [];
        this.$store.commit("setPermission", pd);
        let auth_id_tmp = [];
        let auth_name_tmp = [];
        let auth_child_tmp = [];
        pd.filter(v => {
          //取父权限的数据
          auth_id_tmp.push(v.id);
          auth_name_tmp.push(v.name);
          auth_child_tmp.push(v.childPermissions);
        });
        /**
         * hu获取所以权限内容
         */
        let GAP = function(tmp) {
          if (tmp !== null) {
            for (let i = 0; i < tmp.length; i++) {
              auth_id_tmp.push(tmp[i].id);
              auth_name_tmp.push(tmp[i].name);

              if (tmp[i].childPermissions !== null) {
                GAP(tmp[i].childPermissions);
              }
            }
          }
        };
        for (let i = 0; i < auth_child_tmp.length; i++) {
          GAP(auth_child_tmp[i]);
        }
        _this.permissionId = auth_id_tmp;
        _this.permissionName = auth_name_tmp;
      };
      getPermissionTree(callback);
    },

    onSubmit() {
      let newRole = {
        roleName: this.form.name,
        permissionList: this.$refs.tree.getCheckedKeys()
      };
      addRole(newRole, b => {
        if (b.code === "ok") {
          this.getData(this.listPageNumber);
          this.$message({ type: "success", message: b.data });
          // todo 返回上一页
        } else {
          this.$message({ type: "error", message: b.data });
        }
      });
    }
  },
  computed: {
    //搜索功能
    tables() {
      const search = this.search;
      if (search) {
        // 检查指定数组中符合条件的所有元素。
        return this.tableData.filter(data => {
          return Object.keys(data).some(key => {
            // 没有找到返回-1；
            return (
              String(data[key])
                .toLowerCase()
                .indexOf(search.toLowerCase()) > -1
            );
          });
        });
      }
      return this.tableData;
    }
  },
  mounted: function() {
    this.getPermission();
    this.getData(0);
    this.loadData();
    // this.$refs.tree.setCheckedKeys([this.selectId]);
  }
};
</script>
<style scoped>
.el-row {
  margin-bottom: 10px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
</style>