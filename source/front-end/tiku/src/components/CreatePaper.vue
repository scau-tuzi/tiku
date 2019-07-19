<template>
  <el-container>
    <el-main>
      <el-row>
        <el-col span=3>
          <el-button align="left" plain @click="complete">完成</el-button>
          <el-button type="primary" plain>全选</el-button>
        </el-col>
        <el-col span=10>
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="" style="margin-right: 10px">
            <el-form-item label="" prop="name">
              <el-input v-model="ruleForm.name" placeholder="请输入试卷名称"></el-input>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col span=6>
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
        <el-col span=5>
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
                        v-bind:table-info="leftTable"  v-on:handleButton="handleButton" ></GeneralTable>
        </el-col>
        <el-col :span=11>
          <GeneralTable :listSize="listSize" v-bind:table-info="createPaperOrderMock"  v-on:handleButton="handleButton"></GeneralTable>
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
  import {addPaper} from "../api/Paper";
  import {getTagsList} from "../api/Tag";

  /**
   * 隐藏右边出现过的题目，检查有没有出现过
   * @param leftId
   */
  function checkInRight(leftProblemId) {
    let t = this.createPaperOrderMock.tableData;
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
      this.options=[]
      //this.$store.commit("setNewCommit", pd);
      pd.filter(v => {
        let ress = {
          value: v.value,
          label: v.value
        };
        _this.options.push(ress);
        console.log(_this.options);
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
      _this.listSize = size;

    }; //callback
    getProblems(page, callback, 1);
  } //function
  export default {
    name: "CreatePaper",
    components: {GeneralTable},
    data() {
      return {
        isNewPaper: true,
        options: [],
        modifyPaperTag: [],
        tikuTableInfo,
        leftTable: createPaperInfoMock,
        createPaperOrderMock,
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
      this.editPaper();

    },
    methods: {
      getTags,
      handleSelect() {
        //todo
      },
      querySearchAsync() {
        //todo
      },
      handleView(a,b,c){
        console.log("view")
        console.log(a,b,c)
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
      editPaper() {
        //createPaperOrderMock.tableData=this.$store.state.paperEditData.problems;
        let p = this.$store.state.paperEditData.problems;
        if (p === undefined || p.length===0){
          this.isNewPaper=true
          return;
        }
          this.isNewPaper=false
        let res = [];
        for (let i = 0; i < this.$store.state.paperEditData.problems.length; i++) {
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
        createPaperOrderMock.tableData = res;
      },
      complete() {
        //todo 修改数据

        //拼装 paperfulldata 对象
        let pfd={
          paper: {
            title: this.ruleForm.name,
          },
          //todo tags?: TagInfo[],
          //tags: this.modifyPaperTag,
          serialProblemIdMap: {}
        };
        //装载serialProblemIdMap 数据

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
              _this.createPaperOrderMock.tableData.splice(evt.newIndex, 0, currRow);
            }
            _this.loadDataToLeftTable(_this.curPage);

          },
        });
        new Sortable(tbody[1], {
          group: 'shared',
          onEnd: function (evt) {
            if (tbody[1] === evt.from && tbody[1] === evt.to) {
              const currRow = _this.createPaperOrderMock.tableData.splice(evt.oldIndex, 1)[0];
              _this.createPaperOrderMock.tableData.splice(evt.newIndex, 0, currRow);
            }
            if (tbody[1] === evt.from && tbody[0] === evt.to) {
              const currRow = _this.createPaperOrderMock.tableData.splice(evt.oldIndex, 1)[0];
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
