/**
 * 标签模型对象
 */
interface TagInfo {
    /**
     * @id 标签的id
     */
    id?: number,

    /**
     * @value 标签的内容
     */
    value: string,

    /**
     * @parentId 父标签的id
     */
    parentId?: number
}
export default TagInfo;