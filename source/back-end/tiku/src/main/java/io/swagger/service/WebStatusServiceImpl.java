package io.swagger.service;

import io.swagger.pojo.dao.Status;
import io.swagger.pojo.dao.repos.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebStatusServiceImpl extends BasicService<Status> implements WebStatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Status add(Status status, Long createBy) {
        super.beforeAdd(status, createBy);
        return statusRepository.save(status);
    }
}
