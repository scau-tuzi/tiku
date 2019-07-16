<template>
  <div class="about">
    <PaperTable></PaperTable>
    <h1>This is an about page</h1>
  </div>
</template>

<script>
  import TikuTable from "../components/TikuTable";
  import InputTiku from "../components/InputTiku";
  import VerifyTable from "../components/VerifyTable";
  import ViewProblem from "../components/ViewProblem";
  import ModifyProblem from "../components/ModifyProblem";
  import PaperTable from "../components/CreatePaper";
  export default {
    components: {PaperTable, ModifyProblem, ViewProblem, VerifyTable, InputTiku, TikuTable}
  }
</script>



<style>

</style>
