package io.swagger.service;

import io.swagger.pojo.dao.Permission;

import java.util.List;
import java.util.Map;

public interface WebPermissionService {
    void add(Permission permission, Long createBy) throws Exception;

    int deleteBasicInfo(Long id);

    void deleteAll(List<Long> idList) throws Exception;

    void delete(Long id) throws Exception;

    Map<String, Object> list(Integer pageNumber, Integer pageSize);

    void update(Permission permission, Long updateBy) throws Exception;
}
