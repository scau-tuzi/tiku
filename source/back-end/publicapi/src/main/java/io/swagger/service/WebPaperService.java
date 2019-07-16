package io.swagger.service;

import io.swagger.pojo.PaperFullData;
import io.swagger.pojo.dao.Paper;

import java.util.Map;

public interface WebPaperService {

    Map<String, Object> getAll(Integer pageNumber, Integer pageSize, Boolean isDeep, Boolean isDel);

    Long add(PaperFullData paperFullData, Long createBy) throws Exception;

    Paper addBasicInfo(Paper paper, Long createBy);

    void update(PaperFullData paperFullData, Long updateBy) throws Exception;

    Paper updateBasicInfo(Paper paper, Long updateBy);

    void delete(Long id);

    int deleteBasicInfo(Long id);
}
