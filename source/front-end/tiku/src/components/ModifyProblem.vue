<template>
  <el-container>
    <el-main>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">保存</el-button>
          <el-button @click="back">返回</el-button>
        </el-form-item>
        <el-form-item label="题目" prop="ti">
          <el-input type="textarea":rows="5" v-model="ruleForm.problem"></el-input>
        </el-form-item>
        <el-form-item label="答案" prop="answer">
          <el-input type="textarea":rows="5" v-model="ruleForm.answer"></el-input>
        </el-form-item>
        <el-form-item label="多图片" prop="pics">
          <el-upload
            action="https://jsonplaceholder.typicode.com/posts/"
            list-type="picture-card"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove">
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
        <el-form-item label="音频" prop="sound">
          <div style="float: left; margin-right: 10px">
            <el-upload
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :before-remove="beforeRemove"
              multiple
              :limit="1"
              :on-exceed="handleExceed"
              :beforeUpload="beforeUpload"
              :file-list="fileList">
              <el-button size="small" type="primary" style="margin-top: 10px">点击上传</el-button>
            </el-upload>
          </div>
          <div style="float: left; margin-top: 5px">
            <span style="margin-right: 10px; ">123.mp3</span>
            <el-button type="primary" icon="el-icon-caret-right" size="mini" circle></el-button>
          </div>
        </el-form-item>
        <el-form-item label="标签" prop="tags">
          <el-select
            v-model="value"
            multiple
            filterable
            allow-create
            default-first-option
            size="medium"
            placeholder="请选择题目标签">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
          <div style="margin: 100px 0;"></div>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
    export default {
        name: "ModifyProblem",
      data(){
        return {
          dialogImageUrl: '',
          dialogVisible: false,
          disabled: false,
          ruleForm: {
            problem: '1+1',
            answer: '2',
            pics: '',
            sound: '',
            tags: ''
          },
          rules: {
            problem: [
              { required: true, message: '请输入题目', trigger: 'blur' }
            ],
            answer: [
              { required: true, message: '请输入题目', trigger: 'blur'}
            ]

          },
          options: [{
            value: 'FirstGrade',
            label: '一年级'
          }, {
            value: 'SecondGrade',
            label: '二年级'
          }, {
            value: 'ThirdGrade',
            label: '三年级'
          }],
          value: []

        };
      },
      created(){
        this.getParams()
      },
      methods: {
        back(){
          this.$router.push({path: '/TikuTable'})
        },
        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              alert('submit!');
            } else {
              console.log('error submit!!');
              return false;
            }
          });
        },
        resetForm(formName) {
          this.$refs[formName].resetFields();
        },
        handleRemove(file) {
          console.log(file);
        },
        handlePreview(file) {
          console.log(file);
        },
        handleExceed(files, fileList) {
          this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
        },
        beforeRemove(file, fileList) {
          return this.$confirm(`确定移除 ${file.name}？`);
        },
        beforeUpload(file) {
          var testmsg = file.name.substring(file.name.lastIndexOf('.') + 1)
          const extension = testmsg === 'mp3'
          if (!extension) {
            this.$message.warning(`上传文件只能是mp3格式！`);
          }
          return extension;
        },
        getParams(){
          // 取到路由带过来的参数
          const routerQues = this.$route.query.modifyQues
          const routerAnsw = this.$route.query.modifyAnsw
          // 将数据放在当前组件的数据内
          this.ruleForm.problem = routerQues
          this.ruleForm.answer = routerAnsw
        }
      },
      watch: {
        '$route': 'getParams'
      }
    }
</script>

<style scoped>

</style>
