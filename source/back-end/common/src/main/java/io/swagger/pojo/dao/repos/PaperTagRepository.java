package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.PaperTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(nativeQuery = true,
            value = "select paper_id from paper_tag where tag_id in ?1 and is_del=?2 " +
                    "group by paper_id having count(paper_id)=?3",
            countQuery = "select count(paper_id) from paper_tag where tag_id in ?1 and is_del=?2 " +
                    "group by paper_id having count(paper_id)=?3")
    Page<Object> findPaperIdListByTagIdList(
            Pageable pageable, List<Long> tagIdList, Boolean isDel, Integer tagNumber);
}
