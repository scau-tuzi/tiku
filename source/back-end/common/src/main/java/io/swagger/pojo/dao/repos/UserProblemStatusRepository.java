package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.UserProblemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProblemStatusRepository extends JpaRepository<UserProblemStatus,Long> {
}
