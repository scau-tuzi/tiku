<template>
  <el-container>
    <el-main>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
        <el-form-item label="题目" prop="ti">
          <el-input type="textarea":rows="5" v-model="ruleForm.ti"></el-input>
        </el-form-item>
        <el-form-item label="答案" prop="answer">
          <el-input type="textarea":rows="5" v-model="ruleForm.answer"></el-input>
        </el-form-item>
        <el-form-item label="多图片" prop="pics">
          <el-upload
            class="upload-demo"
            drag
            action="https://jsonplaceholder.typicode.com/posts/"
            :before-remove="beforeRemove"
            multiple>
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="音频" prop="sound">
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
            <div slot="tip" class="el-upload__tip">只能上传一个mp3文件，且不超过500kb</div>
          </el-upload>
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
        name: "InputTiku",
      data(){
        return {
          dialogImageUrl: '',
          dialogVisible: false,
          disabled: false,
          ruleForm: {
            ti: '',
            answer: '',
            pics: '',
            sound: '',
            tags: ''
          },
          rules: {
            ti: [
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
      methods: {
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
          return this.$confirm(`确定移除 ${ file.name }？`);
        },
        beforeUpload(file){
          var testmsg = file.name.substring(file.name.lastIndexOf('.')+1)
          const extension = testmsg === 'mp3'
          if(!extension){
            this.$message.warning(`上传文件只能是mp3格式！`);
          }
          return extension;
        }
      }

    }
</script>

<style scoped>

</style>
