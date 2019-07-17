import axios from 'axios'
import ProblemFullData from '@/data/model/ProblemFullData'

import porblemsList from '@/data/mock/ProblemFullDataMock'
import BasicResponse from '@/data/model/BasicResponse';
import TagInfo from '@/data/model/Tag';

var problemTemp: ProblemFullData[] = porblemsList;

/**
 * 获取题目列表方法
 * @param page 
 * @param callback 
 */
function getProblems(page: number, callback: (Problems: ProblemFullData[]) => void) {
    //线上
    axios
        .get("/api/problem/list?pageNumber=" + page + "&pageSize=10")//服务器网址
        .then(res => {
            let lists: ProblemFullData[] = res.data.data.problemFullDataList;
            console.log(lists);
            callback(lists);
        })
}
// function getProblems(page: number, callback: (Problems: ProblemFullData[]) => void) {
//     //获得题目列表方法--本地数据测试
//     let lists: ProblemFullData[] = problemTemp;
//     console.log("获取到的数据");
//     console.log(lists);
//     callback(lists);
// }

/**
 * 增加题目方法
 * @param problem 是一个有关于 problemFullData 的一个对象实例
 * @param callback  回调函数
 */
function addProblem(problem: ProblemFullData, callback: (b: BasicResponse) => void) {
    //线上    
    axios
        .post("/api/problem/add", problem)
        .then(res => {
            //得到一个返回的参数,以确保题目增加成功
            let response: BasicResponse = res.data;
            console.log("获取的数据");
            console.log(problem);

            console.log(response);
            callback(response);
        })
}
// function addProblem(problem: ProblemFullData, callback: (b: BasicResponse) => void) {
//     //增加问题方法--本地数据测试
//     if (problem.problem.id === undefined) {
//         problem.problem.id = Number(problemTemp[problemTemp.length - 1].problem.id) + 1;
//     }
//     problemTemp.push(problem);
//     console.log("增加了题目");
//     console.log(problemTemp);
//     let response: BasicResponse = { code: 'ok' };
//     callback(response);
// }

/**
 * 删除题目方法
 * @param id 要删除的题目的 id, (可能是一个或者多个题目的id数组)
 * @param callback 回调函数
 */
function delProblem(idList: number[], callback: (b: BasicResponse) => void) {
    axios
        .delete("/api/problem/delete?"+"id="+idList[1])
        .then(res => {
            let response: BasicResponse = res.data;
            console.log(response);
            callback(response);
        })
}
// function delProblem(idLsit: number[], callback: (b: BasicResponse) => void) {
//     //删除题目方法--本地数据测试
//     for (var i = 0; i < idLsit.length; i++) {
//         for (var j = 0; j < problemTemp.length; j++) {
//             if (idLsit[i] === problemTemp[j].problem.id) {
//                 problemTemp.splice(j, 1)
//             }
//         }
//     }
//     console.log(problemTemp);
//     let response: BasicResponse = { code: "ok" };
//     console.log(response);
//     callback(response);

// }


/**
 * 查找题目方法 -- 标签查询
 * @param tagIdList 标签列表( 里面是所有标签内容组成的数组 )
 * @param callback 回调函数
 */
// function findProblems(tagIdList: number[], callback: (p: ProblemFullData[]) => void) {
//     let type : string = "tag";
//     axios
//         .post("/api/problem/query", {type ,tagIdList})
//         .then(res => {
//             let response: ProblemFullData[] = res.data;

//             callback(response);
//         })
// }
function findProbLemsByTags(tagList: TagInfo[], callback: (p: ProblemFullData[]) => void) {
    var temp: ProblemFullData[] = [];

    for (var i = 0; i < problemTemp.length; i++) {
        for (var j = 0; j < tagList.length; j++) {
            var isStop = 0;
            for (var k = 0; k < problemTemp[i].tags.length; k++) {
                if (problemTemp[i].tags[k].value === tagList[j].value) {
                    temp.push(problemTemp[i]);
                    isStop = 1;
                    break;
                }
            }
            if (isStop == 1) {
                break;
            }
        }
    }
    console.log("标签查询结果");
    console.log(temp);
    callback(temp);
}

/**
 * 查询题目方法 -- 模糊查询
 * @param stringInfo 模糊查询的字符串
 * @param callback 
 */
function findProblemsVaguely(stringInfo: string, callback: (p: ProblemFullData[]) => void) {
    let type: string = "vague";
    axios
        .post("/api/problem/query", { type, stringInfo })
        .then(res => {
            let list: ProblemFullData[] = res.data;
            callback(list)
        })
}
// function findProblemsVaguely(stringInfo: string, callback: (p: ProblemFullData[]) => void) {
//     console.log("模糊查询结果");
//     var temp: ProblemFullData[] = [];
//     for (var i = 0; i < problemTemp.length; i++) {
//         if (problemTemp[i].extData !== undefined) {
//             for (var tempValue in problemTemp[i].extData) {
//                 if (String(problemTemp[i].extData[tempValue]).indexOf(stringInfo) !== -1) {
//                     temp.push(problemTemp[i]);
//                     break;
//                 }
//             }
//         }
//         if (problemTemp[i].problem.problemText.indexOf(stringInfo) !== -1 || problemTemp[i].answer.answerText.indexOf(stringInfo) !== -1) {
//             temp.push(problemTemp[i]);
//             break;
//         }
//         for (var j = 0; j < problemTemp[i].tags.length; j++) {
//             // console.log(problemTemp[i].tags[j].value);
//             // console.log(problemTemp[i].tags[j].value.indexOf(stringInfo) !== -1);
//             if (problemTemp[i].tags[j].value.indexOf(stringInfo) !== -1) {
//                 temp.push(problemTemp[i]);
//                 break;
//             }
//         }
//     }
//     console.log(temp);
//     callback(temp)
// }

/**
 * 修改题目方法
 * @param problem 一个problemFullData 对象实例
 * @param callback 回调函数
 */
function changeProblem(problem: ProblemFullData, callback: (b: BasicResponse) => void) {
    axios
        .post("/api/problem/update", problem)
        .then(res => {
            //获取修改结果
            let response: BasicResponse = res.data;
            callback(response);
        })
}
// function changeProblem(problem: ProblemFullData, callback: (b: BasicResponse) => void) {
//     console.log("yaoxiugai");
//     console.log(problem);
//     for (var i = 0; i < problemTemp.length; i++) {
//         console.log(i);
//         if (problemTemp[i].problem.id === problem.problem.id) {
//             console.log(problemTemp[i]);   
//             if (problem.problem.problemText !== "") {
//                 problemTemp[i].problem.problemText = problem.problem.problemText
//             }
//             if (problem.problem.answerId !== undefined) {
//                 problemTemp[i].problem.answerId = problem.problem.answerId
//             }
//             if (problem.problem.parentId !== undefined) {
//                 problemTemp[i].problem.parentId = problem.problem.parentId
//             }
//             if (problem.answer.id !== undefined) {
//                 problemTemp[i].answer.id = problem.answer.id;
//             }
//             if (problem.answer.answerImg !== undefined) {
//                 problemTemp[i].answer.answerImg = problem.answer.answerImg;
//             }
//             if (problem.answer.answerText !== "") {
//                 problemTemp[i].answer.answerText = problem.answer.answerText;
//             }
//             if (problem.tags.length !== 0) {
//                 problemTemp[i].tags = problem.tags;
//             }
//             if (problem.extData !== undefined) {
//                 problemTemp[i].extData = problem.extData;
//             }
//             break;
//         }
//     }
//     console.log(problemTemp);
//     callback({ code: "ok" });
// }



export {
    getProblems,
    addProblem,
    delProblem,
    findProbLemsByTags,
    findProblemsVaguely,
    changeProblem
}      