import GeneralTable from "@/data/model/GeneralTable";
import  tableData from  "@/components/TikuTable.vue"
import  FieldInfoMock from  "@/data/mock/FiledInfoMock"
let tikuTableInfo:GeneralTable={
  fieldInfo :FieldInfoMock,
  tableData: [{
    id: "1",
  problem: 'How are you?',
  answer: 'I am fine. Thank you',
  pictures: '',
  sound: 'VOA.mp3',
  tag: '英语',
  status: '已通过'
}, {
    id: "2",
  problem: 'How old are you?',
  answer: '12',
  pictures: '',
  sound: 'VOA.mp3',
  tag: '英语',
  status: '已通过'
}, {
    id:"3",
  problem: 'Have a nice day !',
  answer: 'Thank you ! I hope so !',
  pictures: '',
  sound: 'VOA.mp3',
  tag: '英语',
  status: '已通过'
}],
  col: [
    {
      fixed: "",
      label: "",
      prop: "",
      width: "",
      type: "selection",
      specialType: "",
    },
    {
      fixed: "left",
      label: "问题",
      prop: "problem",
      width: "300",
      type: "",
      specialType: "",
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
      label: "语言",
      prop: "sound",
      width: "50",
      type: "",
      specialType: ""
    },
    {
      fixed: "",
      label: "多图片",
      prop: "pictures",
      width: "50",
      type: "",
      specialType: ""
    },
    {
      fixed: "",
      label: "选项",
      prop: "",
      width: "300",
      type: "",
      specialType: "duoji"
    },
    {
      fixed: "",
      label: "标签",
      prop: "tag",
      width: "300",
      type: "",
      specialType: "tag"
    }],
  operWidth: "250",
  oper: [
    {
      label: "编辑",
      size: "mini",
      type: "plain",
      method: "handleEdit"
    },
    {
      label: '编辑标签',
      size: 'mini',
      type: 'plain',
      method: "showTags"
    },
    {
      label: '删除',
      size: 'mini',
      type: 'danger',
      method: "handleDelete"
    }
  ]
}
export default  tikuTableInfo;
