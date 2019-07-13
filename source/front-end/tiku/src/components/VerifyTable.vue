<template>
  <div>
    <el-container>
      <el-main>
        <!-- 搜索框 -->
        <el-input v-model="search" style="display: inline-block;width: 300px"
                  placeholder="请输入搜索内容">
        </el-input>
        <div style="margin: 20px 0;"></div>
        <el-row>
          <el-table
            :data="tables"
            border
            stripe="true"
            style="width: 100%"
            @selection-change="handleSelectionChange">
            <el-table-column
              fixed="left"
              prop="problemId"
              label="问题id"
              width="0"
            hidden>
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
              width="150">
            </el-table-column>
            <el-table-column
              prop="pictures"
              label="多图片"
              width="150">
            </el-table-column>
            <el-table-column
              prop="status"
              label="审核状态"
              width="220">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="150"
            >
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="text"
                  @click="handleView(scope.$index, scope.row)">查看</el-button>
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
    </el-container>
  </div>
</template>

<script>
  import {getProblems} from "../api/Problem";
  import ProblemFullData from "../data/model/ProblemFullData";
  export default {
    name: "VerifyTable",
    datas:[],
    methods: {
      //查看操作
      handleView(index, row) {
        console.log(index, row),
          // alert(index+row.problem+row.answer),
          //转到ViewProblem页面
          this.$router.push({path: '/ViewProblem',
            //query对象获取参数
            query: {
              problemId:row.problemId
            }
          })
      },
      handlerchange:function(currentPage){//获取题目
        this.getData(currentPage);
      },
      getData:function (currentPage){
        console.log("change")
        var _this=this;
        let callback=(pd)=>{
          var res=[];
          console.log("get it")
          console.log(pd)
          pd.filter(v=>{
            res.push({
              problemId:v.problem.id,
              problem:v.problem.problemText,
              answer:v.answer.answerText,
              pictures:'',
              sound:'',
              status:(!v.status?'未通过':'通过')
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
        search: '',
        tableData: [{
          problem: 'How are you?',
          answer: 'I am fine. Thank you',
          pictures: '',
          sound: 'VOA.mp3',
          tag: '英语',
          status: '已通过'
        }, {
          problem: 'How old are you?',
          answer: '12',
          pictures: '',
          sound: 'VOA.mp3',
          tag: '英语',
          status: '已通过'
        }, {
          problem: 'Have a nice day !',
          answer: 'Thank you ! I hope so !',
          pictures: '',
          sound: 'VOA.mp3',
          tag: '英语',
          status: '已通过'
        }]
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
</style>
