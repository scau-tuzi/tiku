package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
