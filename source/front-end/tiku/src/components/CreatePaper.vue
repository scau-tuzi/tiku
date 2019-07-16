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
          <draggable
            :list="createPaperInfoMock.tableData"
            :options="dragOptions1">
<!--            :move="rowDrop">-->
            <div>
              <el-col span=12.5 style="margin-right: 10px">
                <GeneralTable v-bind:table-info="createPaperInfoMock"></GeneralTable>
              </el-col>
            </div>
<!--            <div v-model="createPaperOrderMock.tableData" :move="rowDrop">-->
<!--              <el-col span=11>-->
<!--                <GeneralTable v-bind:table-info="createPaperOrderMock"></GeneralTable>-->
<!--              </el-col>-->
<!--            </div>-->
          </draggable>
          <draggable
            :list="createPaperOrderMock.tableData"
            :options="dragOptions2">
<!--            :move="rowDrop">-->
            <el-col span=11>
              <GeneralTable v-bind:table-info="createPaperOrderMock"></GeneralTable>
            </el-col>
          </draggable>
      </el-row>
      <el-row>
        <pre style="text-align: left">
      {{createPaperInfoMock.tableData}}
       </pre>
        <hr>
        <pre style="text-align: left">
      {{createPaperOrderMock.tableData}}
       </pre>
      </el-row>

    </el-main>
  </el-container>
</template>

<script>
  import Sortable from 'sortablejs';
  import draggable from 'vuedraggable';
  import GeneralTable from "./GeneralTable";
  import tikuTableInfo from "../data/mock/TikuTableInfoMock";
  import {createPaperInfoMock, createPaperOrderMock} from "../data/mock/CreatePaperInfoMock";
  export default {
        name: "CreatPaper",
    components: {GeneralTable,draggable,Sortable},
    data(){
          return{
            tikuTableInfo,
            createPaperInfoMock,
            createPaperOrderMock,
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
            },
              {
                id: '3',
                problem: '2016-05',
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
    // mounted() {
    //     this.rowDrop();
    // },
    computed:{
      dragOptions1() {
        return {
          animation: 0,
          group: {
            name: "shared",
          },
          ghostClass: "ghost",
          // draggable: ".el-table__body-wrapper tbody"
        };
      },
      dragOptions2(){
        return {
          animation: 0,
          group: {
            name:"shared",

          },
          // draggable: ".el-table__body-wrapper tbody"
        };
      }
    },
    methods: {
      //行拖拽
      rowDrop() {
        const tbody = document.querySelectorAll('.el-table__body-wrapper tbody')
        const _this = this
        for(var i=0;i<tbody.length;i++){
          Sortable.create(tbody, {
            onEnd({ newIndex, oldIndex }) {
              const currRow = _this.tableData.splice(oldIndex, 1)[0]
              _this.tableData.splice(newIndex, 0, currRow)
            }
          })
        }

      }


    }
    }
</script>

<style scoped>

</style>
