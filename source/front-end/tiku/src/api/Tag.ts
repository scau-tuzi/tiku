import axios from 'axios'
import Tag from '@/data/model/Tag';
import tagslistData from '@/data/mock/TagMock'
import BasicResponse from '@/data/model/BasicResponse';


/**
 * 获得标签列表
 * @param callback 回调函数,
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
 * 增加标签
 * @param tagValue: 标签的内容文本
 * @param parentId: 父标签的id(没有的时候可以不用填写)
 * @param Callback: 回调函数
 */
function addTags(tagValue: string, parentId: -1 | number, Callback: (p: BasicResponse) => void) {

    axios
        .post("", {
            //标签数据
            parentId,
            tagValue
        })
        .then(res => {
            //获取添加成功参数
            let response: BasicResponse = res.data;

            Callback(response)
        })

}

/**
 * 删除标签
 * @param tagValue 要删除的标签的内容
 * @param callback 回调函数
 */
function delTag(tagValue: string, callback: (b: BasicResponse) => void) {
    // 删除标签
    axios
        .post("", {
            //删除标签的参数
            tagValue
        })
        .then(res => {
            //成功与否
            let response : BasicResponse= res.data;

            callback(response)
        })

}

export {
    getTagsList,
    addTags,
    delTag
}