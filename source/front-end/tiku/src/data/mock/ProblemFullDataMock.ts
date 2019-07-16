import ProblemFullData from '@/data/model/ProblemFullData'

// let porblems: ProblemFullData[] = [];

let porblemsList: ProblemFullData[] = [
    {
        problem: {
            id: 4564987916,
            problemText: "小明是小学生,正常情况有没有暑假",
            parentId: -1,
            answerId: 1
        },
        answer: {
            id: 345645654,
            answerText: "有",
            answerImg: ""
        },
        tags: [{
            id: 87954651315,
            value: "幼儿园",
            parentId: -1,
        }, {
            id: 895451844,
            value: "常识",
            parentId: -1
        }],
        extData: {
            choice_A:"有",
            choice_B:"没有",
            choice_C:"你猜",
            choice_D:"想得美"            
        },
        status: 0
    }, {
        problem: {
            id: 798566,
            problemText: "一天有几个小时",
            parentId: 0,
            answerId: 2
        },
        answer: {
            id: 2323432894623876,
            answerText: "24",
            answerImg: ""
        },
        tags: [
            {
                id: 895451844,
                value: "常识",
                parentId: 0,
            },{
                id: 87954651315,
                value: "幼儿园",
                parentId: 0,
            }
        ],
        extData: {
            choice_A:"24",
            choice_B:"12",
            choice_C:"10",
            choice_D:"36" 
        },
        status: 0
    },{
        problem:{
            id: 789445515,
            problemText: "今天下雨,是晴天还是雨天",
            parentId: 0,
            answerId: 3
        },
        answer:{
            id: 2334556567,
            answerText: "雨天",
            answerImg: ""
        },
        tags:[
            {
                id: 895451844,
                value:"常识",
                parentId: 0,
            },{
                id: 56467578563,
                value: "小学",
                parentId: 0,
            }
        ],
        extData: {
            choice_A:"晴天",
            choice_B:"雨天",
            choice_C:"阴天",
            choice_D:"大风天" 
        },
        status: 0
    } 
]

// export default porblems;
export default porblemsList;