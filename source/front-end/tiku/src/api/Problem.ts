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
 * pass
 */
function getProblems(page: number, callback: (Problems: ProblemFullData[]) => void) {
    //线上
    axios
        .get("/api/problem/list?pageNumber=" + page + "&pageSize=10")//服务器网址
        .then(res => {
            let lists: ProblemFullData[] = res.data.data.problemFullDataList;
            // console.log(res);
            // console.log(lists);
            callback(lists);
        })
}

/**
 * 增加题目方法
 * @param problem 是一个有关于 problemFullData 的一个对象实例
 * @param callback  回调函数
 * pass
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

/**
 * 删除题目方法
 * @param id 要删除的题目的 id, (可能是一个或者多个题目的id数组)
 * @param callback 回调函数
 * pass
 */
function delProblem(idList: number[], callback: (b: BasicResponse) => void) {
    axios
        .post("/api/problem/delete",idList)
        .then(res => {
            let response: BasicResponse = res.data;
            console.log(response);
            callback(response);
        })
}


/**
 * 查找题目方法 -- 标签查询
 * @param tagIdList 标签列表( 里面是所有标签内容组成的数组 )
 * @param callback 回调函数
 */
function findProbLemsByTags(tagIdList: number[], callback: (p: ProblemFullData[]) => void) {
    let type : string = "tag";
    axios
        .post("/api/problem/query", {type ,tagIdList})
        .then(res => {
            let response: ProblemFullData[] = res.data;

            callback(response);
        })
}
// function findProbLemsByTags(tagList: TagInfo[], callback: (p: ProblemFullData[]) => void) {
//     var temp: ProblemFullData[] = [];

//     for (var i = 0; i < problemTemp.length; i++) {
//         for (var j = 0; j < tagList.length; j++) {
//             var isStop = 0;
//             for (var k = 0; k < problemTemp[i].tags.length; k++) {
//                 if (problemTemp[i].tags[k].value === tagList[j].value) {
//                     temp.push(problemTemp[i]);
//                     isStop = 1;
//                     break;
//                 }
//             }
//             if (isStop == 1) {
//                 break;
//             }
//         }
//     }
//     console.log("标签查询结果");
//     console.log(temp);
//     callback(temp);
// }

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


/**
 * 修改题目方法
 * @param problem 一个problemFullData 对象实例
 * @param callback 回调函数
 * pass
 */
function changeProblem(problem: ProblemFullData, callback: (b: BasicResponse) => void) {
    axios
        .put("/api/problem/update", problem)
        .then(res => {
            //获取修改结果
            let response: BasicResponse = res.data;
            console.log(response);
            console.log("获取修改结果");
            
            callback(response);
        })
}

export {
    getProblems,
    addProblem,
    delProblem,
    findProbLemsByTags,
    findProblemsVaguely,
    changeProblem
}      