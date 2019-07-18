<template>
    <el-container style="border: 1px solid #eee">
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
          <GeneralTable v-bind:table-info="paperListTable" v-on:handleButton="handleButton"></GeneralTable>
        </el-row>
<!--        <el-row>-->
<!--          <el-button v-on:click="this.getData()">测试</el-button>-->
<!--          <el-table :data="tableData">-->
<!--            <el-table-column prop="paperId" label="ID"></el-table-column>-->
<!--            <el-table-column prop="paperTitle" label="Title"></el-table-column>-->
<!--            <el-table-column prop="tag" label="tag"></el-table-column>-->
<!--            <el-table-column prop="map" label="map"></el-table-column>-->
<!--          </el-table>-->
<!--        </el-row>-->
      </el-main>
      <el-footer align="center">
        <el-row>
          <el-pagination
            background
            layout="prev, pager, next"
            :total="1000"
            :current-page.sync="currentPage"
            @current-change="this.handlerchange"
          ></el-pagination>
        </el-row>
      </el-footer>
    </el-container>
</template>

<script>
    import GeneralTable from "../components/GeneralTable";
    import paperListTable from "../data/mock/PaperListTableInfoMock";
    import {getPapers} from "../api/Paper";
    //获取试卷
   function getPaperData(currentPage){
      console.log("change")
      var _this=this;
      let callback=(pd,size)=>{
        this.listSize = size;
        var res=[];
        console.log("get it")
        console.log(pd)
        //this.$store.commit("setNewProblems",pd);
        pd.filter(v=>{
          let ts = [];
          let map ="";
          let problemDeep=[];
          let answerDeep=[];
          if(v.tags!==null){
            for(let i =0; i<v.tags.length; i++){
              ts.push(v.tags[i].value)
            }
          }
          console.log(v)
          if(v.serialProblemIdMap!=null){
            //Object.keys(v.serialProblemIdMap).length
            for(let i =0;i<Object.keys(v.serialProblemIdMap).length;i++){
             // map.push(v.serialProblemIdMap[0][i].serialNumber)
              map=map+(v.serialProblemIdMap[i])+','
            }
          }
          if(v.deep){
            // for(let i =0;i<v.problems.length;i++){
            //   problemDeep.push(v.problems[i].problem)
            //   //problemDeep.push(v.problems[i].problem,v.problems[i].answer,v.problems[i].tags)
            //   //answerDeep.push(v.problems[i].answer)
            // }
            //problemDeep.push(v.problems)
          }
          let ress={
            paperId:v.paper.id,
            title:v.paper.title,
            tag:ts,
            map:map,
            problems:v.problems,
            //answer:answerDeep
          };
          res.push(ress)
        });
        console.log(res);
        paperListTable.tableData=res;
      };

      getPapers(0,callback,1,10);
      console.log("aaaaaa")
    }
    export default {
        name: "PaperList",
      components: {GeneralTable},
      mounted: function(){
        getPaperData.bind(this).call(this);
      },
      watch: {
        '$route': 'data'
      },
      data: function (){
          return{
            listSize:0,
            paperListTable,
            tableData:[]
          }
      },
      methods:{
        //监听子组件
        handleButton(val){
          if(val.method==='ViewPaper'){
            this.handleEdit(val.index,val.row)
          }else if(val.method==='showTags'){
            this.showTags(val.row,val.col,val.index)
          }else if(val.method==='editPaper'){
            console.log("wwwww")
            this.editPaper(val.index,val.row);
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
            this.$router.push({path: '/ViewPaper',
              //query对象获取问题和答案
              // query: {
              //   modifyQues:row.problem,
              //   modifyAnsw:row.answer
              // }
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
        },
        //编辑试卷按钮
        editPaper(index,row){
          console.log(row)
          this.$store.commit("setPaperEditData", row);
          this.$router.push({path: '/CreatePaper'})
        }
        },
      handlerchange:function(currentPage){//获取题目
        this.getPaperData(currentPage-1);
      },
      getPaperData
    }
</script>

<style scoped>

</style>
