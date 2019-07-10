import MenuInfo from "@/data/model/MenuInfo";
import Home from "@/views/Home.vue";
import About from "@/views/About.vue";


let mockData:MenuInfo={
    items:[{
        title:"项目管理",
        subItems:[
            {
                title:"子菜单一",
                path:"/page1",
            },
            {
                title:"子菜单二",
                path:"/page2",
            }
        ]
    },
        {
            title:"主菜单2",
            subItems:[
                {
                    title:"子菜单一",
                    path:"/1",
                },
                {
                    title:"子菜单二",
                    path:"/1",
                }
            ]
        }]
};

export default mockData;