package io.swagger.pojo.dao.repos;

import io.swagger.pojo.dao.UserProblemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProblemStatusRepository extends JpaRepository<UserProblemStatus, Long> {

    List<UserProblemStatus> findAllByProblemIdIn(List<Long> problemIds);

    List<UserProblemStatus> findAllByUserUuidIn(List<String> uuids);

    List<UserProblemStatus> findAllByUserUuid(String uuid);

    List<UserProblemStatus> findAllByStatusContains(String status);
}
