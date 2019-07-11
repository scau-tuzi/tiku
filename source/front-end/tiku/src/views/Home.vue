<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/>
    <button @click=click1>测试</button>
    <div>
      <ul>
<!--        todo 3.显示层，从状态拿数据显示-->
        <li v-for="commit in this.$store.state.commits" v-bind:key="commit.sha">
          {{commit.author.login}} + {{commit.commit.message}}+
          <br/>{{commit.sha}}
        </li>
      </ul>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
import {getAllCommit} from "../api/Commit";


@Component({
  components: {
    HelloWorld,
  },
  methods:{
    // todo 2.事件层，调用网络接口，获取对象，进行处理
    // 然后加到状态变量

    click1: function(e){
      console.log("按钮被按下");
      //数据处理函数

      let getCommitData= (list:CommitInfo[])=> {
        console.log("事件层拿到数据了");
        //！！设置状态
        this.$store.commit("setNewCommits",list)
      };
      //调用网络层
      getAllCommit(getCommitData.bind(this));
    }
  }
})
export default class Home extends Vue {}
</script>
