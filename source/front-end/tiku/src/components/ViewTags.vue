<template>
  <el-container style="border: 1px solid #eee">
    <el-main>
      <el-row gutter="0">
        <el-col span=20>
          <el-button class="el-button" align="left" plain @click="addTag">添加标签</el-button>
          <!-- <el-button type="primary" plain>全选</el-button> -->
          <el-button type="success" plain @click="batchDelete(tableChecked)">批量删除</el-button>
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
                @click="handleDelete(scope.$index)">删除</el-button>
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
  import {addTags} from "../api/Tag";
  import {getTagsList} from "../api/Tag";
  import {ChangeTag} from "../api/Tag";
  import {delTag} from "../api/Tag";
   export default {
      data() {
        return {
          tableChecked:[],//被选中的记录数据
          search: '',
          tableData: [],
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
        //获取标签列表
        handlerchange:function(){
            this.getData();
        },getData:function (){
            console.log("change")
            var _this=this;
            let callback=(pd)=>{
            var res=[];
            console.log("get it");
            console.log(pd);
            this.$store.commit("setNewCommits",pd);
            console.log("aha");
            // console.log(this.$store.state.commits);
            // console.log(this.$store.state.commits[0].id);
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

            //多选触发
            handleSelectionChange(val){
              console.log("handleSelectionChange--",val); //选中项
              this.tableChecked = val;             
            },

            //批量删除
            batchDelete(rows){ 
              let delIndex=[];
              let delId=[];
              // console.log("batchDelete--",rows); 
              for(var i=0;i<this.tableData.length;i++){//获取选中项id
                for(var j=0;j<rows.length;j++){
                  if(this.tableData[i].tag==rows[j].tag){
                    // delIndex.push(i);
                    delId.push(this.$store.state.commits[i].id);
                  }
                }
              }
              // console.log("4--",delId);
              this.$confirm('确定批量删除标签?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              //alert('submit!');           
              delTag(delId,(b)=>{
                if(b.code==="ok"){
                  alert("删除成功");                 
                };
                // this.$router.go(0); //页面刷新（要加上）                
              });
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消删除'
              });          
            });              

            },

            //删除选中项
            handleDelete(index){
              this.$confirm('确定删除该标签?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              console.log("删除标签");
              console.log(this.$store.state.commits[index].id);
              //alert('submit!');
              let tagId=[];
              tagId[0]=this.$store.state.commits[index].id;              
              delTag(tagId,(b)=>{
                if(b.code==="ok"){
                  alert("删除成功");                 
                };
                // this.$router.go(0); //页面刷新（要加上）                
              });
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消删除'
              });          
            });
          },

            //修改标签待测试
            editTags(row,column,index){//修改标签的弹窗
                this.$prompt('请输入修改后的标签名称', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                inputValue:row.tag//初始文本为该标签
                }).then(({ value }) => {
              console.log("修改标签");
              var pd=[];
              let pd_tmp={
                id:this.$store.state.commits[index].id,
                value:value
              }
              pd.push(pd_tmp);
              // alert(pd);
              console.log(pd);
              //alert('submit!');
              ChangeTag(pd,(b)=>{
                if(b.code==="ok"){
                  alert("修改成功");
                  // todo 返回上一页
                  this.$message({
                      type: 'success',
                      message: '修改后标签为: ' + value
                  });   
                }
              });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消修改'
                    });       
                });
            },

            //添加标签
            addTag(){
              this.$prompt('请输入新增的标签名称', '提示', {
              confirmButtonText: '保存',
              cancelButtonText: '取消',
              }).then(({ value }) => {
              console.log("提交标签");
              let pd=[];
              let res={
                value:value
              };
              pd.push(res);
              // alert(pd);
              console.log(pd);
              //alert('submit!');
              addTags(pd,(b)=>{
                if(b.code==="ok"){
                  alert("添加成功");
                  // todo 返回上一页
                    this.$message({
                        type: 'success',
                        message: '新增标签: ' + value
                    });  
                    // this.$router.go(0); //页面刷新（要加上）              
                }
              });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消添加'
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