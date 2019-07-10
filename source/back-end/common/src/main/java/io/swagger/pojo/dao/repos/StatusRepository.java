package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.Status;
import org.springframework.data.jpa.repository.JpaRepository;
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

    List<Status> findAllByProblemId(List<Long> problemIdList);
}
