package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Modifying
    @Query(nativeQuery = true,
            value = "update user_role set is_del=?2 where user_id=?1")
    int updateIsDelByUserId(Long userId, Boolean isDel);



    @Query(nativeQuery = true,
            value = "select role_id from user_role where is_del=?2 and user_id=?1")
    List<Long> findRoleIdsByUserIdEqualsAndIsDel(Long id, Boolean is_del);
}
