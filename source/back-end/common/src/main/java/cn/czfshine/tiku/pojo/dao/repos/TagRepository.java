package cn.czfshine.tiku.pojo.dao.repos;

import cn.czfshine.tiku.pojo.dao.Tag;
import cn.czfshine.tiku.pojo.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 功能描述：标签实体Jpa操作类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{
    Tag findDistinctByValue(String value);

    List<Tag> findAllById(List<Long> id);
}
