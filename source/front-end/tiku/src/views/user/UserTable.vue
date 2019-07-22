<template>
  <el-container style="border: 1px solid #eee">
    <el-main>
      <el-row gutter="0">
        <el-col span="20">
          <el-button class="el-button" align="left" plain @click="dialogFormVisible_add=true">添加用户</el-button>
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
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="id" label="账号" width="250"></el-table-column>
          <el-table-column prop="name" label="用户名" width="250"></el-table-column>
          <!-- <el-table-column prop="icon" label="头像" width="125"></el-table-column> -->
          <el-table-column prop="role" label="角色" width="310">
            <template slot-scope="scope">
              <el-tag
                v-for="(role,index) in scope.row.role"
                v-bind:key="index"
                disable-transitions
              >{{role}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="200">
            <template slot-scope="scope">
              <el-button size="mini" @click="dialogFormVisible_edit=true;editUser(scope.row,scope.column,scope.$index)">编辑</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.row,scope.column,scope.$index)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
    </el-main>
    <el-footer align="center">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="this.listSize"
        @current-change="this.handlerchange"
      ></el-pagination>
    </el-footer>
    <el-dialog title="添加用户" :visible.sync="dialogFormVisible_add" width="30%" center>
        <el-form :model="form">
            <el-form-item label="用户名称" :label-width="formLabelWidth">
            <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="用户密码" :label-width="formLabelWidth">
            <el-input v-model="form.password" autocomplete="off" show-password=true></el-input> 
            <!-- show-password=true -->
            </el-form-item>
            <el-form-item label="用户角色" :label-width="formLabelWidth">
            <el-select v-model="value" placeholder="请选择用户角色" filterable multiple>
               <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
               ></el-option>
            </el-select>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible_add = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible_add = false;addUsers()">确 定</el-button>
            <!-- ;dialogFormVisible_add = false -->
        </div>
    </el-dialog>
    <el-dialog title="编辑用户" :visible.sync="dialogFormVisible_edit" width="30%" center>
        <el-form :model="form_edit">
            <el-form-item label="用户名称" :label-width="formLabelWidth">
            <el-input v-model="form_edit.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="用户密码" :label-width="formLabelWidth">
            <el-input v-model="form_edit.password" autocomplete="off" show-password=true></el-input>
            </el-form-item>
            <el-form-item label="用户角色" :label-width="formLabelWidth">
            <el-select v-model="value_edit" placeholder="请选择用户角色" filterable multiple>
               <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
               ></el-option>
            </el-select>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible_edit = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible_edit = false;editUserConfirm()">确 定</el-button>
        </div>
    </el-dialog>    
  </el-container>
</template>


<script>
import { getUserList,addUser,delUser,changeUser } from "../../api/User";
import { getRoles } from "../../api/Role";
export default {
  data() {
    return {
      dialogFormVisible_add: false,
      dialogFormVisible_edit: false,
      listSize: 0,
      tableChecked:[],
      search: "",
      role:[],
      id_tmp:'',//临时存放要操作行的账号id
      tableData: [
        // {
        //   id:'535436532',
        //   name:'新垣结衣',
        //   // icon:'',
        //   role:'录入员',
        //   password:'123'
        // },{
        //   id:'43546453',
        //   name:'林颖欣',
        //   // icon:'',
        //   role:'审核员',
        //   password:'456'
        // }
      ],
      form: {
        name: '',
        password: '',
        role: ''
      },
      options: [
        // {
        //   value: 'role_1',
        //   label: '角色一'
        // }, {
        //   value: 'role_2',
        //   label: '角色二'
        // }, {
        //   value: 'role_3',
        //   label: '角色三'
        // }
      ],
      value:[],
      value_edit:[],
      form_edit: {
        name: '',
        password: '',
        role: []
      },
    };
  },
  methods: {
    //多选触发
    handleSelectionChange:function(val) {
      console.log("handleSelectionChange--", val); //选中项
      this.tableChecked = val;
    },    
    //获取用户列表
    handlerchange: function(currentpage) {
      this.getData(currentpage-1);
    },
    getData: function(currentpage) {
      // console.log("change");
      var _this = this;
      let callback = (pd, size) => {
        this.listSize = size*10;
        var res = [];
        
        console.log("get it");
        console.log(pd);
        console.log("finish!");      
        this.$store.commit("setNewUsers", pd);
        console.log('存了什么啊？---');
        console.log(this.$store.state.allUser);
        pd.filter(v => {
          var role_id=[];
          var role_tmp=[];
          v.roleIds.forEach((id)=>{
            role_id.push(id);
            for(let i=0;i<this.$store.state.allRole.length;i++){
              if(id===this.$store.state.allRole[i].id){
                role_tmp.push(this.$store.state.allRole[i].roleName);
              }
            }
          });
          let ress = {
            id: v.id,
            name: v.username,
            roleId: role_id,
            role: role_tmp
          };
          res.push(ress);
        });
        console.log("check table data----");
        console.log(res);
        _this.tableData = res;
      };
      getUserList(currentpage, callback);
    },
    //添加用户
    addUsers: function(){
      // let username=this.form.name;
      // let password=this.form.password;
      let roleId_tmp=[];
      this.value.forEach((v)=>{
        for(let i=0;i<this.$store.state.allRole.length;i++){
          if(this.$store.state.allRole[i].roleName===v){
            roleId_tmp.push(this.$store.state.allRole[i].id);
            break;
          }
        }
        
      });
      let newUser={
        username: this.form.name,
        password: this.form.password,
        roleIds: roleId_tmp
      };
      console.log('add a new user!----');
      console.log(newUser);
      addUser(newUser, b => {
            if (b.code === "ok") {
            alert("添加成功");
            // todo 返回上一页
            this.$router.go(0); //页面刷新（要加上）
            
          } else {
            alert("添加失败" + b.data);
          }
        });     
    },
    //显示用户的旧信息
    editUser:function(row,column,index){
      let role_tmp=[];
      let allRole_tmp=[];
      this.id_tmp=row.id;
      row.role.forEach((role_name)=>{
        role_tmp.push(role_name)
      });
      this.value_edit=role_tmp;
      this.form_edit={
        name: row.name,
        password: this.$store.state.allUser[index].password,//其实也拿不到密码，为null
        role: role_tmp
      }

      // for (let i = 0; i < this.$store.state.allRole.length; i++) {
      //   allRole_tmp.push(this.$store.state.allRole[i].roleName); //获取store的标签
      // }
    },
    //编辑用户信息
    editUserConfirm:function(){
      let roleId_tmp=[];
      console.log('form edit ?---');
      console.log(this.value_edit);
      this.value_edit.forEach((v)=>{
        for(let i=0;i<this.$store.state.allRole.length;i++){
          if(this.$store.state.allRole[i].roleName===v){
            roleId_tmp.push(this.$store.state.allRole[i].id);
            break;
          }
        }
      });
      let pd={
        id: this.id_tmp,
        username: this.form_edit.name,
        password: this.form_edit.password,
        roleIds: roleId_tmp
      };
      console.log('change a  user!----');
      console.log(pd);
      console.log(this.value_edit);
      changeUser(pd, b => {
            if (b.code === "ok") {
            alert("修改成功");
            // todo 返回上一页
            this.$router.go(0); //页面刷新（要加上）           
          } else {
            alert("修改失败" + b.data);
          }
        });   
    },
    getRole: function(){
      console.log("getRole!--");
      var _this = this;
      let callback = (pd, size) => {
        this.$store.commit("setNewRoles", pd);
        pd.filter(v => {
          let ress = {
            value: v.roleName,
            label: v.roleName
          };
          this.options.push(ress);
        });
      };
      getRoles(0, callback);
      console.log(this.options);      
    },
    handleDelete: function(row,column,index){
      this.$confirm("确定删除该用户？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          console.log("删除用户");
          console.log(this.$store.state.allUser[index]);
          //alert('submit!');
          let userId = [];
          userId.push(this.$store.state.allUser[index].id);
          // console.log("删除用户id1---");
          // console.log(this.$store.state.allRole[index].id);
          // console.log("删除用户id2---");
          // console.log(row.id);
          // let roleId=this.$store.state.allRole[index].id;
          delUser(userId, b => {
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
    batchDelete:function(rows){
      this.$confirm("确定批量删除用户？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          console.log("批量删除用户");
          //alert('submit!');
          let userId = [];
          rows.forEach((row)=>{
            userId.push(row.id)
          });
          delUser(userId, b => {
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
    this.getRole();
    this.getData(0);
    
  },
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