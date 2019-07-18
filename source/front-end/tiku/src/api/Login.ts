import axios from 'axios'
import UserInfo from '@/data/model/UserInfo';
import BasicResponse from '@/data/model/BasicResponse';


function login(username:string,pwd:string,callback: (u: UserInfo[]) => void) {
  axios
      .post("/api/user/list")
      .then(res => {
        let lists: UserInfo[] = res.data;
        callback(lists)
      })
}