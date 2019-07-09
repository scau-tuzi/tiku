package cn.czfshine.tiku.pojo.dao.repos;

import cn.czfshine.tiku.pojo.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能描述：用户实体Jpa操作类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
