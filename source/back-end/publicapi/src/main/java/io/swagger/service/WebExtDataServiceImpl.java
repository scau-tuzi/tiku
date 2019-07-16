package io.swagger.service;

import io.swagger.pojo.dao.ExtData;
import io.swagger.pojo.dao.repos.ExtDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebExtDataServiceImpl extends BasicService<ExtData> implements WebExtDataService {

    @Autowired
    private ExtDataRepository extDataRepository;

    @Override
    public List<ExtData> addAll(List<ExtData> extDataList, Long createBy) {
        for (ExtData extData : extDataList) {
            super.beforeAdd(extData, createBy);
        }

        return extDataRepository.saveAll(extDataList);
    }

    @Override
    public int deleteByProblemId(Long problemId) {
        return extDataRepository.updateIsDelByProblemId(problemId, Boolean.TRUE);
    }
}
