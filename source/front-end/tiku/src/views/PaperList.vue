<template>
    <el-container style="border: 1px solid #eee">
      <el-main>
        <el-row>
          <el-col span=20>
            <el-button class="el-button" align="left" plain @click="jumpInput">添加试卷</el-button>
            <!-- <el-button type="primary" plain>全选</el-button> -->
            <el-button type="success" @click="paperBatchDelete" plain>批量删除</el-button>
            <el-button type="info" plain>导入Excel</el-button>
            <el-button type="warning" @click="paperBatchTag" plain>标签批量修改</el-button>
          </el-col>
          <el-col span=4>
            <el-input  style="display: inline-block;width: 180px"
                      placeholder="请输入搜索内容">
            </el-input>
          </el-col>
        </el-row>
        <el-row>
          <GeneralTable
            ref="dialog"
            v-bind:paper-id="paperId"
            v-bind:value="value"
            v-bind:centerDialogVisible_single="centerDialogVisible_single"
            v-bind:table-info="paperListTable"
            v-on:handleButton="handleButton"
            v-on:handleSelectionChange="handleSelectionChange"
            v-on:modifyTag="paperChangeBatchTag"
          ></GeneralTable>
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
            :total="this.listSize"
            @current-change="this.handlerchange"
          ></el-pagination>
        </el-row>
      </el-footer>
    </el-container>
</template>

<script>
    import GeneralTable from "../components/GeneralTable";
    import paperListTable from "../data/mock/PaperListTableInfoMock";
    import {delPaper, getPapers} from "../api/Paper";
    import {createPaperOrderMock} from "../data/mock/CreatePaperInfoMock";
    //获取试卷
   function getPaperData(currentPage){
      console.log("change")
      let _this=this;
      let callback=(pd,size)=>{
        _this.listSize = size*10;
        var res=[];
        console.log("get it")
        console.log(pd)
        this.$store.commit("setModifyPaper",pd);
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
            paper:v
            //answer:answerDeep
          };
          res.push(ress)
        });
        console.log(res);
        _this.paperListTable.tableData=res;
      };

      getPapers(currentPage,callback,1,10);
      console.log("aaaaaa")
    }
    export default {
        name: "PaperList",
      components: {GeneralTable},
      mounted: function(){
        getPaperData.bind(this).call(this,0);
      },
      watch: {
        '$route': 'data'
      },
      data(){
          return{
            listSize:100,
            paperListTable,
            tableData:[],
            centerDialogVisible_single:false,
            value:[],
            options:[],
            paperId: 0,
            selectedBatchPaperId:[],
            batchPaperTag: []
          }
      },
      methods:{
        handlerchange:function(currentPage){//获取题目
          console.log("123");
          this.getPaperData(currentPage-1);
        },
        getPaperData,
        //监听子组件
        handleButton(val){
          if(val.method==='ViewPaper'){
            this.viewPaper(val.index,val.row)
          }else if(val.method==='showTags'){
            this.showTags(val.row,val.col,val.index)
          }else if(val.method==='editPaper'){
            console.log("wwwww")
            this.editPaper(val.index,val.row);
          }else {
            this.handleDelete(val.index,val.row)
          }
        },
        //批量删除试卷
        handleSelectionChange(val){
          this.selectedBatchPaperId=val;
          //console.log("d");
          console.log(this.selectedBatchPaperId);
        },
        paperBatchDelete(){
          console.log("batch");
          var _this=this;
          this.$confirm("确定删除问题?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(() => {
            let batch=[];
            for (let i=0;i<this.selectedBatchPaperId.val.length;i++){
              //console.log(this.seletedBatchPaperId.val[i].paperId);
              batch.push(this.selectedBatchPaperId.val[i].paperId);
            }
              delPaper(batch, b => {
                if (b.code === "ok") {
                  alert("删除成功");
                  _this.getPaperData(0);
                }else {
                  alert("删除失败"+b.data)
                }
                // this.$router.go(0); //页面刷新（要加上）
                this.getPaperData();
              });

          });
        },
        //批量修改试卷标签
        paperBatchTag(){
          this.$refs.dialog.centerDialogVisible_single=true;
          this.$refs.dialog.isBatchChangeTag=true;
          console.log(this.$refs.dialog);
          this.batchPaperTag=this.$refs.dialog._data.value;
          },
        paperChangeBatchTag(){
          let value=this.$refs.dialog._data.value;
          for(let i=0;i<this.$refs.dialog._data.multipleSelection.length;i++) {
            let selectedPaper = this.$refs.dialog._data.multipleSelection[i].paper;
            //console.log("test change1!--");
            //console.log(selectedPaper);
            selectedPaper.tags = [];
            value.forEach(v => {
              selectedPaper.tags.push({
                value: v
              });
            });
            console.log("test change2!--");
            console.log(selectedPaper);
            // changePaper(selectedPaper, b => {
            //   if (b.code === "ok") {
            //     alert("修改成功");
            //     // this.$router.go(0); //页面刷新（要加上）
            //   } else {
            //     alert("修改失败" + b.data);
            //   }
            // });
          }
        },
        //添加试卷
          jumpInput(){
            this.$store.commit("paperEditData", []);
            createPaperOrderMock.tableData=[];
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
          var _this=this;
          this.$confirm("确定删除该问题?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(() => {

             delPaper([row.paperId], b => {
              if (b.code === "ok") {
                alert("删除成功");
               _this.getPaperData(0);
              }else {
                alert("删除失败"+b.data)
              }
              // this.$router.go(0); //页面刷新（要加上）
              //this.getPaperData();
            });
          });
        },
        //编辑试卷按钮
        editPaper(index,row){
          console.log(row)
          this.$store.commit("setPaperEditData", row);
          this.$router.push({path: '/ModifyPaper'})
        },
        //查看试卷
        viewPaper(index,row){
          console.log(row)
          this.$store.commit("setPaperEditData", row);
          this.$router.push({path: '/ViewPaper',
            query:{
            isViewOnePro: false,
              isViewPaper:true,}
          });
        },
        showTags(row,col,index){
          this.value=row.tag;
          this.centerDialogVisible_single=true;
          this.paperId=index;
        },
        //分页
        handlerchange: function (currentPage){//获取题目
          this.getPaperData(currentPage-1);
        },
        },
      getPaperData,

    }
</script>

<style scoped>

</style>
