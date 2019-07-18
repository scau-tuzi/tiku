<template>
  <el-container>
    <el-main>
      <el-row>
        <el-col span=3>
          <el-button  align="left" plain @click="complete">完成</el-button>
          <el-button type="primary" plain>全选</el-button>
        </el-col>
        <el-col span=17>
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="" style="margin-right: 10px">
            <el-form-item label="" prop="name">
              <el-input v-model="ruleForm.name" placeholder="请输入试卷名称"></el-input>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col span=4>
          <el-autocomplete
            v-model="state"
            :fetch-suggestions="querySearchAsync"
            placeholder="搜索"
            @select="handleSelect"
          ></el-autocomplete>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span=12.5 style="margin-right: 10px">
          <GeneralTable usePagination :handleChange="leftTablePageChange" :listSize="listSize" v-bind:table-info="leftTable"></GeneralTable>
        </el-col>
       <el-col :span=11>
         <GeneralTable :listSize="listSize" v-bind:table-info="createPaperOrderMock"></GeneralTable>
       </el-col>
      </el-row>
      <el-row>
        <pre style="text-align: left">
      {{leftTable.tableData}}
       </pre>
        <hr>
        <pre style="text-align: left">
      {{createPaperOrderMock.tableData}}
       </pre>
      </el-row>

    </el-main>
  </el-container>
</template>

<script>
  import Sortable from 'sortablejs';
  import draggable from 'vuedraggable';
  import GeneralTable from "./GeneralTable";
  import tikuTableInfo from "../data/mock/TikuTableInfoMock";
  import {createPaperInfoMock, createPaperOrderMock} from "../data/mock/CreatePaperInfoMock";
  import {getProblems} from "../api/Problem";

  /**
   * 隐藏右边出现过的题目，检查有没有出现过
   * @param leftId
   */
  function checkInRight(leftProblemId){
    let t = this.createPaperOrderMock.tableData;
    if(t!==null && t.length>0){
      for (let i = 0; i < t.length; i++) {
          if(leftProblemId===t[i].id){
            return false;
          }
      }
    }
    return true;
  }

  /**
   * 加载题目数据
   * @param page
   */
  function loadDataToLeftTable(page) {
    console.log("will get problem data")
    var _this = this;
    let callback = (pd, size) => {
      var res = [];
      // {
      //   id: '100',
      //   problem: '2016-05-02',
      //   answer: '王小虎1',
      //   tag: '22'
      // },

      pd.filter(v => {
        if(!checkInRight.bind(_this).call(_this,v.problem.id)){
          return;
        }
        let ts = [];
        if (v.tags !== null) {
          for (let i = 0; i < v.tags.length; i++) {
            ts.push(v.tags[i].value);
          }
        }
        if (v.answer === null) {
          v.answer = {
            answerText: ""
          };
        }

        let ress = {
          id: v.problem.id,
          problem: v.problem.problemText,
          answer: v.answer.answerText,
          tag: ts
        };
        res.push(ress);
      });
      console.log(res);
      _this.leftTable.tableData = res;
      _this.listSize=size;

    }; //callback
    getProblems(page, callback, 1);
  } //function
  export default {
        name: "CreatePaper",
    components: {GeneralTable},
    data(){
      return{
        tikuTableInfo,
        leftTable:createPaperInfoMock,
        createPaperOrderMock,
        listSize:100,
        curPage:0,
        ruleForm: {
          name: ''
        },
        rules: {
          name: [
            {required: true, message: '请输入试卷名称', trigger: 'blur'}
          ]
        }
      }
      },
    mounted() {
        this.loadDataToLeftTable(0);
        this.rowDrop();
        this.editPaper();

    },
    methods: {
      loadDataToLeftTable,
      leftTablePageChange(newPage){
        this.curPage=newPage-1;
        this.loadDataToLeftTable(newPage-1);
      },
      editPaper(){
        console.log("paperEdit")
        console.log(this.$store.state.paperEditData)
        //createPaperOrderMock.tableData=this.$store.state.paperEditData.problems;
        let p=this.$store.state.paperEditData.problems;
        if(p===undefined)
          return;
        let res = [];
        for(let i=0;i<this.$store.state.paperEditData.problems.length;i++){
          let ts = [];
          if (p[i].tags !== null) {
            for (let i = 0; i < p[i].tags.length; i++) {
              ts.push(p[i].tags[i].value);
            }
          }
          if (p[i].answer === null) {
            p[i].answer = {
              answerText: ""
            };
          }
          let ress = {
            id: p[i].problem.id,
            problem: p[i].problem.problemText,
            answer: p[i].answer.answerText,
            tag: ts
          };
          res.push(ress);
        }
        console.log("res");
        createPaperOrderMock.tableData=res;
      },
      complete(){
        //this.$router.push("/cart")
        //传递的参数用{{ $route.query.goodsId }}获取
        this.$router.push({path: '/PaperList'})
        //this.$router.go(-2)
        //后退两步
      },
      //行拖拽
      rowDrop() {
        const tbody = document.querySelectorAll('.el-table__body-wrapper tbody')
        const _this = this
        new Sortable(tbody[0], {
          group: 'shared',
          onEnd:function(evt) {
            if(tbody[0]===evt.from&&tbody[0]===evt.to){
              const currRow = _this.leftTable.tableData.splice(evt.oldIndex, 1)[0]
              _this.leftTable.tableData.splice(evt.newIndex, 0, currRow)
              console.log(evt.from)
            }
            if(tbody[0]===evt.from&&tbody[1]===evt.to){
              const currRow = _this.leftTable.tableData.splice(evt.oldIndex, 1)[0]
              _this.createPaperOrderMock.tableData.splice(evt.newIndex, 0, currRow)
              console.log(evt.from)
            }
            _this.loadDataToLeftTable(_this.curPage);

          },
        });
          new Sortable(tbody[1], {
            group: 'shared',
            onEnd:function(evt) {
              if(tbody[1]===evt.from&&tbody[1]===evt.to){
                const currRow = _this.createPaperOrderMock.tableData.splice(evt.oldIndex, 1)[0]
                _this.createPaperOrderMock.tableData.splice(evt.newIndex, 0, currRow)
              }
              if(tbody[1]===evt.from&&tbody[0]===evt.to){
                const currRow = _this.createPaperOrderMock.tableData.splice(evt.oldIndex, 1)[0]
                _this.leftTable.tableData.splice(evt.newIndex, 0, currRow)
              }
              //重新加载左边题目列表
              _this.loadDataToLeftTable(_this.curPage);
            },
          })
        }

      },
      handleSelect(){
          //todo
      },
      querySearchAsync(){
        //todo
      },
    checkInRight,
    }
</script>

<style scoped>

</style>
