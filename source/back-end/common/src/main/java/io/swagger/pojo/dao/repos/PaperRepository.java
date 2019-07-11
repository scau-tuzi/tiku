package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述：试卷实体Jpa操作类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Repository
public interface PaperRepository extends JpaRepository<Paper, Long> {
    List<Paper> findAllByIdIn(List<Long> paperIdList);
}
