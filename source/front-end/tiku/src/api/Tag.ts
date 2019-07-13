import axios from 'axios'
import Tag from '@/data/model/Tag';
import tagslistData from '@/data/mock/TagMock'
import BasicResponse from '@/data/model/BasicResponse';


/**
 * 获得标签列表方法
 * @param callback 回调函数
 */
function getTagsList(callback: (t: Tag[]) => void) {

    // axios
    //     .post("")//服务器地址
    //     .then(res => {
    //         let lists: Tag[] = res.data;
    //         callback(lists)
    //     })

    let lists: Tag[] = tagslistData;
    console.log("get it");
    console.log(lists);
    callback(lists)
}

/**
 * 增加标签方法
 * @param tags 增加的标签数组 ( 一个或者多个 )
 * @param Callback 回调函数
 */
function addTags(tags: Tag[], Callback: (p: BasicResponse) => void) {
    axios
        .post("", tags)
        .then(res => {
            //获取添加成功参数
            let response: BasicResponse = res.data;

            Callback(response)
        })

}

/**
 * 删除标签
 * @param tagID 要删除的标签的 id
 * @param callback 回调函数
 */
function delTag(tagId: number, callback: (b: BasicResponse) => void) {
    axios
        .post("", tagId)
        .then(res => {
            let response: BasicResponse = res.data;
            callback(response)
        })

}

/**
 * 修改标签方法
 * @param tagID 要删除的标签的 id 
 * @param callback 回调函数
 */
function ChangeTag(tagId: number, callback: (b: BasicResponse) => void) {
    axios
        .post("", tagId)
        .then(res => {
            let response: BasicResponse = res.data;
            callback(response)
        })
}

export {
    getTagsList,
    addTags,
    delTag,
    ChangeTag
}