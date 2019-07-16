<template>
  <el-table :data="tableInfo.tableData"
            row-key="id"
            class="newTable"
            border
            fit=true
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
        <el-table-column v-for="(f,index) in tableInfo.fieldInfo" v-bind:key="index" :prop="f.keyname" :label="f.title">
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
                  disable-transitions>{{tagsrc}}</el-tag>
        </template>
      </el-table-column>
    </el-table-column>

    <el-table-column label="操作" :width="tableInfo.operWidth" fixed="right">
      <template slot-scope="scope">
        <el-button v-for="(oper,index) in tableInfo.oper"
                   :key="`oper_${index}`"
                   :type="oper.type"
                   :size="oper.size"
                   v-on:click="handleButton(oper.method,scope.$index, scope.row,scope.column); oper.method==='showTags'?centerDialogVisible=true:centerDialogVisible=false">
          {{oper.label}}</el-button>
        <el-dialog
          title="修改标签"
          :visible.sync="centerDialogVisible"
          width="30%"
          center
          :append-to-body="true">
          <el-tag
            v-for="(tagsrc,index) in scope.row.tag"
            v-bind:key="index"
            closable
            :disable-transitions="false"
            @close="handleClose(tag)"
            style="margin-right: 10px; margin-bottom: 10px">
            {{tagsrc}}
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
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  import  GeneralTable from  "../data/model/GeneralTable"
    export default {
        name: "GeneralTable",
      props: {
        tableInfo:GeneralTable
      },
      data(){
        return{
          centerDialogVisible: false
        }
      },
      methods: {
        handleButton(method,index,row,col){
          this.$emit('handleButton',{'method':method,'index':index,'row':row,'col':col})
        },
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
        }
      }
    }
</script>

<style scoped>

</style>
