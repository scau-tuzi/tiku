package io.swagger.service;

import io.swagger.pojo.ProblemFullData;
import io.swagger.pojo.dao.Problem;
import io.swagger.pojo.dao.repos.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl extends BasicService<Problem> implements ProblemService {

    @Autowired
    private ProblemDataServiceImpl problemDataServiceImpl;

    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public List<ProblemFullData> getAll(Integer pageNumber, Integer pageSize) {

        List<Long> problemIdList = problemRepository.findIdList(PageRequest.of(pageNumber, pageSize));

        return problemDataServiceImpl.getFullDataByIds(problemIdList);
    }
}
