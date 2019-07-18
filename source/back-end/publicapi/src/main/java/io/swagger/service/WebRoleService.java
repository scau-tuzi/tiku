package io.swagger.service;

import io.swagger.pojo.dao.Role;
import io.swagger.pojo.dto.RoleDto;

import java.util.List;
import java.util.Map;

public interface WebRoleService {

    String selectRolenameByRoleId(Long id);

    int deleteBasicInfo(Long id);

    Map<Long, String> selectRole();

    int deletePermission(Long id);

    int detect(List<Long> permissionList) throws Exception;

    void add(RoleDto roleDto, Long createBy) throws Exception;

    void deleteAll(List<Long> idList) throws Exception;

    void delete(Long id) throws Exception;

    Map<String, Object> list(Integer pageNumber, Integer pageSize);

    void update(RoleDto roleDto, Long updateBy) throws Exception;


}
