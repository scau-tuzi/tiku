package io.swagger.service;

import io.swagger.model.StatusInfo;

public interface ProblemStatusService {

    void updateProblemStatus(StatusInfo statusInfo) throws ProblemStatusServiceImpl.ProblemStatusArgumentException;
}
