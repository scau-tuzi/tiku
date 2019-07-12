<template>
  <el-container style="border: 1px solid #eee">
    <el-main>
      <el-row gutter="0">
        <el-col span="20">
          <el-button class="el-button" align="left" plain @click="jumpInput">录入题目</el-button>
          <el-button type="primary" plain>全选</el-button>
          <el-button type="success" plain>批量删除</el-button>
          <el-button type="info" plain>导入Excel</el-button>
          <el-button type="warning" plain>标签批量修改</el-button>
        </el-col>
        <el-col span="4">
          <el-autocomplete
            v-model="state"
            :fetch-suggestions="querySearchAsync"
            placeholder="请输入内容"
            @select="handleSelect"
          ></el-autocomplete>
        </el-col>
      </el-row>
      <el-row><el-col span="24"><div></div></el-col></el-row>
      <el-row>
        <el-table
          :data="tableData"
          border
          stripe
          style="width: 100%"
          @selection-change="handleSelectionChange">
          <el-table-column
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            fixed="left"
            prop="problem"
            label="问题"
            width="300">
          </el-table-column>
          <el-table-column
            prop="answer"
            label="答案"
            width="300">
          </el-table-column>
          <el-table-column
            prop="sound"
            label="语音"
            width="200">
          </el-table-column>
          <el-table-column
            prop="pictures"
            label="多图片"
            width="220">
          </el-table-column>
          <el-table-column
            prop="tag"
            label="标签"
            width="100"
            :filters="[{ text: '一年级', value: '一年级' }, { text: '英语', value: '英语' }]"
            :filter-method="filterTag"
            filter-placement="bottom-end">
            <template slot-scope="scope">
              <el-tag
                :type="scope.row.tag === '英语' ? 'primary' : 'success'"
                disable-transitions>{{scope.row.tag}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="250"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button
                size="mini"
                @click="centerDialogVisible = true">修改标签</el-button>
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
        :total="100"

        @current-change="this.handlerchange">
      </el-pagination>
    </el-footer>
    <el-dialog
      title="修改标签"
      :visible.sync="centerDialogVisible"
      width="30%"
      center>
      <el-tag
        :key="tag"
        v-for="tag in dynamicTags"
        closable
        :disable-transitions="false"
        @close="handleClose(tag)"
      style="margin-right: 10px; margin-bottom: 10px">
        {{tag}}
      </el-tag>
      <el-input
        class="input-new-tag"
        v-if="inputVisible"
        v-model="inputValue"
        ref="saveTagInput"
        size="small"
        @keyup.enter.native="handleInputConfirm"
        @blur="handleInputConfirm"
      >
      </el-input>
      <el-button v-else class="button-new-tag" size="small" @click="showInput">+ New Tag</el-button>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
  </span>
    </el-dialog>
  </el-container>
</template>

<script >
  import {getProblems} from "../api/Problem";
  import ProblemFullData from "../data/model/ProblemFullData";

  export default {
    name: 'TikuTable',
    datas:[],

    methods: {
      handleEdit (index, row) {
        console.log(index, row),
          alert(index+row.problem+row.answer),
          this.$router.push({path: '/ModifyProblem',
            query: {
              modifyQues:row.problem,
              modifyAnsw:row.answer
            }
          })
      },
      handleDelete (index, row) {
        this.tableData.splice(index, 1);//删除该行
        this.$message({
          message: "操作成功！",
          type: 'success'
        })
        console.log(index, row)
      },
      handleSelectionChange (val) {
        this.multipleSelection = val
      },
      filterTag(value, row) {
        return row.tag === value;
      },
      jumpInput(){
        //this.$router.push("/cart")
        //传递的参数用{{ $route.query.goodsId }}获取
        this.$router.push({path: '/InputTiku'})
        //this.$router.go(-2)
        //后退两步
      },
      handleClose(tag) {
        this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
      },
      showInput() {
        this.inputVisible = true;
        this.$nextTick(_ => {
          this.$refs.saveTagInput.$refs.input.focus();
        });
      },
      handleInputConfirm() {
        let inputValue = this.inputValue;
        if (inputValue) {
          this.dynamicTags.push(inputValue);
        }
        this.inputVisible = false;
        this.inputValue = '';
      },
      handlerchange:function(currentPage){
       this.getData(currentPage);
      },getData:function (currentPage){
        console.log("change")
        var _this=this;
        let callback=(pd)=>{
          var res=[];
          console.log("get it")
          console.log(pd)
          pd.filter(v=>{
            res.push({
              problem:v.problem.problemText,
              answer:v.answer.answerText,
              pictures:'',
              sound:'',
              tag:v.tags[0].value
            })
          });
          console.log(res);
          _this.tableData=res;
        };
        getProblems(currentPage,callback);
      },

    },
    mounted: function () {
     this.getData(0);
    },
    data () {
      return {
        dynamicTags: ['标签一', '标签二', '标签三'],
        inputVisible: false,
        inputValue: '',
        centerDialogVisible: false,
        tableData: []
      }
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
