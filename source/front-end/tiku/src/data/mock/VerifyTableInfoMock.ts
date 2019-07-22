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
      label: "标签",
      prop: "tags",
      width: "200",
      type: "",
      specialType: "tag"
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
    },
    {
      label: "审核通过",
      size: "mini",
      type: "text",
      method: "handleVerify"
    },
    {
      label: "删除",
      size: "mini",
      type: "text",
      method: "handleDelete"
    }
  ]
}
export default verifyTableInfo;
