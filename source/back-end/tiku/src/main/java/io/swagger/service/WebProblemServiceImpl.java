package io.swagger.service;

import io.swagger.pojo.ProblemFullData;
import io.swagger.pojo.dao.repos.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebProblemServiceImpl implements WebProblemService {

    @Autowired
    private ProblemDataService problemDataService;

    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public List<ProblemFullData> getAll(Integer pageNumber, Integer pageSize) {

        List<Long> problemIdList = problemRepository.findIdList(PageRequest.of(pageNumber, pageSize));

        return problemDataService.getFullDataByIds(problemIdList);
    }
}
