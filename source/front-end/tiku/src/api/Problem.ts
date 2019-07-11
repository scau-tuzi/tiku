
import axios from 'axios'
import ProblemFullData from '@/data/model/ProblemFullData';

function getProblems(callback: (Problems:ProblemFullData[]) => void) {
    axios
        .get("")//服务器网址
        .then(res => {
            let lists: ProblemFullData[] = res.data;
            console.log("获取到的数据");
            console.log(lists);

            callback(lists);
        })
}


export {
    getProblems
}