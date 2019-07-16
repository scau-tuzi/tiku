package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.Pool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoolRepository extends JpaRepository<Pool, Long> {
}
