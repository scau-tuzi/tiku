package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能描述：角色实体Jpa操作类
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
