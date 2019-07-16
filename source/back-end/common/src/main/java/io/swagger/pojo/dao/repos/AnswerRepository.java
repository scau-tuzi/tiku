package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述：问题答案实体Jpa操作类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByIdIn(List<Long> idList);

    @Modifying
    @Query(nativeQuery = true,
            value = "update answer set is_del=?2 where id=?1")
    int updateIsDelById(Long id, Boolean isDel);

}
