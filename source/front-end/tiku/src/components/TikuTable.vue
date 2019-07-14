<template>
  <el-container style="border: 1px solid #eee">
    <el-main>
      <el-row gutter="0">
        <el-col span=20>
          <el-button class="el-button" align="left" plain @click="jumpInput">录入题目</el-button>
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
            width="100">
          </el-table-column>
          <el-table-column
            prop="pictures"
            label="多图片"
            width="100">
          </el-table-column>
          <el-table-column label="选项">
            <el-table-column v-for="f in fieldInfo" v-bind:key="f" :prop="f.keyname" :label="f.title">
            </el-table-column>
          </el-table-column>
          <el-table-column
            prop="tag"
            label="标签"
            width="220"
            :filters="[{ text: '一年级', value: '一年级' }, { text: '英语', value: '英语' }]"
            :filter-method="filterTag"
            filter-placement="bottom-end">
            <template slot-scope="scope">
              <el-tag v-for="(tagsrc,index) in scope.row.tag" v-bind:key="index"
                      :type="scope.row.tag === '英语' ? 'primary' : 'success'"
                      disable-transitions>{{tagsrc}}</el-tag>
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
                @click="centerDialogVisible = true" v-on:click="showTags(scope.row,scope.column, scope.$index)">修改标签</el-button>
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
        :total="1000"

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
  import { getTagsList } from "../api/Tag";
  import {AllFieldInfo} from "../data/mock/FiledInfoMock";
  export default {
    name: 'TikuTable',
    datas:[],
    methods: {
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
      handleClose(tag) {//标签上的叉
        this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
      },
      showInput() {//添加新标签的框
        this.inputVisible = true;
        this.$nextTick(_ => {
          this.$refs.saveTagInput.$refs.input.focus();
        });
      },
      handleInputConfirm() {//添加完标签之后的确定
        let inputValue = this.inputValue;
        if (inputValue) {
          this.dynamicTags.push(inputValue);
        }
        this.inputVisible = false;
        this.inputValue = '';
      },
      /**
       * tag标签接口测试: 可以在 console 查看是否有tag输出
       */
      getTagsdata: function() {
        let callback = tag => {
          console.log("tag");
          console.log(tag);
        };
        getTagsList(callback);
      },
      /** */
      handlerchange:function(currentPage){//获取题目
        this.getData(currentPage);
      },getData:function (currentPage){
        console.log("change")
        var _this=this;
        let callback=(pd)=>{
          var res=[];
          console.log("get it");
          console.log(pd);
          this.$store.commit("setNewProblems",pd);
          pd.filter(v=>{
            let ts = [];
            if(v.tags!==null){
              for(let i =0; i<v.tags.length; i++){
                ts.push(v.tags[i].value)
              }
            }
            if(v.answer===null){
              v.answer={
                answerText:""
              }
            }
            let ress={
              problem:v.problem.problemText,
              answer:v.answer.answerText,
              pictures:'',
              sound:'',
              tag:ts
            };
            if(v.extData!==null){
              Object.keys(v.extData).forEach((key)=>{
                ress[key]=v.extData[key];
              })
            }
            res.push(ress)
          });
          console.log(res);
          _this.tableData=res;
        };
        getProblems(currentPage,callback);
      },
      showTags:function(row,col,index){//在修改标签窗口显示已有标签
        let dynamicTags_tmp=[];
        for(let i=0;i<this.$store.state.allProblem[0].tags.length;i++){
          dynamicTags_tmp.push(this.$store.state.allProblem[index].tags[i].value);//获取store的标签
        }
        this.dynamicTags=dynamicTags_tmp;
      }      
    },
    mounted: function() {
      this.getTagsdata;
      this.getData(0);
      var all=[];
      Object.keys(AllFieldInfo).forEach((key)=>{
        all.push({
          keyname:key,
          title:AllFieldInfo[key].title,
        })
      });

      this.fieldInfo=all;
    },
    data () {
      return {
        search: '',
        dynamicTags: [],
        inputVisible: false,
        inputValue: '',
        centerDialogVisible: false,
        tableData:[],
        fieldInfo:[],
      }
    },
    // 搜索操作
    computed:{
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
