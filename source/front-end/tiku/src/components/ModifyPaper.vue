<template>
  <el-container>
    <el-header height="30px">
      <el-page-header @back="goBack" content="编辑试卷">
      </el-page-header>
    </el-header>
    <el-main>
      <el-row>
        <el-col :span=3>
          <el-button align="left" plain @click="complete">完成</el-button>
          <!-- <el-button type="primary" plain>全选</el-button> -->
        </el-col>
        <el-col :span=10>
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="" style="margin-right: 10px">
            <el-form-item label="" prop="name">
              <el-input v-model="ruleForm.name" placeholder="请输入试卷名称"></el-input>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span=6>
          <el-select
            v-model="modifyPaperTag"
            multiple
            filterable
            allow-create
            default-first-option
            placeholder="请选择标签"
          >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-col>
        <el-col :span=5>
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
          <GeneralTable usePagination :handleChange="leftTablePageChange" :listSize="listSize"
                        v-bind:table-info="leftTable"  v-on:handleButton="handleButtonLeft" ></GeneralTable>
        </el-col>
        <el-col :span=11>
          <GeneralTable :listSize="listSize" v-bind:table-info="modifyPaperOrderMock"  v-on:handleButton="handleButton"></GeneralTable>
        </el-col>
      </el-row>
      <el-row>
        <pre style="text-align: left">
      {{leftTable.tableData}}
       </pre>
        <hr>
        <pre style="text-align: left">
      {{modifyPaperOrderMock.tableData}}
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
  import {createPaperInfoMock, createPaperOrderMock,modifyPaperOrderMock} from "../data/mock/CreatePaperInfoMock";
  import {getProblems} from "../api/Problem";
  import {addPaper} from "../api/Paper";
  import {getTagsList} from "../api/Tag";

  /**
   * 隐藏右边出现过的题目，检查有没有出现过
   * @param leftId
   */
  function checkInRight(leftProblemId) {
    let t = this.rightTable.tableData;
    if (t !== null && t.length > 0) {
      for (let i = 0; i < t.length; i++) {
        if (leftProblemId === t[i].id) {
          return false;
        }
      }
    }
    return true;
  }
  function getTags() {
    console.log("getTag!");
    var _this = this;
    let callback = (pd, size) => {
      _this.options=[]
      //this.$store.commit("setNewCommit", pd);
      pd.filter(v => {
        let ress = {
          value: v.value,
          label: v.value
        };
        _this.options.push(ress);
        // console.log(_this.options);
      });
    };
    console.log("bbbb");
    getTagsList(0, callback, 0);

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
        if (!checkInRight.bind(_this).call(_this, v.problem.id)) {
          return;
        }
        let ts = [];
        let ext=[];
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
        if (v.extData!== null) {
          for(let j=0;j<Object.keys(v.extData).length;j++){
            ext.push(Object.keys(v.extData)[j]+":"+Object.values(v.extData)[j])
          }
        }
        let ress = {
          id: v.problem.id,
          problem: v.problem.problemText,
          answer: v.answer.answerText,
          tag: ts,
          choice: ext
        };
        res.push(ress);
      });
      console.log(res);
      _this.leftTable.tableData = res;
      _this.listSize = size;

    }; //callback
    getProblems(page, callback, 1);
  } //function
  export default {
    name: "CreatePaper",
    components: {GeneralTable},
    // props:{
    //   modifyPaperTag:Array
    // },
    data() {
      return {
        state: "",
        isNewPaper: false,
        options: [],
        modifyPaperTag: [],
        tikuTableInfo,
        leftTable: createPaperInfoMock,
        createPaperOrderMock,
        modifyPaperOrderMock,
        rightTable:modifyPaperOrderMock,
        listSize: 100,
        curPage: 0,
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
      this.getTags();
      this.loadDataToLeftTable(0);
      this.rowDrop();
      console.log("editP")
      this.editPaper();
      console.log(this.isNewPaper);
    },
    methods: {
      getTags,
      //返回
      goBack(){
        this.$router.push({path: "/PaperList"})
      },
      handleSelect() {
        //todo
      },
      querySearchAsync() {
        //todo
      },
      handleView(index,row){
        // console.log(index, row),
        // alert(index+row.problem+row.answer),
        //转到ViewProblem页面
        console.log(modifyPaperOrderMock.tableData[index]);
        this.$router.push({
          path: "/ViewPaper",
          //query对象获取参数
          query: {
            isViewOnePro: true,
            isViewPaper:false,
            problem: modifyPaperOrderMock.tableData[index]
          }
        });
      },
      handleViewLeft(index,row){
        this.$router.push({
          path: "/ViewPaper",
          //query对象获取参数
          query: {
            isViewOnePro: true,
            isViewPaper:false,
            problem: this.leftTable.tableData[index]
          }
        });
      },
      handleDelete(i,r){
        console.log("view")
        console.log(i,r)
      },
      checkInRight,
      loadDataToLeftTable,
      leftTablePageChange(newPage) {
        this.curPage = newPage - 1;
        this.loadDataToLeftTable(newPage - 1);
      },
      handleButton(val){
        if(val.method==='handleView'){
          this.handleView(val.index,val.row)
        }else if(val.method==='handleDelete'){
          this.handleDelete(val.row,val.col,val.index)
        }
      },
      handleButtonLeft(val){
        if(val.method==='handleView'){
          this.handleViewLeft(val.index,val.row);
        }else if(val.method==='handleDelete'){
          this.handleDelete(val.row,val.col,val.index)
        }
      },
      editPaper() {
        //createPaperOrderMock.tableData=this.$store.state.paperEditData.problems;
        //let s=this.$store.state.paperEditData;
        let p = this.$store.state.paperEditData.problems;
        console.log("p");
        console.log(this.$store.state.paperEditData);
        this.isNewPaper=false
        let res = [];
        for (let i = 0; i < this.$store.state.paperEditData.problems.length; i++) {
          let ts = [];
          let ext=[];
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
          if (p[i].extData!== null) {
            for(let j=0;j<Object.keys(p[i].extData).length;j++){
              ext.push(Object.keys(p[i].extData)[j]+":"+Object.values(p[i].extData)[j])
            }
          }
          let ress = {
            id: p[i].problem.id,
            problem: p[i].problem.problemText,
            answer: p[i].answer.answerText,
            tag: ts,
            choice: ext
          };
          res.push(ress);
        }
        modifyPaperOrderMock.tableData = res;
      },
      complete() {
        //todo 修改数据
        console.log(this);
        let modifyPaperTag = this.modifyPaperTag;
        //拼装 paperfulldata 对象
        let pfd={
          paper: {
            title: this.ruleForm.name,
          },
          //todo tags?: TagInfo[],
          tags:[],
          serialProblemIdMap: {}
        };
        //装载serialProblemIdMap 数
        for (let i=0;i<modifyPaperTag.length;i++){
          pfd.tags.push({value:modifyPaperTag[i]})
        }
        let tableData = this.createPaperOrderMock.tableData;

        if(tableData.length===0){
          alert("问题数为0");
          return ; // todo
        }
        for (let i = 0; i <tableData.length; i++) {
          pfd.serialProblemIdMap[i]=tableData[i].id;
        }
        // ok 了 发送
        if(this.isNewPaper){
          addPaper(pfd,(b)=>{
            if(b.code==="ok"){
              alert("提交成功");
              this.$router.push({path: '/PaperList'})
            }else{
              alert("提交失败"+b.data);
            }
          })
        }else {
          // todo
        }


      },
      //行拖拽
      rowDrop() {
        const tbody = document.querySelectorAll('.el-table__body-wrapper tbody');
        const _this = this
        new Sortable(tbody[0], {
          group: 'shared',
          onEnd: function (evt) {
            if (tbody[0] === evt.from && tbody[0] === evt.to) {
              const currRow = _this.leftTable.tableData.splice(evt.oldIndex, 1)[0];
              _this.leftTable.tableData.splice(evt.newIndex, 0, currRow);
            }
            if (tbody[0] === evt.from && tbody[1] === evt.to) {
              const currRow = _this.leftTable.tableData.splice(evt.oldIndex, 1)[0];
              _this.modifyPaperOrderMock.tableData.splice(evt.newIndex, 0, currRow);
            }
            _this.loadDataToLeftTable(_this.curPage);

          },
        });
        new Sortable(tbody[1], {
          group: 'shared',
          onEnd: function (evt) {
            if (tbody[1] === evt.from && tbody[1] === evt.to) {
              const currRow = _this.modifyPaperOrderMock.tableData.splice(evt.oldIndex, 1)[0];
              _this.modifyPaperOrderMock.tableData.splice(evt.newIndex, 0, currRow);
            }
            if (tbody[1] === evt.from && tbody[0] === evt.to) {
              const currRow = _this.modifyPaperOrderMock.tableData.splice(evt.oldIndex, 1)[0];
              _this.leftTable.tableData.splice(evt.newIndex, 0, currRow);
            }
            //重新加载左边题目列表
            _this.loadDataToLeftTable(_this.curPage);
          },
        })
      }

    },

  }
</script>

<style scoped>

</style>
