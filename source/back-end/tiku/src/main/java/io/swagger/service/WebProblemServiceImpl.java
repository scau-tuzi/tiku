package io.swagger.service;

import com.sun.org.glassfish.external.probe.provider.annotations.ProbeListener;
import io.swagger.pojo.ProblemFullData;
import io.swagger.pojo.dao.*;
import io.swagger.pojo.dao.repos.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import io.swagger.service.BasicService;
import java.util.List;
import java.util.Map;

@Service
public class WebProblemServiceImpl extends BasicService<Problem> implements WebProblemService {

    @Autowired
    private ProblemDataService problemDataService;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private WebAnswerServiceImpl webAnswerServiceImpl;

    @Autowired
    private WebStatusServiceImpl webStatusServiceImpl;

    @Autowired
    private WebExtDataServiceImpl webExtDataServiceImpl;

    @Autowired
    private WebProblemTagServiceImpl webProblemTagServiceImpl;

    @Override
    public List<ProblemFullData> getAll(Integer pageNumber, Integer pageSize) {

        List<Long> problemIdList = problemRepository.findIdList(PageRequest.of(pageNumber, pageSize));

        return problemDataService.getFullDataByIds(problemIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ProblemFullData problemFullData, Long createBy) throws Exception {

        Answer answer = problemFullData.getAnswer();
        List<Tag> tagList = problemFullData.getTags();
        Problem problem = problemFullData.getProblem();
        Status status = problemFullData.getStatus();
        Map<String, String> extData = problemFullData.getExtData();

        if (problem == null || answer == null) {
            throw new Exception("Param error : problem should not be null And answer should not be null");
        }

        /**
         * 新增答案
         */
        answer = webAnswerServiceImpl.add(answer, createBy);

        /**
         * 新增问题基础信息
         */
        problem.setAnswerId(answer.getId());
        problem = this.addBasicInfo(problem, createBy);

        /**
         * 新增题目状态
         */
        status = (status == null ? new Status() : status);
        status.setVerifyStatus(Status.UNCHECK);
        status.setProblemId(problem.getId());
        webStatusServiceImpl.add(status, createBy);

        /**
         * 新增题目标签
         */
        if (tagList != null && tagList.size() > 0) {
            List<ProblemTag> problemTagList = new ArrayList<>();
            for (Tag tag : tagList) {
                ProblemTag problemTag = new ProblemTag();
                problemTag.setTagId(tag.getId());
                problemTag.setProblemId(problem.getId());
                problemTagList.add(problemTag);
            }
            webProblemTagServiceImpl.addAll(problemTagList, createBy);
        }


        /**
         * 新增题目扩展属性
         */
        if (extData != null && extData.size() > 0) {
            List<ExtData> extDataList = new ArrayList<>();
            for (String key : extData.keySet()) {
                ExtData extDataEntity = new ExtData();
                extDataEntity.setKeyname(key);
                extDataEntity.setValue(extData.get(key));
                extDataEntity.setProblemId(problem.getId());
                extDataList.add(extDataEntity);
            }
            webExtDataServiceImpl.addAll(extDataList, createBy);
        }
    }

    @Override
    public Problem addBasicInfo(Problem problem, Long createBy) {
        super.beforeAdd(problem, createBy);
        return problemRepository.save(problem);
    }

}
