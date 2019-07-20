<template>
  <el-container style="border: 1px solid #eee">
    <el-main>
      <el-button type="primary" v-on:click="back">返回</el-button>
      <div style="margin: 20px 0;"></div>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>{{title}}</span>
        </div>
        <div v-for="(item,index) in datas" :key="index" class="text item" style="margin-bottom: 20px">
          {{index+1+"、"+item.problem+"（答案）"+item.answer}}
            <div style="margin: 20px 0;"></div>
          <el-checkbox v-for="(choice,index) in item.choice" :key="index">
            {{choice}}
          </el-checkbox>
<!--          <div>-->
<!--            <el-checkbox v-for="f in AllFieldInfo">-->
<!--              {{f.title+":"+item["choice_A"]}}-->
<!--            </el-checkbox>-->
<!--          </div>-->
        </div>
      </el-card>
    </el-main>
  </el-container>

</template>

<script>
  import {AllFieldInfo} from "@/data/mock/FiledInfoMock"
    export default {
        name: "ViewPaper",
      mounted(){
          this.viewPaper()
      },
      data(){
          return{
            AllFieldInfo,
            title:"",
            datas: []
          }
      },
      methods: {
        back() {
          this.$router.push({path: "/PaperList"})
        },
        viewPaper() {
          let s = this.$store.state.paperEditData;
          let p = this.$store.state.paperEditData.problems;
          this.title = s.title;
          let res = [];
          for (let i = 0; i < p.length; i++) {
            let ext = [];
            if (p[i].extData) {
              for(let j=0;j<Object.keys(p[i].extData).length;j++){
                ext.push(Object.keys(p[i].extData)[j]+":"+Object.values(p[i].extData)[j])
              }
            }
            let ress = {
              problem: p[i].problem.problemText,
              answer: p[i].answer.answerText,
              choice: ext
            };
            // if (p[i].extData !== undefined) {
            //   Object.keys(p[i].extData).forEach(key => {
            //     ress[key] = p[i].extData[key];
            //   });
              res.push(ress);
            }
            this.datas = res;
            console.log(this.datas)
            //console.log(ext)
          }
        }

  }
</script>

<style scoped>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 1000px;
  }

</style>
