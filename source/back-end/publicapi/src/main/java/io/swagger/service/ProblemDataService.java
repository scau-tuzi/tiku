package io.swagger.service;

import cn.czfshine.tiku.pojo.ProblemFullData;

import java.util.List;

public interface ProblemDataService {
    List<ProblemFullData> getFullDataByIds(List<Long> problemIds);
}
