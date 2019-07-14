<template>
  <el-container>
    <el-main>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item>
          <el-button type="primary" @click="back" >返回</el-button>
        </el-form-item>
        <el-form-item label="题目" prop="ti">
          <!-- 设置输入框为不可编辑 -->
          <el-input type="textarea" rows="5" v-model="ruleForm.ti" disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="答案" prop="answer">

          <!-- 设置输入框为不可编辑 -->
          <el-input type="textarea" rows="5" v-model="ruleForm.answer" disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="多图片" prop="pics">
            <div style="direction: inherit">
              <div class="picture" style="float: left; margin-right: 10px" v-for="pic in pics" v-bind:key=pic>
                <el-avatar shape="square" fit="fit" :size="100" :src="pic.url"></el-avatar>
              </div>
            </div>
        </el-form-item>
        <el-form-item label="音频" prop="sound">
          <span style="margin-right: 10px">123.mp3</span>
          <el-button type="primary" icon="el-icon-caret-right" size="mini" circle></el-button>
        </el-form-item>
        <el-form-item label="标签" prop="tags">
          <el-tag v-for="(tag,index) in ruleForm.tags" v-bind:key="index">{{tag}}</el-tag>
          <div style="margin: 100px 0;"></div>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
    export default {
        name: "ViewProblem",
      data(){
        return {
          pics:[
            {name:'first',
              url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
            },
            {
              name:'second',
              url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
            }
          ],
          fits: ['fill'],
          url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
          dialogImageUrl: '',
          dialogVisible: false,
          disabled: false,
          ruleForm: {
            ti: '',
            answer: '',
            pics: '',
            sound: '',
            tags: []
          },
          rules: {
            ti: [
              { required: true, message: '请输入题目', trigger: 'blur' }
            ],
            answer: [
              { required: true, message: '请输入题目', trigger: 'blur'}
            ]

          },
          value: []

        };
      },
      // 页面渲染前执行取路由带过来的参数
      created(){
        this.getParams()
      },
      methods: {
        getParams(){
          // 取到路由带过来的参数
          const routerQues = this.$route.query.viewQues
          const routerAnsw = this.$route.query.viewAnsw
          const routerTags = this.$route.query.viewTags
          // 将数据放在当前组件的数据内
          
          this.ruleForm.ti = routerQues
          this.ruleForm.answer = routerAnsw
          this.ruleForm.tags = routerTags
        },
        back(){
          this.$router.push({path: '/VerifyTable'})
        }
      },
      //监听路由变化
      watch: {
        '$route': 'getParams'
      }
    }
</script>

<style scoped>

</style>
