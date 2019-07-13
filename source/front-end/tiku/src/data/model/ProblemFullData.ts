import Tag from './Tag';
/**
 * 问题模型对象 ( 包含问题的所有信息 )
 */
interface ProblemFullData {
    /**
     * @problem 问题的主体
     *  @id 问题的 id (非必须)
     *  @problemText 问题的内容 
     *  @parentId 父问题的id (非必须)
     *  @answer 问题的答案id (非必须)
     */
    problem: {
        id?: number,
        problemText: string,
        parentId?: number,
        answerId?: number
    },

    /**
    * @answer 问题的答案
    *  @id 答案的id (非必须)
    *  @answer 案的内容 
    *  @answerImg 答案的图片 (有的答案是以图片的形式存在的,数据库存储的是图片在服务器中的 URL )(非必须)
    */
    answer: {
        id?: number,
        answerText: string,
        answerImg?: string
    },


    tags?: Tag[],

    /**
    * @extData 问题的额外数据,例如: 图片、语音 等 (非必须)
    */
    extData?: any,

    /**
    * @status 问题的状态
    */
    status: any
}
export default ProblemFullData;