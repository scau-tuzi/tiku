<template>
  <el-container style="border: 1px solid #eee">
    <el-main>
      <el-row gutter="0">
        <el-col span="20">
          <el-button class="el-button" align="left" plain @click="jumpInput">录入题目</el-button>
          <el-button type="primary" plain>全选</el-button>
          <el-button type="success" plain>批量删除</el-button>
          <el-button type="info" plain>导入Excel</el-button>
          <el-button type="warning" plain>标签批量修改</el-button>
        </el-col>
        <el-col span="4">
          <el-autocomplete
            v-model="state"
            :fetch-suggestions="querySearchAsync"
            placeholder="请输入内容"
            @select="handleSelect"
          ></el-autocomplete>
        </el-col>
      </el-row>
      <el-row><el-col span="24"><div></div></el-col></el-row>
      <el-row>
        <el-table
          :data="tableData"
          border
          stripe="true"
          style="width: 100%"
          @selection-change="handleSelectionChange">
          <el-table-column
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            fixed="left"
            prop="date"
            label="问题"
            width="300">
          </el-table-column>
          <el-table-column
            prop="name"
            label="答案"
            width="300">
          </el-table-column>
          <el-table-column
            prop="province"
            label="语音"
            width="200">
          </el-table-column>
          <el-table-column
            prop="city"
            label="多图片"
            width="220">
          </el-table-column>
          <el-table-column
            prop="tag"
            label="标签"
            width="100"
            :filters="[{ text: '家', value: '家' }, { text: '公司', value: '公司' }]"
            :filter-method="filterTag"
            filter-placement="bottom-end">
            <template slot-scope="scope">
              <el-tag
                :type="scope.row.tag === '家' ? 'primary' : 'success'"
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
                @click="open">修改标签</el-button>
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
        :total="100">
      </el-pagination>
    </el-footer>
  </el-container>
</template>

<script>
  export default {
    name: 'TikuTable',
    methods: {
      handleEdit (index, row) {
        console.log(index, row),
        this.$router.push({path: '/ModifyProblem'})
      },
      handleDelete (index, row) {
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
      open(){
        this.$confirm('修改标签', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true
        }).then(() => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消修改'
          });
        });
      }
    },
    data () {
      return {
        tableData: [{
          date: '2016-05-02',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          tag: '家'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          tag: '家'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          tag: '家'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          tag: '家'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          tag: '家'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          tag: '公司'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          tag: '公司'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          tag: '公司'
        }]
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
