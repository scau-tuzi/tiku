import axios from 'axios'
import PaperFullData from '@/data/model/PaperFullData';

function getPapers(callback: (p: PaperFullData[]) => void) {
    axios
        .post("")//服务器地址
        .then(res => {
            let lists: PaperFullData[] = res.data;
            callback(lists)
        })
        .catch(function (eror) {
            console.log(eror)
        })
}

function addPaper() {
    //增加试卷
    axios
        .post("", {
            //试卷数据
            
        })
        .then(res => {
            //是否添加试卷
        })

}

function changePaper() {
    //修改试卷
    axios
        .post("", {

        })
        .then(res => {

        })
}


export {
    getPapers,
    addPaper,
    changePaper
}