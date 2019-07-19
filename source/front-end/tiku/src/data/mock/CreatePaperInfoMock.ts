import GeneralTable from "@/data/model/GeneralTable";

let createPaperInfoMock:GeneralTable={
  fieldInfo: [],
  tableData: [
    // {
    //   id: '100',
    //   problem: '2016-05-02',
    //   answer: '王小虎1',
    //   tag: '22'
    // },

  ],
  col: [
    {
      fixed: "",
      label: "",
      prop: "",
      width: "0",
      type: "selection",
      specialType: ""
    },
    {
      fixed: "",
      label: "题目",
      prop: "problem",
      width: "200",
      type: "",
      specialType: ""
    },
    {
      fixed: "",
      label: "答案",
      prop: "answer",
      width: "200",
      type: "",
      specialType: ""
    },
    {
      fixed: "",
      label: "标签",
      prop: "tag",
      width: "100",
      type: "",
      specialType: "tag"
    }
  ],
  operWidth:"100",
  oper: [
    {
      label: "查看",
      size: "mini",
      type: "success",
      method: "handleView"
    }
  ]
}
let createPaperOrderMock:GeneralTable={
  fieldInfo: [],
  tableData: [],
  col: [
    {
      fixed: "",
      label: "",
      prop: "",
      width: "0",
      type: "selection",
      specialType: ""
    },
    {
      fixed: "",
      label: "题目",
      prop: "problem",
      width: "200",
      type: "",
      specialType: ""
    },
    {
      fixed: "",
      label: "答案",
      prop: "answer",
      width: "200",
      type: "",
      specialType: ""
    },
    {
      fixed: "",
      label: "标签",
      prop: "tag",
      width: "200",
      type: "",
      specialType: "tag"
    }
  ],
  operWidth: "200",
  oper: [
    {
      label: "查看",
      size: "mini",
      type: "success",
      method: "handleView"
    },
    {
      label: "删除",
      size: "mini",
      type: "danger",
      method: "handleDelete"
    }
  ]
}
export {
  createPaperInfoMock,
  createPaperOrderMock
};
