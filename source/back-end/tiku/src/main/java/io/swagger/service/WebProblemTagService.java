package io.swagger.service;

import io.swagger.pojo.dao.ProblemTag;

import java.util.List;

public interface WebProblemTagService {

    List<ProblemTag> addAll(List<ProblemTag> problemTagList, Long createBy);
}
