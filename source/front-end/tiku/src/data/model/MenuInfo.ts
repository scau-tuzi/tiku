interface MenuInfo {
    items:{
        title:string;
        subItems:{
            title:string;
            path:string;
        }[]
    }[];
}
export default MenuInfo;