import ProblemFullData from './ProblemFullData';
import { __values } from 'tslib';
import Tag from './Tag';

/**
 * 试卷模型对象 ( 包含试卷的所有的信息,除了题目 id,不包含题目的具体内容 )
 */
interface PaperFullData {
    /**
     * @paper 试卷; 
     *  @id 试卷id; 
     *  @title 试卷标题; 
     */
    paper: {
        id?: number,
        title: string
    },
    
    /**
     * @tags 包括试卷多拥有的所有标签 信息
     */
    tags?: Tag[],

    /**
     * @problemId 包含试卷所有的题目 id ;
     */
    problemId: number[],

    /**
     * @problems 问题的所有信息，请参考 problemFullData (非必须)
     */
    problems?: ProblemFullData[],

    /**
     * @deep 问题是否需要具体化（不是就只需要获取它的问题 id ）;
     */
    deep?: boolean
}
export default PaperFullData;