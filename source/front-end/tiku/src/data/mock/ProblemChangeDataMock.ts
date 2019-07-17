import ProblemFullData from "../model/ProblemFullData";

/**
 * 改变问题实例
 */
let problemData: ProblemFullData = {
    problem: {
        id: 0,
        problemText: "",
        parentId: -1,
        answerId: 0
    },
    answer: {
        id: 0,
        answerText: "",
        answerImg: ""
    },
    tags: [],
    extData: null,
    status: {
        verifyStatus: 1
    }
}

export default problemData