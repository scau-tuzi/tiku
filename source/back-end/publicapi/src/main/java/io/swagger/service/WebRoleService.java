package io.swagger.service;

import io.swagger.pojo.dao.Role;

import java.util.List;
import java.util.Map;

public interface WebRoleService {

    String selectRolenameByRoleId(Long id);

    int deleteBasicInfo(Long id);

    Map<Long, String> selectRole();

    void add(Role role, Long createBy) throws Exception;

    void deleteAll(List<Long> idList) throws Exception;

    void delete(Long id) throws Exception;

    Map<String, Object> list(Integer pageNumber, Integer pageSize);

    void update(Role role, Long updateBy) throws Exception;


}
