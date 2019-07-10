package cn.czfshine.tiku.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 功能描述：角色权限实体类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Data
@Table(name = "role_permission")
@Entity
@Accessors(chain = true)
@IdClass(RolePermissionPK.class)
public class RolePermission extends BasicEntity {

    /**
     * 角色id
     */
    @Id
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 权限id
     */
    @Id
    @Column(name = "permission_id")
    private Long permissionId;
}
