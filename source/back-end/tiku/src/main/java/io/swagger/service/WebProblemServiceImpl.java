package io.swagger.service;

import io.swagger.model.Pagination;
import io.swagger.pojo.ProblemFullData;
import io.swagger.pojo.dao.*;
import io.swagger.pojo.dao.repos.ExtDataRepository;
import io.swagger.pojo.dao.repos.ProblemRepository;
import io.swagger.pojo.dao.repos.ProblemTagRepository;
import io.swagger.pojo.dao.repos.StatusRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private ProblemTagRepository problemTagRepository;

    @Autowired
    private ExtDataRepository extDataRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Map<String, Object> getAll(Integer pageNumber, Integer pageSize, Integer verifyStatus, Boolean isDel) {

        Map<String, Object> resultMap = new HashMap<>();

        //查找符合条件的问题id
        Page<Object> page = statusRepository.findProblemIdList(PageRequest.of(pageNumber, pageSize), verifyStatus, isDel);

        //分页信息
        Pagination pagination = new Pagination();
        pagination.setPage(BigDecimal.valueOf(page.getNumber()));
        pagination.setSize(BigDecimal.valueOf(page.getSize()));
        pagination.setTotal(BigDecimal.valueOf(page.getTotalPages()));

        //将id存储进id列表
        List<Long> problemIdList = new ArrayList<>();
        for (Object id : page.getContent()) {
            problemIdList.add(Long.parseLong(id.toString()));
        }

        //问题具体信息
        List<ProblemFullData> problemFullDataList = problemDataService.getFullDataByIds(problemIdList);

        resultMap.put("pagination", pagination);
        resultMap.put("problemFullDataList", problemFullDataList);

        return resultMap;
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
         * 新增问题答案
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {

        /**
         * 删除问题答案
         */
        Problem problem = problemRepository.findById(id).get();
        webAnswerServiceImpl.deleteById(problem.getAnswerId());

        /**
         * 删除问题基本信息
         */
        this.deleteBasicInfo(id);

        /**
         * 删除问题状态
         */
        webStatusServiceImpl.deleteByProblemId(id);

        /**
         * 删除问题标签
         */
        webProblemTagServiceImpl.deleteByProblemId(id);

        /**
         * 删除问题扩展属性
         */
        webExtDataServiceImpl.deleteByProblemId(id);

    }

    @Override
    public int deleteBasicInfo(Long id) {
        return problemRepository.updateIsDelById(id, Boolean.TRUE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProblemFullData problemFullData, Long updateBy) throws Exception {

        Answer answer = problemFullData.getAnswer();
        List<Tag> tagList = problemFullData.getTags();
        Problem problem = problemFullData.getProblem();
        Status status = problemFullData.getStatus();
        Map<String, String> extData = problemFullData.getExtData();

        if (problem == null || problem.getId() == null) {
            throw new Exception("Param error : problem should not be null And problemId should not be null");
        }

        /**
         * 修改问题基本信息
         */
        problem = this.updateBasicInfo(problem, updateBy);

        /**
         * 修改问题答案
         */
        if (answer != null) {
            answer.setId(problem.getAnswerId());
            webAnswerServiceImpl.update(answer, updateBy);
        }

        /**
         * 修改问题状态
         */
        if (status != null) {
            status.setProblemId(problem.getId());
            webStatusServiceImpl.update(status, updateBy);
        }

        /**
         * 修改问题标签
         */
        if (tagList != null && tagList.size() > 0) {
            //先删除已关联的标签关系
            problemTagRepository.deleteAllByProblemIdEquals(problem.getId());

            //再添加重新关联的标签关系
            List<ProblemTag> problemTagList = new ArrayList<>();

            for (Tag tag : tagList) {
                ProblemTag problemTag = new ProblemTag();
                problemTag.setProblemId(problem.getId());
                problemTag.setTagId(tag.getId());
                problemTagList.add(problemTag);
            }
            webProblemTagServiceImpl.addAll(problemTagList, updateBy);
        }

        /**
         * 修改问题扩展属性
         */
        if (extData != null && extData.size() > 0) {
            //先删除已关联的扩展属性
            extDataRepository.deleteAllByProblemId(problem.getId());

            //再添加重新关联的扩展属性
            List<ExtData> extDataList = new ArrayList<>();
            for (String key : extData.keySet()) {
                ExtData extDataEntity = new ExtData();
                extDataEntity.setProblemId(problem.getId());
                extDataEntity.setKeyname(key);
                extDataEntity.setValue(extData.get(key));
                extDataList.add(extDataEntity);
            }
            webExtDataServiceImpl.addAll(extDataList, updateBy);
        }
    }

    @Override
    public Problem updateBasicInfo(Problem problem, Long updateBy) {
        Problem dbProblem = problemRepository.findById(problem.getId()).get();
        dbProblem.setProblemText(problem.getProblemText());
        dbProblem.setParentId(problem.getParentId());
        super.beforeUpdate(dbProblem, updateBy);
        return problemRepository.save(dbProblem);
    }
}
