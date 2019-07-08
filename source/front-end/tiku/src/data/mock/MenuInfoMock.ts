import MenuInfo from "@/data/model/MenuInfo";
import Home from "@/views/Home.vue";
import About from "@/views/About.vue";


let mockData:MenuInfo={
    items:[{
        title:"项目管理",
        subItems:[
            {
                title:"子菜单一",
                component:Home, //todo 热加载脚本，不要编译期使用
                path:"/1",
            },
            {
                title:"子菜单二",
                component:About,
                path:"/1",
            }
        ]
    },
        {
            title:"主菜单2",
            subItems:[
                {
                    title:"子菜单一",
                    component:Home,
                    path:"/1",
                },
                {
                    title:"子菜单二",
                    component:Home,
                    path:"/1",
                }
            ]
        }]
};

export default mockData;