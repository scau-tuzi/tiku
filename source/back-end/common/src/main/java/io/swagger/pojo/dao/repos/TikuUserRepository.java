package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.TikuUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TikuUserRepository extends JpaRepository<TikuUser, Long> {
    List<TikuUser> findAllByUserUuidEquals(String userUuid);
}
