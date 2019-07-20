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

function getProblems(pageNumber: number, callback: (Problems: ProblemFullData[], ProblemListSize: number) => void, isCheck?: number) {
    //线上
    pageNumber = (pageNumber === undefined ? 0 : pageNumber)
    isCheck = (isCheck === undefined ? 0 : isCheck)
    axios
        .get("/api/problem/list?pageNumber=" + pageNumber + "&pageSize=10" + "&isCheck=" + isCheck)//服务器网址
        .then(res => {
            let lists: ProblemFullData[] = res.data.data.problemFullDataList;
            let size: number = res.data.data.pagination.total;
            // console.log(lists);
            callback(lists, size);
        })
}


/**
 * 增加题目方法
 * @param problem 是一个有关于 problemFullData 的一个对象实例
 * @param callback  回调函数
 */
function addProblem(problem: ProblemFullData, callback: (b: BasicResponse) => void) {
    //线上
    axios
        .post("/api/problem/add",problem)
        .then(res=>{
            let response :BasicResponse=res.data;
            callback(response)
        })
}

function checkProblem(problemId: number, callback: (b: BasicResponse) => void) {
  //线上
  axios
      .put("/api/problem/check?id="+problemId)
      .then(res=>{
        let response :BasicResponse=res.data;
        callback(response)
      })
}

/**
 * 删除题目方法
 * @param id 要删除的题目的 id, (可能是一个或者多个题目的id数组)
 * @param callback 回调函数
 */
function delProblem(idList: number[], callback: (b: BasicResponse) => void) {
    axios
        .post("/api/problem/delete", idList)
        .then(res => {
            let response: BasicResponse = res.data;
            callback(response);
        })
}

/**
 * 查找题目方法 -- 标签查询
 * @param tagIdList 标签列表( 里面是所有标签内容组成的数组 )
 * @param callback 回调函数
 */
function findProbLemsByTags(tagIdList: number[], callback: (p: ProblemFullData[]) => void) {
    let type: string = "tag";
    axios
        .post("/api/problem/query", { type, tagIdList })
        .then(res => {
            let response: ProblemFullData[] = res.data;

            callback(response);
        })
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

/**
 * 修改题目方法
 * @param problem 一个problemFullData 对象实例
 * @param callback 回调函数
 */
function changeProblem(problem: ProblemFullData, callback: (b: BasicResponse) => void) {
    axios
        .put("/api/problem/update", problem)
        .then(res => {
            //获取修改结果
            let response: BasicResponse = res.data;

            callback(response);
        })
}

export {
    getProblems,
    addProblem,
    delProblem,
    findProbLemsByTags,
    findProblemsVaguely,
    changeProblem,
  checkProblem
}      