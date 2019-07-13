interface ProblemFullData {
    /**
     * @problem 问题的主体:
     *  @id 问题的id;
     *  @problemText 问题的内容;
     *  @parentId 父问题的id;
     *  @answer 问题的答案id;
     */
    problem: {
        id: number,
        problemText: string,
        parentId: number,
        answerId: number
    },

    /**
    * answer 问题的答案
    *  id: 答案的id
    *  answer: 答案的内容
    *  answerImg: 答案的图片 (有的答案是以图片的形式存在的,数据库存储的是图片在服务器中的 URL )
    */
    answer: {
        id: number,
        answerText: string,
        answerImg: string
    },

    /**
     * tag 问题的标签
     *  id: 标签的id
     *  value: 标签的内容
     *  parentId:  父标签的id
     */
    tags: {
        id: number,
        value: string,
        parentId: number
    }[],

    /**
    * extData 问题的额外数据,例如: 图片、语音 等
    */
    extData: any,

    /**
    * status 问题的状态
    */
    status: any
}
export default ProblemFullData;