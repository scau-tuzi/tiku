import axios from 'axios'
import ProblemFullData from '@/data/model/ProblemFullData'

import porblemsList from '@/data/mock/ProblemFullDataMock'
import BasicResponse from '@/data/model/BasicResponse';


function getProblems(page: number, callback: (Problems: ProblemFullData[]) => void) {
    /**
     * 查看题目列表
     */
    axios
        .get("/api/problem/list?pageNumber=" + page + "&pageSize=10")//服务器网址
        .then(res => {
            let lists: ProblemFullData[] = res.data.data;

    // let lists: ProblemFullData[] = porblemsList;
    // console.log("获取到的数据");
    // console.log(lists);
    callback(lists);
    })
}

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

// function delProblem(id: number, callback: (b: BasicResponse) => void) {
//     //删除题目
//     axios
//         .post("", {
//             id,
//         })
//         .then(res => {
//             let response: BasicResponse = res.data;
//             callback(response);
//         })
// }

function findProblems(tags: string[], callback: (p: ProblemFullData[]) => void) {
    //查找题目
    /**
     * 暂时仅支持通过标签查找
     */
    axios
        .post("", {
            tags
        })
        .then(res => {
            let response: ProblemFullData[] = res.data;

            callback(response);
        })
}

function changeProblem(problem: ProblemFullData, callback: (b: BasicResponse) => void) {
    //修改题目
    axios
        .post("", {
            /**
             * 传递要修改的题目参数
             *      同时传递修改内容
             */

            problem,
        })
        .then(res => {
            //获取修改结果
            let response: BasicResponse = res.data;

            callback(response);

        })

}

export {
    getProblems,
    addProblem,
    // delProblem,
    findProblems,
    changeProblem
}      