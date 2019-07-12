import ProblemFullData from '@/data/model/ProblemFullData'

// let porblems: ProblemFullData[] = [];

let porblemsList: ProblemFullData[] = [
    {
        problem: {
            id: 1,
            problemText: "小明是小学生,正常情况有没有暑假",
            parentId: -1,
            answerId: 1
        },
        answer: {
            id: 1,
            answerText: "有",
            answerImg: ""
        },
        tags: [{
            id: 1,
            value: "幼儿园",
            parentId: -1,
        }, {
            id: 2,
            value: "常识",
            parentId: -1
        }],
        extData: null,
        status: 0
    }, {
        problem: {
            id: 1,
            problemText: "一天有几个小时",
            parentId: 0,
            answerId: 2
        },
        answer: {
            id: 3,
            answerText: "24",
            answerImg: ""
        },
        tags: [
            {
                id: 2,
                value: "常识",
                parentId: 0,
            },{
                id: 1,
                value: "幼儿园",
                parentId: 0,
            }
        ],
        extData: null,
        status: 0
    },{
        problem:{
            id: 3,
            problemText: "今天下雨,是晴天还是雨天",
            parentId: 0,
            answerId: 3
        },
        answer:{
            id: 3,
            answerText: "雨天",
            answerImg: ""
        },
        tags:[
            {
                id: 2,
                value:"常识",
                parentId: 0,
            },{
                id: 3,
                value: "小学",
                parentId: 0,
            }
        ],
        extData: null,
        status: 0
    } 
]

// export default porblems;
export default porblemsList;
