package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述：角色权限实体Jpa操作类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

    @Modifying
    @Query(nativeQuery = true,
            value = "update role_permission set is_del=?2 where role_id=?1")
    int updateIsDelByRoleId(Long roleId, Boolean isDel);


    @Query(nativeQuery = true,
            value = "select permission_id from role_permission where role_id=?1")
    List<Long> findPermissionIdsByRoleIdEquals(Long id);

}
