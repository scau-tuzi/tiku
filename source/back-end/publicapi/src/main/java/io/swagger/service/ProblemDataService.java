package io.swagger.service;

import io.swagger.pojo.ProblemFullData;

import java.util.List;

public interface ProblemDataService {
    List<ProblemFullData> getFullDataByIds(List<Long> problemIds);
}
