/**
 * 用户模型
 */
interface UserInfo {
    /**
     * @id 用户id
     */
    id?: number,

    /**
     * @username 用户名字
     */
    username: string,

    /**
     * @password 用户密码
     */
    password: string,

    /**
     * @imag 用户头像 url 路径
     */
    image?: string,
    
    /**
     * @roleId 用户角色id
     */
    roleId?: number
}
export default UserInfo;