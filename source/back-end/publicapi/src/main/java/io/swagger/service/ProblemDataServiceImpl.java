package io.swagger.service;

import cn.czfshine.tiku.pojo.ProblemFullData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemDataServiceImpl implements ProblemDataService {
    /**获取对应id的问题信息
     * @param problemIds
     * @return
     */
    @Override
    public List<ProblemFullData> getFullDataByIds(List<Long> problemIds) {
        return null;
    }
}
