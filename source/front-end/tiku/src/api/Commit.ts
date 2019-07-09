
// 获取git 历史提交
//https://developer.github.com/v3/repos/commits/

// todo  1.网络层
// 从接口拿数据 返回数据对象

import axios from 'axios'

// 参数是回调函数，拿到数据就调用callback
function getAllCommit(callback:(c:CommitInfo[])=>void){
    axios.get("/repos/czfshine/DMML/commits").then(res=>{

        let lists:CommitInfo[] = res.data;
        console.log("获取到的数据");
        console.log(lists);

        callback(lists);
    })
}


export {
    getAllCommit
}