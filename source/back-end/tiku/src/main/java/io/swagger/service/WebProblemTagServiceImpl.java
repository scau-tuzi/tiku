package io.swagger.service;

import io.swagger.pojo.dao.ProblemTag;
import io.swagger.pojo.dao.repos.ProblemTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class WebProblemTagServiceImpl extends BasicService<ProblemTag> implements WebProblemTagService {

    @Autowired
    private ProblemTagRepository problemTagRepository;

    @Override
    public List<ProblemTag> addAll(List<ProblemTag> problemTagList, Long createBy) {
        for (ProblemTag problemTag : problemTagList) {
            super.beforeAdd(problemTag, createBy);
        }
        return problemTagRepository.saveAll(problemTagList);
    }

    @Override
    public int deleteByProblemId(Long problemId) {
        return problemTagRepository.updateIsDelByProblemId(problemId, Boolean.TRUE);
    }
}
