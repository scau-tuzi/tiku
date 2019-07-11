package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
