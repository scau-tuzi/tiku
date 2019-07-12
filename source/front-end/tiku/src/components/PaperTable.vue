<template>
  <el-container>
    <el-main>
      <el-row>
        <el-col span=3>
          <el-button class="el-button" align="left" plain @click="jumpInput">完成</el-button>
          <el-button type="primary" plain>全选</el-button>
        </el-col>
        <el-col span=17>
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="" style="margin-right: 10px">
            <el-form-item label="" prop="name">
              <el-input v-model="ruleForm.name" placeholder="请输入试卷名称"></el-input>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col span=4>
          <el-autocomplete
            v-model="state"
            :fetch-suggestions="querySearchAsync"
            placeholder="搜索"
            @select="handleSelect"
          ></el-autocomplete>
        </el-col>
      </el-row>
      <el-row>
        <el-table
          :data="tableData"
          row-key="id"
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
  </el-container>
</template>

<script>
  import Sortable from 'sortablejs';
  export default {
        name: "PaperTable",
      data(){
          return{
            tableData: [{
              id: '1',
              problem: '2016-05-02',
              answer: '王小虎',
              sound: '上海市普陀区金沙江路 1518 弄',
              tag: '一年级'
            },{
              id: '2',
              problem: '2016-05-02',
              answer: '王小虎',
              sound: '上海市普陀区金沙江路 1518 弄',
              tag: '英语'
            }],
            ruleForm: {
              name: ''
            },
            rules: {
              name: [
                {required: true, message: '请输入试卷名称', trigger: 'blur'}
              ]
            }
          }
      },
    mounted() {
      this.rowDrop()
    },
    methods: {
      //行拖拽
      rowDrop() {
        const tbody = document.querySelector('.el-table__body-wrapper tbody')
        const _this = this
        Sortable.create(tbody, {
          onEnd({ newIndex, oldIndex }) {
            const currRow = _this.tableData.splice(oldIndex, 1)[0]
            _this.tableData.splice(newIndex, 0, currRow)
          }
        })
      }

    }
    }
</script>

<style scoped>

</style>
