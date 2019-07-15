package io.swagger.service;

import io.swagger.model.ProblemIdList;
import io.swagger.model.StatusInfo;
import io.swagger.pojo.dao.UserProblemStatus;

import java.util.List;

public interface ProblemStatusService {

    void updateProblemStatus(StatusInfo statusInfo) throws ProblemStatusServiceImpl.ProblemStatusArgumentException;

    List<UserProblemStatus> getProblemStatus(ProblemIdList problemIdList) throws ProblemStatusServiceImpl.ProblemStatusArgumentException;
}
