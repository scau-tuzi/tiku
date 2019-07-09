package cn.czfshine.tiku.pojo.dao.repos;

import cn.czfshine.tiku.pojo.dao.ProblemTag;
import cn.czfshine.tiku.pojo.dao.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述：问题所属标签实体Jpa操作类
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Repository
public interface ProblemTagRepository extends JpaRepository<ProblemTag, Long>{
    List<ProblemTag> findAllByTagIdEquals(Long tadId);
}
