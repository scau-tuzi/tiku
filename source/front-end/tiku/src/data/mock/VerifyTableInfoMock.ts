import GeneralTable from "@/data/model/GeneralTable";

let verifyTableInfo:GeneralTable={
  tableData:[],
  fieldInfo: {},
  col: [
    {
      fixed: "left",
      label: "问题ID",
      prop: "problemId",
      width: "0",
      type: "",
      specialType: ""
    },
    {
      fixed: "left",
      label: "问题",
      prop: "problem",
      width: "300",
      type: "",
      specialType: ""
    },
    {
      fixed: "",
      label: "答案",
      prop: "answer",
      width: "300",
      type: "",
      specialType: ""
    },
    {
      fixed: "",
      label: "语音",
      prop: "sound",
      width: "100",
      type: "",
      specialType: ""
    },
    {
      fixed: "",
      label: "多图片",
      prop: "pictures",
      width: "100",
      type: "",
      specialType: ""
    },
    {
      fixed: "",
      label: "审核状态",
      prop: "status",
      width: "220",
      type: "",
      specialType: ""
    }
  ],
  operWidth: "200",
  oper: [
    {
      label: "查看",
      size: "mini",
      type: "text",
      method: "handleView"
    }]
}
export default verifyTableInfo;
