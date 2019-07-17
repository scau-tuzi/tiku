package io.swagger.service;

import io.swagger.pojo.ProblemFullData;
import io.swagger.pojo.dao.Problem;

import java.util.List;
import java.util.Map;

public interface WebProblemService {

    Map<String, Object> getAll(Integer pageNumber, Integer pageSize, Integer verifyStatus, Boolean isDel);

    Map<String, Object> getAllByTagIdList(List<Long> tagIdList, Integer pageNumber, Integer pageSize, Integer verifyStatus);

    Long add(ProblemFullData problemFullData, Long createBy) throws Exception;

    Problem addBasicInfo(Problem problem, Long createBy);

    void deleteAll(List<Long> idList);

    void delete(Long id);

    int deleteBasicInfo(Long id);

    void update(ProblemFullData problemFullData, Long updateBy) throws Exception;

    Problem updateBasicInfo(Problem problem, Long updateBy);

    void check(Long id, Integer checkStatus, Long updateBy);


}
