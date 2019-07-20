/**
 * 角色模型对象
 */
interface RoleInfo {
    /**
     * @id 角色的id
     */
    id?: number,

    /**
     * @roleName 角色的名字
     */
    roleName: string,

    /**
     * @permissionList 角色对应的权限名字列表
     */
    permissionList: string[]
}