import axios from 'axios'
import ProblemFullData from '@/data/model/ProblemFullData'

import porblemsList from '@/data/mock/ProblemFullDataMock'
import BasicResponse from '@/data/model/BasicResponse';


/**
 * 获取题目列表方法
 * @param page 
 * @param callback 
 */
function getProblems(page: number, callback: (Problems: ProblemFullData[]) => void) {
    /**
     * 查看题目列表
     */
    // axios
    //     .get("/api/problem/list?pageNumber=" + page + "&pageSize=10")//服务器网址
    //     .then(res => {
    //         let lists: ProblemFullData[] = res.data.data;
    //         callback(lists);
    //     })

    let lists: ProblemFullData[] = porblemsList;
    console.log("获取到的数据");
    console.log(lists);
    callback(lists);
}

/**
 * 增加题目方法
 * @param problem 是一个有关于 problemFullData 的一个对象实例
 * @param callback  回调函数
 */
function addProblem(problem: ProblemFullData, callback: (b: BasicResponse) => void) {
    axios
        .post("/api/problem/add", problem)
        .then(res => {
            //得到一个返回的参数,以确保题目增加成功
            let response: BasicResponse = res.data;
            console.log("获取的数据");
            console.log(response);
            callback(response);
        })

    // let response: BasicResponse = { code: 'ok', data: undefined };
    // console.log("获取的数据");
    // console.log(response);
    // callback(response);
}

/**
 * 删除题目方法
 * @param id 要删除的题目的 id, (可能是一个或者多个题目的id数组)
 * @param callback 回调函数
 */
function delProblem(id: number[], callback: (b: BasicResponse) => void) {
    axios
        .post("", id)
        .then(res => {
            let response: BasicResponse = res.data;
            callback(response);
        })
}

/**
 * 查找题目方法 (暂时只有通过标签来查询 | 没有模糊查询)
 * @param tags 标签列表( 里面是所有标签内容组成的数组 )
 * @param callback 回调函数
 */
function findProblems(tags: string[], callback: (p: ProblemFullData[]) => void) {
    axios
        .post("", tags)
        .then(res => {
            let response: ProblemFullData[] = res.data;

            callback(response);
        })
}

/**
 * 修改题目方法
 * @param problem 一个problemFullData 对象实例
 * @param callback 回调函数
 */
function changeProblem(problem: ProblemFullData, callback: (b: BasicResponse) => void) {
    axios
        .post("", problem)
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
    findProblems,
    changeProblem
}      