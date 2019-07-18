import GeneralTable from "@/data/model/GeneralTable";

let createPaperInfoMock:GeneralTable={
  fieldInfo: [],
  tableData: [
    {
      id: '100',
      problemText: '2016-05-02',
      answerText: '王小虎1',
      tag: '22'
    },
    {
      id: '200',
      problemText: '2016',
      answerText: '王小虎2',
      tag: '22'
    },
    {
      id: '300',
      problemText: '2016-05',
      answerText: '王小虎3',
      tag: '22'
    }
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
      prop: "problemText",
      width: "200",
      type: "",
      specialType: ""
    },
    {
      fixed: "",
      label: "答案",
      prop: "answerText",
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
      prop: "problemText",
      width: "200",
      type: "",
      specialType: ""
    },
    {
      fixed: "",
      label: "答案",
      prop: "answerText",
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
  operWidth: "100",
  oper: [
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
