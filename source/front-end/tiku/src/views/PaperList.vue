<template>
    <el-container>
      <el-main>
        <el-row>
          <el-col span=20>
            <el-button class="el-button" align="left" plain @click="jumpInput">添加试卷</el-button>
            <!-- <el-button type="primary" plain>全选</el-button> -->
            <el-button type="success" plain>批量删除</el-button>
            <el-button type="info" plain>导入Excel</el-button>
            <el-button type="warning" plain>标签批量修改</el-button>
          </el-col>
          <el-col span=4>
            <el-input v-model="search" style="display: inline-block;width: 180px"
                      placeholder="请输入搜索内容">
            </el-input>
          </el-col>
        </el-row>
        <el-row>
          <GeneralTable v-bind:table-info="paperListTable"></GeneralTable>
        </el-row>
      </el-main>
    </el-container>
</template>

<script>
    import GeneralTable from "../components/GeneralTable";
    import paperListTable from "../data/mock/PaperListTableInfoMock";

    export default {
        name: "PaperList",
      components: {GeneralTable},
      data: function (){
          return{
            paperListTable
          }
      },
      methods:{
        //监听子组件
        handleButton(val){
          if(val.method==='ViewPaper'){
            this.handleEdit(val.index,val.row)
          }else if(val.method==='showTags'){
            this.showTags(val.row,val.col,val.index)
          }else if(val.method==='EditPaper'){
            1+1-2;
          }else {
            this.handleDelete(val.index,val.row)
          }
        },
          jumpInput(){
            //this.$router.push("/cart")
            //传递的参数用{{ $route.query.goodsId }}获取
            this.$router.push({path: '/CreatePaper'})
            //this.$router.go(-2)
            //后退两步
          },
        //编辑操作
        handleEdit (index, row) {
          console.log(index, row),
            // alert(index+row.problem+row.answer),
            //转到ModifyProblem页面
            this.$router.push({path: '/ModifyProblem',
              //query对象获取问题和答案
              query: {
                modifyQues:row.problem,
                modifyAnsw:row.answer
              }
            })
        },
        //删除操作
        handleDelete (index, row) {
          this.tableData.splice(index, 1);//删除该行
          this.$message({
            message: "操作成功！",
            type: 'success'
          })
          console.log(index, row)
        }
      }
    }
</script>

<style scoped>

</style>
