package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Query(nativeQuery = true,
            value = "update paper set is_del=?2 where id=?1")
    int updateIsDelById(Long id, Boolean isDel);

    @Query(nativeQuery = true, value = "select id from paper where is_del=?1",
            countQuery = "select count(*) from paper where is_del=?1")
    Page<Object> findIdList(Pageable pageable, Boolean isDel);
}
