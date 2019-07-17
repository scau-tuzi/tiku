import axios from 'axios'
import UserInfo from '@/data/model/UserInfo';
import BasicResponse from '@/data/model/BasicResponse';

/**
 *  user api 获取用户列表方法
 * @param callback 
 */
function getUserList(callback: (u: UserInfo[]) => void) {
    axios
        .post("/api/user/list")
        .then(res => {
            let lists: UserInfo[] = res.data;
            callback(lists)
        })
}

/**
 * user api 增加用户方法
 * @param user 新增用户的信息 ( 参数为完整的 UseInfo 类型)
 * @param callback 
 */
function adduUser(user: UserInfo, callback: (b: BasicResponse) => void) {
    axios
        .post("/api/user/add", user)
        .then(res => {
            let response: BasicResponse = res.data;
            callback(response)
        })
}

/**
 * user api 删除用户方法
 * @param idList 需要删除的用户id列表
 * @param callback 
 */
function delUser(idList: number[], callback: (b: BasicResponse) => void) {
    axios
        .post("/api/user/delete", idList)
        .then(res => {
            let response: BasicResponse = res.data;
            callback(response)
        })
}

/**
 * user api 修改用户方法
 * @param user 要修改的用户信息 ( 参数类型为 UserInfo 类型)
 * @param callback 
 */
function changeUser(user: UserInfo, callback: (b: BasicResponse) => void) {
    axios
        .put("/api/user/update", user)
        .then(res => {
            let response: BasicResponse = res.data;
            callback(response)
        })
}

export {
    getUserList, adduUser, delUser, changeUser
}