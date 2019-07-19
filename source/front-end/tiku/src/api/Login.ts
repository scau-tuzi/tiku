import axios from 'axios'
import UserInfo from '@/data/model/UserInfo';
import BasicResponse from '@/data/model/BasicResponse';


function login(username:string,pwd:string,callback: (u: BasicResponse) => void) {
  let u:UserInfo={
    username:username,
    password:pwd
  };
  axios
      .post("/web/login",u)
      .then(res => {
        let lists: BasicResponse = res.data;
        callback(lists)
      })
}
export  {
  login
}