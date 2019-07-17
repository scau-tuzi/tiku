package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述：角色实体Jpa操作类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Modifying
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT id from role")
    List<Long> selectRoleId();


    @Modifying
    @Query(nativeQuery = true,
            value = "SELECT rolename from role  where role_id=?1")
    String selectRolenameByRoleId(Long role_id);

    Role findByIdEquals(Long id);

}
