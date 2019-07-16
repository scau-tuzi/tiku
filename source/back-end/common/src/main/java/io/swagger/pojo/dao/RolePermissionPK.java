package io.swagger.pojo.dao;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能描述：角色权限-复合主键类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Data
public class RolePermissionPK implements Serializable {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long permissionId;
}
