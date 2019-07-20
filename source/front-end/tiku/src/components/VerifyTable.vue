<template>
  <div>
    <el-container>
      <el-main>
        <!-- 搜索框 -->
        <el-input v-model="search" style="display: inline-block;width: 300px" placeholder="请输入搜索内容"></el-input>
        <div style="margin: 20px 0;"></div>
<!--        <el-row>-->
<!--          <el-table-->
<!--            :data="tables"-->
<!--            border-->
<!--            stripe="true"-->
<!--            style="width: 100%"-->
<!--            @selection-change="handleSelectionChange"-->
<!--          >-->
<!--            <el-table-column fixed="left" prop="problemId" label="问题id" width="0" hidden></el-table-column>-->
<!--            <el-table-column fixed="left" prop="problem" label="问题" width="300"></el-table-column>-->
<!--            <el-table-column prop="answer" label="答案" width="300"></el-table-column>-->
<!--            <el-table-column prop="sound" label="语音" width="150"></el-table-column>-->
<!--            <el-table-column prop="pictures" label="多图片" width="150"></el-table-column>-->
<!--            <el-table-column-->
<!--              prop="tag"-->
<!--              label="标签"-->
<!--              width="220"-->
<!--              :filter-method="filterTag"-->
<!--              filter-placement="bottom-end"-->
<!--            >-->
<!--              <template slot-scope="scope">-->
<!--                <el-tag-->
<!--                  v-for="(tagsrc,index) in scope.row.tag"-->
<!--                  v-bind:key="index"-->
<!--                  disable-transitions-->
<!--                >{{tagsrc}}</el-tag>-->
<!--              </template>-->
<!--            </el-table-column>-->
<!--            <el-table-column prop="status" label="审核状态" width="220"></el-table-column>-->
<!--            <el-table-column fixed="right" label="操作" width="150">-->
<!--              <template slot-scope="scope">-->
<!--                <el-button size="mini" type="text" @click="handleView(scope.$index)">查看</el-button>-->
<!--                <el-button size="mini" type="text" @click="handleVerify(scope.$index)">审核</el-button>-->
<!--              </template>-->
<!--            </el-table-column>-->
<!--          </el-table>-->
<!--        </el-row>-->
                <el-row>
                  <GeneralTable v-bind:table-info="verifyTableInfo" v-on:handleButton="handleButton"></GeneralTable>
                </el-row>
      </el-main>
      <el-footer align="center">
        <el-pagination
          background
          layout="prev, pager, next"
          :total="this.listSzie"
          @current-change="this.handlerchange"
        ></el-pagination>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
  import {changeProblem, getProblems} from "../api/Problem";
import ProblemFullData from "../data/model/ProblemFullData";
import GeneralTable from "./GeneralTable";
import verifyTableInfo from "../data/mock/VerifyTableInfoMock";
export default {
  name: "VerifyTable",
  components: { GeneralTable },
  datas: [],
  methods: {
    handleButton(val) {
      if (val.method === "handleView") {
        this.handleView(val.index, val.row);
      }else {
        this.handleVerify(val.index,val.row)
      }
    },
    //查看操作
    handleView(index) {
      // console.log(index, row),
      // alert(index+row.problem+row.answer),
      //转到ViewProblem页面
      this.$router.push({
        path: "/ViewProblem",
        //query对象获取参数
        query: {
          viewIndex: index
        }
      });
    },
    //审核操作
    handleVerify(index,row){
      console.log(row.status);
      console.log(verifyTableInfo.tableData[index].status)
      this.$store.state.allProblem[index].status.verifyStatus=1;
      changeProblem(this.$store.state.allProblem[index],(b)=>{
        if (b.code === "ok") {
          alert("审核成功");
          _this.getPaperData(0);
        }else {
          alert("审核失败"+b.data)
        }
      })
    },
    handlerchange: function(currentPage) {
      //获取题目
      this.getData(currentPage - 1);
    },
    getData: function(currentPage) {
      console.log("change");
      var _this = this;
      let callback = (pd, size) => {
        this.listSzie = size * 10;
        var res = [];
        console.log(currentPage);
        console.log("get it");
        console.log(pd);
        _this.$store.commit("setNewProblems", pd);
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
            problemId: v.problem.id,
            problem: v.problem.problemText,
            answer: v.answer.answerText,
            pictures: "",
            sound: "",
            status: !v.status.verifyStatus ? "未通过" : "通过",
            tag: ts
          };
          res.push(ress);
        });
        //console.log(res);
        verifyTableInfo.tableData = res;
      };
      getProblems(currentPage, callback, 0);
    }
  },
  mounted: function() {
    this.getData(0);
  },
  data: function() {
    return {
      listSzie: 0,
      verifyTableInfo,
      search: "",
      tableData: []
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
</style>
