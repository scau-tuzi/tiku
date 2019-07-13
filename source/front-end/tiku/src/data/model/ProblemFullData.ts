interface ProblemFullData {
    /**
<<<<<<< HEAD
     * problem 问题的主体, 
     *  id： 问题的id；
     *  problemText： 问题的内容；
     *  parentId: 父问题的id
     *  answer：问题的答案id
=======
     * @problem 问题的主体:
     *  @id 问题的id;
     *  @problemText 问题的内容;
     *  @parentId 父问题的id;
     *  @answer 问题的答案id;
>>>>>>> ec267ee6943a37c04ce53aa8713730db0dd39465
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
<<<<<<< HEAD
     * status 问题的状态
     */

    problem: {
        id: number,
        problemText: string,
        parentId: number,
        answerId: number
    },
    answer: any,
    tags:[{
      id: number,
      value: string,
      parentId: number
    }],
    extData: any,
    status: number
=======
    * status 问题的状态
    */
    status: any
>>>>>>> ec267ee6943a37c04ce53aa8713730db0dd39465
}
export default ProblemFullData;