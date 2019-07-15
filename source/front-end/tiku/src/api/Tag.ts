import axios from 'axios'
import TagInfo from '@/data/model/Tag';
import tagslistData from '@/data/mock/TagMock'
import BasicResponse from '@/data/model/BasicResponse';

var tagsTemp: TagInfo[] = tagslistData;

/**
 * 获得标签列表方法
 * @param callback 回调函数
 */
// function getTagsList(callback: (t: Tag[]) => void) {
//     //线上
//     axios
//         .post("/api/tag/list")//服务器地址ser
//         .then(res => {
//             let lists: Tag[] = res.data;
//             callback(lists)
//         })
// }
function getTagsList(callback: (t: TagInfo[]) => void) {
    //本地获取数据
    let lists: TagInfo[] = tagsTemp;
    // console.log("get tags list");
    // console.log(lists);
    callback(lists)
}

/**
 * 增加标签方法
 * @param tags 增加的标签数组 ( 一个或者多个 )
 * @param callback 回调函数
 */
// function addTags(tags: Tag[], callback: (b: BasicResponse) => void) {
//     //线上
//     axios
//         .post("/api/tag/add", tags)
//         .then(res => {
//             //获取添加成功参数
//             let response: BasicResponse = res.data;
//             callback(response)
//         })
// }

function addTags(tags: TagInfo[], callback: (b: BasicResponse) => void) {
    //添加标签方法--本地

    // tagsTemp.push({ id: 7, value: "数学", parentId: -1 });
    // tagsTemp.push({ id: 8, value: "语文", parentId: -1 });
    for (var i = 0; i < tags.length; i++) {
        if (tags[i].id === undefined) {
            tags[i].id = Number(tagsTemp[tagsTemp.length - 1].id) + 1;
        }
        if (tags[i].parentId === undefined) {
            tags[i].parentId = -1
        }
        tagsTemp.push(tags[i])
    }
    let lists: TagInfo[] = tagsTemp;
    let response: BasicResponse = { code: "Ok" };
    console.log("get this tags");
    console.log(lists);
    console.log(response);
    callback(response)
}

/**
 * 删除标签
 * @param tagID 要删除的标签的 id
 * @param callback 回调函数
 */
// function delTag(tagId: number[], callback: (b: BasicResponse) => void) {
//     //线上
//     axios
//         .post("/api/tag/delete", tagId)
//         .then(res => {
//             let response: BasicResponse = res.data;
//             callback(response)
//         })
// }
function delTag(tagId: number[], callback: (b: BasicResponse) => void) {
    //删除标签方法--本地
    for (var j = 0; j < tagId.length; j++) {
        for (var i = 0; i < tagsTemp.length; i++) {
            if (tagId[j] === tagsTemp[i].id) {
                tagsTemp.splice(i, 1);
                break;
            }
        }
    }
    let response: BasicResponse = { code: "OK" };
    console.log(tagsTemp);
    console.log(response);
    callback(response)
}

/**
 * 修改标签方法
 * @param tagID 要删除的标签的 id 
 * @param callback 回调函数
 */
// function changeTag(tag: TagInfo, callback: (b: BasicResponse) => void) {
//     axios
//         .post("/api/tag/update", tag)
//         .then(res => {
//             let response: BasicResponse = res.data;
//             callback(response)
//         })
// }
function changeTag(tag: TagInfo, callback: (b: BasicResponse) => void) {
    //修改标签方法--本地
    //预留 ==
}

export {
    getTagsList,
    addTags,
    delTag,
    changeTag
}