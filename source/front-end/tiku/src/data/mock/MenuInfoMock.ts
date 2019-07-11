import MenuInfo from "@/data/model/MenuInfo";

let mockData:MenuInfo={
  items:[{
    title:"题目",
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
      title:"主菜单",
      subItems:[
        {
          title:"子菜单一",
          // component:InputTiku,
          path:"/InputTiku",
        },
        {
          title:"子菜单二",
          // component:Home,
          path:"/1",
        }
      ]
    }]
};

export default mockData;
