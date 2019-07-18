<template>
  <el-container style="border: 1px solid #eee">
    <el-main>
      <el-row gutter="0">
        <el-col span="20">
          <el-button class="el-button" align="left" plain @click="addUser;dialogFormVisible=true">添加用户</el-button>
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
          <el-table-column prop="id" label="账号" width="365"></el-table-column>
          <el-table-column prop="name" label="用户名" width="265"></el-table-column>
          <!-- <el-table-column prop="icon" label="头像" width="125"></el-table-column> -->
          <el-table-column prop="role" label="角色" width="220"></el-table-column>
          <el-table-column label="操作" width="200">
            <template slot-scope="scope">
              <el-button size="mini" @click="editUser(scope.$index)">编辑</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.$index)">删除</el-button>
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
    <el-dialog title="添加用户" :visible.sync="dialogFormVisible" width="30%" center>
        <el-form :model="form">
            <el-form-item label="用户账号" :label-width="formLabelWidth">
            <el-input v-model="form.id" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="用户名称" :label-width="formLabelWidth">
            <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="用户角色" :label-width="formLabelWidth">
            <el-select v-model="form.role" placeholder="请选择用户角色" filterable>
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
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
    </el-dialog>
  </el-container>
</template>


<script>
import { getUserList } from "../../api/User";
export default {
  data() {
    return {
      dialogFormVisible: false,
      listSize: 0,
      search: "",
      tables: [
        {
          id:'535436532',
          name:'新垣结衣',
          icon:'',
          role:'录入员'
        }
      ],
      form: {
        id: '',
        name: '',
        role: ''
      },
      options: [
        {
          value: 'role_1',
          label: '角色一'
        }, {
          value: 'role_2',
          label: '角色二'
        }, {
          value: 'role_3',
          label: '角色三'
        }
      ],
    };
  },
  mounted: function() {
    this.getData(0);
  },
  methods: {
    //获取标签列表
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
        this.$store.commit("setNewCommits", pd);
        // console.log(this.$store.state.commits[0].id);
        pd.filter(v => {
          let ress = {
            id: v.id,
            name: v.username,
            icon: v.image,
            role: v.roleId
          };
          res.push(ress);
        });
        console.log(res);
        _this.tables = res;
      };
      getUserList(currentpage, callback);
    },
    addUser: function(){
        
    }
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