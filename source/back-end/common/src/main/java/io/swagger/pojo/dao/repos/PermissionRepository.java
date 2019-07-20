package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述：权限实体Jpa操作类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByIdEquals(Long id);

    Permission findByName(String name);

    List<Permission> findAllByIsDelEquals(Boolean isDel);

    @Query(nativeQuery = true,
            value = "select * from permission where parent_permission=0")
    List<Permission> selectParentPermissons();

    @Query(nativeQuery = true,
            value = "select * from permission where parent_permission=?1")
    List<Permission> selectChildPermissons(Long id);

    @Query(nativeQuery = true,
            value = "select * from permission where roleId=?1")
    List<Permission> selectPermissionsRoleIdEquals(Long id);

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT id from permission")
    List<Long> selectPermissionId();

    @Modifying
    @Query(nativeQuery = true,
            value = "update permission set is_del=?2 where id=?1")
    int updateIsDelById(Long id, Boolean isDel);


    @Query(nativeQuery = true, value = "select * from permission where is_del=?1",
            countQuery = "select count(*) from permission where is_del=?1")
    Page<Permission> findAllByIsDel(Pageable pageable, Boolean isDel);
}
