package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述：问题实体Jpa操作类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {

    List<Problem> findAllByIdIn(List<Long> idList);

//    @Query(nativeQuery = true, value = "select id from problem where is_del=?1",
//            countQuery = "select count(*) from problem where is_del=?1")
//    Page<Object> findIdList(org.springframework.data.domain.Pageable pageable, Boolean isDel);

    @Modifying
    @Query(nativeQuery = true,
            value = "update problem set is_del=?2 where id=?1")
    int updateIsDelById(Long id, Boolean isDel);
}
