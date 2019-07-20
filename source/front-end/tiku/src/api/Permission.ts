
import axios from 'axios'
import BasicResponse from "@/data/model/BasicResponse";


function getPermissionTree(callback: (p: PermissionInfo[]) => void) {
  axios
      .get("/api/permission/list")//服务器地址
      .then(res => {
        let lists: PermissionInfo[] = res.data.data;
        callback(lists)
      })
}

function updatePermission(data:PermissionInfo, callback: (p: BasicResponse) => void) {
  axios
      .put("/api/permission/update",data)//服务器地址
      .then(res => {
        let lists: BasicResponse = res.data;
        callback(lists)
      })
}

function addPermission(data:PermissionInfo, callback: (p: BasicResponse) => void) {
  axios
      .post("/api/permission/add",data)//服务器地址
      .then(res => {
        let lists: BasicResponse = res.data;
        callback(lists)
      })
}
export {
  getPermissionTree,
  updatePermission
}