import ProblemFullData from './ProblemFullData';
import { __values } from 'tslib';


interface PaperFullData {
    /**
     * paper: 试卷; 
     *  id:试卷id; 
     *  title: 试卷标题; 
     */
    paper: {
        id: number,
        title: string
    },
    /**
    * tag: 试卷的标签; 
    *  id: 标签的id; 
    *  value: 标签的内容; 
    *  parentId: 父标签的id; 
    */
    tags: {
        id: number,
        value: string,
        parentId: number
    }[],

    /**
     * problemId: 题目id;
     */
    problemId: number[],

    /**
     * problems: 问题的所有信息，请参考 problemFullData;
     */
    problems: ProblemFullData[],

    /**
     * deep: 问题是否需要具体化（不是就只需要获取它的问题 id ）;
     */
    deep: boolean
}
export default PaperFullData;