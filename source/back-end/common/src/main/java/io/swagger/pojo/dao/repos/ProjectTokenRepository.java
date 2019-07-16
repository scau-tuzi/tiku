package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.ProjectToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTokenRepository extends JpaRepository<ProjectToken, Long> {
}
