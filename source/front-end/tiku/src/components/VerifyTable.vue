<template>
    <el-container>
      <el-main>
        <el-row>
          <el-col span="24" align="right">
            <el-autocomplete
              v-model="state"
              :fetch-suggestions="querySearchAsync"
              placeholder="请输入内容"
              @select="handleSelect"
            ></el-autocomplete>
          </el-col>
        </el-row>
        <div style="margin: 20px 0;"></div>
        <el-row>
          <el-table
            :data="tableData"
            border
            stripe="true"
            style="width: 100%"
            @selection-change="handleSelectionChange">
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
                  @click="handleEdit(scope.$index, scope.row)">查看</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-row>
      </el-main>
      <el-footer align="center">
        <el-pagination
          background
          layout="prev, pager, next"
          :total="100">
        </el-pagination>
      </el-footer>
    </el-container>
</template>

<script>
    export default {
        name: "VerifyTable",
      methods: {
        handleEdit(index, row) {
          console.log(index, row)
          this.$router.push({path: '/ViewProblem'})
        }
      },
      data () {
        return {
          tableData: [{
            problem: 'How are you?',
            answer: 'I am fine. Thank you',
            pictures: '',
            sound: 'VOA.mp3',
            tag: '英语',
            status: '已通过'
          }, {
            problem: 'How are you?',
            answer: 'I am fine. Thank you',
            pictures: '',
            sound: 'VOA.mp3',
            tag: '英语',
            status: '已通过'
          }, {
            problem: 'How are you?',
            answer: 'I am fine. Thank you',
            pictures: '',
            sound: 'VOA.mp3',
            tag: '英语',
            status: '已通过'
          }, {
            problem: 'How are you?',
            answer: 'I am fine. Thank you',
            pictures: '',
            sound: 'VOA.mp3',
            tag: '英语',
            status: '已通过'
          }, {
            problem: 'How are you?',
            answer: 'I am fine. Thank you',
            pictures: '',
            sound: 'VOA.mp3',
            tag: '英语',
            status: '已通过'
          }, {
            problem: 'How are you?',
            answer: 'I am fine. Thank you',
            pictures: '',
            sound: 'VOA.mp3',
            tag: '英语',
            status: '已通过'
          }, {
            problem: 'How are you?',
            answer: 'I am fine. Thank you',
            pictures: '',
            sound: 'VOA.mp3',
            tag: '英语',
            status: '已通过'
          }, {
            problem: 'How are you?',
            answer: 'I am fine. Thank you',
            pictures: '',
            sound: 'VOA.mp3',
            tag: '英语',
            status: '已通过'
          }]
        }
      }
    }
</script>

<style scoped>

</style>
