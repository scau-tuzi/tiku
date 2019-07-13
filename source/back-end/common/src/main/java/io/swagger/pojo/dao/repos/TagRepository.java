package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述：标签实体Jpa操作类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{
    List<Tag> findByValueEquals(String value);

    List<Tag> findAllByIdIn(List<Long> id);

    Tag findTagById(Long id);

    @Modifying
    @Query(nativeQuery = true,
            value = "update tag set is_del=?2 where id=?1")
    int updateIsDelById(Long id, Boolean isDel);

    @Query(nativeQuery = true, value = "select * from tag where is_del=?1",
            countQuery = "select count(*) from tag where is_del=?1")
    Page<Tag> findAllByIsDel(Pageable pageable, Boolean isDel);

}
