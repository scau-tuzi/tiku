package io.swagger.service;

import io.swagger.pojo.dao.BasicEntity;

import java.util.Date;

/**
 * 问题描述：基础业务逻辑类
 *
 * @作者：黄宽波
 * @时间：2019-07-12
 */
public class BasicService<T extends BasicEntity> {

    /**
     * 插入前设置创建时间、创建人、更新时间、更新人
     * 更新人 = 创建人 更新时间 = 创建时间
     *
     * @param entity
     * @param createBy 创建人id
     */
    public void beforeAdd(T entity, Long createBy) {
        entity.setCreateBy(createBy);
        entity.setCreateAt(new Date());
        entity.setUpdateBy(entity.getCreateBy());
        entity.setUpdateAt(entity.getCreateAt());
    }

    /**
     * 更新前设置更新时间、更新人
     *
     * @param entity
     * @param updateBy 更新人id
     */
    public void beforeUpdate(T entity, Long updateBy) {
        entity.setUpdateBy(updateBy);
        entity.setUpdateAt(new Date());
    }

    /**
     * 删除前设置标志位
     *
     * @param entity
     */
    public void beforeDelete(T entity) {
        entity.setIsDel(Boolean.TRUE);
    }
}
