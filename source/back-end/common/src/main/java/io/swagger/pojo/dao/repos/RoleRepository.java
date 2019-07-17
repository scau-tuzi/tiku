package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Role findByIdEquals(Long id);

    Role findByRoleName(String roleName);

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT id from role")
    List<Long> selectRoleId();


    @Query(nativeQuery = true,
            value = "SELECT rolename from role  where role_id=?1")
    String selectRolenameByRoleId(Long role_id);
//
//
//    @Query(nativeQuery = true,
//            value = "SELECT  role_id from role  where rolename=?1")
//    Long findRoleIdByRolename(String rolename);

    @Modifying
    @Query(nativeQuery = true,
            value = "update role set is_del=?2 where id=?1")
    int updateIsDelById(Long id, Boolean isDel);

    @Query(nativeQuery = true, value = "select * from role where is_del=?1",
            countQuery = "select count(*) from role where is_del=?1")
    Page<Role> findAllByIsDel(Pageable pageable, Boolean isDel);



}
