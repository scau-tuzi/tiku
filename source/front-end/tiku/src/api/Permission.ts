
import axios from 'axios'


function getPermissionTree(callback: (p: PermissionInfo[]) => void) {
  axios
      .get("/api/permission/list")//服务器地址
      .then(res => {
        let lists: PermissionInfo[] = res.data.data;
        callback(lists)
      })
}

export {
  getPermissionTree
}