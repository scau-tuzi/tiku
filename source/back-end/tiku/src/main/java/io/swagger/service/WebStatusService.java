package io.swagger.service;

import io.swagger.pojo.dao.Status;

public interface WebStatusService {

    Status add(Status status, Long createBy);
}
