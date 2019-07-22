<template>
  <el-pagination
          background
          layout="prev, pager, next, jumper"
          :total="this.listSize"
          :current-page="pageNo"
          @current-change="this.handlechangeMe"
          @size-change="this.handleSizeChange"
  ></el-pagination>
</template>

<script>
  export default {
    name: "HistoryPagination",
    props:{
      listSize:Number,
      handlechange:Function,
      handleSizeChange:Function
    },
    data(){
      return{
        pageNo:0
      }
    },
    methods:{
      handlechangeMe(cur){
        this.pageNo=cur;
        localStorage.setItem(this.getName(), this.pageNo);
        this.handlechange(cur)
      },
      getName(){
        return this.$route.path+'pagination'
      }
    }
    ,
    created() {
      this.pageNo = Number(localStorage.getItem(this.getName())) || 1;
      this.handlechange(this.pageNo);
    },

    beforeUpdate () {
      localStorage.setItem(this.getName(), this.pageNo);
    },

    beforeDestroy () {
      localStorage.setItem(this.getName(), '1');
    },
  }
</script>

<style scoped>

</style>