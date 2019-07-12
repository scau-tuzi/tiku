package io.swagger.service;

import com.sun.org.glassfish.external.statistics.Stats;
import io.swagger.pojo.dao.Status;
import io.swagger.pojo.dao.repos.StatusRepository;
import org.springframework.beans.BeanUtils;
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

    @Override
    public int deleteByProblemId(Long problemId) {
        return statusRepository.updateIsDelByProblemId(problemId, Boolean.TRUE);
    }

    @Override
    public Status update(Status status, Long updateBy) {
        Status dbStatus = statusRepository.findByProblemId(status.getProblemId());
        dbStatus.setVerifyStatus(status.getVerifyStatus());
        super.beforeUpdate(dbStatus, updateBy);
        return statusRepository.save(status);
    }
}
