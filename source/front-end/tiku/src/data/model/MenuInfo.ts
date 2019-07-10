interface MenuInfo {
    items:{
        title:string;
        subItems:{
            title:string;
            //component:any;
            path:string;
        }[]
    }[];
}
export default MenuInfo;