package io.swagger.service;

import io.swagger.pojo.dao.Status;

public interface WebStatusService {

    Status add(Status status, Long createBy);

    int deleteByProblemId(Long problemId);

    Status update(Status status, Long updateBy);

    Status updateVerifyStatus(Long problemId, Integer verifyStatus, Long updateBy);
}
