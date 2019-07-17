<template>
  <el-container style="border: 1px solid #eee">
    <el-main>
      <el-row gutter="0">
        <el-col span="20">
          <el-button class="el-button" align="left" plain @click="jumpInput">录入题目</el-button>
          <!-- <el-button type="primary" plain>全选</el-button> -->
          <el-button type="success" plain @click="batchDelete(tableChecked)">批量删除</el-button>
          <el-button type="warning" plain @click="centerDialogVisible_batch = true">标签批量修改</el-button>
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
          <el-table-column fixed="left" prop="problem" label="问题" width="300"></el-table-column>
          <el-table-column prop="answer" label="答案" width="300"></el-table-column>
          <el-table-column prop="sound" label="语音" width="100"></el-table-column>
          <el-table-column prop="pictures" label="多图片" width="100"></el-table-column>
          <el-table-column label="选项">
            <el-table-column
              v-for="f in fieldInfo"
              v-bind:key="f"
              :prop="f.keyname"
              :label="f.title"
            ></el-table-column>
          </el-table-column>
          <el-table-column
            prop="tag"
            label="标签"
            width="220"
            :filter-method="filterTag"
            filter-placement="bottom-end"
          >
            <template slot-scope="scope">
              <el-tag
                v-for="(tagsrc,index) in scope.row.tag"
                v-bind:key="index"
                disable-transitions
              >{{tagsrc}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="250">
            <template slot-scope="scope">
              <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button
                size="mini"
                @click="centerDialogVisible_single = true"
                v-on:click="showTags(scope.$index)"
              >修改标签</el-button>
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
        :total="1000"
        @current-change="this.handlerchange"
      ></el-pagination>
    </el-footer>
    <el-dialog title="修改标签" :visible.sync="centerDialogVisible_single" width="30%" center>
      <template>
        <el-select
            v-model="value"
            multiple
            filterable
            allow-create
            default-first-option
            size="medium"
            placeholder="请选择题目标签">
            <!-- @change="modifyTag" -->
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
        <span slot="footer" class="dialog-footer">
          <el-button @click="centerDialogVisible_single = false">取 消</el-button>
          <el-button type="primary" @click="centerDialogVisible_single = false;modifyTag()">确 定</el-button>
        </span>
      </template>
    </el-dialog>
    <el-dialog title="批量修改标签" :visible.sync="centerDialogVisible_batch" width="30%" center>
      <template>
        <el-select
            v-model="value_batch"
            multiple
            filterable
            allow-create
            default-first-option
            size="medium"
            placeholder="请选择题目标签">
            <!-- @change="modifyTag" -->
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
        <span slot="footer" class="dialog-footer">
          <el-button @click="centerDialogVisible_batch = false">取 消</el-button>
          <el-button type="primary" @click="centerDialogVisible_batch = false;modifyBatchTags()">确 定</el-button>
        </span>
      </template>
    </el-dialog>
  </el-container>
</template>

<script>
import { getProblems, delProblem,changeProblem } from "../api/Problem";
import ProblemFullData from "../data/model/ProblemFullData";
import { getTagsList, addTags, delTag } from "../api/Tag";
import { AllFieldInfo } from "../data/mock/FiledInfoMock";
import { changePaper } from "../api/Paper";

//编辑操作
function handleEdit(index, row) {
  console.log(index, row)
  // alert(index+row.problem+row.answer),
  //转到ModifyProblem页面
  this.$router.push({
    path: "/ModifyProblem",
    //query对象获取问题和答案

    query: {
      modifyIndex:index
    }
  });
}
//删除单行问题
function handleDelete(index) {
      console.log('要删除的下标---');
      console.log(index);
      console.log(this.$store.state.allProblem[index]);
      this.$confirm("确定删除该问题?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
       .then(() => {
      let delId=[];
      delId.push(this.$store.state.allProblem[index].problem.id);//获取要删除的问题id
      delProblem(delId, b => {
            if (b.code === "ok") {
              alert("删除成功");
            }
            // this.$router.go(0); //页面刷新（要加上）
          });
      })
    }
function handleSelectionChange(val) {
  this.tableChecked = val;
}
function batchDelete(rows){
  console.log("batchDelete--", rows);
  let delId = [];
  // console.log("batchDelete--",rows);
  for (var i = 0; i < this.tableData.length; i++) {
    //获取选中项id
    for (var j = 0; j < rows.length; j++) {
      if (this.tableData[i].problem === rows[j].problem) {//获取选中的问题id
        // delIndex.push(i);
        delId.push(this.$store.state.allProblem[i].problem.id);
      }
    }
  }
  // console.log("4--",delId);
  this.$confirm("确定批量删除问题?", "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning"
  })
    .then(() => {
      //alert('submit!');
      delProblem(delId, b => {
        if (b.code === "ok") {
          alert("删除成功");
        }
        // this.$router.go(0); //页面刷新（要加上）
      });
    })
    .catch(() => {
      this.$message({
        type: "info",
        message: "已取消删除"
      });
    });
}
//批量修改标签
function modifyBatchTags(){
  let rows=this.tableChecked;
  let modifyProblems = [];
  // console.log("batchDelete--",rows);
  for (var i = 0; i < this.tableData.length; i++) {
    //获取选中项id
    for (var j = 0; j < rows.length; j++) {
      if (this.tableData[i].problem === rows[j].problem) {//获取选中的问题id
        // delIndex.push(i);
        this.$store.state.allProblem[i].tags=[];
        this.value_batch.forEach((v)=>{
        this.$store.state.allProblem[i].tags.push({
          value:v,
        })
      });
        modifyProblems.push(this.$store.state.allProblem[i]);
      }
    }
  }
  for(var k=0;k<modifyProblems.length;k++){
    changeProblem(modifyProblems[k],(b)=>{
        if(b.code!=="ok"){
          alert("修改失败"+b.data);
        }
      });  
  }
  // this.$router.go(0); //页面刷新（要加上）
  
}
function filterTag(value, row) {
  return row.tag === value;
}
function jumpInput() {
  //this.$router.push("/cart")
  //传递的参数用{{ $route.query.goodsId }}获取
  this.$router.push({ path: "/InputTiku" });
  //this.$router.go(-2)
  //后退两步
}
function handleClose(tag) {
  //标签上的叉
  this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
}
function handleInputConfirm() {
  //添加完标签之后的确定
  let inputValue = this.inputValue;
  if (inputValue) {
    this.dynamicTags.push(inputValue);
  }
  this.inputVisible = false;
  this.inputValue = "";
}
/**
 * problem题目接口测试:测试结果可在 console 观察
 */
function addProblemData() {
  //增加题目方法测试
  let temp = {
    problem: {
      problemText: "李小米的爸爸姓什么?"
    },
    answer: {
      answerText: "李"
    },
    tags: [],
    status: 1
  };
  let callback = p => {};
  addProblem(temp, callback);
}
function findProblemDataByTags() {
  //标签查找题目方法测试
  let temp = [
    {
      value: "幼儿园"
    }
  ];
  let callback = p => {};
  findProbLemsByTags(temp, callback);
}
function findProblemDataByVaguely() {
  //模糊查询题目方法测试
  let temp = "没";
  let callback = p => {};
  findProblemsVaguely(temp, callback);
}
function delProblemData() {
  //删除题目方法测试
  let temp = [1, 2];
  let callback = p => {};
  delProblem(temp, callback);
}
function changeProblemData() {
  //修改题目方法测试
  /**
   * src 为初始原型 ( ProblemFullData 类型),
   */
  let src = {
    problem: {
      id: -1,
      problemText: ""
    },
    answer: {
      answerText: ""
    },
    tags: [],
    extData:{
      A:4,
      B:5,
      C:6,
      D:7
    },
    status: {
    verifyStatus: 1
   }
  };
  src.problem.parentId = 1;
  src.problem.id = 5;
  src.problem.problemText = "2+2=?";
  src.answer.answerText = "4";

  // src.tags.push({ value: "生活" });
  console.log("cs")
  console.log(src);
  let callback = p => {};
  changeProblem(src, callback);
}
/** */

/**
 * tag标签接口测试: 可以在 console 查看是否有tag输出
 */
function getTagsdata(){
  //标签接口_获得标签列表方法本地测试
  let callback = tag => {
    // console.log("get tags data");
    // console.log(tag);
  };
  getTagsList(callback);
}
function addTagsdata() {
  //标签接口_增加标签方法本地测试
  let callback = tag => {};
  let temp = [
    //因为在 js 语言中无类型模式,所以需要根据函数参数类型的具体结构传递参数
    {
      value: "语文",
      parentId: 6
    },
    {
      value: "数学",
      parentId: 7
    },
    {
      value: "英语",
      parentId: 8
    },
    {
      value: "历史"
    },
    {
      value: "化学"
    },
    {
      value: "生物"
    },
    {
      value: "政治"
    },
    {
      value: "地理"
    }
  ];

  addTags(temp, callback);
}
function delTagData() {
  //标签接口_删除标签方法本地测试
  let callback = tag => {};
  let delId = [15, 14];
  delTag(delId, callback);
}
/** */
function handlechange(currentPage) {
  //获取题目
  this.getData(currentPage-1);
}
function getData(currentPage) {
  // console.log("change")
  var _this = this;
  let callback = pd => {
    var res = [];
    // console.log("get it");
    // console.log(pd);
    this.$store.commit("setNewProblems", pd);
    pd.filter(v => {
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
        problem: v.problem.problemText,
        answer: v.answer.answerText,
        pictures: "",
        sound: "",
        tag: ts
      };
      if (v.extData !== undefined) {
        Object.keys(v.extData).forEach(key => {
          ress[key] = v.extData[key];
        });
      }
      res.push(ress);
    });
    console.log(res);
    _this.tableData = res;
  };
  getProblems(currentPage, callback);
}
//在修改标签窗口显示已有标签
function showTags(index) {     
      let value_tmp = [];
      for (let i = 0; i < this.$store.state.allProblem[index].tags.length; i++) {
        value_tmp.push(this.$store.state.allProblem[index].tags[i].value); //获取store的标签
      }
      this.value = value_tmp;
      this.index_tmp=index;
}
//修改某一行问题的标签
function modifyTag(){
      let selectedProblem=this.$store.state.allProblem[this.index_tmp];
      console.log('test change1!--');
      console.log(selectedProblem);
      selectedProblem.tags=[];
      this.value.forEach((v)=>{
        selectedProblem.tags.push({
          value:v,
        })
      });
      console.log('test change2!--');
      console.log(selectedProblem);
      changeProblem(selectedProblem,(b)=>{
        if(b.code==="ok"){
          alert("修改成功");
          // this.$router.go(0); //页面刷新（要加上）
        }else{
          alert("修改失败"+b.data)
        }
      });  
}
//获取全部标签（选择器下拉窗口里用）
function getTags(){
      console.log("getTag!")
      var _this=this;
      let callback=(pd)=>{
      console.log("get it");
      console.log(pd);
      this.$store.commit("setNewCommits",pd);
            // console.log(this.$store.state.commits);
            // console.log(this.$store.state.commits[0].id);
      pd.filter(v=>{
          let ress={
          value:v.value,
          label:v.value
          };
          this.options.push(ress)
      });
      };
      getTagsList(callback);
      console.log(this.options);
}

export default {
  name: "TikuTable",
  datas: [],
  methods: {
    handleEdit,
    handleDelete,
    handleSelectionChange,
    batchDelete,
    modifyBatchTags,
    filterTag,
    jumpInput,
    handleClose,
    handleInputConfirm,
    addProblemData,
    findProblemDataByTags,
    findProblemDataByVaguely,
    delProblemData,
    changeProblemData,
    getTagsdata,
    addTagsdata,
    delTagData,
    handlechange,
    getData,
    showTags,
    getTags,
    modifyTag
  },
  mounted: function() {
    this.getData(0);
    // this.changeProblemData();
    this.getTags();

    let all = [];
    Object.keys(AllFieldInfo).forEach(key => {
      all.push({
        keyname: key,
        title: AllFieldInfo[key].title
      });
    });

    this.fieldInfo = all;
  },
  data() {
    return {
      tableChecked: [], //被选中的记录数据
      search: "",
      inputVisible: false,
      inputValue: "",
      centerDialogVisible_single: false,
      centerDialogVisible_batch: false,
      tableData: [],
      fieldInfo: [],
      options: [],//修改标签窗口选择器下拉的标签列表
      value: [],//修改标签窗口选择器里面的已选标签
      index_tmp:'',//选中修改标签的行index    
      value_batch:[]//批量修改标签窗口选择器里面的已选标签

    };
  },
  // 搜索操作
  computed: {
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
  }
};
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