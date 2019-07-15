package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.PaperItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述：试卷项实体Jpa操作类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Repository
public interface PaperItemRepository extends JpaRepository<PaperItem, Long> {

    List<PaperItem> findAllByIsDelAndPaperIdIn(Boolean isDel, List<Long> paperIdList);

    void deleteAllByPaperIdEquals(Long paperId);

    @Modifying
    @Query(nativeQuery = true,
            value = "update paper_item set is_del=?2 where problem_id=?1")
    int updateIsDelByProblemId(Long problemId, Boolean isDel);

    @Modifying
    @Query(nativeQuery = true,
            value = "update paper_item set is_del=?2 where paper_id=?1")
    int updateIsDelByPaperId(Long paperId, Boolean isDel);
}
