import axios from 'axios'
import Tag from '@/data/model/Tag';
import tagslistData from '@/data/mock/TagMock'

function getTagsList(callback: (t: Tag[]) => void) {
    /**
     * 获得标签列表
     */
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

function addTags() {
    // 增加标签
    axios
        .post("", {
            //标签数据
        })
        .then(res => {
            //获取添加成功参数
        })

}

function delTag() {
    // 删除标签
    axios
        .post("", {
            //删除标签的参数
        })
        .then(res => {
            //成功与否
        })

}

export {
    getTagsList,
    addTags,
    delTag
}