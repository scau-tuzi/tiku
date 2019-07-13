import axios from 'axios'
import PaperFullData from '@/data/model/PaperFullData';
import BasicResponse from '@/data/model/BasicResponse';
import Tag from '@/data/model/Tag';
/**
 * 拿到试卷列表方法
 * @param callback 回调函数
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
        .post("", paper)
        .then(res => {
            //是否添加试卷
            let response: BasicResponse = res.data;
            callback(response)
        })

}

/**
 * 删除试卷
 * @param paperId 试卷id
 * @param callback 回调函数
 */
function delPaper(paperId: number, callback: (b: BasicResponse) => void) {
    axios
        .post("", paperId)
        .then(res => {
            let response: BasicResponse = res.data;
            callback(response)
        })
}

/**
 * 查找方法 ( 通过标题 ) (模糊查询)
 * @param paperTitle 试卷标题模糊查询
 * @param callback 回调函数
 */
function findPaperByTitle(paperTitle: string, callback: (p: PaperFullData[]) => void) {
    axios
        .post("", paperTitle)
        .then(res => {
            let response: PaperFullData[] = res.data;
            callback(response)
        })
}

/**
 * 查找试卷方法 ( 通过标签 )
 * @param tags 试卷标签查询
 * @param callback 回调函数
 */
function findPaperBytags(tags: Tag[], callback: (p: PaperFullData[]) => void) {
    axios
        .post("", tags)
        .then(res => {
            let response: PaperFullData[] = res.data;
            callback(response)
        })
}

/**
 * 修改试卷方法
 * @param paperId 要修改试卷的id
 * @param callback 
 */
function changePaper(paperId: number, callback: (b: BasicResponse) => void) {
    axios
        .post("", paperId)
        .then(res => {
            let response: BasicResponse = res.data;

            callback(response)
        })
}


export {
    getPapers,
    addPaper,
    delPaper,
    findPaperByTitle, findPaperBytags,
    changePaper
}