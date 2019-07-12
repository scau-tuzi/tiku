interface ProblemFullData {
    /**
     * problem 问题的主体, 
     *  id： 问题的id；
     *  problemText： 问题的内容；
     *  parentId: 父问题的id
     *  answer：问题的答案id
     */
    /**
     * answer 问题的答案
     *  id: 标签的id
     *  value: 标签的内容
     *  parentId:  父标签的id
     */
    /**
     * tag 问题的标签
     *  id: 标签的id
     *  value: 标签的内容
     *  parentId:  父标签的id
     */
    /**
     * extData 问题的额外数据,例如: 图片、语音 等
     */

    /**
     * status 问题的状态
     */

    problem: {
        id: number,
        problemText: string,
        parentId: number,
        answerId: number
    },
    answer: {
        answerText:string
    },
    tags:[{
      id: number,
      value: string,
      parentId: number
    }],
    extData: any,
    status: number
}
export default ProblemFullData;