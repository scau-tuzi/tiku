<template>
  <el-container style="border: 1px solid #eee">
    <el-main>
      <el-row gutter="0">
        <el-col span="20">
          <el-button class="el-button" align="left" plain @click="addRole">添加角色</el-button>
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
          <el-table-column prop="role" label="角色名称" width="725"></el-table-column>
          <el-table-column label="操作" width="286">
            <template slot-scope="scope">
              <el-button size="mini" @click="editRole(scope.row,scope.column, scope.$index)">编辑</el-button>
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
  </el-container>
</template>
<script>
// import { addRoles } from "../../api/Role";
export default {
  data() {
    return {
      listSize: 0,
      listPageNumber: 0,
      tableChecked: [], //被选中的记录数据
      search: "",
      tableData: [
        {
          role:'录入员'
        },{
          role:'审核员'
        }
      ]
    };
  },
  methods:{
    //获取标签列表
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
        this.$store.commit("setNewCommits", pd);
        console.log(this.$store.state.commits);
        // console.log(this.$store.state.commits[0].id);
        pd.filter(v => {
          let ress = {
            role: v.value
          };
          res.push(ress);
        });
        console.log(res);
        _this.tableData = res;
      };
      getRolesList(currentpage, callback);
    },    
    addRole: function(){
      this.$prompt("请输入新增的角色名称", "提示", {
        confirmButtonText: "保存",
        cancelButtonText: "取消"
      })
        .then(( {value} ) => {
          console.log("提交新角色");
          var res = {
            value: value
          };
          // alert(pd);
          // console.log(pd);
          //alert('submit!');
          console.log(res);
          let callback = b => {
            if (b.code === "ok") {
              alert("添加成功");
              // todo 返回上一页
              this.$message({
                type: "success",
                message: "新增角色: " + value
              });
              // this.$router.go(0); //页面刷新（要加上）
              this.getData(this.listPageNumber);
            }
          };
          addRoles(res, callback);
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消添加"
          });
        });      
    },
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
            id: this.$store.state.commits[index].id,
            value: value
          };
          console.log(pd.id);
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
              // this.$router.go(0); //页面刷新（要加上）
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消修改"
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