interface GeneralTable {
  fieldInfo: {},
  tableData:{},
  /**
  * col:列
   * fixed:列是否固定在左侧left
   * label:列名
   * prop:列内容的字段名
   * width:列宽度
   * type:selection多选框
   * sepecialType:tag标签/duoji多级表头(fieldInfo)
   * */
  col: {
    fixed: string,
    label: string,
    prop: string,
    width: string,
    type: string,
    specialType: string
  }[],
  /**
   * label:按钮文字
   * size:尺寸medium / small / mini
   * type：类型primary / success / warning / danger / info / text
   * method:方法
   */
  operWidth: string,
  oper: {
    label: string,
    size: string,
    type: string,
    method: string
  }[]
}
export default GeneralTable;
