import axios from 'axios'
import PaperFullData from '@/data/model/PaperFullData';
import BasicResponse from '@/data/model/BasicResponse';
/**
 * 拿到试卷列表方法
 * @param callback 
 */
function getPapers(callback: (p: PaperFullData[]) => void) {
    axios
        .post("")//服务器地址
        .then(res => {
            let lists: PaperFullData[] = res.data;
            callback(lists)
        })
}

/**
 * 增加试卷方法
 * @param paper 需要增加的试卷 ( PaperFullData 类型)
 * @param callback 
 */
function addPaper(paper: PaperFullData, callback: (b: BasicResponse) => void) {
    
    axios
        .post("", {
            //试卷数据
            paper
        })
        .then(res => {
            //是否添加试卷
            let response : BasicResponse = res.data;
            callback(response)
        })

}

/**
 * 修改试卷方法
 * @param paperId 要修改试卷的id
 * @param callback 
 */
function changePaper(paperId: number, callback:(b: BasicResponse)=>void ) {
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