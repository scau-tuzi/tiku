package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 功能描述：用户实体Jpa操作类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByIdEquals(Long id);


    @Modifying
    @Query(nativeQuery = true,
            value = "update user set is_del=?2 where id=?1")
    int updateIsDelById(Long id, Boolean isDel);

    @Query(nativeQuery = true, value = "select * from user where is_del=?1",
            countQuery = "select count(*) from user where is_del=?1")
    Page<User> findAllByIsDel(Pageable pageable, Boolean isDel);

}
