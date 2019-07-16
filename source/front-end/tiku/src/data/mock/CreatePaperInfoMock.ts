import GeneralTable from "@/data/model/GeneralTable";

let createPaperInfoMock:GeneralTable={
  fieldInfo: [],
  tableData: [
    {
      id: '1',
      problem: '2016-05-02',
      answer: '王小虎1',
      tag: '22'
    },
    {
      id: '2',
      problem: '2016',
      answer: '王小虎2',
      tag: '22'
    },
    {
      id: '3',
      problem: '2016-05',
      answer: '王小虎3',
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
  tableData: [
    {
      id: "4",
      problem: "333",
      answer: "222",
      tag: "22"
    },
    {
      id: "5",
      problem: "333",
      answer: "222",
      tag: "22"
    },
    {
      id: "6",
      problem: "444",
      answer: "222",
      tag: "55"
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
