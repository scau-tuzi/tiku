import GeneralTable from "@/data/model/GeneralTable";

let paperListTable:GeneralTable={
  tableData: [],
  fieldInfo: {},
  col: [
    {
      fixed: "left",
      label: "",
      prop: "",
      width: "0",
      type: "selection",
      specialType: ""
    },
    {
      fixed: "left",
      label: "试卷名称",
      prop: "title",
      width: "600",
      type: "",
      specialType: ""
    },
    {
      fixed: "",
      label: "标签",
      prop: "tag",
      width: "300",
      type: "",
      specialType: "tag"
    }
  ],
  operWidth: "300",
  oper:[
    {
      label: "查看",
      size: "mini",
      type: "plain",
      method: "ViewPaper"
    },
    {
      label: "编辑标签",
      size: "mini",
      type: "plain",
      method: "showTags"
    },
    {
      label: "编辑",
      size: "mini",
      type: "plain",
      method: "editPaper"
    },
    {
      label: "删除",
      size: "mini",
      type: "danger",
      method: "handleDelete"
    }
  ]
}
export default paperListTable;
