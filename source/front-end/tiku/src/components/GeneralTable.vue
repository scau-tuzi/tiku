<template>
  <div>
    <el-table :data="tableInfo.tableData"
              row-key="id"
              class="newTable"
              border
              fit
              stripe
              align="left"
              style="width: 100%"
              @selection-change="handleSelectionChange">
        <el-table-column v-for="(item, index) in tableInfo.col"
                         :key="`col_${index}`"
                         :label="item.label"
                         :prop="item.prop"
                         :fixed="item.fixed==='left'?'left':null"
                         :type="item.type==='selection'?'selection':null"
                         :width="item.width">
            <template v-if="item.specialType==='duoji'">
                <el-table-column v-for="(f,index) in tableInfo.fieldInfo" v-bind:key="index" :prop="f.keyname"
                                 :label="f.title">
                </el-table-column>
            </template>
            <el-table-column
                    v-else-if="item.specialType==='tag'"
                    prop="tag"
                    :width="item.width"
                    :filters="[{ text: '一年级', value: '一年级' }, { text: '英语', value: '英语' }]"
                    :filter-method="filterTag"
                    filter-placement="bottom-end">
                <template slot-scope="scope">
                    <el-tag v-for="(tagsrc,index) in scope.row.tag" v-bind:key="index"
                            :type="scope.row.tag === '英语' ? 'primary' : 'success'"
                            disable-transitions>{{tagsrc}}
                    </el-tag>
                </template>
            </el-table-column>
        </el-table-column>

        <el-table-column label="操作" :width="tableInfo.operWidth" fixed="right">
            <template slot-scope="scope">
                <el-button v-for="(oper,index) in tableInfo.oper"
                           :key="`oper_${index}`"
                           :type="oper.type"
                           :size="oper.size"
                           v-on:click="handleButton(oper.method,scope.$index, scope.row,scope.column); oper.method==='showTags'?centerDialogVisible_single=true:centerDialogVisible_single=false">
                    {{oper.label}}
                </el-button>
            </template>
        </el-table-column>
      <el-dialog class="modifyTag" title="修改标签" :visible.sync="this.centerDialogVisible_single" width="30%" center append-to-body>
                        <template>
                          <el-select
                            v-model="value"
                            multiple
                            filterable
                            allow-create
                            default-first-option
                            class="kuang"
                            placeholder="请选择标签"
                          >
                            <!-- @change="modifyTag" -->
                            <el-option
                              v-for="item in options"
                              :key="item.value"
                              :label="item.label"
                              :value="item.value"
                            ></el-option>
                          </el-select>
                          <span slot="footer" class="dialog-footer">
                  <el-button @click="centerDialogVisible_single = false">取 消</el-button>
                  <el-button type="primary" @click="centerDialogVisible_single = false;modifyTag()">确 定</el-button>
                </span>
                        </template>
      </el-dialog>
    </el-table>
    <el-footer align="center">
        <el-pagination v-if="usePagination===true"
                background
                layout="prev, pager, next"
                :total="this.listSize"
                @current-change="this.handleChange"
        ></el-pagination>
    </el-footer>
    </div>
</template>

<script>
    import GeneralTable from "../data/model/GeneralTable"
    import {getProblems} from "../api/Problem";
    import {getTagsList} from "../api/Tag";

    function handleButton(method, index, row, col) {
        this.$emit('handleButton', {'method': method, 'index': index, 'row': row, 'col': col})
    }

    //查看操作
    function handleView(index, row) {
        console.log(index, row),
            // alert(index+row.problem+row.answer),
            //转到ViewProblem页面
            this.$router.push({
                path: '/ViewProblem',
                //query对象获取参数
                query: {
                    problemId: row.problemId
                }
            })
    }

    function handleClose(tag) {//标签上的叉
        this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
    }

    function showInput() {//添加新标签的框
        this.inputVisible = true;
        this.$nextTick(_ => {
            this.$refs.saveTagInput.$refs.input.focus();
        });
    }

    function handleInputConfirm() {//添加完标签之后的确定
        let inputValue = this.inputValue;
        if (inputValue) {
            this.dynamicTags.push(inputValue);
        }
        this.inputVisible = false;
        this.inputValue = '';
    }

    function handlerchange(currentPage) {//获取题目
        this.getData(currentPage-1);
    }

    function getData(currentPage) {
        console.log("change")
        var _this = this;
        let callback = (pd) => {
            var res = [];
            console.log("get it")
            console.log(pd)
            this.$store.commit("setNewProblems", pd);
            pd.filter(v => {
                let ts = [];
                if (v.tags !== null) {
                    for (let i = 0; i < v.tags.length; i++) {
                        ts.push(v.tags[i].value)
                    }
                }
                if (v.answer === null) {
                    v.answer = {
                        answerText: ""
                    }
                }
                let ress = {
                    problemId: v.problem.id,
                    problem: v.problem.problemText,
                    answer: v.answer.answerText,
                    pictures: '',
                    sound: '',
                    status: (!v.status ? '未通过' : '通过'),
                    tag: ts
                };
                res.push(ress)
            });
            console.log(res);
            _this.tableData = res;
        };
        getProblems(currentPage, callback);
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
          //console.log(_this.options);
        });
      };
      console.log("bbbb");
      getTagsList(0, callback, 0);

    }
    //修改某一行问题的标签
    function modifyTag() {
      let selectedPaper = this.$store.state.modifyPaper[this.paperId];
      console.log(this.$store.state.modifyPaper);
      console.log("test change1!--");
      console.log(selectedPaper);
      selectedPaper.tags = [];
      this.value.forEach(v => {
        selectedPaper.tags.push({
          value: v
        });
      });
      console.log("test change2!--");
      console.log(selectedPaper);
      // changeProblem(selectedProblem, b => {
      //   if (b.code === "ok") {
      //     alert("修改成功");
      //     // this.$router.go(0); //页面刷新（要加上）
      //   } else {
      //     alert("修改失败" + b.data);
      //   }
      // });
    }
    export default {
        name: "GeneralTable",
        props: {
            tableInfo: GeneralTable,//表格信息
            listSize:Number,        //总页数
            handleChange:Function,  //页修改回调函: b数
            usePagination:Boolean,  //是否使用内置分页器
          centerDialogVisible_single:Boolean,
          options: Array,
          value: Array,
          paperId: Number
        },
        data() {
            return {

                tableData,
              options: []
            }
        },
        methods: {
            handleButton,
            handleView,
            handleClose,
            showInput,
            handleInputConfirm,
            handlerchange,
            getData,
            getTags,
           modifyTag
        },
        mounted: function () {
          this.getTags();
            this.getData(0);
            console.log("666")

        },
    }
</script>

<style scoped>
.modifyTag{
  z-index: 1000;
}
  .kuang{
    width: 400px;
  }
</style>
