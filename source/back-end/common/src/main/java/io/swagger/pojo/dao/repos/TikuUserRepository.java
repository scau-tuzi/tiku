package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.TikuUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TikuUserRepository extends JpaRepository<TikuUser, Long> {
    Optional<TikuUser> findByUserUuidEquals(String userUuid);
}
