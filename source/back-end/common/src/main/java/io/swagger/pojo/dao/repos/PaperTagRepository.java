package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.PaperTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述：试卷所属标签实体Jpa操作类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Repository
public interface PaperTagRepository extends JpaRepository<PaperTag, Long> {
    List<PaperTag> findAllByTagIdEquals(Long id);

    List<PaperTag> findAllByIsDelAndPaperIdIn(Boolean isDel, List<Long> paperIds);

    void deleteAllByPaperIdIn(List<Long> paperIds);

    void deleteAllByPaperIdEquals(Long paperId);

    @Modifying
    @Query(nativeQuery = true,
            value = "update paper_tag set is_del=?2 where tag_id=?1")
    int updateIsDelByTagId(Long tagId, Boolean isDel);

    @Modifying
    @Query(nativeQuery = true,
            value = "update paper_tag set is_del=?2 where paper_id=?1")
    int updateIsDelByPaperId(Long paperId, Boolean isDel);
}
