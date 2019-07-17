package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述：问题状态实体Jpa操作类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    List<Status> findAllByProblemIdIn(List<Long> problemIdList);

    @Modifying
    @Query(nativeQuery = true,
            value = "update status set is_del=?2 where problem_id=?1")
    int updateIsDelByProblemId(Long problemId, Boolean isDel);

    Status findByProblemId(Long problemId);

    @Query(nativeQuery = true, value = "select problem_id from status where verify_status = ?1 and is_del=?2",
            countQuery = "select count(*) from status where verify_status = ?1 and is_del=?2")
    Page<Object> findProblemIdList(org.springframework.data.domain.Pageable pageable, Integer verifyStatus, Boolean isDel);
}






