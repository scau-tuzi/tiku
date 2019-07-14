<template>
  <el-container style="border: 1px solid #eee">
    <el-main>
      <el-row gutter="0">
        <el-col span=20>
          <el-button class="el-button" align="left" plain @click="addTags">添加标签</el-button>
          <!-- <el-button type="primary" plain>全选</el-button> -->
          <el-button type="success" plain>批量删除</el-button>
        </el-col>
        <el-col span=4>
          <el-input v-model="search" style="display: inline-block;width: 180px"
                    placeholder="请输入搜索内容">
          </el-input>
        </el-col>
      </el-row>
      <el-row><el-col span=24><div></div></el-col></el-row>
      <el-row>
        <el-table
          :data="tables"
          border
          stripe
          style="width: 100%"
          @selection-change="handleSelectionChange">
          <el-table-column
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            prop="tag"
            label="标签名称"
            width="725">
          </el-table-column>
          <el-table-column
            label="操作"
            width="286"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="editTags(scope.row,scope.column, scope.$index)">编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
    </el-main>
    <el-footer align="center">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="1000"

        @current-change="this.handlerchange">
      </el-pagination>
    </el-footer>
  </el-container>
</template>

<script>
  import {getTagsList} from "../api/Tag";
   export default {
      data() {
        return {
          search: '',
          tableData: [{
            tag: '2016-05-02'
          }, {
            tag: '2016-05-04'
          }, {
            tag: '2016-05-01'
          }, {
            tag: '2016-05-03'
          }, {
            tag: '2016-05-04'
          }, {
            tag: '2016-05-01'
          }, {
            tag: '2016-05-03'
          }]
        }
      },
        computed:{//搜索功能
        tables(){
            const search = this.search
            if (search) {
            // 检查指定数组中符合条件的所有元素。
            return this.tableData.filter(data => {
                return Object.keys(data).some(key => {
                // 没有找到返回-1；
                return String(data[key]).toLowerCase().indexOf(search.toLowerCase()) > -1
                })
            })
            }
            return this.tableData
        }
        },
        methods:{
        handlerchange:function(){//获取标签列表
            this.getData();
        },getData:function (){
            console.log("change")
            var _this=this;
            let callback=(pd)=>{
            var res=[];
            console.log("get it");
            console.log(pd);
            this.$store.commit("setNewCommits",pd);
            pd.filter(v=>{
                let ress={
                tag:v.value
                };
                res.push(ress)
            });
            console.log(res);
            _this.tableData=res;
            };
            getTagsList(callback);
            },
            editTags(row,column,index){//修改标签的弹窗
                this.$prompt('请输入修改后的标签名称', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                inputValue:row.tag//初始文本为该标签
                }).then(({ value }) => {
                    this.$message({
                        type: 'success',
                        message: '修改后的标签名称为: ' + value
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消修改'
                    });       
                });
            }

        },
        mounted: function() {
            this.getData();
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
