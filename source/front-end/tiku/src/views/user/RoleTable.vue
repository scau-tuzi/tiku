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
          <el-table-column prop="authority" label="拥有权限" width="400">
            <template slot-scope="scope">
              <el-tag
                v-for="(author,index) in scope.row.authority"
                v-bind:key="index"
                disable-transitions
              >{{author}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="286">
            <template slot-scope="scope">
              <el-button size="mini" @click="editRole(scope.row,scope.column, scope.$index)">编辑</el-button>
              <el-button
                size="mini"
                type="success"
                @click="modifyAuthor(scope.row,scope.column, scope.$index)"
              >修改权限</el-button>
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
    <el-dialog title="添加角色" :visible.sync="dialogFormVisible_add">
      <el-form :model="form">
        <el-form-item label="角色名称" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item >
          <label>选择权限</label>
          <PermissionTree :onSelectChanged=onSelectChanged :selectId="parentId"></PermissionTree>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false;addRoles()">确 定</el-button>
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
import PermissionTree from "./PermissionTree"
import { getRoles,changeRole,delRole,addRole } from "../../api/Role";
import { getPermissionTree } from "../../api/Permission";
export default {
  components:{PermissionTree},
  data() {
    return {
      listSize: 0,
      listPageNumber: 0,
      tableChecked: [], //被选中的记录数据
      search: "",
      id:'',
      dialogFormVisible_add:false,
      form: {
      },
      options: [
      ],
      value:[],
      treeData:'',
      tableData: [
        // {
        //   id:'304958345',
        //   role:'录入员',
        //   authority:['录入题目','组卷']
        // },{
        //   id:'39850394',
        //   role:'审核员',
        //   authority:['审核题目']
        // }
      ],
      permissionId:[],
      permissionName:[]

    };
  },
  methods:{
    handleSelectionChange(val) {
      console.log("handleSelectionChange--", val); //选中项
      this.tableChecked = val;
    },    
    //获取角色列表（名称、权限）
    //通过获取的权限id和权限接口，拿到权限的名字，放在authority数组中
    handlerchange: function(currentpage) {
      this.getData(currentpage - 1);
    },
    getData: function(currentpage) {
      // console.log("change");
      this.listPageNumber = currentpage;
      var _this = this;
      let callback = (pd, size) => {
        this.listSize = size * 10;
        var res = [];
        console.log("get it");
        console.log(pd);
        console.log("finish!");
        this.$store.commit("setNewRoles", pd);
        // console.log(this.$store.state.allRole);
        // console.log(this.$store.state.commits[0].id);
        pd.filter(v => {
          let author_id = [];
          let author_tmp = [];
          if (v.permissionList !== null) {
            for (let i = 0; i < v.permissionList.length; i++) {
              author_id.push(v.permissionList[i]);
            }
          }
          //跟所有的权限id对比，取权限名称
          for(let j=0;j<author_id.length;j++){
            for(let k=0;k<_this.permissionId.length;k++){
              if(author_id[j]===_this.permissionId[k]){
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
        console.log("why?=--------");
        console.log(res);
        _this.tableData = res;
      };
      getRoles(currentpage, callback);
    },    
    //添加角色（后台要修改，待测试）
    addRoles: function(){
      
    //   this.$prompt("请输入新增的角色名称", "提示", {
    //     confirmButtonText: "保存",
    //     cancelButtonText: "取消"
    //   })
    //     .then(( {value} ) => {
    //       // let tmp=[];
    //       // tmp.push(value);
    //       console.log("提交新角色");
    //       var res = {
    //         roleName: value,
    //         // permissionList: [3,4]
    //       };
    //       // alert(pd);
    //       // console.log(pd);
    //       //alert('submit!');
    //       console.log(res);
    //       let callback = b => {
    //         if (b.code === "ok") {
    //           alert("添加成功");
    //           // todo 返回上一页
    //           this.$message({
    //             type: "success",
    //             message: "新增角色: " + value
    //           });
    //           // this.$router.go(0); //页面刷新（要加上）
    //           this.getData(this.listPageNumber);
    //         }
    //       };
    //       addRole(res, callback);
    //     })
    //     .catch(() => {
    //       this.$message({
    //         type: "info",
    //         message: "取消添加"
    //       });
    //     });      
    },
    //修改角色，后台有修改，待测试
    editRole:function(row, column, index){
      //修改角色的弹窗
      this.$prompt("请输入修改后的角色名称", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputValue: row.role //初始文本为该标签
      })
        .then(({ value }) => {
          console.log("修改角色名");
          // var pd = [];
          let pd = {
            id: this.$store.state.allRole[index].id,
            roleName: value,
            permissionList: this.$store.state.allRole[index].permissionList
          };
          console.log("check pd!----");
          // pd.push(pd_tmp);
          // alert(pd);
          console.log(pd);
          //alert('submit!');
          changeRole(pd, b => {
            if (b.code === "ok") {
              alert("修改成功");
              // todo 返回上一页
              this.$message({
                type: "success",
                message: "修改后角色名为: " + value
              });
              this.$router.go(0); //页面刷新（要加上）
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消修改"
          });
        });      
    },
    //单行删除（待测试）
    handleDelete:function(row,column,index){
      this.$confirm("确定删除该角色?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          console.log("删除角色");
          console.log(this.$store.state.allRole[index]);
          //alert('submit!');
          let roleId = [];
          roleId.push(this.$store.state.allRole[index].id);
          // let roleId=this.$store.state.allRole[index].id;
          delRole(roleId, b => {
            if (b.code === "ok") {
              alert("删除成功");
            }
            this.$router.go(0); //页面刷新（要加上）
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });      
    },
    //批量删除，待测试
    batchDelete:function(rows){
      console.log("看看批量删除拿到的是啥----");
      console.log(rows);
      this.$confirm("确定批量删除角色?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
      .then(() => {
        let batchDelId=[];
        rows.forEach(row => {
          batchDelId.push({
            id: row.id
          });
        }); 
        console.log("看看批量删除拿到的是啥id----");   
        console.log(batchDelId);  
        delRole(batchDelId, b => {
          if (b.code === "ok") {
            alert("删除成功");
          }
          this.$router.go(0); //页面刷新（要加上）
        })        
        .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除"
            });
        })  
      })        
    },
    modifyAuthor:function(row,column,index){
      console.log("拿到角色的id吗？----");
      console.log(this.$store.state.allRole[index].id);
      this.$router.push({
        path: "/ModifyProblem",//跳到权限列表
        //query对象获取问题和答案
        query: {
          modifyAuthorId: this.$store.state.allRole[index].id
        }
      });
    },
    getPermission:function(){
      var _this = this;
      let callback = (pd) => {
        var res = [];
        console.log("get it");
        console.log(pd);
        console.log("finish!");
        this.$store.commit("setPermission", pd);
        // console.log(this.$store.state.allRole);
        // console.log(this.$store.state.commits[0].id);
        let auth_id_tmp = [];
        let auth_name_tmp = [];
        let auth_child_tmp = [];
        pd.filter(v => {
          //跟所有的权限id对比，取权限名称
          auth_id_tmp.push(v.id);
          auth_name_tmp.push(v.name);
          auth_child_tmp.push(v.childPermissions);
        });  
        for(let i=0;i<auth_child_tmp.length;i++){
          for(let j=0;j<auth_child_tmp[i].length;j++){
            auth_id_tmp.push(auth_child_tmp[i][j].id);
            auth_name_tmp.push(auth_child_tmp[i][j].name);
          }
        }
        _this.permissionId = auth_id_tmp;
        _this.permissionName = auth_name_tmp;
        console.log('拿到权限吗？？---');
        console.log(_this.permissionId);
        console.log(_this.permissionName);
      };
      getPermissionTree(callback);      
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
    
  }
}
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