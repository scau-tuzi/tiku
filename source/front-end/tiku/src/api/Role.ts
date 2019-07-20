import axios from 'axios'
import BasicResponse from '@/data/model/BasicResponse';


/**
 *  获取角色的列表方法
 * @param pageNumber 
 * @param callback 
 * @param pageSize 
 */
function getRoles(pageNumber: number, callback: (r: RoleInfo[]) => void, pageSize?: number) {
    pageSize = pageSize === undefined ? 10 : pageSize
    axios
        .get("/api/role/list?pageNumber=" + pageNumber + "&pageSize=" + pageSize)
        .then(res => {
            let lists: RoleInfo[] = res.data.data.roleDtoList;
            callback(lists)
        })
}


/**
 * 添加角色方法
 * @param role 
 * @param callback 
 */
function addRole(role: RoleInfo, callback: (b: BasicResponse) => void) {

    axios
        .post("/api/role/add", role)
        .then(res => {
            let response: BasicResponse = res.data;
            callback(response);
        })
}

/**
 * 删除角色方法
 * @param roleId 
 * @param callback 
 */
function delRole(roleId: number, callback: (b: BasicResponse) => void) {
    axios
        .post("/api/role/delete", roleId)
        .then(res => {
            let response: BasicResponse = res.data;
            callback(response)
        })
}

/**
 * 修改角色方法
 * @param role 
 * @param callback 
 */
function changeRole(role: RoleInfo, callback: (b: BasicResponse) => void) {
    axios
        .put("/api/role/update", role)
        .then(res => {
            let response: BasicResponse = res.data;
            callback(response)
        })
}

export {
    getRoles, addRole, delRole, changeRole
}