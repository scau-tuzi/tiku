import ProblemFullData from './ProblemFullData';
import { __values } from 'tslib';


interface PaperFullData {
    /**
     * paper: 试卷
     *  id:试卷id
     *  title: 试卷标题
     */
     /**
     * tag: 试卷的标签
     *  id: 标签的id
     *  value: 标签的内容
     *  parentId: 父标签的id
     */
    /**
     * problemId: 题目id
     */
    /**
     * problems: 问题的所有信息，请参考 problemFullData
     */
    /**
     * deep: 问题是否需要具体化（不是就只需要获取它的问题 id ）
     */
     paper: {
         id: number,
         title: string
     },
     tags:[{
        id: number,
        value: string,
        parentId: number
      }],
     problemId: number[],
     problems: ProblemFullData[],
     deep: boolean
}
export default PaperFullData;