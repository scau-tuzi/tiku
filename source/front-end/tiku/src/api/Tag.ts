import axios from 'axios'
import TagInfo from '@/data/model/Tag';
import tagslistData from '@/data/mock/TagMock'
import BasicResponse from '@/data/model/BasicResponse';

var tagsTemp: TagInfo[] = tagslistData;

/**
 * 获得标签列表方法
 * @param callback 回调函数
 */
function getTagsList(pageNumber: number, callback: (t: TagInfo[], tagsListSize: number) => void,pageSize?:number) {
    //线上
    pageSize = (pageSize === undefined ? 10 : pageSize)
    axios
        .get("/api/tag/list?pageNumber=" + pageNumber+"&pageSize="+pageSize)//服务器地址
        .then(res => {
            let lists: TagInfo[] = res.data.data.tagList;
            let size: number = res.data.data.pagination.total;
            callback(lists, size)
        })
}


/**
 * 增加标签方法
 * @param tags 增加的标签数组 ( 一个或者多个 )
 * @param callback 回调函数
 */
function addTags(tags: TagInfo, callback: (b: BasicResponse) => void) {
    //线上
    console.log(tags);
    
    axios
        .post("/api/tag/add", tags)
        .then(res => {
            //获取添加成功参数
            let response: BasicResponse = res.data;
            callback(response)
        })
}



/**
 * 删除标签
 * @param idLsit 要删除的标签的 id
 * @param callback 回调函数
 */
function delTag(idLsit: number[], callback: (b: BasicResponse) => void) {
    //线上
    axios
        .post("/api/tag/delete" , idLsit)
        .then(res => {
            let response: BasicResponse = res.data;
            callback(response)
        })
}


/**
 * 修改标签方法
 * @param tag 要删除的标签的 id 
 * @param callback 回调函数
 */
function changeTag(tag: TagInfo, callback: (b: BasicResponse) => void) {
    axios
        .put("/api/tag/update", tag)
        .then(res => {
            let response: BasicResponse = res.data;
            callback(response)
        })
}
// function changeTag(tag: TagInfo, callback: (b: BasicResponse) => void) {
//     //修改标签方法--本地
//     //预留 ==
// }

export {
    getTagsList,
    addTags,
    delTag,
    changeTag
}