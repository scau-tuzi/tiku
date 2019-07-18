import MenuInfo from "@/data/model/MenuInfo";

let mockData:MenuInfo={
  items:[{
    title:"题库管理",
    subItems:[
      {
        title:"首页",
        // component:Home, //todo 热加载脚本，不要编译期使用
        path:"/",
      },
      {
        title:"题目列表",
        // component:TikuTable,
        path:"/TikuTable",

      },
      {
        title:"审核列表",
        // component:VerifyTable,
        path:"/VerifyTable",

      }
    ]
  },
    {
      title:"试卷管理",
      subItems:[
        {
          title:"试卷列表",
          // component:InputTiku,
          path:"/PaperList",
        },
        {
          title:"新增试卷",
          // component:Home,
          path:"/CreatePaper",
        }
      ]
    },
    {
      title:"标签管理",
      subItems:[
        {
          title:"查看标签",
          // component:InputTiku,
          path:"/ViewTags",
        }
      ]
    },
    {
      title:"用户管理",
      subItems:[
        {
          title:"角色列表",
          // component:InputTiku,
          path:"/RoleTable",
        },
        {
          title:"用户列表",
          // component:Home,
          path:"/1",
        }
      ]
    },
    {
      title:"授权管理",
      subItems:[
        {
          title:"应用授权",
          // component:InputTiku,
          path:"/InputTiku",
        },
        {
          title:"应用角色",
          // component:Home,
          path:"/1",
        }
      ]
    }]
};

export default mockData;
